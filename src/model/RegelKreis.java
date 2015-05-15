package model;

import java.io.Serializable;

public final class RegelKreis implements RegelGlied, Serializable {
	private static final long serialVersionUID = 1L;
	
	private final RegelStrecke regelstrecke;
	private final AbstractDim dim;

	public RegelKreis(AbstractDim dim, RegelStrecke regelstrecke) {
		this.dim = dim;
		this.regelstrecke = regelstrecke;
	}

	public Regler getRegler() {
		return dim.calc(regelstrecke);
	}

	public AbstractDim getDim() {
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
		StringBuilder builder = new StringBuilder();
		builder.append(regelstrecke);
		builder.append(dim);
		builder.append('\n');
		return builder.toString();
	}
}
