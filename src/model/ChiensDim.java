package model;

public final class ChiensDim extends AbstractDim{
	private static final long serialVersionUID = 1L;

	public static final int APERIODSTOER = 0, APERIODFUEHR = 1,
			ZWANZIGSTOER = 2, ZWANZIGFUEHR = 3;

	private final int j;

	public ChiensDim(int j, ReglerTopologie topo) {
		super(topo);
		if (j < 0 || j > 3) {
			throw new IllegalArgumentException("j must be between 0 and 3");
		}
		this.j = j;
	}
	
	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();

		switch (getTopo()) {
		case P:
			switch (j) {
			case APERIODSTOER:
				return new Regler((0.3 / Ks) * (Tg / Tu));
			case APERIODFUEHR:
				return new Regler((0.3 / Ks) * (Tg / Tu));
			case ZWANZIGSTOER:
				return new Regler((0.7 / Ks) * (Tg / Tu));
			case ZWANZIGFUEHR:
				return new Regler((0.7 / Ks) * (Tg / Tu));
			default:
				assert (false);
				return null;
			}
		case PI:
			switch (j) {
			case APERIODSTOER:
				return new Regler((0.6 / Ks) * (Tg / Tu), 4 * Tu);
			case APERIODFUEHR:
				return new Regler((0.35 / Ks) * (Tg / Tu), 1.2 * Tg);
			case ZWANZIGSTOER:
				return new Regler((0.7 / Ks) * (Tg / Tu), 2.3 * Tu);
			case ZWANZIGFUEHR:
				return new Regler((0.6 / Ks) * (Tg / Tu), 1.35 * Tg);
			default:
				assert (false);
				return null;
			}
		case PID:
			switch (j) {
			case APERIODSTOER:
				return new Regler((0.95 / Ks) * (Tg / Tu), 2.4 * Tu, 0.42 * Tu);
			case APERIODFUEHR:
				return new Regler((0.6 / Ks) * (Tg / Tu), Tg, 0.5 * Tu);
			case ZWANZIGSTOER:
				return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.42 * Tu);
			case ZWANZIGFUEHR:
				return new Regler((0.95 / Ks) * (Tg / Tu), 1.35 * Tg, 0.47 * Tu);
			default:
				assert (false);
				return null;
			}
		default:
			throw new IllegalArgumentException();
		}
	}

	public int getJ() {
		return j;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChiensDim\nj: ");
		builder.append(j);
		builder.append("\n");
		return builder.toString();
	}

	public ChiensDim setJ(int j) {
		return new ChiensDim(j, getTopo());
	}

	@Override
	public ChiensDim setTopo(ReglerTopologie topo) {
		return new ChiensDim(j, topo);
	}

	@Override
	public Dimensionierung getDimensionierung() {
		return Dimensionierung.CHIENS;
	}
	
	@Override
	public ChiensDim copy(){
		return new ChiensDim(j, getTopo());
	}
}
