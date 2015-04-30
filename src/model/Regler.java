package model;

public final class Regler implements RegelGlied {
	private final ReglerTopologie topo;
	private final double kr, tn, tv, tp;

	public Regler(double kr) {
		this.topo = ReglerTopologie.P;
		this.kr = kr;
		this.tn = Double.NaN;
		this.tv = Double.NaN;
		this.tp = Double.NaN;
	}
	
	public Regler(double kr, double tn) {
		this.topo = ReglerTopologie.PI;
		this.kr = kr;
		this.tn = tn;
		this.tv = Double.NaN;
		this.tp = Double.NaN;
	}
	
	public Regler(double kr, double tn, double tv) {
		this.topo = ReglerTopologie.PID;
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
		this.tp = tv / 100;
	}

	public Regler(double kr, double tn, double tv, double tp) {
		if(tv < tp){
			throw new IllegalArgumentException("tv darf nicht kleiner als tp sein!");
		};
		this.topo = ReglerTopologie.PID;
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
		this.tp = tp;
	}

	public Regler(Regler other) {
		topo = other.getTopo();
		kr = other.getKr();
		tn = other.getTn();
		tv = other.getTv();
		tp = other.getTp();
	}

	public ReglerTopologie getTopo() {
		return topo;
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

	public double getTp() {
		return tp;
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
		temp = Double.doubleToLongBits(tp);
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
		if (Double.doubleToLongBits(tp) != Double.doubleToLongBits(other.tp))
			return false;
		if (Double.doubleToLongBits(tv) != Double.doubleToLongBits(other.tv))
			return false;
		return true;
	}

	@Override
	public TransferFunction getTranferFunction() {
		Polynom zaehler;
		Polynom nenner;
		
		switch(topo){
		case P:
			zaehler = new Polynom(new double[] {kr});
			nenner = new Polynom(new double[] {1});
			return new TransferFunction(zaehler, nenner);
		case PI:
			zaehler = new Polynom(new double[] { kr, kr * (tn)});
			nenner = new Polynom(new double[] { 0, tn});
			return new TransferFunction(zaehler, nenner);
		case PID:
			zaehler = new Polynom(new double[] { kr, kr * (tn + tp),
					kr * tn * (tv + tp) });
			nenner = new Polynom(new double[] { 0, tn, tn * tp });
			return new TransferFunction(zaehler, nenner);
		default:
			throw new IllegalArgumentException("Internal Error");
		}

	}

}
