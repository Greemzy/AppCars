package appCars.Model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Trajet {
    private int id;
    private String nom;
    private int places;
    private double start_lat;
    private double start_lng;
    private double end_lat;
    private double end_lng;
    private Date depart;
    private int user_id;
    private int status;
 
	
	public Trajet(int id, String nom, int places, double start_lat, double start_lng, double end_lat, double end_lng,
			Date depart, int user_id, int status) {
		super();
		this.id = id;
		this.nom = nom;
		this.places = places;
		this.start_lat = start_lat;
		this.start_lng = start_lng;
		this.end_lat = end_lat;
		this.end_lng = end_lng;
		this.depart = depart;
		this.user_id = user_id;
		this.status = status;
	}


	public int isStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getPlaces() {
		return places;
	}


	public void setPlaces(int places) {
		this.places = places;
	}


	public double getStart_lat() {
		return start_lat;
	}


	public void setStart_lat(double start_lat) {
		this.start_lat = start_lat;
	}


	public double getStart_lng() {
		return start_lng;
	}


	public void setStart_lng(double start_lng) {
		this.start_lng = start_lng;
	}


	public double getEnd_lat() {
		return end_lat;
	}


	public void setEnd_lat(double end_lat) {
		this.end_lat = end_lat;
	}


	public double getEnd_lng() {
		return end_lng;
	}


	public void setEnd_lng(double end_lng) {
		this.end_lng = end_lng;
	}


	public Date getDepart() {
		return depart;
	}


	public void setDepart(Date depart) {
		this.depart = depart;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public Trajet() {
		
    }
}
