package ing.unibs.it;

import java.io.File;


import util.Unibs.MyIOFile;
import util.Unibs.MyMenu;
import util.Unibs.MyUtil;
import util.Unibs.*;


/**
 * Classe  che contiene le interfacce, utente/fruitore
 * @author Francesco Rossi
 *
 */
public class GestioneMenu {
	
	private Fruitore fruitore;
	private static ArrayFruitore fruitori;
	private File file ;
	private File fileLibri ;
	private File filePrestiti;
	private static Libri libri;
	private Risorsa libro;
	private static ArrayPrestito prestiti;
	private Risorsa film;
	private static Films films;
	private File fileFilms;
//	private static SalvataggiStorico logs;
//	private File fileLogs;
	
	
	/**
	 * Costruttore che inizializza gli attributi,legge gli oggetti salvati su file e li carica
	 */
	public GestioneMenu() {
		
		fruitori=new ArrayFruitore();
		libri= new Libri();
		prestiti= new ArrayPrestito();
		films= new Films();
	//	logs= new SalvataggiStorico();
		file= new File("fruitori.txt");
		fileLibri= new File("libri.txt");
		filePrestiti= new File("prestiti.txt");
		fileFilms= new File("films.txt");
	//	fileLogs= new File("logs.txt");
		
		try {
			IOFileUtil.checkFile(file, fruitori);
			IOFileUtil.checkFile(fileLibri, libri);
			IOFileUtil.checkFile(filePrestiti, prestiti);
			IOFileUtil.checkFile(fileFilms, films);
		//	IOFileUtil.checkFile(fileLogs, logs);
			
			
			
		}	//System.out.println(RIUSCITA);
		catch ( Exception e) {
			e.printStackTrace();
		}	
		fruitori = (ArrayFruitore)IOFileUtil.caricaSingoloOggetto(file);
		libri = (Libri)IOFileUtil.caricaSingoloOggetto(fileLibri);
		prestiti= (ArrayPrestito) IOFileUtil.caricaSingoloOggetto(filePrestiti);
		films =(Films) IOFileUtil.caricaSingoloOggetto(fileFilms);
	//	logs = (SalvataggiStorico) IOFileUtil.caricaSingoloOggetto(fileLogs);
		reloadArrayPrestiti();
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
				
			fruitore= new Fruitore(MyUtil.leggiStringaNonVuota(Costanti.INS_NOME), MyUtil.leggiStringaNonVuota(Costanti.INS_COGNOME), 
														MyUtil.leggiData(Costanti.INS_NASCITA), MyUtil.leggiStringaNonVuota(Costanti.INS_CASA),
														MyUtil.leggiStringaNonVuota(Costanti.INS_USER),
														MyUtil.leggiStringaNonVuota(Costanti.INS_PASS));
			
				fruitori.addFriutore(fruitore);
				LogsDati.getIstance().addInFruitori(fruitore);
				MyIOFile.scriviOggetto(file, fruitori);
			//	MyIOFile.scriviOggetto(fileLogs, logs);
				
			} catch (Exception e) { 
				e.printStackTrace();	
				}
			break;
		case 2: //Area fruitore
			boolean in=false;
			int dentro=-1;
			String user= MyUtil.leggiStringaNonVuota(Costanti.INS_USER);
			String pass= MyUtil.leggiStringaNonVuota(Costanti.INS_PASS);
			if(fruitori.getFruitori().isEmpty()) {
				System.out.println(Costanti.CREDENZIALI_NO);
				break;
			}
			else {
				for(int i=0; i< fruitori.getFruitori().size();i++) {
					if(user.equals(fruitori.getFruitori().get(i).getUsername()) && pass.equals(fruitori.getFruitori().get(i).getPassword())) {
						in=true;
						dentro=i;
					}
				}
				if(in && dentro!=-1)	{
					System.out.println(Costanti.ACCESSO_OK);		
					sottoFruitore(fruitori.getFruitori().get(dentro));	
					
				}
				else
					System.out.println(Costanti.CREDENZIALI_NO);
				
				break;
			}
			//break;
		
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
	 * *GESTIONEfILMS()
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
					
				case 3://sotto menu' Dati
					gestioneDatiOperatore();
					break;
				
