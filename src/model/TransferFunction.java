package model;

import org.apache.commons.math3.complex.Complex;

public class TransferFunction {

	private final Polynom zaehler;
	private final Polynom nenner;

	public TransferFunction(Polynom zaehler, Polynom nenner) {
		super();
		this.zaehler = zaehler;
		this.nenner = nenner;
	}

	public SchrittAntwort schrittantwort() {

		double[] nen_coeff = nenner.getCoeff();
		Complex[] poles = nenner.getRoots();
		Complex[] residue = nenner.getResidues();

		double nen_leading = nen_coeff[nen_coeff.length - 1];
		for (int i = 0; i < residue.length; i++) {
			residue[i] = zaehler.eval(poles[i]).divide(residue[i])
					.divide(nen_leading);
		}

		return new SchrittAntwort(poles, residue);
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

	public double phaseAt(double freq){
		Complex cfreq = new Complex(0, freq);
		Complex val = zaehler.eval(cfreq).divide(nenner.eval(cfreq));
		
		return val.getArgument();
	}

	public double amplitudeAt(double freq){
		Complex cfreq = new Complex(0, freq);
		Complex val = zaehler.eval(cfreq).divide(nenner.eval(cfreq));
		
		return val.abs();
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
