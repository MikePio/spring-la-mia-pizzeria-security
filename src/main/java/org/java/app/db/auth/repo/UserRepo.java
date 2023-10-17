package org.java.app.db.auth.repo;

import org.java.app.db.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


// * STEP 5.1 - AUTHENTICATION - creazione della repo UserRepo in db.auth.repo
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

  // * STEP 5.8 - AUTHENTICATION - aggiorno UserRepo 
  public User findByUsername(String name);

}
