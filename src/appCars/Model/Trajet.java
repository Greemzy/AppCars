package appCars.Model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import appCars.Manager.TrajetManagerDB;

public class Trajet {
	
    private int id;
    private String nom;
    private int places;
    private String origin;
    private String destination;
    private Date depart;
    private int user_id;
    private int status;
    private int placesDispo;
 
	
	public Trajet(int id, String nom, int places,String origin, String destination,
			Date depart, int user_id, int status) {
		super();
		this.id = id;
		this.nom = nom;
		this.places = places;
		this.origin = origin;
		this.destination = destination;
		this.depart = depart;
		this.user_id = user_id;
		this.status = status;
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

	@NotNull
	@Size(max=255)
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	@NotNull
	public int getPlaces() {
		return places;
	}


	public void setPlaces(int places) {
		this.places = places;
	}

	@NotNull
	@Size(max=255)
	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@NotNull
	@Size(max=255)
	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}

	@NotNull
	public int getStatus() {
		return status;
	}

	@NotNull
	public Date getDepart() {		
		return depart;
	}


	public void setDepart(Date depart) {
		this.depart = depart;
	}

	@NotNull
	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getPlacesDispo() {
		TrajetManagerDB managerTrajet = new TrajetManagerDB();
		return managerTrajet.TrajetPlaces(this.id);
	}


	public Trajet() {
		this.setPlaces(1);
    }
	
	public String getStringTrajet(){
    	return "name :" + this.nom +
    			"\n Origine " + this.origin +
    			"\n Destination " + this.destination +
    			"\n Places " + this.places +
    			"\n Date " + this.depart+
    			"\n Status " + this.status;
    }
}
