package ing.unibs.it;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import util.Unibs.MyUtil;

public class ArrayFruitore implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5762203246975649666L;
	/**
	 * 
	 */
	
	private ArrayList<Fruitore> fruitori ;
	
	
	public ArrayFruitore() {
		
		fruitori = new ArrayList <Fruitore>();
	}

	
	
	
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
	
	public void rimuoviIscrizioni(ArrayList<Fruitore> scaduti){
		
		for(Fruitore fruitore: scaduti)
			scaduti.remove(fruitore);
			
		/*if(!fruitori.isEmpty()) {
			for (int i=0; i<fruitori.size(); i++){
			
			fruitori.get(i).ControlloDecadenzaFruitore();
			
				if(!fruitori.get(i).isStatoFruitore())	
		
					fruitori.remove(i);	
			}
		} */
		
		
	}

	
	public void stampaFruitori(){
		
		for(int i = 0; i<fruitori.size(); i++)
		{
			fruitori.get(i).stampaFruitore();
		}
	}
	
	
	public boolean checkUsername(String user) {
		
			
		if(fruitori.size()==0)  return true;
		for(int i = 0; i<fruitori.size(); i++) {
			if(fruitori.get(i).getUsername().equals(user)) 
				return false;
		}
		return true;
		
	}

	
	
	public ArrayList<Fruitore> getFruitori() {
		return fruitori;
	}


	public void setFruitori(ArrayList<Fruitore> fruitori) {
		this.fruitori = fruitori;
	}
	
	
}