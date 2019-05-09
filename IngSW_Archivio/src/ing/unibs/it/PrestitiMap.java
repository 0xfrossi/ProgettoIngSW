package ing.unibs.it;

import java.util.ArrayList;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map.Entry;

import myLib.BelleStringhe;
import myLib.GestioneDate;
import myLib.InputDati;
import parte3.Fruitore;
import parte3.Libro;
import parte3.Prestito;
import util.Unibs.MyUtil;

public class PrestitiMap  {

	
	
	private Prestito prestito;
	
	private HashMap<Fruitore,Prestito> prestiti;
	
	public PrestitiMap() {	
		prestiti= new HashMap<Fruitore, Prestito>();	
	}
	
	
	public void addPrestito(Fruitore fruitore, Prestito prestito) {
		prestiti.put(fruitore, prestito);
		prestiti.get(prestito).getRisorsa().mandaInPrestito();
	}
	
	
	
	/**
	 * conta il numero di prestiti di un utente, relativi ad una categoria
	 * @param username lo username del fruitore
	 * @param categoria la categoria nella quale cercare i prestiti
	 * @return il numero di prestiti dell'utente relativi alla categoria indicata
	 */
	public int numPrestitiDi(String username, String categoria){
		int count = 0;
		if(categoria.equalsIgnoreCase("Libri")) {
			 for (HashMap.Entry<Fruitore, Prestito> mappa : prestiti.entrySet()) {
				 if(mappa.getKey().getUsername().equals(username) && mappa.getValue().getRisorsa() instanceof Libro)
						count++;		
			 }
		}
		return count;
	}
	
	/**
	 * controllo per tutti i prestiti presenti se sono scaduti (li rimuovo) oppure no
	 */
	public void checkPrestiti() {
		int rimossi = 0;
		for (HashMap.Entry<Fruitore, Prestito> mappa : prestiti.entrySet()) {
			if(mappa.getValue().getDataFinePrestito().compareTo((GregorianCalendar)GregorianCalendar.getInstance()) < 0) {	//se dataScadenza � precedente a oggi ritorna -1
				mappa.getValue().getRisorsa().tornaDalPrestito();
				prestiti.remove(mappa.getKey(),mappa.getValue());
				rimossi++;				
			}
		}
		System.out.println("Risorse tornate dal prestito: " + rimossi);
	}
	
	
	/**
	 * stampa i prestiti  di un utente
	 * @param username  username dell'utente di cui stampare i prestiti
	 */
	public void stampaPrestitiDi(String username) {		
		int totPrestiti = 0;
		for (HashMap.Entry<Fruitore, Prestito> mappa : prestiti.entrySet()){
			if(mappa.getKey().getUsername().equals(username)){
				if(totPrestiti == 0){
					System.out.println("\nPrestiti in corso: \n");
					System.out.println();
				}
				mappa.getValue().stampaPrestito();
				System.out.println();
				totPrestiti++;
			}
		}
		if(totPrestiti == 0){
			System.out.println("Nessun prestito registrato");
		}
	}
	
	
	public void stampaPrestiti(){
		for(HashMap.Entry<Fruitore, Prestito> mappa : prestiti.entrySet()){
			System.out.println(mappa.getKey().getUsername()+ " " +mappa.getValue().getRisorsa().getNome());
		}
	}
	
	
	/**
	 * annulla tutti i prestiti  di un utente
	 * @param utente l'utente a cui elimino tutti i prestiti
	 */
	public void annullaPrestitiUtente(Fruitore utente) 
	{		
		boolean prestato=true;
		for(HashMap.Entry<Fruitore, Prestito> mappa : prestiti.entrySet()){
			if(mappa.getKey().getUsername().equals(utente.getUsername())){
				
				mappa.getValue().getRisorsa().tornaDalPrestito();
				prestiti.remove(utente, mappa.getValue());
				prestato=false;
				
			}
		}
		if(prestato){
			System.out.println("Al momento non hai prestiti attivi");
		}
		else{
			System.out.println("i tuoi prestiti sono stati eliminati");
		}
	}	
	
	public void annullaPrestitiUtenti(ArrayList<Fruitore> utenti){
		
		for(HashMap.Entry<Fruitore, Prestito> mappa : prestiti.entrySet())
		{
			annullaPrestitiUtente(mappa.getKey());
		}
	}
	/**
	 * precondizione: fruitore != null & risorsa != null
	 * controlla se il fruitore possiede gi� in prestito la risorsa richiesta
	 * @param fruitore il fruitore che richiede il prestito
	 * @param libro la risorsa richiesta
	 * @return true se il prestito � fattibile (il fruitore non possiede gi� la risorsa in prestito)
	 */
	public boolean prestitoNotExist(Fruitore fruitore, Risorsa risorsa) {
		
		for(HashMap.Entry<Fruitore, Prestito> mappa : prestiti.entrySet()){
			if(mappa.getValue().getRisorsa().getCodiceUnivoco()==risorsa.getCodiceUnivoco() && mappa.getKey().getUsername().equals(fruitore.getUsername())){
				return false;
			}
		}
//		se arriva qua l'utente non ha ancora la risorsa in prestito
		return true;
		}
	
	/**
	 * rinnovo  prestito
	 * @param fruitore il fruitore che richiede il rinnovo 
	 */
	public void rinnovoPrestito(Fruitore fruitore){
		
		GregorianCalendar oggi= (GregorianCalendar)GregorianCalendar.getInstance();
		ArrayList<Prestito>ArrayPrestiti = new ArrayList<>();
		
		for(HashMap.Entry<Fruitore, Prestito> mappa : prestiti.entrySet()){
			
			if(mappa.getKey().getUsername().equals(fruitore.getUsername())){
				ArrayPrestiti.add(prestito);
			}
		}
		if(ArrayPrestiti.isEmpty()){
			System.out.println("Non hai prestiti attivi da rinnovare!");
		}
		
		else{
			System.out.println("Seleziona il prestito da rinnovare: ");
			
			for(int i = 0; i < ArrayPrestiti.size(); i++){
				System.out.println("\n" + (i+1) + ") ");//stampo la posizione partendo da 1)
				System.out.println("**********");
				ArrayPrestiti.get(i).stampaPrestito();
				System.out.println("**********");
			}
			
			int sel = MyUtil.leggiIntero("\n Seleziona la risorsa per la proroga del prestito (0 per annullare): ", 0, ArrayPrestiti.size());
			if(sel != 0){
				Prestito selezionato = ArrayPrestiti.get(sel-1);
				
				if(oggi.after(selezionato.getDataRichiestaProroga())){
					selezionato.setDataInizioPrestito(oggi);
					selezionato.setProrogaOk(true);
				}
				else {
				
					System.out.println(" prestito non ancora rinnovabile: ");	
				}
			}
		}
	}
	
}


