package main;

import util.Matlab;

public final class RegelStrecke extends TranferFunction {
	private final double Ks, Tu, Tg;

	public RegelStrecke(double ks, double tu, double tg) {
		this.Ks = ks;
		this.Tu = tu;
		this.Tg = tg;
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
		return new double[]{this.Ks*this.Tu/this.Tg};
	}

	@Override
	protected double[] getPolyNenner() {
		return Matlab.calcSani(this);
	}
}
