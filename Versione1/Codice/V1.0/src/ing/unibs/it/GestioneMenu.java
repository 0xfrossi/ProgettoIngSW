package ing.unibs.it;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import util.Unibs.IOFileUtil;
import util.Unibs.MyIOFile;
import util.Unibs.MyMenu;
import util.Unibs.MyUtil;


/**
 * Classe per la gestione menu'
 * @author Francesco
 *
 */
public class GestioneMenu implements Serializable {
	
	//Attributi
	
	private static final long serialVersionUID = 1L;
	private Fruitore fruitore;
	private ArrayFruitore fruitori;
	private File file ;
	
	/**
	 * Costruttore che carica i fruitori se sono stati salvati
	 */
	public GestioneMenu() {
		fruitori=new ArrayFruitore();
		try {
			
			file= new File("fruitori.txt");
			
			IOFileUtil.checkFile(file, fruitori);
			fruitori=(ArrayFruitore)MyIOFile.leggiOggetto(file);
			
		}	//System.out.println(RIUSCITA);
		catch (ClassNotFoundException  | IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	/**
	 * Contiene operazioni che puo' svolgere un fruitore
	 */
	public void menuGestFruitore() {
	MyMenu menuPrincipale = new MyMenu("Sezione fruitori",Costanti.SCELTE_FRUITORE);{
	boolean finito = false;
			
	do{
		int scelta=menuPrincipale.scegli();
		
		switch(scelta){
		case 0: //esci
			finito=true;
			break;
		case 1: //iscrizione
			try {
				
			fruitori.addFriutore(fruitore= new Fruitore(MyUtil.leggiStringaNonVuota(Costanti.INS_NOME), MyUtil.leggiStringaNonVuota(Costanti.INS_COGNOME), 
														MyUtil.leggiData(Costanti.INS_NASCITA), MyUtil.leggiStringaNonVuota(Costanti.INS_CASA),
														MyUtil.leggiStringaNonVuota(Costanti.INS_USER),
														MyUtil.leggiStringaNonVuota(Costanti.INS_PASS)));
			
				
				
				MyIOFile.scriviOggetto(file, fruitori);
				
			} catch (IOException e) { 
				e.printStackTrace();	
				}
			break;
		case 2: //Area fruitore
			
			String user= MyUtil.leggiStringaNonVuota(Costanti.INS_USER);
			String pass= MyUtil.leggiStringaNonVuota(Costanti.INS_PASS);
			if(fruitori.getFruitori().isEmpty())
				System.out.println(Costanti.CREDENZIALI_NO);
			else {
				for(int i=0; i< fruitori.getFruitori().size();i++) {
					if(user.equals(fruitori.getFruitori().get(i).getUsername()) && pass.equals(fruitori.getFruitori().get(i).getPassword())){
						System.out.println(Costanti.ACCESSO_OK);		
						sottoFruitore(fruitori.getFruitori().get(i));
								
					}	
				System.out.println(Costanti.CREDENZIALI_NO);
				}	
			}
			break;
		
		default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
			System.out.println(Costanti.COM_NON_RIC);
			break;
		}
	} while(!finito);
	
	}	
}
	/**
	 * Menu' lato operatore
	 */
	public void sottoOp() {
		String pass= MyUtil.leggiStringa(Costanti.INS_PASS);
		if(pass.equals(Costanti.PASS_OPERATORE)) {
			MyMenu  menuOp= new MyMenu(Costanti.TITOLO_OP, Costanti.SCELTE_OP); 
			
			boolean finit=false;

			do{
				int scelt=menuOp.scegli();
				switch(scelt){
				
				case 0: 
					finit=true;
					break;
				case 1:	
					
					fruitori.stampaFruitori();
						
					break;
					
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					System.out.println(Costanti.COM_NON_RIC);
					break;
			}
		}while(!finit );
	}
		
		else  System.out.println(Costanti.PASS_ERRATA);	
	}
	
	
	
	
	
	

	
	/**
	 * Menu' contenente oprazioni lato fruitore
	 */
	private void sottoFruitore(Fruitore fruitore) {
		MyMenu sottoFruitore = new MyMenu(Costanti.COSA_,Costanti.SCELTE_SOTTO_FRUITORE);{
		boolean finito = false;
				
			do{
				int scelta=sottoFruitore.scegli();
			
				switch(scelta){
				case 0: //esci
					finito=true;
					break;
				
				case 1: //Rinnovo iscrizione
				try {
					fruitore.rinnovoIscrizione();
					MyIOFile.scriviOggetto(file, fruitori);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
					break;
				
				case 2: //visualizza riepilogo
				
					fruitore.stampaFruitore();
					break;	
			
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					System.out.println(Costanti.COM_NON_RIC);
					break;
				}
			} 	while(!finito);	
		}
			
	}	
	
	
	
	//Getter and Setters
	public Fruitore getFruitore() {
		return fruitore;
	}

	public void setFruitore(Fruitore fruitore) {
		this.fruitore = fruitore;
	}

	public ArrayFruitore getFruitori() {
		return fruitori;
	}

	public void setFruitori(ArrayFruitore fruitori) {
		this.fruitori = fruitori;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}


