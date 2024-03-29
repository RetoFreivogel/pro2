package test;

import static org.junit.Assert.*;
import main.ZieglerDim;
import main.RegelStrecke;
import main.Regler;

import org.junit.Test;

public class ZieglerDimTest {

	@Test
	public void testCalc() {
		RegelStrecke rs = new RegelStrecke(0.9999, 4.082, 13.24);
		ZieglerDim dim = new ZieglerDim();
		Regler regler = dim.calc(rs);
		Regler regler_referenz = new Regler(2.919449221, 8.164, 2.041);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
		assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
	}
}
