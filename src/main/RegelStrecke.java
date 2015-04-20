package main;

import util.Matlab;

public class RegelStrecke extends TranferFunction {
	private ObservableDouble ks, tu, tg;

	public RegelStrecke(double ks, double tu, double tg) {
		if(ks < 0)throw new IllegalArgumentException("ks can't be negative");
		if(tu < 0)throw new IllegalArgumentException("tu can't be negative");
		if(tg < 0)throw new IllegalArgumentException("tg can't be negative");
		
		this.ks = new ObservableDouble(ks);
		this.tu = new ObservableDouble(tu);
		this.tg = new ObservableDouble(tg);
	}

	public RegelStrecke(RegelStrecke other) {
		ks = new ObservableDouble(other.getKs());
		tu = new ObservableDouble(other.getTu());
		tg = new ObservableDouble(other.getTg());
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
	public double[] getPolyZaehler() {
		return new double[] { ks.getValue() };
	}

	@Override
	public double[] getPolyNenner() {
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
		result = prime * result + ks.hashCode();
		result = prime * result + tg.hashCode();
		result = prime * result + tu.hashCode();
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
		if (!ks.equals(other.ks))
			return false;
		if (!tg.equals(other.tg))
			return false;
		if (!tu.equals(other.tu))
			return false;
		return true;
	}

}
