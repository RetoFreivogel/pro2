package main;

public final class RegelStrecke extends TranferFunction {
	private final double Ks, Tu, Tg;

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

	public double getKs() {
		return this.Ks;
	}

	public double getTu() {
		return this.Tu;
	}

	public double getTg() {
		return this.Tg;
	}
	
	@Override
	protected double[] getPolyZaehler() {
		throw new UnsupportedOperationException("Not Implemented");
	}

	@Override
	protected double[] getPolyNenner() {
		throw new UnsupportedOperationException("Not Implemented");
	}
}
