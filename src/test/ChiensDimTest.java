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
		int j = 0;

		RegelStrecke rs = new RegelStrecke(0.9999, 2.862, 20.24);
		ChiensDim p_dim;
		ChiensDim pi_dim;
		ChiensDim pid_dim;
		j = 0; // APERIODSTOER

		p_dim = new ChiensDim(j, ReglerTopologie.P, null);
		pi_dim = new ChiensDim(j, ReglerTopologie.PI, null);
		pid_dim = new ChiensDim(j, ReglerTopologie.PID, null);

		Regler regler = p_dim.calc(rs);
		Regler regler_referenz = new Regler(2.121805472);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);

		regler = pi_dim.calc(rs);
		regler_referenz = new Regler(4.243610944, 11.448);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);

		regler = pid_dim.calc(rs);
		regler_referenz = new Regler(6.719050661, 6.8688, 1.20204);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);
		assertEquals(regler_referenz.getTv(), regler.getTv(), 0.0001);

		j = 1;
		p_dim = new ChiensDim(j, ReglerTopologie.P, null);
		pi_dim = new ChiensDim(j, ReglerTopologie.PI, null);
		pid_dim = new ChiensDim(j, ReglerTopologie.PID, null);

		regler = p_dim.calc(rs);
		regler_referenz = new Regler(2.121805472);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);

		regler = pi_dim.calc(rs);
		regler_referenz = new Regler(2.475439717, 24.288);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);

		regler = pid_dim.calc(rs);
		regler_referenz = new Regler(4.243610944, 20.24, 1.431);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);
		assertEquals(regler_referenz.getTv(), regler.getTv(), 0.0001);

		j = 2;// ZWANZIGSTOER

		p_dim = new ChiensDim(j, ReglerTopologie.P, null);
		pi_dim = new ChiensDim(j, ReglerTopologie.PI, null);
		pid_dim = new ChiensDim(j, ReglerTopologie.PID, null);

		regler = p_dim.calc(rs);
		regler_referenz = new Regler(4.950879435);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);

		regler = pi_dim.calc(rs);
		regler_referenz = new Regler(4.950879435, 6.5826);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);

		regler = pid_dim.calc(rs);
		regler_referenz = new Regler(8.487221888, 5.724, 1.20204);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);
		assertEquals(regler_referenz.getTv(), regler.getTv(), 0.0001);

		j = 3;// ZWANZIGFUEHR

		p_dim = new ChiensDim(j, ReglerTopologie.P, null);
		pi_dim = new ChiensDim(j, ReglerTopologie.PI, null);
		pid_dim = new ChiensDim(j, ReglerTopologie.PID, null);

		regler = p_dim.calc(rs);
		regler_referenz = new Regler(4.950879435);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);

		regler = pi_dim.calc(rs);
		regler_referenz = new Regler(4.243610944, 27.324);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);

		regler = pid_dim.calc(rs);
		regler_referenz = new Regler(6.719050661, 27.324, 1.34514);
		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);
		assertEquals(regler_referenz.getTv(), regler.getTv(), 0.0001);
	}

}
