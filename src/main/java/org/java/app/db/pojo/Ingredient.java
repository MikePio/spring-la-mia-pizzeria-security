package org.java.app.db.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

// * RELAZIONE MANY-TO-MANY / N-N - STEP 1 - CREAZIONE TABELLA NEL DB
@Entity
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  // * RELAZIONE MANY-TO-MANY / N-N - STEP 2.2/2.2 - COLLEGAMENTO DELLE TABELLE in Pizza.java e poi in Ingredient.java
  @ManyToMany(mappedBy = "ingredients")
  private List<Pizza> pizzas;

  @Length(min = 2, max = 128, message = "\nThe length must be between 2 and 128 characters" )
  private String name;

  public Ingredient(){}

  public Ingredient(String name){
    
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

  // * RELAZIONE MANY-TO-MANY / N-N - STEP 4.1/4.5 - COLLEGARE GLI ID DELLE PIZZE CON GLI ID DEGLI INGREDIENTI NELLA TABELLA db_pizzeria_relationships DEL DB --> creare getter e setter di Pizza in Ingredient
  // * getter e setter di Pizza 
  public List<Pizza> getPizzas() {
    return pizzas;
  }

  public void setPizzas(List<Pizza> pizzas) {
    this.pizzas = pizzas;
  }

  @Override
  public String toString() {
    return "\nIngredient: \nid=" + id + "\nname=" + getName();
  }

  // stampa il nome con solo la prima lettera in maiuscolo
  public String getNameWithInitialCapital() {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  // * METODO CHE RITORNA UN NUMERO INTERO CHE RAPPRESENTA UNIVOCAMENTE L'OGGETTO
  @Override
	public int hashCode() {
		
		return getId();
	}

  // * METODO CHE PRENDE IN INGRESSO UN OGGETTO E RITORNA UN VALORE BOOLEAN PER VERIFICARE SE L'OGGETTO IN INGRESSO è LO STESSO IN CUI MI TROVO 
	@Override
	public boolean equals(Object object) {
		
		if (!(object instanceof Ingredient)) return false;
		
		Ingredient objectIngredient = (Ingredient) object;
		
		return getId() == objectIngredient.getId();
	}

}
