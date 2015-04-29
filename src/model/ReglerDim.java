package model;

import java.util.Observable;

public abstract class ReglerDim extends Observable {
	public abstract Regler calc(RegelStrecke regelstrecke);
}
