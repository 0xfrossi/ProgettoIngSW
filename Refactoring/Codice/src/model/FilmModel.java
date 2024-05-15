package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import interfaces.Loanable;
import interfaces.Risorsa;
import myUtil.MyUtil;

/**
 * Classe che descrive l'oggetto film
 * @author Francesco
 *
 */
public class FilmModel extends Risorsa implements Serializable, Loanable{
	
	 //Attributi
	private static final long serialVersionUID = 1L;
	private String nome; //titolo
	private GregorianCalendar dataUscita;
	private String regista;
	private ArrayList<String> attori;
	private int numLicenze;
	private int codiceUnivoco;
	private String genere;
	private int inPrestito;
	
	
	
	/**
	 * Costruttore della classe 
	 * @param titolo il titolo del film
	 * @param dataUscita la data d'uscita 
	 * @param regista il regista
	 * @param attori gli attori protagonisti
	 * @param numLicenze il numero di licenze conecesse
	 * @param codiceUnivoco il codice univoco 
	 * @param genere il genere
	 */
	public FilmModel(String titolo, GregorianCalendar dataUscita, String regista, ArrayList<String> attori,
			int numLicenze, int codiceUnivoco, String genere) {
		
		this.nome = titolo;
		this.dataUscita = dataUscita;
		this.regista = regista;
		this.attori = attori;
		this.numLicenze = numLicenze;
		this.codiceUnivoco = codiceUnivoco;
		this.genere = genere;
		inPrestito=0;
		}

	@Override
	public boolean equals(Risorsa risorsa) {
		if(this.codiceUnivoco== risorsa.getCodiceUnivoco())
			return true;
		else 
			return false;
	}

	@Override
	/**
	 * Stampa i dati di un film
	 */
	public String toString() {
			
		StringBuilder str= new StringBuilder();
		
		str.append("Titolo: "+ nome + "\n");
		str.append("Genere: "+ genere + "\n");
		str.append("Codice univoco: "+ codiceUnivoco + "\n");
		str.append("Attori: ");
		MyUtil.stampaArray(str, attori);
		str.append("Anno di pubbicazione: "+ MyUtil.toStringData(getDataUscita()) + "\n");
		str.append("Regista: "+ regista + "\n");	
		str.append("Numero di licenze: "+ numLicenze + "\n");
		str.append("In prestito: "+ inPrestito + "\n");
		str.append("Copie disponibili: "+ (getNumLicenze() - getInPrestito()) + "\n");
		
		return str.toString();
		}
		
	
	
	
	//Getters e metodi sovrascritti da risorsa con alcuni non utilizzati
	
	public GregorianCalendar getDataUscita() {
		return dataUscita;
	}
	
	public String getRegista() {
		return regista;
	}

	@Override
	public ArrayList<String> getAttori() {
		return attori;
	}

	public String getGenere() {
		return genere;
	}

	@Override
	public void inizioPrestito() {
		inPrestito++;
		
	}

	@Override
	public void finePrestito() {
		inPrestito--;
		
	}

	@Override
	public GregorianCalendar getDataInizioPrestito() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GregorianCalendar getDataFinePrestito() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GregorianCalendar getDataRichiestaProroga() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getProrogaOk() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInPrestito() {
		// TODO Auto-generated method stub
		return inPrestito;
	}

	@Override
	public int getGiorniDurataPrestito() {
		// TODO Auto-generated method stub
		return GIORNI_DURATA_PRESTITO_FILM;
	}

	@Override
	public int getGiorniDurataProroga() {
		// TODO Auto-generated method stub
		return GIORNI_DURATA_PROROGA_FILM;
	}

	@Override
	public int getGiorniPrimaPerProroga() {
		// TODO Auto-generated method stub
		return GIORNI_PRIMA_PER_PROROGA_FILM;
	}

	@Override
	public int getPrestitiMax() {
		// TODO Auto-generated method stub
		return PRESTITI_MAX_FILM;
	}

	
	@Override
	public void add(Risorsa c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Risorsa c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCodiceUnivoco() {
		// TODO Auto-generated method stub
		return codiceUnivoco;
	}

	@Override
	public String getNome() {
		
		return nome;
	}

	@Override
	public ArrayList<String> getAutori() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumLicenze() {
		// TODO Auto-generated method stub
		return numLicenze;
	}

	

}
