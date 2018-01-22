package jim.classes;

import java.util.ArrayList;
import java.util.Date;

/**
 * Cette classe repr�sente une trace au moyen d'une collection d'objets PointDeTrace<br>
 * Derni�re mise � jour : 21/1/2018<br>
 * @author JM<br>
 *
 */
public class Trace {

	// attributs priv�s ---------------------------------------------------------------------------
	/**
	 * l'identifiant de la trace
	 */
	private int _id;
	/**
	 * date et heure de d�but (utilise un objet java.util.Date)
	 */
	private Date _dateHeureDebut;	
	/**
	 * date et heure de fin (utilise un objet java.util.Date)
	 */
	private Date _dateHeureFin;	
	/**
	 * true si la trace est termin�e, false sinon
	 */
	private boolean _terminee;	
	/**
	 * l'identifiant de l'utilisateur ayant cr�� la trace
	 */
	private int _idUtilisateur;	
	/**
	 * la collection d'objets PointDeTrace
	 */
	private ArrayList<PointDeTrace> _lesPointsDeTrace;
	
	// Constructeurs ------------------------------------------------------------------------------
	/**
	 * Constructeur sans param�tre
	 */
	public Trace() {
		_id = 0;
		_dateHeureDebut = null;
		_dateHeureFin = null;
		_terminee = false;
		_idUtilisateur = 0;
		_lesPointsDeTrace = new ArrayList<PointDeTrace>();
	}

	/**
	 * Constructeur avec param�tre
	 */
	public Trace(int unId, Date uneDateHeureDebut, Date uneDateHeureFin, boolean terminee, int unIdUtilisateur) {
		_id = unId;
		_dateHeureDebut = uneDateHeureDebut;
		_dateHeureFin = uneDateHeureFin;
		_terminee = terminee;
		_idUtilisateur = unIdUtilisateur;
		_lesPointsDeTrace = new ArrayList<PointDeTrace>();
	}
	
	// Accesseurs ---------------------------------------------------------------------------------
    /**
     * Accesseur fournissant l'identifiant de la trace
     * @return l'identifiant de la trace
     */
    public int getId() {
        return _id;
    }
    /**
     * Mutateur pour modifier l'identifiant de la trace
     * @param unId : le nouveal identifiant de la trace
     */
    public void setId(int unId) {
        this._id = unId;
    }

    /**
	 * Accesseur fournissant l'heure de d�but
	 * @return l'heure de d�but
	 */
	public Date getDateHeureDebut() {
		return _dateHeureDebut;
	}
	/**
	 * Mutateur pour modifier l'heure de d�but
	 * @param uneDateHeureDebut : la nouvelle heure de d�but
	 */
	public void setDateHeureDebut(Date uneDateHeureDebut) {
		this._dateHeureDebut = uneDateHeureDebut;
	}    

    /**
	 * Accesseur fournissant l'heure de fin
	 * @return l'heure de d�but
	 */
	public Date getDateHeureFin() {
		return _dateHeureFin;
	}
	/**
	 * Mutateur pour modifier l'heure de fin
	 * @param uneDateHeureFin : la nouvelle heure de fin
	 */
	public void setDateHeureFin(Date uneDateHeureFin) {
		this._dateHeureFin = uneDateHeureFin;
	} 

    /**
     * Accesseur indiquant si la trace est termin�e
     * @return true si la trace est termin�e, false sinon
     */
    public boolean getTerminee() {
        return _terminee;
    }
    /**
     * Mutateur pour indiquer si la trace est termin�e
     * @param terminee : le nouvel �tat
     */
    public void setTerminee(boolean terminee) {
        this._terminee = terminee;
    }

    /**
     * Accesseur fournissant l'identifiant de l'utilisateur ayant cr�� la trace
     * @return l'identifiant de l'utilisateur ayant cr�� la trace
     */
    public int getIdUtilisateur() {
        return _idUtilisateur;
    }
    /**
     * Mutateur pour modifier l'identifiant de l'utilisateur ayant cr�� la trace
     * @param unIdTrace : le nouveal identifiant de l'utilisateur ayant cr�� la trace
     */
    public void setIdUtilisateur(int unIdUtilisateur) {
        this._idUtilisateur = unIdUtilisateur;
    }	
	
