package org.java.app.db.auth;

import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

// * STEP 2 - AUTHENTICATION - creazione della classe User(id, username, password) in db.auth e creazione del costruttore + i getter e i setter
@Entity
public class User {

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String username;
	
	@Column(nullable = false)
	@NotNull
	private String password;

  public User() { }
  public User(String username, String password) {
    
    setUsername(username);
    setPassword(password);
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "\nUser \nid = " + id + "\nusername=" + username + "\npassword = " + password;
  }

}
