package ing.unibs.it;

import java.util.ArrayList;

import java.util.GregorianCalendar;
import java.util.Vector;
import util.Unibs.MyUtil;

/**
 * Classe che gestisce i prestiti 
 * 
 * @author Francesco Rossi
 *
 */
public class ArrayPrestito {
	
	//Attributi
	private ArrayList<Prestito> prestiti;
	
	
	/**
	 * Costruttore vuoto che inizializza l'array contenente i prestiti
	 */
	public ArrayPrestito() {
		prestiti= new ArrayList<Prestito>();
	}
	
	
	
	
	/**
	 * precondizione: fruitore e risorsa esistono
	 * controlla se il fruitore possiede gia' in prestito la risorsa richiesta
	 * @param fruitore il fruitore che richiede il prestito
	 * @param libro la risorsa richiesta
	 * @return true se il prestito fattibile (il fruitore non possiede gi� la risorsa in prestito)
	 */
	public boolean prestitoNotExist(Fruitore fruitore, Risorsa risorsa){
		for(int i=0;i<prestiti.size();i++){
			if(prestiti.get(i).getRisorsa().getNome().equals(risorsa.getNome()) && prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()))
				return false;	
		}
		return true;
	}
	
	/**
	 * Controlla se i prestiti nell'array sono scaduti e in caso li rimuovo 
	 */
	public void checkPrestiti(){
		for (int i = 0; i < prestiti.size(); i++){
			if(prestiti.get(i).getDataFinePrestito().compareTo((GregorianCalendar)GregorianCalendar.getInstance()) < 0){
				
				prestiti.get(i).getRisorsa().finePrestito();
				prestiti.remove(i);					
			}
		}
	
	}
	
	/**
	 * Stampa i dati dei prestiti esistenti
	 */
	public void stampaPrestiti(){
		for(int i = 0; i < prestiti.size(); i++){
			System.out.println(prestiti.get(i).getRisorsa().getNome());
			System.out.println();
		}
	}
	
	
	public void stampaPrestitiUtente(ArrayList<Prestito> daStampare) {
		
		for(int i=0;i<daStampare.size();i++) {
			System.out.println();
			daStampare.get(i).stampaPrestito();
			System.out.println();	
		}
		
	}
	
	
	/**
	 * crea un prestito e lo aggiunge in "prestiti"
	 * @param fruitore il fruitore che chiede il prestito
	 * @param risorsa la risorsa chiesta
	 */
	public void addPrestito(Fruitore fruitore, Risorsa risorsa){
		
		Prestito prestito = new Prestito(fruitore, risorsa);
		prestiti.add(prestito);
		prestito.getRisorsa().inizioPrestito();//aggiorno il num copie in prestito	
	}
	
	/**
	 * conta il numero di prestiti attivi di un utente, per una categoria a scelta
	 * @param user  username del fruitore
	 * @param categoria  categoria di riferimento
	 * @return  numero  prestiti del fruitore per la categoria
	 */
	public int contaPrestitiUtente(String user, String categoria) {
	
		int count = 0;
		if(categoria.equalsIgnoreCase("Libri")) {
			for(int i=0;i<prestiti.size();i++) {
				if(prestiti.get(i).getFruitore().getUsername().equals(user) && prestiti.get(i).getRisorsa() instanceof Libro)
					count++;
			}
		}
		return count;
	}
	
	/**
	 * stampa  prestiti attivi
	 */
	public void stampaPrestitiAttivi(){
		if(prestiti.isEmpty()) 
			System.out.println("Al momento non sono presenti prestiti attivi");
		
		else{
			for(int i=0;i<prestiti.size();i++) {
				System.out.println();
				prestiti.get(i).stampaPrestito();
				System.out.println();
			}
		}
	}
	
	/**
	 * seleziona i prestiti di un certo fruitore
	 * @param fruitore il fruitore a cui ragruppo tutti i prestiti
	 * @return ArrayList<Prestito> array contenente i prestiti del utente selezionato
	 */
	public ArrayList<Prestito> filtraPrestitiPerUser(Fruitore fruitore){
		
		ArrayList<Prestito>arrayPrestiti = new ArrayList();
		
		for(int i = 0; i < prestiti.size(); i++){
			if(prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()))
				arrayPrestiti.add(prestiti.get(i));
			
		}
		return arrayPrestiti;
	}
	
	/**
	 * Seleziona un prestito
	 * @return il prestito selezionato
	 */
	public Prestito selezionaPrestito(Fruitore fruitore) {
		
		int selezionato;
		for(int i = 0; i < prestiti.size(); i++){
			if(prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername())){
				System.out.println((i+1)+".");
				prestiti.get(i).stampaPrestito();
				System.out.println();
			}
		}
		
		selezionato = MyUtil.leggiIntero("\n Seleziona: ", 1, prestiti.size());		
		Prestito prestitoSelezionato = prestiti.get(selezionato-1);
		return prestitoSelezionato;
	}
	
	/**
	 * rinnova un prestito da una lista
	 */
	public void rinnovaPrestito(Fruitore fruitore){
		//int numRisorsa;
		if(prestiti.isEmpty())
			System.out.println("Non ci sono prestiti da rinnovare");

		else{
			System.out.println("Ecco i tuoi prestiti: ");
			
			/*for(int i = 0; i < prestiti.size(); i++){
				System.out.println((i+1)+".");
				prestiti.get(i).stampaPrestito();
				System.out.println();
			}
			
			numRisorsa = MyUtil.leggiIntero("\n Seleziona la risorsa a cui vuoi rinnovare il prestito: ", 1, prestiti.size());		
			Prestito prestitoSelezionato = prestiti.get(numRisorsa-1);*/
			selezionaPrestito(fruitore).rinnovaPrestito();
				
				
				/*if(oggi.after(prestitoSelezionato.getDataRichiestaProroga()))
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
				}*/
		}
	}
	
	public void annullaPrestitoRisorsa(Fruitore fruitore, Risorsa risorsaDaAnnullare) {
		 if(prestitoNotExist(fruitore, risorsaDaAnnullare))
			 System.out.println("Nessun prestito attivo");
		 else {
			
			for(int i = prestiti.size()-1; i >= 0; i--){
				if(prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()) && prestiti.get(i).getRisorsa().getCodiceUnivoco()==risorsaDaAnnullare.getCodiceUnivoco()){
					prestiti.get(i).getRisorsa().finePrestito();
					prestiti.remove(i);
					System.out.println("i tuoi prestiti sono stati eliminati");
				}
			}
		 }
			
	}
	
	
	
	/**
	 * annulla tutti i prestiti  di un utente
	 * @param utente l'utente a cui elimino tutti i prestiti
	 */
	public void annullaPrestitiUtente(Fruitore utente) {		
		boolean rimosso=false;
		for(int i = prestiti.size()-1; i >= 0; i--){
			if(prestiti.get(i).getFruitore().getUsername().equals(utente.getUsername())){
				
				prestiti.get(i).getRisorsa().finePrestito();
				prestiti.remove(i);
				rimosso=true;
			}
		}
		if(rimosso==false)
			System.out.println("Al momento non hai prestiti attivi");
		
		else
			System.out.println("i tuoi prestiti sono stati eliminati");
		
	}	
	
	/**
	 * annulla tutti i prestiti di un insieme di utenti
	 * @param utenti l'elenco degli utenti dei quali rimuovere tutti i prestiti
	 */
	public void annullaPrestitiUtenti(ArrayList<Fruitore>utenti){
		for(int i = 0; i < utenti.size(); i++)
			annullaPrestitiUtente(utenti.get(i));	
	}
	
	
	
	//Get
	
	public ArrayList<Prestito> getPrestiti() {
		return prestiti;
	}
}
	
