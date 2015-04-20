package main;

public final class ZieglerDim extends ReglerDim {

	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs().getValue();
		double Tu = regelstrecke.getTu().getValue();
		double Tg = regelstrecke.getTg().getValue();
		
		return new Regler((0.9 / Ks) * (Tg / Tu), 2 * Tu, 0.5 * Tu); //Tg = T1, Tu = Tt
	}

	@Override
	public ReglerDim makeCopy() {
		return this;
	}
}
