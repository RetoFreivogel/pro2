package test;

import static org.junit.Assert.*;
import main.ZieglerDim;
import main.RegelStrecke;
import main.Regler;

import org.junit.Test;

public class ZieglerDimTest {

	@Test
	public void testCalc() {
		RegelStrecke rs = new RegelStrecke(0.9999, 2.862, 20.24); //Testwerte für alle Faustformeln gleich?
		ZieglerDim dim = new ZieglerDim();
		Regler regler = dim.calc(rs);
		Regler regler_referenz = new Regler(6.3654, 5.724, 1.4310); //Werte Kr, Tn, Tv mit Matlab berechnet

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
		assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
	}
}
