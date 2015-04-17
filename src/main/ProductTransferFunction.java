package main;

import main.TranferFunction;

public class ProductTransferFunction extends TranferFunction {

	double[] nennerFactors;
	double[] zaehlerFactors;
	double base;
	

	public ProductTransferFunction(double[] nennerFactors,
			double[] zaehlerFactors, double base) {
		super();
		this.nennerFactors = nennerFactors;
		this.zaehlerFactors = zaehlerFactors;
		this.base = base;
	}

	@Override
	protected double[] getPolyZaehler() {
		int n = zaehlerFactors.length;

		double[] zaehler = new double[n + 1];
		zaehler[n] = 1.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				zaehler[j] += zaehler[j + 1] * zaehlerFactors[i];
			}
		}
		for (int i = 0; i < zaehler.length; i++) {
			zaehler[i] *= base;
		}
		return zaehler;
	}

	@Override
	protected double[] getPolyNenner() {
		int n = nennerFactors.length;

		double[] nenner = new double[n + 1];
		nenner[n] = 1.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				nenner[j] += nenner[j + 1] * nennerFactors[i];
			}
		}
		return nenner;
	}

	public double[] getNennerFactors() {
		return nennerFactors;
	}

	public void setNennerFactors(double[] nennerFactors) {
		this.nennerFactors = nennerFactors;
	}

	public double[] getZaehlerFactors() {
		return zaehlerFactors;
	}

	public void setZaehlerFactors(double[] zaehlerFactors) {
		this.zaehlerFactors = zaehlerFactors;
	}
}
