package model;


public final class ZieglerDim extends AbstractDim {
	private static final long serialVersionUID = 1L;

	public ZieglerDim(ReglerTopologie topo){
		super(topo, "Ziegler");
	}
	
	@Override
	public String toString() {
		return "ZieglerDim\n";
	}

	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		
		
		switch (getTopo()) {
		case P:
			return new Regler((1 / Ks) * (Tg / Tu));
		case PI:
			return new Regler((0.9 / Ks) * (Tg / Tu), 3.3 * Tu);
		case PID:
			return new Regler((0.9 / Ks) * (Tg / Tu), 2 * Tu, 0.5 * Tu);
		default:
			throw new IllegalArgumentException();
		
		}
	}

	@Override
	public AbstractDim setTopo(ReglerTopologie topo) {
		return new ZieglerDim(topo);
	}
}
