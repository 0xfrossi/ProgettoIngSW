package util.Unibs;

import java.util.Scanner;



public class MyIOconsole {

	private MyIOconsole(){
	}
	
	//			FUNZIONI PER TRATTARE GLI INTERI
	/** 
	 * @param frase: frase che deve essere visualizzata al momento della richiesta di immissione da parte dell'utente.
	 * @return il numero immesso in input
	 */
	public static int leggiIntero(String frase){
		Scanner lettore=new Scanner(System.in);
		boolean termine=false;
		int numero=0;
		
		 
		do{
			System.out.print(frase);
			
			if(lettore.hasNextInt()==true){
				numero=lettore.nextInt();
				termine=true;
			}
			else lettore.nextLine();
				
		}while(termine==false);
		
		return numero;
	}
	
	/**
	 * permette di ottenere @return un intero compreso fra due valori (@param min < intero < @param max)
	 * @param frase permette di modificare il messaggio che viene visualizzato prima dell'immissione
	 *  da parte dell'utente.
	 */
	public static int leggiIntero(String frase, int min, int max){
		int numero;
				
		do{
			numero=MyIOconsole.leggiIntero(frase);
		}while(numero>max || numero<min);
		
		return numero;
	}
	
	//				FUNZIONI PER TRATTARE LE STRINGHE
	public static String leggiStringa(String frase){
		Scanner lettore=new Scanner(System.in);
		String str;
		
		System.out.println(frase);
		str=lettore.nextLine();
		
		return str;
		
	}
	
	/**
	 * permette di ricevere in input una parola da tastiera
	 * @param frase
	 * @return
	 */
	public static String leggiParola(String frase){
		Scanner lettore=new Scanner(System.in);
		String str;
	
		System.out.println(frase);
		str=lettore.next();
	
		return str;
	}
	
	//						FUNZIONI PER TRATTARE I DOUBLE
	/**
	 * permette di ottenere @return un double compreso fra due valori (@param min < double < @param max).
	 * @param frase permette di modificare il messaggioche viene visualizzato prima dell'immissione
	 *  da parte dell'utente.
	 */
	
	public static double leggiDouble(String frase, double min, double max) {
		
		double numero;
		
		do{
			numero=MyIOconsole.leggiDouble(frase);
		}while(numero>max || numero<min);
		
		return numero;
	}
	
	/**
	 * acquisisce un double da tastiera
	 * @param frase: frase che deve essere visualizzata al momento della richiesta di immissione da parte dell'utente.
	 * @return il numero immesso in input
	 */
	public static double leggiDouble(String frase){
		Scanner lettore=new Scanner(System.in);
		boolean termine=false;
		double numero=0;
		
		 
		do{
			System.out.print(frase);
			
			if(lettore.hasNextDouble()==true){
				numero=lettore.nextDouble();
				termine=true;
			}
			else lettore.next();
				
		}while(termine==false);
		
		return numero;
	}
	
	/**
	 * acquisisce un char da tastiera
	 * @param frase: frase da mostrare al momento della richiesta d'immissione
	 * @return il carattere digitato
	 */
	public static char leggiChar(String frase) {
		Scanner lettore=new Scanner(System.in);
		boolean termine=false;
		char ch=0;
		String parziale;
		
		 
		do{
			System.out.print(frase);
			
			if(lettore.hasNext()==true){
				parziale=lettore.next();
				if(parziale.length()==1){
					ch=parziale.charAt(0);
					termine=true;
				}
			}
			else lettore.next();
				
		}while(termine==false);
		
		return ch;
	}
}
