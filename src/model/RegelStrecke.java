package model;

import java.io.Serializable;

public final class RegelStrecke implements RegelGlied, Serializable {
	private static final long serialVersionUID = 1L;
	private final double ks, tu, tg;

	public RegelStrecke(double ks, double tu, double tg) {
		if (ks <= 0)
			throw new IllegalArgumentException("ks muss positiv sein");
		if (tu <= 0)
			throw new IllegalArgumentException("tu muss positiv sein");
		if (tg <= 0)
			throw new IllegalArgumentException("tg muss positiv sein");

		this.ks = ks;
		this.tu = tu;
		this.tg = tg;
	}

	public double getKs() {
		return ks;
	}

	public double getTu() {
		return tu;
	}

	public double getTg() {
		return tg;
	}

	public RegelStrecke setKs(double ks) {
		return new RegelStrecke(ks, tu, tg);
	}

	public RegelStrecke setTu(double tu) {
		return new RegelStrecke(ks, tu, tg);
	}

	public RegelStrecke setTg(double tg) {
		return new RegelStrecke(ks, tu, tg);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ks);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tu);
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
		if (Double.doubleToLongBits(ks) != Double.doubleToLongBits(other.ks))
			return false;
		if (Double.doubleToLongBits(tg) != Double.doubleToLongBits(other.tg))
			return false;
		if (Double.doubleToLongBits(tu) != Double.doubleToLongBits(other.tu))
			return false;
		return true;
	}

	public int getOrdnung() {
		return SaniApprox.getOrdnung(tu, tg);
	}

	@Override
	public TransferFunction getTranferFunction() {
		double[] Tcoeffs = SaniApprox.calcSani(tu, tg);

		double Tprodukt = 1.0;
		for (int i = 0; i < Tcoeffs.length; i++) {
			Tprodukt *= Tcoeffs[i];
		}

		for (int i = 0; i < Tcoeffs.length; i++) {
			Tcoeffs[i] = -1 / Tcoeffs[i];
		}
		Polynom nenner = Polynom.fromRoots(Tcoeffs);

		nenner = nenner.mul(Tprodukt);

		return new TransferFunction(new Polynom(new double[] { ks }), nenner);
	}
	
	@Override
	public String toString() {
		return "ks: " + ks + "\ntu: " + tu + "\ntg: " + tg + "\n";
	}
}
