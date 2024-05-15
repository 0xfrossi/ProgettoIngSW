package customExceptions;

public class outOfAgeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	
	public outOfAgeException() {
		
		super("Accesso consentito solo ad utenti maggiorenni \n");
	}

	
	@Override
	public String toString() {
		return getMessage();
	};
		
		
}
