package model;

public enum ReglerTopologie {
	PI("PI"),
	PID("PID");
	
	private final String text;
	
	ReglerTopologie(String text){
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