				case 4://sotto menu' films
					gestioneFilms();
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
	 * Stampa la lista dei fruitori attuali
	 */
	private void listaFruitori() {
		if(!fruitori.getFruitori().isEmpty())
			fruitori.stampaFruitori();
		else System.out.println(Costanti.NESSUN_FRUIT);
	}
	
	

	
	/**
	 * Menu' che gestisce le scelte principali di un utente, contiene:
	 * *rinnovoIscrizione()
	 * *stamapaFruitore()
	 * *cercaLibro()
	 * *gestionePrestitiFruitore()
	 * 
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
					if(fruitore.statoRinnovo())
						LogsDati.getIstance().addInRinnovati(fruitore);
					fruitore.rinnovoIscrizione();
				//	IOFileUtil.salvaSingoloOggetto(fileLogs, logs, false);

					break;
				
				case 2: //visualizza riepilogo
					fruitore.stampaFruitore();
					break;	
					
				case 3://sezione libri
					gestioneLibriFruitore();
					break;
					
				case 4://sezione films
					gestioneFilmsFruitore();
					break;
					
				case 5:	//Sezione prestiti
					gestionePrestitiFruitore(fruitore);
					break;
			
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					System.out.println(Costanti.COM_NON_RIC);
					break;
				}
			} 	while(!finito);	
		}
			
	}
	
	/**
	 * SottoMenu' dell'interfaccia fruitore che permette operazioni sui prestiti,contiene:
	 * *chiediPrestito()
	 * *rinnovaPrestito()
	 * *stampaPrestitiUtente()
	 * *annullaPrestitoRisorsa() 
	 */
	private void gestionePrestitiFruitore(Fruitore fruitore) {
		
		MyMenu sottoPrestitiFruitore = new MyMenu(Costanti.COSA_,Costanti.SCELTE_PRESTITO_FRUITORE);{
			boolean finito = false;
					
				do{
					int scelta=sottoPrestitiFruitore.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					
					case 1: //chiedi il prestito
						chiediPrestito(fruitore);
						break;
					
					case 2: //rinnova prestito
						prestiti.rinnovaPrestito(fruitore);
						IOFileUtil.salvaSingoloOggetto(filePrestiti, prestiti, false);
						IOFileUtil.salvaSingoloOggetto(fileLibri, libri, false);
						IOFileUtil.salvaSingoloOggetto(fileFilms, films, false);
				//		IOFileUtil.salvaSingoloOggetto(fileLogs, logs, false);
						break;	
						
					case 3://Visualizza prestiti in corso
						if(prestiti.filtraPrestitiPerUser(fruitore).isEmpty())
							System.out.println("Nessun prestito");
						else
							prestiti.stampaPrestitiUtente(prestiti.filtraPrestitiPerUser(fruitore));
						break;
						
					case 4:	//annulla prestito di libro
						
						if(prestiti.filtraPrestitiPerUser(fruitore).isEmpty())
							System.out.println("Nessun prestito");
						else {
						prestiti.annullaPrestitoRisorsa(fruitore, libri.scegliPerNome(MyUtil.leggiStringaNonVuota(Costanti.ANNULLA_PER_TITOLO)));
						IOFileUtil.salvaSingoloOggetto(filePrestiti, prestiti, false);
						IOFileUtil.salvaSingoloOggetto(fileLibri, libri, false);
					//	IOFileUtil.salvaSingoloOggetto(fileLogs, logs, false);
						}
						break;
						
					case 5://annulla prestito film
						if(prestiti.filtraPrestitiPerUser(fruitore).isEmpty())
							System.out.println("Nessun prestito");
						else {
						
						prestiti.annullaPrestitoRisorsa(fruitore, films.scegliPerNome(MyUtil.leggiStringaNonVuota(Costanti.ANNULLA_PER_TITOLO)));
						IOFileUtil.salvaSingoloOggetto(filePrestiti, prestiti, false);
						IOFileUtil.salvaSingoloOggetto(fileFilms, films, false);
				//		IOFileUtil.salvaSingoloOggetto(fileLogs, logs, false);
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
	 * Scegli che tipo di risorsa chiedi in prestito libro o film e chiedi il prestito
	 */
	private void chiediPrestito(Fruitore fruitore) {
		MyMenu  menuCerca= new MyMenu(Costanti.SCEGLI_PRESTITO, Costanti.SCELTE_PRESTITI); 
		
		boolean finito=false;

		do{
			int scelta=menuCerca.scegli();
			switch(scelta){
			
			case 0: 
				
				finito=true;
				break;
			case 1:
				
				prestiti.chiediPrestitoLibro(fruitore, libri.scegliPerNome(MyUtil.leggiStringaNonVuota(Costanti.PRESTITO_PER_TITOLO)));
				IOFileUtil.salvaSingoloOggetto(filePrestiti, prestiti, false);
				IOFileUtil.salvaSingoloOggetto(fileLibri, libri, false);
		//		IOFileUtil.salvaSingoloOggetto(fileLogs, logs, false);
				break;
			case 2: 
				
				prestiti.chiediPrestitofilm(fruitore, films.scegliPerNome(MyUtil.leggiStringaNonVuota(Costanti.PRESTITO_PER_TITOLO)));
				IOFileUtil.salvaSingoloOggetto(filePrestiti, prestiti, false);
				IOFileUtil.salvaSingoloOggetto(fileFilms, films, false);
		//		IOFileUtil.salvaSingoloOggetto(fileLogs,logs, false);
				break;
			default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
				System.out.println(Costanti.COM_NON_RIC);
				break;
			}
		}while(!finito );
	}
	
	
		
	
	
	
	/**
	 * Menu' lato operatore per la gestione dei prestiti, contiene:
	 * *visualizzaPrestitiAttivi()
	 */
	private void gestioneDatiOperatore() {
		
		MyMenu sottoOperatorePrestiti = new MyMenu(Costanti.GESTISCI_SCEGLI,Costanti.SCELTE_SOTTO_GESTIONE);{
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
					case 2:
						
						LogsDati.getIstance().menuQuery();;
						break;
					case 3:
						LogsDati.getIstance().menuStamapaLogs();
						break;
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
				} 	while(!finito);	
			}
	}
	
	/**
	 * Sotto Menu' che contiene le operazioni sui libri da parte dell'operatore, contiene:
	 * *inerisciLibroInSottoCat()
	 * *removeLibro()
	 * *visualizzaLibri()
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
						
						try {
							inserisciLibroInSottocat();
							IOFileUtil.salvaSingoloOggetto(fileLibri, libri, true);
							LogsDati.getIstance().addLibriPrestabiliInPassato(libri.libriRimossiUniforme());
						//	IOFileUtil.salvaSingoloOggetto(fileLogs, logs, false);
							
							} catch (Exception e){  
								e.printStackTrace();	
								}
						
						break;
					
					case 2: //RIMUOVI LIBRO
					
						try {
							libri.removeLibro(MyUtil.leggiIntero(Costanti.REMOVE_LIBRO));
							IOFileUtil.salvaSingoloOggetto(fileLibri, libri, true);
							LogsDati.getIstance().addLibriPrestabiliInPassato(libri.libriRimossiUniforme());
						//	IOFileUtil.salvaSingoloOggetto(fileLogs, logs, false);
							
							} catch (Exception e){  
								e.printStackTrace();	
								}
						break;	
						
					case 3: //VISUALIZZA
					
						visualizzaLibri();
						break;		
						
						
					case 4:
						libri.cercaLibro();
						break;
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
				} 	while(!finito);	
			}
		
		
		
	}
	
	
	/**
	 * Operazioni di ricerca e visualizzazione di libri lato fruitore
	 */
	private void gestioneLibriFruitore() {
		MyMenu sottoOperatoreLibri = new MyMenu(Costanti.MENU_LIBRI,Costanti.SCELTE_SOTTO_LIBRI_FR);{
			boolean finito = false;
					
				do{
					int scelta=sottoOperatoreLibri.scegli();
				
					switch(scelta){
					
					case 0: //esci
						finito=true;
						break;
						
					case 1: //VISUALIZZA
					
						visualizzaLibri();
						break;		
						
						
					case 2:
						libri.cercaLibro();
						break;
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
				} 	while(!finito);	
			}
	}
	
	/**
	 * Sottomenu' per gestire le operzioni su films, quali: aggiunta, rimozione,ricerca e salvataggio delle operazioni
	 */
	private void gestioneFilms() {
		MyMenu sottoOperatoreLibri = new MyMenu(Costanti.MENU_FILMS,Costanti.SCELTE_SOTTO_FILMS);{
			boolean finito = false;
					
				do{
					int scelta=sottoOperatoreLibri.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					
					case 1: //AGGIUNGI FILM
						
						try {
							inserisciFilmInSottocat();
							IOFileUtil.salvaSingoloOggetto(fileFilms, films, true);
							LogsDati.getIstance().addFilmsPrestabiliInPassato(films.filmsRimossiUniforme());
						//	IOFileUtil.salvaSingoloOggetto(fileLogs, logs, false);
							
							} catch (Exception e){  
								e.printStackTrace();	
								}
						break;
					
					case 2: //RIMUOVI 
						
						try {
								films.removeFilm(MyUtil.leggiIntero(Costanti.REMOVE_FILM));
								IOFileUtil.salvaSingoloOggetto(fileFilms, films, true);
								LogsDati.getIstance().addFilmsPrestabiliInPassato(films.filmsRimossiUniforme());
						//		IOFileUtil.salvaSingoloOggetto(fileLogs, logs, false);
								
								} catch (Exception e){  
									e.printStackTrace();	
									}
	
						break;	
						
					case 3: //VISUALIZZA
							
							films.stampaFilms();
							
						break;		
						
					case 4:
							films.cercaFilm();
						break;
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
				} 	while(!finito);	
			}
		
		
		
	}
	
	/**
	 * Operazioni di riceca e visualizzazione di films lato fruitore
	 */
	private void gestioneFilmsFruitore() {
		MyMenu sottoOperatoreLibri = new MyMenu(Costanti.MENU_FILMS,Costanti.SCELTE_SOTTO_FILMS_FR);{
			boolean finito = false;
					
				do{
					int scelta=sottoOperatoreLibri.scegli();
				
					switch(scelta){
					case 0: //esci
						finito=true;
						break;
					case 1: //VISUALIZZA
							
							films.stampaFilms();
							
						break;		
						
					case 2: //CERCA
							films.cercaFilm();
						break;
					
				
					default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
						System.out.println(Costanti.COM_NON_RIC);
						break;
					}
				} 	while(!finito);	
			}
		
		
		
	}
	
	
	/**
	 * Crea un istanza di film e la aggiunge in una sottoCategoria a scelta
	 */
	private void inserisciFilmInSottocat() {
		
		film= new Film(MyUtil.leggiStringaNonVuota(Costanti.INS_NOME), MyUtil.leggiData(Costanti.INS_DATA_OUT), MyUtil.leggiStringaNonVuota(Costanti.INS_REGISTA),
				       MyUtil.inserisciAttori(), MyUtil.leggiIntero(Costanti.INS_NLIC), MyUtil.leggiIntero(Costanti.INS_CODICE),
				       MyUtil.leggiStringaNonVuota(Costanti.INS_GENERE));
		
		MyMenu sceltaSottoCat = new MyMenu(Costanti.INS_FILM,Costanti.SCELTE_SOTTOCAT);{
			boolean finito = false;
			
			do{
				int scelta=sceltaSottoCat.scegli();
		
				switch(scelta){
				case 0: //esci
					finito=true;
					break;
			
				case 1: // INSERISCI IN  ING
			
					films.addInIng(film);
					finito=true;
					break;
			
				case 2: // INSERISCI IN ITA
			
					films.addInIta(film);
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
	 * Crea una nuova istanza di libro e permette di scegliere in che sotto categoria inserirlo
	 */
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
	
	/**
	 * scegli la sotto categoria di cui vuoi visualizzare i libri 
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
	
	
	
	
	/**
	 * "Ricrea" l'array dei prestiti delle risorse perche' con la ricarica del file non puntano piu' agli stessi oggetti (????)
	 */
	public static void reloadArrayPrestiti(){
		for(int i=0; i<prestiti.getPrestiti().size();i++){
			if(prestiti.getPrestiti().get(i).getRisorsa() instanceof Libro ){
				for(Risorsa libro :libri.getLibriIng().getArrayRisorse()) {
				
					if(prestiti.getPrestiti().get(i).getRisorsa().getCodiceUnivoco()==libro.getCodiceUnivoco())
						prestiti.getPrestiti().get(i).setRisorsa(libro);
					
				}
				for(Risorsa lib :libri.getLibriIta().getArrayRisorse()) {
					
					if(prestiti.getPrestiti().get(i).getRisorsa().getCodiceUnivoco()==lib.getCodiceUnivoco())
						prestiti.getPrestiti().get(i).setRisorsa(lib);
				}
			}
			
			else if(prestiti.getPrestiti().get(i).getRisorsa() instanceof Film){
					for(Risorsa film : films.getFilmsIng().getArrayRisorse()){
						if(prestiti.getPrestiti().get(i).getRisorsa().getCodiceUnivoco()==film.getCodiceUnivoco())
							prestiti.getPrestiti().get(i).setRisorsa(film);
					}
					for(Risorsa fil : films.getFilmsIta().getArrayRisorse()){
						if(prestiti.getPrestiti().get(i).getRisorsa().getCodiceUnivoco()==fil.getCodiceUnivoco())
							prestiti.getPrestiti().get(i).setRisorsa(fil);
					}	
			}
		}
			
	}
	
	
	
	//Getters & Setters
	
	public Fruitore getFruitore() {
		return fruitore;
	}

	public void setFruitore(Fruitore fruitore) {
		this.fruitore = fruitore;
	}

	public ArrayFruitore getFruitori() {
		return fruitori;
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

	public Risorsa getLibro() {
		return libro;
	}


	public ArrayPrestito getPrestiti() {
		return prestiti;
	}
	
	public Risorsa getFilm() {
		return film;
	}

	public Films getFilms() {
		return films;
	}

	public File getFileFilms() {
		return fileFilms;
	}

	
/*
	public SalvataggiStorico getLogs() {
		return logs;
	}

	public File getFileLogs() {
		return fileLogs;
	}

*/	
}


