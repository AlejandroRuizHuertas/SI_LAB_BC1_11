package SI;

public class Celda {
	private int fila;
	private int columna;
	private int valor;
	private boolean excavada;
	private boolean[] vecinos;

	public Celda(int fila, int columna, int valor, boolean excavada, boolean[] vecinos) {
		this.fila = fila;
		this.columna = columna;
		this.valor = valor;
		this.excavada = excavada;
		this.vecinos = vecinos;
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
