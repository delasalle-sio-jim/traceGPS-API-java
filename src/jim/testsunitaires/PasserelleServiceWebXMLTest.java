package jim.testsunitaires;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import jim.classes.Outils;
import jim.classes.PasserelleServicesWebXML;
import jim.classes.Point;
import jim.classes.PointDeTrace;
import jim.classes.Trace;
import jim.classes.Utilisateur;

public class PasserelleServiceWebXMLTest {

	@Test
	public void testConnecter() {
//		String msg = PasserelleServicesWebXML.connecter("admin", "adminnnnnnnn");
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWebXML.connecter("admin", Outils.sha1("mdpadmin"));
//		assertEquals("Administrateur authentifi�.", msg);
//		
//		msg = PasserelleServicesWebXML.connecter("europa", Outils.sha1("mdputilisateur"));
//		assertEquals("Utilisateur authentifi�.", msg);	
	}
	
	
	@Test
	public void testDemanderMdp() {
//		String msg = PasserelleServicesWebXML.demanderMdp("jim");
//		assertEquals("Erreur : pseudo inexistant !", msg);
//		
//		msg = PasserelleServicesWebXML.demanderMdp("europa");
//		assertEquals("Vous allez recevoir un courriel avec votre nouveau mot de passe.", msg);
	}	

	
	@Test
	public void testChangerDeMdp() {
//		String msg = PasserelleServicesWebXML.changerDeMdp("europa", Outils.sha1("mdputilisateur"), "passepasse", "passepassepasse");
//		assertEquals("Erreur : le nouveau mot de passe et sa confirmation sont diff�rents !", msg);
//		
//		msg = PasserelleServicesWebXML.changerDeMdp("europa", Outils.sha1("mdputilisateurrrr"), "passepasse", "passepasse");
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWebXML.changerDeMdp("europa", Outils.sha1("mdputilisateur"), "mdputilisateurrrr", "mdputilisateurrrr");
//		assertEquals("Enregistrement effectu� ; vous allez recevoir un mail de confirmation.", msg);
//		
//		msg = PasserelleServicesWebXML.changerDeMdp("europa", Outils.sha1("mdputilisateurrrr"), "mdputilisateur", "mdputilisateur");
//		assertEquals("Enregistrement effectu� ; vous allez recevoir un mail de confirmation.", msg);
	}	

	
	@Test
	public void testCreerUtilisateur() {
//		String msg = PasserelleServicesWebXML.creerUtilisateur("jim", "delasalle.sio.eleves@gmail.com", "1122334455");
//		assertEquals("Erreur : pseudo trop court (8 car minimum) ou d�j� existant !", msg);
//		
//		msg = PasserelleServicesWebXML.creerUtilisateur("turlututu", "delasalle.sio.elevesgmail.com", "1122334455");
//		assertEquals("Erreur : adresse mail incorrecte ou d�j� existante !", msg);
//
//		msg = PasserelleServicesWebXML.creerUtilisateur("turlututu", "delasalle.sio.eleves@gmailcom", "1122334455");
//		assertEquals("Erreur : adresse mail incorrecte ou d�j� existante !", msg);
//		
//		msg = PasserelleServicesWebXML.creerUtilisateur("turlututu", "delasalle.sio.eleves@gmail.com", "1122334455");
//		assertEquals("Erreur : adresse mail incorrecte ou d�j� existante !", msg);
//		
//		msg = PasserelleServicesWebXML.creerUtilisateur("turlututu", "delasallesioeleves@gmail.com", "1122334455");
//		assertEquals("Enregistrement effectu�. Vous allez recevoir un courriel avec votre mot de passe.", msg);
//		
//		msg = PasserelleServicesWebXML.creerUtilisateur("turlututu", "de.la.salle.sio.eleves@gmail.com", "1122334455");
//		assertEquals("Erreur : pseudo trop court (8 car minimum) ou d�j� existant !", msg);	
	}

	
	@Test
	public void testDemarrerEnregistrementParcours() {
//		Trace laTrace = new Trace();
//		String msg = PasserelleServicesWebXML.demarrerEnregistrementParcours("europa", Outils.sha1("mdputilisateurrrrrr"), laTrace);
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		laTrace = new Trace();
//		msg = PasserelleServicesWebXML.demarrerEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), laTrace);
//		assertEquals("Trace cr��e.", msg);	
	}
	

