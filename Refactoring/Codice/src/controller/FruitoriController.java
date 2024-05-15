package controller;

import customExceptions.UserNonDisponibileException;
import customExceptions.outOfAgeException;
import model.FruitoreModel;
import model.FruitoriModel;
import myUtil.MyUtil;
import view.FruitoriView;

/**
 * Controller classe fruitori
 * @author Francesco
 *
 */
public class FruitoriController {

	
	private FruitoriModel fruitori;
	
	/**
	 * Costruttore
	 * @param fruitori model
	 */
	public FruitoriController(FruitoriModel fruitori) {
		
		this.fruitori=fruitori;
	}
	
	
	
	/**
	 * Crea un fruiore e nel caso lo aggiunge all'arrayList
	 */
	public void CreaFruitrore(){
		
		FruitoreModel fruitore= new FruitoreModel(MyUtil.leggiStringaNonVuota(FruitoriView.insNome()), MyUtil.leggiStringaNonVuota(FruitoriView.insCogome()), 
									MyUtil.leggiData( FruitoriView.insGregorian()),MyUtil.leggiStringaNonVuota(FruitoriView.insResidenza()), 
									MyUtil.leggiStringaNonVuota(FruitoriView.insUser()), MyUtil.leggiStringaNonVuota(FruitoriView.insPass()));	
		try {
			
			if(fruitori.addFruitoreIsPossible(fruitore)) {
				
				fruitori.addFruitore(fruitore);
				FruitoriView.iscrizioneEffetuata();
			
			}			
		} catch (UserNonDisponibileException e) {
			
			FruitoriView.printExcept(e.getMessage());
			
		} catch (outOfAgeException e) {
			
			FruitoriView.printExcept(e.getMessage());
		}	
	}
	
	
	/**
	 * Esegui l'accesso al sistema
	 * @return l'utente "loggato"
	 * @throws NullPointerException nel caso il log fallisca torna null
	 */
	public FruitoreModel accesso()  throws NullPointerException{
		
		try {
			FruitoreModel log= fruitori.trovaFruitore(MyUtil.leggiStringaNonVuota(FruitoriView.insUser()), MyUtil.leggiStringaNonVuota(FruitoriView.insPass()));
			if(log!=null) {
				FruitoriView.benvenuto();						
			}
			else {
				FruitoriView.fruitoreNull();				
			}
			return log;	
			
		}catch (NullPointerException e) {
			FruitoriView.fruitoreNull();
			return null;
		}
		
	}
	
	
	/**
	 * Stampa tutti i fruitori nell'arrayList
	 */
	public void stampaFruitori() {
		
		if(!fruitori.getFruitori().isEmpty()) {
			for(FruitoreModel f: fruitori.getFruitori()) 
				stampaFruitore(f);
		}else
				FruitoriView.noFruitori();
			
	}
	
	
	/**
	 * Stampa dati del fruitore passato come argomento
	 * @param f l'argomento
	 */
	public void stampaFruitore(FruitoreModel f) {
		
		FruitoriView.stampadati(f.toString());
		
	}
	
	
	/**
	 * rinnova l'iscrizione se possibile 
	 * @param fruitore il fruitore da rinnovare
	 */
	public void rinnovaFruitore(FruitoreModel fruitore){
		
		if(fruitore.statoRinnovo()) {
			fruitore.rinnovoIscrizione();
			fruitori.getFruitoriRinnovatiStorico().add(fruitore);
			FruitoriView.iscrizioneRinnovata(fruitore.getDataScadenzaIscrizione(), fruitore.getDataRinnovoIscrizione());
		}
		 else
			FruitoriView.rinnovoNonDisp();
		
	}



	
	
	public FruitoriModel getFruitori() {
		return fruitori;
	}	
	
	
}
