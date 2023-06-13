package SAE_Java;

public class Robe extends Pressing {
	
	public Robe() {
		setLabel("Pressing Robe");
		setPrixTTC(6.00);
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