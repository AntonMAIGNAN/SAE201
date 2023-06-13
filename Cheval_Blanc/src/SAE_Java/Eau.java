package SAE_Java;

public class Eau extends Consommation{
	
	public Eau(boolean serviceChambre) {
		super(serviceChambre);
		
		setLabel("Bouteille Eau");
		setServiceChambre(serviceChambre);
		if(serviceChambre) {
			setPrixTTC(6.00 * majoration);
		}
		else {
			setPrixTTC(6.00);
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
