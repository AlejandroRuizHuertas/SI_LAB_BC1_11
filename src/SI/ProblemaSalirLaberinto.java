package SI;

import java.util.ArrayList;
import java.util.List;

import SI.Wilson.Celda;

public class ProblemaSalirLaberinto {
	private static List<Nodo> arbol;
	
	public static void SalirLaberinto(Celda[][]lab, int[]ini, int []fin) {
		arbol = new ArrayList<Nodo>();
		List<Nodo> frontera = new ArrayList<Nodo>();
		Nodo.CrearNodo(lab, arbol);
		int id = 0;
		//Cojo el elemento inicial
		Nodo inicial = convertirANodoInicial(lab[ini[0]][ini[1]], id);
	}
//El valor que introduzco en los nodos es aleatorio
	private static Nodo convertirANodoInicial(Celda celda, int id) {
		String id_estado = "("+celda.getFila()+","+celda.getColumna()+")";
		Nodo n = new Nodo(id, 1, id_estado, null, "",0,0,0 );
		return n;
	}

}
