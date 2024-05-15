package ing.unibs.it;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import util.Unibs.MyUtil;

public class Libro  extends Risorsa implements Serializable {
	
	
	private static final long serialVersionUID = -4579943601571388630L;
	
	private int codiceUnivoco;
	private String nome;
	private int numLicenze;
	private ArrayList<String>  autori;
	private int numPagine;
	private String casaEd;
	private String genere;
	private GregorianCalendar annoPub;
	
	 /** Costuttore 
	 
	  *@param sottoCategoria la sottocategoria della categoria libro (es. Romanzo, Fumetto, Poesia...)
	  *@param nLicenze il numero di licenze disponibili
	  *@param nome il titolo del libro
	  *@param autori il array degli autori del libro
	  *@param numPagine il numero di pagine
	  *@param casaEd la casa editrice
	  *@param lingua la lingua del testo
	  *@param genere il genere del libro 
	  *@param annoPubblicazione l'anno di pubblicazione
	 
	 */
	public Libro(String nome,int numLicenze,int codiceUnivoco, ArrayList<String>  autori ,int numPagine,
				String casaEd,String genere,GregorianCalendar annoPub) {
		
		this.nome=nome;;
		this.numLicenze=numLicenze;
		this.codiceUnivoco=codiceUnivoco;
		this.numPagine=numPagine;
		this.casaEd=casaEd;
		this.genere=genere;
		this.annoPub=annoPub;
		this.autori=autori;
	}

	
	@Override
	/**
	 * Stampa della "descrizione" del libro
	 */
	public void stampaDesc() {
		
		
		System.out.println(Costanti.TITOLO + getNome());
		//System.out.println(Costanti.GENERE+ getGenere());
		System.out.print(Costanti.AUTORI);
		for(int i = 0; i < autori.size(); i++)
		{
			System.out.print(" " + autori.get(i));
			if(i < autori.size()-1)
			{ 
				System.out.print(",");
			}
			else System.out.println();
		}	
		System.out.println(Costanti.CASA_ED+ getCasaEd());
		System.out.println(Costanti.ANNO_PUB+ MyUtil.toStringData(getAnnoPub()));
		//System.out.println(Costanti.LINGUA+ getLingua());
		System.out.println(Costanti.NUM_PAG+ getNumPagine());
		System.out.println(Costanti.NUM_LIC+ getNumLicenze());
	}
	
	/*
	private ArrayList<String> inserisciAutori(){
		ArrayList<String> autori = new ArrayList<String>();
	do
	{
		String autore = MyUtil.leggiStringaNonVuota("Inserisci l'autore: ");
		autori.add(autore+" ");
	} 
	while(MyUtil.yesOrNo("ci sono altri autori? "));
	
	return autori;
	}
	
	*/
	
	//GETTERS & SETTERS
	
	
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


	@Override
	public String getNome() {
		
		return nome;
	}


	@Override
	public int getCodiceUnivoco() {
		
		return codiceUnivoco;
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
