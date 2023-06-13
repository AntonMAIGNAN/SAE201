package SAE_Java;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Recapitulatif {
	
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	private static Date aujourdhui = new Date();
	
	private Reservation uneReservation;
	private String date;
	private HashMap<Prestation, Integer> list_prestation;
	
	
	public Recapitulatif(Reservation uneReservation) {
		this.setUneReservation(uneReservation);
		this.setDate(dateFormat.format(aujourdhui));
		this.setList_prestation(new HashMap<>());
		uneReservation.ajouterRecapitule(this);
	}
	
	
	/***	Getters & Setters	***/
	public Reservation getUneReservation() {
		return uneReservation;
	}

	protected void setUneReservation(Reservation uneReservation) {
		this.uneReservation = uneReservation;
	}
	
	public String getDate() {
		return date;
	}

	protected void setDate(String date) {
		this.date = date;
	}
	
	public HashMap<Prestation, Integer> getList_prestation() {
		return list_prestation;
	}
	
	protected void setList_prestation(HashMap<Prestation, Integer> list_prestation) {
		this.list_prestation = list_prestation;
	}
	
	
	
	/***	Affichage	***/
	public String toString() {
		String affichage = ""; 
		affichage += "Date : " + getDate() + "\n"; 
		if (!getList_prestation().isEmpty()) {
			for(Map.Entry<Prestation, Integer> mapEntry : getList_prestation().entrySet()) {
				Prestation p = mapEntry.getKey();
				Integer quantite =  mapEntry.getValue(); 
				affichage += " --> " + p;
				affichage += "     Quantite  |  " + quantite + "\n";
				affichage += "     Prix      |  " + quantite*p.getPrixTTC() + "\n"; 
			}
		}
		else {
			affichage += "Il n'y a aucune prestation.\n";
		}
		affichage += "\n"; 
		return affichage; 
	}
	
	public void affiche() {
		System.out.println(this);
	}
	
	
	/***     Méthodes     ***/	
	protected void ajouterPresta(Prestation p) {
		list_prestation.put(p, 1);
	}
	
	protected void modifierPresta(Prestation p, int quantite) {
		list_prestation.put(p, quantite);
	}
	
	protected void supprimerPresta(Prestation p) {
		list_prestation.remove(p); 
	}

	
	
	public void ajouterPrestation(Prestation p) {
		if (p != null) {
			if(!list_prestation.containsKey(p)) {
				ajouterPresta(p);
				p.ajouterRecapitule(getUneReservation());
			}
		}
	}
	
	public void modifierPrestation(Prestation p, int quantite) {
		if (p != null) {
			if (list_prestation.containsKey(p)) {
				if (quantite >= 1) {
					modifierPresta(p, quantite);
				}
			}
		}
	}
	
	public void supprimerPrestation(Prestation p) {
		if (p != null && list_prestation.containsKey(p)) {
			supprimerPresta(p);
			p.supprimerRecapitule(getUneReservation()); 
		}
	} 
	
}