	/**
	 * Fournit la collection de points
	 * @return : la collection de points
	 */
	public ArrayList<PointDeTrace> getLesPointsDeTrace() {
		return _lesPointsDeTrace;
	}

	// M�thodes publiques -------------------------------------------------------------------------
	/**
	 * Fournit le nombre de points de passage
	 * @return : le nombre de points de passage
	 */
	public int getNombrePoints() {
		return _lesPointsDeTrace.size();
	}

	/**
	 * Fournit le point central du parcours
	 * @return : un objet Point (ou null si collection vide)
	 */
	public Point getCentre() {
		if (_lesPointsDeTrace.size() == 0)
			return null;
		else {
			// au d�part, les valeurs extr�mes sont celles du premier point
			PointDeTrace premierPoint = _lesPointsDeTrace.get(0);
			double latMini = premierPoint.getLatitude();
			double latMaxi = premierPoint.getLatitude();
			double longMini = premierPoint.getLongitude();
			double longMaxi = premierPoint.getLongitude();
			// parcours des autres points (� partir de la position 1)
			for (int i = 1 ; i < _lesPointsDeTrace.size(); i++) {
				PointDeTrace lePoint = _lesPointsDeTrace.get(i);
				if (lePoint.getLatitude() < latMini) latMini = lePoint.getLatitude();
				if (lePoint.getLatitude() > latMaxi) latMaxi = lePoint.getLatitude();
				if (lePoint.getLongitude() < longMini) longMini = lePoint.getLongitude();
				if (lePoint.getLongitude() > longMaxi) longMaxi = lePoint.getLongitude();
			}
			double latCentre = (latMini + latMaxi) / 2;
			double longCentre = (longMini + longMaxi) / 2;
			Point leCentre = new Point(latCentre, longCentre, 0);
			return leCentre;
		}
	}

	/**
	 * Fournit le d�nivel� (en m) entre le point le plus bas et le point le plus haut du parcours
	 * @return : le d�nivel� en m�tres (ou 0 si la collection est vide)
	 */
	public double getDenivele() {
		if (_lesPointsDeTrace.size() == 0)
			return 0;
		else {
			// au d�part, les valeurs extr�mes sont celles du premier point
			PointDeTrace premierPoint = _lesPointsDeTrace.get(0);
			double altMini = premierPoint.getAltitude();
			double altMaxi = premierPoint.getAltitude();
			// parcours des autres points (� partir de la position 1)
			for (int i = 1 ; i < _lesPointsDeTrace.size(); i++) {
				PointDeTrace lePoint = _lesPointsDeTrace.get(i);
				if (lePoint.getAltitude() < altMini) altMini = lePoint.getAltitude();
				if (lePoint.getAltitude() > altMaxi) altMaxi = lePoint.getAltitude();
			}
			double denivele = altMaxi - altMini;
			return denivele;
		}
	}
	
	/**
	 * Fournit la dur�e totale du parcours (en secondes)
	 * @return : la dur�e en secondes (ou 0 si la collection est vide)
	 */
	public long getDureeEnSecondes() {
		if (_lesPointsDeTrace.size() == 0)
			return 0;
		else {
			int positionDernierPoint = _lesPointsDeTrace.size() -1;
			PointDeTrace dernierPoint = _lesPointsDeTrace.get(positionDernierPoint);
            return dernierPoint.getTempsCumule();
		}
	}
	
	/**
	 * Fournit la dur�e totale du parcours sous forme d'une chaine "hh:mm:ss"
	 * @return : la dur�e (ou "00:00:00" si la collection est vide)
	 */
	public String getDureeTotale() {
		long duree = getDureeEnSecondes();
		if (duree == 0) {
			return "00:00:00";
		}
		else {
			int heures = (int) (duree / 3600);
			duree = duree - heures * 3600;
			int minutes = (int) (duree / 60);
			int secondes = (int) (duree % 60);
			return Outils.formaterNombre(heures, "00") + ":" + Outils.formaterNombre(minutes, "00") + ":" + Outils.formaterNombre(secondes, "00");
		}
	}
	
