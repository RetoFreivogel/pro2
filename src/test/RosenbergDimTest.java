package test;

import static org.junit.Assert.*;
import main.RosenbergDim;
import main.RegelStrecke;
import main.Regler;

import org.junit.Test;

public class RosenbergDimTest {

	@Test
	public void testCalc() {
		RegelStrecke rs = new RegelStrecke(0.9999, 2.862, 20.24);
		RosenbergDim dim = new RosenbergDim();
		Regler regler = dim.calc(rs);
		Regler regler_referenz = new Regler(8.487221888, 5.724, 1.25928);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
		assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
	}

}
