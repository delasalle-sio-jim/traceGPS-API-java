// Projet TraceGPS - API Java
// Fichier : PasserelleServicesWeb.java
// Cette classe h�rite de la classe Passerelle
// Elle fournit des m�thodes pour appeler les diff�rents services web
// Elle utilise le mod�le Jaxp pour parcourir le document XML
// Le mod�le Jaxp fait partie du JDK (et �galement du SDK Android)
// Derni�re mise � jour : 23/1/2018 par Jim

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

	// attributs priv�s
	private static String formatDateUS = "yyyy-MM-dd HH:mm:ss";
	
	// Adresse de l'h�bergeur Internet
	//private static String _adresseHebergeur = "http://jean.michel.cartron.free.fr/tracegps/services/";
	// Adresse du localhost en cas d'ex�cution sur le poste de d�veloppement (projet de tests des classes)
	private static String _adresseHebergeur = "http://127.0.0.1/ws-php-cartron/tracegps/services/";
	
	// Noms des services web d�j� trait�s par la passerelle
	private static String _urlConnecter = "Connecter.php";
	private static String _urlDemanderMdp = "DemanderMdp.php";
	private static String _urlChangerDeMdp = "ChangerDeMdp.php";
	private static String _urlCreerUtilisateur = "CreerUtilisateur.php";
	private static String _urlDemarrerEnregistrementParcours = "DemarrerEnregistrementParcours.php";
    private static String _urlEnvoyerPosition = "EnvoyerPosition.php";
	private static String _urlArreterEnregistrementParcours = "ArreterEnregistrementParcours.php";
	private static String _urlGetTousLesUtilisateurs = "GetTousLesUtilisateurs.php";
	private static String _urlGetLesUtilisateursQueJautorise = "GetLesUtilisateursQueJautorise.php";
	private static String _urlGetLesUtilisateursQuiMautorisent = "GetLesUtilisateursQuiMautorisent.php";
	private static String _urlGetUnParcoursEtSesPoints = "GetUnParcoursEtSesPoints.php";
	private static String _urlGetLesParcoursDunUtilisateur = "GetLesParcoursDunUtilisateur.php";
	private static String _urlSupprimerUneAutorisation = "SupprimerUneAutorisation.php";
	private static String _urlSupprimerUnParcours = "SupprimerUnParcours.php";
	private static String _urlSupprimerUnUtilisateur = "SupprimerUnUtilisateur.php";
	
	// noms des services web pas encore trait�s par la passerelle (� d�velopper)		

	
    // M�thode statique pour se connecter (service Connecter.php)
	// La m�thode doit recevoir 2 param�tres :
	//    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
    public static String connecter(String pseudo, String mdpSha1)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConnecter;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
    		
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }

    
	// M�thode statique pour demander un nouveau mot de passe (service DemanderMdp.php)
	// La m�thode doit recevoir 1 param�tre :
	//    pseudo : le pseudo de l'utilisateur
	public static String demanderMdp(String pseudo)
	{
		String reponse = "";
		try
		{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
			String urlDuServiceWeb = _adresseHebergeur + _urlDemanderMdp;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
			
			// parsing du flux XML
			Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
			reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
			
			// retour de la r�ponse du service web
			return reponse;
		}
		catch (Exception ex)
		{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
	}
    
	
    // M�thode statique pour modifier son mot de passe (service ChangerDeMdp.php)
	// La m�thode doit recevoir 4 param�tres :
	//    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
	//    nouveauMdp : le nouveau mot de passe
	//    confirmationMdp : la confirmation du nouveau mot de passe
    public static String changerDeMdp(String pseudo, String mdpSha1, String nouveauMdp, String confirmationMdp)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlChangerDeMdp;
            urlDuServiceWeb += "?pseudo=" + pseudo;
            urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
            urlDuServiceWeb += "&nouveauMdp=" + nouveauMdp;
            urlDuServiceWeb += "&confirmationMdp=" + confirmationMdp;
			
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    
    // M�thode statique pour cr�er un utilisateur (service CreerUtilisateur.php)
	// La m�thode doit recevoir 3 param�tres :
	//   pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//   adrMail : son adresse mail
	//   numTel : son num�ro de t�l�phone
    public static String creerUtilisateur(String pseudo, String adrMail, String numTel)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlCreerUtilisateur;
            urlDuServiceWeb += "?pseudo=" + pseudo;
            urlDuServiceWeb += "&adrMail=" + adrMail;
            urlDuServiceWeb += "&numTel=" + numTel;

			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }

    
    // M�thode statique pour d�marrer l'enregistrement d'un parcours (service DemarrerEnregistrementParcours.php)
	// La m�thode doit recevoir 3 param�tres :
	//    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
    //    laTrace : un objet Trace (vide) � remplir � partir des donn�es fournies par le service web
    public static String demarrerEnregistrementParcours(String pseudo, String mdpSha1, Trace laTrace)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlDemarrerEnregistrementParcours;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
    		
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsTraces = racine.getElementsByTagName("trace");
			/* Exemple de donn�es obtenues pour la trace :
			    <trace>
			      <idTrace>24</idTrace>
			      <dateHeureDebut>2018-01-25 09:46:52</dateHeureDebut>
			      <dateHeureFin/>
			      <terminee/>
			      <idUtilisateur>3</idUtilisateur>
			    </trace>
			 */
    		
			if (listeNoeudsTraces.getLength() > 0)
			{
				// r�cup�ration du premier et seul �l�ment de la liste
				Element courant = (Element) listeNoeudsTraces.item(0);
				
				// lecture des balises int�rieures
				int unId = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				Date uneDateHeureDebut = Outils.convertirEnDate(courant.getElementsByTagName("dateHeureDebut").item(0).getTextContent(), formatDateUS);
				int unIdUtilisateur = Integer.parseInt(courant.getElementsByTagName("idUtilisateur").item(0).getTextContent());
				
				// Mise � jour de l'objet Trace pass� en param�tre
				laTrace.setId(unId);
				laTrace.setDateHeureDebut(uneDateHeureDebut);
				laTrace.setDateHeureFin(null);
				laTrace.setTerminee(false);
				laTrace.setIdUtilisateur(unIdUtilisateur);		
			}

    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }  
    
  
    // M�thode statique pour envoyer la position de l'utilisateur (service EnvoyerPosition.php)
	// La m�thode doit recevoir 3 param�tres :
	//    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
	//    lePoint : un objet PointDeTrace (vide) qui permettra de r�cup�rer le num�ro attribu� � partir des donn�es fournies par le service web
    public static String envoyerPosition(String pseudo, String mdpSha1, PointDeTrace lePoint)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlEnvoyerPosition;
            urlDuServiceWeb += "?pseudo=" + pseudo;
            urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
            urlDuServiceWeb += "&idTrace=" + lePoint.getIdTrace();
            urlDuServiceWeb += "&dateHeure=" + Outils.formaterDateHeureUS(lePoint.getDateHeure()).replace(" ", "%20");
            urlDuServiceWeb += "&latitude=" + lePoint.getLatitude();
            urlDuServiceWeb += "&longitude=" + lePoint.getLongitude();
            urlDuServiceWeb += "&altitude=" + lePoint.getAltitude();
            urlDuServiceWeb += "&rythmeCardio=" + lePoint.getRythmeCardio();
            
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeuds = racine.getElementsByTagName("id");
			/* Exemple de donn�es obtenues :
			    <donnees>
			      <id>1</id>
			    </donnees>
			 */
    		
			if (listeNoeuds.getLength() > 0)
			{	// r�cup�ration du premier et seul �l�ment de la liste
				Element courant = (Element) listeNoeuds.item(0);
				// lecture de la balise
				int id = Integer.parseInt(courant.getTextContent());
				// mise � jour de l'objet lePoint pass� en param�tre
				lePoint.setId(id);		
			}
			
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    
    // M�thode statique pour terminer l'enregistrement d'un parcours (service ArreterEnregistrementParcours.php)
	// La m�thode doit recevoir 3 param�tres :
	//    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
	//    idTrace : l'id de la trace � terminer
    public static String arreterEnregistrementParcours(String pseudo, String mdpSha1, int idTrace)
    {
    	String etape = "";
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlArreterEnregistrementParcours;
            urlDuServiceWeb += "?pseudo=" + pseudo;
            urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
            urlDuServiceWeb += "&idTrace=" + idTrace;
            
            etape = "1";
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			etape = "2";
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
			etape = "3";
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		etape = "4";
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
    		msg += "\nEtape : " + etape;
			return msg;
		}
    }    
    
    
    // M�thode statique pour obtenir la liste de tous les utilisateurs de niveau 1 (service GetTousLesUtilisateurs.php)
	// La m�thode doit recevoir 3 param�tres :
	//    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
    //    lesUtilisateurs : collection (vide) � remplir � partir des donn�es fournies par le service web
    public static String getTousLesUtilisateurs(String pseudo, String mdpSha1, ArrayList<Utilisateur> lesUtilisateurs)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlGetTousLesUtilisateurs;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
    		
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsUtilisateurs = leDocument.getElementsByTagName("utilisateur");
			/* Exemple de donn�es obtenues pour un utilisateur :
				<utilisateur>
					<id>2</id>
					<pseudo>callisto</pseudo>
					<adrMail>delasalle.sio.eleves@gmail.com</adrMail>
					<numTel>22.33.44.55.66</numTel>
					<niveau>1</niveau>
					<dateCreation>2018-01-19 20:11:24</dateCreation>
					<nbTraces>2</nbTraces>
					<dateDerniereTrace>2018-01-19 13:08:48</dateDerniereTrace>
				</utilisateur>
			 */
			
			// vider d'abord la collection avant de la remplir
			lesUtilisateurs.clear();
			
			// parcours de la liste des noeuds <utilisateur> et ajout dans la collection lesUtilisateurs
			for (int i = 0 ; i <= listeNoeudsUtilisateurs.getLength()-1 ; i++)
			{	// cr�ation de l'�lement courant � chaque tour de boucle
				Element courant = (Element) listeNoeudsUtilisateurs.item(i);
				
				// lecture des balises int�rieures
				int unId = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				String unPseudo = courant.getElementsByTagName("pseudo").item(0).getTextContent();
				String unMdpSha1 = "";								// par s�curit�, on ne r�cup�re pas le mot de passe
				String uneAdrMail = courant.getElementsByTagName("adrMail").item(0).getTextContent();
				String unNumTel = courant.getElementsByTagName("numTel").item(0).getTextContent();
				int unNiveau = Integer.parseInt(courant.getElementsByTagName("niveau").item(0).getTextContent());
				Date uneDateCreation = Outils.convertirEnDate(courant.getElementsByTagName("dateCreation").item(0).getTextContent(), formatDateUS);
				int unNbTraces = Integer.parseInt(courant.getElementsByTagName("nbTraces").item(0).getTextContent());
				Date uneDateDerniereTrace = null;
				if (unNbTraces > 0)
					uneDateDerniereTrace = Outils.convertirEnDate(courant.getElementsByTagName("dateDerniereTrace").item(0).getTextContent(), formatDateUS);
				
				// cr�e un objet Utilisateur
				Utilisateur unUtilisateur = new Utilisateur(unId, unPseudo, unMdpSha1, uneAdrMail, unNumTel, unNiveau, uneDateCreation, unNbTraces, uneDateDerniereTrace);
				
				// ajoute l'utilisateur � la collection lesUtilisateurs
				lesUtilisateurs.add(unUtilisateur);
			}
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    
    // M�thode statique pour obtenir la liste des utilisateurs que j'autorise (service GetLesUtilisateursQueJautorise.php)
	// La m�thode doit recevoir 3 param�tres :
	//    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
    //    lesUtilisateurs : collection (vide) � remplir � partir des donn�es fournies par le service web
    public static String getLesUtilisateursQueJautorise(String pseudo, String mdpSha1, ArrayList<Utilisateur> lesUtilisateurs)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlGetLesUtilisateursQueJautorise;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
    		
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsUtilisateurs = leDocument.getElementsByTagName("utilisateur");
			/* Exemple de donn�es obtenues pour un utilisateur :
				<utilisateur>
					<id>2</id>
					<pseudo>callisto</pseudo>
					<adrMail>delasalle.sio.eleves@gmail.com</adrMail>
					<numTel>22.33.44.55.66</numTel>
					<niveau>1</niveau>
					<dateCreation>2018-01-19 20:11:24</dateCreation>
					<nbTraces>2</nbTraces>
					<dateDerniereTrace>2018-01-19 13:08:48</dateDerniereTrace>
				</utilisateur>
			 */
			
			// vider d'abord la collection avant de la remplir
			lesUtilisateurs.clear();
			
			// parcours de la liste des noeuds <utilisateur> et ajout dans la collection lesUtilisateurs
			for (int i = 0 ; i <= listeNoeudsUtilisateurs.getLength()-1 ; i++)
			{	// cr�ation de l'�lement courant � chaque tour de boucle
				Element courant = (Element) listeNoeudsUtilisateurs.item(i);
				
				// lecture des balises int�rieures
				int unId = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				String unPseudo = courant.getElementsByTagName("pseudo").item(0).getTextContent();
				String unMdpSha1 = "";								// par s�curit�, on ne r�cup�re pas le mot de passe
				String uneAdrMail = courant.getElementsByTagName("adrMail").item(0).getTextContent();
				String unNumTel = courant.getElementsByTagName("numTel").item(0).getTextContent();
				int unNiveau = Integer.parseInt(courant.getElementsByTagName("niveau").item(0).getTextContent());
				Date uneDateCreation = Outils.convertirEnDate(courant.getElementsByTagName("dateCreation").item(0).getTextContent(), formatDateUS);
				int unNbTraces = Integer.parseInt(courant.getElementsByTagName("nbTraces").item(0).getTextContent());
				Date uneDateDerniereTrace = null;
				if (unNbTraces > 0)
					uneDateDerniereTrace = Outils.convertirEnDate(courant.getElementsByTagName("dateDerniereTrace").item(0).getTextContent(), formatDateUS);
				
				// cr�e un objet Utilisateur
				Utilisateur unUtilisateur = new Utilisateur(unId, unPseudo, unMdpSha1, uneAdrMail, unNumTel, unNiveau, uneDateCreation, unNbTraces, uneDateDerniereTrace);
				
				// ajoute l'utilisateur � la collection lesUtilisateurs
				lesUtilisateurs.add(unUtilisateur);
			}
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }  
    
    
    // M�thode statique pour obtenir la liste des utilisateurs qui m'autorisent (service GetLesUtilisateursQuiMautorisent.php)
	// La m�thode doit recevoir 3 param�tres :
	//    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
    //    lesUtilisateurs : collection (vide) � remplir � partir des donn�es fournies par le service web
    public static String getLesUtilisateursQuiMautorisent(String pseudo, String mdpSha1, ArrayList<Utilisateur> lesUtilisateurs)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlGetLesUtilisateursQuiMautorisent;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
    		
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsUtilisateurs = leDocument.getElementsByTagName("utilisateur");
			/* Exemple de donn�es obtenues pour un utilisateur :
				<utilisateur>
					<id>2</id>
					<pseudo>callisto</pseudo>
					<adrMail>delasalle.sio.eleves@gmail.com</adrMail>
					<numTel>22.33.44.55.66</numTel>
					<niveau>1</niveau>
					<dateCreation>2018-01-19 20:11:24</dateCreation>
					<nbTraces>2</nbTraces>
					<dateDerniereTrace>2018-01-19 13:08:48</dateDerniereTrace>
				</utilisateur>
			 */
			
			// vider d'abord la collection avant de la remplir
			lesUtilisateurs.clear();
			
			// parcours de la liste des noeuds <utilisateur> et ajout dans la collection lesUtilisateurs
			for (int i = 0 ; i <= listeNoeudsUtilisateurs.getLength()-1 ; i++)
			{	// cr�ation de l'�lement courant � chaque tour de boucle
				Element courant = (Element) listeNoeudsUtilisateurs.item(i);
				
				// lecture des balises int�rieures
				int unId = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				String unPseudo = courant.getElementsByTagName("pseudo").item(0).getTextContent();
				String unMdpSha1 = "";								// par s�curit�, on ne r�cup�re pas le mot de passe
				String uneAdrMail = courant.getElementsByTagName("adrMail").item(0).getTextContent();
				String unNumTel = courant.getElementsByTagName("numTel").item(0).getTextContent();
				int unNiveau = Integer.parseInt(courant.getElementsByTagName("niveau").item(0).getTextContent());
				Date uneDateCreation = Outils.convertirEnDate(courant.getElementsByTagName("dateCreation").item(0).getTextContent(), formatDateUS);
				int unNbTraces = Integer.parseInt(courant.getElementsByTagName("nbTraces").item(0).getTextContent());
				Date uneDateDerniereTrace = null;
				if (unNbTraces > 0)
					uneDateDerniereTrace = Outils.convertirEnDate(courant.getElementsByTagName("dateDerniereTrace").item(0).getTextContent(), formatDateUS);
				
				// cr�e un objet Utilisateur
				Utilisateur unUtilisateur = new Utilisateur(unId, unPseudo, unMdpSha1, uneAdrMail, unNumTel, unNiveau, uneDateCreation, unNbTraces, uneDateDerniereTrace);
				
				// ajoute l'utilisateur � la collection lesUtilisateurs
				lesUtilisateurs.add(unUtilisateur);
			}
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }  
    

    // M�thode statique pour obtenir un parcours et la liste de ses points (service GetUnParcoursEtSesPoints.php)
	// La m�thode doit recevoir 4 param�tres :
    //    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
    //    idTrace : l'id de la trace � consulter
    //    laTrace : objet Trace (vide) � remplir � partir des donn�es fournies par le service web
    public static String getUnParcoursEtSesPoints(String pseudo, String mdpSha1, int idTrace, Trace laTrace)
    {
    	String etape = "";
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlGetUnParcoursEtSesPoints;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
			urlDuServiceWeb += "&idTrace=" + idTrace;
    		
			etape = "1";
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			etape = "2";
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
			etape = "3";
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		etape = "4";
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// r�cup�rer les donn�es de la balise trace
    		etape = "5";
			NodeList listeNoeudsTraces = racine.getElementsByTagName("trace");
			/* Exemple de donn�es obtenues pour une trace :
			    <trace>
			      <idTrace>2</idTrace>
			      <dateHeureDebut>2018-01-19 13:08:48</dateHeureDebut> 
			      <terminee>1</terminee>
			      <dateHeureFin>2018-01-19 13:11:48</dateHeureFin>
			      <idUtilisateur>2</idUtilisateur>
			    </trace>
			 */
    		
			if (listeNoeudsTraces.getLength() > 0)
			{
				// r�cup�ration du premier et seul �l�ment de la liste
				etape = "6";
				Element courant = (Element) listeNoeudsTraces.item(0);
				
				// lecture des balises int�rieures
				etape = "7";
				int unId = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				etape = "8";
				Date uneDateHeureDebut = Outils.convertirEnDate(courant.getElementsByTagName("dateHeureDebut").item(0).getTextContent(), formatDateUS);
				etape = "9";
				boolean terminee = (Integer.parseInt((courant.getElementsByTagName("terminee").item(0).getTextContent())) == 1);
				etape = "10";
				Date uneDateHeureFin = null;
				if (terminee == true)
					uneDateHeureFin = Outils.convertirEnDate(courant.getElementsByTagName("dateHeureFin").item(0).getTextContent(), formatDateUS);
				int unIdUtilisateur = Integer.parseInt(courant.getElementsByTagName("idUtilisateur").item(0).getTextContent());
				etape = "11";
				
				// Mise � jour de l'objet Trace pass� en param�tre
				laTrace.setId(unId);
				laTrace.setDateHeureDebut(uneDateHeureDebut);
				laTrace.setDateHeureFin(uneDateHeureFin);
				laTrace.setTerminee(terminee);
				laTrace.setIdUtilisateur(unIdUtilisateur);
				
				// r�cup�rer les donn�es des balises points
	    		etape = "12";
				NodeList listeNoeudsPoints = racine.getElementsByTagName("point");
				/* Exemple de donn�es obtenues pour un point :
				      <point>
				        <id>1</id>
				        <latitude>48.2109</latitude>
				        <longitude>-1.5535</longitude>
				        <altitude>60</altitude>
				        <dateHeure>2018-01-19 13:08:48</dateHeure>
				        <rythmeCardio>81</rythmeCardio>
				      </point>
				 */
				
				// vider d'abord la collection des poins avant de la remplir
				laTrace.viderListePoints();
				
				// parcours de la liste des noeuds <point> et ajout dans la collection
				for (int i = 0 ; i <= listeNoeudsPoints.getLength()-1 ; i++)
				{	// cr�ation de l'�lement courant � chaque tour de boucle
					Element pointCourant = (Element) listeNoeudsPoints.item(i);
					
					// lecture des balises int�rieures
					int id = Integer.parseInt(pointCourant.getElementsByTagName("id").item(0).getTextContent());
					double uneLatitude = Double.parseDouble(pointCourant.getElementsByTagName("latitude").item(0).getTextContent());
					double uneLongitude = Double.parseDouble(pointCourant.getElementsByTagName("longitude").item(0).getTextContent());
					double uneAltitude = Double.parseDouble(pointCourant.getElementsByTagName("altitude").item(0).getTextContent());
					Date uneDateHeure = Outils.convertirEnDate(pointCourant.getElementsByTagName("dateHeure").item(0).getTextContent(), formatDateUS);
					int unRythmeCardio = Integer.parseInt(pointCourant.getElementsByTagName("rythmeCardio").item(0).getTextContent());
					
					// cr�e un objet PointDeTrace
					PointDeTrace unPointDeTrace = new PointDeTrace(idTrace, id, uneLatitude, uneLongitude, uneAltitude, uneDateHeure, unRythmeCardio);
					
					// ajoute le points � la collection des points
					laTrace.ajouterPoint(unPointDeTrace);
				}
			}
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
    		msg += "\nEtape : " + etape;
			return msg;
		}
    }
    
    
    // M�thode statique pour obtenir la liste des parcours d'un utilisateur (service GetLesParcoursDunUtilisateur.php)
	// La m�thode doit recevoir 4 param�tres :
	//    pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//    mdpSha1 : le mot de passe hash� en sha1
    //    idUtilisateur : l'id de l'utilisateur dont on veut la liste des parcours
    //    lesTraces : collection (vide) � remplir � partir des donn�es fournies par le service web
    public static String getLesParcoursDunUtilisateur(String pseudo, String mdpSha1, int idUtilisateur, ArrayList<Trace> lesTraces)
    {
    	String etape = "";
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlGetLesParcoursDunUtilisateur;
			urlDuServiceWeb += "?pseudo=" + pseudo;
			urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
			urlDuServiceWeb += "&idUtilisateur=" + idUtilisateur;
    		
			etape = "1";
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			etape = "2";
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
			etape = "3";
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		etape = "4";
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// r�cup�rer les donn�es des balises trace
    		etape = "5";
			NodeList listeNoeudsTraces = racine.getElementsByTagName("trace");
			
			/* Exemple de donn�es obtenues pour une trace :
			    <trace>
			      <idTrace>2</idTrace>
			      <dateHeureDebut>2018-01-19 13:08:48</dateHeureDebut> 
			      <terminee>1</terminee>
			      <dateHeureFin>2018-01-19 13:11:48</dateHeureFin>
			      <idUtilisateur>2</idUtilisateur>
			    </trace>
			 */
    		
			// vider d'abord la collection avant de la remplir
			lesTraces.clear();
			
			// parcours de la liste des noeuds <trace> et ajout dans la collection lesTraces
			for (int i = 0 ; i <= listeNoeudsTraces.getLength()-1 ; i++)
			{	// cr�ation de l'�lement courant � chaque tour de boucle
				etape = "6";
				Element courant = (Element) listeNoeudsTraces.item(i);
				
				// lecture des balises int�rieures
				etape = "7";
				int unId = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				etape = "8";
				Date uneDateHeureDebut = Outils.convertirEnDate(courant.getElementsByTagName("dateHeureDebut").item(0).getTextContent(), formatDateUS);
				etape = "9";
				boolean terminee = (Integer.parseInt((courant.getElementsByTagName("terminee").item(0).getTextContent())) == 1);
				etape = "10";
				Date uneDateHeureFin = null;
				if (terminee == true)
					uneDateHeureFin = Outils.convertirEnDate(courant.getElementsByTagName("dateHeureFin").item(0).getTextContent(), formatDateUS);
				etape = "11";
				int unIdUtilisateur = Integer.parseInt(courant.getElementsByTagName("idUtilisateur").item(0).getTextContent());

				// cr�e un objet Trace
				Trace uneTrace = new Trace(unId, uneDateHeureDebut, uneDateHeureFin, terminee, unIdUtilisateur);
				
				// ajoute la trace � la collection lesTraces
				lesTraces.add(uneTrace);
			}
			
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
    		msg += "\nEtape : " + etape;
			return msg;
		}
    }


    // M�thode statique pour supprimer une autorisation (service SupprimerUneAutorisation.php)
	// La m�thode doit recevoir 3 param�tres :
	//   pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//   mdpSha1 : le mot de passe hash� en sha1
	//   pseudoARetirer : le pseudo de l'utilisateur � qui on veut retirer l'autorisation
    public static String supprimerUneAutorisation(String pseudo, String mdpSha1, String pseudoARetirer)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlSupprimerUneAutorisation;
            urlDuServiceWeb += "?pseudo=" + pseudo;
            urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
            urlDuServiceWeb += "&pseudoARetirer=" + pseudoARetirer;

			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
   
    
    // M�thode statique pour supprimer un parcours (service SupprimerUnParcours.php)
	// La m�thode doit recevoir 3 param�tres :
	//   pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//   mdpSha1 : le mot de passe hash� en sha1
	//   idTrace : l'id de la trace � supprimer
    public static String supprimerUnParcours(String pseudo, String mdpSha1, int idTrace)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlSupprimerUnParcours;
            urlDuServiceWeb += "?pseudo=" + pseudo;
            urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
            urlDuServiceWeb += "&idTrace=" + idTrace;

			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    
    // M�thode statique pour supprimer un utilisateur (service SupprimerUnUtilisateur.php)
    // Ce service permet � un administrateur de supprimer un utilisateur (� condition qu'il ne poss�de aucune trace enregistr�e)
	// La m�thode doit recevoir 3 param�tres :
	//   pseudo : le pseudo de l'utilisateur qui fait appel au service web
	//   mdpSha1 : le mot de passe hash� en sha1
	//   pseudoAsupprimer : le pseudo de l'utilisateur � supprimer
    public static String supprimerUnUtilisateur(String pseudo, String mdpSha1, String pseudoAsupprimer)
    {
    	String reponse = "";
    	try
    	{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlSupprimerUnUtilisateur;
            urlDuServiceWeb += "?pseudo=" + pseudo;
            urlDuServiceWeb += "&mdpSha1=" + mdpSha1;
            urlDuServiceWeb += "&pseudoAsupprimer=" + pseudoAsupprimer;

			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(urlDuServiceWeb);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    
    


    
//	// M�thode statique pour r�cup�rer la liste des �checs de connexion(service ConsulterEchecsConnexion.php)
//	public static String consulterEchecsConnexion(String nomAdmin, String mdpAdmin, int annee, int mois, int raisonRefus, ArrayList<EchecConnexion> lesEchecs)
//	{
//		String reponse = "";
//		try
//		{	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
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
//			/* Exemple de donn�es obtenues pour un �chec :
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
//			{	// cr�ation de l'�lement courant � chaque tour de boucle
//				Element courant = (Element) listeNoeudsEchecs.item(i);
//
//				// lecture des balises int�rieures
//				int unId = Integer.parseInt(courant.getElementsByTagName("id_echec").item(0).getTextContent());
//				Date uneDateCnx = Outils.ConvertirEnDate(courant.getElementsByTagName("date_cnx").item(0).getTextContent(), formatUS);
//				String unName = courant.getElementsByTagName("name").item(0).getTextContent();
//				String unPassword = courant.getElementsByTagName("password").item(0).getTextContent();
//				String uneAdresseIP = courant.getElementsByTagName("adresseIP").item(0).getTextContent();
//				int unIdRaisonRefus = Integer.parseInt(courant.getElementsByTagName("id_raison_refus").item(0).getTextContent());
//
//				// cr�e un objet EchecConnexion
//				EchecConnexion uneEchec = new EchecConnexion(unId, uneDateCnx, unName, unPassword, uneAdresseIP, unIdRaisonRefus);
//
//				// ajoute l'�chec � la collection lesEchecs
//				lesEchecs.add(uneEchec);
//			}
//
//			// retour de la r�ponse du service web
//			return reponse;
//		}
//		catch (Exception ex)
//		{	String msg = "Erreur : " + ex.getMessage();
//			return msg;
//		}
//	}

    
//    // M�thode statique pour r�cup�rer les statistiques des �checs de connexion(service ConsulterStatistiquesEchecsConnexion.php)
//    public static String consulterStatistiquesEchecsConnexion(String nomAdmin, String mdpAdmin, int annee, int mois, ArrayList<StatistiqueEchec>lesStatistiques) {
//        String reponse = "";
//        try
//        {	// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
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
//			/* Exemple de donn�es obtenues pour un �chec :
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
//            {	// cr�ation de l'�lement courant � chaque tour de boucle
//                Element courant = (Element) listeNoeudsStats.item(i);
//
//                // lecture des balises int�rieures
//                int idRaisonRefus = Integer.parseInt(courant.getElementsByTagName("id_raison_refus").item(0).getTextContent());
//                String libelleRaison = courant.getElementsByTagName("libelle_raison").item(0).getTextContent();
//                int nombreRefus = Integer.parseInt(courant.getElementsByTagName("nombre_refus").item(0).getTextContent());
//
//                // cr�e un objet StatistiqueEchec
//                StatistiqueEchec uneStatistique = new StatistiqueEchec(annee, mois, idRaisonRefus, libelleRaison, nombreRefus);
//
//                // ajoute l'�chec � la collection lesStatistiques
//                lesStatistiques.add(uneStatistique);
//            }
//
//            // retour de la r�ponse du service web
//            return reponse;
//        }
//        catch (Exception ex)
//        {	String msg = "Erreur : " + ex.getMessage();
//            return msg;
//        }
//    }
    
} // fin de la classe
