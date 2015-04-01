package main;

import util.Matlab;

public final class RegelStrecke extends TranferFunction {
	private final double Ks, Tu, Tg;

	public RegelStrecke(double ks, double tu, double tg) {
		this.Ks = ks;
		this.Tu = tu;
		this.Tg = tg;
	}
	
	public RegelStrecke(RegelStrecke other) {
		this.Ks = other.getKs();
		this.Tu = other.getTu();
		this.Tg = other.getTg();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Ks);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Tg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Tu);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegelStrecke other = (RegelStrecke) obj;
		if (Double.doubleToLongBits(Ks) != Double.doubleToLongBits(other.Ks))
			return false;
		if (Double.doubleToLongBits(Tg) != Double.doubleToLongBits(other.Tg))
			return false;
		if (Double.doubleToLongBits(Tu) != Double.doubleToLongBits(other.Tu))
			return false;
		return true;
	}

	@Override
	protected double[] getPolyNenner() {
		return Matlab.calcSani(this);
	}
}
