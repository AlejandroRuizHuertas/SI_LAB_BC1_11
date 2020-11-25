package SI;

public class Objetivo {
	private String Inicial;
	private String Objetivo;
	private String Maze;

	public Objetivo(String inicial, String objetivo, String maze) {
		Inicial = inicial;
		Objetivo = objetivo;
		Maze = maze;
	}

	public String getInicial() {
		return Inicial;
	}

	public void setInicial(String inicial) {
		Inicial = inicial;
	}

	public String getObjetivo() {
		return Objetivo;
	}

	public void setObjetivo(String objetivo) {
		Objetivo = objetivo;
	}

	public String getMaze() {
		return Maze;
	}

	public void setMaze(String maze) {
		Maze = maze;
	}

}
