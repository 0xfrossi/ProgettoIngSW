package model;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Risorsa;


/**
 * Classe che gestisce l'arrayList di libri
 * @author Francesco
 *
 */
public class LibriModel extends RisorseModel implements Serializable{

		//Attributi	
		private static final long serialVersionUID = 1L;
		private CategoriaModel libri;
		private CategoriaModel libriIng;
		private CategoriaModel libriIta;
		private ArrayList<Risorsa> rimossi;


		/**
		 * Costruttore della classe crea la categoria e le sotto-categorie
		 * e le aggiunge nella categoria
		 */
		 public LibriModel() {
				
			libri= new CategoriaModel();
			libriIng= new CategoriaModel(); 
			libriIta= new CategoriaModel(); 
			libri.add(libriIng);
			libri.add(libriIta);
			rimossi=new ArrayList<Risorsa>();
		}
	 
		 /**
		 * Effettua una ricerca di un libro attraverso l'autore
		 * @param autore l'autore di cui voglio i libri/o
		 * @return la lista di risorse
		 */
		public ArrayList<Risorsa> cercaLibroPerAutore(String autore) throws NullPointerException{
			
				
			ArrayList<Risorsa> tutti= listaUniforme(libriIng,libriIta,libriIng.getArrayRisorse(),libriIta.getArrayRisorse());
			ArrayList<Risorsa> ritorna= new ArrayList<>();
			
			if(libriIng.getArrayRisorse().isEmpty() && libriIta.getArrayRisorse().isEmpty())
				return null;
			
			else {	
				for(Risorsa risorsa : tutti) {
					for(String autor: risorsa.getAutori()) {
						if(autor.toLowerCase().equals(autore.toLowerCase()) || autor.toLowerCase().contains(autore.toLowerCase())) {
												
							ritorna.add(risorsa);
						}
					}	
				}
			return ritorna;
			}
		}
		
		
		/**
		 * Riunisce le risorse rimosse in un unica lista
		 */
		public void libriRimossiUniforme(){
			
			ArrayList<Risorsa>lista =listaUniforme(libriIng, libriIta, libriIng.getRisorseRimosse(), libriIta.getRisorseRimosse());
			if(lista==null)
				return;
			else {
				for(Risorsa r: lista) {
					rimossi.add(r);
				}
			}
		}
			
		
		
		//getters
		
		public CategoriaModel getLibri() {
			return libri;
		}

		public CategoriaModel getLibriIng() {
			return libriIng;
		}

		public CategoriaModel getLibriIta() {
			return libriIta;
		}	
		
		public ArrayList<Risorsa> getRimossi(){
			return rimossi;
		}
}
