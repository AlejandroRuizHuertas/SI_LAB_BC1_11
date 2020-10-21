package SI;

import java.util.*;

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
	
	public Wilson

	public static void crearLaberinto(Celda[][] laberinto) {
		Celda actual;
		int mov[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		String[] id_mov = { "N", "E", "S", "O" };
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
		generarJson(filas, columnas, mov, id_mov, laberinto);

	}

	private static void generarJson(int filas, int columnas, int[][] mov, String[] id_mov, Celda[][] cells) {
		Gson gson = new Gson();
		gson.toJson(cells);
		Collection collection = new ArrayList();
		collection.add(filas);
		collection.add(columnas);
		collection.add(4);
		collection.add(mov);
		collection.add(id_mov);
		collection.add(cells);
		String j = gson.toJson(collection);
		System.out.println(j);

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
				b = pila.peek();
			} catch (Exception EmptyStackException) {

			}
			a.setExcavada(true);
			a.removeWalls(b);

		}

	}

	public static void hacerCamino(Celda[][] laberinto, Celda actual) {
		Stack<Celda> pila = new Stack<Celda>();
		pila.push(actual);
		while (!actual.isExcavada()) {
			Celda nueva = Celda.obtenerVecinoAleatorio(actual, laberinto);
			if (pila.contains(nueva)) {
				while (pila.pop().equals(nueva)) {
				}
			} else {
				pila.push(nueva);
			}
			actual = nueva;
		}
		for (Celda c : pila) {
			System.out.println(c.getFila() + " " + c.getColumna());
		}
		System.out.println(pila.toString());
		excavar(pila);
	}
	/*
	 * if (actual.getVisitado()) { añadirAlCamino();
	 * 
	 * List<Celda> noCamino = arrayCeldas.parallelStream().filter(c ->
	 * !c.getVisitado()) .collect(Collectors.toList()); if (!noCamino.isEmpty()) {
	 * actual = noCamino.get(random.nextInt(noCamino.size())); } }
	 * 
	 * actual.setCamino(true); Celda proxima =
	 * actual.obtenerCeldaSinCamino(arrayCeldas);
	 * 
	 * if (proxima != null) { pila.push(actual); actual.eliminarPared(proxima);
	 * actual = proxima; } else if (!pila.isEmpty()) { actual = pila.pop(); } }
	 */

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
				boolean[] vecinos = { true, true, true, true };
				if (i == 0) {
					vecinos[0] = false; // norte
				}
				if (j == (arrayCeldas[0].length - 1)) {
					vecinos[1] = false; // este
				}
				if (i == (arrayCeldas.length - 1)) {
					vecinos[2] = false; // sur
				}
				if (j == 0) {
					vecinos[3] = false; // oeste
				}
				arrayCeldas[i][j] = new Celda(i, j, vecinos);
			}
		}

	}

	/*
	 * public Celda obtenerVecinoNoExcavado(List<Celda> arrayCeldas) { List<Celda>
	 * listaVecinos = new ArrayList<Celda>(4);
	 * 
	 * Celda norte = comprobarCeldaVecina(arrayCeldas, new Celda(filas, columnas -
	 * 1)); Celda este = comprobarCeldaVecina(arrayCeldas, new Celda(filas + 1,
	 * columnas)); Celda sur = comprobarCeldaVecina(arrayCeldas, new Celda(filas,
	 * columnas + 1)); Celda oeste = comprobarCeldaVecina(arrayCeldas, new
	 * Celda(filas - 1, columnas));
	 * 
	 * if (norte != null && !norte.isExcavada()) { listaVecinos.add(norte); } else
	 * if (este != null && !este.isExcavada()) { listaVecinos.add(este); } else if
	 * (sur != null && !sur.isExcavada()) { listaVecinos.add(sur); } else if (oeste
	 * != null && oeste.isExcavada()) { listaVecinos.add(oeste); }
	 * 
	 * if (listaVecinos.size() == 1) { return listaVecinos.get(0); } else { return
	 * celdaVecinaAleatoria(listaVecinos); } }
	 */

}
