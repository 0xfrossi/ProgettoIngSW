package util.Unibs;

import java.io.*;

public class IOFileUtil
{
	private final static String MSG_NO_FILE = "ATTENZIONE: NON TROVO IL FILE ";
	private final static String MSG_NO_LETTURA = "ATTENZIONE: PROBLEMI CON LA LETTURA DEL FILE ";
	private final static String MSG_NO_SCRITTURA = "ATTENZIONE: PROBLEMI CON LA SCRITTURA DEL FILE ";
	private final static String MSG_NO_CHIUSURA ="ATTENZIONE: PROBLEMI CON LA CHIUSURA DEL FILE ";
	private static final String MSG_SALVATO = "Salvataggio effettuato con successo!";
	private static final String MSG_CARICATO = "Caricamento avvenuto con successo!";
  	
	public static Object caricaSingoloOggetto (File f)
	 {
		 Object letto = null;
		 ObjectInputStream ingresso = null;
			
		 try
			{
			 ingresso = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
				
			 letto = ingresso.readObject();
			 
			
				
			}
		 catch (FileNotFoundException excNotFound)
			{
			 System.out.println(MSG_NO_FILE + f.getName() );
			}
		 catch (IOException excLettura)
			{
			 System.out.println(MSG_NO_LETTURA + f.getName() );
			}
		 catch (ClassNotFoundException excLettura)
			{
			 System.out.println(MSG_NO_LETTURA + f.getName() );
			}
  	 finally
			{
			 if (ingresso != null)
				{
				 try 
					{
				   ingresso.close();
					}
				 catch (IOException excChiusura)
					{
			 			System.out.println(MSG_NO_CHIUSURA + f.getName() );
					}
				}
			} 

		 return letto;
		  
	 } 
	
	
	public static void salvaSingoloOggetto (File f, Object daSalvare, boolean stampaConferma)
	 {
		 ObjectOutputStream uscita = null;
			
		 try
			{
			 uscita = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
				
			 uscita.writeObject(daSalvare);
			 
			 if(stampaConferma)
				 System.out.println(MSG_SALVATO);
				
			}
		 catch (IOException excScrittura)
			{
			 System.out.println(MSG_NO_SCRITTURA + f.getName() );
			}
		 
  	     finally
			{
			 if (uscita != null)
				{
				 try 
				  {
				   uscita.close();
				  }
				 catch (IOException excChiusura)
					{
			 			System.out.println(MSG_NO_CHIUSURA + f.getName() );
					}
				}
			} 

		 } 
	
	public static void scriviSuFile(File f, String s)
	{
		try
	     {
	          FileOutputStream prova = new FileOutputStream(f);
	          @SuppressWarnings("resource")
			PrintStream scrivi = new PrintStream(prova);
	          scrivi.println(s);
	      }
	      catch (IOException e)
	      {
	          System.out.println("Errore: " + e);
	          System.exit(1);
	      }
	}
	
	public static void checkFile(File file) throws IOException 
	{
		if (file.exists())
		{
            System.out.println("Il file " + file.getPath() + " esiste");

		}
        else if (file.createNewFile())
        {
            System.out.println("Il file " + file.getPath() + " � stato creato");
        }
        else
            System.out.println("Il file " + file.getPath() + " non pu� essere creato");
	}
	
	
	public static void checkFile(File file, Object obj) throws IOException 
	{
		if (file.exists()){
            ;

		}
        else if (file.createNewFile()) {
      
    		IOFileUtil.salvaSingoloOggetto(file, obj, false);
        }
        else
            System.out.println("Il file " + file.getPath() + " non pu� essere creato");
	}
}

