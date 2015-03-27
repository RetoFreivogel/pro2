package main;

public class RegelStrecke extends RegelGlied{
	private double Ks, Tu, Tg;

	public RegelStrecke(double ks, double tu, double tg){
		this.Ks = ks;
		this.Tu = tu;
		this.Tg = tg;
	}
	
	public double getKs() {
		return Ks;
	}

	public double getTu() {
		return Tu;
	}

	public double getTg() {
		return Tg;
	}	
}
