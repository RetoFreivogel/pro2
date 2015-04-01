package main;

public final class RegelKreis extends TranferFunction{
	private final Regler regler;
	private final RegelStrecke regelstrecke;
	private final ReglerDim dim;

	public RegelKreis(ReglerDim dim, RegelStrecke regelstrecke) {
		this.dim = dim.makeCopy();
		this.regelstrecke = regelstrecke;
		this.regler = this.dim.calc(regelstrecke);
	}

	public RegelKreis(Regler regler, RegelStrecke regelstrecke) {
		this.dim = new ManuellDim(regler);
		this.regelstrecke = regelstrecke;
		this.regler = this.dim.calc(regelstrecke);
	}
	
	public RegelKreis setKr(double kr){
		return setRegler(this.regler.setKr(kr));
	}

	public RegelKreis setRegler(Regler regler){
		return new RegelKreis(regler, this.regelstrecke);
	}
	
	public Regler getRegler() {
		return this.regler;
	}

	public ReglerDim getDim() {
		return this.dim.makeCopy();
	}

	public RegelStrecke getRegelstrecke() {
		return this.regelstrecke;
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
