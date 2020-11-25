package SI;

import SI.Wilson.Celda;

public class ProblemaSalirLaberinto {

	public static void SalirLaberinto(Celda[][] lab, Celda inicio, Celda fin) {
		Frontera.getFrontera();
		String id_ini = "(0, 0)";
		String id_fin = "(" + fin.getFila() + ", " + fin.getColumna() + ")";
		String txt = "sucesores" + lab.length + "X" + lab[0].length + ".json";
		Objetivo o = new Objetivo(id_ini, id_fin, txt);
		int id = 0;
		Nodo ini = new Nodo(id++, 0, id_ini, null, null, 0, Heuristica(lab, inicio, fin), calcula());
	}

	
	private static int Heuristica(Celda[][] lab, Celda estado, Celda fin) {
		int h1 = Math.abs(estado.getFila() - fin.getFila());
		int h2 = Math.abs(estado.getColumna() - fin.getColumna());
		int resultado = h1 + h2;
		return resultado;
	}

	private static int calcula() {
		
		return 0;
	}

	/*
	 * El valor que introduzco en los nodos es aleatorio private static Nodo
	 * convertirANodoInicial(Celda celda, int id) { String id_estado = "(" +
	 * celda.getFila() + "," + celda.getColumna() + ")"; Nodo n = new Nodo(id, 1,
	 * id_estado, null, "", 0, 0, 0); return n; }
	 */
}
