package ing.unibs.it;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import util.Unibs.MyUtil;



/**
 * Classe che definisce la Risorsa Libro
 * @author Francesco Rossi
 *
 */
public class Libro  extends Risorsa implements Serializable {
	
	
	//Attributi
	
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
	  *@param codiceUnivoco il cod univoco
	  *@param nome il titolo del libro
	  *@param autori il array degli autori del libro
	  *@param numPagine il numero di pagine
	  *@param casaEd la casa editrice
	  *@param genere il genere del libro 
	  *@param annoPub l'anno di pubblicazione
	 
	 */
	public Libro(String nome,int codiceUnivoco, int numLicenze, ArrayList<String>  autori ,int numPagine,
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
	public void stampaDesc() {
		
		System.out.println(Costanti.TITOLO + getNome());
		System.out.println(Costanti.GENERE+ getGenere());
		System.out.println(Costanti.CODICE+ getCodiceUnivoco());
		System.out.print(Costanti.AUTORI);
		
		for(int i = 0; i < autori.size(); i++){
			System.out.print(" " + autori.get(i));
			if(i < autori.size()-1)
				System.out.print(",");
			
			else System.out.println();
		}	
		System.out.println(Costanti.CASA_ED+ getCasaEd());
		System.out.println(Costanti.ANNO_PUB+ MyUtil.toStringData(getAnnoPub()));
		//System.out.println(Costanti.LINGUA+ getLingua());
		System.out.println(Costanti.NUM_PAG+ getNumPagine());
		System.out.println(Costanti.NUM_LIC+ getNumLicenze());
		System.out.println("In prestito: " + getInPrestito());
		System.out.println("Copie disponibili " + (getNumLicenze() - getInPrestito()));
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
		return Costanti.GIORNI_DURATA_PRESTITO;
	}

	@Override
	public int getGiorniDurataProroga() {
		return Costanti.GIORNI_DURATA_PROROGA;
	}

	@Override
	public int getGiorniPrimaPerProroga() {
		return Costanti.GIORNI_PRIMA_PER_PROROGA;
	}

	@Override
	public int getPrestitiMax() {
		return Costanti.PRESTITI_MAX;
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

	
	


	
}
