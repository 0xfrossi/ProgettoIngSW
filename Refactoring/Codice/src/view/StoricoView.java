package view;

public class StoricoView {

	
	public static void numPrestitiAnnoFruitore(String user, int count) {
	
		System.out.printf( user + " ha totalizzato "+ count + " prestiti nell'anno" + "\n");
	}
	
	
	public static String insAnnoDiRif() {
		return"Inserisci l'anno di riferimento: ";
	}
	
	public static void noDati() {
		
		System.out.println("Nessun dato trovato");
	}
	
	
	public static void stampaDato(String mess) {
		
		System.out.println(mess);
	}
	
	
	
	public static void numPrestAnno(int count) {
	System.out.printf( "Nell'anno  sono stati registrati "+ count + " prestiti "+" \n");
	}
	
	
	public static void prorogheAnnoSolare(int count) {
	System.out.printf( "Nell'anno  sono state realizzate " +count+ " proroghe"+ "\n");
	}
	
	public static void risorsaPiuRic(String nome) {
		System.out.printf("La risorsa piu' richista nell'anno specificato e' "+ nome +"\n");
	}
	
	
	public static void noRisorsa() {
		
		System.out.println("Nessuna risorsa chiesta ad oggi ");
	}
	
	public static void nessunoStorico() {
		
		System.out.println("Nessun dato storico al monento disponibile ");
	}
}
