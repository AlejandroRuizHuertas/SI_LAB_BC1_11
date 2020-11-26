package SI.Problema;

import java.util.ArrayList;
import java.util.List;

import SI.Wilson.Celda;

public class Busqueda {

	public static void SalirLaberinto(Celda[][] lab, Celda inicio, Celda fin, String estrategia) {
		int profundidad = 1000000;
		
		Frontera frontera = Frontera.getFrontera();
		List <String> visitados = new ArrayList<String>();
		boolean solucion = false;
		
		int id = 0;

		Nodo inicial = new Nodo(id++, 0, "(0, 0)", null, null, 0, heuristica(lab, inicio, fin), calcula(estrategia, null),inicio);
		frontera.insertar(inicial);
		
		while(!frontera.esVacia()&&!solucion) {
			Nodo nodo = frontera.retirar();
			if(esObjetivo(nodo, fin)) {
				solucion = true;
			}
			//Esto comprobarlo
			else if (!visitados.contains(nodo.getId_estado()) && nodo.getProfundidad()<profundidad) {
				visitados.add(nodo.getId_estado());
				List <Nodo> hijos = new ArrayList<Nodo>();
				hijos = expandirNodo(lab, estrategia, nodo);
				for (Nodo n : hijos) {
					frontera.insertar(n);
				}
			}
			
		}
		if (solucion) {
			//Hay que asignar en el lab los valores 4 a los que son solución, 5 a los que están en la frontera y 6 a los que están visitados
		}
	}

	private static List<Nodo> expandirNodo(Celda[][] lab, String estrategia, Nodo nodo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

	private static boolean esObjetivo(Nodo nodo, Celda fin) {
		Celda c = nodo.getCelda();
		if (c.getFila() == fin.getFila() && c.getColumna() == fin.getColumna())
			return true;
		else
			return false;
	}

	private static int heuristica(Celda[][] lab, Celda estado, Celda fin) {
		int h1 = Math.abs(estado.getFila() - fin.getFila());
		int h2 = Math.abs(estado.getColumna() - fin.getColumna());
		int resultado = h1 + h2;
		return resultado;
	}

	private static int calcula(String estrategia, Nodo n) {

		return 0;
	}

	/*
	 * El valor que introduzco en los nodos es aleatorio private static Nodo
	 * convertirANodoInicial(Celda celda, int id) { String id_estado = "(" +
	 * celda.getFila() + "," + celda.getColumna() + ")"; Nodo n = new Nodo(id, 1,
	 * id_estado, null, "", 0, 0, 0); return n; }
	 */
}
