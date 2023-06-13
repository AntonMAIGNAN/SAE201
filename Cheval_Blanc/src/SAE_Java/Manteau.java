package SAE_Java;

public class Manteau extends Pressing {
	
	public Manteau() {
		setLabel("Pressing Manteau");
		setPrixTTC(12.00);
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