package tests;


import static org.junit.Assert.assertEquals;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;
import customExceptions.UserNonDisponibileException;
import customExceptions.outOfAgeException;
import model.FruitoreModel;
import model.FruitoriModel;

class FruitoriTest {

	
	
	FruitoriModel fruitori = new FruitoriModel();
	
	GregorianCalendar dataDiNascita = new GregorianCalendar(1995, 5, 11);
	GregorianCalendar dataDiNascitaNn = new GregorianCalendar(2005, 5, 11);
	FruitoreModel fruitore1 = new FruitoreModel("marco", "bianchi", dataDiNascita, "bs", "user", "password");
	FruitoreModel fruitore2 = new FruitoreModel("luca", "bianchi", dataDiNascita, "bs", "user1", "password1");
	FruitoreModel fruitore3 = new FruitoreModel("luca", "bianchi", dataDiNascitaNn, "bs", "user1", "password1");
	
	@Test
	public void UserPresenteTest() {
		
		fruitori.addFruitore(fruitore1);
		fruitori.addFruitore(fruitore2);
		
		assertEquals(false,fruitori.checkUsername("user"));
	}

	@Test
	public void UserNNPresenteTest() {
		
		fruitori.addFruitore(fruitore1);
		fruitori.addFruitore(fruitore2);
		
		assertEquals(true,fruitori.checkUsername("qwerty"));
	}
	
	@Test
	public void addFruitoreIsPossibleTest() throws outOfAgeException, UserNonDisponibileException {
		
		assertEquals(true,fruitori.addFruitoreIsPossible(fruitore1));
	}
	
	
	@Test
	public void trovaFuitoreTest() {
		fruitori.addFruitore(fruitore1);
		fruitori.addFruitore(fruitore2);
		assertEquals(fruitore1,fruitori.trovaFruitore("user", "password"));
	}
	
	
	@Test
	public void TrovaFuitoreFailTest() {
		fruitori.addFruitore(fruitore1);
		fruitori.addFruitore(fruitore2);
		assertEquals(null,fruitori.trovaFruitore("user23", "password"));
	}
	
	@Test
	public void utentiScadutiTest() {
		fruitori.addFruitore(fruitore1);
		fruitori.addFruitore(fruitore2);
		assertEquals(true,fruitori.utentiScaduti().isEmpty());
	}
	
	@Test
	public void utenteScadutoTest() {
		fruitori.addFruitore(fruitore1);
		fruitori.addFruitore(fruitore2);
		//imposto la scadenza allo scorso anno
		fruitori.getFruitori().get(0).setDataScadenzaIscrizione(new GregorianCalendar(2018, 5, 11));
		
		assertEquals(fruitore1,fruitori.utentiScaduti().get(0));
	}
	
	@Test
	public void removeScaduti() {
		
		fruitori.addFruitore(fruitore1);
		
		//imposto la scadenza allo scorso anno
		fruitori.getFruitori().get(0).setDataScadenzaIscrizione(new GregorianCalendar(2018, 5, 11));
		fruitori.rimuoviIscrizioni(fruitori.utentiScaduti());
		assertEquals(true,fruitori.getFruitori().isEmpty());
	}
	
	
	
	
	
	
}
