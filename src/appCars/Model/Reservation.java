package appCars.Model;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class Reservation {
    private int id;
    private int trajet_id;
    private int user_id;
    private int place;
    private Date date;
    private int status;
    
    public Reservation(){
    	
    }
    
	public Reservation(int id, int trajet_id, int user_id, int place, Date date, int status) {
		super();
		this.id = id;
		this.trajet_id = trajet_id;
		this.user_id = user_id;
		this.place = place;
		this.date = date;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@NotNull
	public int getTrajet_id() {
		return trajet_id;
	}

	public void setTrajet_id(int trajet_id) {
		this.trajet_id = trajet_id;
	}
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	@NotNull
	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
