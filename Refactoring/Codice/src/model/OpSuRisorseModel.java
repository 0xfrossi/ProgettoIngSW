package model;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Risorsa;

/**
 * Classe che implementa operazioni  della classe risorsa
 * @author Francesco
 *
 */
public class OpSuRisorseModel  implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;

	public OpSuRisorseModel() {
		
	}
	
	
	/**
	 * Effettua una ricerca di un film attraverso l'attore che recita 
	 * @param attore l'attore di cui cerco i films
	 */
	/*
	public ArrayList<Risorsa> selezionaArrayPerAttore(String attore, CategoriaModel c1, CategoriaModel c2, ArrayList<Risorsa> listaUnioforme) throws NullPointerException{
		
		//ArrayList<Risorsa> tutti=  listaUniforme( c1,  c2);
		ArrayList<Risorsa> ritorna= new ArrayList<>();
		if(c1.getArrayRisorse().isEmpty()&& c2.getArrayRisorse().isEmpty())
			
			return null;
		
		else {
			for(Risorsa risorsa : listaUnioforme) {
				for(String attor: risorsa.getAttori()) {
					if(attor.toLowerCase().equals(attore.toLowerCase()) || attor.toLowerCase().contains(attore.toLowerCase())) {
						
						ritorna.add(risorsa);	
					}
				}	
			}
			return ritorna;
		}	
	}
	
	
	/**
	 * Effettua una ricerca di un libro attraverso l'autore
	 * @param autore l'autore di cui voglio i libri/o
	 */
	/*
	public ArrayList<Risorsa> selezionaArrayPerAutore(String autore,CategoriaModel c1, CategoriaModel c2, ArrayList<Risorsa> listaUnioforme) throws NullPointerException{
		
	//	ArrayList<Risorsa> tutti=  listaUniforme( c1,  c2);
		ArrayList<Risorsa> ritorna= new ArrayList<>();
		if(c1.getArrayRisorse().isEmpty()&& c2.getArrayRisorse().isEmpty())
			
			return null;
		
		else {
			
			for(Risorsa risorsa : listaUnioforme) {
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
	 * Effettua una ricerca di una risorsa tramite titolo o parte di esso
	 * @param titolo il titolo da ricercare
	 * @return l'arrayList di risorse trovate
	 */
	/*
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
	
	*/
	
	/*
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
	
	
	*/
	
	
	}
