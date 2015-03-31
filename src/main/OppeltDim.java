package main;

public class OppeltDim extends ReglerDim {

	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.42 * Tu);
	}
}
