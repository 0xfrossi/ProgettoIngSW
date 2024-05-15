package controller;

import java.util.ArrayList;

import customExceptions.RisorsaNotFoundException;
import interfaces.Risorsa;
import model.LibriModel;
import model.LibroModel;
import myUtil.MyUtil;
import view.LibriView;
import view.RisorseView;

/**
 * Controller della classe libri
 * @author Francesco
 *
 */
public class LibriController {

	private LibriModel libriM;
	
	/**
	 * Costruttore
	 * @param libri il model
	 */
	public LibriController(LibriModel libri) {
		
		this.libriM=libri;
		
		
	}
	
	
	/**
	 * Crea un libro di tipo Risorsa
	 * @return il libro creato
	 */
	public Risorsa creaLibro() {
		
		Risorsa libro= new LibroModel(MyUtil.leggiStringaNonVuota(RisorseView.insTitolo()), MyUtil.leggiInteroConMinimo(RisorseView.insCodice(), 1),
										 MyUtil.leggiInteroConMinimo(RisorseView.insNumLicenze(), 1), MyUtil.inserisciAutori(),
										 MyUtil.leggiInteroConMinimo(LibriView.insNumPag(), 10), 
										 MyUtil.leggiStringaNonVuota(LibriView.insCasaEd()),
										 MyUtil.leggiStringaNonVuota(RisorseView.insGenere()), MyUtil.leggiData(LibriView.insAnnoPub()));
		
		return libro;
		
	}
	
	/**
	 * inserisci un libro in una sottoCategoria prestabilita
	 * @param l libro da inserire
	 */
	public void inserisciLibroInSotto(Risorsa l) {
		
		if(libriM.getLibriIng().checkRisorsa(l) || libriM.getLibriIta().checkRisorsa(l))
			RisorseView.RisorsaPresente();
		
		else{
			RisorseView.insInSotto();
			int scelta= MyUtil.leggiIntero("scegli", 1, 2);
		
			if(scelta==1) { 
				libriM.getLibriIta().add(l);
				RisorseView.StampaAggiunta();
			}
			else {
				libriM.getLibriIng().add(l);
				RisorseView.StampaAggiunta();
			}
		}
	}
	
	
	/**
	 * Seleziona un libro attraverso la ricerca per titolo
	 * @return il libro selezionato
	 */
	public Risorsa selezionaLibro() {
		
		ArrayList<Risorsa> lista=libriM.SelezionaArrayPerTitolo(MyUtil.leggiStringaNonVuota(RisorseView.insTitolo()), libriM.getLibriIta(),
													 libriM.getLibriIng(),getLibriM().listaUniforme(libriM.getLibriIng(),
													 libriM.getLibriIta(), libriM.getLibriIng().arrayUniforme(),
													 libriM.getLibriIta().arrayUniforme()));
		
		if(lista==null) {
			RisorseView.RisorsaNonPresente();
			return null;
		}
		
		else {
			int seleziona;
			for(int i = 0; i < lista.size(); i++){
				RisorseView.StampaInfo("\n" + (i+1) + ".");
				RisorseView.StampaInfo(libriM.aggiungiNomeSottoCat(lista.get(i), libriM.getLibriIta(), libriM.getLibriIng()));
				RisorseView.StampaInfo(lista.get(i).toString());
				System.out.println();
			}
			
			seleziona=MyUtil.leggiIntero(RisorseView.insSele(), 1, lista.size());
			return lista.get(seleziona-1);
			
		}
	}
	
	/**
	 * Rimuovi un libro dall'archivio tramite codice
	 * @param codice codice libro da rimuovere
	 */
	public void rimuoviLibro(int codice) {
		
	try {
			
			libriM.removeRisorsa(codice, libriM.getLibriIng(), libriM.getLibriIta());
			LibriView.libroRimosso();
			
		}catch(RisorsaNotFoundException e) {
			
			RisorseView.StampaInfo(e.getMessage());
	
		}
		
	}
	


	public LibriModel getLibriM() {
		return libriM;
	}

	
	
	
}
