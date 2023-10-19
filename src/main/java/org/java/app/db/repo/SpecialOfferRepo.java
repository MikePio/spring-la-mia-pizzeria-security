package org.java.app.db.repo;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialOfferRepo extends JpaRepository<SpecialOffer, Integer> {

  // * Step 1 - eliminazione degli ingredienti associati ad una pizza - query per cercare l'id delle pizze e viene restituita una lista con gli id 
  public List<SpecialOffer> findByPizza(Pizza pizza);
  // equivale a:
  // SELECT *
  // FROM special_offer
  // WHERE pizza_id = :pizzaId
}
