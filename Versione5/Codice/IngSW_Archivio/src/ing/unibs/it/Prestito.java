package ing.unibs.it;

import java.io.Serializable;
import java.util.GregorianCalendar;
import util.Unibs.MyUtil;

/**
 * Classe  le cui istanze associano un fruitore ad una risorsa, creando la relazione di "prestito"
 * @author Francesco Rossi
 *
 */
public class Prestito implements Serializable  {
	
	//Attributi
	
	private static final long serialVersionUID = 1L;
	private Risorsa risorsa;
	private Fruitore fruitore;
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
	public Prestito(Fruitore fruitore, Risorsa risorsa) {
		this.risorsa = risorsa;
		this.fruitore = fruitore;
		this.dataInizioPrestito=(GregorianCalendar)GregorianCalendar.getInstance();
		dataFinePrestito = new GregorianCalendar(dataInizioPrestito.get(GregorianCalendar.YEAR), dataInizioPrestito.get(GregorianCalendar.MONTH), dataInizioPrestito.get(GregorianCalendar.DAY_OF_MONTH));

		dataFinePrestito.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniDurataPrestito());

		dataRichiestaProroga = new GregorianCalendar(dataFinePrestito.get(GregorianCalendar.YEAR), dataFinePrestito.get(GregorianCalendar.MONTH), dataFinePrestito.get(GregorianCalendar.DAY_OF_MONTH));
		dataRichiestaProroga.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniPrimaPerProroga());
		prorogaOk = false;	
		prestitoFinito=false;
	}

	
	
	
	
	/**
	 * Visualizza i dati del prstito
	 */
	public void stampaPrestito(){
		
		System.out.println("Categoria: " + risorsa.getClass().getSimpleName());
		System.out.println(Costanti.TITOLO+ risorsa.getNome());
		System.out.println(Costanti.FRUITORE + fruitore.getUsername());
		System.out.println(Costanti.INIZIO_PRESTITO + MyUtil.toStringData(dataInizioPrestito));
		System.out.println(Costanti.FINE_PRESTITO + MyUtil.toStringData(dataFinePrestito));
		
		if(!prorogaOk)
			System.out.println(Costanti.OK_RINNOVO_DAL + MyUtil.toStringData(dataRichiestaProroga));
		
		else
			System.out.println(Costanti.NO_RINNOVO);
		
	}
	/**
	 * Finisci prestito
	 */
	public void finisciPrestito() {
		
		risorsa.finePrestito();
		prestitoFinito=true;
	}
	
	/**
	 * consente il rinnovo del prestito (1 sola volta)
	 */
	public void rinnovaPrestito() {
		
		GregorianCalendar oggi=(GregorianCalendar) GregorianCalendar.getInstance();
		
		if(getProrogaOk()==true)
			System.out.println(Costanti.NO_PROROGA);
		
		else if(oggi.after(getDataRichiestaProroga())&& getProrogaOk()==false){
				setDataInizioPrestito(oggi);
				setProrogaOk(true);
				System.out.println(" Rinnovato ");
			}

		else if(!oggi.after(getDataRichiestaProroga())&& getProrogaOk()==false)
				System.out.println(Costanti.NO_RINNOVABILE);
			
	}
	
	
	// Getters & Setters
	public Risorsa getRisorsa() {
		return risorsa;
	}

	public void setRisorsa(Risorsa risorsa) {
		this.risorsa = risorsa;
	}

	public Fruitore getFruitore() {
		return fruitore;
	}

	public void setFruitore(Fruitore fruitore) {
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
