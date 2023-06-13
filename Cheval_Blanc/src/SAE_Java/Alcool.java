package SAE_Java;

public class Alcool extends Consommation{
	
	public Alcool(boolean serviceChambre) {
		super(serviceChambre);
		
		setLabel("Verre Alcool");
		setServiceChambre(serviceChambre);
		if(serviceChambre) {
			setPrixTTC(18.00 * majoration);
		}
		else {
			setPrixTTC(18.00);
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
