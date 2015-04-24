package main;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

public class TransferFunction {

	private Polynom zaehler;
	private Polynom nenner;
	
	public TransferFunction(Polynom zaehler, Polynom nenner) {
		super();
		this.zaehler = zaehler;
		this.nenner = nenner;
	}

	public double[] schrittantwort() {
		Complex[] poles = nenner.getRoots();
		Complex[] residuen = nenner.getResidues();
		
		for(int i = 0; i < residuen.length; i++){
			residuen[i] = zaehler.eval(poles[i]).divide(residuen[i]);
		}
		
		double[] impulse = new double[4 * 1024];
		
		for (int i = 0; i < impulse.length; i++) {
			double t = (double)i/(double)impulse.length * 0.01;
			for (int j = 0; j < poles.length; j++) {
				impulse[i] += residuen[j].multiply(poles[j].multiply(t).exp()).getReal();
			}
		}
		
		System.out.println(impulse[0]);
		System.out.println(impulse[1024]);
		System.out.println(impulse[2048]);
		System.out.println(impulse[3072]);
		System.out.println(impulse[4095]);
		
		for (int i = 1; i < impulse.length; i++) {
			impulse[i] += impulse[i-1];
		}
		
		return impulse;
		//return Matlab.calcStep(z, n);
	}

	public TransferFunction conv(TransferFunction other) {
		return new TransferFunction(zaehler.mul(other.getZaehler()),
				nenner.mul(other.getNenner()));
	}

	public TransferFunction feedback_loop() {
		return new TransferFunction(zaehler, nenner.add(zaehler));
	}

	public Polynom getZaehler() {
		return zaehler;
	}

	public Polynom getNenner() {
		return nenner;
	}

	public void setZaehler(Polynom zaehler) {
		this.zaehler = zaehler;
	}

	public void setNenner(Polynom nenner) {
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

	@Override
	public String toString() {
		return "TransferFunction [zaehler=" + zaehler + ", nenner=" + nenner
				+ "]";
	}
	
}
