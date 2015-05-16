package model;


/**
 * 
 * @author Reto
 * Reglerdimensionierung nach Oppelt 
 */
public final class OppeltDim extends AbstractDim {
	private static final long serialVersionUID = 1L;

	public OppeltDim(ReglerTopologie topo){
		super(topo, "Oppelt");
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
			return new Regler((0.8/ Ks) * (Tg / Tu), 3 * Tu);
		case PID:
			return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.42 * Tu);
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OppeltDim\n");
		return builder.toString();
	}

	@Override
	public AbstractDim setTopo(ReglerTopologie topo) {
		return new OppeltDim(topo);
	}
}
