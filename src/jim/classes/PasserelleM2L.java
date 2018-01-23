/**
 * Application de suivi des r�servations de la Maison des Ligues de Lorraine
 * Th�me : d�veloppement et test des classes Salle, Reservation, Utilisateur et Passerelle
 * Auteur : JM CARTRON
 * Derni�re mise � jour : 7/11/2016
 */
package jim.classes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

//les import suivant n�cessitent d'ajouter au projet tous les composant du plugin (fourni) 
//httpcomponents-client-4.2.3 / lib  (ces plugins font partie �galement du SDK Android)
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

//les import suivant sont inclus dans le JDK
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Cette classe fait le lien entre les services web et l'application
 * Elle utilise le mod�le Jaxp pour parcourir le document XML
 * Ce mod�le fait partie du JDK (et �galement du SDK Android)
 */
public class PasserelleM2L {

	/** Membres priv�s */
	
	// Adresse de l'h�bergeur Internet
	//private static String _adresseHebergeur = "http://jean.michel.cartron.free.fr/mdl.sw/";
	// Adresse du localhost en cas d'ex�cution sur le poste de d�veloppement (projet de tests des classes)
	private static String _adresseHebergeur = "http://127.0.0.1/ws-php-cartron/m.mdl/services/";
	
	// Noms des services web d�j� trait�s par la passerelle
	private static String _urlConnecter = "Connecter.php";
	private static String _urlConsulterReservations = "ConsulterReservations.php";
	private static String _urlCreerUtilisateur = "CreerUtilisateur.php";
	
	// noms des services web pas encore trait�s par la passerelle (� d�velopper)
	private static String _urlConsulterSalles = "ConsulterSalles.php";
	private static String _urlAnnulerReservation = "AnnulerReservation.php";
	private static String _urlChangerDeMdp = "ChangerDeMdp.php";	
	private static String _urlConfirmerReservation = "ConfirmerReservation.php";	
	private static String _urlDemanderMdp = "DemanderMdp.php";	
	private static String _urlSupprimerUtilisateur = "SupprimerUtilisateur.php";
	
	// fonction priv�e statique pour obtenir un document XML � partir de l'URL d'un service web
	private static Document getDocumentXML(String urlDuServiceWeb, ArrayList<NameValuePair> parametresPostes)
	{
    	try
    	{	// cr�ation d'un DefaultHttpClient
			HttpClient unClientHttp = new DefaultHttpClient();
			
			// cr�ation d'une requ�te HTTP de type POST
			HttpPost uneRequeteHttp = new HttpPost(urlDuServiceWeb);
			uneRequeteHttp.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			// passage des param�tres � poster
			uneRequeteHttp.setEntity(new UrlEncodedFormEntity(parametresPostes, "UTF-8"));
			
			// ex�cution de la requ�te HTTP
			HttpResponse uneReponseHttp = unClientHttp.execute(uneRequeteHttp);
			
			// r�cup�ration de la r�ponse dans un flux en lecture (InputStream)
			InputStream unFluxEnEntree = uneReponseHttp.getEntity().getContent();
	
			// cr�ation d'une instance de DocumentBuilderFactory et DocumentBuilder
			DocumentBuilderFactory leDBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder leDB = leDBF.newDocumentBuilder();
	
			// cr�ation d'un nouveau document XML avec en argument le flux XML
			Document leDocument = leDB.parse(unFluxEnEntree);
			return leDocument;
    	}
    	catch (Exception ex)
    	{	return null;
		}	
	}
	
