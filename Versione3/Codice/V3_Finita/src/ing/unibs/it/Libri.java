package ing.unibs.it;

import java.io.Serializable;
import java.util.ArrayList;
import util.Unibs.MyMenu;
import util.Unibs.MyUtil;



public class Libri implements Serializable  {
	
	//Attributi	
	private static final long serialVersionUID = 1L;
	
	private Categoria libri;
	private Categoria libriIng;
	private Categoria libriIta;
	

	/**
	 * Costruttore della classe crea la categoria e le sotto-categorie
	 * e le aggiunge nella categoria
	 */
	public Libri() {
		libri= new Categoria();
		libriIng= new Categoria(); 
		libriIta= new Categoria(); 
		libri.add(libriIng);
		libri.add(libriIta);
	}
	
	/**
	 * Aggiungo un libro nella sotto-categiria italiana
	 * @param l il libro da aggiungere
	 */
	public void addInIta(Risorsa l) {
		if(libriIng.checkRisorsa(l))
			System.out.println("Risorsa gia' presente");

		else libriIta.addInSotto(l);
	}
	
	/**
	 * Aggiungo un libro nella sotto-categiria inglese
	 * @param l il libro da aggiungere
	 */
	public void addInIng(Risorsa l) {
		if(libriIta.checkRisorsa(l))
			System.out.println(Costanti.RISORSA_PRESENTE);
		else libriIng.addInSotto(l);
	}
	
	
	/**
	 * Rimuove un libro nella lista atraverso il codice univoco
	 * @param codice il codice del libro da rimuovere
	 */
	public void removeLibro(int codice) {
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty())
			System.out.println(Costanti.ARCHIVIO_VUOTO);
		
