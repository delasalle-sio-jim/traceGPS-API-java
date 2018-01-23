package jim.tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import jim.classes.Outils;
import jim.classes.PasserelleServicesWeb;
import jim.classes.Point;
import jim.classes.PointDeTrace;
import jim.classes.Trace;
import jim.classes.Utilisateur;

public class PasserelleTest {

	@Test
	public void testConnecter() {
		String msg = PasserelleServicesWeb.connecter("admin", "adminnnnnnnn");
		assertEquals("Test Passerelle.connecter", "Erreur : authentification incorrecte !", msg);
		
		msg = PasserelleServicesWeb.connecter("admin", Outils.sha1("mdpadmin"));
		assertEquals("Test Passerelle.connecter", "Administrateur authentifié.", msg);
		
		msg = PasserelleServicesWeb.connecter("europa", Outils.sha1("mdputilisateur"));
		assertEquals("Test Passerelle.connecter", "Utilisateur authentifié.", msg);	
	}

	@Test
	public void testCreerUtilisateur() {
//		Utilisateur unUtilisateur = new Utilisateur(0, 4, "yvesz", "", "yves.zenels@gmail.com");
//		String msg = Passerelle.creerUtilisateur("admin", "adminnnnnnnn", unUtilisateur);
//		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : le niveau doit être 0, 1 ou 2.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
//		msg = Passerelle.creerUtilisateur("admin", "adminnnnnnnn", unUtilisateur);
//		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : authentification incorrecte.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
//		msg = Passerelle.creerUtilisateur("admin", "admin", unUtilisateur);
//		assertEquals("Test Passerelle.creerUtilisateur", "Enregistrement effectué.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
//		msg = Passerelle.creerUtilisateur("admin", "admin", unUtilisateur);
//		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : nom d'utilisateur déjà existant.", msg);	
	}

	private static String FormaterDateHeure(Date uneDate, String unFormat) {
		SimpleDateFormat leFormat = new SimpleDateFormat(unFormat);
		return leFormat.format(uneDate);
	}
	
	@Test
	public void testConsulterReservations() {
//		Utilisateur unUtilisateur = new Utilisateur(0, 0, "giboired", "passeeeeeeeeeee", "");
//		String msg = Passerelle.consulterReservations(unUtilisateur);
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 0, "giboired", "passe", "");
//		msg = Passerelle.consulterReservations(unUtilisateur);
//		assertEquals("Erreur : vous n'avez aucune réservation.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 0, "jim", "passe", "");
//		msg = Passerelle.consulterReservations(unUtilisateur);
//		assertEquals("Vous avez effectué 2 réservation(s).", msg);
//		assertEquals(2, unUtilisateur.getNbReservations());
//		
//		String formatUS = "yyyy-MM-dd HH:mm:ss";
//		Reservation laReservation = unUtilisateur.getLaReservation(0);
//		assertEquals("Amphithéâtre", laReservation.getRoomName());		
//		assertEquals(0, laReservation.getStatus());	
//		assertEquals("2015-06-21 18:00:00", FormaterDateHeure(laReservation.getStartTime(), formatUS));
//		assertEquals("2015-06-22 00:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
//		
//		laReservation = unUtilisateur.getLaReservation(1);
//		assertEquals("Hall d'accueil", laReservation.getRoomName());		
//		assertEquals(4, laReservation.getStatus());	
//		assertEquals("2015-06-21 18:00:00", FormaterDateHeure(laReservation.getStartTime(), formatUS));
//		assertEquals("2015-06-22 00:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
	}

	@Test
	public void testDemanderMdp() {
//		String msg = Passerelle.demanderMdp("jim2");
//		assertEquals("Erreur : nom d'utilisateur inexistant.", msg);
//		
//		msg = Passerelle.demanderMdp("jim1");
//		assertEquals("Vous allez recevoir un mail avec votre nouveau mot de passe.", msg);
	}	

	@Test
	public void testConfirmerReservation() {
//		String msg = Passerelle.confirmerReservation("jim", "passeeeeeeeeee", "3");
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "3");
//		assertEquals("Erreur : numéro de réservation inexistant.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "11");
//		assertEquals("Erreur : vous n'êtes pas l'auteur de cette réservation.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "13");
//		assertEquals("Erreur : cette réservation est déjà confirmée.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "12");
//		assertEquals("Erreur : cette réservation est déjà passée.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "14");
//		assertEquals("Enregistrement effectué ; vous allez recevoir un mail de confirmation.", msg);
//		
//		msg = Passerelle.confirmerReservation("jim", "passe", "14");
//		assertEquals("Erreur : cette réservation est déjà confirmée.", msg);
	}
	
	@Test
	public void testAnnulerReservation() {
//		String msg = Passerelle.annulerReservation("jim", "passeeeeeeeeee", "3");
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "3");
//		assertEquals("Erreur : numéro de réservation inexistant.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "11");
//		assertEquals("Erreur : vous n'êtes pas l'auteur de cette réservation.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "12");
//		assertEquals("Erreur : cette réservation est déjà passée.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "15");
//		assertEquals("Enregistrement effectué ; vous allez recevoir un mail de confirmation.", msg);
//		
//		msg = Passerelle.annulerReservation("jim", "passe", "15");
//		assertEquals("Erreur : numéro de réservation inexistant.", msg);
	}

	
	@Test
	public void testChangerDeMdp() {
//		String msg = Passerelle.changerDeMdp("jim", "passeeee", "passepasse", "passepassepasse");
//		assertEquals("Erreur : le nouveau mot de passe et sa confirmation sont différents.", msg);
//		
//		msg = Passerelle.changerDeMdp("jim", "passeeee", "passepasse", "passepasse");
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		msg = Passerelle.changerDeMdp("jim", "passe", "passepasse", "passepasse");
//		assertEquals("Enregistrement effectué ; vous allez recevoir un mail de confirmation.", msg);
//		
//		msg = Passerelle.changerDeMdp("jim", "passe", "passepasse", "passepasse");
//		assertEquals("Erreur : authentification incorrecte.", msg);	
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
//		// essayer de supprimer un compte existant mais ayant effectué des réservations à venir
//		msg = Passerelle.supprimerUtilisateur("admin", "admin", "jim");
//		assertEquals("Erreur : cet utilisateur a passé des réservations à venir.", msg);
//	
//		// essayer de supprimer un compte existant n'ayant pas effectué de réservations à venir
//		msg = Passerelle.supprimerUtilisateur("admin", "admin", "jim2");
//		assertEquals("Enregistrement effectué.", msg);		
	}

} // fin du test
