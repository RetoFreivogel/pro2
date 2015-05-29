package test;

import static org.junit.Assert.*;
import model.RegelStrecke;
import model.Regler;
import model.dimensionierung.DimEnum;
import model.dimensionierung.Dimensionierung;
import model.dimensionierung.TopoEnum;

import org.junit.Test;

public class OppeltDimTest {

	@Test
	public void testCalc() {
		RegelStrecke rs = new RegelStrecke(0.9999, 2.862, 20.24);
		Dimensionierung dim = new Dimensionierung(DimEnum.OPPELT, TopoEnum.P);

		Regler regler = dim.calc(rs);
		Regler regler_referenz = new Regler(7.072684907);

		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);

		dim = dim.setTopo(TopoEnum.PI);
		regler = dim.calc(rs);
		regler_referenz = new Regler(5.658147925, 8.586);

		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);

		dim = dim.setTopo(TopoEnum.PID);
		regler = dim.calc(rs);
		regler_referenz = new Regler(8.487221888, 5.724, 1.20204);

		assertEquals(regler_referenz.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_referenz.getTn(), regler.getTn(), 0.0001);
		assertEquals(regler_referenz.getTv(), regler.getTv(), 0.0001);

	}
}
