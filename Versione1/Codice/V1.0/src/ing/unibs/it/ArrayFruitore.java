package ing.unibs.it;


import java.io.Serializable;
import java.util.ArrayList;
import util.Unibs.MyUtil;
/**
 * Classe per la gestione dell'array di fruitori
 * @author Francesco
 *
 */
public class ArrayFruitore implements Serializable {
	
	//Attributi
	private static final long serialVersionUID = 5762203246975649666L;
	private ArrayList<Fruitore> fruitori ;
	
	/**
	 * Costruttore che inizializza il vettore
	 */
	public ArrayFruitore() {
		
		fruitori = new ArrayList <Fruitore>();
	}

	
	
	/**
	 * Aggiunge un fruitore al vettore con contollo su eta' e username unico
	 * @param fruitore il fruitore
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
	 * Controlla se sono scadute le iscrizione e rimuove il fruitore nel caso
	 */
	public void controlloIscrizioni(){
		
		if(!fruitori.isEmpty()) {
			for (int i=0; i<fruitori.size(); i++){
			
			fruitori.get(i).ControlloDecadenzaFruitore();
			
				if(!fruitori.get(i).isStatoFruitore())	
					fruitori.remove(i);	
			}
		} 
	}

	/**
	 * Stampa tutti i fruitori
	 */
	public void stampaFruitori(){
		if(!fruitori.isEmpty()) {
			for(int i = 0; i<fruitori.size(); i++)
				fruitori.get(i).stampaFruitore();
			}	
		else System.out.println(Costanti.NESSUN_FRUIT);
	}
	
	/**
	 * Controlla se l'username e' disponibile
	 * @param user username da contollare
	 * @return true se disponibile
	 */
	public boolean checkUsername(String user) {
		
			
		if(fruitori.size()==0)  return true;
		for(int i = 0; i<fruitori.size(); i++) {
			if(fruitori.get(i).getUsername().equals(user)) 
				return false;
		}
		return true;
		
	}

	
	//Getters
	
	public ArrayList<Fruitore> getFruitori() {
		return fruitori;
	}
	
	public void setFruitori(ArrayList<Fruitore> fruitori) {
		this.fruitori = fruitori;
	}
	
	
}