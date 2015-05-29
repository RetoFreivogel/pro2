package model.dimensionierung;

import model.RegelStrecke;
import model.Regler;


/**
 * 
 * @author Reto
 * Reglerdimensionierung nach Oppelt 
 */
final class OppeltDim extends AbstractDim {
	private static final long serialVersionUID = 1L;

	OppeltDim(TopoEnum topo){
		super(topo);
	}
	
	@Override
	Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();

		switch (getTopo()) {
		case P:
			return new Regler((1.0 / Ks) * (Tg / Tu));
		case PI:
			return new Regler((0.8/ Ks) * (Tg / Tu), 3 * Tu);
		case PID:
			return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.42 * Tu);
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	OppeltDim setTopo(TopoEnum topo) {
		return new OppeltDim(topo);
	}

	@Override
	DimEnum getTyp() {
		return DimEnum.OPPELT;
	}

	@Override
	public Object copy() {
		return new OppeltDim(getTopo());
	}
}
