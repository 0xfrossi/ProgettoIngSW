package model;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Risorsa;

/**
 * Classe che gestisce l'arrayList di films
 * @author Francesco
 *
 */
public class FilmsModel extends RisorseModel implements Serializable {

	//Attributi
	private static final long serialVersionUID = 1L;
	private CategoriaModel films;
	private CategoriaModel filmsIng;
	private CategoriaModel filmsIta;
	private ArrayList<Risorsa> rimossi;
	
	
	/**
	 * Costruttore che inizializza le categorie
	 */
	public FilmsModel() {
		films= new CategoriaModel();
		filmsIng= new CategoriaModel();
		filmsIta= new CategoriaModel();
		films.add(filmsIng);
		films.add(filmsIta);
		rimossi= new ArrayList<>();
	}
	
	
	/**
	 * Riunisce le risorse rimosse in un unica lista
	 */
	public void filmsRimossiUniforme(){
		
		ArrayList<Risorsa>lista =listaUniforme(filmsIng, filmsIta, filmsIng.getRisorseRimosse(), filmsIta.getRisorseRimosse());
		if(lista==null)
			return;
		else {
			for(Risorsa r: lista) {
				rimossi.add(r);
			}
		}
	}
	
	
	
	/**
	 * Effettua una ricerca di un film attraverso l'attore che recita in esso
	 * @param attore l'attore di cui cerco i films
	 * @return array degli attori trovati
	 */
	public ArrayList<Risorsa> cercaFilmPerAttore(String attore) throws NullPointerException {
		
		
		ArrayList<Risorsa> tutti= listaUniforme(filmsIng,filmsIta,filmsIng.getArrayRisorse(),filmsIta.getArrayRisorse());
		ArrayList<Risorsa> ritorna= new ArrayList<>();
		if(filmsIng.getArrayRisorse().isEmpty()&& filmsIta.getArrayRisorse().isEmpty())
			return null;
		
		else {
			for(Risorsa risorsa : tutti) {
				for(String attor: risorsa.getAttori()) {
					if(attor.toLowerCase().equals(attore.toLowerCase()) || attor.toLowerCase().contains(attore.toLowerCase())) {
						
						ritorna.add(risorsa);
					}
				}	
			}
			return ritorna;
		}	
	}
	
	
	
	//Getters
	
	public CategoriaModel getFilms() {
		return films;
	}

	public CategoriaModel getFilmsIng() {
		return filmsIng;
	}

	public CategoriaModel getFilmsIta() {
		return filmsIta;
	}
	
	public ArrayList<Risorsa> getRimossi(){
		return rimossi;
	}
}
