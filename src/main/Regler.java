package main;

public final class Regler extends TranferFunction {
	private final double Kr, Tn, Tv;

	public Regler(double kr, double tn, double tv) {
		this.Kr = kr;
		this.Tn = tn;
		this.Tv = tv;
	}

	public Regler(Regler regler) {
		this.Kr = regler.getKr();
		this.Tn = regler.getTn();
		this.Tv = regler.getTv();
	}
	
	public Regler setKr(double kr){
		return new Regler(kr, this.Tn, this.Tv);
	}

	public double getKr() {
		return this.Kr;
	}

	public double getTn() {
		return this.Tn;
	}

	public double getTv() {
		return this.Tv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.Kr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.Tn);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.Tv);
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
		if (Double.doubleToLongBits(this.Kr) != Double
				.doubleToLongBits(other.Kr))
			return false;
		if (Double.doubleToLongBits(this.Tn) != Double
				.doubleToLongBits(other.Tn))
			return false;
		if (Double.doubleToLongBits(this.Tv) != Double
				.doubleToLongBits(other.Tv))
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
