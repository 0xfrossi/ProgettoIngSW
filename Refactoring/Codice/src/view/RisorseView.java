package view;

public class RisorseView {

	
	
	public RisorseView() {
		
	}
	
	
	public static void RisorseNonPresenti() {
		
		System.out.println("Nessuna risorsa disponibile ");
	}
	
	public static void RisorsaNonPresente() {
		
		System.out.println("Risorsa non presente ");
	}
	public static void RisorsaPresente() {
		
		System.out.println("Risorsa gia'presente ");
	}
	
	public  static void StampaInfo(String mess) {
		
		System.out.println(mess);
	}
	
	public static String insSele() {
		
		return "Seleziona una risorsa tra le visualizzate: ";
	}
	
	public static String insTitolo() {
		
		return "Inserisici titolo: ";
	}
	
	public static void insInSotto() {
		
		System.out.println("scegli in che sottoCategoria vuoi inserire la risorsa: \n lingua ITA, premi (1) \n lingua ING premi (2) ");
	}
	
	public static String insGenere() {
		
		return "Inserisci il gernere: ";
		
	}
	
	public static String insCodice() {
		
		return "Inserisci il codice univoco: ";
	}
	
	public static String insNumLicenze() {
		
		return "Inserisci il numero di Licenze: ";
	}
	
	public static void StampaAggiunta() {
		
		System.out.println("Risorsa Aggiunta ");
	}
	
	
	public static String rimuoviLibro() {
		
		return "Inserisci il codice del libro da rimuovere: ";
	}
	
	public static String rimuoviFilm() {
		
		return "Inserisci il codice del film da rimuovere: ";
	}
}
