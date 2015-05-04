package model;

public class ZellwegerDim extends AbstractDim {
	private double phasenrand;
	
	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		TransferFunction tf = regelstrecke.getTranferFunction();
		tf.phaseAt(0);
		
		return new Regler(1, 1, 1);
	}

	public ZellwegerDim(double phasenrand) {
		super();
		this.phasenrand = phasenrand;
	}

	public double getPhasenrand() {
		return phasenrand;
	}

	public void setPhasenrand(double phasenrand) {
		this.phasenrand = phasenrand;
	}

}
