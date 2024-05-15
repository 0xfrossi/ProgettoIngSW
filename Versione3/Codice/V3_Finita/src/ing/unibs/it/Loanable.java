package ing.unibs.it;

import java.util.GregorianCalendar;


/**
 * Interfaccia che contiene metodi che deve possedere una risorsa predisposta al prestito
 * @author Francesco Rossi
 *
 */
public interface Loanable {
	
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
