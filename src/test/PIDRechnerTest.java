package test;

import static org.junit.Assert.*;
import main.PIDRechner;

import org.junit.Test;

import util.Matlab;

public class PIDRechnerTest {

	@Test
	public void testInit() {
		Matlab.setMocked(true);
		PIDRechner rechner = new PIDRechner();
		rechner.init();
		rechner.dispose();
		//fail("Not yet implemented");
	}

}
