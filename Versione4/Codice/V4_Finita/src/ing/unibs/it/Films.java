package ing.unibs.it;

import java.io.Serializable;
import java.util.ArrayList;

import util.Unibs.MyMenu;
import util.Unibs.MyUtil;

public class Films  implements Serializable{

	//Attributi
	private static final long serialVersionUID = 1L;
	private Categoria films;
	private Categoria filmsIng;
	private Categoria filmsIta;
	
	
	/**
	 * Costruttore che inizializza le categorie films
	 */
	public Films() {
		films= new Categoria();
		filmsIng= new Categoria();
		filmsIta= new Categoria();
		films.add(filmsIng);
		films.add(filmsIta);
	}
	
	/**
	 * Aggiungo un libro nella sotto-categiria italiana
	 * @param l il libro da aggiungere
	 */
	public void addInIta(Risorsa l) {
		if(filmsIng.checkRisorsa(l))
			System.out.println(Costanti.RISORSA_PRESENTE);

		else filmsIta.addInSotto(l);
	}
	
	/**
	 * Aggiungo un libro nella sotto-categiria inglese
	 * @param l il libro da aggiungere
	 */
	public void addInIng(Risorsa l) {
		if(filmsIta.checkRisorsa(l))
			System.out.println(Costanti.RISORSA_PRESENTE);
		else filmsIng.addInSotto(l);
	}
	
	
	/**
	 * Rimuove un film nella lista atraverso il codice univoco
	 * @param codice il codice del libro da rimuovere
	 */
	public void removeFilm(int codice) {
		
		if(filmsIng.getArrayRisorse().isEmpty()&& filmsIta.getArrayRisorse().isEmpty())
			System.out.println(Costanti.ARCHIVIO_VUOTO);
		
		else {
			if(filmsIng.esisteInSotto(codice))
				filmsIng.removePerNome(codice);
			
			else if(filmsIta.esisteInSotto(codice))
				filmsIta.removePerNome(codice);
			
			else if(filmsIng.esisteInSotto(codice)==false && filmsIta.esisteInSotto(codice)==false)
				System.out.println(Costanti.FILM_NON_PRESENTE);
			
		}
		
	}
		/**
		 * Visualizza tutti i films nella sottoCategotria italiana
		 */
		private void stampaItaFilms() {
			if(filmsIta.getArrayRisorse().isEmpty())
				System.out.println(Costanti.ARCHIVIO_VUOTO);
			else
				filmsIta.stampaDesc();
		}
		
		
		/**
		 * Visualizza tutti i films nella sottoCategotria inglese
		 */
		private void stampaIngFilms() {
			if(filmsIng.getArrayRisorse().isEmpty())
				System.out.println(Costanti.ARCHIVIO_VUOTO);
			else
			filmsIng.stampaDesc();
		}
		
		/**
		 * Visualizza tutti i films
		 */
		private void stampaTuttoFilms() {
			if(filmsIng.getArrayRisorse().isEmpty()&& filmsIta.getArrayRisorse().isEmpty())
				System.out.println(Costanti.ARCHIVIO_VUOTO);
			else	
				films.stampaDesc();
		}
		
		/**
		 * Scegli cosa visualizzare in base alla sotto-categoria
		 */
		public void stampaFilms() {
			
			MyMenu  menuStampa= new MyMenu(Costanti.SCEGLI_STAMPA, Costanti.SCELTE_STAMPA_FILMS); 
			boolean finito=false;

			do{
				int scelta=menuStampa.scegli();
				switch(scelta){
				
				case 0: 
					finito=true;
					break;
				case 1:	//stampa ita
					
					stampaItaFilms();
						
					break;
				case 2: //stampa ing
					stampaIngFilms();
					break;
					
				case 3://stampa tutto
					stampaTuttoFilms();
			
				}
			}while(!finito);
			
		}
		/**
		 * Menu' di ricerca	
		 */
		public void cercaFilm() {
			MyMenu  menuCerca= new MyMenu(Costanti.TITOLO_OP, Costanti.SCELTE_RICERCA_FILMS); 
			
			boolean finito=false;

			do{
				int scelta=menuCerca.scegli();
				switch(scelta){
				
				case 0: 
					finito=true;
					break;
				case 1:
					cercaFilmPerTitolo(MyUtil.leggiStringaNonVuota(Costanti.CERCA_PER_TITOLO));					
					break;
				case 2: 
					cercaFilmPerAttore(MyUtil.leggiStringaNonVuota(Costanti.INS_ATTORE));;
					break;
				}
			}while(!finito );
		}
		
