package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.*;

public class ReglerTest {

	@Test
	public void copyConstructorMakesAnEqualRegler() {
		Regler regler = new Regler(1.0, 0.5, 0.0);
		Regler reglercopy = new Regler(regler);	
		assertFalse(regler == reglercopy);
		assertTrue(regler.equals(reglercopy));
	}

}
