package test;

import static org.junit.Assert.*;
import java.util.Random;

import main.RegelStrecke;

import org.junit.Test;

public class RegelStreckeTest {

	@Test
	public void testCopy() {
		Random r = new Random();
		RegelStrecke rs = new RegelStrecke(r.nextDouble(), r.nextDouble(), r.nextDouble());
		RegelStrecke copy_rs = new RegelStrecke(rs);
		if(rs == copy_rs){
			fail("Didn't create new instance");
		}
		if(!rs.equals(copy_rs)){
			fail("Object wasn't equal");
		}
	}

}
