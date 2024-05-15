package controller;

import java.util.ArrayList;
import customExceptions.RisorsaNotFoundException;
import interfaces.Risorsa;
import model.FilmModel;
import model.FilmsModel;
import myUtil.MyUtil;
import view.FilmsView;
import view.RisorseView;


/**
 * Controller classe fruitori
 * @author Francesco
 *
 */
public class FilmsController {

	private FilmsModel films;
	
	/**
	 * Costruttore
	 * @param films il model di Films
	 */
	public FilmsController(FilmsModel films) {
		
		this.films=films;
		
	}
	
	
	/**
	 * crea un oggetto film di tipo risorsa
	 * @return il film creato
	 */
	public Risorsa creaFilm() {
		
		Risorsa film= new FilmModel(MyUtil.leggiStringaNonVuota(RisorseView.insTitolo()), MyUtil.leggiData(FilmsView.insUscita()),
									MyUtil.leggiStringaNonVuota(FilmsView.insRegista()), MyUtil.inserisciAttori(), 
									MyUtil.leggiInteroConMinimo(RisorseView.insNumLicenze(), 1), MyUtil.leggiInteroConMinimo(RisorseView.insCodice(), 1), 
									MyUtil.leggiStringaNonVuota(RisorseView.insGenere()));
		
		return film;
	}
	
	
	/**
	 * inserisci un film in una sottoCategoria
	 * @param l la risorsa da inserire
	 */
	public void inserisciFilmInSotto(Risorsa l) {
		
		if(films.getFilmsIng().checkRisorsa(l)||films.getFilmsIta().checkRisorsa(l)) 
			
			RisorseView.RisorsaPresente();
		
		else {
			
			RisorseView.insInSotto();
			int scelta= MyUtil.leggiIntero("scegli", 1, 2);
		
			if(scelta==1) {
				films.getFilmsIta().add(l);
				RisorseView.StampaAggiunta();
				}
			else {
				films.getFilmsIng().add(l);	
				RisorseView.StampaAggiunta();
				}
		}
	}
	
	
	/**
	 * Rimuovi un film da archivio dato il codice e mostra il risultato dell'operazione
	 * @param codice il codice da rimuovere
	 */
	public void rimuoviFilm(int codice) {
		
		try {				
				films.removeRisorsa(codice, films.getFilmsIng(), films.getFilmsIta());
				FilmsView.filmRimosso();
				
			}catch(RisorsaNotFoundException e) {
				
				RisorseView.StampaInfo(e.getMessage());
				
			}catch (NullPointerException e) {
				RisorseView.RisorsaNonPresente();
			}
		}
	
	/**
	 * Seleziona un film da una lista
	 * @return Risorsa il film selezionato
	 * @throws NullPointerException se non c'e' selezionato
	 */
	public Risorsa selezionaFilm() throws NullPointerException{
		
			ArrayList<Risorsa> lista=films.SelezionaArrayPerTitolo(MyUtil.leggiStringaNonVuota(RisorseView.insTitolo()), films.getFilmsIta(),
																				films.getFilmsIng(),getFilms().listaUniforme(films.getFilmsIng(),
																				films.getFilmsIta(), films.getFilmsIng().arrayUniforme(),
																				films.getFilmsIta().arrayUniforme()));
			
			if(lista==null) {
				RisorseView.RisorsaNonPresente();
				return null;
			}			
			else {
				int seleziona;
				for(int i = 0; i < lista.size(); i++){
					RisorseView.StampaInfo("\n" + (i+1) + ".");
					RisorseView.StampaInfo(films.aggiungiNomeSottoCat(lista.get(i), films.getFilmsIta(), films.getFilmsIng()));
					RisorseView.StampaInfo(lista.get(i).toString());
					System.out.println();
				}
				
				seleziona=MyUtil.leggiIntero(RisorseView.insSele(), 1, lista.size());
				return lista.get(seleziona-1);
				
			}
		}
	
	

	
		
	public FilmsModel getFilms() {
		return films;
	}

	

	
}
