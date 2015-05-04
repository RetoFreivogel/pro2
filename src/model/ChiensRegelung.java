package model;

public enum ChiensRegelung {
	APERIODSTOER("Aper. Störverhalten"), 
	APERIODFUEHR("Aper. Führungsverhalten"),
	ZWANZIGSTOER("Per. Störverhalten"),
	ZWANZIGFUEHR("Per. Führungsverhalten"),
	
	private final String text;
	
	private ChiensRegelung(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
