package handlers.handlersMenu;

import controller.FruitoriController;
import controller.PrestitiController;
import controller.RisorseController;
import controller.StoricoController;
import myUtil.MyMenu;
import services.SavesManager;
import view.SystemView;


/**
 * Classe che si occupa della gestione del primo menu' che comparira'
 * @author Francesco
 *
 */
public class MenuPrinicipaleHandler {

	
	private MenuOperatoreHandler menuOp;
	private MenuFruitoreHandler menuFru;
	
	
	/**
	 * Costuttore, inizializza menuFruitore e menuOp
	 * @param risorseC il controller risorse
	 * @param fruitoriC il controller fruitori
	 * @param saves il gestore salvataggi
	 * @param prestitiC il controller prestiti
	 * @param storicoC il controller storico
	 */
	public MenuPrinicipaleHandler(RisorseController risorseC,FruitoriController fruitoriC,SavesManager saves,PrestitiController prestitiC,StoricoController storicoC) {
		
		menuFru= new MenuFruitoreHandler(fruitoriC, risorseC, prestitiC, saves);
		menuOp= new MenuOperatoreHandler(fruitoriC, risorseC, prestitiC, saves,storicoC);	
	}
	
	
	
	
	/**
	 * Gestisce la prima scelta: entri come operatore o fruitore?
	 */
	public void menuPrincipale() {
		
		MyMenu sottoOperatorePrestiti = new MyMenu(SystemView.welcome(),SystemView.ACCESSO);{
			boolean finito = false;
					
				do{
					int scelta=sottoOperatorePrestiti.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					
					case 1: 
						
						menuFru.menuFruitoreAccesso();
						break;
					case 2:
						
						menuOp.accessoOp();
					
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						SystemView.comandoNnriconosciuto();
						break;
					}
				} 	while(!finito);	
			}
	}
	
	
	
	
}
