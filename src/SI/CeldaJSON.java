package SI;

public class CeldaJSON {
	int value;
	boolean[] neighbors;
	
	public CeldaJSON(int value, boolean[] neighbors) {
		super();
		this.value = value;
		this.neighbors = neighbors;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean[] getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(boolean[] neighbors) {
		this.neighbors = neighbors;
	}
}
