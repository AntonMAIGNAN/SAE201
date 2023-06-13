package SAE_Java;

public class Champagne extends Consommation {
	
	public Champagne(boolean serviceChambre) {
		super(serviceChambre);
		
		setLabel("Bouteille Champagne");
		setServiceChambre(serviceChambre);
		if(serviceChambre) {
			setPrixTTC(110.00 * majoration);
		}
		else {
			setPrixTTC(110.00);
		}
	}
	
	/***	Affichage	***/
	public String toString() {
		String affichage = ""; 
		affichage += getLabel() + "\n"; 
		return affichage; 
	}
	
	public void affiche() {
		System.out.println(this);
	}
}
