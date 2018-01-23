/**
 * Application de suivi des réservations de la Maison des Ligues de Lorraine
 * Thème : développement et test des classes Salle, Reservation, Utilisateur et Passerelle
 * Auteur : JM CARTRON
 * Dernière mise à jour : 7/11/2016
 */
package jim.classes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

//les import suivant nécessitent d'ajouter au projet tous les composant du plugin (fourni) 
//httpcomponents-client-4.2.3 / lib  (ces plugins font partie également du SDK Android)
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
 * Elle utilise le modèle Jaxp pour parcourir le document XML
 * Ce modèle fait partie du JDK (et également du SDK Android)
 */
public class PasserelleM2L {

	/** Membres privés */
	
	// Adresse de l'hébergeur Internet
	//private static String _adresseHebergeur = "http://jean.michel.cartron.free.fr/mdl.sw/";
	// Adresse du localhost en cas d'exécution sur le poste de développement (projet de tests des classes)
	private static String _adresseHebergeur = "http://127.0.0.1/ws-php-cartron/m.mdl/services/";
	
	// Noms des services web déjà traités par la passerelle
	private static String _urlConnecter = "Connecter.php";
	private static String _urlConsulterReservations = "ConsulterReservations.php";
	private static String _urlCreerUtilisateur = "CreerUtilisateur.php";
	
	// noms des services web pas encore traités par la passerelle (à développer)
	private static String _urlConsulterSalles = "ConsulterSalles.php";
	private static String _urlAnnulerReservation = "AnnulerReservation.php";
	private static String _urlChangerDeMdp = "ChangerDeMdp.php";	
	private static String _urlConfirmerReservation = "ConfirmerReservation.php";	
	private static String _urlDemanderMdp = "DemanderMdp.php";	
	private static String _urlSupprimerUtilisateur = "SupprimerUtilisateur.php";
	
	// fonction privée statique pour obtenir un document XML à partir de l'URL d'un service web
	private static Document getDocumentXML(String urlDuServiceWeb, ArrayList<NameValuePair> parametresPostes)
	{
    	try
    	{	// création d'un DefaultHttpClient
			HttpClient unClientHttp = new DefaultHttpClient();
			
			// création d'une requête HTTP de type POST
			HttpPost uneRequeteHttp = new HttpPost(urlDuServiceWeb);
			uneRequeteHttp.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			// passage des paramètres à poster
			uneRequeteHttp.setEntity(new UrlEncodedFormEntity(parametresPostes, "UTF-8"));
			
			// exécution de la requête HTTP
			HttpResponse uneReponseHttp = unClientHttp.execute(uneRequeteHttp);
			
			// récupération de la réponse dans un flux en lecture (InputStream)
			InputStream unFluxEnEntree = uneReponseHttp.getEntity().getContent();
	
			// création d'une instance de DocumentBuilderFactory et DocumentBuilder
			DocumentBuilderFactory leDBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder leDB = leDBF.newDocumentBuilder();
	
			// création d'un nouveau document XML avec en argument le flux XML
			Document leDocument = leDB.parse(unFluxEnEntree);
			return leDocument;
    	}
    	catch (Exception ex)
    	{	return null;
		}	
	}
	
