package ing.unibs.it;

import java.util.GregorianCalendar;
import util.Unibs.MyUtil;

public class Prestito  {
	
	private Risorsa risorsa;
	private Fruitore fruitore;
	private GregorianCalendar dataInizio;
	private GregorianCalendar dataFine;
	private GregorianCalendar dataRichiestaProroga;
	private boolean prorogaOk;
	
	/**
	 * Il costruttore Prestito associa il fruitore ad una risorsa
	 */
	public Prestito(Fruitore fruitore, Risorsa risorsa) {
		this.risorsa = risorsa;
		this.fruitore = fruitore;
		this.dataInizio=(GregorianCalendar)GregorianCalendar.getInstance();
		dataFine = new GregorianCalendar(dataInizio.get(GregorianCalendar.YEAR), dataInizio.get(GregorianCalendar.MONTH), dataInizio.get(GregorianCalendar.DAY_OF_MONTH));

		dataFine.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniDurataPrestito());

		dataRichiestaProroga = new GregorianCalendar(dataFine.get(GregorianCalendar.YEAR), dataFine.get(GregorianCalendar.MONTH), dataFine.get(GregorianCalendar.DAY_OF_MONTH));
		dataRichiestaProroga.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniPrimaPerProroga());
		prorogaOk = false;		
	}

	public void stampaPrestito()
	{
		System.out.println("Categoria: " + risorsa.getClass().getSimpleName());
		System.out.println("Titolo: " + risorsa.getNome());
		System.out.println("Fruitore: " + fruitore.getUsername());
		System.out.println("Data prestito: " + MyUtil.toStringData(dataInizio));
		System.out.println("Data scadenza: " + MyUtil.toStringData(dataFine));
		if(!prorogaOk)
		{
			System.out.println("Rinnovo disponibile dal: " + MyUtil.toStringData(dataRichiestaProroga));
		}
		else
		{
			System.out.println("Prestito non rinnovabile");
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

	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(GregorianCalendar dataInizio) {
		this.dataInizio = dataInizio;
	}

	public GregorianCalendar getDataFine() {
		return dataFine;
	}

	public void setDataFine(GregorianCalendar dataFine) {
		this.dataFine = dataFine;
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