	/**
	 * Fournit la distance totale du parcours (en Km)
	 * @return : la distance totale du parcours (en Km)
	 */
	public double getDistanceTotale() {
		if (_lesPointsDeTrace.size() == 0)
			return 0;
		else {
			int positionDernierPoint = _lesPointsDeTrace.size() -1;
			PointDeTrace dernierPoint = _lesPointsDeTrace.get(positionDernierPoint);
            return dernierPoint.getDistanceCumulee();
		}
	}
	
    /**
     * Fournit le d�nivel� positif (en m)
     * @return : le d�nivel� positif (en m)
     */
    public double getDenivelePositif()
    {
        double denivele = 0;
        // parcours de tous les couples de points
        for (int i = 0; i < _lesPointsDeTrace.size() - 1; i+=1)
        {
            PointDeTrace lePoint1 = (PointDeTrace)_lesPointsDeTrace.get(i);
            PointDeTrace lePoint2 = (PointDeTrace)_lesPointsDeTrace.get(i + 1);
            // on teste si �a monte
            if ( lePoint2.getAltitude() > lePoint1.getAltitude() )
                denivele += lePoint2.getAltitude() - lePoint1.getAltitude();
        }
        return denivele;
    }

    /**
     * Fournit le d�nivel� n�gatif (en m)
     * @return : le d�nivel� n�gatif (en m)
     */
    public double getDeniveleNegatif()
    {
        double denivele = 0;
        // parcours de tous les couples de points
        for (int i = 0; i < _lesPointsDeTrace.size() - 1; i+=1)
        {
            PointDeTrace lePoint1 = (PointDeTrace)_lesPointsDeTrace.get(i);
            PointDeTrace lePoint2 = (PointDeTrace)_lesPointsDeTrace.get(i + 1);
            // on teste si �a descend
            if (lePoint2.getAltitude() < lePoint1.getAltitude())
                denivele += lePoint1.getAltitude() - lePoint2.getAltitude();
        }
        return denivele;
    }	
	
	/**
	 * Fournit la vitesse moyenne du parcours (en km/h)
	 * @return : la vitesse moyenne (ou 0 si la distance est nulle)
	 */
	public double getVitesseMoyenne() {
		if (getDistanceTotale() == 0)
			return 0;
		else {
			double vitesseEnKmH = getDistanceTotale() / (double) getDureeEnSecondes() * 3600;
			return vitesseEnKmH;
		}
	}
	
	/**
	 * Fournit une chaine contenant toutes les donn�es de l'objet
	 */
	public String toString() {
		String msg = "";
        msg += "Id : \t\t\t\t" + getId() + "\n";
        msg += "Utilisateur : \t\t\t" + getIdUtilisateur() + "\n";
        if (getDateHeureDebut() != null) msg += "Heure de d�but :\t\t" + Outils.formaterDateHeure(getDateHeureDebut()) + "\n";
        if (getTerminee()) msg += "Termin�e : Oui \n"; else msg += "Termin�e : Non \n";
		msg += "Nombre de points :\t\t" + Outils.formaterNombre(getNombrePoints(), "00000") + "\n";
		if (getNombrePoints() > 0) {
			if (getDateHeureFin() != null) msg += "Heure de fin :\t\t\t" + Outils.formaterDateHeure(getDateHeureFin()) + "\n";
			msg += "Dur�e en secondes :\t\t" + getDureeEnSecondes() + "\n";
			msg += "Dur�e totale :\t\t\t" + getDureeTotale() + "\n";
			msg += "Distance totale en Km :\t\t" + Outils.formaterNombre(getDistanceTotale(), "000.00") + "\n";
			msg += "D�nivel� en m :\t\t\t" + Outils.formaterNombre(getDenivele(), "0000.00") + "\n";
            msg += "D�nivel� positif en m :\t\t" + Outils.formaterNombre(getDenivelePositif(), "0000.00") + "\n";
            msg += "D�nivel� n�gatif en m :\t\t" + Outils.formaterNombre(getDeniveleNegatif(), "0000.00") + "\n";
			msg += "Vitesse moyenne en Km/h :\t" + Outils.formaterNombre(getVitesseMoyenne() , "00.00") + "\n";
			msg += "Centre du parcours :\n";
            msg += "   - Latitude :\t\t\t" + Outils.formaterNombre(getCentre().getLatitude(), "000.000") + "\n";
            msg += "   - Longitude :\t\t" + Outils.formaterNombre(getCentre().getLongitude(), "000.000") + "\n";
            msg += "   - Altitude :\t\t\t" + Outils.formaterNombre(getCentre().getAltitude(), "000.000") + "\n";
		}
		return msg;
	}
	
