package org.java.app.db.auth.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

// * STEP 3 - AUTHENTICATION - creazione della classe Role(id, name) in db.auth.pojo e creazione del costruttore + i getter e i setter
@Entity
public class Role {

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String name;
	
	public Role() { }
	public Role(String name) {
		
		setName(name);
	}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "\nRole \nid = " + id + "\nname = " + name;
  }
  
  @Override
	public int hashCode() {
		
		return getId();
	}

  @Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Role)) return false;
		
		Role objRole = (Role) obj;
		
		return getId() == objRole.getId();
	}
}
