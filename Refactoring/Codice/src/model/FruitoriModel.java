package model;

import java.io.Serializable;
import java.util.ArrayList;
import customExceptions.UserNonDisponibileException;
import customExceptions.outOfAgeException;
import myUtil.MyUtil;


/**
 * Gestisce l'arrayList di fruitori
 * @author Francesco
 *
 */
public class FruitoriModel implements Serializable {
		
		//Attributi
		private static final long serialVersionUID = 5762203246975649666L;
		private  ArrayList<FruitoreModel> fruitori ;
		private  ArrayList<FruitoreModel>  fruitoriStorico;
		private  ArrayList<FruitoreModel> fruitoriScadutiStorico;
		private  ArrayList<FruitoreModel> fruitoriRinnovatiStorico;
			
		/**
		 * Costruttore che inizializza il vettore fruitori
		 */
		 public FruitoriModel() {
			
			fruitori = new ArrayList <FruitoreModel>();
			fruitoriStorico = new ArrayList <FruitoreModel>();
			fruitoriScadutiStorico = new ArrayList <FruitoreModel>();
			fruitoriRinnovatiStorico = new ArrayList <FruitoreModel>();
		}
		 
		
		 /**
		 * controlla se un userName e' gia' in uso
		 * @param user l'userName da verificare
		 * @return false se disponibile, true altrimenti
		 */
		public boolean checkUsername(String user) {
							
			if(fruitori.size()==0)  return true;
			for(int i = 0; i<fruitori.size(); i++) {
				if(fruitori.get(i).getUsername().equals(user)) 
					return false;
			}
			return true;
		}

		
		/**
		 * Restituisce un fruitore date le credenziali
		 * @param username l' username
		 * @param pass la password
		 * @return Fruitore il fruitore corrispondente
		 * precondizione: username != null  
		 */
		public FruitoreModel selezionaUtente(String username, String pass){
			
			for(int i = 0; i < fruitori.size(); i++) {
				if(fruitori.get(i).getUsername().equals(username) && fruitori.get(i).getPassword().equals(pass))
					return fruitori.get(i);				
			}
			return null;	
		}	
		
		
		/**
		 * Controlla se il fruitore si puo' iscrivere
		 * @param fruitore il fruitore da esaminare
		 * @return true se e' soddisfa i requisiti, false altrimenti
		 * @throws outOfAgeException se utente non e' maggiorenne
		 * @throws UserNonDisponibileException se l'user e' gia'utilizzato
		 */
		public boolean addFruitoreIsPossible(FruitoreModel fruitore) throws outOfAgeException, UserNonDisponibileException  {
			if(fruitori.isEmpty()) { 
				if(MyUtil.maggiorenne(fruitore.getDataDiNascita())) {
					
					return true;
				}
				else throw new outOfAgeException();
			}
			else if(MyUtil.maggiorenne(fruitore.getDataDiNascita())) {
				
					if(checkUsername(fruitore.getUsername())) {
						
						return true;
					}
					else throw new UserNonDisponibileException() ;
			}
			else throw  new outOfAgeException();
		}
		
		
		
		/**
		 * agginugi un fruitore alla lista
		 * @param fruitore il fruitore da aggiungere
		 */
		public void addFruitore(FruitoreModel fruitore) {
			fruitori.add(fruitore);
			fruitoriStorico.add(fruitore);
		}
		
		
		/**
		 * controlla se le iscrizioni degli utenti sono scadute e in caso le raggruppa in un array
		 * @return array di utenti con iscrizione scaduta
		 */
		public ArrayList<FruitoreModel> utentiScaduti() {
			
			ArrayList<FruitoreModel> scaduti=new ArrayList();
			if(!fruitori.isEmpty()) {
				for (int i=0; i<fruitori.size(); i++){
					fruitori.get(i).ControlloDecadenzaFruitore();
					
					if(!fruitori.get(i).getStatoFruitore()) {
						scaduti.add(fruitori.get(i));
						fruitoriScadutiStorico.add(fruitori.get(i));
					}
				}
			}
			return scaduti;
		}
		
		
		/**
		 * rimuove dall'array dei fruitori quelli con l'iscrizione scaduta
		 * @param scaduti l'array di utenti da rimuovere
		 */
		public void rimuoviIscrizioni(ArrayList<FruitoreModel> scaduti){
			
			if(!scaduti.isEmpty()) {
				for(FruitoreModel fruitore: scaduti)
					fruitori.remove(fruitore);
			}
					
		}
		
		
		/**
		 * metodo finale per contollo utenti scaduti
		 */
		public void checkFruitoriScaduti() {
			
			rimuoviIscrizioni(utentiScaduti());
		}
		
			
		/**
		 * Torna un fruitore se i dati corrispondono (per accesso)
		 * @param user l'username da cercare
		 * @param pass la password da cercare
		 * @return il fruitore se trovato
		 * @throws NullPointerException se torna null
		 */
		public FruitoreModel trovaFruitore(String user, String pass) throws NullPointerException{
			
			for(FruitoreModel fruitore: fruitori) {
				if(fruitore.getUsername().equals(user) && fruitore.getPassword().equals(pass))
					return fruitore;
			}
			return null;
		}
		
	
		//Getter
		public ArrayList<FruitoreModel> getFruitori() {
			return fruitori;
		}


		public ArrayList<FruitoreModel> getFruitoriStorico() {
			return fruitoriStorico;
		}


		public ArrayList<FruitoreModel> getFruitoriScadutiStorico() {
			return fruitoriScadutiStorico;
		}


		public ArrayList<FruitoreModel> getFruitoriRinnovatiStorico() {
			return fruitoriRinnovatiStorico;
		}
		
				
		
}
