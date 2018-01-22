package jim.classes;

import java.util.Date;

/**
 * Cette classe repr�sente un point de passage de la trace.<br>
 * Elle h�rite de la classe Point.<br>
 * Elle y ajoute :<br>
 * - l'id de la trace<br>
 * - l'id du point (relatif � la trace)<br>
 * - l'heure de passage au point<br>
 * - le rythme cardiaque<br>
 * - le temps cumul� depuis le d�part (en secondes)<br>
 * - la distance cumul�e depuis le d�part (en Km)<br>
 * - la vitesse instantan�e, calcul�e entre le point pr�c�dent et le point actuel (en Km/h)<br>
 * <br>
 * Derni�re mise � jour : 21/1/2018<br>
 * @author JM<br>
 *
 */
public class PointDeTrace extends Point {

	// attributs priv�s ---------------------------------------------------------------------------

	/**
	 * l'identifiant de la trace
	 */
	private int _idTrace;
    /**
     * l'identifiant relatif du point dans la trace
     */
    private int _id;
	/**
	 * l'heure de passage au point (utilise un objet java.util.Date)
	 */
	private Date _dateHeure;
    /**
     * le rythme cardiaque au point
     */
    private int _rythmeCardio;
    /**
     * le temps cumul� depuis le d�part (en secondes)
     */
    private long _tempsCumule;
    /**
     * la distance cumul�e depuis le d�part (en Km)
     */
    private double _distanceCumulee;
    /**
     * la vitesse instantan�e, calcul�e entre le point pr�c�dent et le point actuel (en Km/h)
     */
    private double _vitesse;	
	
	
	// Constructeurs ------------------------------------------------------------------------------
	/**
	 * Constructeur sans param�tre
	 */
	public PointDeTrace() {
		// appelle le constructeur de la classe m�re
		super();
		// initialise les nouveaux champs
		_idTrace = 0;
		_id = 0;
		_dateHeure = null;
        _rythmeCardio = 0;
        _tempsCumule = 0;
        _distanceCumulee = 0;
        _vitesse = 0;		
	}
	/**
	 * Constructeur avec 6 param�tres
	 * @param idTrace            : identifiant de la trace
	 * @param id                 : identifiant relatif du point dans la trace
     * @param uneLatitude        : latitude du point (en degr�s d�cimaux)
     * @param uneLongitude       : longitude du point (en degr�s d�cimaux)
     * @param uneAltitude        : altitude du point (en m�tres)
     * @param uneDateHeure       : heure de passage au point
	 */
	public PointDeTrace(int idTrace, int id, double uneLatitude, double uneLongitude, double uneAltitude, Date uneDateHeure) {
		// appelle le constructeur de la classe m�re
		super(uneLatitude, uneLongitude, uneAltitude);
		// initialise les nouveaux champs
		_idTrace = idTrace;
		_id = id;
		_dateHeure = uneDateHeure;
        _rythmeCardio = 0;
        _tempsCumule = 0;
        _distanceCumulee = 0;
        _vitesse = 0;		
	}
    /**
     * Constructeur avec 7 param�tres
	 * @param idTrace            : identifiant de la trace
	 * @param id                 : identifiant relatif du point dans la trace
     * @param uneLatitude        : latitude du point (en degr�s d�cimaux)
     * @param uneLongitude       : longitude du point (en degr�s d�cimaux)
     * @param uneAltitude        : altitude du point (en m�tres)
     * @param uneDateHeure       : heure de passage au point
     * @param unRythmeCardio     : rythme cardiaque au passage au point
     */
    public PointDeTrace(int idTrace, int id, double uneLatitude, double uneLongitude, double uneAltitude, Date uneDateHeure, int unRythmeCardio) {
		// appelle le constructeur de la classe m�re
		super(uneLatitude, uneLongitude, uneAltitude);
		// initialise les nouveaux champs
		_idTrace = idTrace;
		_id = id;
        _dateHeure = uneDateHeure;
        _rythmeCardio = unRythmeCardio;
        _tempsCumule = 0;
        _distanceCumulee = 0;
        _vitesse = 0;
    }
    /**
     * Constructeur avec 10 param�tres
	 * @param idTrace            : identifiant de la trace
	 * @param id                 : identifiant relatif du point dans la trace
     * @param uneLatitude        : latitude du point (en degr�s d�cimaux)
     * @param uneLongitude       : longitude du point (en degr�s d�cimaux)
     * @param uneAltitude        : altitude du point (en m�tres)
     * @param uneDateHeure       : heure de passage au point
     * @param unRythmeCardio     : rythme cardiaque au passage au point
     * @param unTempsCumule      : temps cumul� depuis le d�part(en secondes)
     * @param uneDistanceCumulee : distance cumul�e depuis le d�part (en Km)
     * @param uneVitesse         : vitesse instantan�e, calcul�e entre le point pr�c�dent et le point suivant (en Km/h)
     */
    public PointDeTrace(int idTrace, int id, double uneLatitude, double uneLongitude, double uneAltitude, Date uneDateHeure, int unRythmeCardio,
        long unTempsCumule, double uneDistanceCumulee, double uneVitesse) {
		// appelle le constructeur de la classe m�re
		super(uneLatitude, uneLongitude, uneAltitude);
		// initialise les nouveaux champs
		_idTrace = idTrace;
		_id = id;
        _dateHeure = uneDateHeure;
        _rythmeCardio = unRythmeCardio;
        _tempsCumule = unTempsCumule;
        _distanceCumulee = uneDistanceCumulee;
        _vitesse = uneVitesse;
    }
    /**
     * Constructeur par recopie (ou clonage) d'un point existant
     * @param unPointExistant  : le point � cloner
     */
    public PointDeTrace(PointDeTrace unPointExistant) {
        // appelle le constructeur de la classe m�re
        super(unPointExistant.getLatitude(), unPointExistant.getLongitude(), unPointExistant.getAltitude());
        // initialise les nouveaux champs
		_idTrace = unPointExistant._idTrace;
		_id = unPointExistant._id;
        _dateHeure = unPointExistant._dateHeure;
        _rythmeCardio = unPointExistant._rythmeCardio;
        _tempsCumule = unPointExistant._tempsCumule;
        _distanceCumulee = unPointExistant._distanceCumulee;
        _vitesse = unPointExistant._vitesse;
    }
    
