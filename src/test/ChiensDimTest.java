package test;

import static org.junit.Assert.*;
import model.RegelStrecke;
import model.Regler;
import model.dimensionierung.ChiensEnum;
import model.dimensionierung.DimEnum;
import model.dimensionierung.Dimensionierung;
import model.dimensionierung.TopoEnum;

import org.junit.Test;

public class ChiensDimTest {

	@Test
	public void testCalc() {
		RegelStrecke rs = new RegelStrecke(0.9999, 2.862, 20.24);
		Dimensionierung p_dim = new Dimensionierung(DimEnum.CHIENS, TopoEnum.P);
		Dimensionierung pi_dim = new Dimensionierung(DimEnum.CHIENS, TopoEnum.PI);
		Dimensionierung pid_dim = new Dimensionierung(DimEnum.CHIENS, TopoEnum.PID);

		p_dim = p_dim.setVerhalten(ChiensEnum.APERIODSTOER);
		pi_dim = pi_dim.setVerhalten(ChiensEnum.APERIODSTOER);
		pid_dim = pid_dim.setVerhalten(ChiensEnum.APERIODSTOER);

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

		p_dim = p_dim.setVerhalten(ChiensEnum.APERIODFUEHR);
		pi_dim = pi_dim.setVerhalten(ChiensEnum.APERIODFUEHR);
		pid_dim = pid_dim.setVerhalten(ChiensEnum.APERIODFUEHR);

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

		p_dim = p_dim.setVerhalten(ChiensEnum.ZWANZIGSTOER);
		pi_dim = pi_dim.setVerhalten(ChiensEnum.ZWANZIGSTOER);
		pid_dim = pid_dim.setVerhalten(ChiensEnum.ZWANZIGSTOER);

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

		p_dim = p_dim.setVerhalten(ChiensEnum.ZWANZIGFUEHR);
		pi_dim = pi_dim.setVerhalten(ChiensEnum.ZWANZIGFUEHR);
		pid_dim = pid_dim.setVerhalten(ChiensEnum.ZWANZIGFUEHR);

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
