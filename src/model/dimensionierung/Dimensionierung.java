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
		case ZELLWEGER:
			dim = new ZellwegerDim(45, topo);
			break;
		case ITERATIV:
			dim = new IterativDim(23, topo);
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
		if(typ == DimEnum.MANUELL){
			return new Dimensionierung(new ManuellDim(calc(strecke)));				
		}
		
		return new Dimensionierung(typ, getTopo());	
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

	public Dimensionierung setUeberschwingen(double ueberschwingen){
		return new Dimensionierung(((IterativDim) dim).setUeberschwingen(ueberschwingen));
	}
	
	public double getUeberschwingen(){
		return ((IterativDim)dim).getUeberschwingen();
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
