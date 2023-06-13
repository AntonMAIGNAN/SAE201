package SAE_Java;

public class Petit_dejeuner extends Service {
	
	public Petit_dejeuner(boolean serviceChambre) {
		super(serviceChambre);
		
		setLabel("Petit-déjeuner");
		setServiceChambre(serviceChambre);
		if (serviceChambre) {
			setPrixTTC(16.00);
		} 
		else {
			setPrixTTC(10.00);
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
