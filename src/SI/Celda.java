package SI;

import java.util.List;
import java.util.Random;

public class Celda {
	private int fila;
	private int columna;
	private int valor;
	private int n_vecinos;
	private boolean excavada;
	private boolean[] vecinos;

	
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.valor = 0;
	}


	public Celda() {
		// TODO Auto-generated constructor stub
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
		return vecinos;
	}

	public void setVecinos(boolean[] vecinos) {
		this.vecinos = vecinos;
	}
	
	
}
