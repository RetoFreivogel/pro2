package test;

import static org.junit.Assert.*;
import model.ManuellDim;
import model.Regler;
import model.ReglerTopologie;

import org.junit.Test;

public class ManuellDimTest {

	@Test
	public void testGets(){
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0, 0.03, ReglerTopologie.PID, null);
		ManuellDim unmodified_dim = new ManuellDim(dim.calc(null), null);
		
		assertEquals(1.0, dim.getKr(), 0);
		assertEquals(2.0, dim.getTn(), 0);
		assertEquals(3.0, dim.getTv(), 0);
		
		assertEquals(unmodified_dim, dim);
	}
	
	@Test
	public void testCalc() {
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0, 0.03, ReglerTopologie.PID, null);
		Regler regler_ref = new Regler(1.0, 2.0, 3.0);
		Regler regler = dim.calc(null);
		assertEquals(regler_ref, regler);
		
		dim = dim.setKr(4.0);
		dim = dim.setTn(5.0);
		dim = dim.setTv(6.0);
		dim = dim.setTp(0.06);
		
		regler_ref = new Regler(4.0, 5.0, 6.0);
		regler = dim.calc(null);
		
		assertEquals(regler_ref.getKr(), regler.getKr(), 0.0001);
		assertEquals(regler_ref.getTn(), regler.getTn(), 0.0001);
		assertEquals(regler_ref.getTv(), regler.getTv(), 0.0001);
		
		ManuellDim other_dim = new ManuellDim(regler, null);
		assertEquals(dim, other_dim);
	}

	@Test
	public void testEquals(){
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0, 0.03, ReglerTopologie.PID, null);
		
		assertEquals(dim, dim);
		assertNotEquals(dim, 1.0);
		assertNotEquals(dim, null);
	}
	
	@Test
	public void testMakeCopy() {
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0, 0.03, ReglerTopologie.PID, null);
		ManuellDim copy_dim = new ManuellDim(dim.calc(null), null);
		assertEquals(dim, copy_dim);
		
		copy_dim = new ManuellDim(dim.calc(null), null);
		dim = dim.setKr(0.1);	
		assertNotEquals(dim, copy_dim);
		
		copy_dim = new ManuellDim(dim.calc(null), null);
		dim = dim.setTn(0.1);	
		assertNotEquals(dim, copy_dim);
		
		copy_dim = new ManuellDim(dim.calc(null), null);
		dim = dim.setTv(0.1);	
		assertNotEquals(dim, copy_dim);
		
		//Tests that no references are kept
		copy_dim = new ManuellDim(dim.calc(null), null);
		
	}
	
	@Test
	public void testHash(){
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0, 0.03, ReglerTopologie.PID, null);
		ManuellDim copy_dim = new ManuellDim(dim.calc(null), null);
		
		assertEquals(dim.hashCode(), copy_dim.hashCode());
	}

}
