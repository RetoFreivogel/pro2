package model.dimensionierung;

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
