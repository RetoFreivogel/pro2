package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Observable;
import java.util.Vector;

import util.Copyable;
import model.dimensionierung.Dimensionierung;

public class Model extends Observable implements Serializable, Copyable{
	private static final long serialVersionUID = 1L;
	
	private final Vector<Dimensionierung> alleDim = new Vector<>(0, 1);
	private RegelStrecke regelstrecke;
		
	public Model(RegelStrecke regelstrecke, Dimensionierung[] dims){
		if(dims.length == 0){
			throw new IllegalArgumentException();
		}
		this.regelstrecke = (RegelStrecke) regelstrecke.copy();
		for (int i = 0; i < dims.length; i++) {
			alleDim.addElement((Dimensionierung) dims[i].copy());
		}
	}
	
	public Vector<RegelKreis> getAlleRegelkreise() {
		Vector<RegelKreis> kreise = new Vector<>(alleDim.size());
		for(Dimensionierung dim : alleDim){
			kreise.add(new RegelKreis(dim, regelstrecke));
		}
		return kreise;
	}
	
	public Iterator<Dimensionierung> getAlleDim(int index) {
		return alleDim.iterator();
	}

	public void replaceDim(Dimensionierung old_dim, Dimensionierung new_dim) {
		int index = alleDim.indexOf(old_dim);
		alleDim.remove(index);
		alleDim.insertElementAt(new_dim, index);
		setChanged();
		notifyObservers();
	}

	public RegelStrecke getRegelstrecke() {
		return regelstrecke;
	}

	public void setRegelstrecke(RegelStrecke regelstrecke) {
		this.regelstrecke = regelstrecke;
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Model\nalleDim: ");
		builder.append(alleDim);
		builder.append("\nregelstrecke: ");
		builder.append(regelstrecke);
		return builder.toString();
	}
	
	public void removeDim(Dimensionierung dim) {
		if(alleDim.size() == 1){
			throw new IllegalStateException("Letzter Regler kann nicht entfernt werden.");
		}
		alleDim.remove(dim);
		setChanged();
		notifyObservers();
	}

	public void copyDim(Dimensionierung dim) {
		alleDim.add((Dimensionierung) dim.copy());
		setChanged();
		notifyObservers();
	}

	@Override
	public Object copy() {
		Dimensionierung[] dims = new Dimensionierung[alleDim.size()];
		alleDim.copyInto(dims);
		return new Model(regelstrecke, dims);
	}
}
