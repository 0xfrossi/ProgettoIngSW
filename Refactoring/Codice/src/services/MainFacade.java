package services;

import controller.ControllerFacade;
import handlers.handlersMenu.MenuPrinicipaleHandler;
import model.FilmsModel;
import model.FruitoriModel;
import model.LibriModel;
import model.PrestitiModel;
import model.StoricoModel;

public class MainFacade {

	
	private ControllerFacade controller;
	private StartManager start;
	private SavesManager saves;
	private ScadenzeSevice scadenze;
	private MenuPrinicipaleHandler menuP;


	
	
	public MainFacade(LibriModel libriM,FilmsModel filmsM,FruitoriModel fruitoriM,PrestitiModel prestitiM,StartManager start,StoricoModel storicoM) {
		
		this.start=start;
		this.controller= new ControllerFacade(libriM, filmsM, fruitoriM, prestitiM, start, storicoM);
		this.saves= new SavesManager(start);	
		menuP= new MenuPrinicipaleHandler(controller.getRisorseC(), controller.getFruitoriC(), saves, controller.getPrestitiC(),controller.getStoricoC());	
		scadenze = new ScadenzeSevice(controller.getFruitoriC(), controller.getPrestitiC(), saves);
		
	}
	
		
	public void run() {
		
		scadenze.servizioRimozione();
		menuP.menuPrincipale();
	}
	
	
	
	
}
