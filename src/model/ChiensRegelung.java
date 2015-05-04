package model;

public enum ChiensRegelung {
	APERIODSTOER("Aperiodisches Störverhalten"), 
	APERIODFUEHR("Aperiodisches Führungsverhalten"),
	ZWANZIGSTOER("Periodisches Störverhalten"),
	ZWANZIGFUEHR("Periodisches Führungsverhalten");
	
	private final String text;
	
	private ChiensRegelung(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