		else {
			if(libriIng.esisteInSotto(codice))
				libriIng.removePerNome(codice);
			
			else if(libriIta.esisteInSotto(codice))
				
				libriIta.removePerNome(codice);
			
			else if(libriIng.esisteInSotto(codice)==false && libriIta.esisteInSotto(codice)==false)
				System.out.println(Costanti.LIBRO_NON_PRESENTE);
			
		}
	}
	
	
	/**
	 * Visualizza tutti i libri nella sottoCategotria italiana
	 */
	public void stampaIta() {
		if(libriIta.getArrayRisorse().isEmpty())
			System.out.println(Costanti.ARCHIVIO_VUOTO);
		
		libriIta.stampaDesc();
	}
	
	
	/**
	 * Visualizza tutti i libri nella sottoCategotria inglese
	 */
	public void stampaIng() {
		if(libriIng.getArrayRisorse().isEmpty())
			System.out.println(Costanti.ARCHIVIO_VUOTO);
		
		libriIng.stampaDesc();
	}
	
	/**
	 * Visualizza tutti i libri 
	 */
	public void stampaTutto() {
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty())
			System.out.println(Costanti.ARCHIVIO_VUOTO);
			
		libri.stampaDesc();
	}
	
	
	
	
	/**
	 * metodo che contiene la scelta per effettuare la ricerca di libri
	 ** cercaLibroPerNome()
	 * *cercaLibroPerAutore()
	 */
	public void cercaLibro() {
		MyMenu  menuCerca= new MyMenu(Costanti.TITOLO_OP, Costanti.SCELTE_RICERCA_LIBRI); 
		
		boolean finito=false;

		do{
			int scelta=menuCerca.scegli();
			switch(scelta){
			
			case 0: 
				
				finito=true;
				break;
			case 1:
				
				cercaLibroPerNome(MyUtil.leggiStringaNonVuota(Costanti.CERCA_PER_TITOLO));					
				break;
			case 2: 
				
				cercaLibroPerAutore(MyUtil.leggiStringaNonVuota(Costanti.INS_AUTORE));;
				break;
			}
			}while(!finito );
	}
	
	/**
	 * verifica a che categoria appartiene una risorsa e aggiunge la "lingua" del libro
	 * @param cercata la risorsa a cui interessa l'appartenenza
	 */
	private void aggiungiNomeSottoCat(Risorsa cercata) {
		
	for(Risorsa risorsa: libriIta.getArrayRisorse()) {
		if(risorsa.getCodiceUnivoco()==cercata.getCodiceUnivoco())
			System.out.println(Costanti.LINGUA_ITA);
	} 
	for(Risorsa risorsa: libriIng.getArrayRisorse()) {
		if(risorsa.getCodiceUnivoco()==cercata.getCodiceUnivoco())
			System.out.println(Costanti.LINGUA_ING);
	}
	
	}
	
	/**
	 * ricomponi libri in un unica lista
	 * @return array uniforme
	 */
	public ArrayList<Risorsa> libriUniforme(){
		ArrayList<Risorsa> tutti= new ArrayList<Risorsa>();
		for(Risorsa risorsa: libriIng.getArrayRisorse()) 
			tutti.add(risorsa);
		for(Risorsa risorsa: libriIta.getArrayRisorse()) 
			tutti.add(risorsa);
			
		return tutti;
	}
	
	/**
	 * Effettua una ricerca di un libro tramite titolo o parte di esso
	 * @param titolo il titolo da ricercare
	 */
	private void cercaLibroPerNome(String titolo) {
		
		boolean trovato=false;
		ArrayList<Risorsa> tutti= libriUniforme();
		
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty())
			System.out.println(Costanti.ARCHIVIO_VUOTO);
		
		else {
			for(Risorsa risorsa : tutti) {
				if(risorsa.getNome().toLowerCase().contains(titolo.toLowerCase()) || risorsa.getNome().toLowerCase().equals(titolo.toLowerCase())) {
						trovato=true;
						aggiungiNomeSottoCat(risorsa);
						risorsa.stampaDesc();
				}
			}
			if(trovato==false)
				System.out.println(Costanti.LIBRO_NON_TROVATO);
			}
	}
	
	
	/**
	 * Effettua una ricerca di un libro attraverso l'autore
	 * @param autore l'autore di cui voglio i libri/o
	 */
	private void cercaLibroPerAutore(String autore) {
		
		boolean trovato=false;
		ArrayList<Risorsa> tutti= libriUniforme();
		
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty())
			System.out.println(Costanti.ARCHIVIO_VUOTO);
		
		else {
			
			for(Risorsa risorsa : tutti) {
				for(String autor: risorsa.getAutori()) {
					if(autor.toLowerCase().equals(autore.toLowerCase()) || autor.toLowerCase().contains(autore.toLowerCase())) {
						trovato=true;
						aggiungiNomeSottoCat(risorsa);
						risorsa.stampaDesc();
					}
				}	
			}
		if(trovato==false)
			System.out.println(Costanti.LIBRO_NON_TROVATO);
		}
	}
	
	/**
	 * Seleziono un libro(type risorsa) tramite la ricerca per titolo,per chiderne il prestito
	 * @param titolo il tittolo del libro da selezionare
	 * @return la risorsa (Libro) selezionata
	 */
	public Risorsa scegliPerNome(String titolo) {
		int seleziona;
		ArrayList<Risorsa> tutti= libriUniforme();
		ArrayList<Risorsa> selezionati=new ArrayList<Risorsa>();
		if(libriIng.getArrayRisorse().isEmpty()&& libriIta.getArrayRisorse().isEmpty()) {
			System.out.println(Costanti.ARCHIVIO_VUOTO);
			return null;
		}
			
		for(Risorsa risorsa : tutti) {
			if(risorsa.getNome().toLowerCase().contains(titolo.toLowerCase()) || risorsa.getNome().toLowerCase().equals(titolo.toLowerCase())) 
					selezionati.add(risorsa);
			
		}
		if(selezionati.isEmpty()) {
			System.out.println(Costanti.LIBRO_NON_TROVATO);
			return null;
		}
		else {
			for(int i = 0; i < selezionati.size(); i++){
				System.out.println("\n" + (i+1) + ".");
				aggiungiNomeSottoCat(selezionati.get(i));
				selezionati.get(i).stampaDesc();
				System.out.println();
			}

			
			seleziona=MyUtil.leggiIntero(Costanti.SELEZIONA_LIBRO_PRESTITO, 1, selezionati.size());
			return selezionati.get(seleziona-1);
		}
	}

	

	public Categoria getLibri() {
		return libri;
	}

	public Categoria getLibriIng() {
		return libriIng;
	}

	public Categoria getLibriIta() {
		return libriIta;
	}
	
}
