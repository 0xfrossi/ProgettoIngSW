package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import customExceptions.RisorsaNotFoundException;
import interfaces.Risorsa;

/**
 * Definisce l'elemento (sotto)categoria
 * @author Francesco Rossi
 *
 */
public class CategoriaModel extends Risorsa implements Serializable {


		//Attributi
		private static final long serialVersionUID = 1L;
		private ArrayList<Risorsa> arrayRisorse=new ArrayList<Risorsa>();
		private ArrayList<Risorsa> risorseRimosse=new ArrayList<Risorsa>();
		
		/**
		 * Costruttore default
		 */
		public CategoriaModel() {
			
		} 
		
			
		/**
		 * Controlla se una risorsa e' gia' presente attraverso un codice
		 * @param r la risorsa per la quale verificare l'esistenza
		 * @return true se esiste gi� in archivio
		 *		   false altrienti
		 */
		public boolean checkRisorsa(Risorsa r) {
			boolean check=false;
		
			for(int i=0; i<arrayRisorse.size();i++) {
				if(arrayRisorse.get(i).getCodiceUnivoco()==r.getCodiceUnivoco() )
					check=true;
			}
			if(check==true)
				return true;
			
			else 
				return false;
		}
		
		/**
		 * ragruppa le risorse in sottoCategorie diverse in un unico array
		 * @return l'array di risorse totali
		 */
		public ArrayList<Risorsa> arrayUniforme(){
			
			ArrayList<Risorsa> tutti=new ArrayList<>();
			for(int i=0; i<arrayRisorse.size();i++)
					tutti.add(arrayRisorse.get(i));
			return tutti;
		}
		
		
		/**
		 * Aggiunge una risorsa in una sottoCategoria 
		 * @param r la risorsa da aggiungere
		 * @return 0 add, -1 gia'presente
		 */
		public int checkAddInSotto(Risorsa r) {
			
			
			if(arrayRisorse.isEmpty()) 
				//arrayRisorse.add(r);
				return 0;	
			else {
					if(checkRisorsa(r))
						return -1;
						//System.out.println("Risorsa gia' presente");	
					
					return 0;
			}
		}
		

			
		/**
		 * verifica se una risorsa e' gia' stata inserita in una sottocategoria
		 * @param codice il codice della risorsa da verificare
		 * @return true se esiste gia' false altriementi
		 */
		public boolean esisteInSotto(int codice) {
			
			for(int i=0; i<arrayRisorse.size();i++) {
				if(arrayRisorse.get(i).getCodiceUnivoco()==codice )
					return true;
			}	
			return false;
		}
		
		
		/**
		 * Rimuove una risorsa dall'archivio attraverso il codice univoco
		 * @param codice il codice della risorsa da rimuovere
		 * @return indice
		 */
		public int estraiIndiceDaRimuovere(int codice) {
					
			int indice=-1;
			for (int i = 0; i <arrayRisorse.size(); i++){
				if(arrayRisorse.get(i).getCodiceUnivoco()==codice) {
					indice=i;
				}
			}
			
			return indice;
		}	
		
		
		/**
		 * rimuovi risosa da arrayList dato indice
		 * @param indice posizione da rimuovere
		 * @throws RisorsaNotFoundException se non e'presente
		 */
		public void rimuoviRisorsa(int indice) throws RisorsaNotFoundException {
				
			if(indice != -1) {
				risorseRimosse.add(arrayRisorse.get(indice));
				arrayRisorse.remove(indice);
			}
			else throw new RisorsaNotFoundException();
		}
		
		
		
			
		public boolean rimuoviRisorsaTest(int indice) {
			
			if(indice != -1) {
				risorseRimosse.add(arrayRisorse.get(indice));
				arrayRisorse.remove(indice);
				return true;
			}
			else return false;
		}
		
		
		/**
		 * Aggiunge una risorsa in arrayRisorse
		 * @param c risorsa da aggiungere
		 */
		public void add (Risorsa c){
					arrayRisorse.add(c);
				
		}
		
		/**
		 * Rimuove una risorsa da arrayRisorse
		 * @param c risorsa da rimuovere
		 */
		public void remove(Risorsa c) {
			arrayRisorse.remove(c);
		}
		
		
		
		
	//GETTERS
	//Override di alcuni metodi non usati

		
		public ArrayList<Risorsa> getRisorseRimosse() {
			return risorseRimosse;
		}

		public ArrayList<Risorsa> getArrayRisorse() {
			return arrayRisorse;
		}

		

		

		@Override
		public int getCodiceUnivoco() {
			// TODO Auto-generated method stub
			return 0;
		}

		

		@Override
		public String getNome() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ArrayList<String> getAutori() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getNumLicenze() {
			// TODO Auto-generated method stub
			return 0;
		}



		@Override
		public ArrayList<String> getAttori() {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public boolean equals(Risorsa risorsa) {
			// TODO Auto-generated method stub
			return false;
		}



		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void inizioPrestito() {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void finePrestito() {
			// TODO Auto-generated method stub
			
		}


		@Override
		public GregorianCalendar getDataInizioPrestito() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public GregorianCalendar getDataFinePrestito() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public GregorianCalendar getDataRichiestaProroga() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public boolean getProrogaOk() {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public int getInPrestito() {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public int getGiorniDurataPrestito() {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public int getGiorniDurataProroga() {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public int getGiorniPrimaPerProroga() {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public int getPrestitiMax() {
			// TODO Auto-generated method stub
			return 0;
		}
	
		
		
		
		
	
}
