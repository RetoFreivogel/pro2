package main;

import util.Matlab;

public abstract class TranferFunction {
	public abstract double[] getPolyZaehler();

	public abstract double[] getPolyNenner();

	public double[] schrittantwort() {
		final double[] zaehler = getPolyZaehler();
		final double[] nenner = getPolyNenner();

		return Matlab.calcStep(zaehler, nenner);
	}
}
