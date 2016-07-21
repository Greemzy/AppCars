package appCars.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

import appCars.Model.Reservation;

public class ReservationManagerDB {

	private Connection connection;
	private static Logger log = Logger.getLogger(ReservationManagerDB.class.getName());
	
	public ReservationManagerDB() {
			try {
				this.connection = this.getConnection();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				log.log(Level.SEVERE, "Erreur de connexion bdd", e);
			}		
	}
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/freeshuttle";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection =  DriverManager.getConnection(url, "root","");
		return connection;
	}
	
	public boolean createReservation(Reservation reservation){
		PreparedStatement stmt = null;
		int result = 0;
		try{
			String reservSQL = "INSERT INTO "
					+ "users_trajets (trajet_id,user_id,place,date,status) VALUES(?,?,?,?,?);";
			stmt = this.connection.prepareStatement(reservSQL);
			stmt.setInt(1, reservation.getTrajet_id());
			stmt.setInt(2, reservation.getUser_id());
			stmt.setInt(3, reservation.getPlace());
			stmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			stmt.setInt(5, reservation.getStatus());
			result = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "CreateReservation", e);
		}
		return result == 1;
	}
	
	public boolean DeleteReservation(int id){
		PreparedStatement stmt = null;
		int result = 0;
		try{
			String reservSQL = "DELETE FROM users_trajets WHERE id = ?";
			stmt = this.connection.prepareStatement(reservSQL);
			stmt.setInt(1, id);
			result = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "deleteReservation", e);
		}
		return result == 1;
	}
	
	public Reservation getReservation(int id){

		PreparedStatement stmt = null;
		Reservation reservation = null;
		ResultSet rs = null;
		try{
			String trajetSQL = "SELECT * FROM users_trajets WHERE id = ?";
			stmt = this.connection.prepareStatement(trajetSQL);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				int trajet_id = rs.getInt("trajet_id");
				int user_id = rs.getInt("user_id");
				int place = rs.getInt("place");
				Date date = rs.getDate("date");
				int status = rs.getInt("status");
				reservation = new Reservation(id,trajet_id,user_id,place,date,status);
				break;
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "getReservation", e);
		}
		return reservation;
	}
	
	public List<Reservation> GetReservationPerso(int user_id){
		PreparedStatement stmt = null;
		List<Reservation> reservations = new ArrayList<>();
		ResultSet rs = null;
		try{
			String reservationsSQL = "SELECT users_trajets.id,users_trajets.trajet_id,users_trajets.place,users_trajets.date,users_trajets.status,trajets.nom FROM users_trajets INNER JOIN trajets on trajets.id = users_trajets.trajet_id where users_trajets.user_id = ?";
			stmt = this.connection.prepareStatement(reservationsSQL);
			stmt.setInt(1, user_id);
			rs = stmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				int trajet_id = rs.getInt("trajet_id");
				int place = rs.getInt("place");
				Date date = rs.getDate("date");
				int status = rs.getInt("status");
				Reservation reservation = new Reservation(id,trajet_id,user_id,place,date,status);
				String name = rs.getString("nom");
				reservation.setName(name);
				reservations.add(reservation);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "Réservation perso", e);
		}
		
		return reservations;
	}

}
