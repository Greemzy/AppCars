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

import appCars.Model.Trajet;

public class TrajetManagerDB {

	private Connection connection;
	private static Logger log = Logger.getLogger(TrajetManagerDB.class.getName());
	
	public TrajetManagerDB() {
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
	
	public boolean createTrajet(Trajet trajet){
		PreparedStatement stmt = null;
		int result = 0;
		try{
			String trajetSQL = "INSERT INTO "
					+ "trajets (nom,places,origin,destination,depart,user_id,status) VALUES(?,?,?,?,?,?,?);";
			stmt = this.connection.prepareStatement(trajetSQL);
			stmt.setString(1, trajet.getNom());
			stmt.setInt(2, trajet.getPlaces());
			stmt.setString(3, trajet.getOrigin());
			stmt.setString(4, trajet.getDestination());
			stmt.setDate(5, new java.sql.Date(trajet.getDepart().getTime()));
			stmt.setInt(6, trajet.getUser_id());
			stmt.setBoolean(7, true);
			result = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "create trajet bdd", e);
		}
		return result == 1;
	}
	
	public boolean DeleteTrajet(int id){
		PreparedStatement stmt = null;
		int result = 0;
		try{
			String trajetSQL = "UPDATE trajets SET status = ? WHERE id = ?";
			stmt = this.connection.prepareStatement(trajetSQL);
			stmt.setBoolean(1, false);
			stmt.setInt(2, id);
			result = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "delete trajet", e);
		}
		return result == 1;
	}
	
	public Trajet getTrajet(int id){

		PreparedStatement stmt = null;
		Trajet trajet = null;
		ResultSet rs = null;
		try{
			String trajetSQL = "SELECT * FROM trajets WHERE id = ?";
			stmt = this.connection.prepareStatement(trajetSQL);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				String name = rs.getString("nom");
				int places = rs.getInt("places");
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				Date date = rs.getDate("depart");
				int user_id = rs.getInt("user_id");
				int status = rs.getInt("status");
				trajet = new Trajet(id,name,places,origin,destination,date,user_id,status);
				break;
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "get trajet", e);
		}
		return trajet;
	}
	
	public int TrajetPlaces(int id){
		Trajet trajet = this.getTrajet(id);
		int i = 0;
		if(trajet != null){
			PreparedStatement stmt = null;
			ResultSet rs = null;
			i = trajet.getPlaces();
			try{
				String trajetSQL = "SELECT * FROM users_trajets WHERE trajet_id = ?";
				stmt = this.connection.prepareStatement(trajetSQL);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				while(rs.next()){
					i -= rs.getInt("place");
				}
				rs.close();
				stmt.close();
			}catch(SQLException e){
				log.log(Level.SEVERE, "Trajet Places", e);
			}
		}
		return i;
	}
	
	public List<Trajet> GetTrajetsDispo(){
		PreparedStatement stmt = null;
		List<Trajet> trajets = new ArrayList<>();
		ResultSet rs = null;
		try{
			String trajetSQL = "SELECT * FROM trajets where depart < ?";
			stmt = this.connection.prepareStatement(trajetSQL);
			// create a java calendar instance
			Date datestring = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			stmt.setDate(1, datestring);
			rs = stmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("nom");
				int places = rs.getInt("places");
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				Date date = rs.getDate("depart");
				int user_id = rs.getInt("user_id");
				int status = rs.getInt("status");
				Trajet trajet = new Trajet(id,name,places,origin,destination,date,user_id,status);
				trajets.add(trajet);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "get trajets dispo", e);
		}
		
		return trajets;
	}
	
	public List<Trajet> GetTrajetPerso(int user_id){
		PreparedStatement stmt = null;
		List<Trajet> trajets = new ArrayList<>();
		ResultSet rs = null;
		try{
			String trajetSQL = "SELECT * FROM trajets where user_id = ? ";
			stmt = this.connection.prepareStatement(trajetSQL);
			stmt.setInt(1, user_id);
			rs = stmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("nom");
				int places = rs.getInt("places");
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				Date date = rs.getDate("depart");
				int status = rs.getInt("status");
				Trajet trajet = new Trajet(id,name,places,origin,destination,date,user_id,status);
				trajets.add(trajet);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "get trajet perso", e);
		}
		
		return trajets;
	}

}
