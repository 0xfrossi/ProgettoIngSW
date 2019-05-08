package ing.unibs.it;

import java.io.Serializable;

public abstract class Risorsa implements Serializable {
	
	
	
	  
	
	private static final long serialVersionUID = 1L;
	
	
	public Risorsa() {
		
	}

	public abstract  String getNome();


	public  abstract void stampaDesc();
	
	public  void add(Risorsa c) {};
	
	public  void remove(Risorsa c) {};
	
	public abstract int getGiaInPrestito();
	
	public abstract int getGiorniDurataPrestito();
	
	public abstract int getGiorniDurataProroga();
	
	public abstract int getGiorniPrimaPerProroga();
	
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
