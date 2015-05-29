package model;

import java.io.Serializable;

/**
 * Basisklasse für alle Reglerdimensionierungen.
 * @author Reto
 */
public abstract class AbstractDim implements Serializable, Copyable {
	private static final long serialVersionUID = 1L;
	private final ReglerTopologie topo;

	protected AbstractDim(ReglerTopologie topo){
		this.topo = topo;
	}
	
	public ReglerTopologie getTopo(){
		return topo;
	}
	
	public abstract Dimensionierung getDimensionierung();
	public abstract AbstractDim setTopo(ReglerTopologie topo);
	public abstract Regler calc(RegelStrecke regelstrecke);
}
