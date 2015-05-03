package model;

public enum Dimensionierung {
	MANUELL("Manuell"), 
	PHASENGANG("Phasengang"),
	ZIEGLER("Ziegler"),
	OPPELT("Oppelt"),
	ROSENBERG("Rosenberg"),
	CHIEN("Chien");
	
	private final String text;
	
	private Dimensionierung(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}