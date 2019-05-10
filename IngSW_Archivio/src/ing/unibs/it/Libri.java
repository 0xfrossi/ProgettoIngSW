package ing.unibs.it;

import java.io.Serializable;
import java.util.ArrayList;



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
	
	
	
	public void removeLibro(int codice) {
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty())
			System.out.println("L'Archivio e' vuoto \n");
		
		else {
			if(libriIng.esisteInSotto(codice))
				libriIng.removePerNome(codice);
			
			else if(libriIta.esisteInSotto(codice))
				
				libriIta.removePerNome(codice);
			
			else if(libriIng.esisteInSotto(codice)==false && libriIta.esisteInSotto(codice)==false)
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
	
	/*public ArrayList<Risorsa> creaArrayUniforme(){
		
		ArrayList<Risorsa> tutti=new ArrayList<>();
		for(int i=0; i<libri.getArrayRisorse().size();i++) 
			tutti.add(libri.getArrayRisorse().get(i));
		return tutti;
	}
	*/
	
	public void cercaLibroPerNome(String titolo) {
		
		boolean trovato=false;
		ArrayList<Risorsa> tutti= libri.arrayUniforme();
		
		for(Risorsa risorsa : tutti) {
			if(risorsa.getNome().toLowerCase().contains(titolo.toLowerCase()) || risorsa.getNome().toLowerCase().equals(titolo.toLowerCase())) {
					trovato=true;
					risorsa.stampaDesc();
			}
		}
		if(trovato==false)
			System.out.print("nessun libro trovato");
		
	}
	
public void cercaLibroPerAutore(String autore) {
		
		boolean trovato=false;
		ArrayList<Risorsa> tutti= libri.arrayUniforme();
		
		for(Risorsa risorsa : tutti) {
			for(String autor: risorsa.getAutori()) {
				if(autor.toLowerCase().equals(autore.toLowerCase()) || autor.toLowerCase().equals(autore.toLowerCase())) {
					trovato=true;
					risorsa.stampaDesc();
				}
			}	
		}
		if(trovato==false)
			System.out.print("nessun libro trovato");
		
	}
	
	
	
	
	
	
	

	//GETTERS & SETTERS
	
	
	
	
	
}
