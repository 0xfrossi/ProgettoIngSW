package ing.unibs.it;

import java.io.File;

import myLib.ServizioFile;
import util.Unibs.MyIOFile;
import util.Unibs.MyMenu;
import util.Unibs.MyUtil;
import util.Unibs.*;



public class GestioneMenu {
	
	private Fruitore fruitore;
	private ArrayFruitore fruitori;
	private File file ;
	private File fileLibri ;
	private File filePrestiti;
	private Libri libri;
	private Libro libro;
	private ArrayPrestito prestiti;
	
	
	/**
	 * Costruttore che inizializza gli attributi,legge gli oggetti salvati su file e li carica
	 */
	public GestioneMenu() {
		fruitori=new ArrayFruitore();
		libri= new Libri();
		prestiti= new ArrayPrestito();
		file= new File("fruitori.txt");
		fileLibri= new File("libri.txt");
		filePrestiti= new File("prestiti.txt");
		
		try {
			ServizioFile.checkFile(file, fruitori);
			ServizioFile.checkFile(fileLibri, libri);
			ServizioFile.checkFile(filePrestiti, prestiti);
			/*if(file.length()!=0)
				fruitori=(ArrayFruitore)MyIOFile.leggiOggetto(file);
			
			if(fileLibri.length()!=0)
				libri=(Libri) MyIOFile.leggiOggetto(fileLibri);*/
				
			
			
		}	//System.out.println(RIUSCITA);
		catch ( Exception e) {
			e.printStackTrace();
		}	
		fruitori = (ArrayFruitore)ServizioFile.caricaSingoloOggetto(file);
		libri = (Libri)ServizioFile.caricaSingoloOggetto(fileLibri);
		prestiti= (ArrayPrestito) ServizioFile.caricaSingoloOggetto(filePrestiti);
	}
	
	/**
	 * Contiene l'interfaccia principale del menu' fruitore:
	 * *iscrizione
	 * *Area fruitore che esegue il metodo sottoFruitore() previo controllo credenziali
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
			
				/*if(!file.exists()) {
					 file = new File("fruitori.txt");*/
				
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
						sottoFruitore();
								
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
	 * menu' che contiene l'interfaccia principale per l'operatore,accesso previo controllo, contiene:
	 * *listaFruitori()
	 * *gestioneLibri()
	 * *gestionePrestitiOperatore()
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
				case 1:	//visualizza i fruitori attuali del sistema
					
					listaFruitori();
						
					break;
				case 2: //sotto menu' libri
					gestioneLibri();
					break;
					
				case 3://sotto menu' prestiti
					gestionePrestitiOperatore();
			
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
	
	

	
	
	private void sottoFruitore() {
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
					break;
				
				case 2: //visualizza riepilogo
				
					fruitore.stampaFruitore();
					break;	
				case 3://cerca libro
					libri.cercaLibro();
					break;
				case 4:	//Sezione prestiti
					gestionePrestitiFruitore();
			
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					System.out.println(Costanti.COM_NON_RIC);
					break;
				}
			} 	while(!finito);	
		}
			
	}	
	
	private void gestionePrestitiFruitore() {
		MyMenu sottoPrestitiFruitore = new MyMenu(Costanti.COSA_,Costanti.SCELTE_PRESTITO_FRUITORE);{
			boolean finito = false;
					
				do{
					int scelta=sottoPrestitiFruitore.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					
					case 1: //chiedi il prestito
						chiediIlPrestito();
						break;
					
					case 2: //rinnova prestito
						prestiti.rinnovaPrestito(fruitore);
						ServizioFile.salvaSingoloOggetto(filePrestiti, prestiti, false);
						ServizioFile.salvaSingoloOggetto(fileLibri, libri, false);
					
						break;	
					case 3://Visualizza prestiti in corso
						prestiti.stampaPrestitiUtente(prestiti.filtraPrestitiPerUser(getFruitore()));
						break;
					case 4:	//annulla prestito di libro
						prestiti.annullaPrestitoRisorsa(getFruitore(), libri.scegliPerNome(MyUtil.leggiStringaNonVuota("inserisci il titolo del libro del quale vuoi annullare il prestito: ")));
						ServizioFile.salvaSingoloOggetto(filePrestiti, prestiti, false);
						ServizioFile.salvaSingoloOggetto(fileLibri, libri, false);
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
					
				} 	while(!finito);	
		}
		
		
	}
	
	
	
	
	private void chiediIlPrestito(){
		
		if(prestiti.contaPrestitiUtente(getFruitore().getUsername(), "Libri") == libro.getPrestitiMax())
		
			System.out.println("Operazione non disponibile, hai gia'in prestito il numero di risorse massime per la categoria Libri ");
		
		else{
			Libro libro = (Libro) libri.scegliPerNome(MyUtil.leggiStringaNonVuota("Inserisci il titolo del libro che richiedi in prestito: "));
			
			if(libro != null){
				if(prestiti.prestitoNotExist(getFruitore(), libro)){
					prestiti.addPrestito(getFruitore(), libro);
					System.out.println(libro.getNome() + "prenotazione avvenuta con successo!");
				}
				else
					System.out.println("Hai gia' il libro chiesto ");
				
			}
//			qui libro==null
		}
		ServizioFile.salvaSingoloOggetto(filePrestiti, prestiti, false);
		ServizioFile.salvaSingoloOggetto(fileLibri, libri, false);
		
		
	}
		
	
	
	
	
	private void gestionePrestitiOperatore() {
		
		MyMenu sottoOperatorePrestiti = new MyMenu("Gestisci i prestiti: ",Costanti.SCELTE_SOTTO_LIBRI);{
			boolean finito = false;
					
				do{
					int scelta=sottoOperatorePrestiti.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					
					case 1: //visualizza prestiti 
						
						prestiti.stampaPrestitiAttivi();
						break;
								
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
				} 	while(!finito);	
			}
	}
	
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
						
						
					case 4:
						libri.cercaLibro();
						
						
					case 5: //SALVA LAVORO
						try {
						ServizioFile.salvaSingoloOggetto(fileLibri, libri, true);
						
						} catch (Exception e) {  e.printStackTrace();	}
						
						break;	
				
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
				} 	while(!finito);	
			}
		
		
		
	}
	
	private void inserisciLibroInSottocat() {
		
			libro=new Libro(MyUtil.leggiStringaNonVuota(Costanti.INS_TITOLO),MyUtil.leggiIntero(Costanti.INS_CODICE),MyUtil.leggiInteroConMinimo(Costanti.INS_NLIC, 1), MyUtil.inserisciAutori(),MyUtil.leggiIntero(Costanti.INS_NPAGINE), 
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




	public File getFileLibri() {
		return fileLibri;
	}




	public File getFilePrestiti() {
		return filePrestiti;
	}




	public Libri getLibri() {
		return libri;
	}




	public Libro getLibro() {
		return libro;
	}




	public ArrayPrestito getPrestiti() {
		return prestiti;
	}


	


	
}


