package main;

import main.TranferFunction;

public abstract class ProductTransferFunction extends TranferFunction {

	abstract double[] getNennerFactors();
	abstract double[] getZaehlerFactors();
	abstract double getBaseFactor();

	@Override
	public double[] getPolyZaehler() {
		int n = getZaehlerFactors().length;

		double[] zaehler = new double[n + 1];
		zaehler[n] = 1.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				zaehler[j] += zaehler[j + 1] * getZaehlerFactors()[i];
			}
		}
		for (int i = 0; i < zaehler.length; i++) {
			zaehler[i] *= getBaseFactor();
		}
		return zaehler;
	}

	@Override
	public double[] getPolyNenner() {
		int n = getNennerFactors().length;

		double[] nenner = new double[n + 1];
		nenner[n] = 1.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				nenner[j] += nenner[j + 1] * getNennerFactors()[i];
			}
		}
		return nenner;
	}

}
