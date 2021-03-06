package appCars.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCrypt;

import appCars.Model.User;

public class UserManagerDB {

	private Connection connection;
	private static Logger log = Logger.getLogger(UserManagerDB.class.getName());
	
	
	public UserManagerDB() {
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
	
	public boolean createUser(User user){
		PreparedStatement stmt = null;
		int result = 0;
		try{
			String userSQL = "INSERT INTO "
					+ "users (firstname,lastname,password,email,salt,token,activated) VALUES(?, ?, ?, ?, ?, ?, ?);";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setString(1, user.getFirstname());
			stmt.setString(2, user.getLastname());
			String salt = BCrypt.gensalt(12);
			stmt.setString(3, BCrypt.hashpw(user.getPassword(),salt));
			stmt.setString(4, user.getEmail());
			stmt.setString(5, salt);
			stmt.setString(6, user.getToken());
			stmt.setBoolean(7, user.isActivated());
			
			result = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "create user", e);
		}
		return result == 1;
	}
	
	public boolean DeleteUser(String email){
		PreparedStatement stmt = null;
		int result = 0;
		try{
			String userSQL = "DELETE FROM users WHERE email=?";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setString(1, email);
			result = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "delete user", e);
		}
		return result == 1;
	}
	
	public User getUser(String email){
		PreparedStatement stmt = null;
		User user = null;
		ResultSet rs = null;
		try{
			String userSQL = "SELECT * FROM users WHERE email = ?";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String fistname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String password = rs.getString("password");
				String email2 = rs.getString("email");
				String salt = rs.getString("salt");
				String token = rs.getString("token");
				Boolean activated = rs.getBoolean("activated");
				user = new User(fistname,lastname,email2,password);
				user.setId(id);
				user.setSalt(salt);
				user.setToken(token);
				user.setActivated(activated);
				break;
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "get user", e);
		}
		return user;
	}
	
	public boolean EmailExist(String email){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		try{
			String userSQL = "SELECT * FROM users WHERE email = ?";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			while(rs.next()){
				result = true;
				break;
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			log.log(Level.SEVERE, "Email exists", e);
		}
		return result;
	}

}
