package ing.unibs.it;

import java.io.Serializable;

public abstract class Risorsa implements Serializable {
	
	
	
	  
	
	private static final long serialVersionUID = 1L;
	
	public Risorsa() {
	
	}

	public abstract String getNome();
	public abstract int getCodiceUnivoco();
	
	public abstract void stampaDesc();
	
	public abstract void add(Risorsa c);
	
	public abstract void remove(Risorsa c);
	
	
}
