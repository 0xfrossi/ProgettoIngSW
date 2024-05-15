package services;

import java.io.File;
import java.io.IOException;
import model.FilmModel;
import model.FilmsModel;
import model.FruitoriModel;
import model.LibriModel;
import model.LibroModel;
import model.PrestitiModel;
import myUtil.IOFileUtil;
import interfaces.Risorsa;
/**
 * Classe che contiene i servizi che lavorano in background in fase di avvio
 * @author Francesco
 *
 */
public class StartManager {
	
	//Attributi
	private PrestitiModel prestiti;
	private LibriModel libri;
	private FilmsModel films;
	private FruitoriModel fruitori;
	
	private File filePrestiti;
	private File fileLibri;
	private File fileFilms;
	private File fileFruitori;
	
	
	/**
	 * Costruttore che inizializza oggetti e file per op. di r/w
	 */
	public StartManager() {
		prestiti=new PrestitiModel();
		libri= new LibriModel();
		films= new FilmsModel();
		fruitori= new FruitoriModel();
		filePrestiti = new File("Prestiti.dat");
		fileLibri=new File("Libri.dat");
		fileFilms= new File("Films.dat");
		fileFruitori = new File("Fruitori.dat");
		start();
	}
	
	/**
	 * controlla che se i file sono già presenti e in caso uso quelli (per il caicamento)
	 */
	private void inizializza() {
				
		try{
			IOFileUtil.checkFile(filePrestiti, prestiti);
			IOFileUtil.checkFile(fileLibri, libri);
			IOFileUtil.checkFile(fileFilms, films);
			IOFileUtil.checkFile(fileFruitori, fruitori);	
		}	
		catch (IOException e){
		
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * Carico oggetti da file
	 */
	private void caricaOggetti() {
		
		fruitori = (FruitoriModel)IOFileUtil.caricaSingoloOggetto(fileFruitori);
		libri = (LibriModel)IOFileUtil.caricaSingoloOggetto(fileLibri);
		prestiti= (PrestitiModel) IOFileUtil.caricaSingoloOggetto(filePrestiti);
		films =(FilmsModel) IOFileUtil.caricaSingoloOggetto(fileFilms);
	}
	
	/**
	 * metodo con op. di avvio
	 */
	public void start() {
		
		inizializza();
		caricaOggetti();
		reloadArrayPrestiti();
		
	}
	
	/*
	public void salvaFruitori() {
		IOFileUtil.salvaSingoloOggetto(fileFruitori, fruitori, false);	
	}

	public void salvaPrestiti() {
		IOFileUtil.salvaSingoloOggetto(filePrestiti, prestiti, false);	
	}
	
	public void salvaLibri() {
		IOFileUtil.salvaSingoloOggetto(fileLibri, libri, false);	
	}
	
	public void salvaFilms() {
		IOFileUtil.salvaSingoloOggetto(fileFilms, films, false);	
	}
	
	public void salvaRisorse() {
		salvaFilms();
		salvaLibri();
	}
	
	*/
	
	/**
	 * "Ricrea" l'array dei prestiti delle risorse perche' con la ricarica del file non puntano piu' agli stessi oggetti (????)
	 */
	public  void reloadArrayPrestiti(){
		if(!getPrestiti().getPrestiti().isEmpty()) {
			for(int i=0; i<prestiti.getPrestiti().size();i++){
				if(prestiti.getPrestiti().get(i).getRisorsa() instanceof LibroModel ){
					for(Risorsa libro :libri.getLibriIng().getArrayRisorse()) {
				
						if(prestiti.getPrestiti().get(i).getRisorsa().getCodiceUnivoco()==libro.getCodiceUnivoco())
							prestiti.getPrestiti().get(i).setRisorsa(libro);	
					}
					for(Risorsa lib :libri.getLibriIta().getArrayRisorse()) {
					
						if(prestiti.getPrestiti().get(i).getRisorsa().getCodiceUnivoco()==lib.getCodiceUnivoco())
							prestiti.getPrestiti().get(i).setRisorsa(lib);
					}
				}
				else if(prestiti.getPrestiti().get(i).getRisorsa() instanceof FilmModel){
						for(Risorsa film : films.getFilmsIng().getArrayRisorse()){
							if(prestiti.getPrestiti().get(i).getRisorsa().getCodiceUnivoco()==film.getCodiceUnivoco())
								prestiti.getPrestiti().get(i).setRisorsa(film);
						}
						for(Risorsa fil : films.getFilmsIta().getArrayRisorse()){
							if(prestiti.getPrestiti().get(i).getRisorsa().getCodiceUnivoco()==fil.getCodiceUnivoco())
								prestiti.getPrestiti().get(i).setRisorsa(fil);
						}	
				}
			}
		}
		else 
			return;
	}

	
	// GETTERS
	
	public PrestitiModel getPrestiti() {
		return prestiti;
	}

	public LibriModel getLibri() {
		return libri;
	}

	public FilmsModel getFilms() {
		return films;
	}

	public FruitoriModel getFruitori() {
		return fruitori;
	}

	public File getFilePrestiti() {
		return filePrestiti;
	}

	public File getFileLibri() {
		return fileLibri;
	}

	public File getFileFilms() {
		return fileFilms;
	}

	public File getFileFruitori() {
		return fileFruitori;
	}
	
	
	
	
}
