package org.java.app.db.auth.repo;

import org.java.app.db.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// * STEP 5.2 - AUTHENTICATION - creazione della repo RoleRepo in db.auth.repo
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}