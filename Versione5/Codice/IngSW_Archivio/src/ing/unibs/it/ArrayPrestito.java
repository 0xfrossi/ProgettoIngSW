package ing.unibs.it;

import java.io.Serializable;
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
public class ArrayPrestito implements Serializable {
	
	//Attributi
	private static final long serialVersionUID = 1L;
	private ArrayList<Prestito> prestiti;
	//private LogsDati logs =LogsDati.getIstance();
	
	
	/**
	 * Costruttore vuoto che inizializza l'array contenente i prestiti
	 */
	public ArrayPrestito() {
		prestiti= new ArrayList<Prestito>();
		//LogsDati.getIstance();
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
			if(prestiti.get(i).getRisorsa().getCodiceUnivoco()==risorsa.getCodiceUnivoco() && prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()))
				return false;	
		}
		return true;
	}
	
	/**
	 * Controlla se i prestiti nell'array sono scaduti e in caso li rimuovo 
	 */
	public void checkPrestiti(){
		for (int i = 0; i < prestiti.size(); i++){
			if(!prestiti.get(i).isPrestitoFinito()) {
				if(prestiti.get(i).getDataFinePrestito().compareTo((GregorianCalendar)GregorianCalendar.getInstance()) < 0){
				
				prestiti.get(i).setPrestitoFinito(true);
				prestiti.get(i).getRisorsa().finePrestito();
				LogsDati.getIstance().addPrestitiTerminati(prestiti.get(i));
				prestiti.remove(i);					
				}
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
	
	/**
	 * Stampa prestiti
	 * @param daStampare array da stampare
	 */
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
		LogsDati.getIstance().addInlogsPrestiti(prestito);
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
			System.out.println(Costanti.NO_PRESTITI_ATTIVI);
		
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
	 * @return  array contenente i prestiti del utente selezionato
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
	 * @param fruitore che seleziona
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
		
		selezionato = MyUtil.leggiIntero(Costanti.SELEZIONA, 1, prestiti.size());		
		Prestito prestitoSelezionato = prestiti.get(selezionato-1);
		return prestitoSelezionato;
	}
	
	/**
	 * rinnova un prestito da una lista
	 */
	public void rinnovaPrestito(Fruitore fruitore){
		
		if(prestiti.isEmpty())
			System.out.println(Costanti.NO_PRESTITI_RINNOVI);

		else{
			System.out.println(Costanti.MOSTRA_PRESTITI);
			
			
			selezionaPrestito(fruitore).rinnovaPrestito();
			
			if(selezionaPrestito(fruitore).getProrogaOk())
				LogsDati.getIstance().addInProrogati(selezionaPrestito(fruitore));
	
		}
	}
	
	
	/**
	 * Annulla il prestito di una certa risorsa
	 * @param fruitore il fruitore associato al prestito
	 * @param risorsaDaAnnullare la risorsa per la quale chiedo l'annullamento
	 */
	public void annullaPrestitoRisorsa(Fruitore fruitore, Risorsa risorsaDaAnnullare) {
		 if(prestitoNotExist(fruitore, risorsaDaAnnullare))
			 System.out.println(Costanti.NO_PRESTITI_ATTIVI);
		 else {
			
			for(int i = prestiti.size()-1; i >= 0; i--){
				if(prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()) && prestiti.get(i).getRisorsa().getCodiceUnivoco()==risorsaDaAnnullare.getCodiceUnivoco()){
					prestiti.get(i).setPrestitoFinito(true);
					LogsDati.getIstance().addPrestitiTerminati(prestiti.get(i));
					prestiti.remove(i);
					System.out.println(Costanti.PRESTITO_ANNULLATO);
				}
			}
		 }
			
	}
	
	
	
	/**
	 * annulla tutti i prestiti  di un utente
	 * @param utente l'utente a cui elimino tutti i prestiti
	 */
	public void annullaPrestitiUtente(Fruitore utente) {		
		
		for(int i = prestiti.size()-1; i >= 0; i--){
			if(prestiti.get(i).getFruitore().getUsername().equals(utente.getUsername())){
				prestiti.get(i).setPrestitoFinito(true);
				prestiti.get(i).getRisorsa().finePrestito();
				LogsDati.getIstance().addPrestitiTerminati(prestiti.get(i));
				prestiti.remove(i);
			}
		}
		
	}	
	
	/**
	 * annulla tutti i prestiti di un insieme di utenti
	 * @param utenti l'elenco degli utenti dei quali rimuovere tutti i prestiti
	 */
	public void annullaPrestitiUtenti(ArrayList<Fruitore>utenti){
		for(int i = 0; i < utenti.size(); i++)
			annullaPrestitiUtente(utenti.get(i));	
	}
	
	
	
	/**
	 * Permette di chiedere un libro in prestito, previo controllo sul numero di libri gia' in prestito
	 */
	public void chiediPrestitoLibro(Fruitore fruitore ,Risorsa libro){
		
		if(libro==null)
			System.out.println("nessun libro disponibile");
		
		else {
			if(contaPrestitiUtente(fruitore.getUsername(), "Libri") == libro.getPrestitiMax())
		
				System.out.println(Costanti.LIBRI_MAX);
		
			else{
			
		
				if(libro.getInPrestito()< libro.getNumLicenze()) {
					if(prestitoNotExist(fruitore, libro)){
						addPrestito(fruitore,libro );
						
						System.out.println(Costanti.PRENOTAZIONE_OK);
					}
					else
						System.out.println(Costanti.LIBRO_POSSEDUTO);	
				}
				else 
					System.out.println(Costanti.COPIE_GIA_INPRESTITO);
			
			}
		
		}
	 
	}
	

	/**
	 * Permette di chiedere un libro in prestito, previo controllo sul numero di libri gia' in prestito
	 * @param fruitore il fritore
	 * @param film il film
	 */
	public void chiediPrestitofilm(Fruitore fruitore ,Risorsa film){
		
		if(film==null)
			System.out.println("nessun libro disponibile");
		
		else {
		
		if(contaPrestitiUtente(fruitore.getUsername(), "Films") == film.getPrestitiMax())
		
			System.out.println(Costanti.FILMS_MAX);
		
		else{
			
			
			if(film.getInPrestito()< film.getNumLicenze()) {
				
				if(prestitoNotExist(fruitore, film)){
					addPrestito(fruitore, film);
					System.out.println(Costanti.PRENOTAZIONE_OK);
				}
				else
					System.out.println(Costanti.FILM_POSSEDUTO);
			}
			else 
				System.out.println(Costanti.COPIE_GIA_INPRESTITO);
				
			}
		
		
		}
	}
	
	
	//Get
	
	public ArrayList<Prestito> getPrestiti() {
		return prestiti;
	}
}
	
