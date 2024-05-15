package customExceptions;

public class PrestitoIsNotPossibleException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	
	public PrestitoIsNotPossibleException() {
		super("Il Prestito non e' possibile, controlla se hai gia'la risorsa o se hai gia'raggunto il num. max di risorse prestabili \n ");
	}

	
	@Override
	public String toString() {
		return getMessage();
	}
	
	
}
