package SAE_Java;

public abstract class Service extends Prestation {
	
	private boolean serviceChambre;
	
	public Service(boolean serviceChmabre) {
		setServiceChambre(serviceChambre); 
	}

	
	/***	Getters & Setters	***/
	public boolean getServiceChambre() {
		return serviceChambre;
	}

	protected void setServiceChambre(boolean serviceChambre) {
		this.serviceChambre = serviceChambre;
	}
	
	
	/***	Méthodes	***/
	public void modifierServiceChambre (boolean b) {
		this.serviceChambre = b;
	}
	
}
