package model;

public enum ChiensRegelung {
	APERIODSTOER("Aperiodisches St�rverhalten"), 
	APERIODFUEHR("Aperiodisches F�hrungsverhalten"),
	ZWANZIGSTOER("Periodisches St�rverhalten"),
	ZWANZIGFUEHR("Periodisches F�hrungsverhalten");
	
	private final String text;
	
	private ChiensRegelung(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
