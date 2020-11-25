package SI.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

import SI.Wilson.Celda;

public class PuzzleJSON {
	private int rows;
	private int cols;
	private int max_n = 4;
	private int mov[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private String[] id_mov = { "N", "E", "S", "O" };
	private Map<String, CeldaJSON> cells = new LinkedHashMap<String, CeldaJSON>();

	public PuzzleJSON(int rows, int cols, Celda[][] laberinto) {
		super();
		this.rows = rows;
		this.cols = cols;
		rellenarMap(laberinto);

	}

	private void rellenarMap(Celda[][] laberinto) {
		for (int i = 0; i < laberinto.length; i++) {
			for (int j = 0; j < laberinto[0].length; j++) {
				Celda c = laberinto[i][j];
				String id = "(" + c.getFila() + ", " + c.getColumna() + ")";
				cells.put(id, new CeldaJSON(c.getvalue(), c.getneighbors()));
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getMax_n() {
		return max_n;
	}

	public void setMax_n(int max_n) {
		this.max_n = max_n;
	}

	public int[][] getMov() {
		return mov;
	}

	public void setMov(int[][] mov) {
		this.mov = mov;
	}

	public String[] getId_mov() {
		return id_mov;
	}

	public void setId_mov(String[] id_mov) {
		this.id_mov = id_mov;
	}

	public Map<String, CeldaJSON> getCells() {
		return cells;
	}

	public void setCells(Map<String, CeldaJSON> colours) {
		this.cells = cells;
	}

	public Celda[][] getLaberinto() {
		Celda[][] lab = new Celda[this.rows][this.cols];
		for (Map.Entry<String, CeldaJSON> entry : cells.entrySet()) {
			String id = entry.getKey();
			String fila = id.substring(1, id.indexOf(','));
			String columna = id.substring(id.indexOf(' ') + 1, id.indexOf(')'));
			int fil = Integer.parseInt(fila);
			int col = Integer.parseInt(columna);
			CeldaJSON cell = entry.getValue();
			lab[fil][col] = new Celda(fil, col, cell.getNeighbors());

		}
		return lab;
	}
}
