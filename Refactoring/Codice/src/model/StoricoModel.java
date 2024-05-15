package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import interfaces.Risorsa;

/**
 * Classe con op su dati storici
 * @author Francesco
 *
 */
public class StoricoModel {

	//Attributi
	private FruitoriModel fruitori;
	private PrestitiModel prestiti;
	private LibriModel libri;
	private FilmsModel films;
	
	
	/**
	 * Costruttore
	 * @param fruitori model
	 * @param prestiti model
	 * @param libri model
	 * @param films model
	 */
	public StoricoModel(FruitoriModel fruitori,PrestitiModel prestiti,LibriModel libri,FilmsModel films) {
		
		this.fruitori=fruitori;
		this.prestiti=prestiti;
		this.libri=libri;
		this.films=films;
		
	}
	
	
	/**
	 * calcola in numero di prestiti di un utente per qualsiasi risorsa
	 * @param userName l'userName dell utente 
	 * @param annoRif anno da inserire
	 * @return count il numero totale dei prestiti
	 */
	public  int numPrestitiFruitoreInAnno(String userName, int annoRif){
	
		int count=0;
		for(int i=0;i< prestiti.getPrestitiStorico().size();i++){
			if(prestiti.getPrestitiStorico().get(i).getFruitore().getUsername().equals(userName)&& prestiti.getPrestitiStorico().get(i).getDataInizioPrestito().get(GregorianCalendar.YEAR)== annoRif)
				count++;
			
		}
		return count;
	}
	
	/**
	 * torna tutti i prestiti in un certo anno avvenuti nel sistema
	 * @param annoRiferimento l'anno del quale sapere il num
	 * @return l'array di prestiti
	 */
	public  ArrayList<PrestitoModel> prestitiInAnno(int annoRiferimento){
		
		
		ArrayList<PrestitoModel> inAnno = new ArrayList<>();
		for (int i=0;i< prestiti.getPrestitiStorico().size();i++) {
			if(prestiti.getPrestitiStorico().get(i).getDataInizioPrestito().get(GregorianCalendar.YEAR) == annoRiferimento)
				inAnno.add(prestiti.getPrestitiStorico().get(i));	
		}	
		return inAnno;
		
	}
	
	
	/**
	 * Calcola in numero di prestiti in un certo anno avvenuti nel sistema
	 * @param annoRiferimento l'anno del quale sapere il num
	 * @return il numero di prestiti
	 */
	public  int numPrestitiInAnnoSolare(int annoRiferimento){
		
		int count=0;
		for(int i=0; i< prestiti.getPrestitiStorico().size();i++) {
			if( prestiti.getPrestitiStorico().get(i).getDataInizioPrestito().get(GregorianCalendar.YEAR)==annoRiferimento) 
				count++;
		}
		return count;
	}
	
	
	/**
	 * Calcola in numero di proroghe in un certo anno avvenuti nel sistema
	 * @param annoRiferimento l'anno del quale sapere il num
	 * @return il numero di proroghe
	 */
	public  int  prorogheAnnoSolare(int annoRiferimento) {
		
		int count=0;
		for(int i=0; i<prestiti.getPrestitiProrogatiStorico().size();i++) {
			if(prestiti.getPrestitiProrogatiStorico().get(i).getDataFinePrestito().get(GregorianCalendar.YEAR)==annoRiferimento) 
				count++;			
		}
		return count;
	} 
	
	
	/**
	 * estrai le risorse   in un anno 
	 * @param annoRiferimento l'anno nel quale fare la statistica
	 */
	private  ArrayList<Risorsa> estraiRisorseDaPrestiti(int annoRiferimento) throws NullPointerException{
			
		ArrayList<Risorsa> risorseTemp = new ArrayList<Risorsa>();
		for (PrestitoModel prestito : prestiti.getPrestitiStorico()){
			if(prestito.getDataInizioPrestito().get(GregorianCalendar.YEAR) == annoRiferimento)
				risorseTemp.add(prestito.getRisorsa());	
		}
		return risorseTemp;
	}	
		
		/**
		 * cerca il nome della risorsa piu' in prestito
		 * @param risorse arrayList dove cercare
		 * @return il nome
		 */
		private String NomerisorsaPiuInPrestito(ArrayList<Risorsa> risorse) {
			
			int maxTotale = 0;
			String titoloRisorsaMax = null;
			for(int i = 0; i < risorse.size(); i++){
			
				int maxRisorsa = 1;
				int codice = risorse.get(i).getCodiceUnivoco();
			
				for (int j = risorse.size()-1; j > i; j--) {
					if(codice==risorse.get(j).getCodiceUnivoco()){
						maxRisorsa++;
						risorse.remove(j);
					}
				}
				if(maxRisorsa > maxTotale) {
					maxTotale = maxRisorsa;
					titoloRisorsaMax = risorse.get(i).getNome();
				}
			}		
			if(maxTotale==0)
				return null;
			
			else
				return titoloRisorsaMax;
	} 
	
	/**
	* gestisci e mostra la risorsa piu' in prestito
	 * @param annoRiferimento anno di riferimento
	 * @return nome risorsa
	 * @throws Exception se non trova niente
	 */
	public String RisorsaPiuRichiesta(int annoRiferimento) throws Exception {
			
			ArrayList<Risorsa> risorse= estraiRisorseDaPrestiti(annoRiferimento);
			if(!risorse.isEmpty())
				return NomerisorsaPiuInPrestito(risorse);
			else 
				throw new Exception();
		}

	/**
	 * Raggruppa le rimosse in unico arrayList
	 * @return risorse rimosse
	 */
	public ArrayList<Risorsa> risorseRimosse(){
		
		ArrayList<Risorsa> tutte= new ArrayList<>();
		libri.libriRimossiUniforme();
		films.filmsRimossiUniforme();
		
		if(!libri.getRimossi().isEmpty()) {
			for(Risorsa l: libri.getRimossi()) {
				tutte.add(l);
			}
		}
		if(!films.getRimossi().isEmpty()) {
			for(Risorsa f: films.getRimossi()) {
			tutte.add(f);
			}
		}
		return tutte;
		
	}
	

	//Getters
	
	public FruitoriModel getFruitori() {
		return fruitori;
	}

	public PrestitiModel getPrestiti() {
		return prestiti;
	}

	public LibriModel getLibri() {
		return libri;
	}

	public FilmsModel getFilms() {
		return films;
	}
		
	
	
	
}
