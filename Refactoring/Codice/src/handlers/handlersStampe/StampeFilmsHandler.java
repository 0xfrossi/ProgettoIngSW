package handlers.handlersStampe;

import java.util.ArrayList;

import controller.FilmsController;
import interfaces.Risorsa;
import myUtil.MyMenu;
import myUtil.MyUtil;
import view.FilmsView;
import view.RisorseView;

/**
 * Classe che si occupa di gestire le ricerche di films e delle stampe
 * @author Francesco
 *
 */
public class StampeFilmsHandler {

	
	private FilmsController filmsController;
	
	
	/**
	 * Costruttore 
	 * @param filmsController il controller films
	 */
	public StampeFilmsHandler(FilmsController filmsController) {
		
		this.filmsController=filmsController;
		
	}
	
	/**
	 * metodo che si occupa della visualizzazione della ricerca per titolo di un film
	 * @param titolo il titolo del film
	 */
	public void stampaFilmsPerTitolo(String titolo) {
		try {
		ArrayList<Risorsa> lista= filmsController.getFilms().SelezionaArrayPerTitolo(titolo, filmsController.getFilms().getFilmsIng(), filmsController.getFilms().getFilmsIta(), 
																								  filmsController.getFilms().listaUniforme(filmsController.getFilms().getFilmsIng(),	 
																								  filmsController.getFilms().getFilmsIta(), filmsController.getFilms().getFilmsIng().getArrayRisorse(),
																								  filmsController.getFilms().getFilmsIta().getArrayRisorse()));
		
		if(lista.isEmpty()) 	
			RisorseView.RisorsaNonPresente();
		else {
			for(Risorsa r:lista) {
				RisorseView.StampaInfo(filmsController.getFilms().aggiungiNomeSottoCat(r, filmsController.getFilms().getFilmsIta(), filmsController.getFilms().getFilmsIng()));
				RisorseView.StampaInfo(r.toString());
			}	
		}	
		}catch(NullPointerException e) {
			RisorseView.RisorsaNonPresente();
		}
	}
	
	
	
	/**
	 * metodo che si occupa della visualizzazione della ricerca per attore di un film
	 * @param attore l'attore del film
	 */
	public void stampaFilmsPerAttore(String attore) {
	
		try {
			ArrayList<Risorsa> lista=filmsController.getFilms().cercaFilmPerAttore(attore);
		
			if(lista==null) 
			
				RisorseView.RisorsaNonPresente();
			else {
				for(Risorsa r:lista) 
					RisorseView.StampaInfo(r.toString());
			}
		}catch(NullPointerException e) {
			RisorseView.RisorsaNonPresente();
		}
	}	
	
	
	/**
	 * menu' che racchiude i tipi di ricerca films
	 */
	public void stampaRicercaFilms() {
		
		MyMenu menu= new MyMenu("Scegli come effettuare la ricerca:",FilmsView.SCEGLI_RICERCA );{
		boolean finito=false;
		do {
			int scegli= menu.scegli();
			switch(scegli) {
			
			case 0: //esci
				finito=true;
				break;
				
			case 1: 
				stampaFilmsPerTitolo(MyUtil.leggiStringaNonVuota(RisorseView.insTitolo()));
				break;	
			case 2:
				stampaFilmsPerAttore(MyUtil.leggiStringaNonVuota(FilmsView.insAttore()));
				break;
			}
			
		}while(!finito);
		}		
	}
	
	
	/**
	 * menu' che contiene i metodi di stampa di films in archivio
	 */
	public void menuStampaFilms() {
		
		MyMenu menu= new MyMenu("Scegli : \n",FilmsView.SCEGLI_STAMPA );{
		boolean finito=false;
		do {
			int scegli= menu.scegli();
			switch(scegli) {
			
			case 0: //esci
				finito=true;
				break;
				
			case 1: 
				stampaFilms();
				break;	
			case 2:
				stampaFilmsIta();
				break;
			case 3:
				stampaFilmsIng();
				break;
			}	
		}while(!finito);
		}		
	}
	
	
	
	/**
	 * Stampa tutti i films
	 */
	public void stampaFilms() {
		
		ArrayList<Risorsa> lista= filmsController.getFilms().listaUniforme(filmsController.getFilms().getFilmsIng(),	 
				  filmsController.getFilms().getFilmsIta(), filmsController.getFilms().getFilmsIng().getArrayRisorse(),
				  filmsController.getFilms().getFilmsIta().getArrayRisorse());
		if(!lista.isEmpty()) {
			for(Risorsa l: lista) 
				RisorseView.StampaInfo(l.toString());
			
		}else
			RisorseView.RisorseNonPresenti();
		
	}
	
	/**
	 * Stampa i films in lingua italiana (categoria)
	 */
	public void stampaFilmsIta() {
		
		if(!filmsController.getFilms().getFilmsIta().getArrayRisorse().isEmpty()) {
			for(Risorsa l: filmsController.getFilms().getFilmsIta().getArrayRisorse()) 
				RisorseView.StampaInfo(l.toString());
		}
		else	
			RisorseView.RisorseNonPresenti();
		
	}
	
	
	/**
	 * Stampa i films in lingua inglese (categoria)
	 */
	public void stampaFilmsIng() {
		
		if(!filmsController.getFilms().getFilmsIng().getArrayRisorse().isEmpty()) {
			for(Risorsa l: filmsController.getFilms().getFilmsIng().getArrayRisorse())
				RisorseView.StampaInfo(l.toString());
			
		}	else
				RisorseView.RisorseNonPresenti();
		}
	
	
	
}
