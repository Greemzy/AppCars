package appCars.Model;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String salt;
    private String token;
    private boolean activated;
 
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public User() {
    }
 
    public User(String firstname,String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.activated = true;
    }
    
    public String getStringUser(){
    	return "Firstname :" + this.firstname +
    			"\n Lastname" + this.lastname +
    			"\n email" + this.email +
    			"\n password" + this.password +
    			"\n salt" + this.salt;
    }
}
