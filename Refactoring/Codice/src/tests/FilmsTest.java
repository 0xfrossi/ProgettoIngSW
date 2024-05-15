package tests;


import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;
import interfaces.Risorsa;
import model.FilmModel;
import model.FilmsModel;

class FilmsTest {

	
	FilmsModel films = new FilmsModel();
	FilmModel film1 = new FilmModel("Harry Potter",  new GregorianCalendar(2000,5,5), "JJ A", creaAttori1(), 5, 111, "fantasy");
	FilmModel film2 = new FilmModel("Star wars",  new GregorianCalendar(2000,5,5), "JJ A", creaAttori2(), 5, 222, "fantasy");

	
	
	public ArrayList<String> creaAttori1(){
		
		  ArrayList<String> autori=new ArrayList<>();
		  autori.add("Daniel");
		  autori.add("Emma");
			return autori;
	 }
	
	public ArrayList<String> creaAttori2(){
		
		  ArrayList<String> autori=new ArrayList<>();
		  autori.add("michael");
		  autori.add("jhon");
			return autori;
	 }
	
	
	
	@Test
	public void cercaPerAttoreTest() {
		
		films.getFilmsIta().add(film1);
		films.getFilmsIng().add(film2);
		ArrayList<Risorsa> trovati = films.cercaFilmPerAttore("Daniel"); //libro1
		//libro1 = codice 111
		 assertEquals(111,trovati.get(0).getCodiceUnivoco());
	}
	
	
	@Test
	public void cercaPerAttoreFailTest() {
		
		films.getFilmsIta().add(film1);
		films.getFilmsIng().add(film2);
		ArrayList<Risorsa> trovati = films.cercaFilmPerAttore("Matteo");
		
		 assertEquals(true,trovati.isEmpty());
	}
	
	
	
	
	
}
