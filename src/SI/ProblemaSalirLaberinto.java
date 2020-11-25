package SI;

import java.util.ArrayList;
import java.util.List;

import SI.Wilson.Celda;

public class ProblemaSalirLaberinto {
	private static List<Nodo> arbol;

	public static void SalirLaberinto(Celda[][] lab, Celda inicio, Celda objetivo) {
		arbol = new ArrayList<Nodo>();
		List<Nodo> frontera = new ArrayList<Nodo>();
		String id_ini = "(0, 0)";
		String id_fin = "("+objetivo.getFila()+", "+objetivo.getColumna()+")";
		String txt = "sucesores"+lab.length+"X"+lab[0].length+".json";
		Objetivo fin = new Objetivo(id_ini,id_fin,txt);
		int id = 0;
		Nodo ini = new Nodo(id++, 0,id_ini , null, null, 0, Heuristica(lab, id_ini),calcula());
	}

	private static int Heuristica(Celda[][]lab, String estado ) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int calcula() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * El valor que introduzco en los nodos es aleatorio private static Nodo
	 * convertirANodoInicial(Celda celda, int id) { String id_estado = "(" +
	 * celda.getFila() + "," + celda.getColumna() + ")"; Nodo n = new Nodo(id, 1,
	 * id_estado, null, "", 0, 0, 0); return n; }
	 */
}
