package main;

public class RegelStrecke extends RegelGlied{
	private double Ks, Tu, Tg;

	public RegelStrecke(double ks, double tu, double tg){
		this.Ks = ks;
		this.Tu = tu;
		this.Tg = tg;
	}
	
	public RegelStrecke(RegelStrecke regelstrecke){
		this.Ks = regelstrecke.getKs();
		this.Tu = regelstrecke.getTu();
		this.Tg = regelstrecke.getTg();
	}
	
	public void setKs(double ks) {
		Ks = ks;
		setChanged();
		notifyObservers();
	}

	public void setTu(double tu) {
		Tu = tu;
		setChanged();
		notifyObservers();
	}

	public void setTg(double tg) {
		Tg = tg;
		setChanged();
		notifyObservers();
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
