package model;

public enum ChiensRegelung {
	APERIODSTOER("Aper. St�rverhalten"), 
	APERIODFUEHR("Aper. F�hrungsverhalten"),
	ZWANZIGSTOER("Per. St�rverhalten"),
	ZWANZIGFUEHR("Per. F�hrungsverhalten"),
	
	private final String text;
	
	private ChiensRegelung(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
