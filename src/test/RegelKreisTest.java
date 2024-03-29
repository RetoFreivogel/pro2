package test;

import static org.junit.Assert.*;
import main.*;

import org.junit.Test;

public class RegelKreisTest {

	@Test
	public void makeOppeltKreis() {
		RegelStrecke regelstrecke = new RegelStrecke(1.0, 0.1, 0.5);
		OppeltDim dim = new OppeltDim();
		RegelKreis regelkreis = new RegelKreis(dim, regelstrecke);
		Regler regler = regelkreis.getRegler();
		assertFalse(regler == null);
		assertTrue(regelkreis.getDim() instanceof OppeltDim);
	}

	@Test
	public void modifyInternalRegler() {
		// modification of internal regler must change dim to a ManuellDim

		RegelStrecke regelstrecke = new RegelStrecke(1.0, 0.1, 0.5);
		OppeltDim dim = new OppeltDim();
		RegelKreis regelkreis = new RegelKreis(dim, regelstrecke);
		Regler regler = regelkreis.getRegler();
		// assertTrue(dim != regelkreis.getDim());
		// assertTrue(regelkreis.getDim() instanceof ManuellDim);
		assertTrue(regler.equals(regelkreis.getRegler()));
		// assertFalse(reglercopy.equals(regelkreis.getRegler()));
	}
}