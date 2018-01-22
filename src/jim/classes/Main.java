package jim.classes;

public class Main {

	public static void main(String[] args) {
        // test des passerelles
        // les fichiers de donn�es sont plac�s dans le dossier d'ex�cution
        String nomFichier;

        //nomFichier = "fit-20161203T102115.gpx";
        //nomFichier = "fit-20161203T102115.pwx";
        nomFichier = "fit-20161203T102115.tcx";

        Trace laTrace = new Trace();
        Passerelle laPasserelle = null;

        // cr�ation de la passerelle en fonction du type de fichier
        if (nomFichier.toLowerCase().endsWith(".gpx")) laPasserelle = new PasserelleGPX();
        if (nomFichier.toLowerCase().endsWith(".pwx")) laPasserelle = new PasserellePWX();
        if (nomFichier.toLowerCase().endsWith(".tcx")) laPasserelle = new PasserelleTCX();

        String msg = laPasserelle.creerTrace(nomFichier, laTrace);
		
		if ( msg.equals("") )
			System.out.println(laTrace.toString());		// si aucune erreur
		else
			System.out.println(msg);					// si erreur retourn�e par la passerelle
	}
}
