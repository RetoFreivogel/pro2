package model;

import model.dimensionierung.TopoEnum;

/**
 * Ein P, PI oder PID-T Regler
 * @author Reto Freivogel
 *
 */
public final class Regler implements RegelGlied {
	private final TopoEnum topo;
	private final double kr, tn, tv, tp;

	/**
	 * Erzeugt einen neuen P Regler
	 * @param kr Die Reglerverstärkung des Reglers
	 */
	public Regler(double kr) {
		if(kr <= 0){
			throw new IllegalArgumentException("kr muss positiv sein");
		}
		this.topo = TopoEnum.P;
		this.kr = kr;
		this.tn = 1.0;
		this.tv = 1.0;
		this.tp = 0.1;
	}
	
	/**
	 * Erzeugt einen neuen PI Regler
	 * @param kr Die Reglerverstärkung des Reglers
	 * @param tn Die Nachstellzeit des Reglers
	 */
	public Regler(double kr, double tn) {
		if(kr <= 0){
			throw new IllegalArgumentException("kr muss positiv sein");
		}
		if(tn <= 0){
			throw new IllegalArgumentException("tn muss positiv sein");
		}

		this.topo = TopoEnum.PI;
		this.kr = kr;
		this.tn = tn;
		this.tv = 1.0;
		this.tp = 0.1;
	}
	
	/**
	 * Erzeugt einen neuen PID-T Regler bei der tp = tv / 100 ist.
	 * @param kr Die Reglerverstärkung des Reglers
	 * @param tn Die Nachstellzeit des Reglers
	 * @param tv Die Vorhaltezeit des Reglers
	 */
	public Regler(double kr, double tn, double tv) {
		if(kr <= 0){
			throw new IllegalArgumentException("kr muss positiv sein");
		}
		if(tn <= 0){
			throw new IllegalArgumentException("tn muss positiv sein");
		}
		if(tv <= 0){
			throw new IllegalArgumentException("tv muss positiv sein");
		}
		
		this.topo = TopoEnum.PID;
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
		this.tp = tv / 100;
	}

	/**
	 * Erzeugt einen neuen PID-T Regler.
	 * @param kr Die Reglerverstärkung des Reglers
	 * @param tn Die Nachstellzeit des Reglers
	 * @param tv Die Vorhaltezeit des Reglers
	 * @param tp Die parasitäre Zeitkonstante des Reglers
	 */
	public Regler(double kr, double tn, double tv, double tp) {
		if(kr <= 0){
			throw new IllegalArgumentException("kr muss positiv sein");
		}
		if(tn <= 0){
			throw new IllegalArgumentException("tn muss positiv sein");
		}
		if(tv <= 0){
			throw new IllegalArgumentException("tv muss positiv sein");
		}
		if(tp <= 0){
			throw new IllegalArgumentException("tp muss positiv sein");
		}
		if(tv < tp){
			throw new IllegalArgumentException("tv darf nicht kleiner als tp sein");
		}
		this.topo = TopoEnum.PID;
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
		this.tp = tp;
	}

	/**
	 * Gibt die Topologie des Reglers zurück
	 * @return Die Topologie
	 */
	public TopoEnum getTopo() {
		return topo;
	}

	/**
	 * Gibt Kr zurück
	 * @return Kr
	 */
	public double getKr() {
		return kr;
	}

	/**
	 * Gibt Tn zurück
	 * @return Tn
	 */
	public double getTn() {
		return tn;
	}

	/**
	 * Gibt Tv zurück
	 * @return Tv
	 */
	public double getTv() {
		return tv;
	}

	/**
	 * Gibt Tp zurück
	 * @return Tp
	 */
	public double getTp() {
		return tp;
	}

	@Override
	public TransferFunction getTranferFunction() {
		Polynom zaehler;
		Polynom nenner;
		
		switch(topo){
		case P:
			zaehler = new Polynom(new double[] {kr});
			nenner = new Polynom(new double[] {1});
			return new TransferFunction(zaehler, nenner);
		case PI:
			zaehler = new Polynom(new double[] { kr, kr * (tn)});
			nenner = new Polynom(new double[] { 0, tn});
			return new TransferFunction(zaehler, nenner);
		case PID:
			zaehler = new Polynom(new double[] { kr, kr * (tn + tp),
					kr * tn * (tv + tp) });
			nenner = new Polynom(new double[] { 0, tn, tn * tp });
			return new TransferFunction(zaehler, nenner);
		default:
			throw new IllegalArgumentException("Internal Error");
		}
	}
}
