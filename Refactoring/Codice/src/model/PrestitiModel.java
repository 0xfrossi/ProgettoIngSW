package model;

import java.io.Serializable;
import java.util.ArrayList;
import customExceptions.PrestitoIsNotPossibleException;
import interfaces.Risorsa;
import myUtil.MyUtil;



/**
 * Gestione prestiti 
 * @author Francesco
 *
 */
public class PrestitiModel implements Serializable{
	
	//Attributi
	private static final long serialVersionUID = 1L;
	private ArrayList<PrestitoModel> prestiti;
	private ArrayList<PrestitoModel> prestitiStorico;
	private ArrayList<PrestitoModel> prestitiTerminatiStorico;
	private ArrayList<PrestitoModel> prestitiProrogatiStorico;
		
		
		
	/**
	 * Costruttore vuoto che inizializza l'array contenente i prestiti
	 */
	public PrestitiModel() {
		
		prestiti = new ArrayList<PrestitoModel>();
		prestitiStorico = new ArrayList<PrestitoModel>();
		prestitiTerminatiStorico = new ArrayList<PrestitoModel>();
		prestitiProrogatiStorico = new ArrayList<PrestitoModel>();	
	}

	
	
	/**
	 * crea un prestito e lo aggiunge in "prestiti"
	 * @param fruitore il fruitore che chiede il prestito
	 * @param risorsa la risorsa chiesta
	 * @throws PrestitoIsNotPossibleException prestito non possibile
	 */
	public void addPrestito(FruitoreModel fruitore, Risorsa risorsa) throws PrestitoIsNotPossibleException{
		
		PrestitoModel prestito = new PrestitoModel(fruitore, risorsa);
		if(prestitoIsPossible(fruitore, risorsa)) {
				prestiti.add(prestito);
				prestitiStorico.add(prestito);
				prestito.getRisorsa().inizioPrestito();//aggiorno il num copie in prestito	
				
		}
		else  
			   throw new PrestitoIsNotPossibleException();
		
		
	}
	
	
	/**
	 * Controlla se il prestito da rimuovere e contenuto nell'array
	 * @param fruitore il fruitore associato al prestito
	 * @param risorsaDaAnnullare la risorsa per la quale chiedo l'annullamento
	 * @return la posizione
	 */
	public int posizonePrestitoDaAnnullare(FruitoreModel fruitore, Risorsa risorsaDaAnnullare) {
		
			
			int posizione=-1;
			if(!prestiti.isEmpty()) {
				for(int i = prestiti.size()-1; i >= 0; i--){
					if(prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()) && prestiti.get(i).getRisorsa().getCodiceUnivoco()==risorsaDaAnnullare.getCodiceUnivoco()){
						posizione=i;	
					}
				}
			}
				return posizione;
			
	}
	
	/**
	 * Annulla il prestito selezionato
	 * @param posizione la posizione del prestito nella lista 
	 */
	public void annullaPrestito(int posizione) {
		if(posizione!=-1) {
			
			prestiti.get(posizione).finisciPrestito();
			prestitiTerminatiStorico.add(prestiti.get(posizione));
			prestiti.remove(posizione);
		}
		
	}
	
	
	
	/**
	 * annulla tutti i prestiti  di un utente
	 * @param utente l'utente a cui elimino tutti i prestiti
	 */
	public void annullaPrestitiUtente(FruitoreModel utente) {		
		
		for(int i = prestiti.size()-1; i >= 0; i--){
			if(prestiti.get(i).getFruitore().getUsername().equals(utente.getUsername())){
				
				annullaPrestito(i);
				//LogsDati.getIstance().addPrestitiTerminati(prestiti.get(i));
				
			}
		}
		
	}	
	
	
	/**
	 * annulla tutti i prestiti di un insieme di utenti
	 * @param utenti l'elenco degli utenti dei quali rimuovere tutti i prestiti
	 */
	public void annullaPrestitiUtenti(ArrayList<FruitoreModel> utenti){
		for(int i = 0; i < utenti.size(); i++)
			annullaPrestitiUtente(utenti.get(i));	
	}
	
	
	
	/**
	 * Controlla se il prestito puo' venire eseguito
	 * @param fruitore il fruitore che richiede il prestito
	 * @param risorsa la risorsa che chiede
	 * @return true se e' eseguibile, false altrimenti
	 */
	public boolean prestitoIsPossible(FruitoreModel fruitore, Risorsa risorsa) {
		
		if(risorsa==null)
			return false;
		/*for(int i=0; i<prestiti.size(); i++) {
			
			if(prestiti.get(i).getRisorsa().equals(risorsa) && prestiti.get(i).getFruitore().equals(fruitore) )
				return false;
		}
		*/

		if(contaPrestitiUtente(fruitore, risorsa.getClass().getSimpleName())== risorsa.getPrestitiMax())
			return false;
		else { 
			if(risorsa.getInPrestito()<risorsa.getNumLicenze()) {
				if(prestitoNotExist(fruitore, risorsa))	
					return true;		
			}
			return false;
		}
	}
	
	
	/**
	 * precondizione: fruitore e risorsa esistono
	 * controlla se il fruitore possiede gia' in prestito la risorsa richiesta
	 * @param fruitore il fruitore che richiede il prestito
	 * @param risorsa la risorsa richiesta
	 * @return true se il prestito fattibile (il fruitore non possiede gia' la risorsa in prestito)
	 */
	public boolean prestitoNotExist(FruitoreModel fruitore, Risorsa risorsa){
		for(int i=0;i<prestiti.size();i++){
			if(prestiti.get(i).getRisorsa().getCodiceUnivoco()==risorsa.getCodiceUnivoco() && prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()))
				return false;	
		}
		return true;
	}
	
	
	/**
	 * conta il numero di prestiti attivi di un utente, per una categoria a scelta
	 * @param fruitore   fruitore
	 * @param categoria  categoria di riferimento
	 * @return  numero  prestiti del fruitore per la categoria
	 */
	public int contaPrestitiUtente(FruitoreModel fruitore, String categoria) {
	
		int count = 0;
		if(categoria.equalsIgnoreCase("LibroModel")) {
			for(int i=0;i<prestiti.size();i++) {
				if(prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()) && prestiti.get(i).getRisorsa() instanceof LibroModel)
					count++;
			}
		}	
		else if(categoria.equalsIgnoreCase("FilmModel")) {
			for(int i=0;i<prestiti.size();i++) {
				if(prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()) && prestiti.get(i).getRisorsa() instanceof FilmModel)
					count++;
			}
		
		}		
		return count;
	}
	
	
	/**
	 * seleziona i prestiti di un certo fruitore
	 * @param fruitore il fruitore a cui ragruppo tutti i prestiti
	 * @return  array contenente i prestiti del utente selezionato
	 */
	public ArrayList<PrestitoModel> filtraPrestitiPerUser(FruitoreModel fruitore){
		
		ArrayList<PrestitoModel>arrayPrestiti = new ArrayList();
		
		for(int i = 0; i < prestiti.size(); i++){
			if(prestiti.get(i).getFruitore().getUsername().equals(fruitore.getUsername()))
				arrayPrestiti.add(prestiti.get(i));
			
		}
		return arrayPrestiti;
	}
	
	
	/**
	 * Controlla se i prestiti nell'array sono scaduti e in caso li rimuovo 
	 */
	public void checkPrestitiTerminati() {
	
		for (int i = 0; i < prestiti.size(); i++){
			if(!prestiti.get(i).isPrestitoFinito()) {
				if(prestiti.get(i).getDataFinePrestito().compareTo(MyUtil.dataCorrente()) < 0){		
				prestiti.get(i).setPrestitoFinito(true);
				prestiti.get(i).getRisorsa().finePrestito();
			//	LogsDati.getIstance().addPrestitiTerminati(prestiti.get(i));
				prestiti.remove(i);					
				}
			}
		}	
	}
	

	
	
	
	
	//Getter
	
	public ArrayList<PrestitoModel> getPrestiti() {
		return prestiti;
	}

	public ArrayList<PrestitoModel> getPrestitiStorico() {
		return prestitiStorico;
	}

	public ArrayList<PrestitoModel> getPrestitiTerminatiStorico() {
		return prestitiTerminatiStorico;
	}

	public ArrayList<PrestitoModel> getPrestitiProrogatiStorico() {
		return prestitiProrogatiStorico;
	}
	
	
	
	
	
	
}
