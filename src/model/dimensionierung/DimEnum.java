package model.dimensionierung;

/**
 * Enum zur Unterscheidung von Dimensionierungsarten
 * @author Reto Freivogel
 *
 */
public enum DimEnum {
	MANUELL("Manuell"), 
	ZELLWEGER("Phasengang"),
	ITERATIV("Iterativ "),
	ZIEGLER("Ziegler"),
	OPPELT("Oppelt"),
	ROSENBERG("Rosenberg"),
	CHIENS("Chiens");
	
	private final String text;
	
	private DimEnum(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
