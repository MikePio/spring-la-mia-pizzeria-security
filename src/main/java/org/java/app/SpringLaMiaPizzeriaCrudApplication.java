package org.java.app;

import java.time.LocalDate;

import org.java.app.db.pojo.Ingredient;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.serv.IngredientService;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// * STEP 6 vengono creati gli oggetti con i propri dati che verranno inseriti come righe della tabella nel database
// * implementare CommandLineRunner
// * aggiungere @Autowired private PizzaService pizzaService;
// * creare nel metodo run degli oggetti
@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{

	@Autowired
	private PizzaService pizzaService;

	// importazione del file service nel file Application
	@Autowired
	private SpecialOfferService specialOfferService;
	
	// * RELAZIONE MANY-TO-MANY / N-N - STEP 3.3/3.5 - INSERIRE DATI NEL DB --> importazione del file service nel file Application
	@Autowired
	private IngredientService ingredientService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// * RELAZIONE MANY-TO-MANY / N-N - STEP 3.4/3.5 - INSERIRE DATI NEL DB --> CREARE gli elementi da inserire nel db
		Ingredient pomodoro = new Ingredient("pomodoro");
		Ingredient mozzarella = new Ingredient("mozzarella");
		Ingredient prosciuttoCotto = new Ingredient("prosciutto cotto");
		Ingredient salamePiccante = new Ingredient("salame piccante");
		
		// * RELAZIONE MANY-TO-MANY / N-N - STEP 3.5/3.5 - INSERIRE DATI NEL DB --> SALVARE gli elementi da inserire nel db
		ingredientService.save(pomodoro);
		ingredientService.save(mozzarella);
		ingredientService.save(prosciuttoCotto);
		ingredientService.save(salamePiccante);

    // * RELAZIONE MANY-TO-MANY / N-N - STEP 4.5/4.5 - COLLEGARE GLI ID DELLE PIZZE CON GLI ID DEGLI INGREDIENTI NELLA TABELLA db_pizzeria_relationships DEL DB --> aggiungere gli ingredienti(ogetti) creati alle pizze(oggetti)
		Pizza margherita = new Pizza("Margherita", "Pomodoro e mozzarella", "margherita.jpg", 5.00f, pomodoro, mozzarella );
		Pizza cotto = new Pizza("Prosciutto Cotto", "Pomodoro, mozzarella e prosciutto cotto", "cotto.jpg", 6.50f, pomodoro, mozzarella, prosciuttoCotto);
		Pizza diavola = new Pizza("Diavola", "Pomodoro, mozzarella e salame piccante", "diavola.jpg", 7.00f, pomodoro, mozzarella, salamePiccante);
	
		pizzaService.save(margherita);
		pizzaService.save(cotto);
		pizzaService.save(diavola);
		
		System.out.println("\n\nDati inseriti nella tabella del database\n\n");
	
		// oggetti SpecialOffer da inserire nel db
		SpecialOffer specialOffer1 = new SpecialOffer(0, "Offerta Speciale 1", LocalDate.now(), LocalDate.parse("2023-10-23"), margherita);
		SpecialOffer specialOffer2 = new SpecialOffer(0, "Offerta Speciale 2", LocalDate.now(), LocalDate.parse("2024-03-27"), cotto);
		SpecialOffer specialOffer3 = new SpecialOffer(0, "Offerta Speciale 3", LocalDate.now(), LocalDate.parse("2024-08-15"), diavola);
		SpecialOffer specialOffer4 = new SpecialOffer(0, "Offerta Speciale 4", LocalDate.now(), LocalDate.parse("2023-10-26"), margherita);

		specialOfferService.save(specialOffer1);
		specialOfferService.save(specialOffer2);
		specialOfferService.save(specialOffer3);
		specialOfferService.save(specialOffer4);
	}

}
