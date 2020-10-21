package SI;

public class json {
	int rows;
	int cols;
	int max_n;
	int[][] mov;
	String[] id_mov;
	Celda[][] cells;

	public json(int rows, int cols, int max_n, int[][] mov, String[] id_mov, Celda[][] cells) {
		super();
		this.rows = rows;
		this.cols = cols;
		this.max_n = max_n;
		this.mov = mov;
		this.id_mov = id_mov;
		this.cells = cells;
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

	public Celda[][] getCells() {
		return cells;
	}

	public void setCells(Celda[][] cells) {
		this.cells = cells;
	}

	
}
