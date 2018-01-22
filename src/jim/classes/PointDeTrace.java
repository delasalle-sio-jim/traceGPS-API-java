package jim.classes;

import java.util.Date;

// Cette classe repr�sente un point de passage de la trace.
// Elle h�rite de la classe Point.
// Elle y ajoute :
// - l'id de la trace
// - l'id du point (relatif � la trace)
// - l'heure de passage au point
// - le rythme cardiaque
// - le temps cumul� depuis le d�part (en secondes)
// - la distance cumul�e depuis le d�part (en Km)
// - la vitesse instantan�e, calcul�e entre le point pr�c�dent et le point actuel (en Km/h)
// Derni�re mise � jour : 22/1/2018 par Jim

public class PointDeTrace extends Point {

	// attributs priv�s ---------------------------------------------------------------------------

	private int _idTrace;				// l'identifiant de la trace
    private int _id;					// l'identifiant relatif du point dans la trace
	private Date _dateHeure;			// l'heure de passage au point (utilise un objet java.util.Date)
    private int _rythmeCardio;			// le rythme cardiaque au point
    private long _tempsCumule;			// le temps cumul� depuis le d�part (en secondes)
    private double _distanceCumulee;	// la distance cumul�e depuis le d�part (en Km)
    private double _vitesse;			// la vitesse instantan�e, calcul�e entre le point pr�c�dent et le point actuel (en Km/h)	
	
	// Constructeurs ------------------------------------------------------------------------------

    // Constructeur sans param�tre
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
	
	// Constructeur avec 6 param�tres
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
	
	// Constructeur avec 7 param�tres
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
    
	// Constructeur avec 10 param�tres
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

    // Constructeur par recopie (ou clonage) d'un point existant
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

    public int getIdTrace() {return _idTrace;}
    public void setIdTrace(int unIdTrace) {this._idTrace = unIdTrace;}
    
    public int getId() {return _id;}
    public void setId(int unId) {this._id = unId;}

	public Date getDateHeure() {return _dateHeure;}
	public void setDateHeure(Date uneDateHeure) {this._dateHeure = uneDateHeure;}

    public int getRythmeCardio() {return _rythmeCardio;}
    public void setRythmeCardio(int unRythmeCardio) {this._rythmeCardio = unRythmeCardio;}

    public long getTempsCumule() {return _tempsCumule;}
    public void setTempsCumule(long unTempsCumule) {this._tempsCumule = unTempsCumule;}

    public double getDistanceCumulee() {return _distanceCumulee;}
    public void setDistanceCumulee(double uneDistanceCumulee) {this._distanceCumulee = uneDistanceCumulee;}

    public double getVitesse() {return _vitesse;}
    public void setVitesse(double uneVitesse) {this._vitesse = uneVitesse;}
    
	// M�thodes publiques -------------------------------------------------------------------------

    // M�thode fournissant le temps cumul� depuis le d�part (sous la forme d'une chaine "00:00:00")
    public String getTempsCumuleEnChaine()
    {
        long duree = _tempsCumule;
        int heures = (int)(duree / 3600);
        duree = duree - heures * 3600;
        int minutes = (int)(duree / 60);
        int secondes = (int)(duree % 60);

        return Outils.formaterNombre(heures, "00") + ":" + Outils.formaterNombre(minutes, "00") + ":" + Outils.formaterNombre(secondes, "00");
    }
    
    // Fournit une chaine contenant toutes les donn�es de l'objet
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
