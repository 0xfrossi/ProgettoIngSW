package model;

import java.io.Serializable;
import java.util.ArrayList;

import customExceptions.RisorsaNotFoundException;
import interfaces.Risorsa;

/**
 * Gestisce operazioni comuni su  risorse
 * @author Francesco
 *
 */
public abstract class RisorseModel  implements Serializable{

	
	private static final long serialVersionUID = 1L;	

	public RisorseModel() {
		
	}

	
	/**
	 * ricomoni array divesi in un unico array
	 * @param c1 categoria 1
	 * @param c2 categoria 2
	 * @param c1Array array categoria 1
	 * @param c2Array array categoria 2
	 * @return arraylist
	 */
	public ArrayList<Risorsa> listaUniforme(CategoriaModel c1, CategoriaModel c2, ArrayList<Risorsa> c1Array, ArrayList<Risorsa> c2Array){
		
		ArrayList<Risorsa> tutti= new ArrayList<Risorsa>();
		
		for(Risorsa risorsa: c1Array) 
			tutti.add(risorsa);
		for(Risorsa risorsa: c2Array) 
			tutti.add(risorsa);
			
		return tutti;
	}
	
	
	
	
	/**
	 * Rimuovi una risosa
	 * @param codice risorsa da rimuovere
	 * @param c1 sottoCat 1
	 * @param c2 sottoCat 2
	 * @throws RisorsaNotFoundException se non e' presente
	 */
	public void removeRisorsa(int codice, CategoriaModel c1, CategoriaModel c2) throws RisorsaNotFoundException{
		
			if(c1.esisteInSotto(codice)) {
			  int ind= c1.estraiIndiceDaRimuovere(codice);
			   c1.rimuoviRisorsa(ind);	
			}
			
			else if(c2.esisteInSotto(codice)) {
				int ind= c2.estraiIndiceDaRimuovere(codice);
				   c2.rimuoviRisorsa(ind);
				}	
			else 
				throw new RisorsaNotFoundException();
								
	}
	
	
	/**
	 * verifica a che categoria appartiene una risorsa e aggiunge la "lingua" 
	 * @param cercata la risorsa a cui interessa l'appartenenza
	 * @param ita la cat ita
	 * @param ing la cat ing
	 * @return il nome
	 */
	public String aggiungiNomeSottoCat(Risorsa cercata, CategoriaModel ita, CategoriaModel ing) {
		
		for(Risorsa risorsa: ita.getArrayRisorse()) {
			if(risorsa.getCodiceUnivoco()==cercata.getCodiceUnivoco())
				return "Lingua italiana \n";
		} 
		for(Risorsa risorsa: ing.getArrayRisorse()) {
			if(risorsa.getCodiceUnivoco()==cercata.getCodiceUnivoco())
				return "Lingua inglese \n";
		}
		return "errore \n";
	
	}
	
	
	/**
	 * Effettua una ricerca di una risorsa tramite titolo o parte di esso
	 * @param titolo il titolo da ricercare
	 * @param c1 categoria 
	 * @param c2 categoria 
	 * @param uniforme lista di tutte le risorse 
	 * @return l'arrayList di risorse trovate
	 */
	public ArrayList<Risorsa> SelezionaArrayPerTitolo(String titolo, CategoriaModel c1, CategoriaModel c2, ArrayList<Risorsa> uniforme){
		
	//	ArrayList<Risorsa> tutti= listaUniforme(c1,c2);
		ArrayList<Risorsa> ritorna= new ArrayList<>();
		
		if(c1.getArrayRisorse().isEmpty() && c2.getArrayRisorse().isEmpty())
			return null;
		
		else {
			for(Risorsa risorsa : uniforme) {
				if(risorsa.getNome().toLowerCase().contains(titolo.toLowerCase()) || risorsa.getNome().toLowerCase().equals(titolo.toLowerCase())){		
						ritorna.add(risorsa);
				}
			}
			return ritorna;
		}
	}
	
	
	
	
	public String stampaRisorse(ArrayList<Risorsa> lista) throws NullPointerException {
		
		StringBuilder ritorno= new StringBuilder();
		if(lista.isEmpty())	
			return null;
		else {
			for(Risorsa l: lista ) 
				ritorno.append(l.toString()+"\n");	
			
			return ritorno.toString();
		}
	}	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
