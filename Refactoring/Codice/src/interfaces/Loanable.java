package interfaces;

import java.util.GregorianCalendar;


/**
 * Interfaccia che contiene metodi che deve possedere una risorsa predisposta al prestito
 * @author Francesco Rossi
 *
 */
public interface Loanable {
	
	//costanti per la categoria film	
	public static final int GIORNI_DURATA_PRESTITO_FILM = 20;
	public static final int GIORNI_DURATA_PROROGA_FILM = 20;
	public static final int GIORNI_PRIMA_PER_PROROGA_FILM = -5;
	public static final int PRESTITI_MAX_FILM = 5;
	
	//costanti per la categoria libro
	public static final int GIORNI_DURATA_PRESTITO = 30;
	public static final int GIORNI_DURATA_PROROGA = 30;
	public static final int GIORNI_PRIMA_PER_PROROGA = -5;
	public static final int PRESTITI_MAX = 3;
	
	/**
	 * manda in prestito una risorsa
	 */
	public abstract void inizioPrestito();
	
	/**
	 * termina il prestito di una risorsa
	 */
	public abstract void finePrestito();
	
	//Getters da sovrascrivere 
	public abstract GregorianCalendar getDataInizioPrestito() ;
	public abstract GregorianCalendar getDataFinePrestito() ;
	public abstract GregorianCalendar getDataRichiestaProroga();
	public abstract boolean   		  getProrogaOk(); 
	public abstract int getInPrestito();
	public abstract int getGiorniDurataPrestito();
	public abstract int getGiorniDurataProroga();
	public abstract int getGiorniPrimaPerProroga();
	public abstract int getPrestitiMax();
	
}
