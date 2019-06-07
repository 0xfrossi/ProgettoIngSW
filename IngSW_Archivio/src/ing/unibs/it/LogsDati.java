package ing.unibs.it;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import util.Unibs.MyMenu;
import util.Unibs.MyUtil;


/**
 * Classe che contine dati sui dati storici del sistema, costruita con Singleton
 * @author Francesco Rossi
 *
 */
public class LogsDati implements Serializable{

	//Attributi 
	
	private static final long serialVersionUID = 1L;
	private static LogsDati istance =null;
	private static ArrayList<Fruitore>  fruitori;
	private static ArrayList<Fruitore> fruitoriScaduti;
	private static ArrayList<Fruitore> fruitoriRinnovati;
	private static ArrayList<Risorsa> risorsePrestabiliInPassato;
	private static ArrayList<Prestito> logsPrestiti;
	private static ArrayList<Prestito> prorogati;
	private static ArrayList<Prestito> prestitiTerminati;
	
	
	/**
	 * Costruttore, inzializza tutti gli arrayList
	 */
	private LogsDati() {
		
		fruitoriScaduti= new ArrayList<Fruitore>();
		fruitoriRinnovati= new ArrayList<Fruitore>();
		risorsePrestabiliInPassato= new ArrayList<Risorsa>();
		logsPrestiti= new ArrayList<Prestito>();
		prorogati= new ArrayList<Prestito>();
		prestitiTerminati= new ArrayList<Prestito>();
	
	}
	
