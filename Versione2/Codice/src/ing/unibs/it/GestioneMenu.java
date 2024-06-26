package ing.unibs.it;

import java.io.File;




import util.Unibs.MyIOFile;
import util.Unibs.MyMenu;
import util.Unibs.MyUtil;
import util.Unibs.*;


/**
 * Classe di gestione menu' general
 * @author Francesco
 *
 */
public class GestioneMenu {
	
	//Attributi
	private Fruitore fruitore;
	private ArrayFruitore fruitori;
	private File file ;
	private File fileLibri ;
	private Libri libri;
	private Libro libro;
	
	/**
	 * Costruttore che inizializza i file di salvataggio e li carica se gia' presenti
	 */
	public GestioneMenu() {
		fruitori=new ArrayFruitore();
		libri= new Libri();
		file= new File("fruitori.txt");
		fileLibri= new File("libri.txt");
		
		try {
			IOFileUtil.checkFile(file, fruitori);
			IOFileUtil.checkFile(fileLibri, libri);	
			}
		catch ( Exception e) {
			e.printStackTrace();
		}	
		fruitori = (ArrayFruitore)IOFileUtil.caricaSingoloOggetto(file);
		libri = (Libri)IOFileUtil.caricaSingoloOggetto(fileLibri);
	}
	
	
	
	/**
	 * Menu' che contiene metodi lato fruitore
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
				
			} catch (Exception e) { 
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
	 * Menu' contenente metodi lato operatore
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
					
					listaFruitori();
						
					break;
				case 2: 
					gestioneLibri();
					break;
			
				}
				}while(!finit );
		}
		
		else  System.out.println(Costanti.PASS_ERRATA);	
	}
	
	
	
	
	private void listaFruitori() {
		if(!fruitori.getFruitori().isEmpty())
			fruitori.stampaFruitori();
		else System.out.println(Costanti.NESSUN_FRUIT);
	}
	
	

	
	/**
	 * Menu' di gestione fruitore
	 * @param fruitore il fruitore selezionato
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
				
					fruitore.rinnovoIscrizione();
					IOFileUtil.salvaSingoloOggetto(file, fruitori, false);
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
	
	/**
	 * Sotto menu' per la gestione libri dell'operatore
	 */
	private void gestioneLibri() {
		MyMenu sottoOperatoreLibri = new MyMenu(Costanti.MENU_LIBRI,Costanti.SCELTE_SOTTO_LIBRI);{
			boolean finito = false;
					
				do{
					int scelta=sottoOperatoreLibri.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					
					case 1: //AGGIUNGI LIBRO
						inserisciLibroInSottocat();
						
						break;
					
					case 2: //RIMUOVI LIBRO
					
						libri.removeLibro(MyUtil.leggiIntero(Costanti.REMOVE_LIBRO));
						break;	
						
					case 3: //VISUALIZZA
					
						visualizzaLibri();
						break;		
						
					case 4: //SALVA LAVORO
						try {
						IOFileUtil.salvaSingoloOggetto(fileLibri, libri, true);
						
						} catch (Exception e) { 
							e.printStackTrace();	
							}
						
						break;			
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
				} 	while(!finito);	
			}
		
		
		
	}
	
	/**
	 * Crea l'oggetto libro e scegli in che sotto categoria inserirlo
	 */
	private void inserisciLibroInSottocat() {
		
			libro=new Libro(MyUtil.leggiStringaNonVuota(Costanti.INS_TITOLO),MyUtil.leggiInteroConMinimo(Costanti.INS_NLIC, 1), MyUtil.leggiIntero(Costanti.INS_COD), MyUtil.inserisciAutori(),MyUtil.leggiIntero(Costanti.INS_NPAGINE), 
							MyUtil.leggiStringaNonVuota(Costanti.INS_CASAED),MyUtil.leggiStringaNonVuota(Costanti.INS_GENERE), MyUtil.leggiData(Costanti.INS_ANNOPUBB));
			MyMenu sceltaSottoCat = new MyMenu(Costanti.INS_LIBRO,Costanti.SCELTE_SOTTOCAT);{
				boolean finito = false;
				
				do{
					int scelta=sceltaSottoCat.scegli();
			
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
				
					case 1: // INSERISCI IN LIBRI ING
				
						libri.addInIng(libro);
						finito=true;
						break;
				
					case 2: // INSERISCI IN LIBRI ITA
				
						libri.addInIta(libro);
						finito=true;
						break;	
			
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
				} 	while(!finito);	
			}
		}
	
	/**
	 * Metodo che consente la scelta di quale categoria visalizzare i libri
	 */
	private void visualizzaLibri() {
		MyMenu visualizzaLibri = new MyMenu(Costanti.COSA_,Costanti.SCELTE_VISUALIZZ);{
		boolean finito = false;
				
			do{
				int scelta=visualizzaLibri.scegli();
			
				switch(scelta){
				case 0: //esci
					finito=true;
					break;
				
				case 1: //visualizza libri in ing
				
					libri.stampaIng();
					break;
				
				case 2: //visualizza libri in ita
				
					libri.stampaIta();
					break;	
				case 3: //visualizza tutto
					
					libri.stampaTutto();
					break;	
			
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					System.out.println(Costanti.COM_NON_RIC);
					break;
				}
			} 	while(!finito);	
		}
			
	}	
	
	
	//Getters
	
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





	
}


