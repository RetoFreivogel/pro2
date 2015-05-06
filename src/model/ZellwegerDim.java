package model;

import java.util.Scanner;

public class ZellwegerDim extends AbstractDim {
	private double phasenrand;

	public double searchPhase(TransferFunction tf, double phase){
		double low_freq = 0, high_freq = 1;
		
		while(tf.phaseAt(high_freq) >= phase){
			low_freq = high_freq;
			high_freq *= 2;
		}
		
		double middle_freq = (low_freq + high_freq) / 2;
		while (Math.abs(tf.phaseAt(middle_freq)-phase) >= 0.0001) {
			middle_freq = (low_freq + high_freq) / 2;
			if (tf.phaseAt(middle_freq) < phase) {
				high_freq = middle_freq;
			}else {
				low_freq = middle_freq;
			}
		}
		return middle_freq;
	}
	
	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		TransferFunction tf = regelstrecke.getTranferFunction();
		double[] T = SaniApprox.calcSani(regelstrecke.getTu(),
				regelstrecke.getTg());
		
		// gs in array [gs,w]= get_gs(T,Ks) ist komplex
		double beta, beta1, beta2;
		double subst;
		double tnk, tvk, tppid;
		double krpid, tvpid, tnpid, krk;
		double gspid, grpid, gopid;
		
		double wpid = searchPhase(tf, Math.PI * 3 / 4);

		double ablwpid = 0;
		for (int k = 0; k < T.length; k++) {
			ablwpid = ablwpid - T[k]
					/ (1 + Math.pow(wpid, 2) * Math.pow(T[k], 2));
		}

		subst = -0.5 - ablwpid * wpid;
		beta1 = 1 / subst + Math.sqrt(1 / Math.pow(subst, 2) - 1);
		beta2 = 1 / subst - Math.sqrt(1 / Math.pow(subst, 2) - 1);

		if (subst < -1 || subst > 1 || Math.abs(beta1) > 1 || beta1 < 0) {
			beta1 = 1;
		}
		beta = beta1;
		if (subst >= -1 && subst < 1 && Math.abs(beta2) < 1 && beta2 > 0) {
			beta = beta2;
		}

		tnk = 1 / (beta * wpid);
		tvk = beta / wpid;
		tppid = tvk / 10;

		double wdpid = searchPhase(tf, 180-phasenrand);

		gspid = Math.abs(tf.phaseAt(wdpid)); // gs komplex

		grpid = Math.sqrt(1 + Math.pow(wdpid * tnk, 2))
				* Math.sqrt(1 + Math.pow(wdpid * tvk, 2)) / (wdpid * tnk);
		gopid = gspid * grpid;
		krk = 1 / gopid;

		tnpid = tnk + tvk - tppid;
		tvpid = (tnk * tvk) / (tnk + tvk - tppid) - tppid;
		krpid = krk * (1 + tvk / tnk - tppid / tnk);

		return new Regler(tnpid, tvpid, krpid, tppid);
	}

	public ZellwegerDim(double phasenrand) {
		super();
		this.phasenrand = phasenrand;
	}

	public ZellwegerDim(Scanner sc) {
		super();
		// sc.skip("ph");
	}

	public double getPhasenrand() {
		return phasenrand;
	}

	public void setPhasenrand(double phasenrand) {
		this.phasenrand = phasenrand;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZellwegerDim\nphasenrand: ");
		builder.append(phasenrand);
		builder.append("\n");
		return builder.toString();
	}

	@Override
	public AbstractDim makeCopy() {
		return new ZellwegerDim(phasenrand);
	}

}
