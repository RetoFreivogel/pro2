package main;

import util.Matlab;

public class RegelStrecke extends TranferFunction {
	private ObservableDouble ks, tu, tg;

	public RegelStrecke(double ks, double tu, double tg) {
		this.ks = new ObservableDouble(ks);
		this.tu = new ObservableDouble(tu);
		this.tg = new ObservableDouble(tg);
	}

	public RegelStrecke(RegelStrecke other) {
		ks = new ObservableDouble(other.getKs());
		ks = new ObservableDouble(other.getKs());
		ks = new ObservableDouble(other.getKs());
	}

	public ObservableDouble getKs() {
		return ks;
	}

	public ObservableDouble getTu() {
		return tu;
	}

	public ObservableDouble getTg() {
		return tg;
	}

	@Override
	protected double[] getPolyZaehler() {
		return new double[] { ks.getValue() };
	}

	@Override
	protected double[] getPolyNenner() {
		double[] Tcoeff = Matlab.calcSani(this);
		int n = Tcoeff.length;

		double[] nenner = new double[n + 1];
		nenner[n] = 1.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				nenner[j] += nenner[j + 1] * Tcoeff[i];
			}
		}
		return nenner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ks == null) ? 0 : ks.hashCode());
		result = prime * result + ((tg == null) ? 0 : tg.hashCode());
		result = prime * result + ((tu == null) ? 0 : tu.hashCode());
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
		if (ks == null) {
			if (other.ks != null)
				return false;
		} else if (!ks.equals(other.ks))
			return false;
		if (tg == null) {
			if (other.tg != null)
				return false;
		} else if (!tg.equals(other.tg))
			return false;
		if (tu == null) {
			if (other.tu != null)
				return false;
		} else if (!tu.equals(other.tu))
			return false;
		return true;
	}

}
