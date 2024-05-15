package services;


import model.FilmsModel;
import model.FruitoriModel;
import model.LibriModel;
import model.PrestitiModel;
import model.StoricoModel;

public class Main {

	public static void main(String[] args) {
	
		//avvio lo start manager e associo gli oggetti model ai caricamenti 
		
		StartManager start= new StartManager();
		LibriModel libri= start.getLibri();
		FilmsModel films= start.getFilms();
		PrestitiModel prestiti= start.getPrestiti();
		FruitoriModel fruitori= start.getFruitori();		
		StoricoModel storico= new StoricoModel(fruitori, prestiti, libri, films);	
		
		
		MainFacade mainFacade = new MainFacade(libri, films, fruitori, prestiti, start,storico);
		
		//avvio
		mainFacade.run();
		
		
	}

}
