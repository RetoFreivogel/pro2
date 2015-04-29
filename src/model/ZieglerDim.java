package model;


public final class ZieglerDim extends ReglerDim {

	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		
		return new Regler((0.9 / Ks) * (Tg / Tu), 2 * Tu, 0.5 * Tu); //Tg = T1, Tu = Tt
	}

}
