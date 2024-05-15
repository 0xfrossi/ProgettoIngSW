package view;

public class FilmsView {

	
	public static final String[] SCEGLI_RICERCA = {"Cerca per titolo ","Cerca per attore "};
	public static final String[] SCEGLI_STAMPA = {"Visualizza tutti i films \n","Visualizza films in italiano \n","Visualizza films in inglese \n"};
	
	
	public static String insTitolo() {
		
		return "\n Inserisici il titolo: ";
	}
	
	public static void StampaInfo(String mess) {
		
		System.out.println(mess);
	}
	
	public static String insUscita() {
		
		return "\n Inserisici la data d'uscita: ";
	}
	
	public static String insRegista() {
		
		return "\n Inserisici il regista: ";
	}
	
	public static String insAttore() {
		
		return "\n Inserisici l'attore/i protagonisti: ";
	}
	
	public static void filmRimosso() {
		
		
		System.out.println( "Il film selezionato e'stato rimosso ");
	}
	
	
}
