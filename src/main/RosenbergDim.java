package main;

public final class RosenbergDim extends ReglerDim {

	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs().getValue();
		double Tu = regelstrecke.getTu().getValue();
		double Tg = regelstrecke.getTg().getValue();
		
		return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.44 * Tu);
	}

	@Override
	public ReglerDim makeCopy() {
		return this;
	}
}
