package test;

import static org.junit.Assert.*;
import model.RegelStrecke;
import model.Regler;
import model.ReglerTopologie;
import model.ZieglerDim;

import org.junit.Test;

public class ZieglerDimTest {

	@Test
	public void testCalc() {
		RegelStrecke rs = new RegelStrecke(0.9999, 4.082, 13.24);
		ZieglerDim dim = new ZieglerDim(ReglerTopologie.P);
		
		Regler regler = dim.calc(rs);
		Regler regler_referenz = new Regler(3.243832468);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);

		dim = dim.setTopo(ReglerTopologie.PI);
		regler = dim.calc(rs);
		regler_referenz = new Regler(2.919449221, 13.4706);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);

		dim = dim.setTopo(ReglerTopologie.PID);
		regler = dim.calc(rs);
		regler_referenz = new Regler(2.919449221, 8.164, 2.041);

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
		assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
	}
}
