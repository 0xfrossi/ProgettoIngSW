package ing.unibs.it;

import java.io.Serializable;
import java.util.GregorianCalendar;
import util.Unibs.MyUtil;

public class Prestito implements Serializable  {
	
	
	private static final long serialVersionUID = 1L;
	private Risorsa risorsa;
	private Fruitore fruitore;
	private GregorianCalendar dataInizioPrestito;
	private GregorianCalendar dataFinePrestito;
	private GregorianCalendar dataRichiestaProroga;
	private boolean prorogaOk;
	
	/**
	 * Il costruttore Prestito associa il fruitore ad una risorsa
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
	}

	
	/*public Prestito(Risorsa risorsa) {
		this.risorsa=risorsa;
		this.dataInizioPrestito=(GregorianCalendar)GregorianCalendar.getInstance();
		dataFinePrestito = new GregorianCalendar(dataInizioPrestito.get(GregorianCalendar.YEAR), dataInizioPrestito.get(GregorianCalendar.MONTH), dataInizioPrestito.get(GregorianCalendar.DAY_OF_MONTH));

		dataFinePrestito.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniDurataPrestito());

		dataRichiestaProroga = new GregorianCalendar(dataFinePrestito.get(GregorianCalendar.YEAR), dataFinePrestito.get(GregorianCalendar.MONTH), dataFinePrestito.get(GregorianCalendar.DAY_OF_MONTH));
		dataRichiestaProroga.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniPrimaPerProroga());
		prorogaOk = false;		
	}*/
	
	public void stampaPrestito(){
		
		System.out.println("Categoria: " + risorsa.getClass().getSimpleName());
		System.out.println("Titolo: " + risorsa.getNome());
		System.out.println("Fruitore: " + fruitore.getUsername());
		System.out.println("Data prestito: " + MyUtil.toStringData(dataInizioPrestito));
		System.out.println("Data scadenza: " + MyUtil.toStringData(dataFinePrestito));
		
		if(!prorogaOk)
			System.out.println("Rinnovo (1 sola volta) disponibile dal: " + MyUtil.toStringData(dataRichiestaProroga));
		
		else
			System.out.println("Prestito non rinnovabile");
		
	}

	public void rinnovaPrestito() {
		
		GregorianCalendar oggi=(GregorianCalendar)GregorianCalendar.getInstance();
		if(oggi.after(getDataRichiestaProroga())){
				setDataInizioPrestito(oggi);
				setProrogaOk(true);
			}
			else{
				System.out.println("prestito non ancora rinnovabile: ");
			}
	}
	
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

	public boolean isProrogaOk() {
		return prorogaOk;
	}

	public void setProrogaOk(boolean prorogaOk) {
		this.prorogaOk = prorogaOk;
	}
	
	
}
