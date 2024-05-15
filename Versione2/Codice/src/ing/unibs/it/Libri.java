package ing.unibs.it;

import java.io.Serializable;


/**
 * Classe che gestisce il vettore di libri divisi in categorie
 * @author Francesco
 *
 */
public class Libri implements Serializable  {
	
	
	//Attributi
	private static final long serialVersionUID = 1L;
	private Categoria libri;
	private Categoria libriIng;
	private Categoria libriIta;
	

	/**
	 * Cosruttore che inizializza le categorie
	 */
	public Libri() {
		libri= new Categoria();
		libriIng= new Categoria(); 
		libriIta= new Categoria(); 
		libri.add(libriIng);
		libri.add(libriIta);
	}
	
	
	/**
	 * Aggiunge libro in categoria ita
	 * @param l libro da aggiungere
	 */
	public void addInIta(Risorsa l) {
		if(libriIng.checkRisorsa(l))
			System.out.println("Risorsa gia' presente");
		else libriIta.addInSotto(l);
	}
	
	/**
	 * Aggiunge libro in categoria ing
	 * @param l libro da aggiungere
	 */
	public void addInIng(Risorsa l) {
		if(libriIta.checkRisorsa(l))
			System.out.println("Risorsa gia' presente");
		else libriIng.addInSotto(l);
	}
	
	
	/**
	 * Rimuove un libro da qualsiasi categoria
	 * @param cod codice del libro da rimuovere
	 */
	public void removeLibro(int cod) {
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty())
			System.out.println("L'Archivio e' vuoto \n");
		
		else {
			if(libriIng.esisteInSotto(cod))
				libriIng.removePerNome(cod);
			
			else if(libriIta.esisteInSotto(cod))
				
				libriIta.removePerNome(cod);
			
			else if(libriIng.esisteInSotto(cod)==false && libriIta.esisteInSotto(cod)==false)
				System.out.println("Siamo spiacenti, il libro non e' presente nell'archivio");
			
		}
	}
	
	
	
	
	/**
	 * Stampa libri in categoria ita
	 */
	public void stampaIta() {
		if(libriIta.getArrayRisorse().isEmpty())
			System.out.println("L'Archivio e' vuoto \n");
		else
			libriIta.stampaDesc();
	}

	/**
	 * Stampa libri in categoria ing
	 */
	public void stampaIng() {
		if(libriIng.getArrayRisorse().isEmpty())
			System.out.println("L'Archivio e' vuoto \n");
		else
			libriIng.stampaDesc();
	}
	

	/**
	 * Stampa tutti i libri 
	 */
	public void stampaTutto() {
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty())
			System.out.println("L'Archivio e' vuoto \n");
		else
			libri.stampaDesc();
	}
	
	
	
	
	
	
	
	
	
	

	//GETTERS & SETTERS
	
	
	
	
	
}
