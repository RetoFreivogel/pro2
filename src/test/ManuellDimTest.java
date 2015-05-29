package test;

import static org.junit.Assert.*;
import model.Regler;
import model.dimensionierung.DimEnum;
import model.dimensionierung.Dimensionierung;
import model.dimensionierung.TopoEnum;

import org.junit.Test;

public class ManuellDimTest {
	
	@Test
	public void testCalc() {
		Dimensionierung dim = new Dimensionierung(DimEnum.MANUELL, TopoEnum.PID);
		dim = dim.setKr(1.0);
		dim = dim.setTn(2.0);
		dim = dim.setTv(3.0);
		dim = dim.setTp(0.03);
		Regler regler_ref = new Regler(1.0, 2.0, 3.0);
		Regler regler = dim.calc(null);
		assertEquals(regler_ref.getKr(), regler.getKr(), 0.001);
		assertEquals(regler_ref.getTn(), regler.getTn(), 0.001);
		assertEquals(regler_ref.getTv(), regler.getTv(), 0.001);
		assertEquals(regler_ref.getTp(), regler.getTp(), 0.001);
	}
}
