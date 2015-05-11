package model;

public enum ChiensRegelung {
	APERIODSTOER("<html>Aperiodisches<br>Störverhalten</html>"), 
	APERIODFUEHR("<html>Aperiodisches<br>Führungsverhalten</html>"),
	ZWANZIGSTOER("<html>Periodisches<br>Störverhalten</html>"),
	ZWANZIGFUEHR("<html>Periodisches<br>Führungsverhalten</html>");
	
	private final String text;
	
	private ChiensRegelung(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
