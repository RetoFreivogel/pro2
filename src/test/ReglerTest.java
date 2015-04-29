package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Regler;

public class ReglerTest {

	@Test
	public void copyConstructorMakesAnEqualRegler() {
		Regler regler = new Regler(1.0, 0.5, 0.0);
		Regler reglercopy = new Regler(regler);
		assertNotSame(regler, reglercopy);
		assertEquals(regler, reglercopy);
	}
	
	@Test
	public void testEquals(){
		Regler regler = new Regler(1.0, 0.5, 0.0);
		
		assertEquals(regler, regler);
		assertNotEquals(regler, null);
		assertNotEquals(regler, 1.0);
		assertNotEquals(regler, new Regler(2.0, 0.5, 0.0));
		assertNotEquals(regler, new Regler(1.0, 1.5, 0.0));
		assertNotEquals(regler, new Regler(1.0, 0.5, 1.0));
		assertEquals(regler, new Regler(1.0, 0.5, 0.0));
	}
	
	@Test
	public void testHash(){
		Regler regler = new Regler(1.0, 0.5, 0.0);
		Regler reglercopy = new Regler(regler);
		
		assertEquals(regler.hashCode(), reglercopy.hashCode());
	}

}
