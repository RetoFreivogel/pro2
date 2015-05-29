package test;

import static org.junit.Assert.*;
import model.RegelStrecke;
import model.Regler;
import model.dimensionierung.DimEnum;
import model.dimensionierung.Dimensionierung;
import model.dimensionierung.TopoEnum;

import org.junit.Test;


/*
 * Die Abweichungen von den Matlab werten kommen primär von der Suche nach wpid, die in Matlab leicht ungenau ist;
 */
public class ZellwegerDimTest {
		
	@Test
	public void testPID() {
		RegelStrecke rs = new RegelStrecke(1.0, 1.71, 7.6);
		Dimensionierung dim = new Dimensionierung(DimEnum.PHASENGANG, TopoEnum.PID);
		
		Regler r = dim.calc(rs);

		assertEquals(2.3034, r.getKr(), 0.05);
		assertEquals(3.9447, r.getTn(), 0.05);
		assertEquals(0.8556, r.getTv(), 0.05);
		assertEquals(0.1598, r.getTp(), 0.05);
	}

	@Test
	public void testPI() {
		RegelStrecke rs = new RegelStrecke(1.0, 1.11, 8.62);
		Dimensionierung dim = new Dimensionierung(DimEnum.PHASENGANG, TopoEnum.PI);

		Regler r = dim.calc(rs);

		assertEquals(1.46, r.getKr(), 0.01);
		assertEquals(3.24, r.getTn(), 0.01);
	}
}
