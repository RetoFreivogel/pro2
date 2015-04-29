package model;

public final class OppeltDim extends ReglerDim {

	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		
		return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.42 * Tu);
	}
}
