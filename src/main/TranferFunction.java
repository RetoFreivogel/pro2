package main;

import util.Matlab;

public abstract class TranferFunction {
	protected abstract double[] getPolyZaehler();

	protected abstract double[] getPolyNenner();

	public double[] schrittantwort() {
		final double[] zaehler = getPolyZaehler();
		final double[] nenner = getPolyNenner();

		return Matlab.calcStep(zaehler, nenner);
	}
}
