package view;

public class SystemView {

	public static final String[]  OPERAZIONI = {"Agginugi risorsa in archivio ","Rimuovi risorsa dall'archivio "};
	public static final String[] REM_RISORSA = {"Rimuovi un Libro dall'archivio","Rimuovi un Film dall'archivio"};
	public static final String[] ADD_RISORSA = {"Agginugi un Libro all'archivio","Aggiungi un Film all'archivio"};
	public static final String[] SCEGLI = {"Libro ","Film "};
	public static final String[] SCEGLI_OPZIONE= {"Visualizza i libri ","cerca un libro ","Visualizza i films ","Cerca un film "};
	public static final String[] MENU_OP= {"Visualizza i fruitori attivi ","Cerca o visualizza le risorse in archivio ","Aggiungi/Rimuovi risorse dall'archivio ","Visualizza i prestiti attivi ","Accedi allo storico del sistema "};
	public static final String[] MENU_F= {"Visualizza informazioni personali ","Rinnova la tua iscrizione ","Visualizza/Cerca Risorse in Archivio ","I miei prestiti "};
	public static final String[] ACCESSO_F = {"Iscriviti ","Accedi "};
	public static final String[] MENU_PRESTITI= {"chiedi un prestito ","Rinnova un prestito ","Annulla un prestito ","Visualizza i prestiti attivi "};
	public static final String[] ACCESSO = {"Sezione Fruitori ","Sezione Operatore "};
	public static final String[] SCEGLI_STORICO = {"Chiedi Statistiche di archivio ","Dati storici "};
	public static String[]	SCEGLI_STAMPE= {"Visualizza tutti i fruitori registrati","Visualizza tutti i fruitori rinnovati","Visualizza i fruitori rimossi","Visualizza tutti i prestiti richiesti prorogati","Visualizza tutti i prestiti richiesti terminati","Visualizza le risorse rimosse"};
	public static String[]	SCEGLI_QUERY= {"Visualizza il num di prestiti di un fruitore di un certo anno","Visualizza il num di prestiti avvenuti in un certo anno","Visualizza i prestiti avvenuti in un certo anno","Visualizza i prestiti prorogati in un anno","Visualizza la risorsa piu'richiesta in un certo anno"};

	public static void comandoNnriconosciuto() {
		 
		System.out.println("Comando non riconosciuto ");
	  }
	
	
	public static  String scegli() {
		return "Scegli: ";
	}
	
	
	public static String welcome() {
		
		return "Benvenuto, accedi: ";
	}
	
	public static  String insPassOp() {
		
		return "Inserisci la password di amministratore: ";
	}
	
	public static String sceltaRisorsa() {
		
		return "Scegli che risorsa vuoi selezionare: ";
	}
	
	public static void problemiAccesso() {
		  System.out.println("Problemi di accesso, riprova ");
	  }
	
	
	public static void noAccessoOp() {
		System.out.println("Password non valida, riprova ");
	}
}

