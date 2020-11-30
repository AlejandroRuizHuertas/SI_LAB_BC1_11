package SI.Problema;

import java.util.ArrayList;
import java.util.List;

import SI.Wilson.Celda;

public class Busqueda {

	public static void SalirLaberinto(Celda[][] lab, Celda inicio, Celda fin, String estrategia) {
		int profundidad = 1000000;

		Frontera frontera = Frontera.getFrontera();
		List<String> visitados = new ArrayList<String>();
		boolean solucion = false;
		Nodo nodo = null;

		int id = 0;

		int valorinicial = valorInicial(estrategia, inicio, fin);

		Nodo inicial = new Nodo(id++, 0, "(0, 0)", null, null, 0, heuristica(inicio, fin), valorinicial, inicio);
		frontera.insertar(inicial);

		while (!frontera.esVacia() && !solucion) {
			nodo = frontera.retirar();
			if (esObjetivo(nodo, fin)) {
				solucion = true;
			}
			// Esto comprobarlo
			else if (!visitados.contains(nodo.getId_estado()) && nodo.getProfundidad() < profundidad) {
				visitados.add(nodo.getId_estado());
				List<Nodo> hijos = new ArrayList<Nodo>();
				hijos = expandirNodo(lab, estrategia, nodo, id++, fin);
				for (Nodo n : hijos) {
					frontera.insertar(n);
				}
			}

		}
		if (solucion) {
			// Hay que asignar en el lab los valores 4 a los que son solución, 5 a los que
			// están en la frontera y 6 a los que están visitados
			
			crearNodosSolucion(frontera, visitados, nodo, inicial);

		} else if (!solucion) {
			System.out.println("No hay solución.");
		}
	}

	private static void crearNodosSolucion(Frontera frontera, List<String> visitados, Nodo nodoFinal,
			Nodo nodoInicial) {
		List<Nodo> solucion = new ArrayList<Nodo>();
		solucion.add(nodoFinal);
		Nodo padre = nodoFinal.getId_padre();
		solucion.add(padre);

		while (!padre.equals(nodoInicial) && padre.getId_estado() != "(0, 0)") {

			padre = padre.getId_padre();
			solucion.add(padre);
			
			System.out.println(padre.getId_estado());
		}
		
		//Hasta aquí
		for(Nodo d : solucion) {
			d.getCelda().setvalue(4);
		}
	}
	//El método valorInicial crea el valor inicial del primer nodo
	private static int valorInicial(String estrategia, Celda inicial, Celda fin) {
		
		int valor = 0;
		
		switch (estrategia) {
		case "BREADTH":
			valor = 0;
			break;

		case "DEPTH":
			valor = 0;
			break;

		case "UNIFORM":
			valor = 0;
			break;

		case "GREEDY":
			valor = heuristica(inicial, fin);
			break;

		case "A":
			valor = heuristica(inicial, fin);
			break;
		}
		return valor;
	}

	private static List<Nodo> expandirNodo(Celda[][] lab, String estrategia, Nodo nodo, int id, Celda fin) {
		List<Nodo> listanodos = new ArrayList<Nodo>();
		List<Celda> vecinos = nodo.getCelda().obtenerSucesores(lab, nodo.getCelda());
		for (Celda c : vecinos) {
			Nodo n = new Nodo(id++, c.getvalue() + 1, "(" + c.getFila() + ", " + c.getColumna() + ")", nodo,
					direccion(nodo.getCelda(), c), nodo.getProfundidad() + 1, heuristica(c, fin), 0, c);
			n.setValor(calcula(estrategia, nodo, n, fin));
			listanodos.add(n);
		}
		return listanodos;
	}

	private static String direccion(Celda actual, Celda destino) {
		int x = destino.getFila() - actual.getFila();
		int y = destino.getColumna() - actual.getColumna();

		if (x == 1) {// Sur
			return "S";
		} else if (x == -1) { // Norte
			return "N";
		}

		if (y == 1) {// Este
			return "E";
		} else if (y == -1) {// Oeste
			return "O";
		}
		return null;
	}

	private static boolean esObjetivo(Nodo nodo, Celda fin) {
		Celda c = nodo.getCelda();
		if (c.getFila() == fin.getFila() && c.getColumna() == fin.getColumna())
			return true;
		else
			return false;
	}

	private static int heuristica(Celda estado, Celda fin) {
		int h1 = Math.abs(estado.getFila() - fin.getFila());
		int h2 = Math.abs(estado.getColumna() - fin.getColumna());
		int resultado = h1 + h2;
		return resultado;
	}

	private static int calcula(String estrategia, Nodo padre, Nodo hijo, Celda fin) {
		int valor = hijo.getValor();
		switch (estrategia) {
		case "BREADTH":
			valor = hijo.getProfundidad();
			break;

		case "DEPTH":
			valor = -hijo.getProfundidad();
			break;

		case "UNIFORM":
			valor = padre.getCosto() + 1;
			break;

		case "GREEDY":
			valor =  heuristica(hijo.getCelda(), fin);
			break;

		case "A":
			valor = hijo.getCosto() + heuristica(hijo.getCelda(), fin);
			break;
		}

		return valor;
	}

}
