package ing.unibs.it;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che descrive elemento generico categoria
 * @author Francesco
 *
 */
public  class Categoria extends Risorsa implements Serializable{

	
	//Attributi
	private static final long serialVersionUID = 1L;
	private ArrayList<Risorsa> arrayRisorse;
	
	/**
	 * Costruttore che inizializza l'array di risorse
	 * 
	 */
	public Categoria () {	

		arrayRisorse=new ArrayList<Risorsa>();
	
	}
	
	
	/**
	 * Controlla che la risorsa non sia gia' presente
	 * @param r risorsa da controllare
	 * @return true se presente
	 */
	public boolean checkRisorsa(Risorsa r) {
		boolean check=false;
	
		for(int i=0; i<arrayRisorse.size();i++) {
			if(arrayRisorse.get(i).getCodiceUnivoco()==r.getCodiceUnivoco())
				check=true;
		}
		if(check==true)
			return true;
		
		else return false;
	}
	
	
	/**
	 * Aggiunge risorsa in categoria
	 * @param r da aggiungere
	 */
	public void addInSotto(Risorsa r) {
		
		boolean ok=false;
		
		if (arrayRisorse.isEmpty()) {
			
			arrayRisorse.add(r);
			System.out.println("Operazione effettuata");
		}
		else {
			
			
			for(int i=0; i<arrayRisorse.size();i++) {
				if(arrayRisorse.get(i).getNome().equalsIgnoreCase(r.getNome())) {
					ok=true;
					System.out.println("Risorsa gia' presente");	
					
				}
			}	
			
				if(ok==false) {
					arrayRisorse.add(r);
					System.out.println("Operazione effettuata");
				}
		}
		}	

	
	public boolean esisteInSotto(int codice) {
		
		for(int i=0; i<arrayRisorse.size();i++) {
			if(arrayRisorse.get(i).getCodiceUnivoco()==codice)
				return true;
		}	
		return false;
	}
	
	/**
	 * Rimuove una risorsa inserendo il codice univoco
	 * @param codice codice della risorsa da rimuovere
	 */
	public void removePerNome(int codice) {
		
		int posizione=-1;
		boolean in=false;
		
		for (int i = 0; i <arrayRisorse.size(); i++){
			if(arrayRisorse.get(i).getCodiceUnivoco()==codice){
				posizione=i;
				in=true;	
			}
		}
	
		 if(in){
			arrayRisorse.remove(posizione);
			System.out.println("Rimozione avvenuta con successo!");
		}
	
	}	
	
	
	/**
	 * Stampa dati risorse			
	 */
	public void stampaDesc() {
		
			for(int i=0;i<arrayRisorse.size();i++)
			{
				
				System.out.println();
				arrayRisorse.get(i).stampaDesc();
				System.out.println();
		
			}
	}
	
	
	/**
	 * Aggiungi una risorsa
	 */
	public void add (Risorsa c){
			arrayRisorse.add(c);
			
	}
	/**
	 * Rimuovi una risorsa
	 */
	public void remove(Risorsa c) {
		arrayRisorse.remove(c);
	}
	
	
	
	
	
	//GETTER

	public ArrayList<Risorsa> getArrayRisorse() {
		return arrayRisorse;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCodiceUnivoco() {
		// TODO Auto-generated method stub
		return 0;
	}


	

	


	
	
	
}
