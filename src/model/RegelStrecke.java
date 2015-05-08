package model;

import java.util.Observable;
import java.util.Scanner;

public class RegelStrecke extends Observable implements RegelGlied {

	private double ks, tu, tg;

	public RegelStrecke(Scanner sc) {
		sc.skip("ks: ");
		ks = sc.nextDouble();
		sc.nextLine();
		sc.skip("tu: ");
		tu = sc.nextDouble();
		sc.nextLine();
		sc.skip("tg: ");
		tg = sc.nextDouble();
		sc.nextLine();
	}

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
		if (ks <= 0)
			throw new IllegalArgumentException("ks muss positiv sein");

		this.ks = ks;
		setChanged();
		notifyObservers();
	}

	public void setTu(double tu) {
		if (tu <= 0)
			throw new IllegalArgumentException("tu muss positiv sein");

		this.tu = tu;
		setChanged();
		notifyObservers();
	}

	public void setTg(double tg) {
		if (tg <= 0)
			throw new IllegalArgumentException("tg muss positiv sein");

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
