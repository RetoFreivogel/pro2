package main;

import util.Matlab;

public abstract class TranferFunction {
	protected abstract double[] getPolyZaehler();

	protected abstract double[] getPolyNenner();

	public double[] schrittantwort() {
		final double[] zaehler = getPolyZaehler();
		final double[] nenner = getPolyNenner();

		String polyString = "[";
		for (int i = 0; i < zaehler.length; i++) {
			polyString += zaehler[i] + " ";
		}
		polyString += "],[";
		for (int i = 0; i < nenner.length; i++) {
			polyString += nenner[i] + " ";
		}
		polyString += "]";

		double[] antwort = Matlab.calcStep(polyString);

		return antwort;
	}
}
