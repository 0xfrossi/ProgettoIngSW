package customExceptions;

public class RisorsaNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public RisorsaNotFoundException() {
		super("Nessuna risorsa trovata, per completare l'operazione \n");
	}
	
	
	
}
