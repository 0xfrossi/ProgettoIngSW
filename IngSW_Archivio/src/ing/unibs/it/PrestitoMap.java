package ing.unibs.it;

import java.util.ArrayList;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map.Entry;

public class PrestitoMap  {

	
	
	private Risorsa risorsa;
	private Fruitore fruitore;
	private GregorianCalendar dataInizio;
	private GregorianCalendar dataFine;
	private GregorianCalendar dataRichiestaProroga;
	private boolean prorogaOk;
	private HashMap<Fruitore,Risorsa> prestiti;
	
	public PrestitoMap(Fruitore fruitore,Risorsa risorsa) {
		
		this.risorsa = risorsa;
		this.fruitore = fruitore;
		
		this.dataInizio=(GregorianCalendar)GregorianCalendar.getInstance();
		
		dataFine = new GregorianCalendar(dataInizio.get(GregorianCalendar.YEAR), dataInizio.get(GregorianCalendar.MONTH), dataInizio.get(GregorianCalendar.DAY_OF_MONTH));

		dataFine.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniDurataPrestito());

		dataRichiestaProroga = new GregorianCalendar(dataFine.get(GregorianCalendar.YEAR), dataFine.get(GregorianCalendar.MONTH), dataFine.get(GregorianCalendar.DAY_OF_MONTH));
		dataRichiestaProroga.add(GregorianCalendar.DAY_OF_MONTH, risorsa.getGiorniPrimaPerProroga());
		prorogaOk = false;	
		
		prestiti= new HashMap<Fruitore, Risorsa>();
		
	}
	
	
	public void addPrestito(Fruitore fruitore, Risorsa risorsa) {
		prestiti.put(fruitore, risorsa);
		prestiti.get(risorsa).mandaInPrestito();
	}
	
	
	
	/**
	 * conta il numero di prestiti di un utente, relativi ad una categoria
	 * @param username lo username del fruitore
	 * @param categoria la categoria nella quale cercare i prestiti
	 * @return il numero di prestiti dell'utente relativi alla categoria indicata
	 */
	public int numPrestitiDi(String username, String categoria)
	{
		int count = 0;
		if(categoria.equalsIgnoreCase("Libri")) {
			 for (HashMap.Entry<Fruitore, Risorsa> mappa : prestiti.entrySet()) {
				 if(mappa.getKey().getUsername().equals(username) && mappa.getValue() instanceof Libro)
						count++;		
			 }
		}
		return count;
	}
	
	/**
	 * controllo per tutti i prestiti presenti se sono scaduti (li rimuovo) oppure no
	 */
	public void controlloPrestiti() 
	{
		int rimossi = 0;
		for (int i = 0; i < prestiti.size(); i++) 
		{
			if(prestiti.get(i).getDataScadenza().compareTo(GestioneDate.DATA_CORRENTE) < 0)	//se dataScadenza � precedente a oggi ritorna -1
			{
				prestiti.get(i).getRisorsa().tornaDalPrestito();
				prestiti.remove(i);
				rimossi++;				
			}
		}
		System.out.println("Risorse tornate dal prestito: " + rimossi);
	}
	
	
}
