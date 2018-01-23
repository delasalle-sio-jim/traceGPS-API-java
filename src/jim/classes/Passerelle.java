package jim.classes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

// Cette classe abstraite fournit les outils permettant d'obtenir un document XML � partir d'un fichier ou d'un service web
// Derni�re mise � jour : 23/1/2018 par Jim

public abstract class Passerelle {

    // m�thode prot�g�e statique pour obtenir un flux en lecture (java.io.InputStream)
    // � partir de l'adresse d'un fichier ou de l'URL d'un service web
    protected static InputStream getFluxEnLecture(String adrFichierOuServiceWeb)
    {
		InputStream unFluxEnLecture;
		try
		{
			if (adrFichierOuServiceWeb.startsWith("http"))
			{
				// connexion HTTP au service web
				URL url = new URL(adrFichierOuServiceWeb);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

				// r�cup�ration de la r�ponse dans un flux en lecture (InputStream)
				unFluxEnLecture = new BufferedInputStream(urlConnection.getInputStream());

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
}
