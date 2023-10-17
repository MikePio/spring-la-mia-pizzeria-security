package org.java.app.db.auth.pojo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

// * STEP 2 - AUTHENTICATION - creazione della classe User(id, username, password) in db.auth.pojo e creazione del costruttore + i getter e i setter
@Entity
public class User implements UserDetails { // * STEP 5.9 - AUTHENTICATION - implements UserDetails nella classe pojo User.java per inserire dei metodi in modo automatico: getAuthorities, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String username;
	
	@Column(nullable = false)
	@NotNull
	private String password;

  // * STEP 4 - AUTHENTICATION - collegamento / relazione tra tabelle nel database
  @ManyToMany(fetch = FetchType.EAGER) // * STEP 6.7 - AUTHENTICATION - aggiungere (fetch = FetchType.EAGER) a destra di @ManyToMany nel file User.java
	
  private Set<Role> roles;

  public User() { }
  public User(String username, String password, Role... roles) {   // * STEP 5.12 - AUTHENTICATION - PARTE 1/2 aggiungere al costruttore i parametri Role e creare i metodi getter e setter per Roles
    
    setUsername(username);
    setPassword(password);
    setRoles(new HashSet<>(Arrays.asList(roles)));
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

  // * STEP 5.12 - AUTHENTICATION - PARTE 2/2 creare i metodi getter e setter per Roles e aggiungere setRole al costruttore
  public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

  @Override
  public String toString() {
    return "\nUser \nid = " + id + "\nusername=" + username + "\npassword = " + password;
  }

  // * STEP 5.10 - AUTHENTICATION - al metodo getAuthorities implementato in modo automatico trasformare il set di ruoli in una collection di GrantedAuthority
  @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
    
    // * STEP 5.13 - AUTHENTICATION - inserire qui il metodo getRoles() e mapparlo
    return getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
  }
    
  // * STEP 5.11 - AUTHENTICATION - inserire il return true ai metodi implementati in modo automatico: isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled
  @Override public boolean isAccountNonExpired() { return true; }
	@Override public boolean isAccountNonLocked() { return true; }
	@Override public boolean isCredentialsNonExpired() { return true; }
	@Override public boolean isEnabled() { return true; }

}

