package controller;

import handlers.handlersStampe.StampeFilmsHandler;
import handlers.handlersStampe.StampeLibriHandler;
import interfaces.Risorsa;
import model.FilmsModel;
import model.LibriModel;
import myUtil.MyMenu;
import myUtil.MyUtil;
import view.RisorseView;
import view.SystemView;

/**
 * Controller che gestisce un oggetto di tipo Risosa generico (libro o film)
 * @author Francesco
 *
 */
public class RisorseController {
	
	private FilmsController fCont;
	private LibriController lCont;
	private StampeLibriHandler ricercheL;
	private StampeFilmsHandler ricercheF;
	
	/**
	 * Costruttore, inizializza StampeLibriHandler,StampeFilmsHandler
	 * @param films model
	 * @param libri model
	 */
	public RisorseController(FilmsModel films, LibriModel libri) {
		
		this.fCont= new FilmsController(films);
		this.lCont= new LibriController(libri);
		this.ricercheL=new StampeLibriHandler(lCont);
		this.ricercheF=new StampeFilmsHandler(fCont);
	}
	
	
	
	
	
	/**
	 * menu' che consente la scelta sul tipo di risorsa da selezionare
	 * @return risorsa selezionata
	 */
	public Risorsa selezionaRisorsa() {
		
		MyMenu menu= new MyMenu(SystemView.sceltaRisorsa(), SystemView.SCEGLI);
		boolean finito=false;
		do {
			int scegli= menu.scegli();
			switch(scegli) {
			/*
			case 0: //esci
				finito=true;
				break;
			*/	
			case 1: 
				return lCont.selezionaLibro();
				
			case 2:
				return fCont.selezionaFilm();
				
			default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
				SystemView.comandoNnriconosciuto();
				break;
			}
			
		}while(!finito);
		return null;
	}

		/**
		 * menu' che contiene la scelta di visualizzazione o ricerca di risorse
		 */
		public void menuVisualizzaRisorse() {
				
			MyMenu menu= new MyMenu(SystemView.scegli(), SystemView.SCEGLI_OPZIONE );
			boolean finito=false;
			do {
				int scegli= menu.scegli();
				switch(scegli) {
				
				case 0: //esci
					finito=true;
					break;
					
				case 1: 
					ricercheL.menuStampaLibri();
					break;
					
				case 2:
					ricercheL.menuRicercaLibri();
					break;
					
				case 3:
					ricercheF.menuStampaFilms();
					break;
					
				case 4:
					ricercheF.stampaRicercaFilms();
					break;	
					
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					SystemView.comandoNnriconosciuto();
					break;
				}
				
			}while(!finito);
				
		}
	
		/**
		 * agginugi o rimuovi risorse
		 */
		public void manageRisorse() {
			
			MyMenu menu= new MyMenu(SystemView.scegli(), SystemView.OPERAZIONI );
			boolean finito=false;
			do {
				int scegli= menu.scegli();
				switch(scegli) {
				
				case 0: //esci
					finito=true;
					break;
					
				case 1: 
					aggiungiRisorsa();
					break;	
					
				case 2:
					rimuoviRisorsa();
					break;
					
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					SystemView.comandoNnriconosciuto();
					break;
				}
				
			}while(!finito);
			
		}
	
		/**
		 * Aggiungi una risosa in archivio (films o libri)
		 */
		public void aggiungiRisorsa() {
			
			MyMenu menu= new MyMenu(SystemView.scegli(), SystemView.ADD_RISORSA );
			boolean finito=false;
			
			do {
				int scegli= menu.scegli();
				switch(scegli) {
				
				case 0: //esci
					finito=true;
					break;
					
				case 1: 
					lCont.inserisciLibroInSotto(lCont.creaLibro());
					break;	
					
				case 2:
					fCont.inserisciFilmInSotto(fCont.creaFilm());
					break;
					
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					SystemView.comandoNnriconosciuto();
					break;
				}
				
			}while(!finito);
			
		}
		
		/**
		 * Rimuovi una risosa in archivio (films o libri)
		 */
		public void rimuoviRisorsa() {
			
			MyMenu menu= new MyMenu(SystemView.scegli(), SystemView.REM_RISORSA );
			boolean finito=false;
			
			do {	
				int scegli= menu.scegli();
				switch(scegli) {
				
				case 0: //esci
					finito=true;
					break;	
				case 1: 
					lCont.rimuoviLibro(MyUtil.leggiIntero(RisorseView.rimuoviLibro()));;
					break;	
					
				case 2:
					fCont.rimuoviFilm(MyUtil.leggiIntero(RisorseView.rimuoviFilm()));
					break;
					
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					SystemView.comandoNnriconosciuto();
					break;
				}
				
			}while(!finito);
			
		}



	public FilmsController getfCont() {
		return fCont;
	}

	public LibriController getlCont() {
		return lCont;
	}

	public StampeLibriHandler getRicercheL() {
		return ricercheL;
	}

	public StampeFilmsHandler getRicercheF() {
		return ricercheF;
	}
		
	
		
}
