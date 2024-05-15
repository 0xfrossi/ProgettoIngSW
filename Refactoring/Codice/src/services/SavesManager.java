package services;

import myUtil.IOFileUtil;

/**
 * Classe che si occupa di Salvataggi su file di oggetti
 * @author Francesco
 *
 */
public class SavesManager {

	private StartManager start;
	
	/**
	 * Costruttore
	 * @param start lo starter
	 */
	public SavesManager(StartManager start) {
		this.start=start;
	}
	
	
	
	//Metodi per il salvataggio di oggetti su files appropriati
	
	public void salvaFruitori() {
		IOFileUtil.salvaSingoloOggetto(start.getFileFruitori(), start.getFruitori(), false);	
	}

	public void salvaPrestiti() {
		IOFileUtil.salvaSingoloOggetto(start.getFilePrestiti(), start.getPrestiti(), false);	
	}
	
	public void salvaLibri() {
		IOFileUtil.salvaSingoloOggetto(start.getFileLibri(), start.getLibri(), false);	
	}
	
	public void salvaFilms() {
		IOFileUtil.salvaSingoloOggetto(start.getFileFilms(), start.getFilms(), false);	
	}
	
	
	public void salvaRisorse() {
		salvaFilms();
		salvaLibri();
	}


	
	//Getter
	public StartManager getStart() {
		return start;
	}
	
	
}

