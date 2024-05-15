package model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import myUtil.MyUtil;


/**
 * Indetifica un fruitore
 * @author Francesco
 *
 */
public class FruitoreModel implements Serializable {
	
		
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
	 	public  FruitoreModel (String nome, String cognome, GregorianCalendar DataDiNascita, String residenza, String username, String password) {
			
			this.nome=nome;
			this.cognome=cognome;
			this.DataDiNascita=DataDiNascita;
			this.residenza=residenza;
			this.dataIscrizione= MyUtil.dataCorrente();
			this.username=username;
			this.password=password;
			this.dataScadenzaIscrizione= CalcoloDataScadenza(dataIscrizione);
			this.dataRinnovoIscrizione= CalcoloDataRichiestaRinnovo(dataScadenzaIscrizione);
			this.statoFruitore= true;
		}

	 	
	 	/**
	 	 * Calcola in base alla data odierna(data di iscrizione) la data di scadenza, tra 5 anni da oggi
	 	 * @param data la data
	 	 * @return dataScadenza
	 	 */
	 	public GregorianCalendar CalcoloDataScadenza(GregorianCalendar data) {
	 		
	 		GregorianCalendar dataScadenza = new GregorianCalendar(data.get(GregorianCalendar.YEAR), data.get(GregorianCalendar.MONTH), data.get(GregorianCalendar.DAY_OF_MONTH));
			dataScadenza.add(GregorianCalendar.YEAR, 5);
			return dataScadenza;
	 	}
	 	
	 	/**
	 	 * calcola la data dalla quale e'possiblie effettuare il rinnovo del titolo di fruitore
	 	 * @param data la data
	 	 * @return rinnovo
	 	 */
	 	public GregorianCalendar CalcoloDataRichiestaRinnovo(GregorianCalendar data) {
	 		
	 		GregorianCalendar rinnovo = new GregorianCalendar(data.get(GregorianCalendar.YEAR), data.get(GregorianCalendar.MONTH), data.get(GregorianCalendar.DAY_OF_MONTH));
	 		rinnovo.add(GregorianCalendar.DAY_OF_MONTH, -10);
			return rinnovo;
			 
	 	 }
	 	
	 	
	 	
	 	 /**
	 	  * Mi da' informazioni sulla possibilita' di eseguire il rinnovo quel giorno
	 	  * @return stato
	 	  */
	 	public boolean statoRinnovo() {
	 		
	 		GregorianCalendar dataCorrente= MyUtil.dataCorrente();
	 		
	 		if(dataCorrente.compareTo(dataRinnovoIscrizione)==1  )
	 			return true;
	 		else 
	 			return false;
	 	} 
	 	 
	 	
	 	/**
	 	 * rinnova l'iscrizione
	 	 */
	 	 public void rinnovoIscrizione() {
	 		 
	 		setDataIscrizione((GregorianCalendar) GregorianCalendar.getInstance());
	 			 	
	 	 }
	 	 
	 	 
	 	 
	 	 /**
	 	  * Controlla se il titolo di fruitore e' scaduto
	 	  */
	 	 public void ControlloDecadenzaFruitore() {
	 		 
	 		GregorianCalendar dataCorrente= MyUtil.dataCorrente();
	 		if(dataCorrente.compareTo(dataScadenzaIscrizione)==1) 
	 			setStatoFruitore(false);
	 		
	 	 }
	 	 
	 	 
	 	 public String toString() {
	 		 
	 		 StringBuilder str= new StringBuilder();
	 		 str.append("Nome: "+ nome+ "\n");
	 		 str.append("Cognome: "+ cognome + "\n");
	 		 str.append("Residenza: "+ residenza + "\n");
	 		 str.append("UserName: "+ username + "\n");
	 		 str.append("Data di nascita: "+ MyUtil.toStringData(DataDiNascita) + "\n");
	 		 str.append("Data di iscrizione: "+ MyUtil.toStringData(dataIscrizione) + "\n");
	 		 str.append("Data rinnovo iscrizione: "+ MyUtil.toStringData(dataRinnovoIscrizione) + "\n");
	 		 str.append("Data scadenza iscrizione: "+ MyUtil.toStringData(dataScadenzaIscrizione) + "\n");
	 		 
			return str.toString();
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
		
		

}


