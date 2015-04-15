package test;

import static org.junit.Assert.*;
import main.PIDRechner;

import org.junit.Test;

import util.Matlab;

public class PIDRechnerTest {

	@Test
	public void testInit() {
		PIDRechner rechner = new PIDRechner();
		rechner.init();
		rechner.dispose();
		Matlab.closeProxy();
		//fail("Not yet implemented");
	}

}
