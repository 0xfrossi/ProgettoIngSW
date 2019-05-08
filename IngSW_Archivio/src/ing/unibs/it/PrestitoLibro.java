package ing.unibs.it;

public interface PrestitoLibro {
	
public static final int GIORNI_DURATA_PRESTITO = 30;
	
	/**
	 *  durata proroga del prestito di un Libro
	 */
	public static final int GIORNI_DURATA_PROROGA = 30;
	
	/**
	 *da che giorno e'possibile chiedere la proroga del prestito del Libro
	 */
	public static final int GIORNI_PRIMA_PER_PROROGA = -5;
	
	/**
	 * quanti Libri possono essere in prestito contemporaneamente dalla stessa persona
	 */
	public static final int PRESTITI_MAX = 3;
	
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
