package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import interfaces.Loanable;
import interfaces.Risorsa;
import myUtil.MyUtil;

/**
 * Identifica l'oggetto libro
 * @author Francesco
 *
 */
public class LibroModel extends Risorsa implements Serializable, Loanable {
	
	//Dichiarazione Attributi
	
	private static final long serialVersionUID = -4579943601571388630L;
	private String nome;
	private int codiceUnivoco;
	private int numLicenze;
	private ArrayList<String>  autori;
	private int numPagine;
	private String casaEd;
	private String genere;
	private GregorianCalendar annoPub;
	private int inPrestito;
	
	
	
	 /** Costuttore 
	 
	  *@param numLicenze il numero di licenze disponibili
	  *@param nome il titolo del libro
	  *@param codiceUnivoco il cod 
	  *@param autori il array degli autori del libro
	  *@param numPagine il numero di pagine
	  *@param casaEd la casa editrice
	  *@param genere il genere del libro 
	  *@param annoPub l'anno di pubblicazione
	 
	 */
	public LibroModel(String nome,int codiceUnivoco, int numLicenze, ArrayList<String>  autori ,int numPagine,
				String casaEd,String genere,GregorianCalendar annoPub) {
		
		this.nome=nome;
		this.codiceUnivoco=codiceUnivoco;
		this.numLicenze=numLicenze;
		this.numPagine=numPagine;
		this.casaEd=casaEd;
		this.genere=genere;
		this.annoPub=annoPub;
		this.autori=autori;
		this.inPrestito=0;
	}

	
	@Override
	/**
	 * visulizza "descrizione" del libro
	 */
	public String toString() {
		
		StringBuilder str= new StringBuilder();
		str.append("Categoria: Libro \n");
		str.append("Titolo: "+ nome+ "\n");
		str.append("Autori: " );
		MyUtil.stampaArray(str, autori);
		str.append("Casa Editrice: "+ casaEd+ "\n");
		str.append("Anno di pubbicazione: "+ MyUtil.toStringData(getAnnoPub())+ "\n");
		str.append("Numero di pagine: "+ numPagine+ "\n");	
		str.append("Numero Licenze: "+ numLicenze+ "\n");
		str.append("In prestito: "+ inPrestito+ "\n");
		str.append("Copie disponibili: "+ (getNumLicenze() - getInPrestito())+ "\n");
		
		return str.toString();
	}
	
	
	
	
	
	
	public boolean equals(Risorsa risorsa) {
		
		if(codiceUnivoco==risorsa.getCodiceUnivoco())
			return true;
		else 
			return false;
		
	}
	
	
	//GETTERS & SETTERS
	
	@Override
	public String getNome() {
		return nome;
	}
	
	
	public ArrayList<String> getAutori() {
		return autori;
	}

	public int getNumLicenze() {
		return numLicenze;
	}


	public void setNumLicenze(int numLicenze) {
		this.numLicenze = numLicenze;
	}


	public void setAutori(ArrayList<String> autori) {
		this.autori = autori;
	}

	public int getNumPagine() {
		return numPagine;
	}

	public void setNumPagine(int numPagine) {
		this.numPagine = numPagine;
	}

	public String getCasaEd() {
		return casaEd;
	}

	public void setCasaEd(String casaEd) {
		this.casaEd = casaEd;
	}

	

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public GregorianCalendar getAnnoPub() {
		return annoPub;
	}

	public void setAnnoPub(GregorianCalendar annoPub) {
		this.annoPub = annoPub;
	}


	public int getInPrestito() {
		return inPrestito;
	}


	public void setGiaInPrestito(int inPrestito) {
		this.inPrestito = inPrestito;
	}

	
	@Override
	public void inizioPrestito(){
		inPrestito++;
	}
	
	@Override
	public void finePrestito(){
		inPrestito--;
	}

	@Override
	public int getGiorniDurataPrestito() {
		return GIORNI_DURATA_PRESTITO;
	}

	@Override
	public int getGiorniDurataProroga() {
		return GIORNI_DURATA_PROROGA;
	}

	@Override
	public int getGiorniPrimaPerProroga() {
		return GIORNI_PRIMA_PER_PROROGA;
	}

	@Override
	public int getPrestitiMax() {
		return PRESTITI_MAX;
	}


	@Override
	public int getCodiceUnivoco() {
		// TODO Auto-generated method stub
		return codiceUnivoco;
	}

//loanable
	
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
	public void add(Risorsa c) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void remove(Risorsa c) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ArrayList<String> getAttori() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
