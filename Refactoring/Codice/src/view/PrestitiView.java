package view;

public class PrestitiView {

	
	
	public static void prestitoEseguito() {
		
		System.out.println("Richiesta eseguita, la risorsa e' ora disponibile ");
	}
	
	public  static void StampaInfo(String mess) {
		
		System.out.println(mess);
	}
	
	public static void spazio() {
		
		System.out.println();
	}
	
	public static void prestitiVuoti() {
		
		System.out.println("Nessun prestito attivo al momento ");
	}
	
	public static void prestitoAnnullato() {
		
		System.out.println("Il prestito selezionato e'stato annullato ");
	}
	public static void noAnnullamento() {
		
		System.out.println("Nessun prestito da annullare ");
	}
	
	
	public static String selezionaPrestito() {
		
		return "Seleziona il prestito, digita un numero: ";
	}
	
	public static void mostraPrestiti() {
		
		System.out.println("Ecco i tuoi prestiti: ");
	}
	
	public static void nessunRinnovoPrestiti() {
		
		System.out.println("Nessun prestito disponibile al rinnovo ");
	}
	
	
}
