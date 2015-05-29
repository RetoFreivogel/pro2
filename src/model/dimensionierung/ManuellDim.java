package model.dimensionierung;

import model.RegelStrecke;
import model.Regler;

class ManuellDim extends AbstractDim {
	private static final long serialVersionUID = 1L;
	
	private final double kr, tn, tv, tp;
		
	ManuellDim(double kr, double tn, double tv, double tp, TopoEnum topo) {		
		super(topo);
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
		this.tp = tp;
	}
	
	ManuellDim(Regler regler) {
		super(regler.getTopo());
		kr = regler.getKr();
		tn = regler.getTn();
		tv = regler.getTv();
		tp = regler.getTp();
	}

	@Override
	Regler calc(RegelStrecke regelstrecke) {
		switch(getTopo()){
		case P:
			return new Regler(kr);
		case PI:
			return new Regler(kr, tn);
		case PID:
			return new Regler(kr, tn, tv, tp);
		default:
			throw new IllegalArgumentException("Internal Error");
		}
	}

	double getKr() {
		return kr;
	}
	
	ManuellDim setKr(double kr) {
		return new ManuellDim(kr, tn, tv, tp, getTopo());
	}
	
	double getTn() {
		return tn;
	}
	
	ManuellDim setTn(double tn) {
		return new ManuellDim(kr, tn, tv, tp, getTopo());
	}
	
	double getTv() {
		return tv;
	}
	
	ManuellDim setTv(double tv) {
		return new ManuellDim(kr, tn, tv, tp, getTopo());
	}

	double getTp() {
		return tp;
	}

	ManuellDim setTp(double tp) {
		return new ManuellDim(kr, tn, tv, tp, getTopo());
	}
	
	@Override
	AbstractDim setTopo(TopoEnum topo) {
		return new ManuellDim(kr, tn, tv, tp, topo);
	}

	@Override
	DimEnum getTyp() {
		return DimEnum.MANUELL;
	}

	@Override
	public Object copy() {
		return new ManuellDim(kr, tn, tv, tp, getTopo());
	}
}
