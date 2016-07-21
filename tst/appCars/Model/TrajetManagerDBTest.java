package appCars.Model;

import static org.junit.Assert.*;


import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import appCars.Manager.ReservationManagerDB;
import appCars.Manager.TrajetManagerDB;

public class TrajetManagerDBTest {

	private TrajetManagerDB manager = new TrajetManagerDB();
	private ReservationManagerDB managerReservation = new ReservationManagerDB();
	private static Logger log = Logger.getLogger(TrajetManagerDBTest.class.getName());
	

	@Test
	public void testCreateTrajet(){
		Trajet trajet = new Trajet(1,"Super",5,"Paris","Marseille",new Date(),1,0);
		
		boolean successAdd = manager.createTrajet(trajet);
		assertTrue("true",successAdd);
		
		boolean successDelete = manager.DeleteTrajet(trajet.getId());
		Assert.assertTrue("true",successDelete);
	}
	
	@Test
	public void testGetTrajet(){
		Trajet trajet = new Trajet(1,"Super",5,"Paris","Marseille",new Date(),1,0);
		boolean successAdd = manager.createTrajet(trajet);
		assertTrue("true",successAdd);
		
		Trajet getTrajet = manager.getTrajet(trajet.getId());
		assertEquals(trajet, getTrajet);
		
		
		boolean successDelete = manager.DeleteTrajet(trajet.getId());
		assertTrue("true",successDelete);
	}
	
	@Test
	public void testTrajetPlace(){
		Trajet trajet = new Trajet(1,"Super",5,"Paris","Marseille",new Date(),1,0);
		boolean successAdd = manager.createTrajet(trajet);
		assertTrue("true",successAdd);
		
		Trajet getTrajet = manager.getTrajet(trajet.getId());
		assertNotNull(getTrajet);
		assertNotEquals(trajet, getTrajet);
		assertEquals(trajet.getNom(), getTrajet.getNom());
		assertEquals(trajet.getOrigin(), getTrajet.getOrigin());
		
		int placesRestantes = manager.TrajetPlaces(getTrajet.getPlacesDispo());
		assertEquals(placesRestantes, getTrajet.getPlacesDispo());
		
		Reservation reservation = new Reservation(0, trajet.getId(),2,2, new Date(),0);
		boolean successAddReservation = managerReservation.createReservation(reservation);
		assertTrue(successAddReservation);
		
		boolean successDelete = manager.DeleteTrajet(trajet.getId());
		assertTrue("true",successDelete);
	}
	
}
