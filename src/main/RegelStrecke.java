package main;

public class RegelStrecke extends RegelGlied {
	private double Ks, Tu, Tg;

	public RegelStrecke(double ks, double tu, double tg) {
		this.Ks = ks;
		this.Tu = tu;
		this.Tg = tg;
	}

	public RegelStrecke(RegelStrecke regelstrecke) {
		this.Ks = regelstrecke.getKs();
		this.Tu = regelstrecke.getTu();
		this.Tg = regelstrecke.getTg();
	}

	public void setKs(double ks) {
		this.Ks = ks;
		setChanged();
		notifyObservers();
	}

	public void setTu(double tu) {
		this.Tu = tu;
		setChanged();
		notifyObservers();
	}

	public void setTg(double tg) {
		this.Tg = tg;
		setChanged();
		notifyObservers();
	}

	public double getKs() {
		return this.Ks;
	}

	public double getTu() {
		return this.Tu;
	}

	public double getTg() {
		return this.Tg;
	}
}
