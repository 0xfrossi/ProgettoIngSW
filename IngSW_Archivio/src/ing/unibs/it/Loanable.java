package ing.unibs.it;

import java.util.GregorianCalendar;

public interface Loanable {
	
	public abstract GregorianCalendar getDataInizioPrestito();
	public abstract GregorianCalendar getDataFinePrestito();
	public abstract GregorianCalendar getDataRichiestaProroga();
	public abstract boolean   		  getProrogaOk(); 
	
	
	/**
	 * @return il numero di copie della risorsa attualmente in prestito
	 */
	public abstract int getGiaInPrestito();
	/**
	 * @return quanti giorni la risorsa pu� stare in prestito
	 */
	public abstract int getGiorniDurataPrestito();
	/**
	 * @return di quanto viene prorogato il prestito della risorsa
	 */
	public abstract int getGiorniDurataProroga();
	/**
	 * @return quanti giorni prima della scadenza del prestito se ne pu� richiedere la proroga
	 */
	public abstract int getGiorniPrimaPerProroga();
	/**
	 * @return il numero massimo di risorse che un fruitore pu� chiedere in prestito, per ogni categoria
	 */
	public abstract int getPrestitiMax();
//	specificano cosa succede quanto una risorsa viene mandata in prestito/torna dal prestito (es. modificare il numero di copie in prestito)
	/**
	 * precondizione: ci sono copie della risorsa disponibili per il prestito.
	 * Aggiorna il numero di copie disponibili e in prestito della risorsa
	 */
	public abstract void mandaInPrestito();
	/**
	 * precondizione: ci sono copie della risorsa attualmente in prestito.
	 * Aggiorna il numero di copie disponibili e in prestito della risorsa
	 */
	public abstract void tornaDalPrestito();
}
