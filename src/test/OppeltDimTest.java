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
		OppeltDim dim = new OppeltDim(ReglerTopologie.PID);

		Regler regler = dim.calc(rs);
		Regler regler_referenz = new Regler(7.072684907);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);

		dim.setTopo(ReglerTopologie.PI);
		regler = dim.calc(rs);
		regler_referenz = new Regler(5.658147925, 8.586);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);

		dim.setTopo(ReglerTopologie.PID);
		regler = dim.calc(rs);
		regler_referenz = new Regler(8.487221888, 5.724, 1.20204);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
		assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);

	}
}
