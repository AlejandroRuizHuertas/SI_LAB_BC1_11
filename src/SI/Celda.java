package SI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Celda {
	
	
	private int fila;
	private int columna;
	private int valor;
	
	//private Celda padres;
	
	private boolean bucle = false;
	private boolean camino = false;
	private boolean visitado = false;
	
	
	private boolean excavada;
	private boolean[] paredes;

	
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.valor = 0;
		paredes = new boolean[] {false, false, false, false};
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
		Random random = new Random();
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
	 * Nombre: obtenerVecinoNoVisitado
	 * 
	 * Explicacion: Comprobamos todos los posibles vecinos de la celda actual en la
	 * que nos encontramos. Si tiene algun vecino que no este visitado, entonces lo
	 * añade a una lista de vecinos. Si la lista solo tiene un elemento, devolvemos
	 * ese elemento, si tiene mas de uno, entonces buscara uno aleatorio.
	 * 
	 * Version 1.0
	 */
	public Celda obtenerListaVecinoNoVisitado(List<Celda> listaCeldas) {
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
	 * Nombre: obtenerVecinoNoVisitado
	 * 
	 * Explicacion: Obtiene una celda vecina no visitada previamente
	 * 
	 * Version: 1.2
	 * 
	 */
	
	public Celda obtenerVecinoNoVisitado(List<Celda> listaCeldas) {
		
		List<Celda> listaVecinos = obtenerListaVecinoNoVisitado(listaCeldas);
		
		if(listaVecinos.size() == 1) {
			return listaVecinos.get(0);
		}else {
			return celdaVecinaAleatoria(listaVecinos);
		}
	}
	
	
	
	public void eliminarPared(Celda proxima) {
		int x = this.x - proxima.x;
		
		if(x == 1) {
			paredes[3] = false;
			proxima.paredes[1] = false;
		}
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean isExcavada() {
		return excavada;
	}

	public void setExcavada(boolean excavada) {
		this.excavada = excavada;
	}

	public boolean[] getVecinos() {
		return paredes;
	}

	public void setVecinos(boolean[] paredes) {
		this.paredes = paredes;
	}


	public boolean getVisitado() {
		return visitado;
	}


	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	public boolean getCamino() {
		return camino;
	}
	
	public void setCamino(boolean camino) {
		this.camino = camino;
	}
	
}
