package model.dimensionierung;

/**
 * Enum zur Unterscheidung von den Topologien
 * @author Reto Freivogel
 *
 */
public enum TopoEnum {
	P("P"),
	PI("PI"),
	PID("PID");
	
	private final String text;
	
	TopoEnum(String text){
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
