package jim.tests;

import static org.junit.Assert.*;

import java.text.ParseException;
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
		int unNbTraces = 3;
		Date uneDateDerniereTrace = Outils.convertirEnDateHeure("28/06/2016 14:00:00");
		utilisateur2 = new Utilisateur(unId, unPseudo, unMdpSha1, uneAdrMail, unNumTel, unNiveau, uneDateCreation, unNbTraces, uneDateDerniereTrace);
	}

	@Test
	public void testGetId() {
		int id = utilisateur1.getId();
		assertEquals("Test getId", 0, id);
		
		id = utilisateur2.getId();
		assertEquals("Test getId", 111, id);	
	}

	@Test
	public void testSetId() {
		utilisateur1.setId(112);
        assertEquals(112, utilisateur1.getId());
	}

	@Test
	public void testGetPseudo() {
		String pseudo = utilisateur1.getPseudo();
		assertEquals("Test getPseudo", "", pseudo);
		
		pseudo = utilisateur2.getPseudo();
		assertEquals("Test getPseudo", "toto", pseudo);	
	}

	@Test
	public void testSetPseudo() {
		utilisateur1.setPseudo("titi");
        assertEquals("titi", utilisateur1.getPseudo());
	}

	@Test
	public void testGetMdpSha1() {
		String mdpSha1 = utilisateur1.getMdpSha1();
		assertEquals("Test getMdpSha1", "", mdpSha1);
		
		mdpSha1 = utilisateur2.getMdpSha1();
		assertEquals("Test getMdpSha1", "abcdef", mdpSha1);
	}

	@Test
	public void testSetMdpSha1() {
		utilisateur1.setMdpSha1("123456");
        assertEquals("123456", utilisateur1.getMdpSha1());
	}

	@Test
	public void testGetAdrMail() {
		String adrMail = utilisateur1.getAdrMail();
		assertEquals("Test getAdrMail", "", adrMail);
		
		adrMail = utilisateur2.getAdrMail();
		assertEquals("Test getAdrMail", "toto@free.fr", adrMail);
	}

	@Test
	public void testSetAdrMail() {
		utilisateur1.setAdrMail("titi@free.fr");
        assertEquals("titi@free.fr", utilisateur1.getAdrMail());
	}

	@Test
	public void testGetNumTel() {
		String numTel = utilisateur1.getNumTel();
		assertEquals("Test getNumTel", "", numTel);
		
		numTel = utilisateur2.getNumTel();
		assertEquals("Test getNumTel", "11.22.33.44.55", numTel);
	}

	@Test
	public void testSetNumTel() {
		utilisateur1.setNumTel("2233445566");
        assertEquals("22.33.44.55.66", utilisateur1.getNumTel());
	}

	@Test
	public void testGetNiveau() {
		int niveau = utilisateur1.getNiveau();
		assertEquals("Test getNiveau", 0, niveau);
		
		niveau = utilisateur2.getNiveau();
		assertEquals("Test getNiveau", 1, niveau);	
	}

	@Test
	public void testSetNiveau() {
		utilisateur1.setNiveau(2);
        assertEquals(2, utilisateur1.getNiveau());
	}

	@Test
	public void testGetDateCreation() {
		Date dateCreation = utilisateur1.getDateCreation();
		assertNull("Test getDateCreation", dateCreation);
		
		String strDateCreation = Outils.formaterDateHeure(utilisateur2.getDateCreation());
		assertEquals("Test getDateCreation", "21/06/2016 14:00:00", strDateCreation);	
	}

	@Test
	public void testSetDateCreation() throws ParseException {
		Date uneDateCreation = Outils.convertirEnDateHeure("21/06/2016 14:00:00");
		utilisateur1.setDateCreation(uneDateCreation);
		String strDateCreation = Outils.formaterDateHeure(utilisateur1.getDateCreation());
		assertEquals("Test setDateCreation", "21/06/2016 14:00:00", strDateCreation);
	}

	@Test
	public void testGetNbTraces() {
		int nbTraces = utilisateur1.getNbTraces();
		assertEquals("Test getNbTraces", 0, nbTraces);
		
		nbTraces = utilisateur2.getNbTraces();
		assertEquals("Test getNbTraces", 3, nbTraces);	
	}

	@Test
	public void testSetNbTraces() {
		utilisateur1.setNbTraces(4);
        assertEquals(4, utilisateur1.getNbTraces());
	}

	@Test
	public void testGetDateDerniereTrace() {
		Date dateDerniereTrace = utilisateur1.getDateDerniereTrace();
		assertNull("Test getDateDerniereTrace", dateDerniereTrace);
		
		String strDateDerniereTrace = Outils.formaterDateHeure(utilisateur2.getDateDerniereTrace());
		assertEquals("Test getDateDerniereTrace", "28/06/2016 14:00:00", strDateDerniereTrace);	
	}

	@Test
	public void testSetDateDerniereTrace() throws ParseException {
		Date dateDerniereTrace = Outils.convertirEnDateHeure("29/06/2016 14:00:00");
		utilisateur1.setDateDerniereTrace(dateDerniereTrace);
		String strDateDerniereTrace = Outils.formaterDateHeure(utilisateur1.getDateDerniereTrace());
		assertEquals("Test setDateDerniereTrace", "29/06/2016 14:00:00", strDateDerniereTrace);
	}

	@Test
	public void testToString() {
		String msg = "";
	    msg += "id : " + "0" + "\n";
	    msg += "pseudo : " + "" + "\n";
	    msg += "mdpSha1 : " + "" + "\n";
	    msg += "adrMail : " + "" + "\n";
	    msg += "numTel : " + "" + "\n";
	    msg += "niveau : " + "0" + "\n";
	    msg += "nbTraces : " + "0" + "\n";
	    assertEquals("Test toString", msg, utilisateur1.toString());
	    
		msg = "";
	    msg += "id : " + "111" + "\n";
	    msg += "pseudo : " + "toto" + "\n";
	    msg += "mdpSha1 : " + "abcdef" + "\n";
	    msg += "adrMail : " + "toto@free.fr" + "\n";
	    msg += "numTel : " + "11.22.33.44.55" + "\n";
	    msg += "niveau : " + "1" + "\n";
	    msg += "dateCreation : " + "21/06/2016 14:00:00" + "\n";
	    msg += "nbTraces : " + "3" + "\n";
	    msg += "dateDerniereTrace : " + "28/06/2016 14:00:00" + "\n";
	    assertEquals("Test toString", msg, utilisateur2.toString());
	}

}
