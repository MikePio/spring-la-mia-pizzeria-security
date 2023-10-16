package org.java.app.db.serv;

import java.util.List;

import org.java.app.db.pojo.Ingredient;
import org.java.app.db.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// * RELAZIONE MANY-TO-MANY / N-N - STEP 3.2/3.5 - INSERIRE DATI NEL DB --> CREARE IL FILE SERVICE IngredientService.java
@Service
public class IngredientService {

	@Autowired
	private IngredientRepo ingredientRepo;
	
	public List<Ingredient> findAll() {
		
		return ingredientRepo.findAll();
	}
  
	public Ingredient findById(int id) {
		
		return ingredientRepo.findById(id).get();
  }

  public void save(Ingredient ingredient) {
		
		ingredientRepo.save(ingredient);
	}

	// per la delete
	public void delete(Ingredient ingredient) {
		
		ingredientRepo.delete(ingredient);
	}

}
