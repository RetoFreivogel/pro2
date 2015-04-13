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
		double[] polyStrecke = regelstrecke.getPolyZaehler();
		double[] polyRegler = dim.calc(regelstrecke).getPolyZaehler();
		return convPoly(polyStrecke, polyRegler);
	}

	@Override
	protected double[] getPolyNenner() {
		double[] zaehler = getPolyZaehler();
		double[] polyStrecke = regelstrecke.getPolyNenner();
		double[] polyRegler = dim.calc(regelstrecke).getPolyNenner();
		double[] nenner = convPoly(polyStrecke, polyRegler);
		for (int i = 0; i < zaehler.length; i++) {
			nenner[i+(nenner.length - zaehler.length)] += zaehler[i];
		}		
		return nenner;
	}

	private double[] convPoly(double[] P1, double[] P2) {
		double[] P = new double[P1.length + P2.length - 1];

		for (int i = 0; i < P1.length; i++) {
			for (int j = 0; j < P2.length; j++) {
				P[i + j] += P1[i] * P2[j];
			}
		}
		return P;
	}
}
