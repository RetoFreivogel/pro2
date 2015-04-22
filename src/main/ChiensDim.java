package main;

public final class ChiensDim extends ReglerDim {

	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		double Ks = regelstrecke.getKs().getValue();
		double Tu = regelstrecke.getTu().getValue();
		double Tg = regelstrecke.getTg().getValue();
		
		int j;
		//Soll prüfen:
		//Aperiodischer Verlauf (Gutes Stoer- [0] /Fuehrungsverhalten [1]) oder
		//20 Prozent Ueberschwingen (Gutes Stoer- [2]/Fuehrungsverhalten [3])
		
		switch (j) {
		case 0:
			new Regler((0.95 / Ks) * (Tg / Tu), 2.4 * Tu, 0.42 * Tu);
			break;
			
		case 1:
			new Regler((0.6 / Ks) * (Tg / Tu), Tg, 0.5 * Tu);
			break;
			
		case 2:
			new Regler((1.2 / Ks) * (Tg / Tu), 2 * Tu, 0.42 * Tu);
			break;
			
		case 3:
			new Regler((0.95 / Ks) * (Tg / Tu), 1.35 * Tu, 0.47 * Tu);
			break;

		default: //Wenn Eingabewerte falsch
			System.out.println();
			break;
		}
		//return Regler(Kr, Tn, Tv); //Gibt Resultat zurueck
	}

	@Override
	public ReglerDim makeCopy() {
		return this;
	}
}
