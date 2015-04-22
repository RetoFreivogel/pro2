package main;

public final class ChiensDim extends ReglerDim {
	public static final int APERIODSTOER=1, APERIODFUEHR=2, ZWANZIGSTOER=3, ZWANZIGFUEHR=4;
	private int j;
	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs().getValue();
		double Tu = regelstrecke.getTu().getValue();
		double Tg = regelstrecke.getTg().getValue();
		
		switch (j) {
		case APERIODSTOER:
			return new Regler((0.95 / Ks) * (Tg / Tu), 2.4 * Tu, 0.42 * Tu);
		case APERIODFUEHR:
			return new Regler((0.6 / Ks) * (Tg / Tu), Tg, 0.5 * Tu);
		case ZWANZIGSTOER:
			return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.42 * Tu);
		case ZWANZIGFUEHR:
			return new Regler((0.95 / Ks) * (Tg / Tu), 1.35 * Tu, 0.47 * Tu);
		default:
			assert(false);
		}
	}

	public ChiensDim(int j) {
		super();
		this.j = j;
		if (int j = 1; j < 4; j++) {
			j = APERIODSTOER;
			j = APERIODFUEHR;
			j = ZWANZIGSTOER;
			j = ZWANZIGFUEHR;
		}
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	@Override
	public ReglerDim makeCopy() {
		return this;
	}
}
