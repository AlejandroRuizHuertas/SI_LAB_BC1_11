package SI;

import java.util.*;


public class Wilson extends Celda {

	private final List<Celda> listaCeldas = new ArrayList<Celda>();
	private final Stack<Celda> pila = new Stack<Celda>();
	private final Celda celdaActual = new Celda();
	private final Random random = new Random();

	//int filas, columnas;

	/*
	 * Nombre: Crear Matriz
	 * 
	 * Explicacion: Generamos una matriz con las dimensiones que le especifiquemos
	 * mediante filas y columnas
	 * 
	 * Version 1.0
	 */
	public static int[][] crearMatriz(int filas, int columnas) {

		int matriz[][] = new int[filas][columnas];
		return matriz;
	}

	/*
	 * Nombre: inicializarCelda
	 * 
	 * Explicacion: Con la matriz previamente generada, añadimos cada una de las
	 * celdas a una lista de celdas
	 * 
	 * Version 1.0
	 */
	public void inicializarCelda(int filas, int columnas) {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				listaCeldas.add(new Celda(i, j));
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

		int celda = (int) Math.random() * limite + 1;
		return celda;
	}

	/*
	 * Nombre: obtenerVecinoNoVisitado
	 * 
	 * Explicacion: Comprobamos todos los posibles vecinos de la celda actual en la
	 * que nos encontramos. Si tiene algun vecino que no este visitado, entonces lo
	 * añade a una lista de vecinos. Si la lista solo tiene un elemento, devolvemos
	 * ese elemento, si tiene mas de uno, entonces buscara uno aleatorio.
	 * 
	 * Version 1.0
	 */
	public Celda obtenerVecinoNoVisitado(List<Celda> listaCeldas) {
		List<Celda> listaVecinos = new ArrayList<Celda>(4);

		Celda norte = comprobarCeldaVecina(listaCeldas, new Celda(main.filas, main.columnas - 1));
		Celda este = comprobarCeldaVecina(listaCeldas, new Celda(main.filas + 1, main.columnas));
		Celda sur = comprobarCeldaVecina(listaCeldas, new Celda(main.filas, main.columnas + 1));
		Celda oeste = comprobarCeldaVecina(listaCeldas, new Celda(main.filas - 1, main.columnas));

		if (norte != null && !norte.getVisitado()) {
			listaVecinos.add(norte);
		} else if (este != null && !este.getVisitado()) {
			listaVecinos.add(este);
		} else if (sur != null && !sur.getVisitado()) {
			listaVecinos.add(sur);
		} else if (oeste != null && oeste.getVisitado()) {
			listaVecinos.add(oeste);
		}

		if (listaVecinos.size() == 1) {
			return listaVecinos.get(0);
		} else {
			return celdaVecinaAleatoria(listaVecinos);
		}
	}

	/*
	 * Nombre: celdaVecinaAleatoria
	 * 
	 * Explicacion: Escoje una celda aleatoria de los vecinos posibles que se
	 * convertira en el proximo paso del laberinto. Esa celda que escojamos la
	 * ponemos como visitada.
	 * 
	 * Version 1.1
	 */
	public Celda celdaVecinaAleatoria(List<Celda> listaVecinos) {
		Celda celda = listaVecinos.get(random.nextInt(listaVecinos.size()));
		celda.setVisitado(true);
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
	public Celda comprobarCeldaVecina(List<Celda> listaCeldas, Celda vecino) {
		if (listaCeldas.contains(vecino)) {
			return listaCeldas.get(listaCeldas.indexOf(vecino));
		} else {
			return null;
		}
	}
	
	
	/*
	 * Nombre: obtenerCeldaSinCamino
	 * 
	 * Explicacion: Obtiene una celda que no este dentro del camino para evitar los bucles.
	 * 
	 * Version 1.1
	 */
	public Celda obtenerCeldaSinCamino(List<Celda> listaCeldas) {
		
		List<Celda> listaVecinos = new ArrayList<Celda>(4);
		
		Celda norte = comprobarCeldaVecina(listaCeldas, new Celda(main.filas, main.columnas - 1));
		Celda este = comprobarCeldaVecina(listaCeldas, new Celda(main.filas + 1, main.columnas));
		Celda sur = comprobarCeldaVecina(listaCeldas, new Celda(main.filas, main.columnas + 1));
		Celda oeste = comprobarCeldaVecina(listaCeldas, new Celda(main.filas - 1, main.columnas -1));
		
		if(norte != null && !norte.getCamino()) {
			listaVecinos.add(norte);
		}else if(este != null && !este.getCamino()) {
			listaVecinos.add(este);
		}else if(sur != null && !sur.getCamino()) {
			listaVecinos.add(sur);
		}else if(oeste != null && !oeste.getCamino()) {
			listaVecinos.add(oeste);
		}
		
		if (listaVecinos.size() == 1) {
			return listaVecinos.get(0);
		} else {
			return celdaVecinaAleatoria(listaVecinos);
		}
	}
	
	
	

	/*
	 * public Celda obtenerVecinoNoExcavado(List<Celda> listaCeldas) { List<Celda>
	 * listaVecinos = new ArrayList<Celda>(4);
	 * 
	 * Celda norte = comprobarCeldaVecina(listaCeldas, new Celda(filas, columnas -
	 * 1)); Celda este = comprobarCeldaVecina(listaCeldas, new Celda(filas + 1,
	 * columnas)); Celda sur = comprobarCeldaVecina(listaCeldas, new Celda(filas,
	 * columnas + 1)); Celda oeste = comprobarCeldaVecina(listaCeldas, new
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
