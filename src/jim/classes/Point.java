package jim.classes;

/**
 * Cette classe repr�sente un point g�ographique avec une latitude, une longitude et une altitude<br>
 * Derni�re mise � jour : 21/1/2018<br>
 * @author JM<br>
 *
 */
public class Point {
	
	// attributs priv�s ---------------------------------------------------------------------------
	/**
	 * la latitude du point (en degr�s d�cimaux)
	 */
	private double _latitude;
	/**
	 * la longitude du point (en degr�s d�cimaux)
	 */
	private double _longitude;
	/**
	 * l'altitude du point (en m�tres)
	 */
	private double _altitude;
	
	// Constructeurs ------------------------------------------------------------------------------
	/**
	 * Constructeur sans param�tre
	 */
	public Point() {
		_latitude = 0;
		_longitude = 0;
		_altitude = 0;
	}
	/**
	 * Constructeur avec param�tres
	 * @param uneLatitude : latitude du nouveau point (en degr�s d�cimaux)
	 * @param uneLongitude : longitude du nouveau point (en degr�s d�cimaux)
	 * @param uneAltitude : altitude du nouveau point (en m�tres)
	 */
	public Point(double uneLatitude, double uneLongitude, double uneAltitude) {
		_latitude = uneLatitude;
		_longitude = uneLongitude;
		_altitude = uneAltitude;
	}
	
	// Accesseurs ---------------------------------------------------------------------------------
	/**
	 * Accesseur fournissant la latitude du point (en degr�s d�cimaux)
	 * @return la latitude du point (en degr�s d�cimaux)
	 */
	public double getLatitude() {
		return _latitude;
	}
	/**
	 * Mutateur pour modifier la latitude du point
	 * @param uneLatitude : la nouvelle latitude du point (en degr�s d�cimaux)
	 */
	public void setLatitude(double uneLatitude) {
		this._latitude = uneLatitude;
	}
	/**
	 * Accesseur fournissant la longitude du point (en degr�s d�cimaux)
	 * @return la longitude du point (en degr�s d�cimaux)
	 */
	public double getLongitude() {
		return _longitude;
	}
	/**
	 * Mutateur pour modifier la longitude du point
	 * @param uneLatitude : la nouvelle longitude du point (en degr�s d�cimaux)
	 */
	public void setLongitude(double uneLongitude) {
		this._longitude = uneLongitude;
	}
	/**
	 * Accesseur fournissant l'altitude du point (en m�tres)
	 * @return l'altitude du point (en m�tres)
	 */
	public double getAltitude() {
		return _altitude;
	}
	/**
	 * Mutateur pour modifier l'altitude du point
	 * @param uneLatitude : la nouvelle altitude du point (en m�tres)
	 */
	public void setAltitude(double uneAltitude) {
		this._altitude = uneAltitude;
	}	
	// M�thodes publiques -------------------------------------------------------------------------
	
	/**
	 * Calcul de la distance (en Km) entre 2 points g�ographiques.<br>
	 * Ce code est transpos� du forum JavaScript suivant :<br>
	 * www.clubic.com/forum/programmation/calcul-de-distance-entre-deux-coordonnees-gps-id178494-page1.html<br>
	 * CETTE FONCTION EST A TESTER ABSOLUMENT<br>
	 * (on pourra par exemple utiliser le site http://www.lexilogos.com/calcul_distances.htm)
	 * 
	 * @param latitude1  : latitude point 1 (en degr�s d�cimaux)
	 * @param longitude1 : longitude point 1 (en degr�s d�cimaux)
	 * @param latitude2  : latitude point 2 (en degr�s d�cimaux)
	 * @param longitude2 : longitude point 2 (en degr�s d�cimaux)
	 * @return           : la distance (en Km) entre les 2 points
	 */
	private static double getDistanceBetween (double latitude1, double longitude1, double latitude2, double longitude2)
	{
		if (latitude1 == latitude2 && longitude1 == longitude2) return 0;
		
		double a = Math.PI / 180; 
		latitude1 = latitude1 * a; 
		latitude2 = latitude2 * a; 
		longitude1 = longitude1 * a; 
		longitude2 = longitude2 * a;  
		double t1 = Math.sin(latitude1) * Math.sin(latitude2); 
		double t2 = Math.cos(latitude1) * Math.cos(latitude2); 
		double t3 = Math.cos(longitude1 - longitude2); 
		double t4 = t2 * t3; 
		double t5 = t1 + t4; 
		double rad_dist = Math.atan(-t5 / Math.sqrt(-t5 * t5 + 1)) + 2 * Math.atan(1);  
		return (rad_dist * 3437.74677 * 1.1508) * 1.6093470878864446;		
	}
	
	/**
	 * Calcul de la distance (en Km) entre 2 points g�ographiques.
	 * @param autrePoint : le deuxi�me point (le premier est l'instance courante)
	 * @return           : la distance (en Km) entre les 2 points
	 */
	public double getDistance (Point autrePoint) {
		double lat1 = this._latitude;
		double long1 = this._longitude;
		double lat2 = autrePoint._latitude;
		double long2 = autrePoint._longitude;
		double dist = getDistanceBetween(lat1, long1, lat2, long2);
		return dist;
	}
	
	/**
	 * Calcul de la distance (en Km) entre 2 points g�ographiques.
	 * @param point1 : le premier point
	 * @param point2 : le second point
	 * @return       : la distance (en Km) entre les 2 points
	 */
	public static double getDistance (Point point1, Point point2) {
		double lat1 = point1._latitude;
		double long1 = point1._longitude;
		double lat2 = point2._latitude;
		double long2 = point2._longitude;
		double dist = getDistanceBetween(lat1, long1, lat2, long2);
		return dist;
	}
	
	/**
	 * Fournit une chaine contenant toutes les donn�es de l'objet
	 */
	public String toString()
	{	String msg = "";
		msg += "Latitude :\t" + Outils.formaterNombre(this._latitude, "000.000") + "\n";
		msg += "Longitude :\t" + Outils.formaterNombre(this._longitude, "000.000") + "\n";
		msg += "Altitude :\t" + Outils.formaterNombre(this._altitude, "000.000") + "\n";
		return msg;
	}
	
} // fin de la classe
