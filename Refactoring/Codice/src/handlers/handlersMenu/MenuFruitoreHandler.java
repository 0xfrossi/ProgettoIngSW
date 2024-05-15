package handlers.handlersMenu;

import controller.FruitoriController;
import controller.PrestitiController;
import controller.RisorseController;
import model.FruitoreModel;
import myUtil.MyMenu;
import services.SavesManager;
import view.SystemView;


/**
 * Classe che gestisce il menu' di un fruitore
 * @author Francesco
 *
 */
public class MenuFruitoreHandler {

	//Attributi
	
	private FruitoriController fruitoriC;
	private FruitoreModel fruitore;
	private RisorseController risorseC;
	private MenuPrestitiFruitoreHandler prestitiSaves;
	private SavesManager saves;
	
	
	/**
	 * Costruttore 
	 * @param fruitoriC controller fruitore
	 * @param risorseC controller risorse
	 * @param prestitiC controller prestiti
	 * @param saves sevizio salataggio
	 */
	public MenuFruitoreHandler(FruitoriController fruitoriC,RisorseController risorseC,PrestitiController prestitiC,SavesManager saves) {
		
		this.fruitoriC=fruitoriC;
		this.risorseC=risorseC;
		this.saves=saves;
		this.prestitiSaves=new MenuPrestitiFruitoreHandler(prestitiC, risorseC, saves);
	}
	
	
	/**
	 * menu' che gestisce le prime scelte di un utente, iscrizione e accesso
	 */
	public void menuFruitoreAccesso() {
		
		MyMenu accessoF = new MyMenu(SystemView.scegli(),SystemView.ACCESSO_F);{
			boolean finito = false;
					
				do{
					int scelta=accessoF.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					
					case 1: //iscrizione
						
						fruitoriC.CreaFruitrore();
						saves.salvaFruitori();
						break;
					case 2: //accedi
						
						accediASottoMenu();
						break;
					
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						SystemView.comandoNnriconosciuto();
						break;
					}
				} 	while(!finito);	
			}
		}
			
	
	/**
	 * metodo che da accesso alla pagina dedicata di un fruitore
	 * precondizione: il fruitore deve inseire le credenziali esatte
	 */
	private void accediASottoMenu() {
		
		 fruitore = fruitoriC.accesso();
		if(fruitore!=null)
			menuFruitore(fruitore);
	}
	
	/**
	 * menu del fruitore con le operazioni correlate
	 * @param f fuitore selezioanto
	 * percondizione: il f deve esistere
	 */
	private void menuFruitore(FruitoreModel f) {
		
		MyMenu menuF = new MyMenu(SystemView.scegli(),SystemView.MENU_F);{
			boolean finito = false;
					
				do{
					int scelta=menuF.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					
					case 1: 
						
						fruitoriC.stampaFruitore(f);
						break;
					case 2:{
						
						fruitoriC.rinnovaFruitore(f);
						saves.salvaFruitori();
					}
						break;
					case 3:
						risorseC.menuVisualizzaRisorse();
						break;
					case 4:
						prestitiSaves.menuPrestiti(f);
						break;
					
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						SystemView.comandoNnriconosciuto();
						break;
					}
				} 	while(!finito);	
			}
	}
		
	
	

	



}
