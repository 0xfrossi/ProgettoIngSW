package ing.unibs.it;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;


/**
 * Definisce l'elemento (sotto)categoria
 * @author Francesco Rossi
 *
 */
public  class Categoria extends Risorsa implements Serializable{

	
	//Attributi
	private static final long serialVersionUID = 1L;
	private ArrayList<Risorsa> arrayRisorse=new ArrayList<Risorsa>();;
	
	/**
	 * Costruttore default
	 */
	public Categoria () {	
	}
	
	
	
	/**
	 * Controlla se una risorsa e' gia' presente attraverso un codice
	 * @param r la risorsa per la quale verificare l'esistenza
	 * @return true se esiste gi� in archivio
	 *		   false altrienti
	 */
	public boolean checkRisorsa(Risorsa r) {
		boolean check=false;
	
		for(int i=0; i<arrayRisorse.size();i++) {
			if(arrayRisorse.get(i).getCodiceUnivoco()==r.getCodiceUnivoco())
				check=true;
		}
		if(check==true)
			return true;
		
		else 
			return false;
	}
	
	/**
	 * ragruppa le risorse in sottoCategorie diverse in un unico array
	 * @return l'array di risorse totali
	 */
	public ArrayList<Risorsa> arrayUniforme(){
		
		ArrayList<Risorsa> tutti=new ArrayList<>();
		for(int i=0; i<arrayRisorse.size();i++) 
			tutti.add(arrayRisorse.get(i));
		return tutti;
	}
	
	/**
	 * Aggiunge una risorsa in una sottoCategoria 
	 * @param r la risorsa da aggiungere
	 */
	public void addInSotto(Risorsa r) {
		
		boolean ok=false;
		
		if (arrayRisorse.isEmpty()) {
			
			arrayRisorse.add(r);
			System.out.println("Operazione effettuata");
		}
		else {
			
			for(int i=0; i<arrayRisorse.size();i++) {
				if(arrayRisorse.get(i).getCodiceUnivoco()==r.getCodiceUnivoco()) {
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

	/**
	 * verifica se una risorsa � gia' stata inserita in una sottocategoria
	 * @param codice il codice della risorsa da verificare
	 * @return true se esiste gia' false altriementi
	 */
	public boolean esisteInSotto(int codice) {
		
		for(int i=0; i<arrayRisorse.size();i++) {
			if(arrayRisorse.get(i).getCodiceUnivoco()==codice)
				return true;
		}	
		return false;
	}
	
	
	/**
	 * Rimuove una risorsa dall'archivio attraverso il codice univoco
	 * @param codice il codice della risorsa da rimuovere
	 */
	public void removePerNome(int codice) {
		
		boolean rimuovere=false;
		int indice=-1;
		for (int i = 0; i <arrayRisorse.size(); i++){
			if(arrayRisorse.get(i).getCodiceUnivoco()==codice) {
				rimuovere=true;
				indice=i;
			}
		}
		
		 if(rimuovere && indice !=-1 ){
			arrayRisorse.remove(indice);
			System.out.println(Costanti.RIMOZIONE_OK);
		 }
		
	}	
	
	/**
	 * Visualizza le risorse inserite			
	 */
	public void stampaDesc() {
		
			for(int i=0;i<arrayRisorse.size();i++){
				
				System.out.println();
				arrayRisorse.get(i).stampaDesc();
				System.out.println();
			}
	}
	
	/**
	 * Aggiunge una risorsa in arrayRisorse
	 * @param c risorsa da aggiungere
	 */
	public void add (Risorsa c){
				arrayRisorse.add(c);
			
	}
	
	/**
	 * Rimuove una risorsa da arrayRisorse
	 * @param c risorsa da rimuovere
	 */
	public void remove(Risorsa c) {
		arrayRisorse.remove(c);
	}
	
	
	
	
	
	
	
	//GETTER

	public ArrayList<Risorsa> getArrayRisorse() {
		return arrayRisorse;
	}

	@Override
	public int getGiorniDurataPrestito() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGiorniDurataProroga() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGiorniPrimaPerProroga() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPrestitiMax() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public int getCodiceUnivoco() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GregorianCalendar getDataInizioPrestito() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GregorianCalendar getDataFinePrestito() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GregorianCalendar getDataRichiestaProroga() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getProrogaOk() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInPrestito() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void inizioPrestito() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finePrestito() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAutori() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumLicenze() {
		// TODO Auto-generated method stub
		return 0;
	}


	

	


	
	
	
}