    // Méthode de classe pour se connecter (service Connecter.php)
    public static String connecter(String nomUtilisateur, String mdpUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// préparation des paramètres à poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));    		
    		
    		// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConnecter;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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

    // Méthode de classe pour créer un utilisateur (service CreerUtilisateur.php)
    public static String creerUtilisateur(String nomAdmin, String mdpAdmin, Utilisateur unUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// préparation des paramètres à poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nomAdmin", nomAdmin));
    		parametresPostes.add(new BasicNameValuePair("mdpAdmin", mdpAdmin));
    		parametresPostes.add(new BasicNameValuePair("name", unUtilisateur.getName()));
    		parametresPostes.add(new BasicNameValuePair("level", String.valueOf(unUtilisateur.getLevel())));
    		parametresPostes.add(new BasicNameValuePair("email", unUtilisateur.getEmail()));
    		
    		// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlCreerUtilisateur;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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

    // Méthode de classe pour récupérer les réservations d'un utilisateur (service ConsulterReservations.php)
    public static String consulterReservations(Utilisateur unUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// préparation des paramètres à poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", unUtilisateur.getName()));
    		parametresPostes.add(new BasicNameValuePair("mdp", unUtilisateur.getPassword()));
    		
    		// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConsulterReservations;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsMembres = leDocument.getElementsByTagName("reservation");
			/* Exemple de données obtenues pour un utilisateur :
			    <reservation>
			      <id>1</id>
			      <timestamp>2014-09-11 22:20:54</timestamp>
			      <start_time>2014-10-01 09:00:00</start_time>
			      <end_time>2014-10-01 12:00:00</end_time>
			      <room_name>Multimédia</room_name>
			      <status>4</status>
			      <digicode></digicode>
			    </reservation>
			 */
			String formatUS = "yyyy-MM-dd HH:mm:ss";
			
			// parcours de la liste des noeuds <reservation> et ajout dans l'objet unUtilisateur
			for (int i = 0 ; i <= listeNoeudsMembres.getLength()-1 ; i++)
			{	// création de l'élement courant à chaque tour de boucle
				Element courant = (Element) listeNoeudsMembres.item(i);
				
				// lecture des balises intérieures
				int id = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				Date timestamp = Outils.convertirEnDate(courant.getElementsByTagName("timestamp").item(0).getTextContent(), formatUS);
				Date start_time = Outils.convertirEnDate(courant.getElementsByTagName("start_time").item(0).getTextContent(), formatUS);
				Date end_time = Outils.convertirEnDate(courant.getElementsByTagName("end_time").item(0).getTextContent(), formatUS);
				String room_name = courant.getElementsByTagName("room_name").item(0).getTextContent();
				int status = Integer.parseInt(courant.getElementsByTagName("status").item(0).getTextContent());
				String digicode = courant.getElementsByTagName("digicode").item(0).getTextContent();
				
				// crée un objet Reservation
				Reservation uneReservation = new Reservation(id, timestamp, start_time, end_time, room_name, status, digicode);
				
				// ajoute la réservation à l'objet unUtilisateur
				unUtilisateur.ajouteReservation(uneReservation);
			}

    		// retour de la réponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }

    // Méthode de classe pour récupérer la liste des salles(service ConsulterSalles.php)
    public static String consulterSalles(String nomUtilisateur, String mdpUtilisateur, ArrayList<Salle> lesSalles)
    {
    	String reponse = "";
    	try
    	{	// préparation des paramètres à poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));   
    		
    		// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConsulterSalles;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsSalles = leDocument.getElementsByTagName("salle");
			/* Exemple de données obtenues pour une salle :
			    <salle>
			      <id>5</id>
			      <room_name>Multimédia</room_name>
			      <capacity>25</capacity>
			      <area_name>Informatique - multimédia</area_name>
			    </salle>
			 */
			
			// parcours de la liste des noeuds <salle> et ajout dans la collection lesSalles
			for (int i = 0 ; i <= listeNoeudsSalles.getLength()-1 ; i++)
			{	// création de l'élement courant à chaque tour de boucle
				Element courant = (Element) listeNoeudsSalles.item(i);
				
				// lecture des balises intérieures
				int id = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				String room_name = courant.getElementsByTagName("room_name").item(0).getTextContent();
				int capacity = Integer.parseInt(courant.getElementsByTagName("capacity").item(0).getTextContent());
				String area_name = courant.getElementsByTagName("area_name").item(0).getTextContent();
				
				// crée un objet Salle
				Salle uneSalle = new Salle(id, room_name, capacity, area_name);
				
				// ajoute la salle à la collection lesSalles
				lesSalles.add(uneSalle);
			}

    		// retour de la réponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    // Méthode de classe pour demander un nouveau mot de passe (service DemanderMdp.php)
    public static String demanderMdp(String nomUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// préparation des paramètres à poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));  		
    		
    		// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlDemanderMdp;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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

    // Méthode de classe pour confirmer une réservation (service ConfirmerReservation.php)
    public static String confirmerReservation(String nomUtilisateur, String mdpUtilisateur, String numReservation)
    {
    	String reponse = "";
    	try
    	{	// préparation des paramètres à poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));    		
    		parametresPostes.add(new BasicNameValuePair("numreservation", numReservation));  
    		
    		// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConfirmerReservation;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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
    
    // Méthode de classe pour annuler une réservation (service AnnulerReservation.php)
    public static String annulerReservation(String nomUtilisateur, String mdpUtilisateur, String numReservation)
    {
    	String reponse = "";
    	try
    	{	// préparation des paramètres à poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));    		
    		parametresPostes.add(new BasicNameValuePair("numreservation", numReservation));  
    		
    		// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlAnnulerReservation;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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

    // Méthode de classe pour modifier son mot de passe (service ChangerDeMdp.php)
    public static String changerDeMdp(String nom, String ancienMdp, String nouveauMdp, String confirmationMdp)
    {
    	String reponse = "";
    	try
    	{	// préparation des paramètres à poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nom));
    		parametresPostes.add(new BasicNameValuePair("ancienMdp", ancienMdp));    		
    		parametresPostes.add(new BasicNameValuePair("nouveauMdp", nouveauMdp));
    		parametresPostes.add(new BasicNameValuePair("confirmationMdp", confirmationMdp)); 
    		
    		// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlChangerDeMdp;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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
    
    // Méthode de classe pour supprimer un utilisateur (service SupprimerUtilisateur.php)
    public static String supprimerUtilisateur(String nomAdmin, String mdpAdmin, String name)
    {
    	String reponse = "";
    	try
    	{	// préparation des paramètres à poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nomAdmin", nomAdmin));
    		parametresPostes.add(new BasicNameValuePair("mdpAdmin", mdpAdmin));
    		parametresPostes.add(new BasicNameValuePair("name", name));
    		
    		// création d'un nouveau document XML à partir de l'URL du service web et des paramètres
    		String urlDuServiceWeb = _adresseHebergeur + _urlSupprimerUtilisateur;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
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
    
}
