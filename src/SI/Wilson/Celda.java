package SI.Wilson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Celda {

	private int fila;
	private int columna;
	private int value;
	private boolean excavada;
	private boolean[] neighbors;

	public Celda(int fila, int columna, boolean[] neighbors) {
		this.fila = fila;
		this.columna = columna;
		this.value = 0;
		this.neighbors = neighbors;
		this.excavada = false;
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
	 * Nombre: obtenerListaneighbors
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
		int x = next.getFila() - this.fila;
		int y = next.getColumna() - this.columna;		
		// top 0, right 1, bottom 2, left 3
		// a = (2,1)
		//next = (1,1)
		//mov = a - next = (1,0)
		
		if (x == 1) {//Sur
			neighbors[2] = true;
			next.neighbors[0] = true;
		} else if (x == -1) { //Norte
			neighbors[0] = true;
			next.neighbors[2] = true;
		}



		if (y == 1) {//Este
			neighbors[1] = true;
			next.neighbors[3] = true;
		} else if (y == -1) {//Oeste
			neighbors[3] = true;
			next.neighbors[1] = true;
		}
	}
	/*
	 * Nombre: obtenerVecinoAleatorio
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