	/**
	 * ajoute un objet PointDeTrace � la collection
	 * @param unPoint : le point � ajouter
	 */
	public void ajouterPoint(PointDeTrace unPoint) {
        if (_lesPointsDeTrace.size() == 0)
        {   // si premier point de la trace, mise � z�ro des donn�es cumul�es et de la vitesse
            unPoint.setTempsCumule(0);
            unPoint.setDistanceCumulee(0);
            unPoint.setVitesse(0);
        }
        else
        {   // si d�j� d'autres points dans la trace, on cumule la dur�e et la distance avec celle du dernier point stock�
            PointDeTrace dernierPoint = (PointDeTrace) _lesPointsDeTrace.get(_lesPointsDeTrace.size()-1);

            long duree = (unPoint.getDateHeure().getTime() - dernierPoint.getDateHeure().getTime()) / 1000;		// en sec
            unPoint.setTempsCumule(dernierPoint.getTempsCumule() + duree);

            double distance = Point.getDistance(dernierPoint, unPoint);
            unPoint.setDistanceCumulee(dernierPoint.getDistanceCumulee() + distance);

            // calcul de la vitesse entre l'avant-dernier point et le point � ajouter
            if (_lesPointsDeTrace.size() >= 1)   // il faut au moins 1 point pr�c�dent
            {
                // distance et dur�e entre le dernier point et le nouveau point � ajouter
                distance = Point.getDistance(dernierPoint, unPoint);
                duree = (unPoint.getDateHeure().getTime() - dernierPoint.getDateHeure().getTime()) / 1000;	// en sec
                double vitesse = distance / (double) duree * 3600;
                // on affecte la vitesse calcul�e au nouveau point
                unPoint.setVitesse(vitesse);
            }
        }
		_lesPointsDeTrace.add(unPoint);
	}
	
	/**
	 * vide la collection
	 */
	public void viderListePoints() {
		_lesPointsDeTrace.clear();
	}

    /**
     * lisse les donn�es (altitude surtout)
     * fournit un nouvel objet Trace avec les donn�es liss�es
     */
    public Trace lisserDonnees()
    {
        Trace laTraceLissee = new Trace();
        for (int i = 0 ; i < _lesPointsDeTrace.size() ; i++)
        {
            PointDeTrace lePointCourant = (PointDeTrace)_lesPointsDeTrace.get(i);
            // clonage du point original
            PointDeTrace lePointAvecMoyenne = new PointDeTrace(lePointCourant);
            // correction de l'altitude : calcul de l'altitude moyenne des points n-2, n-1, n, n+1, n+2
            if (i > 1 && i < _lesPointsDeTrace.size() - 2)
            {
                PointDeTrace lePointMoins2 = (PointDeTrace)_lesPointsDeTrace.get(i-2);
                PointDeTrace lePointMoins1 = (PointDeTrace)_lesPointsDeTrace.get(i-1);
                PointDeTrace lePointPlus1 = (PointDeTrace)_lesPointsDeTrace.get(i+1);
                PointDeTrace lePointPlus2 = (PointDeTrace)_lesPointsDeTrace.get(i+2);
                double altitudeMoyenne = (lePointMoins2.getAltitude() + lePointMoins1.getAltitude() + lePointCourant.getAltitude() + lePointPlus1.getAltitude() + lePointPlus2.getAltitude()) / 5;
                lePointAvecMoyenne.setAltitude(altitudeMoyenne);
            }
            laTraceLissee.ajouterPoint(lePointAvecMoyenne);
        }
        return laTraceLissee;
    }
}
