package ing.unibs.it;

import java.util.ArrayList;

import java.util.GregorianCalendar;
import java.util.Vector;
import util.Unibs.MyUtil;

public class ArrayPrestito {
	
	private ArrayList<Prestito> prestiti;
	
	public ArrayPrestito() {
		prestiti= new ArrayList<Prestito>();
	}
	
	/**
	 * Controllo sei i prestiti sono scaduti e in caso rimuovo
	 */
	public void checkPrestiti() 
	{
		int rimossi = 0;
		for (int i = 0; i < prestiti.size(); i++) 
		{
			if(prestiti.get(i).getDataFine().compareTo((GregorianCalendar)GregorianCalendar.getInstance()) < 0)	//se dataScadenza e' precedente a oggi ritorna -1
			{
				prestiti.get(i).getRisorsa().tornaDalPrestito();
				prestiti.remove(i);
				rimossi++;				
			}
		}
		System.out.println("Risorse tornate dal prestito: " + rimossi);
	}
	
	public void stampaPrestiti()
	{
		for(int i = 0; i < prestiti.size(); i++)
		{
			System.out.println(prestiti.get(i).getRisorsa().getNome());
		}
	}
	
	/**
	 * crea e aggiunge un prestito all'elenco
	 * @param fruitore il fruitore che richiede il prestito
	 * @param risorsa la risorsa chiesta in prestito
	 */
	public void addPrestito(Fruitore fruitore, Risorsa risorsa)
	{
		Prestito prestito = new Prestito(fruitore, risorsa);
		prestiti.add(prestito);
		prestito.getRisorsa().mandaInPrestito();//aggiorna il numero di copie attualmente in prestito	
	}
	
	/**
	 * conta il numero di prestiti attivi di un utente, per una categoria
	 * @param user  username del fruitore
	 * @param categoria  categoria di riferimento
	 * @return  numero  prestiti del fruitore per la categoria
	 */
	public int numPrestitiDi(String user, String categoria) {
	
		int count = 0;
		
		if(categoria.equalsIgnoreCase("Libri")) {
		
			for(int i=0;i<prestiti.size();i++) {
				if(prestiti.get(i).getFruitore().getUsername().equals(user) && prestiti.get(i).getRisorsa() instanceof Libro) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * stampa  prestiti attivi
	 */
	public void stampaPrestitiAttivi() 
	{
		if(prestiti.isEmpty()) {
		
			System.out.println("Al momento non sono presenti prestiti attivi");
		}
		else
		{
			for(int i=0;i<prestiti.size();i++) {
				System.out.println();
				prestiti.get(i).stampaPrestito();;
			}
		}
	}
	
	/**
	 * precondizione: fruitore e risorsa esistono
	 * controlla se il fruitore possiede gia' in prestito la risorsa richiesta
	 * @param fruitore il fruitore che richiede il prestito
	 * @param libro la risorsa richiesta
	 * @return true se il prestito � fattibile (il fruitore non possiede gi� la risorsa in prestito)
	 */
	public boolean checkprestito(Fruitore fruitore, Risorsa libro) 
	{
		for(Prestito prestito : prestiti)
		{
			if(prestito.getRisorsa().getNome()==libro.getNome() && prestito.getFruitore().getUsername().equals(fruitore.getUsername()))
			{
				return false;
			}
		}
		//se arriva qua l'utente non ha gia' la risorsa in prestito
		return true;
		}
	
	/**
	 *rinnovo prestito
	 * @param fruitore il fruitore che richiede il rinnovo
	 */
	public void rinnovaPrestito(Fruitore fruitore) 
	{
		Vector<Prestito>prestitiUtente = new Vector<>();
		GregorianCalendar oggi=(GregorianCalendar)GregorianCalendar.getInstance();
		for(Prestito prestito : prestiti)
		{
			if(prestito.getFruitore().getUsername().equals(fruitore.getUsername()))
			{
				prestitiUtente.add(prestito);
			}
		}
		if(prestitiUtente.isEmpty())
		{
			System.out.println("Non ci sono prestiti da rinnovare!");
		}
		else
		{
			System.out.println("Seleziona il prestito che vuoi rinnovare: ");
			
			for(int i = 0; i < prestitiUtente.size(); i++)
			{
				System.out.println("\n" + (i+1) + ") ");//stampo la posizione partendo da 1)
				System.out.println();
				prestitiUtente.get(i).stampaPrestito();
				System.out.println();
			}
			
			int selezione = MyUtil.leggiIntero("\nSeleziona il libro per il quale chiedere il rinnovo del prestito (0 per annullare): ", 0, prestitiUtente.size());
					
			if(selezione != 0)
			{
				Prestito prestitoSelezionato = prestitiUtente.get(selezione-1);
				
				if(oggi.after(prestitoSelezionato.getDataRichiestaProroga()))
//				e' necessariamente precedente alla data di scadenza prestito altrimenti sarebbe stato rimosso
				{
					prestitoSelezionato.setDataInizio((GregorianCalendar)GregorianCalendar.getInstance());
					prestitoSelezionato.setProrogaOk(true);
				}
				else//non si puo' ancora rinnovare prestito
				{
					System.out.println("Il tuo prestito non � ancora rinnovabile: ");
					System.out.println("potrai effettuare questa operazione tra il " + MyUtil.toStringData(prestitoSelezionato.getDataInizio()) + 
																			" e il " + MyUtil.toStringData(prestitoSelezionato.getDataFine()));
				}
			}
			
			
		}
	}
	
	
	/**
	 * annulla tutti i prestiti attivi di un utente
	 * @param utente l'utente del quale eliminare tutti i prestiti
	 */
	public void annullaPrestitiDi(Fruitore utente) 
	{		
		int x = 0;
//dal fondo perche' se elimino dall'inizio si cambiano le posizioni
		for(int i = prestiti.size()-1; i >= 0; i--)
		{
			if(prestiti.get(i).getFruitore().getUsername().equals(utente.getUsername()))
			{
				prestiti.get(i).getRisorsa().tornaDalPrestito();
				prestiti.remove(i);
				x++;
			}
		}
		if(x == 0)
		{
			System.out.println("Al momento non hai prestiti attivi");
		}
		else
		{
			System.out.println("i tuoi prestiti sono stati eliminati");
		}
	}	
	
	/**
	 * annulla tutti i prestiti di un insieme di utenti
	 * @param utenti l'elenco degli utenti dei quali rimuovere tutti i prestiti
	 */
	public void annullaPrestitiDi(ArrayList<Fruitore>utenti)
	{
		for(int i = 0; i < utenti.size(); i++)
		{
			annullaPrestitiDi(utenti.get(i));
		}
	}
}
	
