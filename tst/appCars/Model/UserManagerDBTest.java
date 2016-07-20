package appCars.Model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import appCars.Manager.TrajetManagerDB;
import appCars.Manager.UserManagerDB;

public class UserManagerDBTest {

	private UserManagerDB manager = new UserManagerDB();
	private static Logger log = Logger.getLogger(UserManagerDBTest.class.getName());
	@Test
	public void testConnection() {
		try {
			assertNotNull("The manager should return a non null connection",manager.getConnection());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			log.log(Level.SEVERE, "Email exists", e);
			fail("the connection should not throw an exception");
		}
	}
	
	@Test
	public void testCreateUser() {
		User u = new User("test5","test","test@email.fr","password");
		
		boolean successAdd = manager.createUser(u);
		assertTrue("true",successAdd);
		
		boolean successDelete = manager.DeleteUser(u.getEmail());
		assertTrue("true",successDelete);
	}

}
