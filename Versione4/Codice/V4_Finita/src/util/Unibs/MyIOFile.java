package util.Unibs;

import java.io.*;

public class MyIOFile {
	
		/**
		 * legge un oggetto da file
		 * @param file: oggetto File da dove leggere l'oggetto
		 * @return un Object (c'è quindi la necessità di un cast) corrispondente all'oggetto letto
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		public static Object leggiOggetto(File file) throws IOException, ClassNotFoundException{
			ObjectInputStream sorgente;
			sorgente = new ObjectInputStream(new BufferedInputStream
						(new FileInputStream(file)));
				
			Object obj= sorgente.readObject();
			sorgente.close();
			return obj;
		}
		
		/**
		 * scrive un oggetto su File
		 * @param file oggetto File dove scrivere l'oggetto (viene creato in caso non esistesse)
		 * @param obj: oggetto da scrivere sul file
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public static void scriviOggetto(File file, Object obj) throws FileNotFoundException, IOException{
			ObjectOutputStream archivio;
			file.createNewFile();
			archivio = new ObjectOutputStream(new BufferedOutputStream
					(new FileOutputStream(file)));
			archivio.writeObject(obj);
			archivio.close();

		}



}