    // M�thode de classe pour se connecter (service Connecter.php)
    public static String connecter(String nomUtilisateur, String mdpUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));    		
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConnecter;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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

    // M�thode de classe pour cr�er un utilisateur (service CreerUtilisateur.php)
    public static String creerUtilisateur(String nomAdmin, String mdpAdmin, Utilisateur unUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nomAdmin", nomAdmin));
    		parametresPostes.add(new BasicNameValuePair("mdpAdmin", mdpAdmin));
    		parametresPostes.add(new BasicNameValuePair("name", unUtilisateur.getName()));
    		parametresPostes.add(new BasicNameValuePair("level", String.valueOf(unUtilisateur.getLevel())));
    		parametresPostes.add(new BasicNameValuePair("email", unUtilisateur.getEmail()));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlCreerUtilisateur;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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

    // M�thode de classe pour r�cup�rer les r�servations d'un utilisateur (service ConsulterReservations.php)
    public static String consulterReservations(Utilisateur unUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", unUtilisateur.getName()));
    		parametresPostes.add(new BasicNameValuePair("mdp", unUtilisateur.getPassword()));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConsulterReservations;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsMembres = leDocument.getElementsByTagName("reservation");
			/* Exemple de donn�es obtenues pour un utilisateur :
			    <reservation>
			      <id>1</id>
			      <timestamp>2014-09-11 22:20:54</timestamp>
			      <start_time>2014-10-01 09:00:00</start_time>
			      <end_time>2014-10-01 12:00:00</end_time>
			      <room_name>Multim�dia</room_name>
			      <status>4</status>
			      <digicode></digicode>
			    </reservation>
			 */
			String formatUS = "yyyy-MM-dd HH:mm:ss";
			
			// parcours de la liste des noeuds <reservation> et ajout dans l'objet unUtilisateur
			for (int i = 0 ; i <= listeNoeudsMembres.getLength()-1 ; i++)
			{	// cr�ation de l'�lement courant � chaque tour de boucle
				Element courant = (Element) listeNoeudsMembres.item(i);
				
				// lecture des balises int�rieures
				int id = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				Date timestamp = Outils.convertirEnDate(courant.getElementsByTagName("timestamp").item(0).getTextContent(), formatUS);
				Date start_time = Outils.convertirEnDate(courant.getElementsByTagName("start_time").item(0).getTextContent(), formatUS);
				Date end_time = Outils.convertirEnDate(courant.getElementsByTagName("end_time").item(0).getTextContent(), formatUS);
				String room_name = courant.getElementsByTagName("room_name").item(0).getTextContent();
				int status = Integer.parseInt(courant.getElementsByTagName("status").item(0).getTextContent());
				String digicode = courant.getElementsByTagName("digicode").item(0).getTextContent();
				
				// cr�e un objet Reservation
				Reservation uneReservation = new Reservation(id, timestamp, start_time, end_time, room_name, status, digicode);
				
				// ajoute la r�servation � l'objet unUtilisateur
				unUtilisateur.ajouteReservation(uneReservation);
			}

    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }

    // M�thode de classe pour r�cup�rer la liste des salles(service ConsulterSalles.php)
    public static String consulterSalles(String nomUtilisateur, String mdpUtilisateur, ArrayList<Salle> lesSalles)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));   
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConsulterSalles;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsSalles = leDocument.getElementsByTagName("salle");
			/* Exemple de donn�es obtenues pour une salle :
			    <salle>
			      <id>5</id>
			      <room_name>Multim�dia</room_name>
			      <capacity>25</capacity>
			      <area_name>Informatique - multim�dia</area_name>
			    </salle>
			 */
			
			// parcours de la liste des noeuds <salle> et ajout dans la collection lesSalles
			for (int i = 0 ; i <= listeNoeudsSalles.getLength()-1 ; i++)
			{	// cr�ation de l'�lement courant � chaque tour de boucle
				Element courant = (Element) listeNoeudsSalles.item(i);
				
				// lecture des balises int�rieures
				int id = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				String room_name = courant.getElementsByTagName("room_name").item(0).getTextContent();
				int capacity = Integer.parseInt(courant.getElementsByTagName("capacity").item(0).getTextContent());
				String area_name = courant.getElementsByTagName("area_name").item(0).getTextContent();
				
				// cr�e un objet Salle
				Salle uneSalle = new Salle(id, room_name, capacity, area_name);
				
				// ajoute la salle � la collection lesSalles
				lesSalles.add(uneSalle);
			}

    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    // M�thode de classe pour demander un nouveau mot de passe (service DemanderMdp.php)
    public static String demanderMdp(String nomUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));  		
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlDemanderMdp;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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

    // M�thode de classe pour confirmer une r�servation (service ConfirmerReservation.php)
    public static String confirmerReservation(String nomUtilisateur, String mdpUtilisateur, String numReservation)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));    		
    		parametresPostes.add(new BasicNameValuePair("numreservation", numReservation));  
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConfirmerReservation;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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
    
    // M�thode de classe pour annuler une r�servation (service AnnulerReservation.php)
    public static String annulerReservation(String nomUtilisateur, String mdpUtilisateur, String numReservation)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));    		
    		parametresPostes.add(new BasicNameValuePair("numreservation", numReservation));  
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlAnnulerReservation;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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

    // M�thode de classe pour modifier son mot de passe (service ChangerDeMdp.php)
    public static String changerDeMdp(String nom, String ancienMdp, String nouveauMdp, String confirmationMdp)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nom));
    		parametresPostes.add(new BasicNameValuePair("ancienMdp", ancienMdp));    		
    		parametresPostes.add(new BasicNameValuePair("nouveauMdp", nouveauMdp));
    		parametresPostes.add(new BasicNameValuePair("confirmationMdp", confirmationMdp)); 
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlChangerDeMdp;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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
    
    // M�thode de classe pour supprimer un utilisateur (service SupprimerUtilisateur.php)
    public static String supprimerUtilisateur(String nomAdmin, String mdpAdmin, String name)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nomAdmin", nomAdmin));
    		parametresPostes.add(new BasicNameValuePair("mdpAdmin", mdpAdmin));
    		parametresPostes.add(new BasicNameValuePair("name", name));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlSupprimerUtilisateur;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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
    
}
