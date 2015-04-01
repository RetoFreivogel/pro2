package main;

public class RegelKreis extends TranferFunction {
	private RegelStrecke regelstrecke;
	private ReglerDim dim;

	public RegelKreis(ReglerDim dim, RegelStrecke regelstrecke) {
		this.dim = dim;
		this.regelstrecke = regelstrecke;
	}

	public RegelKreis(Regler regler, RegelStrecke regelstrecke) {
		dim = new ManuellDim(regler);
		this.regelstrecke = regelstrecke;
	}

	public Regler getRegler() {
		return dim.calc(regelstrecke);
	}

	public ReglerDim getDim() {
		return dim;
	}

	public RegelStrecke getRegelstrecke() {
		return regelstrecke;
	}

	@Override
	protected double[] getPolyZaehler() {
		throw new UnsupportedOperationException("Not Implemented");
	}

	@Override
	protected double[] getPolyNenner() {
		throw new UnsupportedOperationException("Not Implemented");
	}
}