		/**
		 * Uniforma i films in un unico vettore
		 * @return il vettore
		 */
		public ArrayList<Risorsa> filmsUniforme(){
			ArrayList<Risorsa> tutti= new ArrayList<Risorsa>();
			for(Risorsa risorsa: filmsIng.getArrayRisorse()) 
				tutti.add(risorsa);
			for(Risorsa risorsa: filmsIta.getArrayRisorse()) 
				tutti.add(risorsa);
				
			return tutti;
		}
		
		
		/**
		 * Cerca un film in base al titolo
		 * @param titolo il titolo del film da cercare
		 */
		private void cercaFilmPerTitolo(String titolo) {
			boolean trovato=false;
			ArrayList<Risorsa> tutti= filmsUniforme();
			
			for(Risorsa risorsa : tutti) {
				if(risorsa.getNome().toLowerCase().contains(titolo.toLowerCase()) || risorsa.getNome().toLowerCase().equals(titolo.toLowerCase())) {
						trovato=true;
						aggiungiNomeSottoCat(risorsa);
						risorsa.stampaDesc();
				}
			}
			if(trovato==false)
				System.out.println(Costanti.FILM_NON_TROVATO);
		}
		
		/**
		 * Effettua una ricerca di un film attraverso l'attore che recita in esso
		 * @param attore l'attore di cui cerco i films
		 */
		private void cercaFilmPerAttore(String attore) {
			
			boolean trovato=false;
			ArrayList<Risorsa> tutti= filmsUniforme();
			if(filmsIng.getArrayRisorse().isEmpty()&& filmsIta.getArrayRisorse().isEmpty())
				System.out.println(Costanti.ARCHIVIO_VUOTO);
			
			else {
				for(Risorsa risorsa : tutti) {
					for(String attor: risorsa.getAttori()) {
						if(attor.toLowerCase().equals(attore.toLowerCase()) || attor.toLowerCase().contains(attore.toLowerCase())) {
							trovato=true;
							aggiungiNomeSottoCat(risorsa);
							risorsa.stampaDesc();
						}
					}	
				}
				if(trovato==false)
					System.out.println(Costanti.FILM_NON_TROVATO);
			}	
		}
		
		/**
		* verifica a che categoria appartiene una risorsa e aggiunge la "lingua" del film
		* @param cercata la risorsa a cui interessa l'appartenenza
		*/
		private void aggiungiNomeSottoCat(Risorsa cercata) {
			
			int i=0;
			for(Risorsa risorsa: filmsIta.getArrayRisorse()) {
				if(risorsa.getCodiceUnivoco()==cercata.getCodiceUnivoco())
					i=1;
					//System.out.println(Costanti.LINGUA_ITA);
			} 
			for(Risorsa risorsa: filmsIng.getArrayRisorse()) {
				if(risorsa.getCodiceUnivoco()==cercata.getCodiceUnivoco())
					i=2;
					//System.out.println(Costanti.LINGUA_ING);
			}
			if(i==1)
				System.out.println(Costanti.LINGUA_ITA);
			else if(i==2)
				System.out.println(Costanti.LINGUA_ING);
			else if(i==0)
				System.out.println("errore");
			
		}
			
		/**
		 * Seleziono un film(type risorsa) tramite la ricerca per titolo,per chiderne il prestito
		 * @param titolo il tittolo del film da selezionare
		 * @return la risorsa (Film) selezionata
		 */
		public Risorsa scegliPerNome(String titolo) {
			int seleziona;
			ArrayList<Risorsa> tutti= filmsUniforme();
			ArrayList<Risorsa> selezionati=new ArrayList<Risorsa>();
			
			for(Risorsa risorsa : tutti) {
				if(risorsa.getNome().toLowerCase().contains(titolo.toLowerCase()) || risorsa.getNome().toLowerCase().equals(titolo.toLowerCase())) 
						selezionati.add(risorsa);
				
			}
			if(selezionati.isEmpty()) {
				System.out.println(Costanti.FILM_NON_TROVATO);
				return null;
			}
			else {
				for(int i = 0; i < selezionati.size(); i++){
					System.out.println("\n" + (i+1) + ".");
					aggiungiNomeSottoCat(selezionati.get(i));
					selezionati.get(i).stampaDesc();
					System.out.println();
				}
	
				seleziona=MyUtil.leggiIntero(Costanti.SELEZIONA_FILM_PRESTITO, 1, selezionati.size());
				return selezionati.get(seleziona-1);
				
			}	
		}

		
		
		//Getters
		public Categoria getFilms() {
			return films;
		}

		public Categoria getFilmsIng() {
			return filmsIng;
		}

		public Categoria getFilmsIta() {
			return filmsIta;
		}

		
	}
	
	
	

