package SI.Problema;

import java.util.PriorityQueue;
import java.util.Queue;

public class Frontera {

	private static PriorityQueue<Nodo> fronteraPQ;
	private static Frontera F;

	public static Frontera getFrontera() {
		if (F == null) {
			F = new Frontera();
		}
		return F;
	}

	private Frontera() {
		fronteraPQ = new PriorityQueue<Nodo>();
	}

	public void insertarVarios(Queue<Nodo> listaNodos) {
		while (!listaNodos.isEmpty()) {
			fronteraPQ.add(listaNodos.poll());
		}
	}

	public void insertar(Nodo nodo) {
		fronteraPQ.add(nodo);
	}

	public Nodo retirar() {

		return fronteraPQ.poll();

	}
	public Nodo verPrimerElemento() {
		return fronteraPQ.peek();
	}

	public boolean esVacia() {
		return fronteraPQ.isEmpty();
	}
}
