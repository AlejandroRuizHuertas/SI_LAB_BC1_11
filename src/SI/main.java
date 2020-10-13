package SI;

import java.util.Scanner;

public class main {
	
	static int filas;
	static int columnas;
	static Scanner teclado = new Scanner (System.in);
	
	public static void main(String [] args) {
		System.out.println("Introduzca el numero de filas: \n");
		filas = teclado.nextInt();
		System.out.println("Introduzca el numero de columnas: \n");
		columnas = teclado.nextInt();
		
		int matriz[][] = Wilson.crearMatriz(filas, columnas);
		
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
}
