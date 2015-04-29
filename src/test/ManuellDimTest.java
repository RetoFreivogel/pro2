package test;

import static org.junit.Assert.*;
import model.ManuellDim;
import model.Regler;

import org.junit.Test;

public class ManuellDimTest {

	@Test
	public void testGets(){
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0);
		ManuellDim unmodified_dim = new ManuellDim(dim.calc(null, null));
		
		assertEquals(1.0, dim.getKr(), 0);
		assertEquals(2.0, dim.getTn(), 0);
		assertEquals(3.0, dim.getTv(), 0);
		
		assertEquals(unmodified_dim, dim);
	}
	
	@Test
	public void testCalc() {
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0);
		Regler regler_ref = new Regler(1.0, 2.0, 3.0);
		Regler regler = dim.calc(null, null);
		assertEquals(regler_ref, regler);
		
		dim.setKr(4.0);
		dim.setTn(5.0);
		dim.setTv(6.0);
		dim.setTp(60.0);
		
		regler_ref = new Regler(4.0, 5.0, 6.0);
		regler = dim.calc(null, null);
		assertEquals(regler_ref, regler);
		
		ManuellDim other_dim = new ManuellDim(regler);
		assertEquals(dim, other_dim);
	}

	@Test
	public void testEquals(){
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0);
		
		assertEquals(dim, dim);
		assertNotEquals(dim, 1.0);
		assertNotEquals(dim, null);
	}
	
	@Test
	public void testMakeCopy() {
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0);
		ManuellDim copy_dim = new ManuellDim(dim.calc(null, null));
		assertEquals(dim, copy_dim);
		
		copy_dim = new ManuellDim(dim.calc(null, null));
		dim.setKr(0.0);	
		assertNotEquals(dim, copy_dim);
		
		copy_dim = new ManuellDim(dim.calc(null, null));
		dim.setTn(0.0);	
		assertNotEquals(dim, copy_dim);
		
		copy_dim = new ManuellDim(dim.calc(null, null));
		dim.setTv(0.0);	
		assertNotEquals(dim, copy_dim);
	}
	
	@Test
	public void testHash(){
		ManuellDim dim = new ManuellDim(1.0, 2.0, 3.0);
		ManuellDim copy_dim = new ManuellDim(dim.calc(null, null));
		
		assertEquals(dim.hashCode(), copy_dim.hashCode());
	}

}
