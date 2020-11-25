package SI;

import java.util.List;

import SI.Wilson.Celda;

public class Nodo {
	private int id;
	private int costo;
	private String id_estado; // Las coordenadas del estado actual
	private Nodo id_padre;
	private String accion; // N, E, S, O
	private int profundidad;
	private float heuristica;
	private int valor;

	public Nodo(int id, int costo, String id_estado, Nodo id_padre, String accion, int profundidad, float heuristica,
			int valor) {
		this.id = id;
		this.costo = costo;
		this.id_estado = id_estado;
		this.id_padre = id_padre;
		this.accion = accion;
		this.profundidad = profundidad;
		this.heuristica = heuristica;
		this.valor = valor;
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

	public float getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(float heuristica) {
		this.heuristica = heuristica;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public static void CrearNodo(Celda[][] lab, List<Nodo> arbol) {

	}
}