	// Accesseurs ---------------------------------------------------------------------------------
    /**
     * Accesseur fournissant l'identifiant de la trace
     * @return l'identifiant de la trace
     */
    public int getIdTrace() {
        return _idTrace;
    }
    /**
     * Mutateur pour modifier l'identifiant de la trace
     * @param unIdTrace : le nouveal identifiant de la trace
     */
    public void setIdTrace(int unIdTrace) {
        this._idTrace = unIdTrace;
    }
    
    /**
     * Accesseur fournissant l'identifiant relatif du point dans la trace
     * @return l'identifiant relatif du point dans la trace
     */
    public int getId() {
        return _id;
    }
    /**
     * Mutateur pour modifier l'identifiant relatif du point dans la trace
     * @param unId : le nouveal identifiant relatif du point dans la trace
     */
    public void setId(int unId) {
        this._id = unId;
    }

    /**
	 * Accesseur fournissant l'heure de passage au point
	 * @return l'heure de passage au point
	 */
	public Date getDateHeure() {
		return _dateHeure;
	}
	/**
	 * Mutateur pour modifier l'heure de passage au point
	 * @param uneLatitude : la nouvelle heure de passage au point
	 */
	public void setDateHeure(Date uneDateHeure) {
		this._dateHeure = uneDateHeure;
	}

