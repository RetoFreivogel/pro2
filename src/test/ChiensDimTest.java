package test;

import static org.junit.Assert.*;
import model.ChiensDim;
import model.RegelStrecke;
import model.Regler;
import model.ReglerTopologie;

import org.junit.Test;

public class ChiensDimTest {

	@Test
	public void testCalc() {
		int j = 1;

		RegelStrecke rs = new RegelStrecke(0.9999, 2.862, 20.24);
		ChiensDim p_dim = new ChiensDim(j, ReglerTopologie.P);
		ChiensDim pi_dim = new ChiensDim(j, ReglerTopologie.PI);
		ChiensDim pid_dim = new ChiensDim(j, ReglerTopologie.PID);

		if (j == 1) {// APERIODSTOER
			Regler regler = p_dim.calc(rs);
			Regler regler_referenz = new Regler(2.121805472);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			
			regler = pi_dim.calc(rs);
			regler_referenz = new Regler(4.243610944, 11.448);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
			
			regler = pid_dim.calc(rs);
			regler_referenz = new Regler(6.719050661, 6.8688, 1.20204);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
			assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
		}

		if (j == 2) {// APERIODFUEHR
			Regler regler = p_dim.calc(rs);
			Regler regler_referenz = new Regler(2.121805472);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			
			regler = pi_dim.calc(rs);
			regler_referenz = new Regler(2.475439717, 3.4344);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
			
			regler = pid_dim.calc(rs);
			regler_referenz = new Regler(4.243610944, 20.24, 1.431);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
			assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
		}

		if (j == 3) {// ZWANZIGSTOER
			Regler regler = p_dim.calc(rs);
			Regler regler_referenz = new Regler(4.950879435);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			
			regler = pi_dim.calc(rs);
			regler_referenz = new Regler(4.950879435, 6.5826);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
			
			regler = pid_dim.calc(rs);
			regler_referenz = new Regler(8.487221888, 5.724, 1.20204);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
			assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
		}

		if (j == 4) {// ZWANZIGFUEHR
			Regler regler = p_dim.calc(rs);
			Regler regler_referenz = new Regler(4.950879435);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);

			regler = pi_dim.calc(rs);
			regler_referenz = new Regler(4.243610944, 27.324);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);

			regler = pid_dim.calc(rs);
			regler_referenz = new Regler(6.719050661, 27.324, 1.34514);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
			assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
		}

	}

}
