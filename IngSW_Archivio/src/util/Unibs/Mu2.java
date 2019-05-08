package util.Unibs;

import java.util.Scanner;

public class Mu2 {
	private static Scanner lettore= new Scanner(System.in);
	public static int leggiIntero(String messaggio)
	{
		System.out.print(messaggio);
		return lettore.nextInt();
	}
	
	private static Scanner lettoreD= new Scanner (System.in);
	public static double leggiDouble(String messaggio)
	{
		System.out.print(messaggio);
		return lettoreD.nextDouble();
	}
}