    /**
     * Accesseur fournissant le rythme cardiaque au point
     * @return le rythme cardiaque au point
     */
    public int getRythmeCardio()
    {
        return _rythmeCardio;
    }
    /**
     * Mutateur pour modifier le rythme cardiaque au point
     * @param unRythmeCardio : le nouveau rythme cardiaque au point
     */
    public void setRythmeCardio(int unRythmeCardio)
    {
        this._rythmeCardio = unRythmeCardio;
    }

    /**
     * Accesseur fournissant le temps cumul� depuis le d�part (en secondes)
     * @return le temps cumul� depuis le d�part (en secondes)
     */
    public long getTempsCumule()
    {
        return _tempsCumule;
    }
    /**
     * Mutateur pour modifier le temps cumul� depuis le d�part (en secondes)
     * @param unTempsCumule : le temps cumul� depuis le d�part (en secondes)
     */
    public void setTempsCumule(long unTempsCumule)
    {
        this._tempsCumule = unTempsCumule;
    }

    /**
     * Accesseur fournissant la distance cumul�e depuis le d�part (en Km)
     * @return la distance cumul�e depuis le d�part (en Km)
     */
    public double getDistanceCumulee()
    {
        return _distanceCumulee;
    }
    /**
     * Mutateur pour modifier la distance cumul�e depuis le d�part (en Km)
     * @param uneDistanceCumulee : la distance cumul�e depuis le d�part (en Km)
     */
    public void setDistanceCumulee(double uneDistanceCumulee)
    {
        this._distanceCumulee = uneDistanceCumulee;
    }

    /**
     * Accesseur fournissant la vitesse instantan�e, calcul�e entre le point pr�c�dent et le point suivant (en Km/h)
     * @return la vitesse instantan�e, calcul�e entre le point pr�c�dent et le point suivant (en Km/h)
     */
    public double getVitesse()
    {
        return _vitesse;
    }
    /**
     * Mutateur pour modifier la vitesse instantan�e, calcul�e entre le point pr�c�dent et le point suivant (en Km/h)
     * @param uneVitesse : la vitesse instantan�e, calcul�e entre le point pr�c�dent et le point suivant (en Km/h)
     */
    public void setVitesse(double uneVitesse)
    {
        this._vitesse = uneVitesse;
    }
    
	// M�thodes publiques -------------------------------------------------------------------------
    /**
     * M�thode fournissant le temps cumul� depuis le d�part (sous la forme d'une chaine "00:00:00")
     * @return le temps cumul� depuis le d�part (sous la forme d'une chaine "00:00:00")
     */
    public String getTempsCumuleEnChaine()
    {
        long duree = _tempsCumule;
        int heures = (int)(duree / 3600);
        duree = duree - heures * 3600;
        int minutes = (int)(duree / 60);
        int secondes = (int)(duree % 60);

        return Outils.formaterNombre(heures, "00") + ":" + Outils.formaterNombre(minutes, "00") + ":" + Outils.formaterNombre(secondes, "00");
    }
    
	/**
	 * Fournit une chaine contenant toutes les donn�es de l'objet
	 */
	public String toString()
	{	String msg = "";
		msg += "Id trace :\t" + Outils.formaterNombre(_idTrace, "0") + "\n";
		msg += "Id point :\t" + Outils.formaterNombre(_id, "0") + "\n";	
		msg += super.toString();
		if (this._dateHeure != null)
			msg += "Heure de passage :\t" + Outils.formaterDateHeure(this._dateHeure) + "\n";
        msg += "Rythme cardiaque :\t" + Outils.formaterNombre(_rythmeCardio, "0") + "\n";
        msg += "Temps cumule (s) :\t" + Outils.formaterNombre(_tempsCumule, "0") + "\n";
        msg += "Temps cumule (hh:mm:ss) :\t" + getTempsCumuleEnChaine() + "\n";
        msg += "Distance cumul�e (Km) :\t" + Outils.formaterNombre(_distanceCumulee, "000.000") + "\n";
        msg += "Vitesse (Km/h) :\t" + Outils.formaterNombre(_vitesse, "000.000") + "\n";		
		return msg;
	}	
	
}
