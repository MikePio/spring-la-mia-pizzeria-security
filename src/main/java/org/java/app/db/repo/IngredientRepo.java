package org.java.app.db.repo;

import org.java.app.db.pojo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// * RELAZIONE MANY-TO-MANY / N-N - STEP 3.1/3.5 - INSERIRE DATI NEL DB --> CREARE IL FILE REPO IngredientRepo.java
@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Integer> {

}