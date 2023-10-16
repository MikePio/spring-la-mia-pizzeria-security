package org.java.app.db.serv;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// * STEP 5 PizzaService è una classe di servizio che offre metodi per eseguire operazioni CRUD sulle entità Pizza
// * PizzaService utilizza un'istanza di PizzaRepo per eseguire delle operazioni CRUD con la sorgente dati (ad esempio, un database)
@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;
	
	public void save(Pizza pizza) {
		pizzaRepo.save(pizza);
	}
	
	public List<Pizza> findAll() {
		return pizzaRepo.findAll();
	}
	
	public Pizza findById(int id) {
		return pizzaRepo.findById(id).get();
	}
	
  // findByName perché il campo salvato nel db è name (se invece fosse stato title allora findByTitle)
	// public List<Pizza> findByName(String PizzaName){
		
		// * trova il nome della pizza con la stessa parola usata per il nome della pizza (parola cercata: "diavola" trovata la pizza "diavola")
		// 	return pizzaRepo.findByName(PizzaName);
	// }
		
	// * findByName perché il campo salvato nel db è name (se invece fosse stato title allora findByTitle)
	public List<Pizza> findByName(String string){
		
		// ! STEP 2 PER CERCARE UN OGGETTO NEL FORM
		// * trova il nome della pizza con le lettere che sono incluse nel nome della pizza (parola cercata: "vola" trovata la pizza "diavola")
		return pizzaRepo.findByNameContaining(string);
	}
	
	// * DELETE - STEP 1 - aggiungere in PizzaService il metodo per l'eliminazione da importare nel controller
	public void deletePizza(Pizza pizza) {

		pizzaRepo.delete(pizza);
	}
}
