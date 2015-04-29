package test;

import static org.junit.Assert.*;
import model.OppeltDim;
import model.RegelStrecke;
import model.Regler;
import model.ReglerTopologie;

import org.junit.Test;

public class OppeltDimTest {

	@Test
	public void testCalc() {
		RegelStrecke rs = new RegelStrecke(0.9999, 2.862, 20.24);
		OppeltDim dim = new OppeltDim();
		Regler regler = dim.calc(rs, ReglerTopologie.PID);
		Regler regler_referenz = new Regler(8.487221888, 5.724, 1.20204);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
		assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
	}
}
