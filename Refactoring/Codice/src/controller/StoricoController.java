package controller;

import java.util.ArrayList;
import model.PrestitoModel;
import model.StoricoModel;
import myUtil.MyUtil;
import view.FruitoriView;
import view.StoricoView;

public class StoricoController {

	
	private StoricoModel storicoM;
	
	
	/**
	 * Costruttore
	 * @param storicoM model
	 */
	public StoricoController(StoricoModel storicoM) {
		
		this.storicoM=storicoM;
	}
	
	
	
	/**
	 * Mostra il num di prestiti di fruitore in un certo anno
	 */
	public void stampaNumPrestitiFruitoreInAnno(){
		
		String user=MyUtil.leggiStringaNonVuota(FruitoriView.insUser());

		StoricoView.numPrestitiAnnoFruitore(user, storicoM.numPrestitiFruitoreInAnno(user,MyUtil.leggiInteroConMinimo(StoricoView.insAnnoDiRif(),2000) ));
		
		
	}
	
	/**
	 * Stampa i prestiti in un dato anno
	 */
	public void stampaPrestitiInAnno() {
		
	try {			
			ArrayList<PrestitoModel> stampa= storicoM.prestitiInAnno(MyUtil.leggiInteroConMinimo(StoricoView.insAnnoDiRif(),2000));
			if(!stampa.isEmpty()) {
				for( int i=0; i< stampa.size();i++) 
					StoricoView.stampaDato(stampa.get(i).toString());
			}
			else
				StoricoView.nessunoStorico();
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}		
	}

	
	/**
	 * mostra il num do prestiti avvenuti in un dato anno
	 */
	public void stampaNumPrestitiAnno() {
		
	try {	
		if(!storicoM.getPrestiti().getPrestitiStorico().isEmpty()) 
			StoricoView.numPrestAnno(storicoM.numPrestitiInAnnoSolare(MyUtil.leggiInteroConMinimo(StoricoView.insAnnoDiRif(),2000)));
		
		else
			StoricoView.nessunoStorico();
	
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}	
	}
	
	
	/**
	 * Stampa il num di proroghe avvenute in un anno
	 */
	public void stampaProrogheAnno() {
		
	try {	
		if(!storicoM.getPrestiti().getPrestitiProrogatiStorico().isEmpty())
			StoricoView.numPrestAnno(storicoM.prorogheAnnoSolare(MyUtil.leggiInteroConMinimo(StoricoView.insAnnoDiRif(),2000)));
		else
			StoricoView.nessunoStorico();
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}
	}
	
	/**
	 * stampa la risorsa piu'richiesta
	 */
	public void stampaRisorsaPiuRichiesta() {
		
	try {
			String nome= storicoM.RisorsaPiuRichiesta(MyUtil.leggiInteroConMinimo(StoricoView.insAnnoDiRif(),2000));
			if(nome==null)
				StoricoView.noRisorsa();
			else
				StoricoView.risorsaPiuRic(nome);
			
		}catch(Exception e) {
			StoricoView.noRisorsa();
		}
	}

	
	
	/**
	 * stampa tutti i fruitori entrati nel sistema
	 */
	public void stampaFruitori() {
		
	try {
			if(!storicoM.getFruitori().getFruitoriStorico().isEmpty()) {	
				for(int i=0; i<storicoM.getFruitori().getFruitoriStorico().size();i++) {
					StoricoView.stampaDato(storicoM.getFruitori().getFruitoriStorico().get(i).toString());	
					System.out.println();
				}
		
		}else
			StoricoView.nessunoStorico();
			
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}
	}
	
	
	/**
	 * stampa fruitori rinnovati
	 */
	public void stampaRinnovati() {
	
	try {
		if(!storicoM.getFruitori().getFruitoriRinnovatiStorico().isEmpty()) {
			for(int i=0; i<storicoM.getFruitori().getFruitoriRinnovatiStorico().size();i++) {
				StoricoView.stampaDato(storicoM.getFruitori().getFruitoriRinnovatiStorico().get(i).toString());	
				System.out.println();
			}
		
		}else
			StoricoView.nessunoStorico();
		
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}
	}
	
	
	/**
	 * Stampa fruitori scaduti
	 */
	public void stampaScaduti() {
		
	try {
		if(!storicoM.getFruitori().getFruitoriScadutiStorico().isEmpty()) {	
			for(int i=0; i<storicoM.getFruitori().getFruitoriScadutiStorico().size();i++) {
				StoricoView.stampaDato(storicoM.getFruitori().getFruitoriScadutiStorico().get(i).toString());	
				System.out.println();
			}
		
		}else
			StoricoView.nessunoStorico();
		
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}
	
	}
	
	/**
	 * Stampa risorse prestabili in passato
	 */
	public  void stampaPrestabiliInPassato() {
		
	try {
		if(!storicoM.risorseRimosse().isEmpty()) {
			for(int i=0; i<storicoM.risorseRimosse().size();i++) {
				StoricoView.stampaDato(storicoM.risorseRimosse().get(i).toString());	
				System.out.println();
			}
		
		}else
			StoricoView.nessunoStorico();	
		
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}
	
	}
	
	
	/**
	 * Stampa prestiti passati
	 */
	public void stampaPrestiti() {
		
	try {
		if(!storicoM.getPrestiti().getPrestitiStorico().isEmpty()) {
			for(int i=0; i<storicoM.getPrestiti().getPrestitiStorico().size();i++) {
				StoricoView.stampaDato(storicoM.getPrestiti().getPrestitiStorico().get(i).toString());	
				System.out.println();
			}
		}else
			StoricoView.nessunoStorico();
		
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}
		
	}
	
	/**
	 * Stampa prestiti prorogati
	 */
	public void stampaProrogati() {
		
	try {
		if(!storicoM.getPrestiti().getPrestitiProrogatiStorico().isEmpty()) {
			for(int i=0; i<storicoM.getPrestiti().getPrestitiProrogatiStorico().size();i++) {
				StoricoView.stampaDato(storicoM.getPrestiti().getPrestitiProrogatiStorico().get(i).toString());	
				System.out.println();
			}
		}else
			StoricoView.nessunoStorico();
		
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}
		
	}
	
	/**
	 * Stampa prestiti finiti
	 */
	public void stampaPrestitiTerminati() {
		
	try {	
		if(!storicoM.getPrestiti().getPrestitiTerminatiStorico().isEmpty()) {
			for(int i=0; i<storicoM.getPrestiti().getPrestitiTerminatiStorico().size();i++) {
				StoricoView.stampaDato(storicoM.getPrestiti().getPrestitiTerminatiStorico().get(i).toString());	
				System.out.println();
			}
		
		}else
			StoricoView.nessunoStorico();
		
	}catch(Exception e) {
		StoricoView.noRisorsa();
		}			
	}

	
	
	
}
