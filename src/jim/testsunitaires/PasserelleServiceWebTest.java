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
//		assertEquals("Administrateur authentifi�.", msg);
//		
//		msg = PasserelleServicesWeb.connecter("europa", Outils.sha1("mdputilisateur"));
//		assertEquals("Utilisateur authentifi�.", msg);	
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
//		assertEquals("Erreur : le nouveau mot de passe et sa confirmation sont diff�rents !", msg);
//		
//		msg = PasserelleServicesWeb.changerDeMdp("europa", Outils.sha1("mdputilisateurrrr"), "passepasse", "passepasse");
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		msg = PasserelleServicesWeb.changerDeMdp("europa", Outils.sha1("mdputilisateur"), "mdputilisateurrrr", "mdputilisateurrrr");
//		assertEquals("Enregistrement effectu� ; vous allez recevoir un mail de confirmation.", msg);
//		
//		msg = PasserelleServicesWeb.changerDeMdp("europa", Outils.sha1("mdputilisateurrrr"), "mdputilisateur", "mdputilisateur");
//		assertEquals("Enregistrement effectu� ; vous allez recevoir un mail de confirmation.", msg);
	}	

	
	@Test
	public void testCreerUtilisateur() {
//		String msg = PasserelleServicesWeb.creerUtilisateur("jim", "delasalle.sio.eleves@gmail.com", "1122334455");
//		assertEquals("Erreur : pseudo trop court (8 car minimum) ou d�j� existant !", msg);
//		
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "delasalle.sio.elevesgmail.com", "1122334455");
//		assertEquals("Erreur : adresse mail incorrecte ou d�j� existante !", msg);
//
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "delasalle.sio.eleves@gmailcom", "1122334455");
//		assertEquals("Erreur : adresse mail incorrecte ou d�j� existante !", msg);
//		
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "delasalle.sio.eleves@gmail.com", "1122334455");
//		assertEquals("Erreur : adresse mail incorrecte ou d�j� existante !", msg);
//		
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "delasallesioeleves@gmail.com", "1122334455");
//		assertEquals("Enregistrement effectu�. Vous allez recevoir un courriel avec votre mot de passe.", msg);
//		
//		msg = PasserelleServicesWeb.creerUtilisateur("turlututu", "de.la.salle.sio.eleves@gmail.com", "1122334455");
//		assertEquals("Erreur : pseudo trop court (8 car minimum) ou d�j� existant !", msg);	
	}

	
	@Test
	public void testDemarrerEnregistrementParcours() {
//		Trace laTrace = new Trace();
//		String msg = PasserelleServicesWeb.demarrerEnregistrementParcours("europa", Outils.sha1("mdputilisateurrrrrr"), laTrace);
//		assertEquals("Erreur : authentification incorrecte !", msg);
//		
//		laTrace = new Trace();
//		msg = PasserelleServicesWeb.demarrerEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), laTrace);
//		assertEquals("Trace cr��e.", msg);	
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
//		assertEquals("Erreur : le num�ro de trace n'existe pas !", msg);
//		
//		lePoint = new PointDeTrace(22, 0, 48.15, -1.68, 50, laDate, 80);
//		msg = PasserelleServicesWeb.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
//		assertEquals("Erreur : le num�ro de trace ne correspond pas � cet utilisateur !", msg);	
//		
//		lePoint = new PointDeTrace(23, 0, 48.15, -1.68, 50, laDate, 80);
//		msg = PasserelleServicesWeb.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
//		assertEquals("Point cr��.", msg);	
	}
	
	
	@Test
	public void testArreterEnregistrementParcours() {
		String msg = PasserelleServicesWeb.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateurrrrrr"), 23);
		assertEquals("Erreur : authentification incorrecte !", msg);
		
		msg = PasserelleServicesWeb.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 230);
		assertEquals("Erreur : le num�ro de trace n'existe pas !", msg);
		
		msg = PasserelleServicesWeb.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 4);
		assertEquals("Erreur : cette trace est d�j� termin�e !", msg);	
		
		msg = PasserelleServicesWeb.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 23);
		assertEquals("Enregistrement termin�.", msg);	
	}
	
	
	
	
	
	

	
	@Test
	public void testConsulterReservations() {
//		Utilisateur unUtilisateur = new Utilisateur(0, 0, "giboired", "passeeeeeeeeeee", "");
//		String msg = Passerelle.consulterReservations(unUtilisateur);
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 0, "giboired", "passe", "");
//		msg = Passerelle.consulterReservations(unUtilisateur);
//		assertEquals("Erreur : vous n'avez aucune r�servation.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 0, "jim", "passe", "");
//		msg = Passerelle.consulterReservations(unUtilisateur);
//		assertEquals("Vous avez effectu� 2 r�servation(s).", msg);
//		assertEquals(2, unUtilisateur.getNbReservations());
//		
//		String formatUS = "yyyy-MM-dd HH:mm:ss";
//		Reservation laReservation = unUtilisateur.getLaReservation(0);
//		assertEquals("Amphith��tre", laReservation.getRoomName());		
//		assertEquals(0, laReservation.getStatus());	
//		assertEquals("2015-06-21 18:00:00", Outils.formaterDateHeureUS(laReservation.getStartTime()));
//		assertEquals("2015-06-22 00:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
//		
//		laReservation = unUtilisateur.getLaReservation(1);
//		assertEquals("Hall d'accueil", laReservation.getRoomName());		
//		assertEquals(4, laReservation.getStatus());	
//		assertEquals("2015-06-21 18:00:00", FormaterDateHeure(laReservation.getStartTime(), formatUS));
//		assertEquals("2015-06-22 00:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
	}



	@Test
	public void testConfirmerReservation() {
//		String msg = Passerelle.confirmerReservation("jim", "passeeeeeeeeee", "3");
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "3");
//		assertEquals("Erreur : num�ro de r�servation inexistant.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "11");
//		assertEquals("Erreur : vous n'�tes pas l'auteur de cette r�servation.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "13");
//		assertEquals("Erreur : cette r�servation est d�j� confirm�e.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "12");
//		assertEquals("Erreur : cette r�servation est d�j� pass�e.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "14");
//		assertEquals("Enregistrement effectu� ; vous allez recevoir un mail de confirmation.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "14");
//		assertEquals("Erreur : cette r�servation est d�j� confirm�e.", msg);
	}
	
	@Test
	public void testAnnulerReservation() {
//		String msg = Passerelle.annulerReservation("jim", "passeeeeeeeeee", "3");
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "3");
//		assertEquals("Erreur : num�ro de r�servation inexistant.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "11");
//		assertEquals("Erreur : vous n'�tes pas l'auteur de cette r�servation.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "12");
//		assertEquals("Erreur : cette r�servation est d�j� pass�e.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "15");
//		assertEquals("Enregistrement effectu� ; vous allez recevoir un mail de confirmation.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "15");
//		assertEquals("Erreur : num�ro de r�servation inexistant.", msg);
	}


	
	@Test
	public void testSupprimerUtilisateur() {
//		// utilisation d'un compte inexistant
//		String msg = Passerelle.supprimerUtilisateur("admin", "adminnnnnnnn", "jim3");
//		assertEquals("Erreur : authentification incorrecte.", msg);
//
//		// utilisation d'un compte existant, mais n'ayant pas le niveau administrateur
//		msg = Passerelle.supprimerUtilisateur("jim", "passe", "jim3");
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		// essayer de supprimer un compte inexistant
//		msg = Passerelle.supprimerUtilisateur("admin", "admin", "jim3");
//		assertEquals("Erreur : nom d'utilisateur inexistant.", msg);
//
//		// essayer de supprimer un compte existant mais ayant effectu� des r�servations � venir
//		msg = Passerelle.supprimerUtilisateur("admin", "admin", "jim");
//		assertEquals("Erreur : cet utilisateur a pass� des r�servations � venir.", msg);
//	
//		// essayer de supprimer un compte existant n'ayant pas effectu� de r�servations � venir
//		msg = Passerelle.supprimerUtilisateur("admin", "admin", "jim2");
//		assertEquals("Enregistrement effectu�.", msg);		
	}

} // fin du test
