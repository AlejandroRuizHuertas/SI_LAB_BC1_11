package SI;

import java.util.*;
import java.util.stream.Collectors;


public class Wilson{

	private List<Celda> listaCeldas = new ArrayList<Celda>();
	//static List<Celda> camino = new ArrayList<Celda>();
	final Stack<Celda> pila = new Stack<Celda>();
	private Celda actual;
	private final Random random = new Random();
	
	
	public Wilson(List<Celda> listaCeldas, int filas, int columnas) {
		this.listaCeldas = listaCeldas;
		inicializarCelda(filas, columnas);
		actual = listaCeldas.get(random.nextInt(listaCeldas.size()));
		actual.setVisitado(true);
		actual = listaCeldas.get(random.nextInt(listaCeldas.size()));
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
		if(actual.getVisitado()) {
			añadirAlCamino();
			
			List<Celda> noCamino = listaCeldas.parallelStream().filter(c -> !c.getVisitado()).collect(Collectors.toList());
			if (!noCamino.isEmpty()) {
				actual = noCamino.get(random.nextInt(noCamino.size()));
			}
		}
		
		actual.setCamino(true);
		Celda proxima = actual.obtenerCeldaSinCamino(listaCeldas);
		
		if(proxima != null) {
			pila.push(actual);
			actual.eliminarPared(proxima);
			actual = proxima;
		}else if(!pila.isEmpty()) {
			actual = pila.pop();
			}
	}
	
	private void añadirAlCamino() {
		listaCeldas.parallelStream().filter(c -> c.getCamino()).forEach(c->{
			c.setVisitado(true);
			c.setCamino(false);
		});
		pila.clear();
	}




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
	public List<Celda> inicializarCelda(int filas, int columnas) {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				listaCeldas.add(new Celda(i, j));
			}
		}
		return listaCeldas;
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