	/**
	 * Crea l'unica istanza possibile
	 * @return istance,  unica istanza dell'oggetto
	 */
	public static LogsDati getIstance() {
        if(istance==null)
                istance = new LogsDati();
        return istance;
}
	
	
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param fruitore
	 */
	public void addFruitoriScaduti(Fruitore fruitore) {
		fruitoriScaduti.add(fruitore);
	}
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param prestito
	 */
	public  void addPrestitiTerminati(Prestito prestito) {
		prestitiTerminati.add(prestito);
	}
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param fruitore
	 */
	public  void addInFruitori(Fruitore fruitore) {
		fruitori.add(fruitore);
	}
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param fruitore
	 */
	public  void addInRinnovati(Fruitore fruitore) {
		fruitoriRinnovati.add(fruitore);
	}
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param prestito
	 */
	public  void addInlogsPrestiti(Prestito prestito) {
		logsPrestiti.add(prestito);
	}
	/**
	 * Aggiunge un elemento nell'arrayList 
	 * @param prestito
	 */
	public  void addInProrogati(Prestito prorogato) {
		prorogati.add(prorogato);
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
	private void numPrestitiFruitoreInAnno(String userName, int annoRif){
	
		int count=0;
		for(int i=0;i< logsPrestiti.size();i++){
			if(logsPrestiti.get(i).getFruitore().getUsername().equals(userName)&& logsPrestiti.get(i).getDataInizioPrestito().get(GregorianCalendar.YEAR)== annoRif)
				count++;
			
		}
		System.out.printf( userName+" ha totalizzato  %n prestiti nell'anno %n /n",count,annoRif);
	}
	
	/**
	 * torna tutti i prestiti in un certo anno avvenuti nel sistema
	 * @param annoRiferimento l'anno del quale sapere il num
	 * @return l'array di prestiti
	 */
	private void prestitiInAnno(int annoRiferimento){
		
		
		ArrayList<Prestito> inAnno = new ArrayList<>();
		for (int i=0;i< logsPrestiti.size();i++) {
			if(logsPrestiti.get(i).getDataInizioPrestito().get(GregorianCalendar.YEAR) == annoRiferimento)
				inAnno.add(logsPrestiti.get(i));	
		}
		if(inAnno.isEmpty())
			System.out.println("Nessun dato trovato");			
		else
			
			for( int i=0; i< inAnno.size();i++) {
				inAnno.get(i).stampaPrestito();
			}
			
			
	}
	
	/**
	 * Calcola in numero di prestiti in un certo anno avvenuti nel sistema
	 * @param annoRiferimento l'anno del quale sapere il num
	 * @return il numero di prestiti
	 */
	private void numPrestitiInAnnoSolare(int annoRiferimento){
		
		int count=0;
		for(int i=0; i< logsPrestiti.size();i++) {
			if( logsPrestiti.get(i).getDataInizioPrestito().get(GregorianCalendar.YEAR)==annoRiferimento) 
				count++;
		}
		System.out.printf( "Nell'anno %n sono stati registrati %n prestiti /n",annoRiferimento,count);
	}
	
	/**
	 * Calcola in numero di proroghe in un certo anno avvenuti nel sistema
	 * @param annoRiferimento l'anno del quale sapere il num
	 * @return il numero di proroghe
	 */
	private void  prorogheAnnoSolare(int annoRiferimento) {
		
		int count=0;
		for(int i=0; i<prorogati.size();i++) {
			if(prorogati.get(i).getDataFinePrestito().get(GregorianCalendar.YEAR)==annoRiferimento) 
				count++;
			
			
		}
		System.out.printf( "Nell'anno %n sono state realizzate %n proroghe /n", annoRiferimento , count);
	}
	
	/**
	 * Visualizza la risorsa piu' richiesta in un anno 
	 * @param annoRiferimento l'anno nel quale fare la statistica
	 */
	public void risorsaPiuInPrestito(int annoRiferimento){
		int maxTotale = 0;
		String titoloRisorsaMax = null;
		ArrayList<Risorsa> risorseTemp = new ArrayList<Risorsa>();
		
		for (Prestito prestito : logsPrestiti){
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
			System.out.printf("La risorsa piu' richista nell'anno specificato e' %s, %n volte /n",titoloRisorsaMax, maxTotale);
		
	}
	
	private void stampaFruitori() {
		if(!fruitori.isEmpty()) {	
			for(int i=0; i<fruitori.size();i++) {
				fruitori.get(i).stampaFruitore();	
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
		
	
	private void stampaRinnovati() {
	
		if(!fruitoriRinnovati.isEmpty()) {	
			for(int i=0; i<fruitoriRinnovati.size();i++) {
				fruitoriRinnovati.get(i).stampaFruitore();	
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	private void stampaScaduti() {
		
		if(!fruitoriScaduti.isEmpty()) {	
			for(int i=0; i<fruitoriScaduti.size();i++) {
				fruitoriScaduti.get(i).stampaFruitore();	
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	
	private  void stampaPrestabiliInPassato() {
		
		if(!risorsePrestabiliInPassato.isEmpty()) {	
			for(int i=0; i<risorsePrestabiliInPassato.size();i++) {
				risorsePrestabiliInPassato.get(i).stampaDesc();	
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	private void stampaPrestiti() {
		if(!logsPrestiti.isEmpty()) {	
			for(int i=0; i<logsPrestiti.size();i++) {
				logsPrestiti.get(i).stampaPrestito();	
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	private void stampaProrogati() {
		
		if(!prorogati.isEmpty()) {	
			for(int i=0; i<prorogati.size();i++) {
				prorogati.get(i).stampaPrestito();	
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	private void stampaPrestitiTerminati() {
		
		if(!prestitiTerminati.isEmpty()) {	
			for(int i=0; i<prestitiTerminati.size();i++) {
				prestitiTerminati.get(i).stampaPrestito();	
			}
		}
		else System.out.println("Nessun dato salvato ");
	}
	
	

	
	/**
	 * aggiungi  le risorse non piu' prestabili, cioe' quelle rimosse, in un array per lo storico
	 * @param risorseRimosse array di rimossi
	 */
	public void addLibriPrestabiliInPassato(ArrayList<Risorsa> libriRimossi){
	
		if(!libriRimossi.isEmpty()) {
			for(int i=0; i< libriRimossi.size();i++) 
				risorsePrestabiliInPassato.add(libriRimossi.get(i));
			
		}
	}
	
	/**
	 * aggiungi  le risorse non piu' prestabili, cioe' quelle rimosse, in un array per lo storico
	 * @param risorseRimosse array di rimossi
	 */
	public void addFilmsPrestabiliInPassato(ArrayList<Risorsa> filmsRimossi){
		if(!filmsRimossi.isEmpty()) {
			for(int i=0; i<filmsRimossi.size();i++) {
				risorsePrestabiliInPassato.add(filmsRimossi.get(i));
			}
		}	
		
	}
	
	
	
	
	
	/**
	 * Lista di fruitori decaduti
	 * @return array di decaduti
	 */
/*	public ArrayList<Fruitore> logsFruitoriDecaduti()
	{
		bool);ean in=false;
		ArrayList<Fruitore> decaduti = new ArrayList<>();
		
		for (int i = 0; i < fruitori.size(); i++) {
			if(!fruitori.get(i).getStatoFruitore()){
				decaduti.add(fruitori.get(i));
				in=true;
			}
		}
		if(in)
			return decaduti;
		else
			return null;
	}
	
	
	/**
	 * Stampa la lista dei decaduti
	 */
/*	public void stampaDecaduti() {
		if(!logsFruitoriDecaduti().isEmpty()) {
			for(int i=0; i< logsFruitoriDecaduti().size();i++) {
				System.out.println("Ecco i fruitori decaduti: ");
				System.out.println(logsFruitoriDecaduti().get(i).getUsername());
			}		
		}
		else
			System.out.println("nessun fruitore decaduto ");
	}
	
	
	/**
	 * Visualizza il numero di fruitori che hanno rinnovato il servizio
	 * @return
	 */
/*	public ArrayList<String> logsFruitoriRinnovati(){
		
		boolean in=false;
		ArrayList<String> fruitoriRinnovati = new ArrayList<>();
		for (int i = 0; i < fruitori.size(); i++){ 
		
			if(fruitori.get(i).getCountRinnovi()!=0 && fruitori.get(i).getStatoFruitore())
				in=true;
				fruitoriRinnovati.add(fruitori.get(i).getUsername());
		}
		
		if(in ==false) {
			System.out.println("nessun fruitore rinnovato ");
			return null;
		}
		else {
			for (int i = 0; i < fruitoriRinnovati.size(); i++){ 
				
				System.out.println("fruitor rinnovati: ");
				System.out.println(fruitoriRinnovati.get(i));
				
			}
			return fruitoriRinnovati;
		}
	}
	
	
	/*
	public ArrayList<Risorsa> filmsPrestabiliInPassato(){
		ArrayList<Risorsa> prestabili = new ArrayList<>();
		ArrayList<Risorsa> rFilms= films.filmsUniforme();
				
		for(int i=0;i<rFilms.size();i++){
			if( !rFilms.get(i).getPrestabile())
				prestabili.add(rFilms.get(i));
		}
		if(prestabili.isEmpty())
			return null;
		
		else
			return prestabili;	
	}
*/
	
	/**
	 * Torna un array dei prestiti prorogti dall'inizio
	 * @return array di prestiti
	 */
/*	public void prestitiProrogati()
	{
		boolean in=false;
		ArrayList<Prestito> prorogati = new ArrayList<>();
		
		for (int i = 0; i < prestiti.getPrestiti().size(); i++) {
			if(prestiti.getPrestiti().get(i).getProrogaOk()){
				in=true;
	//			prorogati.add(prestiti.getPrestiti().get(i));
				System.out.printf("Nome risorsa: %s -- fruitore: %s /n",prestiti.getPrestiti().get(i).getRisorsa().getNome(),prestiti.getPrestiti().get(i).getFruitore().getUsername());
			}
		}
		if(in==false)
			System.out.println("Nessuna proroga per adesso ");
		/*else
			return prorogati; */	
	
	
	
/*	public void prestitiTerminati(){
		boolean  in=false;
		ArrayList<Prestito> finiti = new ArrayList();
		for (int i = 0; i < prestiti.getPrestiti().size(); i++) {
			if(prestiti.getPrestiti().get(i).isPrestitoFinito()){
				in=true;
				System.out.printf("Nome risorsa: %s -- fruitore: %s /n",prestiti.getPrestiti().get(i).getRisorsa().getNome(),prestiti.getPrestiti().get(i).getFruitore().getUsername());
				
	//			finiti.add(prestiti.getPrestiti().get(i));
			}
		}
		if(in == false)
			System.out.printf("Nessun prestito terminato");
		
		
	}
	
	
*/

	public ArrayList<Fruitore> getFruitoriScaduti() {
		return fruitoriScaduti;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static ArrayList<Fruitore> getFruitori() {
		return fruitori;
	}
	public static ArrayList<Fruitore> getFruitoriRinnovati() {
		return fruitoriRinnovati;
	}
	public static ArrayList<Risorsa> getRisorsePrestabiliInPassato() {
		return risorsePrestabiliInPassato;
	}
	public static ArrayList<Prestito> getLogsPrestiti() {
		return logsPrestiti;
	}
	public static ArrayList<Prestito> getProrogati() {
		return prorogati;
	}
	public static ArrayList<Prestito> getPrestitiTerminati() {
		return prestitiTerminati;
	}
	
	
}
