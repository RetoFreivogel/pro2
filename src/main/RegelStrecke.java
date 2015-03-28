package main;

interface ImmutableRegelStrecke extends ImmutableRegelGlied{
	public double getKs();
	public double getTu();
	public double getTg();
}

public class RegelStrecke extends RegelGlied implements ImmutableRegelStrecke{
	private double Ks, Tu, Tg;

	public RegelStrecke(double ks, double tu, double tg){
		this.Ks = ks;
		this.Tu = tu;
		this.Tg = tg;
	}
	
	public RegelStrecke(ImmutableRegelStrecke regelstrecke){
		this.Ks = regelstrecke.getKs();
		this.Tu = regelstrecke.getTu();
		this.Tg = regelstrecke.getTg();
	}
	
	public void setKs(double ks) {
		Ks = ks;
	}

	public void setTu(double tu) {
		Tu = tu;
	}

	public void setTg(double tg) {
		Tg = tg;
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
