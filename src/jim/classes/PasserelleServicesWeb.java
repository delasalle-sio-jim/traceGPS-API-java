// Projet TraceGPS - API Java
// Fichier : PasserelleServicesWeb.java
// Cette classe hérite de la classe Passerelle
// Elle fournit des méthodes pour appeler les différents services web
// Elle utilise le modèle Jaxp pour parcourir le document XML
// Le modèle Jaxp fait partie du JDK (et également du SDK Android)
// Dernière mise à jour : 23/1/2018 par Jim

package jim.classes;

import java.util.ArrayList;
import java.util.Date;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class PasserelleServicesWeb extends Passerelle {

	// attributs privés
	
	// Adresse de l'hébergeur Internet
	private static String _adresseHebergeur = "http://jean.michel.cartron.free.fr/tracegps/services/";
	// Adresse du localhost en cas d'exécution sur le poste de développement (projet de tests des classes)
	//private static String _adresseHebergeur = "http://127.0.0.1/ws-php-cartron/tracegps/services/";
	
	// Noms des services web déjà traités par la passerelle
	private static String _urlConnecter = "Connecter.php";
	private static String _urlDemanderMdp = "DemanderMdp.php";
	private static String _urlChangerDeMdp = "ChangerDeMdp.php";
	private static String _urlCreerUtilisateur = "CreerUtilisateur.php";
	private static String _urlDemarrerEnregistrementParcours = "DemarrerEnregistrementParcours.php";
	
	// noms des services web pas encore traités par la passerelle (à développer)
	private static String _urlArreterEnregistrementParcours = "ArreterEnregistrementParcours.php";
    private static String _urlEnvoyerPosition = "EnvoyerPosition.php";
	private static String _urlGetLesParcoursDunUtilisateur = "GetLesParcoursDunUtilisateur.php";
	private static String _urlGetLesUtilisateursQueJautorise = "GetLesUtilisateursQueJautorise.php";
	private static String _urlGetLesUtilisateursQuiMautorisent = "GetLesUtilisateursQuiMautorisent.php";	
	private static String _urlGetTousLesUtilisateurs = "GetTousLesUtilisateurs.php";	
	private static String _urlGetUnParcoursEtSesPoints = "GetUnParcoursEtSesPoints.php";
	private static String _urlSupprimerUneAutorisation = "SupprimerUneAutorisation.php";
	private static String _urlSupprimerUnParcours = "SupprimerUnParcours.php";	
	private static String _urlSupprimerUnUtilisateur = "SupprimerUnUtilisateur.php";
	private static String _urlValiderDemandeAutorisation = "ValiderDemandeAutorisation.php";
	
    // Méthode statique pour se connecter (service Connecter.php)
	// Le service web doit recevoir 2 paramètres :
	//    pseudo : le pseudo de l'utilisateur
	//    mdpSha1 : le mot de passe hashé en sha1
    public static String connecter(String pseudo, String mdpSha1)
    {
    	String reponse = "";
    	try
    	{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConnecter;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
    		
			// création d'un flux en lecture (InputStream) à partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// création d'un objet org.w3c.dom.Document à partir du flux ; il servira à parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la réponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }

    
	// Méthode statique pour demander un nouveau mot de passe (service DemanderMdp.php)
	// Le service web doit recevoir 1 paramètre :
	//    pseudo : le pseudo de l'utilisateur
	public static String demanderMdp(String pseudo)
	{
		String reponse = "";
		try
		{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
			String urlDuServiceWeb = _adresseHebergeur + _urlDemanderMdp;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			
			// création d'un flux en lecture (InputStream) à partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// création d'un objet org.w3c.dom.Document à partir du flux ; il servira à parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
			
			// parsing du flux XML
			Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
			reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
			
			// retour de la réponse du service web
			return reponse;
		}
		catch (Exception ex)
		{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
	}
    
	
    // Méthode statique pour modifier son mot de passe (service ChangerDeMdp.php)
	// Le service web doit recevoir 4 paramètres :
	//    pseudo : le pseudo de l'utilisateur
	//    mdpSha1 : le mot de passe hashé en sha1
	//    nouveauMdp : le nouveau mot de passe
	//    confirmationMdp : la confirmation du nouveau mot de passe
    public static String changerDeMdp(String pseudo, String mdpSha1, String nouveauMdp, String confirmationMdp)
    {
    	String reponse = "";
    	try
    	{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlChangerDeMdp;
            urlDuServiceWeb += "?pseudo=" + pseudo;
            urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
            urlDuServiceWeb += "&nouveauMdp=" + nouveauMdp;
            urlDuServiceWeb += "&confirmationMdp=" + confirmationMdp;
			
			// création d'un flux en lecture (InputStream) à partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// création d'un objet org.w3c.dom.Document à partir du flux ; il servira à parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la réponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    
    // Méthode statique pour créer un utilisateur (service CreerUtilisateur.php)
	// Le service web doit recevoir 3 paramètres :
	//   pseudo : le pseudo de l'utilisateur
	//   adrMail : son adresse mail
	//   numTel : son numéro de téléphone
    public static String creerUtilisateur(String pseudo, String adrMail, String numTel)
    {
    	String reponse = "";
    	try
    	{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlCreerUtilisateur;
            urlDuServiceWeb += "?pseudo=" + pseudo;
            urlDuServiceWeb += "&adrMail=" + adrMail;
            urlDuServiceWeb += "&numTel=" + numTel;

			// création d'un flux en lecture (InputStream) à partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// création d'un objet org.w3c.dom.Document à partir du flux ; il servira à parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la réponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }

    
    // Méthode statique pour démarrer l'enregistrement d'un parcours (service DemarrerEnregistrementParcours.php)
	// Le service web doit recevoir 2 paramètres :
	//    pseudo : le pseudo de l'utilisateur
	//    mdpSha1 : le mot de passe hashé en sha1
    public static String demarrerEnregistrementParcours(String pseudo, String mdpSha1)
    {
    	String reponse = "";
    	try
    	{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlDemarrerEnregistrementParcours;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
    		
			// création d'un flux en lecture (InputStream) à partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// création d'un objet org.w3c.dom.Document à partir du flux ; il servira à parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la réponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }  
    
    
    
    
    
    
    
    
//    // Méthode statique pour récupérer les réservations d'un utilisateur (service ConsulterReservations.php)
//    public static String consulterReservations(Utilisateur unUtilisateur)
//    {
//    	String reponse = "";
//    	try
//    	{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
//    		String urlDuServiceWeb = _adresseHebergeur + _urlConsulterReservations;
//            urlDuServiceWeb += "?nom=" + unUtilisateur.getName();
//            urlDuServiceWeb += "&mdp=" + unUtilisateur.getPassword();
//    		Document leDocument = getDocumentXML(urlDuServiceWeb);
//
//    		// parsing du flux XML
//    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
//
//    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
//    		
//			NodeList listeNoeudsMembres = leDocument.getElementsByTagName("reservation");
//			/* Exemple de données obtenues pour un utilisateur :
//			    <reservation>
//			      <id>1</id>
//			      <timestamp>2014-09-11 22:20:54</timestamp>
//			      <start_time>2014-10-01 09:00:00</start_time>
//			      <end_time>2014-10-01 12:00:00</end_time>
//			      <room_name>Multimédia</room_name>
//			      <status>4</status>
//			      <digicode></digicode>
//			    </reservation>
//			 */
//			String formatUS = "yyyy-MM-dd HH:mm:ss";
//			
//			// parcours de la liste des noeuds <reservation> et ajout dans l'objet unUtilisateur
//			for (int i = 0 ; i <= listeNoeudsMembres.getLength()-1 ; i++)
//			{	// création de l'élement courant à chaque tour de boucle
//				Element courant = (Element) listeNoeudsMembres.item(i);
//				
//				// lecture des balises intérieures
//				int id = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
//				Date timestamp = Outils.ConvertirEnDate(courant.getElementsByTagName("timestamp").item(0).getTextContent(), formatUS);
//				Date start_time = Outils.ConvertirEnDate(courant.getElementsByTagName("start_time").item(0).getTextContent(), formatUS);
//				Date end_time = Outils.ConvertirEnDate(courant.getElementsByTagName("end_time").item(0).getTextContent(), formatUS);
//				String room_name = courant.getElementsByTagName("room_name").item(0).getTextContent();
//				int status = Integer.parseInt(courant.getElementsByTagName("status").item(0).getTextContent());
//				String digicode = courant.getElementsByTagName("digicode").item(0).getTextContent();
//				
//				// crée un objet Reservation
//				Reservation uneReservation = new Reservation(id, timestamp, start_time, end_time, room_name, status, digicode);
//				
//				// ajoute la réservation à l'objet unUtilisateur
//				unUtilisateur.ajouteReservation(uneReservation);
//			}
//
//    		// retour de la réponse du service web
//    		return reponse;
//    	}
//    	catch (Exception ex)
//    	{	String msg = "Erreur : " + ex.getMessage();
//			return msg;
//		}
//    }

    
//    // Méthode statique pour récupérer la liste des salles(service ConsulterSalles.php)
//    public static String consulterSalles(String nomUtilisateur, String mdpUtilisateur, ArrayList<Salle> lesSalles)
//    {
//    	String reponse = "";
//    	try
//    	{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
//    		String urlDuServiceWeb = _adresseHebergeur + _urlConsulterSalles;
//            urlDuServiceWeb += "?nom=" + nomUtilisateur;
//            urlDuServiceWeb += "&mdp=" + mdpUtilisateur;
//    		Document leDocument = getDocumentXML(urlDuServiceWeb);
//
//    		// parsing du flux XML
//    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
//
//    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
//    		
//			NodeList listeNoeudsSalles = leDocument.getElementsByTagName("salle");
//			/* Exemple de données obtenues pour une salle :
//			    <salle>
//			      <id>5</id>
//			      <room_name>Multimédia</room_name>
//			      <capacity>25</capacity>
//			      <area_name>Informatique - multimédia</area_name>
//			    </salle>
//			 */
//			
//			// parcours de la liste des noeuds <salle> et ajout dans la collection lesSalles
//			for (int i = 0 ; i <= listeNoeudsSalles.getLength()-1 ; i++)
//			{	// création de l'élement courant à chaque tour de boucle
//				Element courant = (Element) listeNoeudsSalles.item(i);
//				
//				// lecture des balises intérieures
//				int id = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
//				String room_name = courant.getElementsByTagName("room_name").item(0).getTextContent();
//				int capacity = Integer.parseInt(courant.getElementsByTagName("capacity").item(0).getTextContent());
//				String area_name = courant.getElementsByTagName("area_name").item(0).getTextContent();
//				
//				// crée un objet Salle
//				Salle uneSalle = new Salle(id, room_name, capacity, area_name);
//				
//				// ajoute la salle à la collection lesSalles
//				lesSalles.add(uneSalle);
//			}
//
//    		// retour de la réponse du service web
//    		return reponse;
//    	}
//    	catch (Exception ex)
//    	{	String msg = "Erreur : " + ex.getMessage();
//			return msg;
//		}
//    }
    
    


    
//    // Méthode statique pour confirmer une réservation (service ConfirmerReservation.php)
//    public static String confirmerReservation(String nomUtilisateur, String mdpUtilisateur, String numReservation)
//    {
//    	String reponse = "";
//    	try
//    	{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
//    		String urlDuServiceWeb = _adresseHebergeur + _urlConfirmerReservation;
//            urlDuServiceWeb += "?nom=" + nomUtilisateur;
//            urlDuServiceWeb += "&mdp=" + mdpUtilisateur;
//            urlDuServiceWeb += "&numreservation=" + numReservation;
//    		Document leDocument = getDocumentXML(urlDuServiceWeb);
//    		
//    		// parsing du flux XML
//    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
//    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
//    		
//    		// retour de la réponse du service web
//    		return reponse;
//    	}
//    	catch (Exception ex)
//    	{	String msg = "Erreur : " + ex.getMessage();
//			return msg;
//		}
//    }
  
    
//    // Méthode statique pour annuler une réservation (service AnnulerReservation.php)
//    public static String annulerReservation(String nomUtilisateur, String mdpUtilisateur, String numReservation)
//    {
//    	String reponse = "";
//    	try
//    	{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
//    		String urlDuServiceWeb = _adresseHebergeur + _urlAnnulerReservation;
//            urlDuServiceWeb += "?nom=" + nomUtilisateur;
//            urlDuServiceWeb += "&mdp=" + mdpUtilisateur;
//            urlDuServiceWeb += "&numreservation=" + numReservation;
//    		Document leDocument = getDocumentXML(urlDuServiceWeb);
//    		
//    		// parsing du flux XML
//    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
//    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
//    		
//    		// retour de la réponse du service web
//    		return reponse;
//    	}
//    	catch (Exception ex)
//    	{	String msg = "Erreur : " + ex.getMessage();
//			return msg;
//		}
//    }

    

 
    
//    // Méthode statique pour supprimer un utilisateur (service SupprimerUtilisateur.php)
//    public static String supprimerUtilisateur(String nomAdmin, String mdpAdmin, String name)
//    {
//    	String reponse = "";
//    	try
//    	{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
//    		String urlDuServiceWeb = _adresseHebergeur + _urlSupprimerUtilisateur;
//            urlDuServiceWeb += "?nomAdmin=" + nomAdmin;
//            urlDuServiceWeb += "&mdpAdmin=" + mdpAdmin;
//            urlDuServiceWeb += "&name=" + name;
//    		Document leDocument = getDocumentXML(urlDuServiceWeb);
//    		
//    		// parsing du flux XML
//    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
//    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
//    		
//    		// retour de la réponse du service web
//    		return reponse;
//    	}
//    	catch (Exception ex)
//    	{	String msg = "Erreur : " + ex.getMessage();
//			return msg;
//		}
//    }

    
//	// Méthode statique pour récupérer la liste des échecs de connexion(service ConsulterEchecsConnexion.php)
//	public static String consulterEchecsConnexion(String nomAdmin, String mdpAdmin, int annee, int mois, int raisonRefus, ArrayList<EchecConnexion> lesEchecs)
//	{
//		String reponse = "";
//		try
//		{	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
//			String urlDuServiceWeb = _adresseHebergeur + _urlConsulterEchecsConnexion;
//			urlDuServiceWeb += "?nomAdmin=" + nomAdmin;
//			urlDuServiceWeb += "&mdpAdmin=" + mdpAdmin;
//			urlDuServiceWeb += "&annee=" + annee;
//			urlDuServiceWeb += "&mois=" + mois;
//			urlDuServiceWeb += "&raisonRefus=" + raisonRefus;
//			Document leDocument = getDocumentXML(urlDuServiceWeb);
//
//			// parsing du flux XML
//			Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
//
//			reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
//
//			NodeList listeNoeudsEchecs = leDocument.getElementsByTagName("echec");
//			/* Exemple de données obtenues pour un échec :
//				<echec>
//				  <id_echec>17</id_echec>
//				  <date_cnx>2017-05-19 01:11:30</date_cnx>
//				  <name>jim</name>
//				  <password>passeeeee</password>
//				  <adresseIP>91.160.119.31</adresseIP>
//				  <id_raison_refus>2</id_raison_refus>
//				</echec>
//			 */
//
//			String formatUS = "yyyy-MM-dd HH:mm:ss";
//
//			// parcours de la liste des noeuds <echec> et ajout dans la collection lesEchecs
//			for (int i = 0 ; i <= listeNoeudsEchecs.getLength()-1 ; i++)
//			{	// création de l'élement courant à chaque tour de boucle
//				Element courant = (Element) listeNoeudsEchecs.item(i);
//
//				// lecture des balises intérieures
//				int unId = Integer.parseInt(courant.getElementsByTagName("id_echec").item(0).getTextContent());
//				Date uneDateCnx = Outils.ConvertirEnDate(courant.getElementsByTagName("date_cnx").item(0).getTextContent(), formatUS);
//				String unName = courant.getElementsByTagName("name").item(0).getTextContent();
//				String unPassword = courant.getElementsByTagName("password").item(0).getTextContent();
//				String uneAdresseIP = courant.getElementsByTagName("adresseIP").item(0).getTextContent();
//				int unIdRaisonRefus = Integer.parseInt(courant.getElementsByTagName("id_raison_refus").item(0).getTextContent());
//
//				// crée un objet EchecConnexion
//				EchecConnexion uneEchec = new EchecConnexion(unId, uneDateCnx, unName, unPassword, uneAdresseIP, unIdRaisonRefus);
//
//				// ajoute l'échec à la collection lesEchecs
//				lesEchecs.add(uneEchec);
//			}
//
//			// retour de la réponse du service web
//			return reponse;
//		}
//		catch (Exception ex)
//		{	String msg = "Erreur : " + ex.getMessage();
//			return msg;
//		}
//	}

    
//    // Méthode statique pour récupérer les statistiques des échecs de connexion(service ConsulterStatistiquesEchecsConnexion.php)
//    public static String consulterStatistiquesEchecsConnexion(String nomAdmin, String mdpAdmin, int annee, int mois, ArrayList<StatistiqueEchec>lesStatistiques) {
//        String reponse = "";
//        try
//        {	// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
//            String urlDuServiceWeb = _adresseHebergeur + _urlConsulterStatistiquesEchecsConnexion;
//            urlDuServiceWeb += "?nomAdmin=" + nomAdmin;
//            urlDuServiceWeb += "&mdpAdmin=" + mdpAdmin;
//            urlDuServiceWeb += "&annee=" + annee;
//            urlDuServiceWeb += "&mois=" + mois;
//            Document leDocument = getDocumentXML(urlDuServiceWeb);
//
//            // parsing du flux XML
//            Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
//
//            reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
//
//            NodeList listeNoeudsStats = leDocument.getElementsByTagName("statistique");
//			/* Exemple de données obtenues pour un échec :
//                <statistique>
//                    <annee>2017</annee>
//                    <mois>5</mois>
//                    <id_raison_refus>1</id_raison_refus>
//                    <libelle_raison>Login inexistant</libelle_raison>
//                    <nombre_refus>2</nombre_refus>
//                </statistique>
//			 */
//
//            // parcours de la liste des noeuds <statistique> et ajout dans la collection lesStatistiques
//            for (int i = 0 ; i <= listeNoeudsStats.getLength()-1 ; i++)
//            {	// création de l'élement courant à chaque tour de boucle
//                Element courant = (Element) listeNoeudsStats.item(i);
//
//                // lecture des balises intérieures
//                int idRaisonRefus = Integer.parseInt(courant.getElementsByTagName("id_raison_refus").item(0).getTextContent());
//                String libelleRaison = courant.getElementsByTagName("libelle_raison").item(0).getTextContent();
//                int nombreRefus = Integer.parseInt(courant.getElementsByTagName("nombre_refus").item(0).getTextContent());
//
//                // crée un objet StatistiqueEchec
//                StatistiqueEchec uneStatistique = new StatistiqueEchec(annee, mois, idRaisonRefus, libelleRaison, nombreRefus);
//
//                // ajoute l'échec à la collection lesStatistiques
//                lesStatistiques.add(uneStatistique);
//            }
//
//            // retour de la réponse du service web
//            return reponse;
//        }
//        catch (Exception ex)
//        {	String msg = "Erreur : " + ex.getMessage();
//            return msg;
//        }
//    }
    
} // fin de la classe
