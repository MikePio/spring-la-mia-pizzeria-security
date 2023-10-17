package org.java.app.db.auth.service;

import java.util.List;

import org.java.app.db.auth.pojo.User;
import org.java.app.db.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// * STEP 5.5 - AUTHENTICATION - creazione del service RoleService in db.auth.service
@Service
public class UserService implements UserDetailsService { // * STEP 5.6 - AUTHENTICATION - implements UserDetailsService implementa in modo automatico la funzione/metodo loadUserByUsername

	@Autowired
	private UserRepo userRepo;
	
	public List<User> findAll() {
		
		return userRepo.findAll();
	}

	public User findById(int id) {
		
		return userRepo.findById(id).get();
	}

	public void save(User user) {
		
		userRepo.save(user);
	}

	// * STEP 5.7 - AUTHENTICATION - una volta implementato in modo automatico loadUserByUsername aggiorno UserRepo 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepo.findByUsername(username);
	}
}
