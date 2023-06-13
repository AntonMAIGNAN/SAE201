package SAE_Java;

import java.util.ArrayList;

public abstract class Prestation {
	
	private String label; 
	private double prixTTC;
	private ArrayList<Reservation> list_reservation;
	
	public Prestation() {
		list_reservation = new ArrayList<>();
	}

	/***	Getters & Setters	***/
	public String getLabel() {
		return label;
	}

	protected void setLabel(String label) {
		this.label = label;
	}
	
	public double getPrixTTC() {
		return prixTTC;
	}

	protected void setPrixTTC(double prixTTC) {
		this.prixTTC = prixTTC;
	}

	public ArrayList<Reservation> getList_reservation() {
		return list_reservation;
	}

	protected void setList_reservation(ArrayList<Reservation> list_reservation) {
		this.list_reservation = list_reservation;
	}
	
	
	/***	Affichage	***/
	public void affiche_list_reservation() {
		for (Reservation r : getList_reservation()) {
			System.out.println(r.getNum_reservation());
		}
	}
	
	
	/***     Méthodes     ***/
	protected void ajouterRecap(Reservation r) {
		getList_reservation().add(r);
	}
	
	protected void supprimerRecap(Reservation r) {
		getList_reservation().remove(r);
	}
	
	
	public void ajouterRecapitule(Reservation r) {
		if (r != null && !getList_reservation().contains(r)) {
				ajouterRecap(r);
		}
	}
	
	public void supprimerRecapitule(Reservation r) {
		if (r != null && getList_reservation().contains(r)) {
				supprimerRecap(r);
		}
	}
}