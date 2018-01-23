package jim.classes;

// Cette classe abstraite d�termine les outils permettant de "parser" un fichier GPS pour mettre � jour un objet Trace.
// Derni�re mise � jour : 23/1/2018 par Jim

public abstract class PasserelleFichier extends Passerelle {
	
	// m�thode abstraite pour mettre � jour un objet Trace (vide) � partir n'un fichier GPS
	// param�tre nomFichier : le nom du fichier contenant la trace
	public abstract String creerTrace(String nomFichier, Trace laTraceAcreer);
}
