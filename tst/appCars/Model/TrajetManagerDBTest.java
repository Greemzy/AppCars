package appCars.Model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import appCars.Manager.UserManagerDB;

public class TrajetManagerDBTest {

	private UserManagerDB manager = new UserManagerDB();
	private static Logger log = Logger.getLogger(TrajetManagerDBTest.class.getName());
	
}
