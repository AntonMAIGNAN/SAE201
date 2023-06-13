package SAE_JavaFX;

import java.util.HashMap;
import java.util.Map;

import SAE_Java.Prestation;
import SAE_Java.Recapitulatif;
import SAE_Java.Reservation;
import SAE_Java.Service;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Recap_Recherche extends Stage {
	
	/***	Barre de recherche	***/ 
	private Label searchLabal = new Label("Indiquer le n° de réservation : "); 
	private TextField searchField = new TextField();
	private Button searchButton = new Button("");
    
    /***	Resultat Recherche	***/
	private Label resulNull = new Label("Il n'y a aucune réservation à ce nom."); 
	private Label resulReservation = new Label("N°réservation : "); 
	private VBox resulRecherche = new VBox();
    
	/***	Footer	***/
	private Button btnFermer = new Button("Fermer");
    
	public Recap_Recherche() {
		this.setTitle("Recapitulatif");
		Scene laScene = new Scene(creerContenu());
		this.setScene(laScene);
		
		this.setWidth(450);
		this.setMinWidth(450);
		this.setMaxWidth(450);
		
		this.setHeight(750);
		this.setMinHeight(310);
		
		
	}
	
	
	
	
	public VBox recherche() {
		Label num_reservation = new Label(); 
		
		String donnee = searchField.getText();
		Reservation reservation = Reservation.rechercherReservation(donnee);
		resulRecherche.getChildren().clear(); 
		if (reservation != null) {
			num_reservation.setText(resulReservation.getText() + reservation.getNum_reservation()); 
			resulRecherche.getChildren().addAll(num_reservation); 
		
			resulRecherche.getChildren().add(constructeurRecap(reservation)); 
		}
		else {
			num_reservation.setText(resulNull.getText()); 
			resulRecherche.getChildren().addAll(num_reservation);
		}
		
		return resulRecherche; 
	}
	
	public Parent creerContenu() {
		VBox racine = new VBox();
		
		/***	Barre de recherche	***/
		HBox barre_recherche = new HBox(); 
		searchField.setPromptText("Rechercher");
		
		ImageView iconRecherche = new ImageView(new Image("img/recherche.png"));
		ImageView iconRechercheBis = new ImageView(new Image("img/rechercheBis.png"));
		
		/* Bouton RECHERCHE */
		BackgroundFill backgroundFillWhite = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background backgroundWhite = new Background(backgroundFillWhite);
        searchButton.setBackground(backgroundWhite);
        iconRecherche.setFitWidth(16);
        iconRecherche.setFitHeight(15);
        iconRechercheBis.setFitWidth(16);
        iconRechercheBis.setFitHeight(15);
        searchButton.setOnMouseEntered(event -> searchButton.setGraphic(iconRechercheBis));
        searchButton.setOnMouseExited(event -> searchButton.setGraphic(iconRecherche));
		searchButton.setGraphic(iconRecherche);
		
		
        
		barre_recherche.setPadding(new Insets(0,0,5,0));
		barre_recherche.getChildren().addAll(searchLabal, searchField, searchButton); 
		
		
		/***	Resultat Recherche	***/
		searchField.setOnKeyPressed(event -> {
	        if (event.getCode() == KeyCode.ENTER) {
	        	resulRecherche = recherche();
	        }
	    });
		
		searchButton.setOnAction(e -> {
			resulRecherche = recherche();
		});
		
		resulRecherche.setSpacing(20);
		
		racine.setPadding(new Insets(20,15,20,15));
		
		HBox footer = new HBox();
		footer.setPadding(new Insets(30,0,15,0));
		footer.setAlignment(Pos.BASELINE_RIGHT);
		footer.getChildren().add(btnFermer);
		
		btnFermer.setOnAction(e -> this.close()); 
		
		racine.getChildren().addAll(barre_recherche, resulRecherche,  footer); 	
		return racine;
	}
	
	
	
	public HBox constructeurPrestation(Prestation p, Integer qte, boolean typePres) {
		HBox prestation = new HBox();
		
		Label label = new Label(p.getLabel()); 
		Label quantite = new Label(String.valueOf(qte));
		Label chambres = new Label("");
		Label prix = new Label(String.valueOf(qte*p.getPrixTTC()) + " €");
		
		if (p instanceof SAE_Java.Service) {
			Service service = (Service) p;
			if (service.getServiceChambre()) {
				chambres.setText("Oui");
			}
			else {
				chambres.setText("Non");
			}
		    
		}
		
		label.setMinWidth(145);
		
		if (!typePres) {
			quantite.setMinWidth(105);
			chambres.setMinWidth(80);
		} else {
			quantite.setMinWidth(60);
			
		}
		prestation.setSpacing(10);
		prestation.getChildren().addAll(label, quantite, chambres, prix); 		
		return prestation;
	}
	
	
	public VBox constructeurListPrestations(Reservation res, Recapitulatif r, boolean typePres) {
		VBox listPrestations = new VBox(); 
		
		// En tete
		HBox enTeteRecap = new HBox(); 
		Label date = new Label(r.getDate());
		Button btnModifier = new Button("");
		date.setFont(Font.font("",FontWeight.BOLD, 12));
		
		/* Bouton MODIFIER */
		ImageView iconModifier = new ImageView(new Image("img/modifier.png"));
		ImageView iconModifierBis = new ImageView(new Image("img/modifierBis.png"));
		BackgroundFill backgroundFillWhite = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background backgroundWhite = new Background(backgroundFillWhite);
        btnModifier.setBackground(backgroundWhite);
        iconModifier.setFitWidth(25);
        iconModifier.setFitHeight(25);
        iconModifierBis.setFitWidth(25);
        iconModifierBis.setFitHeight(25);
        btnModifier.setOnMouseEntered(event -> btnModifier.setGraphic(iconModifierBis));
        btnModifier.setOnMouseExited(event -> btnModifier.setGraphic(iconModifier));
        btnModifier.setGraphic(iconModifier);
		
		enTeteRecap.getChildren().addAll(date, btnModifier); 
		enTeteRecap.setAlignment(Pos.CENTER);
		enTeteRecap.setSpacing(300);
		
		btnModifier.setOnAction(e -> {
			if (typePres) {
				AjoutTicketAccueil FenTicket = new AjoutTicketAccueil(res, r, false); 
				FenTicket.showAndWait(); 
			}
			else {
				AjoutTicketBar FenTicket = new AjoutTicketBar(res, r, false); 
				FenTicket.showAndWait(); 
			}
			searchButton.fire();
		});
		
		// Detail
		HBox detail = new HBox(); 
		Label prestation = new Label("Prestation"); 
		Label quantite = new Label("Quantité");
		Label chambres = new Label("En chambre");
		Label prix = new Label("Prix");

		BackgroundFill backgroundFillYellow = new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background backgroundYellow = new Background(backgroundFillYellow);
        
        detail.setSpacing(50);
        detail.setPadding(new Insets(2,20,2,20));
        detail.setBackground(backgroundYellow);
        
        if (typePres) {
        	detail.getChildren().addAll(prestation, quantite, prix); 
        }
        else {
        	detail.getChildren().addAll(prestation, quantite, chambres, prix); 
        }
		
		
		
		// Liste prestation
		HashMap<Prestation, Integer> list_prestation = r.getList_prestation(); 
		
		if (!list_prestation.isEmpty()) {
			
			listPrestations.getChildren().addAll(enTeteRecap, detail);
			
			for(Map.Entry<Prestation, Integer> mapEntry : list_prestation.entrySet()) { 
				listPrestations.getChildren().add(constructeurPrestation(mapEntry.getKey(), mapEntry.getValue(), typePres)); 
			}			
		}
		
		listPrestations.setPadding(new Insets(15,0,15,0));
		return listPrestations;
	}
	
	public VBox constructeurRecap(Reservation r) {
		VBox recapitulatifs = new VBox();
		VBox recapitulatifsBar = new VBox();
		VBox recapitulatifsAccueil = new VBox();
		
		Label labelBar = new Label("Bar");
		Label labelAccueil = new Label("Accueil");
		
		HBox headBar = new HBox();
		HBox headAccueil = new HBox();
		
		Button btnAjouterBar = new Button("");
		Button btnAjouterAccueil = new Button("");
		
		BackgroundFill backgroundFillBlue = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background backgroundBlue = new Background(backgroundFillBlue);
        
		BackgroundFill backgroundFillWhite = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background backgroundWhite = new Background(backgroundFillWhite);
        
        ImageView iconAjouterB = new ImageView(new Image("img/plus.png"));
        iconAjouterB.setFitWidth(22);
        iconAjouterB.setFitHeight(22);
        
        ImageView iconAjouterBbis = new ImageView(new Image("img/addBis.png"));
        iconAjouterBbis.setFitWidth(22);
        iconAjouterBbis.setFitHeight(22);
        
        ImageView iconAjouterA = new ImageView(new Image("img/plus.png"));
        iconAjouterA.setFitWidth(22);
        iconAjouterA.setFitHeight(22);
        
        ImageView iconAjouterAbis = new ImageView(new Image("img/addBis.png"));
        iconAjouterAbis.setFitWidth(22);
        iconAjouterAbis.setFitHeight(22);
        
        /* bouton AJOUTER BAR*/
        btnAjouterBar.setBackground(backgroundBlue);
        btnAjouterBar.setOnMouseEntered(event -> btnAjouterBar.setGraphic(iconAjouterB));
        btnAjouterBar.setOnMouseExited(event -> btnAjouterBar.setGraphic(iconAjouterBbis));
        btnAjouterBar.setGraphic(iconAjouterBbis);
       
        /* bouton AJOUTER ACCUEIL*/
        btnAjouterAccueil.setBackground(backgroundBlue);
        btnAjouterAccueil.setOnMouseEntered(event -> btnAjouterAccueil.setGraphic(iconAjouterA));
        btnAjouterAccueil.setOnMouseExited(event -> btnAjouterAccueil.setGraphic(iconAjouterAbis));
        btnAjouterAccueil.setGraphic(iconAjouterAbis);
        
		headBar.setPadding(new Insets(5,10,5,10));
		headBar.setBackground(backgroundBlue);
		headBar.setSpacing(330);
		headBar.setAlignment(Pos.CENTER);
		headBar.getChildren().addAll(labelBar, btnAjouterBar); 
		
		
		headAccueil.setPadding(new Insets(5,10,5,10));
		headAccueil.setBackground(backgroundBlue);
		headAccueil.setSpacing(310);
		headAccueil.setAlignment(Pos.CENTER);
		
		headAccueil.getChildren().addAll(labelAccueil, btnAjouterAccueil);
		
		btnAjouterBar.setOnAction(e -> {
			AjoutTicketBar FenTicket = new AjoutTicketBar(r, new Recapitulatif(r), true);
			FenTicket.showAndWait();
			searchButton.fire();
		});
		btnAjouterAccueil.setOnAction(e -> {
			AjoutTicketAccueil FenTicket = new AjoutTicketAccueil(r, new Recapitulatif(r), true); 
			FenTicket.showAndWait();
			searchButton.fire();
		});
		
		
		recapitulatifsBar.getChildren().add(headBar); 
		recapitulatifsAccueil.getChildren().add(headAccueil); 
		
		if (r.getList_recapitulatif().size() > 0) {
			for (Recapitulatif recap : r.getList_recapitulatif()) {
				if (recap.getList_prestation().size() > 0) {
					boolean typePrest = true; 	// True --> Accueil		False --> Bar 
					
					if (recap.getList_prestation().keySet().iterator().next() instanceof SAE_Java.Service) {
						typePrest = false;
					}
					
					if (typePrest) {
						recapitulatifsAccueil.getChildren().add(constructeurListPrestations(r, recap, typePrest));
					}
					else {
						recapitulatifsBar.getChildren().add(constructeurListPrestations(r, recap, typePrest));
					}
				}
			}
		}
		else {
			Label labelVideBar = new Label("Il n'y a aucune prestation.");
			Label labelVideAccueil = new Label("Il n'y a aucune prestation.");
			
			recapitulatifsBar.getChildren().add(labelVideBar); 
			recapitulatifsAccueil.getChildren().add(labelVideAccueil);
		}
		
		recapitulatifs.setSpacing(20);
		
		recapitulatifsAccueil.setBackground(backgroundWhite);
		recapitulatifsBar.setBackground(backgroundWhite);
		
		recapitulatifs.getChildren().addAll(recapitulatifsAccueil, recapitulatifsBar);
		return recapitulatifs; 
	}
}
