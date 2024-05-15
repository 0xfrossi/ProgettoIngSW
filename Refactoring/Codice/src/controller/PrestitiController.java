package controller;

import java.util.ArrayList;
import customExceptions.PrestitoIsNotPossibleException;
import interfaces.Risorsa;
import model.FruitoreModel;
import model.PrestitiModel;
import model.PrestitoModel;
import myUtil.MyUtil;
import view.PrestitiView;
import view.RisorseView;

public class PrestitiController {

	private PrestitiModel prestitiM;
	
	
	public PrestitiController(PrestitiModel prestitiM) {
		
		this.prestitiM=prestitiM;
		
	}
	
	
	
	public void chiediPrestito(FruitoreModel f, Risorsa r)  {
		
		if(r==null) {
			RisorseView.RisorsaNonPresente();
		}
		else {
			try {
				prestitiM.addPrestito(f, r);
				PrestitiView.prestitoEseguito();
			
			}
			catch(PrestitoIsNotPossibleException e) {
				RisorseView.StampaInfo(e.getMessage());
			}
		}
	}
	
	
	public void stampaPrestiti() {
		
		
		if(!prestitiM.getPrestiti().isEmpty()){
			for(PrestitoModel p: prestitiM.getPrestiti()) {
				PrestitiView.StampaInfo(p.toString());
				PrestitiView.spazio();
			}
		}else
			PrestitiView.prestitiVuoti();
		
	}
	
	
	public void stampaPrestitiUtente(ArrayList<PrestitoModel> prestitiUtente) {
		
			
			if(!prestitiUtente.isEmpty()) {
				for(int i=0; i< prestitiUtente.size();i++) {
				
					PrestitiView.StampaInfo( (i+1)+"." + prestitiUtente.get(i).toString());
					PrestitiView.spazio();
					}
			}
			else
				PrestitiView.prestitiVuoti();
		
	}
	
	
	/**
	 * Seleziona un prestito
	 * @param fruitore che seleziona
	 * @return il prestito selezionato
	 */
	public PrestitoModel selezionaPrestito(FruitoreModel fruitore)  throws NullPointerException{
		
		int selezionato;
		ArrayList<PrestitoModel> lista=prestitiM.filtraPrestitiPerUser(fruitore);
		if(lista.isEmpty())
			return null;
		
		stampaPrestitiUtente(lista);
		selezionato = MyUtil.leggiIntero(PrestitiView.selezionaPrestito(), 1, lista.size());		
		PrestitoModel prestitoSelezionato = lista.get(selezionato-1);
		return prestitoSelezionato;
	}
	
	
	
	
	public void rinnovaPrestito(FruitoreModel fruitore) {
		
		PrestitiView.mostraPrestiti();
		PrestitoModel selezionato= selezionaPrestito(fruitore);
		if(selezionato==null)
			PrestitiView.nessunRinnovoPrestiti();
		else if(selezionato.isRinnovabile()) {
					selezionato.eseguiProroga();
					prestitiM.getPrestitiProrogatiStorico().add(selezionato);
		}
		else
			PrestitiView.nessunRinnovoPrestiti();
	}
	
	
	public void annullaPrestito(PrestitoModel p ) {
		if(p==null) 
			PrestitiView.prestitiVuoti();
		
		else {
			int pos= prestitiM.posizonePrestitoDaAnnullare(p.getFruitore(), p.getRisorsa());
			if(pos==-1)
				PrestitiView.noAnnullamento();
			else {
				prestitiM.annullaPrestito(pos);
				PrestitiView.prestitoAnnullato();
			}
		}
	}
	
	

	public PrestitiModel getPrestitiM() {
		return prestitiM;
	}
	
	
	
	
}
