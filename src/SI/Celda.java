package SI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Celda {

	private int fila;
	private int columna;
	private int value;
	// private Celda padres;
	private boolean excavada;
	private boolean[] neighbors;

	public Celda(int fila, int columna, boolean[] neighbors) {
		this.fila = fila;
		this.columna = columna;
		this.value = 0;
		this.neighbors = neighbors;
		this.excavada = false;
	}

	public Celda(int fila, int columna) {

	}

	/*
	 * public enum Movimientos { Norte(-1, 0), Este(0, 1), Sur(1, 0), Oeste(0, -1);
	 * 
	 * private final int fila; private final int columna;
	 * 
	 * Movimientos(int x, int y) { this.fila = x; this.columna = y; }
	 * 
	 * public int getX() { return fila; }
	 * 
	 * public int getY() { return columna; }
	 * 
	 * private static final List<Movimientos> VALUES =
	 * Collections.unmodifiableList(Arrays.asList(values())); private static final
	 * int SIZE = VALUES.size(); private static final Random RANDOM = new Random();
	 * 
	 * public static Movimientos movimientoRandom() { return
	 * VALUES.get(RANDOM.nextInt(SIZE)); } }
	 */

	

	@Override
	public String toString() {
		return ;
	}

	/*
	 * Nombre: celdaVecinaAleatoria
	 * 
	 * Explicacion: Escoje una celda aleatoria de los neighbors posibles que se
	 * convertira en el proximo paso del laberinto. Esa celda que escojamos la
	 * ponemos como visitada.
	 * 
	 * Version 1.1
	 */
	public static Celda celdaVecinaAleatoria(List<Celda> listaneighbors) {
		Random random = new Random();
		Celda celda = listaneighbors.get(random.nextInt(listaneighbors.size()));
		return celda;

	}

	/*
	 * Nombre:comprobarCeldaVecina
	 * 
	 * Explicacion: Comprueba si la celda vecina que se le atribuye por parametros
	 * existe ya en la lista. Si existe entonces devuelve la posicion de la lista
	 * donde se encuentra ese vecino.
	 * 
	 * 
	 * Version 1.0
	 */
	public static Celda comprobarCeldaVecina(int desplazamientoFila, int desplazamientoColumna, Celda[][] laberinto,
			Celda actual) {
		Celda vecino = null;
		try {
			vecino = laberinto[actual.getFila() + desplazamientoFila][actual.getColumna() + desplazamientoColumna];
			return vecino;
		} catch (Exception ArrayIndexOutOfBoundsException) {
			return null;
		}
	}

	/*
	 * Nombre: obtenerCeldaSinCamino
	 * 
	 * Explicacion: Obtiene una celda que no este dentro del camino para evitar los
	 * bucles.
	 * 
	 * Version 1.1
	 */
	/*
	 * public Celda obtenerCeldaSinCamino(List<Celda> listaCeldas, Celda[][]
	 * laberinto, Celda actual) {
	 * 
	 * List<Celda> listaneighbors = new ArrayList<Celda>(4);
	 * 
	 * Celda norte = comprobarCeldaVecina(listaCeldas, laberinto[actual.getFila() -
	 * 1][actual.getColumna()]); Celda este = comprobarCeldaVecina(listaCeldas,
	 * laberinto[actual.getFila()][actual.getColumna() + 1]); Celda sur =
	 * comprobarCeldaVecina(listaCeldas, laberinto[actual.getFila() +
	 * 1][actual.getColumna()]); Celda oeste = comprobarCeldaVecina(listaCeldas,
	 * laberinto[actual.getFila()][actual.getColumna() - 1]);
	 * 
	 * if (norte != null && !norte.getCamino()) { listaneighbors.add(norte); } else if
	 * (este != null && !este.getCamino()) { listaneighbors.add(este); } else if (sur
	 * != null && !sur.getCamino()) { listaneighbors.add(sur); } else if (oeste !=
	 * null && !oeste.getCamino()) { listaneighbors.add(oeste); }
	 * 
	 * if (listaneighbors.size() == 1) { return listaneighbors.get(0); } else { return
	 * celdaVecinaAleatoria(listaneighbors); } }
	 */
	/*
	 * Nombre: obtenerVecinoNoVisitado
	 * 
	 * Explicacion: Comprobamos todos los posibles neighbors de la celda actual en la
	 * que nos encontramos. Si tiene algun vecino que no este visitado, entonces lo
	 * a�ade a una lista de neighbors. Si la lista solo tiene un elemento,
	 * devolvemos ese elemento, si tiene mas de uno, entonces buscara uno aleatorio.
	 * 
	 * Version 1.0
	 */
	public static List<Celda> obtenerListaneighbors(Celda[][] laberinto, Celda actual) {
		List<Celda> listaneighbors = new ArrayList<Celda>();

		Celda norte = comprobarCeldaVecina(-1, 0, laberinto, actual);
		Celda este = comprobarCeldaVecina(0, 1, laberinto, actual);
		Celda sur = comprobarCeldaVecina(1, 0, laberinto, actual);
		Celda oeste = comprobarCeldaVecina(0, -1, laberinto, actual);

		if (norte != null) {
			listaneighbors.add(norte);
		}
		if (este != null) {
			listaneighbors.add(este);
		}
		if (sur != null) {
			listaneighbors.add(sur);
		}
		if (oeste != null) {
			listaneighbors.add(oeste);
		}

		return listaneighbors;

	}

	public void removeWalls(Celda next) {
		int x = this.fila - next.getFila();
		// top 0, right 1, bottom 2, left 3

		if (x == 1) {
			neighbors[3] = false;
			next.neighbors[1] = false;
		} else if (x == -1) {
			neighbors[1] = false;
			next.neighbors[3] = false;
		}

		int y = this.columna - next.getColumna();

		if (y == 1) {
			neighbors[0] = false;
			next.neighbors[2] = false;
		} else if (y == -1) {
			neighbors[2] = false;
			next.neighbors[0] = false;
		}
	}
	/*
	 * Nombre: obtenerVecinoNoVisitado
	 * 
	 * Explicacion: Obtiene una celda vecina no visitada previamente
	 * 
	 * Version: 1.2
	 * 
	 */

	public static Celda obtenerVecinoAleatorio(Celda actual, Celda[][] laberinto) {

		List<Celda> neighbors = obtenerListaneighbors(laberinto, actual);

		Celda aleatoria = celdaVecinaAleatoria(neighbors);
		return aleatoria;

	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getvalue() {
		return value;
	}

	public void setvalue(int value) {
		this.value = value;
	}

	public boolean isExcavada() {
		return excavada;
	}

	public void setExcavada(boolean excavada) {
		this.excavada = excavada;
	}

	public boolean[] getneighbors() {
		return neighbors;
	}

	public void setneighbors(boolean[] neighbors) {
		this.neighbors = neighbors;
	}

}
