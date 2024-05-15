package controller;


import model.FilmsModel;
import model.FruitoriModel;
import model.LibriModel;
import model.PrestitiModel;
import model.StoricoModel;
import services.SavesManager;
import services.StartManager;

/**
 * Classe che raggruppa tutti i controller in uno solo
 * @author Francesco
 *
 */
public class ControllerFacade {

	//Attributi
	private RisorseController risorseC;
	private FruitoriController fruitoriC;
	private PrestitiController prestitiC;
	private StoricoController storicoC;
	private SavesManager saves;
	
	/**
	 * Costruttore 
	 * @param libriM model 
	 * @param filmsM model
	 * @param fruitoriM model
	 * @param prestitiM model
	 * @param start servizio di avvio
	 * @param storicoM model
	 */
	public ControllerFacade(LibriModel libriM,FilmsModel filmsM,FruitoriModel fruitoriM,PrestitiModel prestitiM,StartManager start,StoricoModel storicoM) {
		
		risorseC= new RisorseController(filmsM, libriM);
		fruitoriC= new FruitoriController(fruitoriM);
		prestitiC= new PrestitiController(prestitiM);
		storicoC= new StoricoController(storicoM);	
		this.saves= new SavesManager(start);
		
	}


	//GETTERS
	
	public RisorseController getRisorseC() {
		return risorseC;
	}

	public FruitoriController getFruitoriC() {
		return fruitoriC;
	}

	public PrestitiController getPrestitiC() {
		return prestitiC;
	}

	public StoricoController getStoricoC() {
		return storicoC;
	}

	public SavesManager getSaves() {
		return saves;
	}
	
		
	
}
