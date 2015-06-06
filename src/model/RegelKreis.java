package model;

import java.io.Serializable;

import model.dimensionierung.Dimensionierung;


public final class RegelKreis implements RegelGlied, Serializable {
	private static final long serialVersionUID = 1L;
	
	private final RegelStrecke regelstrecke;
	private final Dimensionierung dim;
	private final String name;

	public RegelKreis(Dimensionierung dim, RegelStrecke regelstrecke, String name) {
		this.dim = dim;
		this.regelstrecke = regelstrecke;
		this.name = name + ": " + dim.getTyp().toString();
	}

	public Regler getRegler() {
		return dim.calc(regelstrecke);
	}

	public Dimensionierung getDim() {
		return dim;
	}

	public RegelStrecke getRegelstrecke() {
		return regelstrecke;
	}

	@Override
	public TransferFunction getTranferFunction() {
		TransferFunction tf_s = regelstrecke.getTranferFunction();
		TransferFunction tf_r = getRegler().getTranferFunction();

		TransferFunction tf_k = tf_s.conv(tf_r);
		tf_k = tf_k.feedback_loop();

		return tf_k;
	}

	@Override
	public String toString() {
		return name;
	}
}
