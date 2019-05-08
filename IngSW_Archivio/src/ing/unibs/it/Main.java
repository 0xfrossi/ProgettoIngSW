package ing.unibs.it;

import java.io.File;
import java.io.IOException;

import util.Unibs.MyIOFile;
import util.Unibs.MyMenu;
import util.Unibs.MyUtil;

public class Main {
	

	
	public static void main(String[] args) {
		
		
		GestioneMenu menu= new GestioneMenu();
		
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
