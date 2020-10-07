package SI;

import java.util.*;

public class Wilson extends Celda {

	private final List<Celda> listaCeldas = new ArrayList<Celda>();
	private final Stack<Celda> pila = new Stack<Celda>();
	private final Celda celdaActual = new Celda();
	private final Random random = new Random();
	
	int filas, columnas;

	/* Creamos la matriz segun los datos de entrada que tengamos */

	public int[][] crearMatriz(int filas, int columnas) {

		int matriz[][] = new int[filas][columnas];
		return matriz;
	}

	/* Añadimos a la lista las celdas que creamos mediante la matriz */
	public void iniciarlizarCelda(int filas, int columnas) {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				listaCeldas.add(new Celda(i, j));
			}
		}
	}

	/* Generar numero aleatorio para las casillas de inicio y fin del laberinto */
	public int celdaAleatoria(int limite) {

		int celda = (int) Math.random() * limite + 1;
		return celda;
	}

	/* Generar celda aleatoria de la matriz, usando la lista de celdas como parametro */
	public Celda celdaVecinaAleatoria(List<Celda> listaVecinos) {
		Random random = new Random();
		Celda celda = listaVecinos.get(random.nextInt(listaVecinos.size()));
		return celda;
	}

	/*
	 * Comprobamos para saber quien es vecino de quien y lo guardamos a una lista,
	 * de la cual despues obtendremos un valor aleatorio de esa lista, que sera el
	 * vecino al que iremos despues
	 */
	public Celda obtenerVecinoAleatoria(List<Celda> listaCeldas) {
		List<Celda> listaVecinos = new ArrayList<Celda>(4);

		Celda norte = comprobarCeldaVecina(listaCeldas, new Celda(filas, columnas - 1));
		Celda este = comprobarCeldaVecina(listaCeldas, new Celda(filas + 1, columnas));
		Celda sur = comprobarCeldaVecina(listaCeldas, new Celda(filas, columnas + 1));
		Celda oeste = comprobarCeldaVecina(listaCeldas	, new Celda(filas - 1, columnas));

		if (norte != null) {
			listaVecinos.add(norte);
		} else if (este != null) {
			listaVecinos.add(este);
		} else if (sur != null) {
			listaVecinos.add(sur);
		} else if (oeste != null) {
			listaVecinos.add(oeste);
		}

		if (listaVecinos.size() == 1) {
			return listaVecinos.get(0);
		} else {
			return celdaVecinaAleatoria(listaVecinos);
		}
	}
	
	public Celda comprobarCeldaVecina(List<Celda> listaCeldas, Celda vecino) {
		if(listaCeldas.contains(vecino)) {
			return listaCeldas.get(listaCeldas.indexOf(vecino));
		}
		else {
			return null;
		}
	}

}
