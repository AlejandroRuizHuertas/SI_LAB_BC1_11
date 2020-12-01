package SI.Problema;

import java.util.List;

import SI.Wilson.Celda;

public class Nodo implements Comparable<Nodo> {
	@Override
	public String toString() {
		if (id_padre == null) {
			return "[" + id + "][" + costo + "," + id_estado + ",None,None," + profundidad + "," + heuristica + ","
					+ valor + "]\n";
		} else {
			return "[" + id + "][" + costo + "," + id_estado + "," + id_padre.getId() + "," + accion + "," + profundidad
					+ "," + heuristica + "," + valor + "]\n";
		}
	}

	private int id;
	private int costo;
	private String id_estado; // Las coordenadas del estado actual
	private Nodo id_padre;
	private String accion; // N, E, S, O
	private int profundidad;
	private int heuristica;
	private int valor;
	private Celda c;

	public Nodo(int id, int costo, String id_estado, Nodo id_padre, String accion, int profundidad, int heuristica,
			int valor, Celda c) {
		this.id = id;
		this.costo = costo;
		this.id_estado = id_estado;
		this.id_padre = id_padre;
		this.accion = accion;
		this.profundidad = profundidad;
		this.heuristica = heuristica;
		this.valor = valor;
		this.c = c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getId_estado() {
		return id_estado;
	}

	public void setId_estado(String id_estado) {
		this.id_estado = id_estado;
	}

	public Nodo getId_padre() {
		return id_padre;
	}

	public void setId_padre(Nodo id_padre) {
		this.id_padre = id_padre;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public int getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(int heuristica) {
		this.heuristica = heuristica;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int compareTo(Nodo nodo1) {
		// Valor del nodo
		if (this.valor > nodo1.getValor()) {
			return 1;
		} else if (this.valor < nodo1.getValor()) {
			return -1;
		}
		// Fila del estado del nodo
		else if (c.getFila() > nodo1.getCelda().getFila()) {
			return 1;

		} else if (c.getFila() < nodo1.getCelda().getFila()) {
			return -1;
		}

		// Columna del estado del nodo
		else if (c.getColumna() > nodo1.getCelda().getColumna()) {
			return 1;
		} else if (c.getColumna() < nodo1.getCelda().getColumna()) {
			return -1;
		}
		// Identificador único del nodo
		else {

			if (this.id > nodo1.getId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public Celda getCelda() {
		return c;
	}

	public void setCelda(Celda c) {
		this.c = c;
	}

}
