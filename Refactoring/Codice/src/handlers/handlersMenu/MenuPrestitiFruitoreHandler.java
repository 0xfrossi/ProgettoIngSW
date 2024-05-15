package handlers.handlersMenu;

import java.util.ArrayList;
import controller.PrestitiController;
import controller.RisorseController;
import model.FruitoreModel;
import myUtil.MyMenu;
import services.SavesManager;
import view.SystemView;
/**
 * Classe che si occupa di gestire le operazioni di prestito del fruitore salvandolee
 * @author Francesco
 *
 */
public class MenuPrestitiFruitoreHandler {

	
	private PrestitiController prestitiC;
	private RisorseController risorseC;
	private SavesManager saves;
	
	/**
	 * ostruttore
	 * @param prestitiC contorller prestiti
	 * @param risorseC contorller risorse
	 * @param saves servizio salvataggio
	 */
	public MenuPrestitiFruitoreHandler(PrestitiController prestitiC,RisorseController risorseC ,SavesManager saves) {
		
		this.prestitiC=prestitiC;
		this.risorseC=risorseC;	
		this.saves=saves;
	}
	
	
	/**
	 * Chiedi un presito e salva
	 * @param f fruitore che chiede
	 */
	public void chiediPrestito(FruitoreModel f) {
		
		prestitiC.chiediPrestito(f, risorseC.selezionaRisorsa());
		saves.salvaRisorse();
		saves.salvaPrestiti();	
	}
	
	
	/**
	 * annulla un presito e salva
	 * @param f fruitore che chiede
	 */
	public void annullaPrestito(FruitoreModel f) {	
		
		prestitiC.annullaPrestito(prestitiC.selezionaPrestito(f));
		saves.salvaRisorse();
		saves.salvaPrestiti();
	}
	
	/**
	 * rinnova un presito e salva
	 * @param f fruitore che chiede
	 */
	public void rinnovaPrestito(FruitoreModel f) {
		
		prestitiC.rinnovaPrestito(f);
		saves.salvaRisorse();
		saves.salvaPrestiti();
	}
	
	
	/**
	 * annulla insieme di prestiti e salva
	 * @param utenti fruitori a cui annullare
	 */
	public void annullaprestitiUtenti(ArrayList<FruitoreModel> utenti) {
		prestitiC.getPrestitiM().annullaPrestitiUtenti(utenti);
		saves.salvaRisorse();
		saves.salvaPrestiti();
	}

	/**
	 * menu' per la gestione prestiti di un fruitore
	 * @param f fruitore selezionato
	 */
	public void menuPrestiti(FruitoreModel f) {
		
		MyMenu menuPrestiti = new MyMenu(SystemView.scegli(),SystemView.MENU_PRESTITI);{
			boolean finito = false;			
				do{
					int scelta=menuPrestiti.scegli();
				
					switch(scelta){
					
					case 0: //esci
						finito=true;
						break;
					
					case 1: 
						
						chiediPrestito(f);
						break;
						
					case 2:
						
						rinnovaPrestito(f);
						break;
					case 3:
						
						annullaPrestito(f);
						break;
						
					case 4:
						
						prestitiC.stampaPrestitiUtente(prestitiC.getPrestitiM().filtraPrestitiPerUser(f));	
						break;
					
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						SystemView.comandoNnriconosciuto();
						break;
					}
				} 	while(!finito);	
			}
	}
	

	
	
	
	//GETTERS
	
	public PrestitiController getPrestitiC() {
		return prestitiC;
	}

	public RisorseController getRisorseC() {
		return risorseC;
	}



	
}
