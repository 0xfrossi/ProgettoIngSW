package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import interfaces.Risorsa;
import model.LibriModel;
import model.LibroModel;

class LibriTest {

	 LibriModel libri= new LibriModel();
	 Risorsa libro1= new LibroModel("BiancaNeve", 111, 3, creaAutori1(), 100, "mondadori", "fantasy", new GregorianCalendar(2000,5,5));
	
	 Risorsa libro3= new LibroModel("Promessi Sposi", 222, 3, creaAutori3(), 500, "mondadori", "classico", new GregorianCalendar(2000,5,5));
	 Risorsa libro4= new LibroModel("divina commedia", 333, 3, creaAutori4(), 500, "mondadori", "classico", new GregorianCalendar(2000,6,5));
	 
	 
	 public ArrayList<String> creaAutori1(){
		  ArrayList<String> autori=new ArrayList<>();
			 autori.add("Harry");
			 return autori;
	 }

	 public ArrayList<String> creaAutori4(){
		  ArrayList<String> autori=new ArrayList<>();
			 autori.add("Dante Alighieri");
			 return autori;
	 }
	 public ArrayList<String> creaAutori3(){
		  ArrayList<String> autori=new ArrayList<>();
			 autori.add("Manzoni");
			 return autori;
	 }
	 
	 @Test
	 public void cercaLibroPerAutoreTest() {
		 
		 libri.getLibriIta().add(libro1);
		 libri.getLibriIta().add(libro3);
		 libri.getLibriIng().add(libro4);
		 ArrayList<Risorsa> trovati = libri.cercaLibroPerAutore("Manzoni");
		 
		 assertEquals(222,trovati.get(0).getCodiceUnivoco());
		 assertEquals(true, libro3.equals(trovati.get(0)));
	 }
	 
	 
	
		 
	 @Test
	 public void cercaLibroPerAutoreFailTest() {
		 
		 libri.getLibriIta().add(libro1);
		 libri.getLibriIta().add(libro3);
		 libri.getLibriIng().add(libro4);
		 ArrayList<Risorsa> trovati = libri.cercaLibroPerAutore("Pirandello");
		 
		
		 assertEquals(true, trovati.isEmpty());
	 }
	 
	 
}
