package ing.unibs.it;



import java.util.ArrayList;
import java.util.GregorianCalendar;

import java.io.File;

import util.Unibs.IOFileUtil;
import util.Unibs.MyMenu;
import util.Unibs.MyUtil;


/**
 * Classe che contine dati sui dati storici del sistema, costruita con Singleton
 * @author Francesco Rossi
 *
 */
public class LogsDati {

	//Attributi 
	
	//private static final long serialVersionUID = 1L;
	private static LogsDati istance ;
	private File fileLog;
	private SalvataggiStorico storico;
	
	//private static SalvataggiStorico dati= new SalvataggiStorico();
	
	
	/**
	 * Costruttore, inzializza tutti gli arrayList
	 */
	private LogsDati() {
		
		storico= new SalvataggiStorico();
		fileLog= new File("Storico.txt");
		
		try {
			
		IOFileUtil.checkFile(fileLog, storico);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		storico=(SalvataggiStorico) IOFileUtil.caricaSingoloOggetto(fileLog);
		
	}
	
	/**
	 * Crea l'unica istanza possibile
	 * @return istance,  unica istanza dell'oggetto
	 */
	public static LogsDati getIstance() {
        if(istance==null) {
                istance = new LogsDati();
        }
        return istance;
}
	
	
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param fruitore da aggiungere
	 */
	public  void addFruitoriScaduti(Fruitore fruitore) {
		storico.getFruitoriScaduti().add(fruitore);
		IOFileUtil.salvaSingoloOggetto(fileLog, storico, false);
	}
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param prestito da aggiungere
	 */
	public  void addPrestitiTerminati(Prestito prestito) {
		storico.getPrestitiTerminati().add(prestito);
		IOFileUtil.salvaSingoloOggetto(fileLog, storico, false);
	}
	
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param fruitore da aggiungere
	 */
	public  void addInFruitori(Fruitore fruitore) {
		storico.getFruitori().add(fruitore);
		IOFileUtil.salvaSingoloOggetto(fileLog, storico, false);
	}
	
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param fruitore da aggiungere
	 */
	public  void addInRinnovati(Fruitore fruitore) {
		storico.getFruitoriRinnovati().add(fruitore);
		IOFileUtil.salvaSingoloOggetto(fileLog, storico, false);
	}
	
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param prestito da aggiungere
	 */
	public  void addInlogsPrestiti(Prestito prestito) {
		storico.getLogsPrestiti().add(prestito);
		IOFileUtil.salvaSingoloOggetto(fileLog, storico, false);
	}
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param prorogato il prestito
	 */
	public   void addInProrogati(Prestito prorogato) {
		storico.getProrogati().add(prorogato);
		IOFileUtil.salvaSingoloOggetto(fileLog, storico, false);
	}
	

	/**
	 * aggiungi  le risorse non piu' prestabili, cioe' quelle rimosse, in un array per lo storico
	 * @param libriRimossi array di rimossi
	 */
	public  void addLibriPrestabiliInPassato(ArrayList<Risorsa> libriRimossi){
	
		if(!libriRimossi.isEmpty()) {
			for(int i=0; i< libriRimossi.size();i++) 
				storico.getRisorsePrestabiliInPassato().add(libriRimossi.get(i));	
		
		IOFileUtil.salvaSingoloOggetto(fileLog, storico, false);
		}
	}
	
	/**
	 * aggiungi  le risorse non piu' prestabili, cioe' quelle rimosse, in un array per lo storico
	 * @param filmsRimossi array di rimossi
	 */
	public  void addFilmsPrestabiliInPassato(ArrayList<Risorsa> filmsRimossi){
		if(!filmsRimossi.isEmpty()) {
			for(int i=0; i<filmsRimossi.size();i++) {
				storico.getRisorsePrestabiliInPassato().add(filmsRimossi.get(i));
			}
			IOFileUtil.salvaSingoloOggetto(fileLog, storico, false);
		}	
		
	}

	
	
	
	

	/**
	 * Menu' che contiene tutti i metodi per stampare i dati storici
	 */
	public void menuStamapaLogs() {
		
		MyMenu sceltaStampa= new MyMenu(Costanti.SCELTA_LOG,Costanti.SCELTE_STAMPA_LOGS);{
			boolean finito = false;
			
			do{
				int scelta=sceltaStampa.scegli();
		
				switch(scelta){
				case 0: //esci
					finito=true;
					break;
			
				case 1: 
					stampaScaduti();
					break;
			
				case 2: 
					stampaRinnovati();
					
					break;
				case 3: 
					stampaFruitori();
					
					break;
				case 4: 
					stampaPrestabiliInPassato();
					break;
					
				case 5: 
					stampaPrestiti();
					break;
					
				case 6: 
					stampaProrogati();
					break;
					
				case 7:
					stampaPrestitiTerminati();
					break;
					
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					System.out.println(Costanti.COM_NON_RIC);
					break;
				}
			} 	while(!finito);	
		}
		
	} 
	
	/**
	 * Menu' contenente metodi di interrogazione allo storico 
	 */
	public void menuQuery() {
		
		MyMenu sceltaStampa= new MyMenu(Costanti.SCELTA_LOG,Costanti.SCELTE_STAMPA_DATI);{
			boolean finito = false;
			
			do{
				int scelta=sceltaStampa.scegli();
		
				switch(scelta){
				case 0: //esci
					finito=true;
					break;
			
				case 1: 
					numPrestitiFruitoreInAnno(MyUtil.leggiStringaNonVuota(Costanti.INS_USER), MyUtil.leggiInteroConMinimo(Costanti.INS_ANNO_RIF, 2000));
					break;
			
				case 2: 
					prestitiInAnno( MyUtil.leggiInteroConMinimo(Costanti.INS_ANNO_RIF, 2000));
					
					break;
				case 3: 
					numPrestitiInAnnoSolare( MyUtil.leggiInteroConMinimo(Costanti.INS_ANNO_RIF, 2000));
			
					break;
				case 4: 
					prorogheAnnoSolare( MyUtil.leggiInteroConMinimo(Costanti.INS_ANNO_RIF, 2000));
					break;
					
				case 5: 
					risorsaPiuInPrestito( MyUtil.leggiInteroConMinimo(Costanti.INS_ANNO_RIF, 2000));
					break;
		
				default: //in caso si inserisca un valore non riconosciuto (teoricamente mai applicato)
					System.out.println(Costanti.COM_NON_RIC);
					break;
				}
			} 	while(!finito);	
		}
	}
	
	
	
	/**
	 * calcola in numero di prestiti di un utente per qualsiasi risorsa
	 * @param userName l'userName dell utente 
	 * @return count il numero totale dei prestiti
	 */
	private  void numPrestitiFruitoreInAnno(String userName, int annoRif){
	
		int count=0;
		for(int i=0;i< storico.getLogsPrestiti().size();i++){
			if(storico.getLogsPrestiti().get(i).getFruitore().getUsername().equals(userName)&& storico.getLogsPrestiti().get(i).getDataInizioPrestito().get(GregorianCalendar.YEAR)== annoRif)
				count++;
			
		}
		System.out.printf( userName + " ha totalizzato "+ count+ " prestiti nell'anno" + "\n");
	}
	
	/**
	 * torna tutti i prestiti in un certo anno avvenuti nel sistema
	 * @param annoRiferimento l'anno del quale sapere il num
	 * @return l'array di prestiti
	 */
	private  void prestitiInAnno(int annoRiferimento){
		
		
		ArrayList<Prestito> inAnno = new ArrayList<>();
		for (int i=0;i< storico.getLogsPrestiti().size();i++) {
			if(storico.getLogsPrestiti().get(i).getDataInizioPrestito().get(GregorianCalendar.YEAR) == annoRiferimento)
				inAnno.add(storico.getLogsPrestiti().get(i));	
		}
		if(inAnno.isEmpty())
			System.out.println("Nessun dato trovato");			
		else
			
			for( int i=0; i< inAnno.size();i++) {
				inAnno.get(i).stampaPrestito();
				System.out.println();
			}
			
			
	}
	
	/**
	 * Calcola in numero di prestiti in un certo anno avvenuti nel sistema
	 * @param annoRiferimento l'anno del quale sapere il num
	 * @return il numero di prestiti
	 */
	private  void numPrestitiInAnnoSolare(int annoRiferimento){
		
		int count=0;
		for(int i=0; i< storico.getLogsPrestiti().size();i++) {
			if( storico.getLogsPrestiti().get(i).getDataInizioPrestito().get(GregorianCalendar.YEAR)==annoRiferimento) 
				count++;
		}
		System.out.printf( "Nell'anno  sono stati registrati "+ count + " prestiti "+" \n");
	}
	
	/**
	 * Calcola in numero di proroghe in un certo anno avvenuti nel sistema
	 * @param annoRiferimento l'anno del quale sapere il num
	 * @return il numero di proroghe
	 */
	private  void  prorogheAnnoSolare(int annoRiferimento) {
		
		int count=0;
		for(int i=0; i<storico.getProrogati().size();i++) {
			if(storico.getProrogati().get(i).getDataFinePrestito().get(GregorianCalendar.YEAR)==annoRiferimento) 
				count++;
			
			
		}
		System.out.printf( "Nell'anno  sono state realizzate " +count+ " proroghe"+ "\n");
	} 
	
	/**
	 * Visualizza la risorsa piu' richiesta in un anno 
	 * @param annoRiferimento l'anno nel quale fare la statistica
	 */
	public  void risorsaPiuInPrestito(int annoRiferimento){
		
		int maxTotale = 0;
		String titoloRisorsaMax = null;
		ArrayList<Risorsa> risorseTemp = new ArrayList<Risorsa>();
		
		for (Prestito prestito : storico.getLogsPrestiti()){
			if(prestito.getDataInizioPrestito().get(GregorianCalendar.YEAR) == annoRiferimento)
				risorseTemp.add(prestito.getRisorsa());	
		}
		
		for(int i = 0; i < risorseTemp.size(); i++){
			int maxRisorsa = 1;
			int codice = risorseTemp.get(i).getCodiceUnivoco();
			
			for (int j = risorseTemp.size()-1; j > i; j--) {
				if(codice==risorseTemp.get(j).getCodiceUnivoco()){
					maxRisorsa++;
					risorseTemp.remove(j);
				}
			}
			if(maxRisorsa > maxTotale) {
				maxTotale = maxRisorsa;
				titoloRisorsaMax = risorseTemp.get(i).getNome();
			}
		}
		if(maxTotale==0)
			System.out.println("Nessuna risorsa chiesta nell'anno ");	
		
		else
			System.out.printf("La risorsa piu' richista nell'anno specificato e' "+ titoloRisorsaMax +", " +maxTotale + " volte" +"\n");
		
	} 
	
	private void stampaFruitori() {
		if(!storico.getFruitori().isEmpty()) {	
			for(int i=0; i<storico.getFruitori().size();i++) {
				storico.getFruitori().get(i).stampaFruitore();	
				System.out.println();
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
		
	
	private void stampaRinnovati() {
	
		if(!storico.getFruitoriRinnovati().isEmpty()) {	
			for(int i=0; i<storico.getFruitoriRinnovati().size();i++) {
				storico.getFruitoriRinnovati().get(i).stampaFruitore();	
				System.out.println();
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	/**
	 * Stampa fruitori scaduti
	 */
	private void stampaScaduti() {
		
		if(!storico.getFruitoriScaduti().isEmpty()) {	
			for(int i=0; i<storico.getFruitoriScaduti().size();i++) {
				storico.getFruitoriScaduti().get(i).stampaFruitore();	
				System.out.println();
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	/**
	 * Stampa risorse prestabili in passato
	 */
	private  void stampaPrestabiliInPassato() {
		
		if(!storico.getRisorsePrestabiliInPassato().isEmpty()) {	
			for(int i=0; i<storico.getRisorsePrestabiliInPassato().size();i++) {
				storico.getRisorsePrestabiliInPassato().get(i).stampaDesc();	
				System.out.println();
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	
	/**
	 * Stampa prestiti passati
	 */
	private void stampaPrestiti() {
		if(!storico.getLogsPrestiti().isEmpty()) {	
			for(int i=0; i<storico.getLogsPrestiti().size();i++) {
				storico.getLogsPrestiti().get(i).stampaPrestito();	
				System.out.println();
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	/**
	 * Stampa prestiti prorogati
	 */
	private void stampaProrogati() {
		
		if(!storico.getProrogati().isEmpty()) {	
			for(int i=0; i<storico.getProrogati().size();i++) {
				storico.getProrogati().get(i).stampaPrestito();	
				System.out.println();
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	/**
	 * Stampa prestiti finiti
	 */
	private void stampaPrestitiTerminati() {
		
		if(!storico.getPrestitiTerminati().isEmpty()) {	
			for(int i=0; i<storico.getPrestitiTerminati().size();i++) {
				storico.getPrestitiTerminati().get(i).stampaPrestito();	
				System.out.println();
			}
		}
		else System.out.println("Nessun dato salvato ");
	}

	
}
