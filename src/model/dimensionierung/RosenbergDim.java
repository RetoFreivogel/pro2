package model.dimensionierung;

import model.RegelStrecke;
import model.Regler;

/**
 * Reglerdimensionierung nach Rosenberg
 * @author Dennis Stampfli, Reto Freivogel
 *
 */
final class RosenbergDim extends AbstractDim {
	private static final long serialVersionUID = 1L;

	RosenbergDim(TopoEnum topo){
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
			return new Regler((0.91/ Ks) * (Tg / Tu), 3.3 * Tu);
		case PID:
			return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.44 * Tu);
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	RosenbergDim setTopo(TopoEnum topo) {
		return new RosenbergDim(topo);
	}

	@Override
	DimEnum getTyp() {
		return DimEnum.ROSENBERG;
	}

	@Override
	public Object copy() {
		return new RosenbergDim(getTopo());
	}
}
