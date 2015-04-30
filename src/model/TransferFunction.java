package model;

import org.apache.commons.math3.complex.Complex;

public class TransferFunction {

	private Polynom zaehler;
	private Polynom nenner;
	
	public TransferFunction(Polynom zaehler, Polynom nenner) {
		super();
		this.zaehler = zaehler;
		this.nenner = nenner;
	}

	public double getTend(){
		Complex[] poles = nenner.getRoots();
		Complex[] zeros = zaehler.getRoots();
		
		double Tsum = 0;
		for (int i = 0; i < poles.length; i++) {
			Tsum += 1 / poles[i].abs();
		}
		
		for (int i = 0; i < zeros.length; i++) {
			Tsum -= 1 / zeros[i].abs();
		}
		
		Tsum *= 10;
		return Tsum;
	}

	public double[] schrittantwort() {
		return schrittantwort(getTend());
	}
	
	public double[] schrittantwort(double Tend) {
		
		double[] nen_coeff = nenner.getCoeff();
		Complex[] poles = nenner.getRoots();
		Complex[] residue = nenner.getResidues();
		
		double nen_leading = nen_coeff[nen_coeff.length -1];
		for(int i = 0; i < residue.length; i++){
			residue[i] = zaehler.eval(poles[i]).divide(residue[i]).divide(nen_leading);
		}		
		
		double T = Tend / (128-1);
		double[] impulse = new double[128];
		
		for (int i = 0; i < impulse.length; i++) {
			double t = T * (double)i;
			
			for (int j = 0; j < poles.length; j++) {
				impulse[i] += residue[j].multiply(poles[j].multiply(t).exp()).divide(poles[j]).getReal();
			}
			
		}
			
		
		for (int i = 0; i < impulse.length; i++) {
			impulse[i] += 1.0;
		}
		
		
		return impulse;
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
