package model.dimensionierung;

import model.RegelStrecke;
import model.Regler;


final class ZieglerDim extends AbstractDim {
	private static final long serialVersionUID = 1L;

	ZieglerDim(TopoEnum topo){
		super(topo);
	}
	
	@Override
	Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();
		
		
		switch (getTopo()) {
		case P:
			return new Regler((1 / Ks) * (Tg / Tu));
		case PI:
			return new Regler((0.9 / Ks) * (Tg / Tu), 3.3 * Tu);
		case PID:
			return new Regler((0.9 / Ks) * (Tg / Tu), 2 * Tu, 0.5 * Tu);
		default:
			throw new IllegalArgumentException();
		
		}
	}

	@Override
	ZieglerDim setTopo(TopoEnum topo) {
		return new ZieglerDim(topo);
	}

	@Override
	DimEnum getTyp() {
		return DimEnum.ZIEGLER;
	}

	@Override
	public Object copy() {
		return new ZieglerDim(getTopo());
	}
}
