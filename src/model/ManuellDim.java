package model;

import java.util.Scanner;

public class ManuellDim extends AbstractDim {
	private double kr, tn, tv, tp;
	
	public ManuellDim(Scanner sc) {
		sc.skip("kr: ");
		kr = sc.nextDouble();
		sc.nextLine();
		sc.skip("tn: ");
		tn = sc.nextDouble();
		sc.nextLine();
		sc.skip("tv: ");
		tv = sc.nextDouble();
		sc.nextLine();
		sc.skip("tp: ");
		tp = sc.nextDouble();

	}

	public ManuellDim(double kr, double tn, double tv) {
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
		this.tp = tv / 100;
	}
	
	public ManuellDim(double kr, double tn, double tv, double tp) {
		this.kr = kr;
		this.tn = tn;
		this.tv = tv;
		this.tp = tp;
	}

	public ManuellDim(Regler regler) {
		kr = regler.getKr();
		tn = regler.getTn();
		tv = regler.getTv();
		tp = regler.getTp();
	}

	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		switch(topo){
		case P:
			return new Regler(kr);
		case PI:
			return new Regler(kr, tn);
		case PID:
			return new Regler(kr, tn, tv, tp);
		default:
			throw new IllegalArgumentException("Internal Error");
		}
	}

	public double getKr() {
		return kr;
	}
	
	public void setKr(double kr) {
		this.kr = kr;
		setChanged();
		notifyObservers();
	}
	
	public double getTn() {
		return tn;
	}
	
	public void setTn(double tn) {
		this.tn = tn;
		setChanged();
		notifyObservers();
	}
	
	public double getTv() {
		return tv;
	}
	
	public void setTv(double tv) {
		this.tv = tv;
		setChanged();
		notifyObservers();
	}

	public double getTp() {
		return tp;
	}

	public void setTp(double tp) {
		this.tp = tp;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(kr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tn);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tv);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManuellDim other = (ManuellDim) obj;
		if (Double.doubleToLongBits(kr) != Double.doubleToLongBits(other.kr))
			return false;
		if (Double.doubleToLongBits(tn) != Double.doubleToLongBits(other.tn))
			return false;
		if (Double.doubleToLongBits(tv) != Double.doubleToLongBits(other.tv))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ManuellDim\nkr: ");
		builder.append(kr);
		builder.append("\ntn: ");
		builder.append(tn);
		builder.append("\ntv: ");
		builder.append(tv);
		builder.append("\ntp: ");
		builder.append(tp);
		builder.append("\n");
		return builder.toString();
	}

	@Override
	public AbstractDim makeCopy() {
		return new ManuellDim(kr, tn, tv, tp);
	}
}
