package handlers.handlersMenu;

import controller.StoricoController;
import myUtil.MyMenu;
import view.SystemView;

/**
 * COntiene i menu' per la gestione dello storico
 * @author Francesco
 *
 */
public class menuStoricoHandler {

	
	private StoricoController storicoC;
	
	/**
	 * Costruttore 
	 * @param storicoC il controller storico
	 */
	public menuStoricoHandler(StoricoController storicoC) {
		this.storicoC=storicoC;
	}
	
	
	/**
	 * menu principale per accesso a storico o statistiche
	 */
	public void menuStorico() {
		
		MyMenu menu = new MyMenu(SystemView.scegli(),SystemView.SCEGLI_STORICO);{
		boolean finito = false;
				
			do{
				int scelta=menu.scegli();
			
				switch(scelta){
				case 0: //esci
					finito=true;
					break;
				
				case 1: //query
					
					menuQuery();
					break;
				case 2: //stampaDati
					
					menuStampe();
					break;
				
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					SystemView.comandoNnriconosciuto();
					break;
				}
			} while(!finito);
		}
		
		}
	
	
	/**
	 * menu' che contiene la riheiesta di dati statistici
	 */
	private void menuQuery() {
		
		MyMenu menu = new MyMenu(SystemView.scegli(),SystemView.SCEGLI_QUERY);{
		boolean finito = false;
				
			do{
				int scelta=menu.scegli();
			
				switch(scelta){
				case 0: //esci
					finito=true;
					break;				
				case 1: 					
					storicoC.stampaNumPrestitiFruitoreInAnno();
					break;
				case 2: 					
					storicoC.stampaNumPrestitiAnno();
					break;
				case 3: 					
					storicoC.stampaPrestitiInAnno();
					break;
				case 4: 		
					storicoC.stampaProrogheAnno();
					break;	
				case 5: 	
					storicoC.stampaRisorsaPiuRichiesta();
					break;	
						
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					SystemView.comandoNnriconosciuto();
					break;
				}
			} while(!finito);
		}
	}
	
	
	/**
	 * menu'che contiene tutti i dati storici
	 */
	private void menuStampe() {
		
		MyMenu menu = new MyMenu(SystemView.scegli(),SystemView.SCEGLI_STAMPE);{
		boolean finito = false;
				
			do{
				int scelta=menu.scegli();
			
				switch(scelta){
				case 0: //esci
					finito=true;
					break;
				
				case 1: 
					
					storicoC.stampaFruitori();
					break;
				case 2: 
					
					storicoC.stampaRinnovati();
					break;
				case 3: 
					
					storicoC.stampaScaduti();
					break;
				case 4: 
					
					storicoC.stampaPrestiti();
					break;	
				case 5: 
					
					storicoC.stampaProrogati();
					break;	
					
				case 6: 
					storicoC.stampaPrestitiTerminati();
					break;
				case 7: 
					storicoC.stampaPrestabiliInPassato();
					break;
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					SystemView.comandoNnriconosciuto();
					break;
				}
			} while(!finito);
		}
	}
	
	
	
	
}
