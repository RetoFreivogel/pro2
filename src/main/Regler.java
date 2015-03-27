package main;

public class Regler extends RegelGlied{
	private double Kr, Tn, Tv;

	public Regler(double kr, double tn, double tv){
		this.Kr = kr;
		this.Tn = tn;
		this.Tv = tv;
	}
	
	public double getKr() {
		return Kr;
	}

	public double getTn() {
		return Tn;
	}

	public double getTv() {
		return Tv;
	}
}
