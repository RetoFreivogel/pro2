package model;

public class ManuellDim extends AbstractDim {
	private static final long serialVersionUID = 1L;
	
	private final double kr, tn, tv, tp;
		
	public ManuellDim(double kr, double tn, double tv, double tp, ReglerTopologie topo) {
		super(topo);
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
		this.tp = tp;
	}

	public ManuellDim(Regler regler) {
		super(regler.getTopo());
		kr = regler.getKr();
		tn = regler.getTn();
		tv = regler.getTv();
		tp = regler.getTp();
	}

	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		switch(getTopo()){
		case P:
			return new Regler(kr);
		case PI:
			return new Regler(kr, tn);
		case PID:
			return new Regler(kr, tn, tv, tp);
		default:
			throw new IllegalArgumentException("Internal Error");
		}
	}

	public double getKr() {
		return kr;
	}
	
	public ManuellDim setKr(double kr) {
		return new ManuellDim(kr, tn, tv, tp, getTopo());
	}
	
	public double getTn() {
		return tn;
	}
	
	public ManuellDim setTn(double tn) {
		return new ManuellDim(kr, tn, tv, tp, getTopo());
	}
	
	public double getTv() {
		return tv;
	}
	
	public ManuellDim setTv(double tv) {
		return new ManuellDim(kr, tn, tv, tp, getTopo());
	}

	public double getTp() {
		return tp;
	}

	public ManuellDim setTp(double tp) {
		return new ManuellDim(kr, tn, tv, tp, getTopo());
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ManuellDim\nkr: ");
		builder.append(kr);
		builder.append("\ntn: ");
		builder.append(tn);
		builder.append("\ntv: ");
		builder.append(tv);
		builder.append("\ntp: ");
		builder.append(tp);
		builder.append("\n");
		return builder.toString();
	}

	@Override
	public AbstractDim setTopo(ReglerTopologie topo) {
		return new ManuellDim(kr, tn, tv, tp, topo);
	}
}
