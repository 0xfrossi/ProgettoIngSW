package ing.unibs.it;


import java.io.Serializable;
import java.util.GregorianCalendar;
import util.Unibs.MyUtil;

public class Fruitore implements Serializable {

	
	
	//Dichiarazione attributi
	
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
	 * Costruttore
	 * @param nome il nome del fruitore
	 * @param cognome il cognome del fruitore
	 * @param DataDiNascita la data di nascita del fruitore
	 * @param residenza luogo di residenza 
	 * @param username username per l'accesso
	 * @param password la password per accedere con @ username
	 *  imposta dataScadenzaIscrizione tramite CalcoloDataScadenza
	 *  imposta dataRinnovoIscrizione tramite   CalcoloDataRichiestaRinnovo
	 */
 	public  Fruitore (String nome, String cognome, GregorianCalendar DataDiNascita, String residenza, String username, String password) {
		
		this.nome=nome;
		this.cognome=cognome;
		this.DataDiNascita=DataDiNascita;
		this.residenza=residenza;
		this.dataIscrizione= (GregorianCalendar) GregorianCalendar.getInstance();
		this.username=username;
		this.password=password;
		this.dataScadenzaIscrizione= CalcoloDataScadenza();
		this.dataRinnovoIscrizione= CalcoloDataRichiestaRinnovo();
		this.statoFruitore= true;
	}

 	
 	
 	/**
 	 * Calcola in base alla data odierna(data di iscrizione) la data di scadenza, tra 5 anni da oggi
 	 * @return dataScadenza
 	 */
 	private GregorianCalendar CalcoloDataScadenza() {
 		//dataScadenzaIscrizione.set(dataIscrizione.get(GregorianCalendar.YEAR +5), dataIscrizione.get(GregorianCalendar.MONTH), dataIscrizione.get(GregorianCalendar.DAY_OF_MONTH));
 		
 		GregorianCalendar dataScadenza = new GregorianCalendar(dataIscrizione.get(GregorianCalendar.YEAR), dataIscrizione.get(GregorianCalendar.MONTH), dataIscrizione.get(GregorianCalendar.DAY_OF_MONTH));
		dataScadenza.add(GregorianCalendar.YEAR, 5);
		return dataScadenza;
 	}
 	
 	/**
 	 * calcola la data dalla quale e'possiblie effettuare il rinnovo del titolo di fruitore
 	 * @return rinnovo
 	 */
 	private GregorianCalendar CalcoloDataRichiestaRinnovo() {
 		//dataRinnovoIscrizione.set(dataIscrizione.get(GregorianCalendar.YEAR ), dataIscrizione.get(GregorianCalendar.MONTH), dataIscrizione.get(GregorianCalendar.DAY_OF_MONTH+10));
 		GregorianCalendar rinnovo = new GregorianCalendar(dataScadenzaIscrizione.get(GregorianCalendar.YEAR), dataScadenzaIscrizione.get(GregorianCalendar.MONTH), dataScadenzaIscrizione.get(GregorianCalendar.DAY_OF_MONTH));
 		rinnovo.add(GregorianCalendar.DAY_OF_MONTH, -10);
		return rinnovo;
		 
 	 }
 	
 	
 	
 	 /**
 	  * Mi da informazioni sulla possibilita' di eseguire il rinnovo quel giorno
 	  */
 	public boolean statoRinnovo() {
 		
 		GregorianCalendar dataCorrente= (GregorianCalendar) GregorianCalendar.getInstance();
 		//ControlloDecadenzaFruitore();
 		if(dataCorrente.compareTo(dataRinnovoIscrizione)==1  )
 			return true;
 		else 
 			return false;
 	} 
 	 
 	
 	/**
 	 * rinnova l'iscrizione se possibile
 	 */
 	 public void rinnovoIscrizione() {
 		 
 		 if(statoRinnovo()) {
 			 setDataIscrizione((GregorianCalendar) GregorianCalendar.getInstance());
 
 			 System.out.printf(Costanti.ISCRIZIONE_OK,  MyUtil.toStringData( dataScadenzaIscrizione),MyUtil.toStringData( dataRinnovoIscrizione));
 		 }
 		 
 		 else 
 			 System.out.printf(Costanti.ISCRIIONE_DOPO, MyUtil.toStringData(dataRinnovoIscrizione),MyUtil.toStringData(dataScadenzaIscrizione));
 		 /*
 		 else if (!statoFruitore)
 			 	System.out.println(Costanti.ISCRIZIONE_DECADUTA);
 		 
 		 else if(statoFruitore && statoRinnovo()==true ) 
 			System.out.printf(Costanti.ISCRIIONE_DOPO, MyUtil.toStringData(dataRinnovoIscrizione),MyUtil.toStringData(dataScadenzaIscrizione));
 		*/ 
 	 }
 	 
 	 
 	 /**
 	  * Controlla se il titolo di fruitore e' scaduto
 	  */
 	 public void ControlloDecadenzaFruitore() {
 		 
 		GregorianCalendar dataCorrente= (GregorianCalendar) GregorianCalendar.getInstance();
 		if(dataCorrente.compareTo(dataScadenzaIscrizione)==1) 
 			setStatoFruitore(false);
 		
 	 }
 	 
 	 
 	 /**
 	  * Stampa i dati del fruitotore
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
 	 
 	 

 	 
 	 
 	 
 	 // GETTERS & SETTERS

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

	public boolean getStatoFruitore() {
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
	/*
	public boolean getIscrizioneDecaduta() {
		return iscrizioneDecaduta;
	}

	public void setIscrizioneDecaduta(boolean iscrizioneDecaduta) {
		this.iscrizioneDecaduta = iscrizioneDecaduta;
	}
	*/
	

}
