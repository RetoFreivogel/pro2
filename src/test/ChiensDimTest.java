package test;

import static org.junit.Assert.*;
import main.ChiensDim;
import main.RosenbergDim;
import main.RegelStrecke;
import main.Regler;

import org.junit.Test;

public class ChiensDimTest {

	@Test
	public void testCalc() {
		RegelStrecke rs = new RegelStrecke(); //Werte von Regelstrecke
		RosenbergDim dim = new ChiensDim(); //
		Regler regler = dim.calc(rs);
		Regler regler_referenz = new Regler();

		assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
		assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
		assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
	}

}
