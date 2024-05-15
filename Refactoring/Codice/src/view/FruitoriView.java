package view;


import java.util.GregorianCalendar;
import myUtil.MyUtil;



public class FruitoriView {

	
	public static String insNome() {
		
		return "\n Inserisci nome: ";
	}
	
	public static String insCogome() {
		
		return "\n Inserisci cognome: ";
	}
	
	public static String insResidenza() {
		
		return "\n Inserisci residenza: ";
	}
	
	public static String insGregorian() {
		
		return "\n Inserisci data di nascita: ";
	}
	
	public static void stampadati(String mess) {
		
		System.out.println(mess);
	}
	
	public static void benvenuto() {
		System.out.println("Accesso eseguito! ");
	}
	
	public static void rinnovoNonDisp() {
		System.out.println("Non e' ancora possibile eseguire il rinnovo, il rinnovo disponibile dal decimo giorno prima della scadenza ");
	}
	
	public static void iscrizioneRinnovata(GregorianCalendar dataScadenzaIscrizione, GregorianCalendar dataRinnovoIscrizione) {
		
		System.out.printf("Iscrizione rinnovata con successo! Prossima scadenza: %s \n Prossima data utile al rinnovo: %s \n",   dataScadenzaIscrizione, dataRinnovoIscrizione);
	}
	
	
	public static void iscrizioneEffetuata() {
		System.out.println("Iscrizione avvenuta con successo !");
	}
	
	public static void noFruitori() {
		System.out.println("nessun fruitore registrato");
	}
	
	public static void fruitoreNull() {
		System.out.println("Username e password non corrispondono, riprova ");
	}
	
	public static void printExcept(String mess) {
		System.out.println(mess);
	}
	
	public static String insUser() {
		
		return "Inserisci l'username: ";
	}
	
	
	public static String insPass() {
		
		return "Inserisci password: ";
	}
}
