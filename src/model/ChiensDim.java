package model;

public final class ChiensDim extends ReglerDim {
	public static final int APERIODSTOER=1, APERIODFUEHR=2, ZWANZIGSTOER=3, ZWANZIGFUEHR=4;
	
	public int j;
	
	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		
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
			return null;
		}
	}

	public ChiensDim(int j) {
		super();
		
		if(j<1 || j > 4){
			throw new IllegalArgumentException("j must be between 1 and 4");
		}
		
		this.j = j;
		
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		if(j<1 || j > 4){
			throw new IllegalArgumentException("j must be between 1 and 4");
		}
		this.j = j;
		setChanged();
		notifyObservers();
	}

}
