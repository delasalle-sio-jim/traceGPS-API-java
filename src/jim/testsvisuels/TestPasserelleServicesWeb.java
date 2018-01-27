package jim.testsvisuels;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import jim.classes.Outils;
import jim.classes.PasserelleServicesWeb;
import jim.classes.PointDeTrace;
import jim.classes.Trace;
import jim.classes.Utilisateur;

public class TestPasserelleServicesWeb {

	public static void main(String[] args) throws ParseException {
		
		String msg;
		
//		// test visuel de la méthode demarrerEnregistrementParcours
//		Trace laTrace = new Trace();
//		msg = PasserelleServicesWeb.demarrerEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), laTrace);
//		// affichage de la réponse
//		System.out.println(msg);
//		// affichage de la trace
//		System.out.println(laTrace.toString());
		
		
//		// test visuel de la méthode envoyerPosition
//		Date laDate = Outils.convertirEnDateHeure("24/01/2018 13:42:21");
//		PointDeTrace lePoint = new PointDeTrace(23, 0, 48.15, -1.68, 50, laDate, 80);
//		msg = PasserelleServicesWeb.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
//		// affichage de la réponse
//		System.out.println(msg);
//		// affichage du point (avec sin id mis à jour)
//		System.out.println(lePoint.toString());
		
		
//		// test visuel de la méthode getTousLesUtilisateurs
//		ArrayList<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();
//		msg = PasserelleServicesWeb.getTousLesUtilisateurs("europa", Outils.sha1("mdputilisateur"), lesUtilisateurs);
//		// affichage de la réponse
//		System.out.println(msg);
//		// affichage du nombre d'utilisateurs
//		System.out.println("Nombre d'utilisateurs : " + lesUtilisateurs.size());
//		// affichage de tous les utilisateurs
//		for (Utilisateur unUtilisateur : lesUtilisateurs)
//		{	System.out.println(unUtilisateur.toString());
//		}

		
//		// test visuel de la méthode getLesUtilisateursQueJautorise
//		ArrayList<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();
//		msg = PasserelleServicesWeb.getLesUtilisateursQueJautorise("europa", Outils.sha1("mdputilisateur"), lesUtilisateurs);
//		// affichage de la réponse
//		System.out.println(msg);
//		// affichage du nombre d'utilisateurs
//		System.out.println("Nombre d'utilisateurs : " + lesUtilisateurs.size());
//		// affichage de tous les utilisateurs
//		for (Utilisateur unUtilisateur : lesUtilisateurs)
//		{	System.out.println(unUtilisateur.toString());
//		}
	
		
//		// test visuel de la méthode getLesUtilisateursQuiMautorisent
//		ArrayList<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();
//		msg = PasserelleServicesWeb.getLesUtilisateursQuiMautorisent("europa", Outils.sha1("mdputilisateur"), lesUtilisateurs);
//		// affichage de la réponse
//		System.out.println(msg);
//		// affichage du nombre d'utilisateurs
//		System.out.println("Nombre d'utilisateurs : " + lesUtilisateurs.size());
//		// affichage de tous les utilisateurs
//		for (Utilisateur unUtilisateur : lesUtilisateurs)
//		{	System.out.println(unUtilisateur.toString());
//		}
		
		
//		// test visuel de la méthode getUnParcoursEtSesPoints
//		Trace laTrace = new Trace();
//		msg = PasserelleServicesWeb.getUnParcoursEtSesPoints("europa", Outils.sha1("mdputilisateur"), 2, laTrace);
//		// affichage de la réponse
//		System.out.println(msg);
//		// affichage de la trace
//		System.out.println(laTrace.toString());

		
		// test visuel de la méthode getLesParcoursDunUtilisateur
		ArrayList<Trace> lesTraces = new ArrayList<Trace>();
		msg = PasserelleServicesWeb.getLesParcoursDunUtilisateur("europa", Outils.sha1("mdputilisateur"), 4, lesTraces);
		// affichage de la réponse
		System.out.println(msg);
		// affichage du nombre de traces
		System.out.println("Nombre de traces : " + lesTraces.size());
		// affichage de tous les utilisateurs
		for (Trace uneTrace : lesTraces)
		{	System.out.println(uneTrace.toString());
		}
		
		
		
		
	} // fin Main
} // fin class
