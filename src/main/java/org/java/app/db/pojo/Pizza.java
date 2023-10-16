package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

// * FASE 3 CREARE LA TABELLA NEL DATABASE
// * eseguire il programma e controllare se è stata creata la tabella nel db (DBeaver, MariaDB, PHPMyAdmin...)
@Entity
public class Pizza {
  
  // * step 5 - Validazione errori - scrivere messaggi di errore custom
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // id della tabella
  private int id;

  // campi della tabella da inserire: nome descrizione foto prezzo
  @Column(length = 128, nullable = false, unique = true)
  @Length(min = 2, max = 128, message = "\nThe length must be between 2 and 128 characters" )
  private String name;

	private String description;
  
  @NotBlank(message = "\nEnter the name of the image followed by the format (e.g. name.jpg, name.png...)")
	private String photo;

  @Column(nullable = false, unique = false)
  @Range(min = 1, max = 1000, message = "\nThe price must be between €1 and €1000" )
  // @DecimalMin(value = "1.00", message = "\nThe price must be at least €1")
  // @DecimalMax(value = "1000.00", message = "\nThe price must be at most €1000")
  private float price;
  
  // * @OneToMany
  @OneToMany(mappedBy = "pizza") // * mappedBy fa l'inverso di ciò che è stato fatto nel @ManyToOne per quanto riguarda i collegamenti/le relazioni tra le tabelle
	private List<SpecialOffer> specialOffers;

  // * RELAZIONE MANY-TO-MANY / N-N - STEP 2.1/2.2 - COLLEGAMENTO DELLE TABELLE in Pizza.java e poi in Ingredient.java
  @ManyToMany
  private List<Ingredient> ingredients;

  // costruttore
  public Pizza() { }
  // * RELAZIONE MANY-TO-MANY / N-N - STEP 4.3/4.5 - COLLEGARE GLI ID DELLE PIZZE CON GLI ID DEGLI INGREDIENTI NELLA TABELLA db_pizzeria_relationships DEL DB --> aggiungere Ingredient... ingredients al costruttore Pizza 
	public Pizza(String name, String description, String photo, float price, Ingredient... ingredients) {

		setId(id);
		setName(name);
		setDescription(description);
		setPhoto(photo);
		setPrice(price);
    // * RELAZIONE MANY-TO-MANY / N-N - STEP 4.4/4.5 - COLLEGARE GLI ID DELLE PIZZE CON GLI ID DEGLI INGREDIENTI NELLA TABELLA db_pizzeria_relationships DEL DB --> aggiungere il setIngredients(Arrays.asList(ingredients)); al costruttore Pizza
    setIngredients(Arrays.asList(ingredients));
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

	public String getFormattedPrice() {
		return String.format("%.2f", price);
	}
  
  // * getter e setter di SpecialOffer (da importare se si vuole ciclare gli ogetti SpecialOffer all'interno dell'oggetto pizza)
  public List<SpecialOffer> getSpecialOffers() {
    return specialOffers;
  }
  
  public void setSpecialOffers(List<SpecialOffer> specialOffers) {
    this.specialOffers = specialOffers;
  }

  // * RELAZIONE MANY-TO-MANY / N-N - STEP 4.2/4.5 - COLLEGARE GLI ID DELLE PIZZE CON GLI ID DEGLI INGREDIENTI NELLA TABELLA db_pizzeria_relationships DEL DB --> creare getter e setter di Ingredient in Pizza
  // * getter e setter di Ingredient (da importare se si vuole ciclare gli ogetti Ingredient all'interno dell'oggetto pizza)
  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
  
  // * FUNZIONE NECESSARIA PER ELIMINARE GLI INGREDIENTI(/ELEMENTI/OGGETTI)
  public void deleteIngredient(Ingredient ingredient) {
		
		getIngredients().remove(ingredient);
	}

  @Override
  public String toString() {
		return "Id: " + getId() + "\n" + "Name: " + getName() + "\n" + "Description: " + getDescription() + "\n" + "Image path: " + getPhoto() + "\n" + "Price: " + getPrice() + " ----> Formatted Price: " + getFormattedPrice() + "\n";
  }

}
