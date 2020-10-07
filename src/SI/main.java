package SI;

import java.util.Scanner;

public class main {
	
	private static int filas, columnas;
	static Scanner teclado = new Scanner (System.in);
	
	public static void main(String [] args) {
		System.out.println("Introduzca el numero de filas: \n");
		filas = teclado.nextInt();
		System.out.println("Introduzca el numero de columnas: \n");
		columnas = teclado.nextInt();
	}
	
}
