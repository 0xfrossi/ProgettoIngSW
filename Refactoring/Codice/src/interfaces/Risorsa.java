package interfaces;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Classe astratta che definisce la risorsa, un elemento generico
 * @author Francesco Rossi
 *
 */
public abstract class Risorsa implements Serializable, Loanable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore di defoult
	 */
	public Risorsa() {
		
	}
	
	/**
	 * Override del metodo equals di Object: in Object. Confronto quindi gli id delle due risorse
	 * @param risorsa la risorsa con cui confrontare l'oggetto
	 * @return true se le due risorse hanno lo stesso codice
	 */
	public abstract boolean equals(Risorsa risorsa);
	
	/**
	 * Stampa i dati caratterizzanti una risorsa
	 */
	public  abstract String toString();
	
	/**
	 * Aggiunge una risorsa in una lista
	 * @param c la risorsa da aggiungere
	 */
	public  abstract void add(Risorsa c);
	
	/**
	 * rimuove una risorsa da una lista
	 * @param c la risorsa da rimuovere
	 */
	public  abstract void remove(Risorsa c);
	
	
	
	//Getters da sovrascrivere
	
	public abstract  int getCodiceUnivoco();
	
	public abstract String getNome();
	
	public  abstract ArrayList<String> getAutori();
	
	public abstract ArrayList<String> getAttori();
	
	public abstract int getNumLicenze();
}
