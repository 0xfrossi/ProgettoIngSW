package ing.unibs.it;

import java.io.Serializable;



public class Libri implements Serializable  {
	
	
	private static final long serialVersionUID = 1L;
	
	private Categoria libri;
	private Categoria libriIng;
	private Categoria libriIta;
	


	public Libri() {
		libri= new Categoria();
		libriIng= new Categoria(); 
		libriIta= new Categoria(); 
		libri.add(libriIng);
		libri.add(libriIta);
	}
	
	
	
	public void addInIta(Risorsa l) {
		if(libriIng.checkRisorsa(l))
			System.out.println("Risorsa gia' presente");
		else libriIta.addInSotto(l);
	}
	
	
	public void addInIng(Risorsa l) {
		if(libriIta.checkRisorsa(l))
			System.out.println("Risorsa gia' presente");
		else libriIng.addInSotto(l);
	}
	
	
	
	public void removeLibro(String titolo) {
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty())
			System.out.println("L'Archivio e' vuoto \n");
		
		else {
			if(libriIng.esisteInSotto(titolo))
				libriIng.removePerNome(titolo);
			
			else if(libriIta.esisteInSotto(titolo))
				
				libriIta.removePerNome(titolo);
			
			else if(libriIng.esisteInSotto(titolo)==false && libriIta.esisteInSotto(titolo)==false)
				System.out.println("Siamo spiacenti, il libro non e' presente nell'archivio");
			
		}
	}
	
	
	
	
	
	public void stampaIta() {
		if(libriIta.getArrayRisorse().isEmpty())
			System.out.println("L'Archivio e' vuoto \n");
		
		libriIta.stampaDesc();
	}
	
	public void stampaIng() {
		if(libriIng.getArrayRisorse().isEmpty())
			System.out.println("L'Archivio e' vuoto \n");
		
		libriIng.stampaDesc();
	}
	
	
	public void stampaTutto() {
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty())
			System.out.println("L'Archivio e' vuoto \n");
		
		libri.stampaDesc();
	}
	
	
	
	
	
	
	
	
	
	

	//GETTERS & SETTERS
	
	
	
	
	
}
