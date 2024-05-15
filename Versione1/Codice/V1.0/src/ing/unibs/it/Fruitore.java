package ing.unibs.it;


import java.io.Serializable;
import java.util.GregorianCalendar;
import util.Unibs.MyUtil;
/**
 * Classe che identifica l'oggetto fruitore
 * @author Francesco
 *
 */
public class Fruitore implements Serializable {

	//Attributi
	private static final long serialVersionUID = 1L;
	private String  nome ;
	private String cognome;
	private GregorianCalendar  DataDiNascita;
	private String residenza;
	private boolean statoFruitore ;
	private GregorianCalendar dataIscrizione;
	private GregorianCalendar dataRinnovoIscrizione;
	private GregorianCalendar dataScadenzaIscrizione;
	private String  username;
	private String  password;
	
	/**
	 * Costruttore di classe
	 * @param nome il nome
	 * @param cognome il cognome
	 * @param DataDiNascita la data di nascita
	 * @param residenza la residenza
	 * @param username username
	 * @param password password
	 */
 	public  Fruitore (String nome, String cognome, GregorianCalendar DataDiNascita, String residenza, String username, String password) {
		
		this.nome=nome;
		this.cognome=cognome;
		this.DataDiNascita=DataDiNascita;
		this.residenza=residenza;
		dataIscrizione= (GregorianCalendar) GregorianCalendar.getInstance();
		this.username=username;
		this.password=password;
		dataScadenzaIscrizione= CalcoloDataScadenza();
		dataRinnovoIscrizione= CalcoloDataRichiestaRinnovo();
		statoFruitore= true;
		
	}

 	/**
 	 * Calcola la data di scadenza iscrizione
 	 * @return la data di scadenza
 	 */
 	private GregorianCalendar CalcoloDataScadenza() {
 		
 		GregorianCalendar dataScadenza = new GregorianCalendar(dataIscrizione.get(GregorianCalendar.YEAR), dataIscrizione.get(GregorianCalendar.MONTH), dataIscrizione.get(GregorianCalendar.DAY_OF_MONTH));
		dataScadenza.add(GregorianCalendar.YEAR, 5);
		return dataScadenza;
 	}
 	
 	/**
 	 * Calcola la data di inizio richiesta rinnovo
 	 * @return la data di inizio richiesta rinnovo
 	 */
 	private GregorianCalendar CalcoloDataRichiestaRinnovo() {
 		//dataRinnovoIscrizione.set(dataIscrizione.get(GregorianCalendar.YEAR ), dataIscrizione.get(GregorianCalendar.MONTH), dataIscrizione.get(GregorianCalendar.DAY_OF_MONTH+10));
 		GregorianCalendar rinnovo = new GregorianCalendar(dataScadenzaIscrizione.get(GregorianCalendar.YEAR), dataScadenzaIscrizione.get(GregorianCalendar.MONTH), dataScadenzaIscrizione.get(GregorianCalendar.DAY_OF_MONTH));
 		rinnovo.add(GregorianCalendar.DAY_OF_MONTH, -10);
		return rinnovo;
		 
 	 }
 	
 	
 	
 	 /**
 	  *  da informazioni sulla possibilità di eseguire il rinnovo quel giorno
 	  */
 	private boolean statoRinnovo() {
 		
 		GregorianCalendar dataCorrente= (GregorianCalendar) GregorianCalendar.getInstance();
 		ControlloDecadenzaFruitore();
 		if(dataCorrente.compareTo(dataRinnovoIscrizione)==1 && statoFruitore ) return true;
 		else return false;
 	} 
 	 
 	
 	/**
 	 * Rinnova l'iscrizione
 	 */
 	 public void rinnovoIscrizione() {
 		 
 		 if(statoRinnovo()) {
 			 setDataIscrizione((GregorianCalendar) GregorianCalendar.getInstance());
 			 System.out.printf(Costanti.ISCRIZIONE_OK,  MyUtil.toStringData( dataScadenzaIscrizione),MyUtil.toStringData( dataRinnovoIscrizione));
 		 }
 		
 		 else if(statoFruitore && statoRinnovo()==false ) 
 			System.out.printf(Costanti.ISCRIIONE_DOPO, MyUtil.toStringData(dataRinnovoIscrizione),MyUtil.toStringData(dataScadenzaIscrizione));
 		 
 	 }
 	 
 	 
 	 /**
 	  * Controlla se oggi l'iscrizione e'scaduta
 	  */
 	 public void ControlloDecadenzaFruitore() {
 		 
 		GregorianCalendar dataCorrente= (GregorianCalendar) GregorianCalendar.getInstance();
 		if(dataCorrente.compareTo(dataScadenzaIscrizione)==1) setStatoFruitore(false);
 		
 	 }
 	 
 	 /**
 	  * Stampa dati fruitore
 	  */
 	 public void stampaFruitore() {
 		System.out.println(Costanti.STAMPA_NOME + nome);
		System.out.println(Costanti.STAMPA_COGNOME + cognome);
		System.out.println(Costanti.STAMPA_USER + username);
		System.out.println(Costanti.STAMPA_NASCITA + MyUtil.toStringData(DataDiNascita));
		System.out.println(Costanti.STAMPA_ISCRIIONE +  MyUtil.toStringData(dataIscrizione));
		System.out.println(Costanti.STAMPA_RINNOVO + MyUtil.toStringData(dataRinnovoIscrizione));
		System.out.println(Costanti.STAMPA_SCADENZA +  MyUtil.toStringData(dataScadenzaIscrizione));
 	 }
 	 
 	 

 	 
 	 
 	 
 	 
 	 //Getters & Setters
	public GregorianCalendar getDataRinnovoIscrizione() {
		return dataRinnovoIscrizione;
	}

	public void setDataRinnovoIscrizione(GregorianCalendar dataRinnovoIscrizione) {
		this.dataRinnovoIscrizione = dataRinnovoIscrizione;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public GregorianCalendar getDataDiNascita() {
		return DataDiNascita;
	}

	public void setDataDiNascita(GregorianCalendar DataDiNascita) {
		this.DataDiNascita = DataDiNascita;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public boolean isStatoFruitore() {
		return statoFruitore;
	}

	public void setStatoFruitore(boolean statoFruitore) {
		this.statoFruitore = statoFruitore;
	}

	public GregorianCalendar getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(GregorianCalendar dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}

	public GregorianCalendar getDataScadenzaIscrizione() {
		return dataScadenzaIscrizione;
	}

	public void setDataScadenzaIscrizione(GregorianCalendar dataScadenzaIscrizione) {
		this.dataScadenzaIscrizione = dataScadenzaIscrizione;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	

}
