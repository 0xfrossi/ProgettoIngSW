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
	 * precondizione: fruitore e risorsa esistono
	 * controlla se il fruitore possiede gia' in prestito la risorsa richiesta
	 * @param fruitore il fruitore che richiede il prestito
	 * @param libro la risorsa richiesta
	 * @return true se il prestito fattibile (il fruitore non possiede gi� la risorsa in prestito)
	 */
	public boolean prestitoNotExist(Fruitore fruitore, Risorsa risorsa){
		for(int i=0;i<prestiti.size();i++){
			if(prestiti.get(i).getRisorsa().getNome().equals(risorsa.getNome()) && prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername())){
				return false;
			}
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
	
	public void stampaPrestiti(){
		for(int i = 0; i < prestiti.size(); i++){
			System.out.println(prestiti.get(i).getRisorsa().getNome());
			System.out.println();
		}
	}
	
	/**
	 * creo un prestito e lo aggiungo in "prestiti"
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
	
	public void rinnovaPrestito(ArrayList<Prestito> array){
		int numRisorsa;
		if(array.isEmpty())
			System.out.println("Non ci sono prestiti da rinnovare!");

		else{
			System.out.println("Seleziona il prestito da rinnovare: ");
			
			for(int i = 0; i < array.size(); i++){
				System.out.println((i+1)+".");
				array.get(i).stampaPrestito();
				System.out.println();
			}
			
			 numRisorsa = MyUtil.leggiIntero("\nSeleziona la risorsa a cui vuoi rinnovare il prestito : ", 1, array.size());
					
				Prestito prestitoSelezionato = array.get(numRisorsa-1);
				 prestitoSelezionato.rinnovaPrestito();
				
				
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
	
	
	public ArrayList<Prestito> getPrestiti() {
		return prestiti;
	}
}
	
