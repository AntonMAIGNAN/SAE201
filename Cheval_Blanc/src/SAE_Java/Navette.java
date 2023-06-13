package SAE_Java;

public class Navette extends Prestation {
	
	public Navette() {
		setLabel("Navette");
		setPrixTTC(14.00);
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