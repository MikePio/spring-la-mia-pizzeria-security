package org.java.app.db.repo;

import java.util.List;
import org.java.app.db.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// * STEP 4 questa interfaccia gestisce l'accesso ai dati per l'entità Pizza cioè permette di eseguire operazioni di persistenza sulle entità Pizza senza dover scrivere manualmente il codice per le operazioni CRUD
@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {
  // findByName perché il campo salvato nel db è name (se invece fosse stato title allora findByTitle)
  // public List<Pizza> findByName(String pizzaName);
  
  // ! STEP 1 PER CERCARE UN OGGETTO NEL FORM
  // * findByName perché il campo salvato nel db è name (se invece fosse stato title allora findByTitle)
  // * trova il nome della pizza con le lettere che sono incluse nel nome della pizza (parola cercata: "vola" trovata la pizza "diavola")
  public List<Pizza> findByNameContaining(String string);
}
