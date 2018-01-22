package jim.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import jim.classes.Outils;
import jim.classes.Utilisateur;

public class UtilisateurTest {
	
	private Utilisateur utilisateur1;
	private Utilisateur utilisateur2;
	
	@Before
	public void setUp() throws Exception {
		utilisateur1 = new Utilisateur();
		
		int unId = 111;
		String unPseudo = "toto";
		String unMdpSha1 = "abcdef";
		String uneAdrMail = "toto@free.fr";
		String unNumTel = "1122334455";
		int unNiveau = 1;
		Date uneDateCreation = Outils.convertirEnDateHeure("21/06/2016 14:00:00");
		int unNbTraces = 2;
		Date uneDateDerniereTrace = Outils.convertirEnDateHeure("28/06/2016 14:00:00");
		utilisateur2 = new Utilisateur(unId, unPseudo, unMdpSha1, uneAdrMail, unNumTel, unNiveau, uneDateCreation, unNbTraces, uneDateDerniereTrace);
	}

	@Test
	public void testGetId() {
		int id1 = utilisateur1.getId();
		assertEquals("Test getId", 0, id1);
		
		int id2 = utilisateur2.getId();
		assertEquals("Test getId", 111, id2);	
	}

	@Test
	public void testSetId() {
		utilisateur1.setId(112);
        assertEquals(112, utilisateur1.getId());
	}

	@Test
	public void testGetPseudo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPseudo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMdpSha1() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMdpSha1() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdrMail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAdrMail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumTel() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNumTel() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNiveau() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNiveau() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDateCreation() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDateCreation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNbTraces() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNbTraces() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDateDerniereTrace() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDateDerniereTrace() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
