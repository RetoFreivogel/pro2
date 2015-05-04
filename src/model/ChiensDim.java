package model;

public final class ChiensDim extends AbstractDim {
	public static final int APERIODSTOER = 0, APERIODFUEHR = 1,
			ZWANZIGSTOER = 2, ZWANZIGFUEHR = 3;

	public int j;

	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();

		switch (topo) {
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
				return new Regler((0.35 / Ks) * (Tg / Tu), 1.2 * Tu);
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

	public ChiensDim(int j) {
		super();

		if (j < 0 || j > 3) {
			throw new IllegalArgumentException("j must be between 0 and 3");
		}

		this.j = j;

	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		if (j < 0 || j > 3) {
			throw new IllegalArgumentException("j must be between 0 and 3");
		}
		this.j = j;
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChiensDim\nj: ");
		builder.append(j);
		builder.append("\n");
		return builder.toString();
	}

}
