package SAE_Java;

public class JusFruit extends Consommation{
	
	public JusFruit(boolean serviceChambre) {
		super(serviceChambre);
		
		setLabel("Jus de Fruit");
		setServiceChambre(serviceChambre);
		if(serviceChambre) {
			setPrixTTC(10.00 * majoration);
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
