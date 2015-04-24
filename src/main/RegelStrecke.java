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
			
		
		
		/*
		double[] Tpoly = new double[Tcoeffs.length + 1];
		Tpoly[0] = 1.0;
		for (int i = 0; i < Tcoeffs.length; i++) {
			for (int j = Tcoeffs.length; j > 0; j--) {
				Tpoly[j] = Tpoly[j-1] * Tcoeffs[i];
			}
		}
		*/
		double Tprodukt = 1.0;
		for (int i = 0; i < Tcoeffs.length; i++) {
			Tprodukt *= Tcoeffs[i];
		}
		
		for (int i = 0; i < Tcoeffs.length; i++) {
			Tcoeffs[i] = -1 / Tcoeffs[i];
		}
		Polynom nenner = Polynom.fromRoots(Tcoeffs);
		
		nenner = nenner.mul(Tprodukt);
		
		return new TransferFunction(Polynom.fromCoeff(new double[]{ks}), nenner);	
	}
}
