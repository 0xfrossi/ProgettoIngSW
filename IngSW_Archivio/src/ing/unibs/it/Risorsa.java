package ing.unibs.it;

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
	 * Stampa i dati caratterizzanti una risorsa
	 */
	public  abstract void stampaDesc();
	
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
	
	public abstract int getNumLicenze();
}
