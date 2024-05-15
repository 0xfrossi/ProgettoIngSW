package ing.unibs.it;


import java.util.ArrayList;
import util.Unibs.MyMenu;
import util.Unibs.IOFileUtil;

public class Main {
	

	public static void main(String[] args) {
		
		//LogsDati scadutiStoricoDati= LogsDati.getIstance();
		ArrayList<Fruitore> utentiScaduti;
		GestioneMenu menu= new GestioneMenu();
		MyMenu menuFinale= new MyMenu(Costanti.TITOLO_PRINCIPALE, Costanti.SCELTE_PRINCIP);
		boolean finito=false;
		
		
		//contollo lo stato delle iscrizioni dei fruitori, in caso li rimuovo dal sistema
		// e rimuovo anche gli evenuali prestiti sottoscritti da essi
		try {
					
			utentiScaduti= menu.getFruitori().utentiScaduti();
			
			if(!utentiScaduti.isEmpty()) {
				for(int i=0; i<utentiScaduti.size();i++) {
					LogsDati.getIstance().addFruitoriScaduti(utentiScaduti.get(i));
				}
			}
			
			menu.getFruitori().rimuoviIscrizioni(utentiScaduti);
			menu.getPrestiti().annullaPrestitiUtenti(utentiScaduti);
			menu.getPrestiti().checkPrestiti();
			
			IOFileUtil.salvaSingoloOggetto(menu.getFile(), menu.getFruitori(), false);
			IOFileUtil.salvaSingoloOggetto(menu.getFilePrestiti(), menu.getPrestiti(), false);
		//	IOFileUtil.salvaSingoloOggetto(menu.getFileLogs(), menu.getLogs(), false);
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
			}
		
		
		// avvio il menu' iniziale
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
			default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
				System.out.println(Costanti.COM_NON_RIC);
				break;
			}
		} while(!finito);
			
	}
	
	
	
}
