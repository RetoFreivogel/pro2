package model;

import java.util.Observable;
import java.util.Scanner;

/**
 * Basisklasse für alle Reglerdimensionierungen.
 * @author Reto
 */
public abstract class AbstractDim extends Observable {
	
	/**
	 * Funktion zur Berechnung eines neuen Reglers.
	 * @param regelstrecke Die regelstrecke, welche der generierte Regler ansteuern soll.
	 * @param topo Die gewünschte Topologie des Reglers
	 * @return
	 */
	public static final AbstractDim fromScanner(Scanner sc){
		String str = sc.nextLine();
		
		switch(str){
		case "OppeltDim":
			return new OppeltDim();
		case "ManuellDim":
			return new ManuellDim(sc);
		case "ChiensDim":
			return new ChiensDim(sc);
		case "RosenbergerDim":
			return new RosenbergDim();
		case "ZellwegerDim":
			return new ZellwegerDim(sc);
		case "ZieglerDim":
			return new ZieglerDim();
		}
		return null;
	}
	
	public abstract Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo);

	@Override
	public String toString() {
		return "";
	}
	
	
}
