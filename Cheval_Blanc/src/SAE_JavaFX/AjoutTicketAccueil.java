package SAE_JavaFX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import SAE_Java.Costume;
import SAE_Java.Manteau;
import SAE_Java.Navette;
import SAE_Java.Prestation;
import SAE_Java.Recapitulatif;
import SAE_Java.Reservation;
import SAE_Java.Robe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AjoutTicketAccueil extends Stage {
	
	private boolean typeModif; 
	//True   ->  Ajouter
	//False  ->  Modifier
	
	private Reservation reservation = null; 
	private Recapitulatif recapitulatif = null; 
	
	private static Navette navette = new Navette(); 
	private static Robe robe = new Robe(); 
	private static Manteau manteau = new Manteau(); 
	private static Costume costume = new Costume();
	
	
	/***	header	***/
	Label lblReserv = new Label("N°réservation : ");
	Label lblDate = new Label();
	
	
	/***	topMain	***/
	Label lblDetail = new Label("Détail des prestations :"); 
	Button btnToutSup = new Button("");
	
	
	/***	Main	***/
	private ObservableList<String> options = FXCollections.observableArrayList(
	            "- Aucune -",
	            "Pressing Robe",
	            "Pressing Manteau",
	            "Pressing Costume",
	            "Navette"
	    );
	
	Button btnSup0 = new Button("");
	ChoiceBox<String> choiceBox0;
	TextField quantite0 = new TextField("1"); 
	Button btnPlus0 = new Button(""); 
	Button btnMoins0 = new Button();
	
	Button btnSup1 = new Button("");
	ChoiceBox<String> choiceBox1;
	TextField quantite1 = new TextField("1"); 
	Button btnPlus1 = new Button(""); 
	Button btnMoins1 = new Button("");
	
	Button btnSup2 = new Button("");
	ChoiceBox<String> choiceBox2;
	TextField quantite2 = new TextField("1"); 
	Button btnPlus2 = new Button(""); 
	Button btnMoins2 = new Button("");
	
	Button btnSup3 = new Button();
	ChoiceBox<String> choiceBox3;
	TextField quantite3 = new TextField("1"); 
	Button btnPlus3 = new Button(""); 
	Button btnMoins3 = new Button("");
	
	
	/***	footer	***/
	Button annuler = new Button("Annuler");
	Button valider = new Button("Valider");
	
	
	public AjoutTicketAccueil (Reservation res, Recapitulatif rec, boolean typeModif) {
		this.setReservation(res); 
		this.setRecapitulatif(rec);
		this.setTypeModif(typeModif);
		
		this.setTitle("Ticket");
		Scene laScene = new Scene(creerContenu());
		this.setScene(laScene);
		
		this.setMinWidth(550);
		this.setMaxWidth(550);
		
		this.setMaxHeight(500);
		this.setMinHeight(470);
		
		this.initModality(Modality.APPLICATION_MODAL);
	}
	

	public void setReservation(Reservation r) {
		this.reservation = r; 
	}
	
	public void setRecapitulatif(Recapitulatif r) {
		this.recapitulatif = r; 
	}
	
	public void setTypeModif(boolean b) {
		this.typeModif = b;
	}
	
	
	public Parent creerContenu() {
		VBox racine = new VBox(); 
		racine.setPadding(new Insets(20,15,20,15));
		
		/***	Header	***/
		HBox header = new HBox();  
		
		if (reservation != null && recapitulatif != null) {
			lblReserv.setText(lblReserv.getText() + reservation.getNum_reservation());
			lblDate.setText(recapitulatif.getDate());
		}
		header.setPadding(new Insets(0,20,20,20));
		header.setSpacing(50);
		header.getChildren().addAll(lblReserv, lblDate); 
		
		
		/***	TopMain	***/
		HBox topMain = new HBox(); 
		
		BackgroundFill backgroundFillBlue = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background backgroundBlue = new Background(backgroundFillBlue);
		topMain.setBackground(backgroundBlue);
        topMain.setPadding(new Insets(5,10,5,10));
        topMain.setAlignment(Pos.CENTER);
        topMain.setSpacing(300);
        
        /* Couleur */
        BackgroundFill backgroundFillBlack = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background backgroundBlack = new Background(backgroundFillBlack);
       
        
        /* Bouton Tous SUPPRIMER */
        ImageView iconSupprimerTous = new ImageView(new Image("img/supprimertous.png"));
	        iconSupprimerTous.setFitWidth(23);
	        iconSupprimerTous.setFitHeight(23);
        
        ImageView iconSupprimerTousBis = new ImageView(new Image("img/supptous.png"));
	        iconSupprimerTousBis.setFitWidth(23);
	        iconSupprimerTousBis.setFitHeight(23);
        
        btnToutSup.setBackground(backgroundBlue);
        btnToutSup.setOnMouseEntered(event -> btnToutSup.setGraphic(iconSupprimerTousBis));
        btnToutSup.setOnMouseExited(event -> btnToutSup.setGraphic(iconSupprimerTous));
        btnToutSup.setGraphic(iconSupprimerTous);
        
		topMain.getChildren().addAll(lblDetail, btnToutSup); 
		
		
		
		btnToutSup.setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION, 
					"Voulez-vous supprimer toutes les prestations du récapitulatif ? ", 
					ButtonType.NO, ButtonType.YES);
			a.showAndWait(); 
			ButtonType result = a.getResult();
			if (result == ButtonType.YES) {
				choiceBox0.getSelectionModel().selectFirst();
				quantite0.setText("1"); 
				choiceBox1.getSelectionModel().selectFirst();
				quantite1.setText("1"); 
				choiceBox2.getSelectionModel().selectFirst();
				quantite2.setText("1"); 
				choiceBox3.getSelectionModel().selectFirst();
				quantite3.setText("1"); 
			}
		});
		
		
		/***	Main	**/
		VBox prestation = new VBox(); 	
		prestation = construirePrestation(); 
		
		
		/***	Footer	***/
		HBox footer = new HBox();
		footer.setAlignment(Pos.CENTER_RIGHT);
		footer.setSpacing(10);
		footer.setPadding(new Insets(10));
		footer.getChildren().addAll(annuler, valider); 
		
		annuler.setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION, 
					"Voulez-vous abandonner les modifications apportées ce récapitulatif ? ", 
					ButtonType.NO, ButtonType.YES);
			a.showAndWait(); 
			ButtonType result = a.getResult();
			if (result == ButtonType.YES) {
				reservation.supprimerRecapitule(recapitulatif);
				this.close(); 
			}
		});
		
		valider.setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION, 
					"Voulez-vous valider les modifications apportées à ce récapitulatif ? ", 
					ButtonType.NO, ButtonType.YES); 
			a.showAndWait(); 
			ButtonType result = a.getResult();
			if (result == ButtonType.YES) {
				Prestation typePresta = null; 
				String lblpresta; 
				int quantite; 
				
				if (!typeModif ) {
					reservation.supprimerRecapitule(recapitulatif); 
					this.setRecapitulatif(new Recapitulatif(reservation));
				}
				
				if (!choiceBox0.getValue().equals("- Aucune -")) {
					lblpresta = choiceBox0.getValue();
					quantite = Integer.parseInt(quantite0.getText());
					if (lblpresta.equals("Pressing Robe")) {
						typePresta = robe; 
					}
					else if (lblpresta.equals("Pressing Manteau")) {
						typePresta = manteau; 
					}
					else if (lblpresta.equals("Pressing Costume")) {
						typePresta = costume; 
					}
					else {
						typePresta = navette;
					}
					
					recapitulatif.ajouterPrestation(typePresta);
					recapitulatif.modifierPrestation(typePresta, quantite);
				}
				if (!choiceBox1.getValue().equals("- Aucune -")) {
					lblpresta = choiceBox1.getValue();
					quantite = Integer.parseInt(quantite1.getText());
					if (lblpresta.equals("Pressing Robe")) {
						typePresta = robe; 
					}
					else if (lblpresta.equals("Pressing Manteau")) {
						typePresta = manteau; 
					}
					else if (lblpresta.equals("Pressing Costume")) {
						typePresta = costume; 
					}
					else {
						typePresta = navette; 
					}
					
					recapitulatif.ajouterPrestation(typePresta);
					recapitulatif.modifierPrestation(typePresta, quantite);
				}
				if (!choiceBox2.getValue().equals("- Aucune -")) {
					lblpresta = choiceBox2.getValue();
					quantite = Integer.parseInt(quantite2.getText());
					if (lblpresta.equals("Pressing Robe")) {
						typePresta = robe; 
					}
					else if (lblpresta.equals("Pressing Manteau")) {
						typePresta = manteau; 
					}
					else if (lblpresta.equals("Pressing Costume")) {
						typePresta = costume; 
					}
					else {
						typePresta = navette; 
					}
					
					recapitulatif.ajouterPrestation(typePresta);
					recapitulatif.modifierPrestation(typePresta, quantite);
				}
				if (!choiceBox3.getValue().equals("- Aucune -")) {
					lblpresta = choiceBox3.getValue();
					quantite = Integer.parseInt(quantite3.getText());
					if (lblpresta.equals("Pressing Robe")) {
						typePresta = robe; 
					}
					else if (lblpresta.equals("Pressing Manteau")) {
						typePresta = manteau; 
					}
					else if (lblpresta.equals("Pressing Costume")) {
						typePresta = costume; 
					}
					else {
						typePresta = navette; 
					}
					
					recapitulatif.ajouterPrestation(typePresta);
					recapitulatif.modifierPrestation(typePresta, quantite);
				}
			    this.close();
			} 
		});
		
		/* bouton SUPP 0 */
		ImageView iconSupprimer0 = new ImageView(new Image("img/supprimer1.png"));
			iconSupprimer0.setFitWidth(23);
			iconSupprimer0.setFitHeight(23);
	        
	    ImageView iconSupprimerBis0 = new ImageView(new Image("img/supp1.png"));
		    iconSupprimerBis0.setFitWidth(23);
		    iconSupprimerBis0.setFitHeight(23);
		    
		btnSup0.setBackground(backgroundBlack);
        btnSup0.setOnMouseEntered(event -> btnSup0.setGraphic(iconSupprimerBis0));
        btnSup0.setOnMouseExited(event -> btnSup0.setGraphic(iconSupprimer0));
        btnSup0.setGraphic(iconSupprimer0);
		
        /* bouton SUPP 1 */
		ImageView iconSupprimer1 = new ImageView(new Image("img/supprimer1.png"));
		iconSupprimer1.setFitWidth(23);
		iconSupprimer1.setFitHeight(23);
	        
	    ImageView iconSupprimerBis1 = new ImageView(new Image("img/supp1.png"));
	    iconSupprimerBis1.setFitWidth(23);
	    iconSupprimerBis1.setFitHeight(23);
		    
		btnSup1.setBackground(backgroundBlack);
		btnSup1.setOnMouseEntered(event -> btnSup1.setGraphic(iconSupprimerBis1));
		btnSup1.setOnMouseExited(event -> btnSup1.setGraphic(iconSupprimer1));
		btnSup1.setGraphic(iconSupprimer1);
        
        /* bouton SUPP 2 */
		ImageView iconSupprimer2 = new ImageView(new Image("img/supprimer1.png"));
		iconSupprimer2.setFitWidth(23);
		iconSupprimer2.setFitHeight(23);
	        
	    ImageView iconSupprimerBis2 = new ImageView(new Image("img/supp1.png"));
	    iconSupprimerBis2.setFitWidth(23);
	    iconSupprimerBis2.setFitHeight(23);
		    
		btnSup2.setBackground(backgroundBlack);
		btnSup2.setOnMouseEntered(event -> btnSup2.setGraphic(iconSupprimerBis2));
		btnSup2.setOnMouseExited(event -> btnSup2.setGraphic(iconSupprimer2));
		btnSup2.setGraphic(iconSupprimer2);
        
        /* bouton SUPP 3 */
		ImageView iconSupprimer3 = new ImageView(new Image("img/supprimer1.png"));
			iconSupprimer3.setFitWidth(23);
			iconSupprimer3.setFitHeight(23);
	        
	    ImageView iconSupprimerBis3 = new ImageView(new Image("img/supp1.png"));
		    iconSupprimerBis3.setFitWidth(23);
		    iconSupprimerBis3.setFitHeight(23);
		    
		btnSup3.setBackground(backgroundBlack);
		btnSup3.setOnMouseEntered(event -> btnSup3.setGraphic(iconSupprimerBis3));
		btnSup3.setOnMouseExited(event -> btnSup3.setGraphic(iconSupprimer3));
		btnSup3.setGraphic(iconSupprimer3);
        
        ImageView iconMoins0 = new ImageView(new Image("img/arrowdown.png"));
        iconMoins0.setFitWidth(23);
        iconMoins0.setFitHeight(23);
        
		ImageView iconPlus0 = new ImageView(new Image("img/arrowup.png"));
		iconPlus0.setFitWidth(23);
		iconPlus0.setFitHeight(23);
		
		btnMoins0.setBackground(backgroundBlack);
		btnMoins0.setGraphic(iconMoins0);
		btnPlus0.setBackground(backgroundBlack);
		btnPlus0.setGraphic(iconPlus0);
		
		ImageView iconMoins1 = new ImageView(new Image("img/arrowdown.png"));
		iconMoins1.setFitWidth(23);
		iconMoins1.setFitHeight(23);
        
		ImageView iconPlus1 = new ImageView(new Image("img/arrowup.png"));
		iconPlus1.setFitWidth(23);
		iconPlus1.setFitHeight(23);
		
		btnMoins1.setBackground(backgroundBlack);
		btnMoins1.setGraphic(iconMoins1);
		btnPlus1.setBackground(backgroundBlack);
		btnPlus1.setGraphic(iconPlus1);
		
		ImageView iconMoins2 = new ImageView(new Image("img/arrowdown.png"));
        iconMoins2.setFitWidth(23);
        iconMoins2.setFitHeight(23);
        
		ImageView iconPlus2 = new ImageView(new Image("img/arrowup.png"));
		iconPlus2.setFitWidth(23);
		iconPlus2.setFitHeight(23);
		
		btnMoins2.setBackground(backgroundBlack);
		btnMoins2.setGraphic(iconMoins2);
		btnPlus2.setBackground(backgroundBlack);
		btnPlus2.setGraphic(iconPlus2);
		
		ImageView iconMoins3 = new ImageView(new Image("img/arrowdown.png"));
        iconMoins3.setFitWidth(23);
        iconMoins3.setFitHeight(23);
        
		ImageView iconPlus3 = new ImageView(new Image("img/arrowup.png"));
		iconPlus3.setFitWidth(23);
		iconPlus3.setFitHeight(23);
		
		btnMoins3.setBackground(backgroundBlack);
		btnMoins3.setGraphic(iconMoins3);
		btnPlus3.setBackground(backgroundBlack);
		btnPlus3.setGraphic(iconPlus3);
		
		quantite0.setMinWidth(100);
		quantite1.setMinWidth(100);
		quantite2.setMinWidth(100);
		quantite3.setMinWidth(100);
	
		racine.getChildren().addAll(header, topMain, prestation, footer);
		return racine; 
	}
	
	
	private ChoiceBox<String> createChoiceBox() {
        ChoiceBox<String> choiceBox = new ChoiceBox<>(options);
        choiceBox.getSelectionModel().selectFirst();

        return choiceBox;
    }
	
	private void updateOptions() {
		ObservableList<String> availableOptions0 = FXCollections.observableArrayList(options);
		ObservableList<String> availableOptions1 = FXCollections.observableArrayList(options);
		ObservableList<String> availableOptions2 = FXCollections.observableArrayList(options);
		ObservableList<String> availableOptions3 = FXCollections.observableArrayList(options);
		
		if (choiceBox0.getValue() != null && !choiceBox0.getValue().equals("- Aucune -")) {
			availableOptions1.removeAll(Collections.singleton(choiceBox0.getValue()));
			availableOptions2.removeAll(Collections.singleton(choiceBox0.getValue()));
			availableOptions3.removeAll(Collections.singleton(choiceBox0.getValue()));
		}
		
		if (choiceBox1.getValue() != null && !choiceBox1.getValue().equals("- Aucune -")) {
			availableOptions0.removeAll(Collections.singleton(choiceBox1.getValue()));
			availableOptions2.removeAll(Collections.singleton(choiceBox1.getValue()));
			availableOptions3.removeAll(Collections.singleton(choiceBox1.getValue()));
		}
		
		if (choiceBox2.getValue() != null && !choiceBox2.getValue().equals("- Aucune -")) {
			availableOptions1.removeAll(Collections.singleton(choiceBox2.getValue()));
			availableOptions0.removeAll(Collections.singleton(choiceBox2.getValue()));
			availableOptions3.removeAll(Collections.singleton(choiceBox2.getValue()));
		}
		
		if (choiceBox3.getValue() != null && !choiceBox3.getValue().equals("- Aucune -")) {
			availableOptions1.removeAll(Collections.singleton(choiceBox3.getValue()));
			availableOptions2.removeAll(Collections.singleton(choiceBox3.getValue()));
			availableOptions0.removeAll(Collections.singleton(choiceBox3.getValue()));
		}
			
		String value0 = choiceBox0.getValue();
		choiceBox0.setItems(availableOptions0);
		choiceBox0.setValue(value0);
		
		String value1 = choiceBox1.getValue();
		choiceBox1.setItems(availableOptions1);
		choiceBox1.setValue(value1);
		
		String value2 = choiceBox2.getValue();
		choiceBox2.setItems(availableOptions2);
		choiceBox2.setValue(value2);
		
		String value3 = choiceBox3.getValue();
		choiceBox3.setItems(availableOptions3);
		choiceBox3.setValue(value3);
		
		choiceBox0.setMinWidth(150);
		choiceBox1.setMinWidth(150);
		choiceBox2.setMinWidth(150);
		choiceBox3.setMinWidth(150);
	
		
		choiceBox0.setPrefWidth(150);
		choiceBox1.setPrefWidth(150);
		choiceBox2.setPrefWidth(150);
		choiceBox3.setPrefWidth(150);

		
		choiceBox0.setMaxWidth(150);
		choiceBox1.setMaxWidth(150);
		choiceBox2.setMaxWidth(150);
		choiceBox3.setMaxWidth(150);
		
    }
	
	public VBox construirePrestation() {
		VBox prestation = new VBox(); 
		
		choiceBox0 = createChoiceBox();
		choiceBox1 = createChoiceBox();
		choiceBox2 = createChoiceBox();
        choiceBox3 = createChoiceBox();
        
        choiceBox0.setMinWidth(150);
		choiceBox1.setMinWidth(150);
		choiceBox2.setMinWidth(150);
		choiceBox3.setMinWidth(150);
	
		
		choiceBox0.setPrefWidth(150);
		choiceBox1.setPrefWidth(150);
		choiceBox2.setPrefWidth(150);
		choiceBox3.setPrefWidth(150);

		
		choiceBox0.setMaxWidth(150);
		choiceBox1.setMaxWidth(150);
		choiceBox2.setMaxWidth(150);
		choiceBox3.setMaxWidth(150);
	
        
        choiceBox0.setOnAction(e -> updateOptions());
        choiceBox1.setOnAction(e -> updateOptions());
        choiceBox2.setOnAction(e -> updateOptions());
        choiceBox3.setOnAction(e -> updateOptions());
		
		HBox presta0 = new HBox();	
		VBox btnQuantite0 = new VBox();
		btnQuantite0.getChildren().addAll(btnPlus0, btnMoins0);
		presta0.getChildren().addAll(btnSup0, choiceBox0, quantite0, btnQuantite0);
		
		HBox presta1 = new HBox();
		VBox btnQuantite1 = new VBox();
		btnQuantite1.getChildren().addAll(btnPlus1, btnMoins1);
		presta1.getChildren().addAll(btnSup1, choiceBox1, quantite1, btnQuantite1);
		
		HBox presta2 = new HBox();
		VBox btnQuantite2 = new VBox();
		btnQuantite2.getChildren().addAll(btnPlus2, btnMoins2);
		presta2.getChildren().addAll(btnSup2, choiceBox2, quantite2, btnQuantite2);
		
		HBox presta3 = new HBox();
		VBox btnQuantite3 = new VBox();
		btnQuantite3.getChildren().addAll(btnPlus3, btnMoins3);
		presta3.getChildren().addAll(btnSup3, choiceBox3, quantite3, btnQuantite3);
		
		if (typeModif) {
			choiceBox0.getSelectionModel().selectFirst();
			choiceBox1.getSelectionModel().selectFirst();
			choiceBox2.getSelectionModel().selectFirst();
			choiceBox3.getSelectionModel().selectFirst();
		}
		else {
			ArrayList<String> listLabelPrestation = new ArrayList<>(); 
			ArrayList<Integer> listQuantitePrestation = new ArrayList<>(); 
			
			for (Map.Entry<Prestation, Integer> mapEntry : recapitulatif.getList_prestation().entrySet()) {
				Prestation p = mapEntry.getKey();
				Integer quantite =  mapEntry.getValue();
				
				listLabelPrestation.add(p.getLabel());
				listQuantitePrestation.add(quantite); 
			}
			if (recapitulatif.getList_prestation().size() > 0) {
				choiceBox0.setValue(listLabelPrestation.get(0));
				quantite0.setText("" + listQuantitePrestation.get(0)); 
			}
			if (recapitulatif.getList_prestation().size() > 1) {
				choiceBox1.setValue(listLabelPrestation.get(1));
				quantite1.setText("" + listQuantitePrestation.get(1)); 
			}
			if (recapitulatif.getList_prestation().size() > 2) {
				choiceBox2.setValue(listLabelPrestation.get(2));
				quantite2.setText("" + listQuantitePrestation.get(2)); 
			}
			if (recapitulatif.getList_prestation().size() > 3) {
				choiceBox3.setValue(listLabelPrestation.get(3));
				quantite3.setText("" + listQuantitePrestation.get(3)); 
			}
		}
		
		quantite0.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				quantite0.setText(newValue.replaceAll("[^\\d]", ""));
		    } 
			else {
		        try {
		            int number = Integer.parseInt(newValue);
		            if (number < 1) {
		                quantite0.setText(oldValue);
		            }
		        } 
		        catch (NumberFormatException e) {
		        	quantite0.setText("");
		        }
		    }
		});
		quantite0.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && quantite0.getText().isEmpty()) {
            	quantite0.setText("1");
            }
		});
		quantite1.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				quantite1.setText(newValue.replaceAll("[^\\d]", ""));
		    } 
			else {
		        try {
		            int number = Integer.parseInt(newValue);
		            if (number < 1) {
		                quantite1.setText(oldValue);
		            }
		        } 
		        catch (NumberFormatException e) {
		        	quantite1.setText("");
		        }
		    }
		});
		quantite1.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && quantite1.getText().isEmpty()) {
            	quantite1.setText("1");
            }
		});
		quantite2.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				quantite2.setText(newValue.replaceAll("[^\\d]", ""));
		    } 
			else {
		        try {
		            int number = Integer.parseInt(newValue);
		            if (number < 1) {
		                quantite2.setText(oldValue);
		            }
		        } 
		        catch (NumberFormatException e) {
		        	quantite2.setText("");
		        }
		    }
		});
		quantite2.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && quantite2.getText().isEmpty()) {
            	quantite2.setText("1");
            }
		});
		quantite3.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				quantite3.setText(newValue.replaceAll("[^\\d]", ""));
		    } 
			else {
		        try {
		            int number = Integer.parseInt(newValue);
		            if (number < 1) {
		                quantite3.setText(oldValue);
		            }
		        } 
		        catch (NumberFormatException e) {
		        	quantite3.setText("");
		        }
		    }
		});
		quantite3.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && quantite3.getText().isEmpty()) {
            	quantite3.setText("1");
            }
		});
		
		
		btnPlus0.setOnAction(e -> {
			quantite0.setText("" + (Integer.parseInt(quantite0.getText()) + 1)); 
		});
		btnPlus1.setOnAction(e -> {
			quantite1.setText("" + (Integer.parseInt(quantite1.getText()) + 1)); 
		});
		btnPlus2.setOnAction(e -> {
			quantite2.setText("" + (Integer.parseInt(quantite2.getText()) + 1)); 
		});
		btnPlus3.setOnAction(e -> {
			quantite3.setText("" + (Integer.parseInt(quantite3.getText()) + 1)); 
		});
		
		btnMoins0.setOnAction(e -> {
			if (Integer.parseInt(quantite0.getText()) - 1 > 0) {
				quantite0.setText("" + (Integer.parseInt(quantite0.getText()) - 1));
			}  
		});
		btnMoins1.setOnAction(e -> {
			if (Integer.parseInt(quantite1.getText()) - 1 > 0) {
				quantite1.setText("" + (Integer.parseInt(quantite1.getText()) - 1));
			}  
		});
		btnMoins2.setOnAction(e -> {
			if (Integer.parseInt(quantite2.getText()) - 1 > 0) {
				quantite2.setText("" + (Integer.parseInt(quantite2.getText()) - 1));
			}  
		});
		btnMoins3.setOnAction(e -> {
			if (Integer.parseInt(quantite3.getText()) - 1 > 0) {
				quantite3.setText("" + (Integer.parseInt(quantite3.getText()) - 1));
			} 
		});
		
		btnSup0.setOnAction(e -> {
			choiceBox0.getSelectionModel().selectFirst();
			quantite0.setText("1"); 
		});
		btnSup1.setOnAction(e -> {
			choiceBox1.getSelectionModel().selectFirst();
			quantite1.setText("1"); 
		});
		btnSup2.setOnAction(e -> {
			choiceBox2.getSelectionModel().selectFirst();
			quantite2.setText("1"); 
		});
		btnSup3.setOnAction(e -> {
			choiceBox3.getSelectionModel().selectFirst();
			quantite3.setText("1"); 
		});
		
		prestation.setPadding(new Insets(15,0,15,0));
		prestation.setSpacing(5);
		prestation.getChildren().addAll(presta0, presta1, presta2, presta3); 
		return prestation; 
	}
}
