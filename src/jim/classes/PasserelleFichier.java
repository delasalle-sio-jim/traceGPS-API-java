package jim.classes;

// Cette classe abstraite détermine les outils permettant de "parser" un fichier GPS pour mettre à jour un objet Trace.
// Dernière mise à jour : 23/1/2018 par Jim

public abstract class PasserelleFichier extends Passerelle {
	
	// méthode abstraite pour mettre à jour un objet Trace (vide) à partir n'un fichier GPS
	// paramètre nomFichier : le nom du fichier contenant la trace
	public abstract String creerTrace(String nomFichier, Trace laTraceAcreer);
}
