package main;

import java.util.Observable;

import util.Matlab;

public class RegelStrecke extends Observable implements RegelGlied {
	private double ks, tu, tg;

	public RegelStrecke(double ks, double tu, double tg) {
		if(ks < 0)throw new IllegalArgumentException("ks can't be negative");
		if(tu < 0)throw new IllegalArgumentException("tu can't be negative");
		if(tg < 0)throw new IllegalArgumentException("tg can't be negative");
		
		this.ks = ks;
		this.tu = tu;
		this.tg = tg;
	}

	public RegelStrecke(RegelStrecke other) {
		ks = other.getKs();
		tu = other.getTu();
		tg = other.getTg();
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

	public void setKs(double ks) {
		this.ks = ks;
		setChanged();
		notifyObservers();
	}

	public void setTu(double tu) {
		this.tu = tu;
		setChanged();
		notifyObservers();
	}

	public void setTg(double tg) {
		this.tg = tg;
		setChanged();
		notifyObservers();
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

	@Override
	public TransferFunction getTranferFunction() {
		double[] Tcoeffs = Matlab.calcSani(this);
		
		int n = Tcoeffs.length;
		double[] nenner = new double[n + 1];
		nenner[n] = 1.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				nenner[j] += nenner[j + 1] * Tcoeffs[i];
			}
		}
			
		return new TransferFunction(new double[]{ks}, nenner);	
	}
}
