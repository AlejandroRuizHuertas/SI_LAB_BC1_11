package SI;

public class Sucesor {
	private String Mov;
	private String Estado;
	private int Coste;

	public Sucesor(String mov, String estado, int coste) {
		Mov = mov;
		Estado = estado;
		Coste = coste;
	}

	public String getMov() {
		return Mov;
	}

	public void setMov(String mov) {
		Mov = mov;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public int getCoste() {
		return Coste;
	}

	public void setCoste(int coste) {
		Coste = coste;
	}

}
