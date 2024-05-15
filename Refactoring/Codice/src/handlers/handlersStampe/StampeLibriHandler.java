package handlers.handlersStampe;

import java.util.ArrayList;

import controller.LibriController;
import interfaces.Risorsa;
import myUtil.MyMenu;
import myUtil.MyUtil;
import view.LibriView;
import view.RisorseView;

public class StampeLibriHandler {

	
	private LibriController libriController;
	
	
	/**
	 * Costruttore
	 * @param libriController il controller libri
	 */
	public StampeLibriHandler(LibriController libriController) {
		
		this.libriController=libriController;
		
	}
	
	
	/**
	 * Cerca un libro per titolo e stampa il risultato
	 * @param titolo titolo libro cercato
	 */
	public void stampaLibriPerTitolo(String titolo) {
		try {
		ArrayList<Risorsa> lista= libriController.getLibriM().SelezionaArrayPerTitolo(titolo, libriController.getLibriM().getLibriIng(), libriController.getLibriM().getLibriIta(), 
																		 libriController.getLibriM().listaUniforme(libriController.getLibriM().getLibriIng(),
																		 libriController.getLibriM().getLibriIta(), libriController.getLibriM().getLibriIng().getArrayRisorse(),
																		 libriController.getLibriM().getLibriIta().getArrayRisorse()));		
		if(lista==null) 
			RisorseView.RisorsaNonPresente();
		else {
			for(Risorsa r:lista) {
				RisorseView.StampaInfo(libriController.getLibriM().aggiungiNomeSottoCat(r, libriController.getLibriM().getLibriIta(), libriController.getLibriM().getLibriIng()));
				RisorseView.StampaInfo(r.toString());
			}	
		}	
		}catch(NullPointerException e) {
			RisorseView.RisorsaNonPresente();
		}
	}
	
	
	/**
	 * Cerca un libro per autore e stampa il risultato
	 * @param autore autore del libro cercato
	 */
	public void stampaLibriPerAutore(String autore) {
		
		try {
			ArrayList<Risorsa> lista=libriController.getLibriM().cercaLibroPerAutore(autore);
		
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
	 * menu che contiene i tipi di ricerca sui libri	
	 */
	public void menuRicercaLibri() {
		
		MyMenu menu= new MyMenu("Scegli come effettuare la ricerca: \n",LibriView.SCEGLI_RICERCA );
		boolean finito=false;
		do {
			int scegli= menu.scegli();
			switch(scegli) {
			
			case 0: //esci
				finito=true;
				break;
				
			case 1: 
				stampaLibriPerTitolo(MyUtil.leggiStringaNonVuota(RisorseView.insTitolo()));
				break;	
			case 2:
				stampaLibriPerAutore(MyUtil.leggiStringaNonVuota(LibriView.insAutori()));
				break;
			
			}
		}while(!finito);
		
	}
	
	/**
	 * menu che contiene come visualizzare tutti i libri in archivio
	 */
	public void menuStampaLibri() {
		
		MyMenu menu= new MyMenu("Scegli : \n",LibriView.SCEGLI_STAMPA );
		boolean finito=false;
		do {
			int scegli= menu.scegli();
			switch(scegli) {
			
			case 0: //esci
				finito=true;
				break;
				
			case 1: 
				stampaLibri();
				break;	
			case 2:
				stampaLibriIta();
				break;
			case 3:
				stampaLibriIng();
				break;
			
			}
		}while(!finito);
		
		
	}
	
	/**
	 * Stamapa tutti i libri
	 */
	public void stampaLibri() {
		
		 ArrayList<Risorsa> lista=libriController.getLibriM().listaUniforme(libriController.getLibriM().getLibriIng(),
				 				  libriController.getLibriM().getLibriIta(), libriController.getLibriM().getLibriIng().getArrayRisorse(),
				 				  libriController.getLibriM().getLibriIta().getArrayRisorse());
		 
		if(!lista.isEmpty()) {
			for(int i=0; i< lista.size(); i++) 
				stampaLibro(lista.get(i));
		}
			else
				RisorseView.RisorseNonPresenti();
		
	}
	
	
	/**
	 * Stampa solo libri in ita
	 */
	public void stampaLibriIta() {
		
		if(!libriController.getLibriM().getLibriIta().getArrayRisorse().isEmpty()) {
			for(Risorsa l: libriController.getLibriM().getLibriIta().getArrayRisorse()) 
				stampaLibro(l);
		}
			
		else
		 RisorseView.RisorseNonPresenti();
		
	}
	
	
	/**
	 *Stampa solo libri in ing
	 */
	public void stampaLibriIng() {
		
		if(!libriController.getLibriM().getLibriIng().getArrayRisorse().isEmpty()) {
			for(Risorsa l: libriController.getLibriM().getLibriIng().getArrayRisorse()) 
				stampaLibro(l);
		}	
		else
			 RisorseView.RisorseNonPresenti();
			
	}
	
	
	/**
	 * stampa libro
	 * @param l libro da stampare
	 */
	public void stampaLibro(Risorsa l) {
		
		RisorseView.StampaInfo(l.toString());
	}
	
	
	
	
	
	
}
