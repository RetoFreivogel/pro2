package main;

public class OppeltDim implements ReglerDim {

	public Regler calc(ImmutableRegelStrecke regelstrecke ) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.42 * Tu);
	}
}
