package jim.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;

/**
 * Cette classe abstraite détermine les outils permettant de "parser" un fichier GPX pour mettre à jour un objet Trace.
 * @author JM
 *
 */
public abstract class Passerelle {

    /**
     * méthode protégée statique pour obtenir un flux en lecture
     * à partir de l'adresse d'un fichier ou de l'URL d'un service web
     * @param adrFichierOuServiceWeb : le nom du fichier contenant la trace (String)
     * @return : un flux de données en lecture (java.io.InputStream)
     */
    protected static InputStream getFluxEnLecture(String adrFichierOuServiceWeb)
    {
		InputStream unFluxEnLecture;
		try
		{
			if (adrFichierOuServiceWeb.startsWith("http"))
			{	// création d'un DefaultHttpClient et d'un HttpGet pour effectuer une requête HTTP de type GET
				HttpClient unClientHttp = new DefaultHttpClient();
				HttpGet uneRequeteHttp = new HttpGet(adrFichierOuServiceWeb);
	
				// exécution du client HTTP avec le HttpGet
				HttpResponse uneReponseHttp = unClientHttp.execute(uneRequeteHttp);
				
				// récupération de la réponse dans un flux en lecture (InputStream)
				unFluxEnLecture = uneReponseHttp.getEntity().getContent();
			}
			else
			{	// création d'un flux en lecture (InputStream) depuis le fichier
				unFluxEnLecture = new FileInputStream(new File(adrFichierOuServiceWeb));
			}
			return unFluxEnLecture;
		}
		catch (Exception ex)
		{	return null;
		}	
    }

    /**
     * méthode protégée statique pour obtenir document XML
     * à partir d'un flux de données en lecture
     * @param unFluxEnLecture : un flux de données en lecture (java.io.InputStream)
     * @return : un document XML (org.w3c.dom.Document)
     */
	protected static Document getDocumentXML(InputStream unFluxEnLecture)
	{
		try
		{
			// création d'une instance de DocumentBuilderFactory et DocumentBuilder
			DocumentBuilderFactory leDBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder leDB = leDBF.newDocumentBuilder();
	
			// on crée un nouveau document XML avec en argument le flux XML
			Document leDocument = leDB.parse(unFluxEnLecture);
			return leDocument;
		}
		catch (Exception ex)
		{	return null;
		}	
	}
	
	/**
	 * méthode abstraite pour mettre à jour un objet Trace (vide) à partir n'un fichier GPX
	 * @param nomFichier : le nom du fichier contenant la trace
	 * @param laTraceAcreer : l'objet Trace à mettre à jour
	 * @return : un message d'erreur de traitement (ou un message vide si pas d'erreur)
	 */
	public abstract String creerTrace(String nomFichier, Trace laTraceAcreer);
}
