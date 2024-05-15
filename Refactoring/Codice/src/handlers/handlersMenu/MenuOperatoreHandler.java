package handlers.handlersMenu;

import controller.FruitoriController;
import controller.PrestitiController;
import controller.RisorseController;
import controller.StoricoController;
import myUtil.MyMenu;
import myUtil.MyUtil;
import services.SavesManager;
import services.StartManager;
import view.SystemView;


/**
 * Classe che gestisce il menu'operatore
 * @author Francesco
 *
 */
public class MenuOperatoreHandler {

	private FruitoriController fruitoriC;
	private RisorseController risorseC;
	private PrestitiController prestitiC;
	private SavesManager savemanager;
	private menuStoricoHandler storicoH;
	
	/**
	 * Costruttore
	 * @param fruitoriC controller fruitori
	 * @param risorseC controller risorse
	 * @param prestitiC controller prestiti
	 * @param savemanager servizio  salvataggio
	 * @param storicoC controller storico
	 */
	public MenuOperatoreHandler(FruitoriController fruitoriC,RisorseController risorseC,PrestitiController prestitiC,SavesManager savemanager,StoricoController storicoC) {
			
			this.fruitoriC=fruitoriC;
			this.risorseC=risorseC;
			this.prestitiC=prestitiC;
			this.savemanager=savemanager;
			this.storicoH= new menuStoricoHandler(storicoC);
	}
	

	/**
	 * menu principale operatore
	 */
	private void menuOperatore() {
		
		MyMenu sottoOperatorePrestiti = new MyMenu(SystemView.scegli(),SystemView.MENU_OP);{
			boolean finito = false;
					
				do{
					int scelta=sottoOperatorePrestiti.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					
					case 1: 
						
						fruitoriC.stampaFruitori();
						break;
					case 2:
						
						risorseC.menuVisualizzaRisorse();
						break;
					case 3:{
						
						risorseC.manageRisorse();
						savemanager.salvaRisorse();
						}
						break;
					case 4:
						
						prestitiC.stampaPrestiti();
						break;
					case 5:
						
						storicoH.menuStorico();
						break;
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						SystemView.comandoNnriconosciuto();
						break;
					}
				} 	while(!finito);	
			}
	}
	
	
	/**
	 * gestisce credenziali operatore, quindi l'accesso
	 */
	public void accessoOp() {
		
		String pass= MyUtil.leggiStringa(SystemView.insPassOp());
		if(pass.equals("admin")) 
			menuOperatore();
		else {
			SystemView.noAccessoOp();
			
		}
	}
	
	
	
	
	
	
}
