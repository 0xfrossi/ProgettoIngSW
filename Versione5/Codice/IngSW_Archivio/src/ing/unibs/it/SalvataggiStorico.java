package ing.unibs.it;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che mantiene memorizzati gli storici del sistema
 * @author Francesco Rossi
 *
 */
public class SalvataggiStorico implements Serializable {

	
	
	//Attributi
	private static final long serialVersionUID = 1L;
	private   ArrayList<Fruitore>  fruitori;
	private   ArrayList<Fruitore> fruitoriScaduti;
	private   ArrayList<Fruitore> fruitoriRinnovati;
	private   ArrayList<Risorsa> risorsePrestabiliInPassato;
	private   ArrayList<Prestito> logsPrestiti;
	private   ArrayList<Prestito> prorogati;
	private   ArrayList<Prestito> prestitiTerminati;
	
	

	/**
	 * Costruttore che inizializza gli array
	 */
	public SalvataggiStorico() {
		
		fruitori= new ArrayList<Fruitore>(); 
		fruitoriScaduti= new ArrayList<Fruitore>();
		fruitoriRinnovati= new ArrayList<Fruitore>();
		risorsePrestabiliInPassato= new ArrayList<Risorsa>();
		logsPrestiti= new ArrayList<Prestito>();
		prorogati= new ArrayList<Prestito>();
		prestitiTerminati= new ArrayList<Prestito>();
	}


	
	
	
	
	
	//GETTER
	public  ArrayList<Fruitore> getFruitori() {
		return fruitori;
	}

	public  ArrayList<Fruitore> getFruitoriScaduti() {
		return fruitoriScaduti;
	}

	public  ArrayList<Fruitore> getFruitoriRinnovati() {
		return fruitoriRinnovati;
	}

	public  ArrayList<Risorsa> getRisorsePrestabiliInPassato() {
		return risorsePrestabiliInPassato;
	}

	public ArrayList<Prestito> getLogsPrestiti() {
		return logsPrestiti;
	}

	public  ArrayList<Prestito> getProrogati() {
		return prorogati;
	}

	public  ArrayList<Prestito> getPrestitiTerminati() {
		return prestitiTerminati;
	}
	
	
	
}
