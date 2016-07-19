package appCars.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.Date;

import org.springframework.security.crypto.bcrypt.BCrypt;

import appCars.Model.Trajet;

public class TrajetManagerDB {

	private Connection connection;
	
	public TrajetManagerDB() {
			try {
				this.connection = this.getConnection();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
					+ "trajets (nom,places,start_lat,start_lng,end_lat,end_lng,depart,user_id,status) VALUES(?,?,?,?,?,?,?,?,?);";
			stmt = this.connection.prepareStatement(trajetSQL);
			stmt.setString(1, trajet.getNom());
			stmt.setInt(2, trajet.getPlaces());
			stmt.setDouble(3, trajet.getStart_lat());
			stmt.setDouble(4, trajet.getStart_lng());
			stmt.setDouble(5, trajet.getEnd_lat());
			stmt.setDouble(6, trajet.getEnd_lng());
			stmt.setDate(7, trajet.getDepart());
			stmt.setInt(8, trajet.getUser_id());
			stmt.setBoolean(9, true);
			result = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return result == 1;
	}
	
	public Trajet getTrajet(int id){

		PreparedStatement stmt = null;
		Trajet trajet = null;
		ResultSet rs = null;
		int result = 0;
		try{
			String trajetSQL = "SELECT * FROM trajets WHERE id = ?";
			stmt = this.connection.prepareStatement(trajetSQL);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");
				int places = rs.getInt("places");
				double slat = rs.getDouble("start_lat");
				double slng = rs.getDouble("start_lng");
				double elat = rs.getDouble("end_lat");
				double elng = rs.getDouble("end_lng");
				Date date = rs.getDate("depart");
				int user_id = rs.getInt("user_id");
				int status = rs.getInt("status");
				trajet = new Trajet(id,name,places,slat,slng,elat,elng,date,user_id,status);
				break;
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return trajet;
	}
	
	public int TrajetPlaces(int id){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i = 0;
		try{
			String trajetSQL = "SELECT * FROM users_trajets WHERE trajet_id = ?";
			stmt = this.connection.prepareStatement(trajetSQL);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				i += rs.getInt("places");
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	
	public List<Trajet> GetTrajetsDispo(){
		PreparedStatement stmt = null;
		List<Trajet> trajets = null;
		ResultSet rs = null;
		try{
			String trajetSQL = "SELECT * FROM trajets where depart > ?";
			stmt = this.connection.prepareStatement(trajetSQL);
			String datestring = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime().getTime());
			stmt.setString(1, datestring);
			rs = stmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int places = rs.getInt("places");
				double slat = rs.getDouble("start_lat");
				double slng = rs.getDouble("start_lng");
				double elat = rs.getDouble("end_lat");
				double elng = rs.getDouble("end_lng");
				Date date = rs.getDate("depart");
				int user_id = rs.getInt("user_id");
				int status = rs.getInt("status");
				Trajet trajet = new Trajet(id,name,places,slat,slng,elat,elng,date,user_id,status);
				trajets.add(trajet);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return trajets;
	}
	
	public List<Trajet> GetTrajetPerso(int user_id){
		PreparedStatement stmt = null;
		List<Trajet> trajets = null;
		ResultSet rs = null;
		try{
			String trajetSQL = "SELECT * FROM trajets where user_id = ? ";
			stmt = this.connection.prepareStatement(trajetSQL);
			stmt.setInt(1, user_id);
			rs = stmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int places = rs.getInt("places");
				double slat = rs.getDouble("start_lat");
				double slng = rs.getDouble("start_lng");
				double elat = rs.getDouble("end_lat");
				double elng = rs.getDouble("end_lng");
				Date date = rs.getDate("depart");
				int status = rs.getInt("status");
				Trajet trajet = new Trajet(id,name,places,slat,slng,elat,elng,date,user_id,status);
				trajets.add(trajet);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return trajets;
	}

}
