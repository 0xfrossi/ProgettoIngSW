package ing.unibs.it;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import util.Unibs.MyUtil;
/**
 * Classe che definisce un tipo di Risorsa
 * @author Francesco Rossi
 *
 */
public class Film extends Risorsa implements Serializable {

	
	  
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
	public Film(String titolo, GregorianCalendar dataUscita, String regista, ArrayList<String> attori,
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
	/**
	 * Stampa i dati di un film
	 */
	public void stampaDesc() {
			
			System.out.println(Costanti.TITOLO + getNome());
			System.out.println(Costanti.GENERE+ getGenere());
			System.out.println(Costanti.CODICE+ getCodiceUnivoco());
			System.out.print(Costanti.ATTORI);
			
			for(int i = 0; i < attori.size(); i++){
				System.out.print(" " + attori.get(i));
				if(i < attori.size()-1)
					System.out.print(",");
				
				else System.out.println();
			}	
			
			System.out.println(Costanti.ANNO_PUB+ MyUtil.toStringData(getDataUscita()));
			System.out.println(Costanti.REGISTA + getRegista());
			System.out.println(Costanti.NUM_LIC+ getNumLicenze());
			System.out.println("In prestito: " + getInPrestito());
			System.out.println("Copie disponibili " + (getNumLicenze() - getInPrestito()));
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
		return Costanti.GIORNI_DURATA_PRESTITO_FILM;
	}

	@Override
	public int getGiorniDurataProroga() {
		// TODO Auto-generated method stub
		return Costanti.GIORNI_DURATA_PROROGA_FILM;
	}

	@Override
	public int getGiorniPrimaPerProroga() {
		// TODO Auto-generated method stub
		return Costanti.GIORNI_PRIMA_PER_PROROGA_FILM;
	}

	@Override
	public int getPrestitiMax() {
		// TODO Auto-generated method stub
		return Costanti.PRESTITI_MAX_FILM;
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
