package appCars.Model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
		String salt = BCrypt.gensalt(12);
		String password = BCrypt.hashpw("test",salt);
		User u = new User("firstname","lastname","test@email.fr",password,salt,"",true);
		
		boolean successAdd = manager.createUser(u);
		assertTrue("true",successAdd);
		
		boolean successDelete = manager.DeleteUser(u.getEmail());
		assertTrue("true",successDelete);
	}

	
	@Test
	public void testGetUser() {
		String salt = BCrypt.gensalt(12);
		String password = BCrypt.hashpw("test",salt);
		User u = new User("firstname","lastname","test@email.fr",password,salt,"",true);
		
		Boolean userCreated = manager.createUser(u);
		Assert.assertTrue("Should be able to create a  user", userCreated);
		
		Assert.assertTrue("The user should exist", manager.EmailExist(u.getEmail()));
		Assert.assertTrue("The user should exist with password test", BCrypt.checkpw(u.getPassword(), "test"));
		Assert.assertFalse("The user should exist but not with this password", BCrypt.checkpw(u.getPassword(), "test1"));
		
		User getu = manager.getUser(u.getEmail());
		Assert.assertEquals(getu, u);
		
		Boolean userDelete = manager.DeleteUser(u.getEmail());
		Assert.assertTrue("Should be able to delete the user", userDelete);
	}

}
