// Projet TraceGPS - API Java
// Fichier : PasserellePWX.java
// Cette classe fournit les outils permettant de "parser" un fichier PWX pour mettre � jour un objet Trace.<br>
// Derni�re mise � jour : 23/1/2018 par Jim

package jim.classes;

import java.io.InputStream;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PasserellePWX extends PasserelleFichier {

	// m�thode pour mettre � jour un objet Trace (vide) � partir n'un fichier PWX
	// param�tre nomFichier  : le nom du fichier contenant la trace
	// param�tre laTraceAmaj : l'objet Trace � mettre � jour
	// retourne              : un message d'erreur de traitement (ou un message vide si pas d'erreur)
	@Override
	public String getUneTrace (String nomFichier, Trace laTraceAmaj) {
		try {
			// cr�ation d'un flux en lecture (InputStream) � partir du fichier
			InputStream unFluxEnLecture = getFluxEnLecture(nomFichier);
			
			// cr�ation d'un objet org.w3c.dom.Document � partir du flux ; il servira � parcourir le flux XML
			Document leDocument = getDocumentXML(unFluxEnLecture);
	
			/* Exemple de donn�es obtenues pour un point de trace :
						<sample>
							<timeoffset>0.0</timeoffset>
							<hr>112</hr>
							<spd>0.0</spd>
							<dist>0.0</dist>
							<lat>48.150052</lat>
							<lon>-1.680224</lon>
							<alt>31.6</alt>
							<time>2016-12-03T09:21:15Z</time>
						</sample>
			 */
		
			// cr�ation d'une liste contenant tous les noeuds <sample> de l'�lement racine
			NodeList lesNoeuds = leDocument.getElementsByTagName("sample");

			// vide la liste actuelle des points de trace
			laTraceAmaj.viderListePoints();
			
			// m�moriser l'id de la trace
			int idTrace = laTraceAmaj.getId();
			// initialiser l'id des points
			int idPoint = 0;

			// parcours de la liste des noeuds <sample>
			for (int i = 0 ; i <= lesNoeuds.getLength()-1 ; i++)
			{	// cr�ation de l'�lement courant � chaque tour de boucle
				Element unNoeud = (Element) lesNoeuds.item(i);

				// on v�rifie que le noeud poss�de toutes les balises
				if (unNoeud.getElementsByTagName("hr").getLength() > 0 &&
				unNoeud.getElementsByTagName("lat").getLength() > 0 &&
				unNoeud.getElementsByTagName("lon").getLength() > 0 &&
				unNoeud.getElementsByTagName("alt").getLength() > 0 &&
				unNoeud.getElementsByTagName("time").getLength() > 0 ) 
				{
					// lecture de la balise <hr> ("heart rate" : rythme cardiaque)
					String valeurNoeud = unNoeud.getElementsByTagName("hr").item(0).getTextContent();
					int rythmeCardio = Integer.parseInt(valeurNoeud);
					
					// lecture de la balise <lat>
					valeurNoeud = unNoeud.getElementsByTagName("lat").item(0).getTextContent();
					double latitude = Double.parseDouble(valeurNoeud);
					
					// lecture de la balise <lon>
					valeurNoeud = unNoeud.getElementsByTagName("lon").item(0).getTextContent();
					double longitude = Double.parseDouble(valeurNoeud);
					
					// lecture de la balise <alt>
					valeurNoeud = unNoeud.getElementsByTagName("alt").item(0).getTextContent();
					double altitude = Double.parseDouble(valeurNoeud);
					
					// lecture de la balise <time>
					valeurNoeud = unNoeud.getElementsByTagName("time").item(0).getTextContent();
					// passage du format "yyyy-MM-ddThh:mm:ssZ" au format "dd/MM/yyyy hh:mm:ss"
					String annee = valeurNoeud.substring(0, 4);
					String mois = valeurNoeud.substring(5, 7);
					String jour = valeurNoeud.substring(8, 10);
					String horaire = valeurNoeud.substring(11, 19);
					String chaineDateHeure = jour + "/" + mois + "/" + annee + " " + horaire;
					Date dateHeure = Outils.convertirEnDateHeure(chaineDateHeure);
					
					// cr�ation d'un point de trace
	                idPoint++;
					PointDeTrace unNouveauPoint = new PointDeTrace(idTrace, idPoint, latitude, longitude, altitude, dateHeure, rythmeCardio);
					
					// ajoute le point � l'objet laTraceAcreer
					laTraceAmaj.ajouterPoint(unNouveauPoint);				
				}				
			}
            // ferme le flux  en lecture
            unFluxEnLecture.close();
			
			return "";						// il n'y a pas de probl�me
		}
		catch (Exception ex)
		{	return "Erreur : " + ex.getMessage();	// il y a un probl�me
		}
	}
}
