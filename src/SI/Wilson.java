package SI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class Wilson {

	int rows;
	int cols;
	int max_n;
	int mov[][];
	String[] id_mov;
	Celda[][] cells;
	static List<Celda> camino = new ArrayList<Celda>();

	final static List<Celda> listaCeldas = new ArrayList<Celda>();

	public Wilson(int rows, int cols, int max_n, int[][] mov, String[] id_mov, Celda[][] cells) {
		this.rows = rows;
		this.cols = cols;
		this.max_n = max_n;
		this.mov = mov;
		this.id_mov = id_mov;
		this.cells = cells;
	}

	public static void crearLaberinto(Celda[][] laberinto) {
		Celda actual;
		Random random = new Random();
		int filas = laberinto.length;
		int columnas = laberinto[0].length;
		inicializarCeldas(laberinto);
		actual = laberinto[random.nextInt(filas)][random.nextInt(columnas)];
		actual.setExcavada(true);
		while (!laberintoExcavado(laberinto)) {
			while (actual.isExcavada()) {
				actual = laberinto[random.nextInt(filas)][random.nextInt(columnas)];
			}
			hacerCamino(laberinto, actual);

		}

	}

	public static void generarJson(Celda[][] cells) {
		int mov[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		String[] id_mov = { "N", "E", "S", "O" };
		int filas = cells.length;
		int columnas = cells[0].length;
		Gson gson = new Gson();
		//Wilson solucion = new Wilson(filas, columnas, 4, mov, id_mov, cells);
		PuzzleJSON solucion = new PuzzleJSON(filas, columnas, cells);
		String j = gson.toJson(solucion);
		System.out.println(j.toString());
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("laberinto"+filas+"x"+columnas+".json"))) {
			bw.write(j);
			System.out.println("Fichero creado");
		} catch (IOException ex) {
			Logger.getLogger(Wilson.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	private static boolean laberintoExcavado(Celda[][] laberinto) {
		for (int i = 0; i < laberinto.length; i++) {
			for (int j = 0; j < laberinto[0].length; j++) {
				if (!laberinto[i][j].isExcavada()) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Nombre: excavar
	 * 
	 * Explicacion:
	 * 
	 * 
	 * Version: 1.2
	 */
	
	public static void excavar(Stack<Celda> pila) {
		Celda a = null;
		Celda b = null;

		while (!pila.isEmpty()) {
			a = pila.pop();
			try {
				b = (Celda) pila.peek();
				a.setExcavada(true);

				a.removeWalls(b);
			} catch (Exception EmptyStackException) {
				a.setExcavada(true);
			}
			

		}

	}

	public static void hacerCamino(Celda[][] laberinto, Celda actual) {
		Stack<Celda> camino = new Stack<Celda>();
		camino.push(actual);
		while (!actual.isExcavada()) {
			Celda nueva = Celda.obtenerVecinoAleatorio(actual, laberinto);
			if (camino.contains(nueva)) {
				while (camino.contains(nueva)) {
					camino.pop();
				}
				camino.push(nueva);
			} else {
				camino.push(nueva);
			}
			actual = nueva;
		}
		
		excavar(camino);
	}

	/*
	 * Nombre: inicializarCelda
	 * 
	 * Explicacion: Con la matriz previamente generada, añadimos cada una de las
	 * celdas a una matriz de celdas y se ponen las paredes de los extremos
	 * 
	 * Version 1.1
	 */

	// norte, este, sur , oeste

	public static void inicializarCeldas(Celda[][] arrayCeldas) {
		for (int i = 0; i < arrayCeldas.length; i++) {
			for (int j = 0; j < arrayCeldas[0].length; j++) {
				boolean[] vecinos = { false, false, false, false };
				arrayCeldas[i][j] = new Celda(i, j, vecinos);
			}
		}

	}

}
