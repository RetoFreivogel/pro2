package model;

import java.io.Serializable;

import util.Copyable;

/**
 * Regelstrecke die durch die Eigenschaten Streckenbeiwert (Ks), Verzugszeit
 * (Tu) und Anstiegszeit(Tg) definiert ist.
 * 
 * @author Reto Freivogel
 *
 */
public final class RegelStrecke implements RegelGlied, Serializable, Copyable {
	private static final long serialVersionUID = 1L;
	private final double ks, tu, tg;

	/**
	 * Erzeugt eine neue Regelstrecke
	 * 
	 * @param ks
	 *            Ks der Strecke
	 * @param tu
	 *            Tu der Strecke
	 * @param tg
	 *            Tg der Strecke
	 */
	public RegelStrecke(double ks, double tu, double tg) {
		if (ks <= 0)
			throw new IllegalArgumentException("ks muss positiv sein");
		if (tu <= 0)
			throw new IllegalArgumentException("tu muss positiv sein");
		if (tg <= 0)
			throw new IllegalArgumentException("tg muss positiv sein");

		this.ks = ks;
		this.tu = tu;
		this.tg = tg;
	}

	/**
	 * Gibt Ks zurück
	 * @return Ks
	 */
	public double getKs() {
		return ks;
	}

	/**
	 * Gibt Tu zurück
	 * @return Tu
	 */
	public double getTu() {
		return tu;
	}

	/**
	 * Gibt Tg zurück
	 * @return Tg
	 */
	public double getTg() {
		return tg;
	}

	/**
	 * Erzeugt eine neue Regelstrecke bei der Ks auf einen neuen Wert gesetzt wurde.
	 * @param ks Das neue Ks
	 * @return Die erzeugte Regelstrecke
	 */
	public RegelStrecke setKs(double ks) {
		return new RegelStrecke(ks, tu, tg);
	}

	/**
	 * Erzeugt eine neue Regelstrecke bei der Tu auf einen neuen Wert gesetzt wurde.
	 * @param tu Das neue Tu
	 * @return Die erzeugte Regelstrecke
	 */
	public RegelStrecke setTu(double tu) {
		return new RegelStrecke(ks, tu, tg);
	}

	/**
	 * Erzeugt eine neue Regelstrecke bei der Tg auf einen neuen Wert gesetzt wurde.
	 * @param tg Das neue Tg
	 * @return Die erzeugte Regelstrecke
	 */
	public RegelStrecke setTg(double tg) {
		return new RegelStrecke(ks, tu, tg);
	}

	/**
	 * Gibt die Ordnung der Identifikation mit der Sani-Methode zurück
	 * @return Die Ordnung der Identifizierten Strecke
	 */
	public int getOrdnung() {
		return SaniApprox.getOrdnung(tu, tg);
	}

	/**
	 * Gibt die Streckenzeiten der Identifikation mit der Sani-Methode zurück.
	 * @return Die Streckenzeiten aufsteigend geordnet
	 */
	public double[] getTcoeffs() {
		return SaniApprox.calcSani(tu, tg);
	}

	@Override
	public TransferFunction getTranferFunction() {
		double[] Tcoeffs = SaniApprox.calcSani(tu, tg);

		double Tprodukt = 1.0;
		for (int i = 0; i < Tcoeffs.length; i++) {
			Tprodukt *= Tcoeffs[i];
		}

		for (int i = 0; i < Tcoeffs.length; i++) {
			Tcoeffs[i] = -1 / Tcoeffs[i];
		}
		Polynom nenner = Polynom.fromRoots(Tcoeffs);

		nenner = nenner.mul(Tprodukt);

		return new TransferFunction(new Polynom(new double[] { ks }), nenner);
	}

	@Override
	public Object copy() {
		return new RegelStrecke(ks, tu, tg);
	}
}
