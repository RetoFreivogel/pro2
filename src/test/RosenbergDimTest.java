package test;

import static org.junit.Assert.*;
import model.RegelStrecke;
import model.Regler;
import model.ReglerTopologie;
import model.RosenbergDim;

import org.junit.Test;

public class RosenbergDimTest {

	@Test
	public void testCalc() {
		RegelStrecke rs = new RegelStrecke(0.9999, 2.862, 20.24);
		RosenbergDim dim = new RosenbergDim();

		Regler regler = dim.calc(rs, ReglerTopologie.P);
		Regler regler_referenz = new Regler(7.072684907);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);

		regler = dim.calc(rs, ReglerTopologie.PI);
		regler_referenz = new Regler(6.436143265, 9.4446);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);

		regler = dim.calc(rs, ReglerTopologie.PID);
		regler_referenz = new Regler(8.487221888, 5.724, 1.25928);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
		assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);

	}

}
