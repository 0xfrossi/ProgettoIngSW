package ing.unibs.it;

import java.io.Serializable;

public abstract class Risorsa implements Serializable, Loanable {
	
	
	
	  
	
	private static final long serialVersionUID = 1L;
	
	
	public Risorsa() {
		
	}

	public abstract  int getCodiceUnivoco();


	public  abstract void stampaDesc();
	
	public  void add(Risorsa c) {};
	
	public  void remove(Risorsa c) {};
	
	
	
}
