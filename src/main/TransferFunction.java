package main;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import util.Matlab;

public class TransferFunction {

	private PolynomialFunction zaehler;
	private PolynomialFunction nenner;

	public TransferFunction(double[] zaehler, double[] nenner) {
		this.zaehler = new PolynomialFunction(reverseDoubleArray(zaehler));
		this.nenner = new PolynomialFunction(reverseDoubleArray(nenner));
	}

	public TransferFunction(PolynomialFunction zaehler, PolynomialFunction nenner) {
		super();
		this.zaehler = zaehler;
		this.nenner = nenner;
	}

	public double[] schrittantwort() {
		double[] z = reverseDoubleArray(zaehler.getCoefficients());
		double[] n = reverseDoubleArray(nenner.getCoefficients());

		//use LaguerreSolver to get roots
		//then calc the residuen
		//then generate the impulse answer
		
		return Matlab.calcStep(z, n);
	}

	private static double[] reverseDoubleArray(double[] array) {
		double[] reverse = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			reverse[array.length - 1 - i] = array[i];
		}
		return reverse;
	}

	public TransferFunction conv(TransferFunction other) {
		return new TransferFunction(zaehler.multiply(other.getZaehler()),
				nenner.multiply(other.getNenner()));
	}

	public TransferFunction feedback_loop() {
		return new TransferFunction(zaehler, nenner.add(zaehler));
	}

	public PolynomialFunction getZaehler() {
		return zaehler;
	}

	public PolynomialFunction getNenner() {
		return nenner;
	}

	public void setZaehler(PolynomialFunction zaehler) {
		this.zaehler = zaehler;
	}

	public void setNenner(PolynomialFunction nenner) {
		this.nenner = nenner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nenner == null) ? 0 : nenner.hashCode());
		result = prime * result + ((zaehler == null) ? 0 : zaehler.hashCode());
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
		TransferFunction other = (TransferFunction) obj;
		if (nenner == null) {
			if (other.nenner != null)
				return false;
		} else if (!nenner.equals(other.nenner))
			return false;
		if (zaehler == null) {
			if (other.zaehler != null)
				return false;
		} else if (!zaehler.equals(other.zaehler))
			return false;
		return true;
	}
	
}
