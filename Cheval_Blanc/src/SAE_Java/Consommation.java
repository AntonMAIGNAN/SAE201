package SAE_Java;

public abstract class Consommation extends Service {
	
	protected static double majoration = 1.50;
	
	public Consommation(boolean serviceChambre) {
		super(serviceChambre);
	}
}
