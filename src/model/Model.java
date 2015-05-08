package model;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Vector;

public class Model extends Observable implements Observer{
	private final Vector<RegelKreis> alleRegelkreise = new Vector<>(0, 1);
	private final Vector<AbstractDim> alleDim = new Vector<>(0, 1);
	private final RegelStrecke regelstrecke;

	public Model(Scanner sc) {
		regelstrecke = new RegelStrecke(sc);
		alleDim.add(AbstractDim.fromScanner(sc));
		alleRegelkreise.add(new RegelKreis(alleDim.get(0), regelstrecke));
		regelstrecke.addObserver(this);
		for(RegelKreis rk : alleRegelkreise){
			rk.addObserver(this);
		}
	}

	public Model() {
		regelstrecke = new RegelStrecke(new RegelStrecke(1.0, 1.71, 7.6));
		alleDim.add(new ZellwegerDim(Math.PI/4));
		alleDim.add(new OppeltDim());
		for(AbstractDim rd : alleDim){
			RegelKreis rk = new RegelKreis(rd, regelstrecke);
			alleRegelkreise.add(rk);
		}
		regelstrecke.addObserver(this);
		for(RegelKreis rk : alleRegelkreise){
			rk.addObserver(this);
		}
	}
	
	public Model(Model other){
		regelstrecke = new RegelStrecke(other.getRegelstrecke());
		for(AbstractDim ad : other.getAlleDim()){
			AbstractDim copy_ad = ad.makeCopy();
			alleDim.add(copy_ad);
			RegelKreis rk = new RegelKreis(copy_ad, regelstrecke);
			alleRegelkreise.add(rk);
		}
		regelstrecke.addObserver(this);
		for(RegelKreis rk : alleRegelkreise){
			rk.addObserver(this);
		}
	}
	
	public Vector<RegelKreis> getAlleRegelkreise() {
		return alleRegelkreise;
	}

	public Vector<AbstractDim> getAlleDim() {
		return alleDim;
	}

	public RegelStrecke getRegelstrecke() {
		return regelstrecke;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (RegelKreis rk : alleRegelkreise) {
			builder.append(rk).append('\n');
		}
		return builder.toString();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}
}
