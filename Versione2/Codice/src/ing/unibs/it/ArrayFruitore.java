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
	 * @param fruitore
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
	 * Controlla lo stato delle iscrizioni
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
	 * Stampa i dati di tutti i fruitori
	 */
	public void stampaFruitori(){
		
		for(int i = 0; i<fruitori.size(); i++)
		{
			fruitori.get(i).stampaFruitore();
		}
	}
	
	/**
	 * Controlla l'unicita' dell'userName inserito
	 * @param user su cui fare il controllo
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