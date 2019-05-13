package ing.unibs.it;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import util.Unibs.MyIOFile;
import util.Unibs.MyMenu;
import util.Unibs.MyUtil;
import util.Unibs.ServizioFile;

public class Main {
	

	public static void main(String[] args) {
		
		ArrayList<Fruitore> utentiScaduti;
		GestioneMenu menu= new GestioneMenu();
		
		try {
			
				
		utentiScaduti= menu.getFruitori().utentiScaduti();			
		menu.getFruitori().rimuoviIscrizioni(utentiScaduti);
		menu.getPrestiti().annullaPrestitiUtenti(utentiScaduti);
		menu.getPrestiti().checkPrestiti();
		ServizioFile.salvaSingoloOggetto(menu.getFile(), menu.getFruitori(), false);
		ServizioFile.salvaSingoloOggetto(menu.getFilePrestiti(), menu.getPrestiti(), false);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		MyMenu menuFinale= new MyMenu(Costanti.TITOLO_PRINCIPALE, Costanti.SCELTE_PRINCIP);
		boolean finito=false;

		do{
			int scelta=menuFinale.scegli();

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
