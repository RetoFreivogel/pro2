package model.dimensionierung;

import model.RegelStrecke;
import model.Regler;

public final class Dimensionierung extends AbstractDim{
	private static final long serialVersionUID = 1L;
	
	private final AbstractDim dim;
	
	private Dimensionierung(AbstractDim dim){
		super(dim.getTopo());
		this.dim = (AbstractDim) dim.copy();
	}
	
	public Dimensionierung(DimEnum typ, TopoEnum topo) {
		super(topo);
		switch(typ){
		case CHIENS:
			dim = new ChiensDim(ChiensEnum.APERIODFUEHR, topo);
			break;
		case MANUELL:
			dim = new ManuellDim(1, 1, 1, 0.1, topo);
			break;
		case OPPELT:
			dim = new OppeltDim(topo);
			break;
		case PHASENGANG:
			dim = new ZellwegerDim(45, topo);
			break;
		case ROSENBERG:
			dim = new RosenbergDim(topo);
			break;
		case ZIEGLER:
			dim = new ZieglerDim(topo);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Object copy() {
		return new Dimensionierung(dim);
	}

	@Override
	public DimEnum getTyp() {
		return dim.getTyp();
	}

	@Override
	public Dimensionierung setTopo(TopoEnum topo) {
		return new Dimensionierung(dim.setTopo(topo));
	}

	@Override
	public TopoEnum getTopo(){
		return dim.getTopo();
	}
	
	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		return dim.calc(regelstrecke);
	}

	public Dimensionierung setTyp(DimEnum typ, RegelStrecke strecke) {
		switch (typ) {
		case MANUELL:
			return new Dimensionierung(new ManuellDim(calc(strecke)));	
		case PHASENGANG:
			return new Dimensionierung(new ZellwegerDim(45, getTopo()));
		case ZIEGLER:
			return new Dimensionierung(new ZieglerDim(getTopo()));
		case CHIENS:
			return new Dimensionierung(new ChiensDim(ChiensEnum.APERIODSTOER, getTopo()));
		case OPPELT:
			return new Dimensionierung(new OppeltDim(getTopo()));
		case ROSENBERG:
			return new Dimensionierung(new RosenbergDim(getTopo()));
		default:
			throw new RuntimeException("Internal Error");
		}
	}

	public Dimensionierung setVerhalten(ChiensEnum verhalten) {
		return new Dimensionierung(((ChiensDim)dim).setVerhalten(verhalten));
	}
	
	public ChiensEnum getVerhalten() {
		return ((ChiensDim)dim).getVerhalten();
	}
	
	public Dimensionierung setPhasenrand(double phasenrand) {
		return new Dimensionierung(((ZellwegerDim)dim).setPhasenrand(phasenrand));
	}

	public double getPhasenrand() {
		return ((ZellwegerDim)dim).getPhasenrand();
	}

	public double getKr() {
		return ((ManuellDim)dim).getKr();
	}
	
	public Dimensionierung setKr(double kr) {
		return new Dimensionierung(((ManuellDim)dim).setKr(kr));
	}
	
	public double getTn() {
		return ((ManuellDim)dim).getTn();
	}
	
	public Dimensionierung setTn(double tn) {
		return new Dimensionierung(((ManuellDim)dim).setTn(tn));
	}
	
	public double getTv() {
		return ((ManuellDim)dim).getTv();
	}
	
	public Dimensionierung setTv(double tv) {
		return new Dimensionierung(((ManuellDim)dim).setTv(tv));
	}

	public double getTp() {
		return ((ManuellDim)dim).getTp();
	}

	public Dimensionierung setTp(double tp) {
		return new Dimensionierung(((ManuellDim)dim).setTp(tp));
	}
}
