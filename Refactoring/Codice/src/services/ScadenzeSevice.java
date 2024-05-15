package services;

import java.util.ArrayList;

import controller.FruitoriController;
import controller.PrestitiController;
import model.FruitoreModel;

/**
 * Classe con metodi chiamati in fase di avvio che contollano le scadeze di iscrizioni e prestiti
 * @author Francesco
 *
 */
public class ScadenzeSevice {

	private FruitoriController fC;
	private PrestitiController pC;
	private SavesManager saves;
	
	/**
	 * Costuttore
	 * @param fC contoller fuitori
	 * @param pC contoller prestiti
	 * @param saves manager salvataggi
	 */
	public ScadenzeSevice(FruitoriController fC,PrestitiController pC,SavesManager saves) {
		
		this.fC=fC;
		this.pC=pC;
		this.saves=saves;
	}
	
			
	/**
	 * rimuove i fruitori scaduti dalla lista e in caso anche i perstiti associati		
	 */
	public void servizioRimozione() {	
		
		
			try {
			ArrayList<FruitoreModel> utentiScaduti= fC.getFruitori().utentiScaduti();
			
				fC.getFruitori().rimuoviIscrizioni(utentiScaduti);
				pC.getPrestitiM().annullaPrestitiUtenti(utentiScaduti);
				pC.getPrestitiM().checkPrestitiTerminati();
				saves.salvaFruitori();
				saves.salvaPrestiti();
			}catch(NullPointerException e) {
				//nn c'e' nessuna rimozione
			}					
	}	

		
}
