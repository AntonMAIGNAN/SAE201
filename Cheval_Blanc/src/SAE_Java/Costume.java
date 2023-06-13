package SAE_Java;

public class Costume extends Pressing{
	
	public Costume() {
		setLabel("Pressing Costume");
		setPrixTTC(8.00);
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