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
		int j = 2;
		
		RegelStrecke rs = new RegelStrecke(0.9999, 2.862, 20.24);
		ChiensDim dim = new ChiensDim(j);
		Regler regler = dim.calc(rs, ReglerTopologie.PID);
		
		if(j==2){
			Regler regler_referenz = new Regler(4.243, 20.24, 1.431);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
			assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
		}
		
		if(j==4){
			Regler regler_referenz = new Regler(6.7190, 27.324, 1.345);
			assertEquals(regler.getKr(), regler_referenz.getKr(), 0.0001);
			assertEquals(regler.getTn(), regler_referenz.getTn(), 0.0001);
			assertEquals(regler.getTv(), regler_referenz.getTv(), 0.0001);
		}

		
	}

}
