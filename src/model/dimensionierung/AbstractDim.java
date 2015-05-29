package model.dimensionierung;

import java.io.Serializable;

import util.Copyable;
import model.RegelStrecke;
import model.Regler;

/**
 * Basisklasse für alle Reglerdimensionierungen.
 * @author Reto
 */
abstract class AbstractDim implements Serializable, Copyable {
	private static final long serialVersionUID = 1L;
	private final TopoEnum topo;

	protected AbstractDim(TopoEnum topo){
		this.topo = topo;
	}
	
	 TopoEnum getTopo(){
		return topo;
	}
	
	 abstract DimEnum getTyp();
	 abstract AbstractDim setTopo(TopoEnum topo);
	 abstract Regler calc(RegelStrecke regelstrecke);
}
