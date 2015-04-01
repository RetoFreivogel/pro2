package main;

public final class Regler extends TranferFunction {
	private final double kr, tn, tv;

	public Regler(double kr, double tn, double tv) {
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
	}

	public Regler(Regler other) {
		kr = other.getKr();
		tn = other.getTn();
		tv = other.getTv();
	}

	public double getKr() {
		return kr;
	}

	public double getTn() {
		return tn;
	}

	public double getTv() {
		return tv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(kr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tn);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tv);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Regler other = (Regler) obj;
		if (Double.doubleToLongBits(kr) != Double.doubleToLongBits(other.kr))
			return false;
		if (Double.doubleToLongBits(tn) != Double.doubleToLongBits(other.tn))
			return false;
		if (Double.doubleToLongBits(tv) != Double.doubleToLongBits(other.tv))
			return false;
		return true;
	}

	@Override
	protected double[] getPolyZaehler() {
		throw new UnsupportedOperationException("Not Implemented");
	}

	@Override
	protected double[] getPolyNenner() {
		throw new UnsupportedOperationException("Not Implemented");
	}
}
