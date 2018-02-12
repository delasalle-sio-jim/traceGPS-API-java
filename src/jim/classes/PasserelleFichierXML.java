// Projet TraceGPS - API Java
// Fichier : PasserelleFichier.java
// Cette classe abstraite h�rite de la classe Passerelle
// Elle pr�cise la signature de la m�thode pour "parser" un fichier GPS afin de mettre � jour un objet Trace fourni en param�tre.
// Derni�re mise � jour : 23/1/2018 par Jim

package jim.classes;

public abstract class PasserelleFichierXML extends PasserelleXML {
	
	// m�thode abstraite pour mettre � jour un objet Trace (vide) � partir d'un fichier GPS
	// param�tre nomFichier  : le nom du fichier contenant la trace
	// param�tre laTraceAmaj : l'objet Trace � mettre � jour
	public abstract String getUneTrace(String nomFichier, Trace laTraceAmaj);
}
