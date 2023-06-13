package Test;

import SAE_Java.Alcool;
import SAE_Java.Champagne;
import SAE_Java.Costume;
import SAE_Java.Eau;
import SAE_Java.JusFruit;
import SAE_Java.Manteau;
import SAE_Java.Navette;
import SAE_Java.Petit_dejeuner;
import SAE_Java.Recapitulatif;
import SAE_Java.Reservation;
import SAE_Java.Robe;
import SAE_JavaFX.Recap_Recherche;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainJavaFX extends Application {

	public void start(Stage maFenetre) {
		maFenetre = new Recap_Recherche();
		maFenetre.show();
	}
	
	@SuppressWarnings("unused")
	public static void main(String [] args) {
		Reservation r1 = new Reservation(); 
		Reservation r2 = new Reservation(); 

		Recapitulatif rec1 = new Recapitulatif(r1); 
		Recapitulatif rec2 = new Recapitulatif(r1); 
		Recapitulatif rec3 = new Recapitulatif(r1);
		Recapitulatif rec4 = new Recapitulatif(r1);

		Navette navette = new Navette(); 
		Robe robe = new Robe(); 
		Manteau manteau = new Manteau(); 
		Costume costume = new Costume(); 
		
		Petit_dejeuner petit_dejT = new Petit_dejeuner(true); 
		Petit_dejeuner petit_dejF = new Petit_dejeuner(false); 
		Alcool alcoolT = new Alcool(true); 
		Alcool alcoolF = new Alcool(false); 
		Champagne champagneT = new Champagne(true); 
		Champagne champagneF = new Champagne(false); 
		Eau eauT = new Eau(true); 
		Eau eauF = new Eau(false); 
		JusFruit jusT = new JusFruit(true); 
		JusFruit jusF = new JusFruit(false); 
		
		rec1.ajouterPrestation(champagneT);
		rec1.modifierPrestation(champagneT, 55);	
		rec1.ajouterPrestation(jusF);
		
		rec2.ajouterPrestation(robe);
		rec2.modifierPrestation(robe, 2);
		rec2.ajouterPrestation(navette);
		rec2.modifierPrestation(navette, 11);
		
		rec3.ajouterPrestation(costume);
		rec3.ajouterPrestation(robe);

		rec4.ajouterPrestation(eauT);
		rec4.modifierPrestation(eauT, 2);
		rec4.ajouterPrestation(jusF);
		
		Application.launch();
	}

}
