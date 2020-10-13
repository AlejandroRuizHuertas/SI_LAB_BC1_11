package SI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
	
	static int filas;
	static int columnas;
	static Scanner teclado = new Scanner (System.in);
	
	public static void main(String [] args) {
		Interfaz In = new Interfaz();
		In.setVisible(true);
		/*System.out.println("Introduzca el numero de filas: \n");
		filas = teclado.nextInt();
		System.out.println("Introduzca el numero de columnas: \n");
		columnas = teclado.nextInt();
		List<Celda> listaCeldas = new ArrayList<Celda>();
		int matriz[][] = Wilson.crearMatriz(filas, columnas);
		Wilson laberinto = new Wilson(listaCeldas, filas, columnas);
		
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
		*/
	}
	
}
