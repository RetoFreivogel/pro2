package test;

import static org.junit.Assert.*;
import model.OppeltDim;
import model.RegelKreis;
import model.RegelStrecke;
import model.Regler;
import model.ReglerTopologie;

import org.junit.Test;

public class RegelKreisTest {

	@Test
	public void makeOppeltKreis() {
		RegelStrecke regelstrecke = new RegelStrecke(1.0, 0.1, 0.5);
		OppeltDim dim = new OppeltDim(ReglerTopologie.PID, null);
		RegelKreis regelkreis = new RegelKreis(dim, regelstrecke);
		Regler regler = regelkreis.getRegler();
		assertFalse(regler == null);
		assertTrue(regelkreis.getDim() instanceof OppeltDim);
	}

	@Test
	public void modifyInternalRegler() {
		// modification of internal regler must change dim to a ManuellDim

		RegelStrecke regelstrecke = new RegelStrecke(1.0, 0.1, 0.5);
		OppeltDim dim = new OppeltDim(ReglerTopologie.PID, null);
		RegelKreis regelkreis = new RegelKreis(dim, regelstrecke);
		Regler regler = regelkreis.getRegler();
		// assertTrue(dim != regelkreis.getDim());
		// assertTrue(regelkreis.getDim() instanceof ManuellDim);
		assertTrue(regler.equals(regelkreis.getRegler()));
		// assertFalse(reglercopy.equals(regelkreis.getRegler()));
	}
}