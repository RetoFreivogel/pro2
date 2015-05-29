package model.dimensionierung;

public enum ChiensEnum {
	APERIODSTOER("<html>Aperiodisches<br>St�rverhalten</html>"), 
	APERIODFUEHR("<html>Aperiodisches<br>F�hrungsverhalten</html>"),
	ZWANZIGSTOER("<html>Periodisches<br>St�rverhalten</html>"),
	ZWANZIGFUEHR("<html>Periodisches<br>F�hrungsverhalten</html>");
	
	private final String text;
	
	private ChiensEnum(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
