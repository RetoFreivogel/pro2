package model;

public final class RosenbergDim extends AbstractDim {

	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		
		return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.44 * Tu);
	}

}
