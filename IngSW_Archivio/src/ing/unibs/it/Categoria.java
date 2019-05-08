package ing.unibs.it;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public  class Categoria extends Risorsa implements Serializable{

	

	private static final long serialVersionUID = 1L;
	private ArrayList<Risorsa> arrayRisorse=new ArrayList<Risorsa>();;
	
	
	public Categoria () {	
	}
	
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

	
	public boolean esisteInSotto(int codice) {
		
		for(int i=0; i<arrayRisorse.size();i++) {
			if(arrayRisorse.get(i).getCodiceUnivoco()==codice)
				return true;
		}	
		return false;
	}
	
	public void removePerNome(int codice) {
		
		Vector<Integer> posizioni = new Vector<>();
		
		for (int i = 0; i <arrayRisorse.size(); i++) 
			
		{
			if(arrayRisorse.get(i).getCodiceUnivoco()==codice)
			{
				posizioni.add(i);
			}
		}
		/*if(posizioni.size()==0)
		{
			System.out.println("Siamo spiacenti, il libro non e' presente nell'archivio");
		}*/
		 if(posizioni.size()==1)
		{
			arrayRisorse.remove((int)posizioni.get(0));
			System.out.println("Rimozione avvenuta con successo!");
		}
		
		}	
				
	public void stampaDesc() {
		
			for(int i=0;i<arrayRisorse.size();i++)
			{
				
				System.out.println();
				arrayRisorse.get(i).stampaDesc();
				System.out.println();
		
			}
	}
	
	public void add (Risorsa c){
				arrayRisorse.add(c);
			
	}

	public void remove(Risorsa c) {
		arrayRisorse.remove(c);
	}
	
	
	
	
	
	
	
	//GETTER

	public ArrayList<Risorsa> getArrayRisorse() {
		return arrayRisorse;
	}

	
	
	@Override
	public int getGiaInPrestito() {
		// TODO Auto-generated method stub
		return 0;
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
	public void tornaDalPrestito() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mandaInPrestito() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCodiceUnivoco() {
		// TODO Auto-generated method stub
		return 0;
	}


	

	


	
	
	
}
