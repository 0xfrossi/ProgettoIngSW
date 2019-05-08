package util.Unibs;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
public class MyUtil {
	
	
	 private static Scanner lettore = creaScanner();
	  
	  private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
	  private final static String ERRORE_MINIMO= "Attenzione: e' richiesto un valore maggiore o uguale a ";
	  private final static String ERRORE_STRINGA_VUOTA= "Attenzione: non hai inserito alcun carattere";
	  private final static String ERRORE_MASSIMO= "Attenzione: e' richiesto un valore minore o uguale a ";
	  private final static String MESSAGGIO_AMMISSIBILI= "Attenzione: i caratteri ammissibili sono: ";

	  private final static char RISPOSTA_SI='S';
	  private final static char RISPOSTA_NO='N';

	  

	  private static Scanner creaScanner ()
	  {
	   Scanner creato = new Scanner(System.in);
	   creato.useDelimiter(System.getProperty("line.separator"));
	   return creato;
	  }
	  
	  public static String leggiStringa (String messaggio)
	  {
		  System.out.print(messaggio);
		  return lettore.next();
	  }
	  
	  public static String leggiStringaNonVuota(String messaggio)
	  {
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 lettura = lettura.trim();
		 if (lettura.length() > 0)
		  finito=true;
		 else
		  System.out.println(ERRORE_STRINGA_VUOTA);
	   } while (!finito);
	   
	   return lettura;
	  }
	  
	  	  
	  public static char leggiChar (String messaggio)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	    {
	     System.out.print(messaggio);
	     String lettura = lettore.next();
	     if (lettura.length() > 0)
	      {
	       valoreLetto = lettura.charAt(0);
	       finito = true;
	      }
	     else
	      {
	       System.out.println(ERRORE_STRINGA_VUOTA);
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	  
	  public static boolean yesOrNo(String messaggio)
	  {
		  String mioMessaggio = messaggio + "("+RISPOSTA_SI+"/"+RISPOSTA_NO+")";
		  char valoreLetto = leggiUpperChar(mioMessaggio,String.valueOf(RISPOSTA_SI)+String.valueOf(RISPOSTA_NO));
		  
		  if (valoreLetto == RISPOSTA_SI)
			return true;
		  else
			  return false;
	  }
	  
	  public static char leggiUpperChar (String messaggio, String ammissibili)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	   {
	    valoreLetto = leggiChar(messaggio);
	    valoreLetto = Character.toUpperCase(valoreLetto);
	    if (ammissibili.indexOf(valoreLetto) != -1)
		 finito  = true;
	    else
	     System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
	   } while (!finito);
	   return valoreLetto;
	  }
	  
	  
	  public static int leggiIntero (String messaggio)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     System.out.print(messaggio);
	     if (lettore.hasNextInt())
	      {
	       valoreLetto = lettore.nextInt();
	       finito = true;
	      }
	     else
	      {
	       System.out.println(ERRORE_FORMATO);
	      }
	    } while (!finito);
	   return valoreLetto;
	  }

	  public static int leggiInteroConMinimo(String messaggio, int minimo)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiIntero(messaggio);
	     if (valoreLetto >= minimo)
	      finito = true;
	     else
	      System.out.println(ERRORE_MINIMO + minimo);
	    } while (!finito);
	     
	   return valoreLetto;
	  }

	  public static int leggiIntero(String messaggio, int minimo, int massimo)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiIntero(messaggio);
	     if (valoreLetto >= minimo && valoreLetto<= massimo)
	      finito = true;
	     else
	      if (valoreLetto < minimo)
	         System.out.println(ERRORE_MINIMO + minimo);
	      else
	    	 System.out.println(ERRORE_MASSIMO + massimo); 
	    } while (!finito);
	     
	   return valoreLetto;
	  }

	  
	  public static double leggiDouble (String messaggio)
	  {
	   boolean finito = false;
	   double valoreLetto = 0;
	   do
	    {
	     System.out.print(messaggio);
	     if (lettore.hasNextDouble())
	      {
	       valoreLetto = lettore.nextDouble();
	       finito = true;
	      }
	     else
	      {
	       System.out.println(ERRORE_FORMATO);
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	 
	  public static double leggiDoubleConMinimo (String messaggio, double minimo)
	  {
	   boolean finito = false;
	   double valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiDouble(messaggio);
	     if (valoreLetto >= minimo)
	      finito = true;
	     else
	      System.out.println(ERRORE_MINIMO + minimo);
	    } while (!finito);
	     
	   return valoreLetto;
	  }
	 
	  public static String toStringData(LocalDate data){
			int anno = data.getYear();
			Month mese = data.getMonth();
			int giorno = data.getDayOfMonth();
			String date = giorno + "/"+ mese.toString() +  "/" + anno; 
			return date;
	  }	
	public static String toStringData(GregorianCalendar data){
			 int anno= data.get(Calendar.YEAR);
			 int mese= data.get(Calendar.MONTH)+1;
			 int giorno = data.get(Calendar.DAY_OF_MONTH);
			 String date = giorno + "/"+ mese +  "/" + anno; 
				return date;
			}		
		
	
	 
	public static boolean maggiorenne(GregorianCalendar datanascita)
	{
		boolean mag=false;

		GregorianCalendar now=new GregorianCalendar();

		int giorno=datanascita.get(Calendar.DAY_OF_MONTH);
		int mese=datanascita.get(Calendar.MONTH);
		int anno=datanascita.get(Calendar.YEAR)+18;
		GregorianCalendar mageta= new GregorianCalendar(anno,mese,giorno,0,0,0);
		mageta.set(Calendar.MILLISECOND, 0);
		long dif=now.getTimeInMillis()-mageta.getTimeInMillis();
		if(dif>=0) mag= true;
		return mag;
	}
	
	public static GregorianCalendar leggiData(String messaggio) {
			System.out.println("\n"+messaggio +"\n");
		 int anno= MyUtil.leggiInteroConMinimo("Inserisci l'anno", 1900);
		 int mese= MyUtil.leggiIntero("Inserisci il mese", 1, 12)-1;
		 int giorno =MyUtil.leggiIntero("Inserisci il giorno",1, 31);
		 
		return new GregorianCalendar(anno, mese, giorno) ;
		
	}
	
	public static  ArrayList<String> inserisciAutori(){
		ArrayList<String> autori = new ArrayList<String>();
	do
	{
		String autore = leggiStringaNonVuota("Inserisci l'autore: ");
		autori.add(autore+" ");
	} 
	while(MyUtil.yesOrNo("ci sono altri autori? "));
	
	return autori;
	}
}
