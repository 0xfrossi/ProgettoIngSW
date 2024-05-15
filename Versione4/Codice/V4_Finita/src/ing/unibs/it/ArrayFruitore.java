package ing.unibs.it;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import util.Unibs.MyUtil;


/**
 * Classe che gestisce la lista di fruitori del sistema
 * @author Francesco Rossi
 *
 */
public class ArrayFruitore implements Serializable {
	
	
	  
    //Attributi
	private static final long serialVersionUID = 5762203246975649666L;
	private ArrayList<Fruitore> fruitori ;
	
	
	/**
	 * Costruttore che inizializza il vettore fruitori
	 */
	public ArrayFruitore() {
		
		fruitori = new ArrayList <Fruitore>();
	}

	
	
	/**
	 * aggiunge un fruitore alla lista 
	 * @param fruitore il fruitore da aggiungere
	 */
	public void addFriutore(Fruitore fruitore) {
		if(fruitori.isEmpty()) { 
			if(MyUtil.maggiorenne(fruitore.getDataDiNascita())) {
				fruitori.add(fruitore);
				System.out.println(Costanti.RIUSCITA);
			}
			else System.out.println(Costanti.ACCESSO_NEGATO);
		}
		else if(MyUtil.maggiorenne(fruitore.getDataDiNascita())) {
			
				if(checkUsername(fruitore.getUsername())) {
					fruitori.add(fruitore);
					System.out.println(Costanti.RIUSCITA);
				}
				else System.out.println(Costanti.USER_NN_DISP);
		}
		else System.out.println(Costanti.ACCESSO_NEGATO);
	}
	
	
	/**
	 * controlla se le iscrizioni degli utenti sono scadute e in caso le raggruppa in un array
	 * @return array di utenti con iscrizione scaduta
	 */
	public ArrayList<Fruitore> utentiScaduti(){
		
		ArrayList<Fruitore> scaduti=new ArrayList();
		if(!fruitori.isEmpty()) {
			for (int i=0; i<fruitori.size(); i++){
			
			fruitori.get(i).ControlloDecadenzaFruitore();
			if(!fruitori.get(i).isStatoFruitore())
				scaduti.add(fruitori.get(i));
				
			}
			
		}
		return scaduti;
	}
	
	
	/**
	 * rimuove dall'array dei fruitori quelli con l'iscrizione scaduta
	 * @param scaduti l'array di utenti da rimuovere
	 */
	public void rimuoviIscrizioni(ArrayList<Fruitore> scaduti){
		
		for(Fruitore fruitore: scaduti)
			fruitori.remove(fruitore);
			
		/*if(!fruitori.isEmpty()) {
			for (int i=0; i<fruitori.size(); i++){
			
			fruitori.get(i).ControlloDecadenzaFruitore();
			
				if(!fruitori.get(i).isStatoFruitore())	
		
					fruitori.remove(i);	
			}
		} */
		
		
	}

	/**
	 * Stampa la lista dei fruitori attuali
	 */
	public void stampaFruitori(){
		
		for(int i = 0; i<fruitori.size(); i++)
		{
			fruitori.get(i).stampaFruitore();
		}
	}

	/**
	 * controlla se un userName e' gia' in uso
	 * @param user l'userName da verificare
	 * @return false se disponibile, true altrimenti
	 */
	public boolean checkUsername(String user) {
					
		if(fruitori.size()==0)  return true;
		for(int i = 0; i<fruitori.size(); i++) {
			if(fruitori.get(i).getUsername().equals(user)) 
				return false;
		}
		return true;
	}

	
	//Getter
	public ArrayList<Fruitore> getFruitori() {
		return fruitori;
	}

	
}