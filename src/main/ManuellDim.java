package main;

public class ManuellDim extends ReglerDim {
	private double Kr, Tn, Tv;

	public ManuellDim(double kr, double tn, double tv) {
		this.Kr = kr;
		this.Tn = tn;
		this.Tv = tv;
	}

	public ManuellDim(Regler regler) {
		this.Kr = regler.getKr();
		this.Tn = regler.getTn();
		this.Tv = regler.getTv();
	}

	public Regler calc(RegelStrecke regelstrecke) {
		return new Regler(Kr, Tn, Tv);
	}

	public double getKr() {
		return Kr;
	}

	public void setKr(double kr) {
		Kr = kr;
	}

	public double getTn() {
		return Tn;
	}

	public void setTn(double tn) {
		Tn = tn;
	}

	public double getTv() {
		return Tv;
	}

	public void setTv(double tv) {
		Tv = tv;
	}
}
