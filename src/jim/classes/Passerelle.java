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

// Cette classe abstraite d�termine les outils permettant de "parser" un fichier GPS pour mettre � jour un objet Trace.
// Derni�re mise � jour : 22/1/2018 par Jim

public abstract class Passerelle {

    // m�thode prot�g�e statique pour obtenir un flux en lecture (java.io.InputStream)
    // � partir de l'adresse d'un fichier ou de l'URL d'un service web
    protected static InputStream getFluxEnLecture(String adrFichierOuServiceWeb)
    {
		InputStream unFluxEnLecture;
		try
		{
			if (adrFichierOuServiceWeb.startsWith("http"))
			{	// cr�ation d'un DefaultHttpClient et d'un HttpGet pour effectuer une requ�te HTTP de type GET
				HttpClient unClientHttp = new DefaultHttpClient();
				HttpGet uneRequeteHttp = new HttpGet(adrFichierOuServiceWeb);
	
				// ex�cution du client HTTP avec le HttpGet
				HttpResponse uneReponseHttp = unClientHttp.execute(uneRequeteHttp);
				
				// r�cup�ration de la r�ponse dans un flux en lecture (InputStream)
				unFluxEnLecture = uneReponseHttp.getEntity().getContent();
			}
			else
			{	// cr�ation d'un flux en lecture (InputStream) depuis le fichier
				unFluxEnLecture = new FileInputStream(new File(adrFichierOuServiceWeb));
			}
			return unFluxEnLecture;
		}
		catch (Exception ex)
		{	return null;
		}	
    }

    // m�thode prot�g�e statique pour obtenir document XML (org.w3c.dom.Document)
    // � partir d'un flux de donn�es en lecture (java.io.InputStream)
	protected static Document getDocumentXML(InputStream unFluxEnLecture)
	{
		try
		{
			// cr�ation d'une instance de DocumentBuilderFactory et DocumentBuilder
			DocumentBuilderFactory leDBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder leDB = leDBF.newDocumentBuilder();
	
			// on cr�e un nouveau document XML avec en argument le flux XML
			Document leDocument = leDB.parse(unFluxEnLecture);
			return leDocument;
		}
		catch (Exception ex)
		{	return null;
		}	
	}
	
	// m�thode abstraite pour mettre � jour un objet Trace (vide) � partir n'un fichier GPS
	// param�tre nomFichier : le nom du fichier contenant la trace
	public abstract String creerTrace(String nomFichier, Trace laTraceAcreer);
}