	@Test
	public void testEnvoyerPosition() throws ParseException {
//		Date laDate = Outils.convertirEnDateHeure("24/01/2018 13:42:21");
//		
//		PointDeTrace lePoint = new PointDeTrace(23, 0, 48.15, -1.68, 50, laDate, 80);
//		String msg = PasserelleServicesWebXML.envoyerPosition("europa", Outils.sha1("mdputilisateurrrrrr"), lePoint);
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		lePoint = new PointDeTrace(2333, 0, 48.15, -1.68, 50, laDate, 80);
//		msg = PasserelleServicesWebXML.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
//		assertEquals("Erreur : le num�ro de trace n'existe pas !", msg);
//		
//		lePoint = new PointDeTrace(22, 0, 48.15, -1.68, 50, laDate, 80);
//		msg = PasserelleServicesWebXML.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
//		assertEquals("Erreur : le num�ro de trace ne correspond pas � cet utilisateur !", msg);	
//		
//		lePoint = new PointDeTrace(23, 0, 48.15, -1.68, 50, laDate, 80);
//		msg = PasserelleServicesWebXML.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
//		assertEquals("Point cr��.", msg);	
	}
	
	
	@Test
	public void testArreterEnregistrementParcours() {
//		String msg = PasserelleServicesWebXML.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateurrrrrr"), 23);
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWebXML.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 230);
//		assertEquals("Erreur : le num�ro de trace n'existe pas !", msg);
//		
//		msg = PasserelleServicesWebXML.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 4);
//		assertEquals("Erreur : cette trace est d�j� termin�e !", msg);	
//		
//		msg = PasserelleServicesWebXML.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 23);
//		assertEquals("Enregistrement termin�.", msg);	
	}
	
	
	@Test
	public void testSupprimerUneAutorisation() {
//		String msg = PasserelleServicesWebXML.supprimerUneAutorisation("europa", Outils.sha1("mdputilisateurrrrrr"), "toto");
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWebXML.supprimerUneAutorisation("europa", Outils.sha1("mdputilisateur"), "toto");
//		assertEquals("Erreur : utilisateur inexistant !", msg);
//		
//		msg = PasserelleServicesWebXML.supprimerUneAutorisation("europa", Outils.sha1("mdputilisateur"), "juno");
//		assertEquals("Erreur : l'autorisation n'�tait pas accord�e !", msg);	
//		
//		msg = PasserelleServicesWebXML.supprimerUneAutorisation("europa", Outils.sha1("mdputilisateur"), "galileo");
//		assertEquals("Autorisation supprim�e. L'int�ress� va recevoir un courriel de notification.", msg);	
	}
	
	
	@Test
	public void testSupprimerUnUnParcours() {
//		String msg = PasserelleServicesWebXML.supprimerUnParcours("europa", Outils.sha1("mdputilisateurrrrrr"), 10);
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWebXML.supprimerUnParcours("europa", Outils.sha1("mdputilisateur"), 100);
//		assertEquals("Erreur : parcours inexistant !", msg);
//		
//		msg = PasserelleServicesWebXML.supprimerUnParcours("europa", Outils.sha1("mdputilisateur"), 22);
//		assertEquals("Erreur : vous n'�tes pas le propri�taire du parcours !", msg);	
//		
//		msg = PasserelleServicesWebXML.supprimerUnParcours("europa", Outils.sha1("mdputilisateur"), 30);
//		assertEquals("Parcours supprim�.", msg);	
	}
	
	
	@Test
	public void testSupprimerUnUtilisateur() {
		String msg = PasserelleServicesWebXML.supprimerUnUtilisateur("europa", Outils.sha1("mdputilisateurrrrrr"), "toto");
		assertEquals("Erreur : authentification incorrecte !", msg);
		
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("europa", Outils.sha1("mdputilisateur"), "toto");
		assertEquals("Erreur : authentification incorrecte !", msg);
		
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("admin", Outils.sha1("mdpadminnnnn"), "toto");
		assertEquals("Erreur : authentification incorrecte !", msg);
		
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("admin", Outils.sha1("mdpadmin"), "toto");
		assertEquals("Erreur : utilisateur inexistant !", msg);
		
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("admin", Outils.sha1("mdpadmin"), "neon");
		assertEquals("Erreur : suppression impossible. Cet utilisateur poss�de encore des traces !", msg);	
		
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("admin", Outils.sha1("mdpadmin"), "photon");
		assertEquals("Suppression effectu�e. Un courriel va �tre envoy� � l'utilisateur.", msg);	
	}
	
	
	

	




} // fin du test
