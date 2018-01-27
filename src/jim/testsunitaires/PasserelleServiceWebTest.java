package jim.testsunitaires;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import jim.classes.Outils;
import jim.classes.PasserelleServicesWeb;
import jim.classes.Point;
import jim.classes.PointDeTrace;
import jim.classes.Trace;
import jim.classes.Utilisateur;

public class PasserelleServiceWebTest {

	@Test
	public void testConnecter() {
//		String msg = PasserelleServicesWeb.connecter("admin", "adminnnnnnnn");
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWeb.connecter("admin", Outils.sha1("mdpadmin"));
//		assertEquals("Administrateur authentifié.", msg);
//		
//		msg = PasserelleServicesWeb.connecter("europa", Outils.sha1("mdputilisateur"));
//		assertEquals("Utilisateur authentifié.", msg);	
	}
	
	
	@Test
	public void testDemanderMdp() {
//		String msg = PasserelleServicesWeb.demanderMdp("jim");
//		assertEquals("Erreur : pseudo inexistant !", msg);
//		
//		msg = PasserelleServicesWeb.demanderMdp("europa");
//		assertEquals("Vous allez recevoir un courriel avec votre nouveau mot de passe.", msg);
	}	

	
	@Test
	public void testChangerDeMdp() {
//		String msg = PasserelleServicesWeb.changerDeMdp("europa", Outils.sha1("mdputilisateur"), "passepasse", "passepassepasse");
//		assertEquals("Erreur : le nouveau mot de passe et sa confirmation sont différents !", msg);
//		
//		msg = PasserelleServicesWeb.changerDeMdp("europa", Outils.sha1("mdputilisateurrrr"), "passepasse", "passepasse");
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWeb.changerDeMdp("europa", Outils.sha1("mdputilisateur"), "mdputilisateurrrr", "mdputilisateurrrr");
//		assertEquals("Enregistrement effectué ; vous allez recevoir un mail de confirmation.", msg);
//		
//		msg = PasserelleServicesWeb.changerDeMdp("europa", Outils.sha1("mdputilisateurrrr"), "mdputilisateur", "mdputilisateur");
//		assertEquals("Enregistrement effectué ; vous allez recevoir un mail de confirmation.", msg);
	}	

	
	@Test
	public void testCreerUtilisateur() {
//		String msg = PasserelleServicesWeb.creerUtilisateur("jim", "delasalle.sio.eleves@gmail.com", "1122334455");
//		assertEquals("Erreur : pseudo trop court (8 car minimum) ou déjà existant !", msg);
//		
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "delasalle.sio.elevesgmail.com", "1122334455");
//		assertEquals("Erreur : adresse mail incorrecte ou déjà existante !", msg);
//
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "delasalle.sio.eleves@gmailcom", "1122334455");
//		assertEquals("Erreur : adresse mail incorrecte ou déjà existante !", msg);
//		
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "delasalle.sio.eleves@gmail.com", "1122334455");
//		assertEquals("Erreur : adresse mail incorrecte ou déjà existante !", msg);
//		
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "delasallesioeleves@gmail.com", "1122334455");
//		assertEquals("Enregistrement effectué. Vous allez recevoir un courriel avec votre mot de passe.", msg);
//		
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "de.la.salle.sio.eleves@gmail.com", "1122334455");
//		assertEquals("Erreur : pseudo trop court (8 car minimum) ou déjà existant !", msg);	
	}

	
	@Test
	public void testDemarrerEnregistrementParcours() {
//		Trace laTrace = new Trace();
//		String msg = PasserelleServicesWeb.demarrerEnregistrementParcours("europa", Outils.sha1("mdputilisateurrrrrr"), laTrace);
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		laTrace = new Trace();
//		msg = PasserelleServicesWeb.demarrerEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), laTrace);
//		assertEquals("Trace créée.", msg);	
	}
	

	@Test
	public void testEnvoyerPosition() throws ParseException {
//		Date laDate = Outils.convertirEnDateHeure("24/01/2018 13:42:21");
//		
//		PointDeTrace lePoint = new PointDeTrace(23, 0, 48.15, -1.68, 50, laDate, 80);
//		String msg = PasserelleServicesWeb.envoyerPosition("europa", Outils.sha1("mdputilisateurrrrrr"), lePoint);
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		lePoint = new PointDeTrace(2333, 0, 48.15, -1.68, 50, laDate, 80);
//		msg = PasserelleServicesWeb.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
//		assertEquals("Erreur : le numéro de trace n'existe pas !", msg);
//		
//		lePoint = new PointDeTrace(22, 0, 48.15, -1.68, 50, laDate, 80);
//		msg = PasserelleServicesWeb.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
//		assertEquals("Erreur : le numéro de trace ne correspond pas à cet utilisateur !", msg);	
//		
//		lePoint = new PointDeTrace(23, 0, 48.15, -1.68, 50, laDate, 80);
//		msg = PasserelleServicesWeb.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
//		assertEquals("Point créé.", msg);	
	}
	
	
	@Test
	public void testArreterEnregistrementParcours() {
//		String msg = PasserelleServicesWeb.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateurrrrrr"), 23);
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWeb.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 230);
//		assertEquals("Erreur : le numéro de trace n'existe pas !", msg);
//		
//		msg = PasserelleServicesWeb.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 4);
//		assertEquals("Erreur : cette trace est déjà terminée !", msg);	
//		
//		msg = PasserelleServicesWeb.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 23);
//		assertEquals("Enregistrement terminé.", msg);	
	}
	
	
	@Test
	public void testSupprimerUneAutorisation() {
//		String msg = PasserelleServicesWeb.supprimerUneAutorisation("europa", Outils.sha1("mdputilisateurrrrrr"), "toto");
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWeb.supprimerUneAutorisation("europa", Outils.sha1("mdputilisateur"), "toto");
//		assertEquals("Erreur : utilisateur inexistant !", msg);
//		
//		msg = PasserelleServicesWeb.supprimerUneAutorisation("europa", Outils.sha1("mdputilisateur"), "juno");
//		assertEquals("Erreur : l'autorisation n'était pas accordée !", msg);	
//		
//		msg = PasserelleServicesWeb.supprimerUneAutorisation("europa", Outils.sha1("mdputilisateur"), "galileo");
//		assertEquals("Autorisation supprimée. L'intéressé va recevoir un courriel de notification.", msg);	
	}
	
	
	@Test
	public void testSupprimerUnUnParcours() {
//		String msg = PasserelleServicesWeb.supprimerUnParcours("europa", Outils.sha1("mdputilisateurrrrrr"), 10);
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWeb.supprimerUnParcours("europa", Outils.sha1("mdputilisateur"), 100);
//		assertEquals("Erreur : parcours inexistant !", msg);
//		
//		msg = PasserelleServicesWeb.supprimerUnParcours("europa", Outils.sha1("mdputilisateur"), 22);
//		assertEquals("Erreur : vous n'êtes pas le propriétaire du parcours !", msg);	
//		
//		msg = PasserelleServicesWeb.supprimerUnParcours("europa", Outils.sha1("mdputilisateur"), 30);
//		assertEquals("Parcours supprimé.", msg);	
	}
	
	
	@Test
	public void testSupprimerUnUtilisateur() {
		String msg = PasserelleServicesWeb.supprimerUnUtilisateur("europa", Outils.sha1("mdputilisateurrrrrr"), "toto");
		assertEquals("Erreur : authentification incorrecte !", msg);
		
		msg = PasserelleServicesWeb.supprimerUnUtilisateur("europa", Outils.sha1("mdputilisateur"), "toto");
		assertEquals("Erreur : authentification incorrecte !", msg);
		
		msg = PasserelleServicesWeb.supprimerUnUtilisateur("admin", Outils.sha1("mdpadminnnnn"), "toto");
		assertEquals("Erreur : authentification incorrecte !", msg);
		
		msg = PasserelleServicesWeb.supprimerUnUtilisateur("admin", Outils.sha1("mdpadmin"), "toto");
		assertEquals("Erreur : utilisateur inexistant !", msg);
		
		msg = PasserelleServicesWeb.supprimerUnUtilisateur("admin", Outils.sha1("mdpadmin"), "neon");
		assertEquals("Erreur : suppression impossible. Cet utilisateur possède encore des traces !", msg);	
		
		msg = PasserelleServicesWeb.supprimerUnUtilisateur("admin", Outils.sha1("mdpadmin"), "photon");
		assertEquals("Suppression effectuée. Un courriel va être envoyé à l'utilisateur.", msg);	
	}
	
	
	

	




} // fin du test
