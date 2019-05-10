package ing.unibs.it;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Risorsa implements Serializable, Loanable {
	
	
	
	  
	
	private static final long serialVersionUID = 1L;
	
	
	public Risorsa() {
		
	}

	public abstract  int getCodiceUnivoco();
	
	public abstract String getNome();

	public  abstract void stampaDesc();
	
	public  abstract void add(Risorsa c);
	
	public  abstract void remove(Risorsa c);
	
	public  abstract ArrayList<String> getAutori();
	
	
	
}
