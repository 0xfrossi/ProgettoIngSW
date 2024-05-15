package model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import interfaces.Loanable;
import interfaces.Risorsa;
import myUtil.MyUtil;



/**
 * Classe che indentifica l'oggetto prestito
 * @author Francesco
 *
 */
public class PrestitoModel implements Serializable{
	
		//Attributi
	
		private static final long serialVersionUID = 1L;
		private Risorsa risorsa;
		private FruitoreModel fruitore;
		private GregorianCalendar dataInizioPrestito;
		private GregorianCalendar dataFinePrestito;
		private GregorianCalendar dataRichiestaProroga;
		private boolean prorogaOk;
		private boolean prestitoFinito;
		
		/**
		 * Il costruttore Prestito associa il fruitore ad una risorsa ed inizializza attributi legati al prestito
		 * @param fruitore il fruitore
		 * @param risorsa la risorsa
		 */
		public PrestitoModel(FruitoreModel fruitore, Risorsa risorsa) {
			this.risorsa = risorsa;
			this.fruitore = fruitore;
			this.dataInizioPrestito= MyUtil.dataCorrente();
			dataFinePrestito= calcolaDataFinePrestito(dataInizioPrestito);
			dataRichiestaProroga = calcolaDataRichiestaProroga(dataFinePrestito);
			prorogaOk = false;	
			prestitoFinito=false;
		}

		
		
		/**
		 * Calcola la data dalla quale si puo'richidere la proroga del prestito
		 * @param dataFinePrestito la data di fine prestito
		 * @return richiesta, la data dalla quale si puo'richidere la proroga
		 */
		private GregorianCalendar calcolaDataRichiestaProroga(GregorianCalendar dataFinePrestito) {
			
			GregorianCalendar richiesta= new GregorianCalendar(dataFinePrestito.get(GregorianCalendar.YEAR), dataFinePrestito.get(GregorianCalendar.MONTH),
																dataFinePrestito.get(GregorianCalendar.DAY_OF_MONTH));
			richiesta.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniPrimaPerProroga());
			return richiesta;
		
		}
		
		
		/**
		 * Calcola la data della scadenza del prestito
		 * @param dataInizioPrestito la data di inizio prestito
		 * @return fine, la data di scadenza
		 */
		private GregorianCalendar calcolaDataFinePrestito(GregorianCalendar dataInizioPrestito) {
			
			GregorianCalendar fine= new GregorianCalendar(dataInizioPrestito.get(GregorianCalendar.YEAR), dataInizioPrestito.get(GregorianCalendar.MONTH), 
					dataInizioPrestito.get(GregorianCalendar.DAY_OF_MONTH));
			fine.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniDurataPrestito());
			
			return fine;
		}
		
		
		
		public String toString() {
			
			StringBuilder str= new StringBuilder();
			
			str.append("Categoria: " + nomeCat(risorsa.getClass().getSimpleName())+ "\n");
			str.append("Titolo: "+ risorsa.getNome()+"\n");
			str.append("Fruitore (usename): "+ fruitore.getUsername()+"\n");
			str.append("Data inizio Prestito: "+  MyUtil.toStringData(dataInizioPrestito)+"\n");
			str.append("Data fine Prestito: "+ MyUtil.toStringData(dataFinePrestito)+"\n");
			
			if(!prorogaOk)
				str.append("Rinnovo Disponibile dal: "+MyUtil.toStringData(dataRichiestaProroga)+ "\n");
			else
				str.append("Rinnovo del prestito non disponibile \n");
			
			return str.toString();
		}
		
		/**
		 * Aggiungi il nome della categoria
		 * @param nomeClasse il nome della classe
		 * @return la categoria 
		 */
		private String nomeCat(String nomeClasse) {
			
			if(nomeClasse.equals("LibroModel"))
				return " Libro ";
			else
				return " Film ";
		}
		
		/**
		 * Finisci prestito
		 */
		public void finisciPrestito() {
			
			risorsa.finePrestito();
			prestitoFinito=true;
		}
		
		/**
		 * Controlla se un perstito puo' essere rinnovato
		 * @return true se rinnovabile, false altrimenti
		 */
		public boolean isRinnovabile() {
			
			if(prorogaOk==true)
				return false;
			
			else if(MyUtil.dataCorrente().after(dataRichiestaProroga))
					return true;
			else 
				return false;
		}
		
		
		/**
		 * Esegue proroga del prestito
		 * (precondizione: il prestito è rinnovabile)
		 */
		public void eseguiProroga() {
			
			setDataInizioPrestito(MyUtil.dataCorrente());
			setDataFinePrestito(calcolaDataFinePrestito(dataInizioPrestito));
			setProrogaOk(true);
			
		}
		


		// Getters & Setters
		public Risorsa getRisorsa() {
			return risorsa;
		}

		public void setRisorsa(Risorsa risorsa) {
			this.risorsa = risorsa;
		}

		public FruitoreModel getFruitore() {
			return fruitore;
		}

		public void setFruitore(FruitoreModel fruitore) {
			this.fruitore = fruitore;
		}

		public GregorianCalendar getDataInizioPrestito() {
			return dataInizioPrestito;
		}

		public void setDataInizioPrestito(GregorianCalendar dataInizioPrestito) {
			this.dataInizioPrestito = dataInizioPrestito;
		}

		public GregorianCalendar getDataFinePrestito() {
			return dataFinePrestito;
		}

		public void setDataFinePrestito(GregorianCalendar dataFine) {
			this.dataFinePrestito = dataFine;
		}

		public GregorianCalendar getDataRichiestaProroga() {
			return dataRichiestaProroga;
		}

		public void setDataRichiestaProroga(GregorianCalendar dataRichiestaProroga) {
			this.dataRichiestaProroga = dataRichiestaProroga;
		}

		public boolean getProrogaOk() {
			return prorogaOk;
		}

		public void setProrogaOk(boolean prorogaOk) {
			this.prorogaOk = prorogaOk;
		}


		public boolean isPrestitoFinito() {
			return prestitoFinito;
		}


		public void setPrestitoFinito(boolean prestitoFinito) {
			this.prestitoFinito = prestitoFinito;
		}
		
	

}
