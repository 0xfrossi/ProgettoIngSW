package view;

public class LibriView {

	public static final String[] SCEGLI_RICERCA = {"Cerca per titolo ","Cerca per autore "};
	public static final String[] SCEGLI_STAMPA = {"Visualizza tutti i libri \n","Visualizza libri in italiano \n","Visualizza libri in inglese \n"};
	
	
	public static String insAutori() {
		
		return "Inserisci l'autore/i: ";
		
	}
	
	public static String insNumPag() {
		
		return "Inserisci il numero di pagine: ";
	}
	

	public  static String insCasaEd() {
		
		return "Inserisici la casa editrice: ";
	}
	
	
	
	public static String insAnnoPub() {
		
		return "Inserisci la data di pubblicazione: ";
	}
	
	
	public static void libroRimosso() {
		
		
		System.out.println( "Il libro selezionato e'stato rimosso ");
	}
	
	
}
