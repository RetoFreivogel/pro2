package model;

public final class RosenbergDim extends AbstractDim {
	private static final long serialVersionUID = 1L;

	public RosenbergDim(ReglerTopologie topo){
		super(topo);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RosenbergDim\n");
		return builder.toString();
	}

	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		
		switch (getTopo()) {
		case P:
			return new Regler((1.0 / Ks) * (Tg / Tu));
		case PI:
			return new Regler((0.91/ Ks) * (Tg / Tu), 3.3 * Tu);
		case PID:
			return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.44 * Tu);
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public RosenbergDim setTopo(ReglerTopologie topo) {
		return new RosenbergDim(topo);
	}

	@Override
	public Dimensionierung getDimensionierung() {
		return Dimensionierung.ROSENBERG;
	}

	@Override
	public Object copy() {
		return new RosenbergDim(getTopo());
	}
}
