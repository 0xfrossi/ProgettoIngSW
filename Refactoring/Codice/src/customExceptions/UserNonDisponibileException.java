package customExceptions;

public class UserNonDisponibileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNonDisponibileException() {
		super("UserName non disponibile \n");
	}
	
	
}
