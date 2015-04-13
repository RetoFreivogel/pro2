package main;

public class ManuellDim extends ReglerDim {
	private double kr;
	private double tn;
	private double tv;

	public ManuellDim(double kr, double tn, double tv) {
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
	}

	public ManuellDim(Regler regler) {
		kr = regler.getKr();
		tn = regler.getTn();
		tv = regler.getTv();
	}

	@Override
	public ReglerDim makeCopy() {
		return new ManuellDim(kr, tn, tv);
	}

	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		return new Regler(kr, tn, tv);
	}

	public double getKr() {
		return kr;
	}
	
	public void setKr(double kr) {
		this.kr = kr;
	}
	
	public double getTn() {
		return tn;
	}
	
	public void setTn(double tn) {
		this.tn = tn;
	}
	
	public double getTv() {
		return tv;
	}
	
	public void setTv(double tv) {
		this.tv = tv;
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
		ManuellDim other = (ManuellDim) obj;
		if (Double.doubleToLongBits(kr) != Double.doubleToLongBits(other.kr))
			return false;
		if (Double.doubleToLongBits(tn) != Double.doubleToLongBits(other.tn))
			return false;
		if (Double.doubleToLongBits(tv) != Double.doubleToLongBits(other.tv))
			return false;
		return true;
	}
}
