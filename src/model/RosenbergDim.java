package model;

public final class RosenbergDim extends AbstractDim {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RosenbergDim\n");
		return builder.toString();
	}

	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		
		switch (topo) {
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

}
