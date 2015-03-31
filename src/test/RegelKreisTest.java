package test;

import static org.junit.Assert.*;
import main.*;

import org.junit.Test;

public class RegelKreisTest {

	@Test
	public static void makeOppeltKreis() {
		RegelStrecke regelstrecke = new RegelStrecke(1.0, 0.1, 0.5);
		OppeltDim dim = new OppeltDim();
		RegelKreis regelkreis = new RegelKreis(dim, regelstrecke);
		Regler regler = regelkreis.getRegler();
		assertFalse(regler == null);
		assertTrue(regelkreis.getDim() instanceof OppeltDim);
	}

	@Test
	public static void modifyInternalRegler() {
		// modification of internal regler must change dim to a ManuellDim

		RegelStrecke regelstrecke = new RegelStrecke(1.0, 0.1, 0.5);
		OppeltDim dim = new OppeltDim();
		RegelKreis regelkreis = new RegelKreis(dim, regelstrecke);
		Regler regler = regelkreis.getRegler();
		Regler reglercopy = new Regler(regler);
		regler.setKr(0.5);
		assertTrue(dim != regelkreis.getDim());
		assertTrue(regelkreis.getDim() instanceof ManuellDim);
		assertTrue(regler.equals(regelkreis.getRegler()));
		assertFalse(reglercopy.equals(regelkreis.getRegler()));
	}
}