package model.dimensionierung;

import model.RegelStrecke;
import model.Regler;

/**
 * Chiens - Dimensionierungsmethode
 * @author Claudius Jörg, Reto Freivogel
 *
 */
final class ChiensDim extends AbstractDim{
	private static final long serialVersionUID = 1L;

	private final ChiensEnum verhalten;

	ChiensDim(ChiensEnum verhalten, TopoEnum topo) {
		super(topo);
		this.verhalten = verhalten;
	}
	
	@Override
	Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs();
		double Tu = regelstrecke.getTu();
		double Tg = regelstrecke.getTg();

		switch (getTopo()) {
		case P:
			switch (verhalten) {
			case APERIODSTOER:
				return new Regler((0.3 / Ks) * (Tg / Tu));
			case APERIODFUEHR:
				return new Regler((0.3 / Ks) * (Tg / Tu));
			case ZWANZIGSTOER:
				return new Regler((0.7 / Ks) * (Tg / Tu));
			case ZWANZIGFUEHR:
				return new Regler((0.7 / Ks) * (Tg / Tu));
			default:
				assert (false);
				return null;
			}
		case PI:
			switch (verhalten) {
			case APERIODSTOER:
				return new Regler((0.6 / Ks) * (Tg / Tu), 4 * Tu);
			case APERIODFUEHR:
				return new Regler((0.35 / Ks) * (Tg / Tu), 1.2 * Tg);
			case ZWANZIGSTOER:
				return new Regler((0.7 / Ks) * (Tg / Tu), 2.3 * Tu);
			case ZWANZIGFUEHR:
				return new Regler((0.6 / Ks) * (Tg / Tu), 1.35 * Tg);
			default:
				assert (false);
				return null;
			}
		case PID:
			switch (verhalten) {
			case APERIODSTOER:
				return new Regler((0.95 / Ks) * (Tg / Tu), 2.4 * Tu, 0.42 * Tu);
			case APERIODFUEHR:
				return new Regler((0.6 / Ks) * (Tg / Tu), Tg, 0.5 * Tu);
			case ZWANZIGSTOER:
				return new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.42 * Tu);
			case ZWANZIGFUEHR:
				return new Regler((0.95 / Ks) * (Tg / Tu), 1.35 * Tg, 0.47 * Tu);
			default:
				assert (false);
				return null;
			}
		default:
			throw new IllegalArgumentException();
		}
	}

	ChiensEnum getVerhalten() {
		return verhalten;
	}

	ChiensDim setVerhalten(ChiensEnum verhalten) {
		return new ChiensDim(verhalten, getTopo());
	}

	@Override
	 ChiensDim setTopo(TopoEnum topo) {
		return new ChiensDim(verhalten, topo);
	}

	@Override
	 DimEnum getTyp() {
		return DimEnum.CHIENS;
	}
	
	@Override
	public ChiensDim copy(){
		return new ChiensDim(verhalten, getTopo());
	}
}
