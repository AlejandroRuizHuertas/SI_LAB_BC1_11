package SI;

import java.util.*;
import java.util.stream.Collectors;

public class Wilson {

	// static List<Celda> camino = new ArrayList<Celda>();
	final Stack<Celda> pila = new Stack<Celda>();
	private Celda actual;
	private final Random random = new Random();

	public static void crearLaberinto(Celda[][] laberinto) {
		Random random = new Random();

		int filas = laberinto.length;
		int columnas = laberinto[0].length;
		inicializarCeldas(laberinto);
		Celda actual;
		actual = laberinto[random.nextInt(filas)][random.nextInt(columnas)];
		actual.setVisitado(true);
		while (actual.getVisitado()) {
			actual = laberinto[random.nextInt(filas)][random.nextInt(columnas)];
		}
		excavar();
	}

	/*
	 * Nombre: excavar
	 * 
	 * Explicacion:
	 * 
	 * 
	 * Version: 1.2
	 */
	public void excavar() {
		if (actual.getVisitado()) {
			aņadirAlCamino();

			List<Celda> noCamino = arrayCeldas.parallelStream().filter(c -> !c.getVisitado())
					.collect(Collectors.toList());
			if (!noCamino.isEmpty()) {
				actual = noCamino.get(random.nextInt(noCamino.size()));
			}
		}

		actual.setCamino(true);
		Celda proxima = actual.obtenerCeldaSinCamino(arrayCeldas);

		if (proxima != null) {
			pila.push(actual);
			actual.eliminarPared(proxima);
			actual = proxima;
		} else if (!pila.isEmpty()) {
			actual = pila.pop();
		}
	}

	private void aņadirAlCamino() {
		arrayCeldas.parallelStream().filter(c -> c.getCamino()).forEach(c -> {
			c.setVisitado(true);
			c.setCamino(false);
		});
		pila.clear();
	}

	/*
	 * Nombre: inicializarCelda
	 * 
	 * Explicacion: Con la matriz previamente generada, aņadimos cada una de las
	 * celdas a una matriz de celdas
	 * 
	 * Version 1.0
	 */
	public static void inicializarCeldas(Celda[][] arrayCeldas) {
		for (int i = 0; i < arrayCeldas.length; i++) {
			for (int j = 0; j < arrayCeldas[0].length; j++) {
				arrayCeldas[i][j] = new Celda(i, j);
			}
		}

	}

	/*
	 * Nombre: celdaAleatoria
	 * 
	 * Explicacion: Genera un numero aleatorio para asi poder escoger la casilla de
	 * inicio y de fin a la hora de generar el laberinto
	 * 
	 * Version 1.0
	 */
	public int celdaAleatoria(int limite) {

		int posicion = (int) Math.random() * limite + 1;
		return posicion;
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
