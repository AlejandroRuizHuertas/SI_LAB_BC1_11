package SI.Problema;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import SI.Wilson.Celda;

public class Busqueda {
	static int id = 0;

	public static void SalirLaberinto(Celda[][] lab, Celda inicio, Celda fin, String estrategia) {
		int profundidad = 1000000;
		Frontera frontera = Frontera.getFrontera();
		List<String> visitados = new ArrayList<String>();
		List<Nodo> listaNodos = new ArrayList<Nodo>();
		boolean solucion = false;
		Nodo nodo = null;

		int valorinicial = valorInicial(estrategia, inicio, fin);

		Nodo inicial = new Nodo(id++, 0, "(0, 0)", null, null, 0, heuristica(inicio, fin), valorinicial, inicio);
		frontera.insertar(inicial);
		listaNodos.add(inicial);
		while (!frontera.esVacia() && !solucion) {
			nodo = frontera.retirar();
			if (esObjetivo(nodo, fin)) {
				solucion = true;
			}
			// Esto comprobarlo
			else if (!visitados.contains(nodo.getId_estado()) && nodo.getProfundidad() < profundidad) {
				visitados.add(nodo.getId_estado());
				expandirNodo(lab, frontera, estrategia, nodo, fin, listaNodos);
			}

		}
		if (solucion) {
			// Hay que asignar en el lab los valores 4 a los que son solución, 5 a los que
			// están en la frontera y 6 a los que están visitados
			crearNodosSolucion(nodo, inicial, estrategia);
			pintarFronteraVisitados(frontera, visitados, listaNodos);

		} else if (!solucion) {
			System.out.println("No hay solución.");
		}
	}

	private static void pintarFronteraVisitados(Frontera frontera, List<String> visitados, List<Nodo> listaNodos) {
		Nodo nodo;
		String id;
		// Vaciamos la frontera
		while (!frontera.esVacia()) {
			nodo = frontera.retirar();
			if (nodo.getCelda().getvalue() != 4) {
				nodo.getCelda().setvalue(5);
			}
		}
		// Vemos los visitados
		while (!visitados.isEmpty()) {
			id = visitados.remove(0);
			for (Nodo n : listaNodos) {
				if (n.getId_estado() == id) {
					if (n.getCelda().getvalue() != 4 && n.getCelda().getvalue() != 5) {
						n.getCelda().setvalue(6);
					}
				}

			}

		}

	}

	private static void crearNodosSolucion(Nodo nodoFinal, Nodo nodoInicial, String estrategia) {
		List<Nodo> solucion = new ArrayList<Nodo>();
		Stack<Nodo> pila = new Stack<Nodo>();
		solucion.add(nodoFinal);
		pila.push(nodoFinal);
		Nodo padre = nodoFinal.getId_padre();
		solucion.add(padre);
		pila.push(padre);

		while (!padre.equals(nodoInicial) && padre.getId_estado() != "(0, 0)") {

			padre = padre.getId_padre();
			solucion.add(padre);
			pila.push(padre);
		}

		// Hasta aquí
		for (Nodo d : solucion) {
			d.getCelda().setvalue(4);
		}
		crearTXTSolucion(pila, nodoFinal, estrategia);
	}

	private static void crearTXTSolucion(Stack<Nodo> solucion, Nodo nodoFinal, String estrategia) {
		try {
			String contenido = "[id][cost,state,father_id,action,depth,h,value]\n";
			while (!solucion.isEmpty()) {
				Nodo nodo = solucion.pop();
				contenido += nodo.toString();
			}

			String ruta = "solution_" + (nodoFinal.getCelda().getFila() + 1) + "x"
					+ (nodoFinal.getCelda().getColumna() + 1) + "_" + estrategia + ".txt";


			File file = new File(ruta);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contenido);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// El método valorInicial crea el valor inicial del primer nodo
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

	private static void expandirNodo(Celda[][] lab, Frontera frontera, String estrategia, Nodo nodo, Celda fin,
			List<Nodo> listaNodos) {
		List<Celda> vecinos = nodo.getCelda().obtenerSucesores(lab, nodo.getCelda(), nodo.getId_padre());
		for (Celda c : vecinos) {
			Nodo n = new Nodo(id++, (nodo.getCosto() + c.getvalue() + 1),
					"(" + c.getFila() + ", " + c.getColumna() + ")", nodo, direccion(nodo.getCelda(), c),
					nodo.getProfundidad() + 1, heuristica(c, fin), 0, c);
			n.setValor(calcula(estrategia, nodo, n, fin));
			frontera.insertar(n);
			listaNodos.add(n);
		}
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
			valor = hijo.getCosto();
			break;

		case "GREEDY":
			valor = heuristica(hijo.getCelda(), fin);
			break;

		case "A":
			valor = hijo.getCosto() + heuristica(hijo.getCelda(), fin);
			break;
		}

		return valor;
	}

}
