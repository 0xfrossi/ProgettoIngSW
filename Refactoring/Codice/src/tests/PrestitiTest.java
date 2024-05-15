package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import customExceptions.PrestitoIsNotPossibleException;
import interfaces.Risorsa;
import model.FilmModel;
import model.FilmsModel;
import model.FruitoreModel;
import model.FruitoriModel;
import model.LibriModel;
import model.LibroModel;
import model.PrestitiModel;
import model.PrestitoModel;
import myUtil.MyUtil;

class PrestitiTest {

	GregorianCalendar dataDiNascita = new GregorianCalendar(1995, 5, 11);
	GregorianCalendar dataIscrizione = MyUtil.dataCorrente();
			
	FruitoreModel f= new FruitoreModel("marco", "bianchi", dataDiNascita, "bs", "user", "password");
	FruitoreModel f2= new FruitoreModel("luca", "rossi", dataDiNascita, "bs", "user2", "password2");
	
	FruitoriModel fruitori= new FruitoriModel();
	
	 LibriModel libri= new LibriModel();
	 Risorsa libro1= new LibroModel("BiancaNeve", 111, 1, creaAutori1(), 100, "mondadori", "fantasy", new GregorianCalendar(2000,5,5));
	 Risorsa libro2= new LibroModel("Commedia", 222, 2, creaAutori1(), 100, "mondadori", "fantasy", new GregorianCalendar(2000,5,5));
	
	 FilmsModel films = new FilmsModel();
	 FilmModel film1 = new FilmModel("Harry Potter",  new GregorianCalendar(2000,5,5), "JJ A", creaAutori1(), 3, 333, "fantasy");
	 
	 PrestitoModel prestito1= new PrestitoModel(f, libro1);
	 PrestitiModel prestiti= new PrestitiModel();
	 
	 
	 
	 
	 
	 public ArrayList<String> creaAutori1(){
		  ArrayList<String> autori=new ArrayList<>();
			 autori.add("Harry");
			 return autori;
	 }
	 
	 
	 
	 @Test
	 public void isRinnovabileTest() {
			 
		 assertEquals(false, prestito1.isRinnovabile());
	 }
	 
	 
	 
	 @Test
	 public void addPrestitoTest() throws PrestitoIsNotPossibleException {
		 
		prestiti.addPrestito(f, libro1);
		 
		 assertEquals(1,prestiti.getPrestiti().size() );
	 }
	 
	 
	 @Test
	 public void prestitoIsPossibleFailTest() throws PrestitoIsNotPossibleException {
		 
		 //inserisco prestito libro1 con 1 licenza
		 prestiti.addPrestito(f, libro1);
		 
		 //verifico disponibilita' prestito di libro1 con unica licenza per un secondo prestito
		 assertEquals(false, prestiti.prestitoIsPossible(f2, libro1));
		 
	 }
	 
	 @Test
	 public void prestitoIsPossibleTest() throws PrestitoIsNotPossibleException {
		 
		 //inserisco prestito film1 con 3 licenze
		 prestiti.addPrestito(f, film1);
		 
		 //verifico disponibilita' prestito di film1 con 3 licenze per un secondo prestito
		 assertEquals(true, prestiti.prestitoIsPossible(f2, film1));
		 
	 }
	 
	 
	 
	 @Test
	 public void posizionePrestitoDaAnnullareTest() throws PrestitoIsNotPossibleException {
		
		 prestiti.addPrestito(f, film1);
		 prestiti.addPrestito(f2, film1);
		 assertEquals(0, prestiti.posizonePrestitoDaAnnullare(f, film1));
		 
	 }
	 
	 @Test
	 public void posizionePrestitoDaAnnullareFailTest() throws PrestitoIsNotPossibleException {
		
		 prestiti.addPrestito(f, film1);
		 prestiti.addPrestito(f2, film1);
		 
		 assertEquals(-1, prestiti.posizonePrestitoDaAnnullare(f, libro1));
		
		 
	 }
	 
	 
	 
	@Test
	public void annullaPrestitoTest() throws PrestitoIsNotPossibleException {
	 
		 prestiti.addPrestito(f, film1);
		 int pos=prestiti.posizonePrestitoDaAnnullare(f, film1);
		 prestiti.annullaPrestito(pos);
		 
		 assertEquals(true, prestiti.getPrestiti().isEmpty());
	}
	 
	
	@Test
	public void annullaPrestitoFailTest() throws PrestitoIsNotPossibleException {
	 
		 prestiti.addPrestito(f, film1);
		 int pos=prestiti.posizonePrestitoDaAnnullare(f, libro1);
		 prestiti.annullaPrestito(pos);
		 
		 assertEquals(1, prestiti.getPrestiti().size());
	}
	 
	
	@Test
	public void contaPrestitiTest() throws PrestitoIsNotPossibleException {
		
		 prestiti.addPrestito(f, libro1);
		 prestiti.addPrestito(f, libro2);
		 
		int num= prestiti.contaPrestitiUtente(f, "LibroModel");
		
		 assertEquals(2, num);
		 
		
	}
	
	@Test
	public void prestitoNotExistTest() throws PrestitoIsNotPossibleException {
		
		 prestiti.addPrestito(f, libro1);
		 prestiti.addPrestito(f, libro2);
		 assertEquals(true, prestiti.prestitoNotExist(f, film1));
		 
	}
	
	@Test
	public void prestitoNotExistFailTest() throws PrestitoIsNotPossibleException {
		
		 prestiti.addPrestito(f, libro1);
		 prestiti.addPrestito(f, libro2);
		 assertEquals(false, prestiti.prestitoNotExist(f, libro1));
		 
	}
	 
	
	@Test
	public void filtraPerUserTest() throws PrestitoIsNotPossibleException {
		
		 prestiti.addPrestito(f, libro1);
		 prestiti.addPrestito(f, libro2);
		 prestiti.addPrestito(f2, film1);
		 ArrayList<PrestitoModel> vett= prestiti.filtraPrestitiPerUser(f);
		 assertEquals(2, vett.size());
		 
	}
	
	
	
	
	
	
	
	
	
	
	
}
