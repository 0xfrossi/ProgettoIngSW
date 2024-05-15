package ing.unibs.it;

import util.Unibs.MyMenu;

public class Main {
	
	public static void main(String[] args) {
		
		
		GestioneMenu menu= new GestioneMenu();
		//Controlla se sono scadute le iscrizioni e rimuove
		
		try {			
		menu.getFruitori().controlloIscrizioni();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		MyMenu menuPrincipal= new MyMenu(Costanti.TITOLO_PRINCIPALE, Costanti.SCELTE_PRINCIP);
		boolean finito=false;

		do{
			int scelta=menuPrincipal.scegli();

			switch(scelta){
			case 0: 
				finito=true;
				break;
			case 1:						//Sezione Fruitore
				menu.menuGestFruitore();
				break;
			case 2:						//Sezione Operatore
				menu.sottoOp();
				break;
			}
		} while(!finito);
			
		}
	
	}
