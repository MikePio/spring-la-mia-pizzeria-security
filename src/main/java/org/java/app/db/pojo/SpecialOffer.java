package org.java.app.db.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.annotation.After;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class SpecialOffer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Column(nullable = false)
  @NotBlank(message = "\nEnter the name of the offer")
  private String title;
  
  @Column(nullable = false)
  @NotNull(message = "\nEnter the start date of the offer")
  // @PastOrPresent(message = "The start date must be today or in the past")
  // @Future(message = "Start date must be in the future")
  @FutureOrPresent(message = "The start date must be today or in the future")
  private LocalDate startDate; 
  
  @Column(nullable = false)
  @NotNull(message = "\nEnter the end date of the offer")
  @Future(message = "End date must be in the future")
  // @After(property = "startDate", message = "The end date must be after the start date", value = "")
  private LocalDate endDate;

  // * @ManyToOne
  @ManyToOne // * per ogni offerta speciale esiste una sola pizza
  @JoinColumn(nullable = false) // * un'offerta speciale non può esistere senza una pizza
  private Pizza pizza;

  public SpecialOffer() {}

  public SpecialOffer(int id, String title, LocalDate startDate, LocalDate endDate, Pizza pizza) {
		
    setTitle(title);
		setStartDate(startDate);
		setEndDate(endDate);
    setPizza(pizza);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getFormattedStartDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return startDate.format(formatter);
  }

  public String getFormattedEndDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return endDate.format(formatter);
  }

  // * STEP 1 - validazione della end date che deve essere dopo la start date
  public int isEndDateAfterStartDate() {
    // controllo se startDate ed endDate sono nulli
    if (startDate == null || endDate == null) {
      return 0;
    }else if(endDate.isAfter(startDate)){
      return 1;
    }
    return 2;
  }

  // * STEP 1/3 - ottenere la data startDate salvata nel db e mostrarla nell'input dell'edit di specialOffer
  public String getHtmlStartDate() {
    
    return getStartDate() == null ? null : getStartDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
  
  // * STEP 2/3 - ottenere la data startDate salvata nel db e mostrarla nell'input dell'edit di specialOffer
	public void setHtmlStartDate(String startDate) {

		setStartDate(LocalDate.parse(startDate));
	}
  
  // * STEP 1/3 - ottenere la data endDate salvata nel db e mostrarla nell'input dell'edit di specialOffer
  public String getHtmlEndDate() {
    
    return getEndDate() == null ? null : getEndDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
  
  // * STEP 2/3 - ottenere la data endDate salvata nel db e mostrarla nell'input dell'edit di specialOffer
	public void setHtmlEndDate(String endDate) {

		setEndDate(LocalDate.parse(endDate));
	}

  public Pizza getPizza() {
    return pizza;
  }

  public void setPizza(Pizza pizza) {
    this.pizza = pizza;
  }

  @Override
  public String toString() {
    return "\nSpecialOffer:\nid=" + id + "\ntitle=" + title + "\nstartDate=" + startDate + "\nendDate=" + endDate;
  }

  
  
}
