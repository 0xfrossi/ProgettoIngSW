package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import model.FruitoreModel;
import myUtil.MyUtil;

class FuitoreTest {
	
	
	GregorianCalendar dataDiNascita = new GregorianCalendar(1995, 5, 11);
	GregorianCalendar dataIscrizione = MyUtil.dataCorrente();
			
	FruitoreModel f= new FruitoreModel("marco", "bianchi", dataDiNascita, "bs", "user", "password");
	
	GregorianCalendar dataScadenza = new GregorianCalendar(dataIscrizione.get(GregorianCalendar.YEAR)+5, dataIscrizione.get(GregorianCalendar.MONTH), dataIscrizione.get(GregorianCalendar.DAY_OF_MONTH));
	
	
	@Test
	public void statoRinnovoTest() {
		
		assertEquals(false, f.statoRinnovo());
	}
	
		
	@Test
	public void calcoloDataInizioRinnovoTest() {
	
		GregorianCalendar data= new GregorianCalendar(dataScadenza.get(GregorianCalendar.YEAR),dataScadenza.get(GregorianCalendar.MONTH),dataScadenza.get(GregorianCalendar.DAY_OF_MONTH)-10);
	
		assertEquals(data, f.getDataRinnovoIscrizione());
		
	}
		
	@Test
	public void calcoloDataScadenzaTest() {
		
		assertEquals(dataScadenza, f.getDataScadenzaIscrizione());
	}
	
	
	@Test
	public void controlloDecadenzaTest() {
		FruitoreModel fruitore= new FruitoreModel("marco", "bianchi", dataDiNascita, "bs", "user", "password");
		
		fruitore.setDataScadenzaIscrizione(new GregorianCalendar(2019, 2, 11));
		fruitore.ControlloDecadenzaFruitore();
		
		assertEquals(false, fruitore.getStatoFruitore());
	}
	
	
	
	

}
