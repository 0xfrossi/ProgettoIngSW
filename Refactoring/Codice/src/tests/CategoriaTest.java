package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import interfaces.Risorsa;
import model.LibriModel;
import model.LibroModel;

class CategoriaTest {

	
//LIBRI O FILMS SONO ENTRAMBI BASATI SU CATEGORIE PER CUI, I SEGUENTI TEST, PRODURREBBERO GLI STESSI RISUTATI USANDO FILMS_MODEL	
	
 LibriModel libri= new LibriModel();
 Risorsa libro1= new LibroModel("BiancaNeve", 111, 3, creaAutori(), 100, "mondadori", "fantasy", new GregorianCalendar(2000,5,5));
 Risorsa libro2= new LibroModel("BiancaNeve", 111, 3, creaAutori(), 100, "mondadori", "fantasy", new GregorianCalendar(2000,5,5));
 Risorsa libro3= new LibroModel("Promessi Sposi", 222, 3, creaAutori(), 500, "mondadori", "classico", new GregorianCalendar(2000,5,5));
 Risorsa libro4= new LibroModel("divina commedia", 333, 3, creaAutori(), 500, "mondadori", "classico", new GregorianCalendar(2000,6,5));
 
 
 public ArrayList<String> creaAutori(){
	  ArrayList<String> autori=new ArrayList<>();
		 autori.add("Harry");
		 return autori;
 }
 
 
 
 @Test
 public void codicePresenteTest() {
	 
	 libri.getLibriIta().add(libro1);
	assertEquals( true,libri.getLibriIta().checkRisorsa(libro2));
 }
 
 
 @Test
 public void estraiIndDaRimuovere() {
	 
	 libri.getLibriIta().add(libro1);
	 libri.getLibriIta().add(libro3);
	 assertEquals(1, libri.getLibriIta().estraiIndiceDaRimuovere(222));
 }
 
 @Test
 public void estraiIndDaRimuovereFail() {
	 
	 libri.getLibriIta().add(libro1);
	 libri.getLibriIta().add(libro3);
	 assertEquals(-1, libri.getLibriIta().estraiIndiceDaRimuovere(000));
 }
 
 
 @Test
 public void rimuoviRisorsa() {
	 
	 libri.getLibriIta().add(libro1);
	 libri.getLibriIta().add(libro3);
	 libri.getLibriIta().rimuoviRisorsaTest(libri.getLibriIta().estraiIndiceDaRimuovere(222));
	 assertEquals(1, libri.getLibriIta().getArrayRisorse().size());
 }
 
 
 
 @Test
 public void rimuoviRisorsaFail() {
	 
	 libri.getLibriIta().add(libro1);
	 libri.getLibriIta().add(libro3);
	 libri.getLibriIta().rimuoviRisorsaTest(libri.getLibriIta().estraiIndiceDaRimuovere(000));
	 assertEquals(2, libri.getLibriIta().getArrayRisorse().size());
 }
 
 
 
 @Test
 public void aggiungiNomeSottoCatTest() {
	 
	 libri.getLibriIta().add(libro1);
	 libri.getLibriIta().add(libro3);
	 assertEquals( "Lingua italiana \n",libri.aggiungiNomeSottoCat(libro1,  libri.getLibriIta(),  libri.getLibriIng()));
 }
 
 @Test
 public void SelezionaArrayPerTitoloTest() {
	 libri.getLibriIta().add(libro1);
	 libri.getLibriIta().add(libro3);
	 libri.getLibriIng().add(libro4);
	 ArrayList<Risorsa> uniforme= libri.listaUniforme(libri.getLibriIta(), libri.getLibriIng(), libri.getLibriIta().getArrayRisorse(), libri.getLibriIng().getArrayRisorse());
	 ArrayList<Risorsa> trovati =libri.SelezionaArrayPerTitolo("divina",   libri.getLibriIta(),libri.getLibriIng(), uniforme);
	 //libro4= codice 333
	 assertEquals(333,trovati.get(0).getCodiceUnivoco());
	 
 }
 
 
 
 
 
 
 
 
 
}
