package test;

import static org.junit.Assert.*;
import model.RegelStrecke;

import org.junit.Test;

import util.Matlab;

public class RegelStreckeTest {

	@Test 
	public void testInvalid(){
		try{
			new RegelStrecke(-1.0, 2.0, 10.0);
			fail("Didn't throw");
		}catch(IllegalArgumentException e){}	
		
		try{
			new RegelStrecke(1.0, -2.0, 10.0);
			fail("Didn't throw");
		}catch(IllegalArgumentException e){}
		
		try{
			new RegelStrecke(1.0, 2.0, -10.0);
			fail("Didn't throw");
		}catch(IllegalArgumentException e){}
	}

	
	@Test
	public void testCopy() {
		RegelStrecke rs = new RegelStrecke(1.0, 2.0, 10.0);
		RegelStrecke copy_rs = new RegelStrecke(rs);

		assertEquals(copy_rs, rs);
		
		copy_rs.setKs(0.5);
		assertNotEquals(copy_rs, rs);	
	}
		
	@Test
	public void testHash(){
		RegelStrecke rs = new RegelStrecke(1.0, 2.0, 10.0);
		RegelStrecke copy_rs = new RegelStrecke(rs);
		
		assertEquals(rs.hashCode(), copy_rs.hashCode());
	}
	
	@Test
	public void testEquals(){
		RegelStrecke rs = new RegelStrecke(1.0, 2.0, 10.0);
		
		assertEquals(rs, rs);
		assertNotEquals(rs, null);
		assertNotEquals(rs, 1.0);
		assertNotEquals(rs, new RegelStrecke(0.5, 2.0, 10.0));
		assertNotEquals(rs, new RegelStrecke(1.0, 0.5, 10.0));
		assertNotEquals(rs, new RegelStrecke(1.0, 2.0, 0.5));
	}
}
