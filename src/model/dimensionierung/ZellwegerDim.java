package model.dimensionierung;

import model.RegelStrecke;
import model.Regler;
import model.SaniApprox;
import model.TransferFunction;

class ZellwegerDim extends AbstractDim {
	private static final long serialVersionUID = 1L;
	private final double phasenrand;

	private double searchPhase(TransferFunction tf, double phase) {
		double low_freq = 0, high_freq = 1;

		while (tf.phaseAt(high_freq) >= phase) {
			low_freq = high_freq;
			high_freq *= 2;
		}

		double middle_freq = (low_freq + high_freq) / 2;
		while (Math.abs(tf.phaseAt(middle_freq) - phase) >= 0.00001) {
			middle_freq = (low_freq + high_freq) / 2;
			if (tf.phaseAt(middle_freq) < phase) {
				high_freq = middle_freq;
			} else {
				low_freq = middle_freq;
			}
		}
		return middle_freq;
	}

	private Regler calcPID(RegelStrecke regelstrecke) {
		TransferFunction tf_strecke = regelstrecke.getTranferFunction();
		double[] T = SaniApprox.calcSani(regelstrecke.getTu(),
				regelstrecke.getTg());

		double wpid = searchPhase(tf_strecke, -Math.PI * 3 / 4);

		// Berechnung von Beta
		double ablwpid = 0;
		for (int k = 0; k < T.length; k++) {
			ablwpid = ablwpid - T[k]
					/ (1 + Math.pow(wpid, 2) * Math.pow(T[k], 2));
		}
		double subst = -0.5 - ablwpid * wpid;
		double beta1 = 1 / subst + Math.sqrt(1 / Math.pow(subst, 2) - 1);
		double beta2 = 1 / subst - Math.sqrt(1 / Math.pow(subst, 2) - 1);
		if (subst < -1 || subst > 1 || Math.abs(beta1) > 1 || beta1 < 0) {
			beta1 = 1;
		}
		double beta = beta1;
		if (subst >= -1 && subst < 1 && Math.abs(beta2) < 1 && beta2 > 0) {
			beta = beta2;
		}

		// Berechnung von Tn, Tv und Tp
		double tnk = 1 / (beta * wpid);
		double tvk = beta / wpid;
		double tppid = tvk / 10;
		double tnpid = tnk + tvk - tppid;
		double tvpid = (tnk * tvk) / (tnk + tvk - tppid) - tppid;

		// Berechnung Kr
		Regler tempRegler = new Regler(1, tnpid, tvpid, tppid);
		TransferFunction tf_regler = tempRegler.getTranferFunction();
		TransferFunction tf_offener_kreis = tf_strecke.conv(tf_regler);

		double wdpid = searchPhase(tf_offener_kreis, phasenrand*Math.PI / 180 - Math.PI);
		
		double gspid = Math.abs(tf_strecke.amplitudeAt(wdpid)); 
	
		double grpid = Math.sqrt(1 + Math.pow(wdpid * tnk, 2))
				* Math.sqrt(1 + Math.pow(wdpid * tvk, 2)) 
				/ (wdpid * tnk);
				
		double gopid = gspid * grpid;
		
		//double gopid = tf_offener_kreis.amplitudeAt(wdpid);
		double krk = 1 / gopid;

		double krpid = krk * (1 + tvk / tnk - tppid / tnk);

		return new Regler(krpid, tnpid, tvpid, tppid);
	}
	
	private Regler calcPI(RegelStrecke regelstrecke) {
		TransferFunction tf_strecke = regelstrecke.getTranferFunction();
		
		double wpi = searchPhase(tf_strecke, -Math.PI/2);

		// Berechnung von Tn
		double tnpi = 1 / wpi;

		// Berechnung Kr
		Regler tempRegler = new Regler(1, tnpi);
		TransferFunction tf_regler = tempRegler.getTranferFunction();
		TransferFunction tf_offener_kreis = tf_strecke.conv(tf_regler);

		double wdpi = searchPhase(tf_offener_kreis, phasenrand*Math.PI / 180 - Math.PI);		
		double gopi = tf_offener_kreis.amplitudeAt(wdpi);
		double krpi = 1 / gopi;

		return new Regler(krpi, tnpi);

	}

	
	@Override
	Regler calc(RegelStrecke regelstrecke) {
		switch (getTopo()) {
		case PID:
			return calcPID(regelstrecke);
		case PI:
			return calcPI(regelstrecke);
		default:
			throw new IllegalArgumentException("P regler werden nicht von der Phasengangmethode unterstützt.");
		}

	}

	ZellwegerDim(double phasenrand, TopoEnum topo) {
		super(topo);
		if (phasenrand < 0 || phasenrand > 90) {
			throw new IllegalArgumentException(
					"Der Phasenrand muss zwischen 0 und 90 Grad liegen");
		}
		this.phasenrand = phasenrand;
	}

	double getPhasenrand() {
		return phasenrand;
	}

	ZellwegerDim setPhasenrand(double phasenrand) {
		return new ZellwegerDim(phasenrand, getTopo());
	}

	@Override
	AbstractDim setTopo(TopoEnum topo) {
		return new ZellwegerDim(phasenrand, topo);
	}

	@Override
	DimEnum getTyp() {
		return DimEnum.ZELLWEGER;
	}

	@Override
	public Object copy() {		
		return new ZellwegerDim(phasenrand, getTopo());
	}
}
