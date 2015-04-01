package test;

import static org.junit.Assert.*;
import main.RegelStrecke;

import org.junit.Test;

public class RegelStreckeTest {

	@Test
	public void testCopy() {
		RegelStrecke rs = new RegelStrecke(1.0, 1.0, 1.0);
		RegelStrecke copy_rs = new RegelStrecke(rs);
		if(rs == copy_rs){
			fail("Not yet implemented");
		}
		if(!rs.equals(copy_rs)){
			fail("Not yet implemented");
		}
	}

}
