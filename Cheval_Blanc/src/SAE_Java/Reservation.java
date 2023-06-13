package SAE_Java;

import java.util.ArrayList;

public class Reservation {
	
	private static int nb_reservation = 0;
	private static ArrayList<Reservation> toutes_reservations = new ArrayList<>(); 
	
	private String num_reservation;
	private ArrayList<Recapitulatif> list_recapitulatif;
	
	
	public Reservation() {
		this.setNum_reservation("R"+ genererNum_reservation());
		this.setList_recapitulatif(new ArrayList<>());
		toutes_reservations.add(this); 
	}
	
	private int genererNum_reservation() {
		nb_reservation++; 
		return nb_reservation; 
	}
	
	
	/*** 	Getters & Setters	***/
	public String getNum_reservation() {
		return num_reservation;
	}

	protected void setNum_reservation(String num_reservation) {
		this.num_reservation = num_reservation;
	}

	public ArrayList<Recapitulatif> getList_recapitulatif() {
		return list_recapitulatif;
	}

	protected void setList_recapitulatif(ArrayList<Recapitulatif> list_recapitulatif) {
		this.list_recapitulatif = list_recapitulatif;
	}
	
	
	/***   Affichage   ***/
	public String toString() {
		String affichage = "";
		affichage += "N°réservation : " + getNum_reservation() + "\n"; 
		if(!getList_recapitulatif().isEmpty()) {
			affichage += "Récapitulatif(s) : \n\n";
			for(Recapitulatif r : getList_recapitulatif()) {
				affichage += r.toString(); 
			}
		} 
		else {
			affichage += "Il n'y a aucune prestation.\n";
		}
		return affichage;
	}
	
	public void affiche() {
		System.out.println(this);
	}
	
	
	
	/***     Méthodes     ***/
	protected void ajouterRecap(Recapitulatif r) {
		list_recapitulatif.add(r);
	}
	
	protected void supprimerRecap(Recapitulatif r) {
		list_recapitulatif.remove(r);
	}
	
	
	protected boolean contains(Object o) {
		return list_recapitulatif.contains(o);
	}

	public void ajouterRecapitule(Recapitulatif r) {
		if (r != null && !list_recapitulatif.contains(r)) {
				ajouterRecap(r);
		}
	}
	
	public void supprimerRecapitule(Recapitulatif r) {
		if (r != null && list_recapitulatif.contains(r)) {
				supprimerRecap(r);
		}
	}
	
	
	
	protected static Reservation rechercherRes(String numR) {
		Reservation r = null;
		
		for (Reservation res : toutes_reservations) {
			String num = res.getNum_reservation();
			if (num.equals(numR)) {
				r = res; 
			}
		}
		
		return r; 
	}
	
	public static Reservation rechercherReservation(String num) {
		if (!num.contains("R")) {
			if (num.contains("r")) {
				num = num.replaceAll("r", "");
			}
			num = "R" + num; 
		}
		
		Reservation r = rechercherRes(num); 
		if (r == null) {
			return null; 
		}
		return r; 
	}
	
}
