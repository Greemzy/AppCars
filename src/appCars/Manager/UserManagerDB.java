package appCars.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import appCars.Model.User;

public class UserManagerDB {

	private Connection connection;
	
	public UserManagerDB() {
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
	
	public boolean createUser(User user){
		PreparedStatement stmt = null;
		int result = 0;
		try{
			String userSQL = "INSERT INTO "
					+ "users (firstname,lastname,password,email,salt,token,activated) VALUES(?, ?, ?, ?, ?, ?, ?);";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setString(1, user.getFirstname());
			stmt.setString(2, user.getLastname());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getSalt());
			stmt.setString(6, user.getToken());
			stmt.setBoolean(7, user.isActivated());
			
			result = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return result == 1;
	}
	
	public User getUser(String email){
		PreparedStatement stmt = null;
		User user = null;
		ResultSet rs = null;
		int result = 0;
		try{
			String userSQL = "SELECT * FROM users WHERE email = ?";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			while(rs.next()){
				String fistname = user.getFirstname();
				String lastname = user.getLastname();
				String password = user.getPassword();
				String email2 = user.getEmail();
				String salt = user.getSalt();
				String token = user.getToken();
				Boolean activated = user.isActivated();
				user = new User(fistname,lastname,password,email2);
				user.setSalt(salt);
				user.setToken(token);
				user.setActivated(activated);
				break;
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}

}
