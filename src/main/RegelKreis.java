package main;

public final class RegelKreis extends TranferFunction {
	private final Regler regler;
	private final RegelStrecke regelstrecke;
	private final ReglerDim dim;

	public RegelKreis(ReglerDim dim, RegelStrecke regelstrecke) {
		this.dim = dim.makeCopy();
		this.regelstrecke = regelstrecke;
		regler = this.dim.calc(regelstrecke);
	}

	public RegelKreis(Regler regler, RegelStrecke regelstrecke) {
		dim = new ManuellDim(regler);
		this.regelstrecke = regelstrecke;
		this.regler = dim.calc(regelstrecke);
	}

	public Regler getRegler() {
		return regler;
	}

	public ReglerDim getDim() {
		return dim.makeCopy();
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
