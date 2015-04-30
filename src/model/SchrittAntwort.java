package model;

import org.apache.commons.math3.complex.Complex;

public class SchrittAntwort {
	private final double constant;
	private final Complex[] poles;
	private final Complex[] residues;

	public SchrittAntwort(Complex[] poles, Complex[] residues) {
		super();
		this.poles = poles;
		this.residues = residues;
		
		double constant = 0;
		
		for (int i = 0; i < residues.length; i++) {
			constant += residues[i].divide(poles[i]).getReal();
		}
		this.constant = -constant;
	}

	public double getY(double x) {
		double y = 0;

		for (int j = 0; j < poles.length; j++) {
			y += residues[j].multiply(poles[j].multiply(x).exp())
					.divide(poles[j]).getReal();
		}
		y += constant;
		return y;
	}

	public double getTend() {
		double Tend = 0;
		for (int i = 0; i < poles.length; i++) {
			Tend += poles[i].abs();
		}
		return Tend;
	}
}
