package model;

import java.io.Serializable;

import model.dimensionierung.Dimensionierung;


/**
 * Abstraktion eines Regelkreises der aus Regler und Regelstrecke besteht.
 * Weiter kann jedem Regelkreis ein Name zugewiesen werden
 * @author Reto Freivogel
 *
 */
public final class RegelKreis implements RegelGlied, Serializable {
	private static final long serialVersionUID = 1L;
	
	private final RegelStrecke regelstrecke;
	private final Dimensionierung dim;
	private final String name;

	/**
	 * Erzeugt einen neuen Regelkreis 
	 * @param dim Die Dimensionierungsmethode die den Regler erzeugt.
	 * @param regelstrecke Die Regelstrecke des Regelkreises
	 * @param name Der zugewiesene Name des Kreises
	 */
	public RegelKreis(Dimensionierung dim, RegelStrecke regelstrecke, String name) {
		this.dim = dim;
		this.regelstrecke = regelstrecke;
		this.name = name + ": " + dim.getTyp().toString();
	}

	/**
	 * Gibt den erzeugten Regler zurück
	 * @return Ein neuer Regler der durch die Dimensionierung erzeugt wurde
	 */
	public Regler getRegler() {
		return dim.calc(regelstrecke);
	}

	/**
	 * Gibt die Dimensionierungsmethode zurück
	 * @return Die Dimensionierungsmethode
	 */
	public Dimensionierung getDim() {
		return dim;
	}

	/**
	 * Gibt die Regelstrecke zurück
	 * @return Die Regelstrecke des Kreises
	 */
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

	/**
	 * Gibt den Namen zurück
	 */
	@Override
	public String toString() {
		return name;
	}
}
