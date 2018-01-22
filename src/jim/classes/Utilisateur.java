package jim.classes;

import java.util.Date;

/**
 * Cette classe repr�sente un utilisateur de l'application<br>
 * Derni�re mise � jour : 22/1/2018<br>
 * @author JM<br>
 *
 */
public class Utilisateur {

	// attributs priv�s ---------------------------------------------------------------------------
	/**
	 * identifiant de l'utilisateur (num�ro automatique dans la BDD)
	 */
	private int _id;	
	/**
	 * pseudo de l'utilisateur
	 */
	private String _pseudo;	
	/**
	 * mot de passe de l'utilisateur (hash� en SHA1)
	 */
	private String _mdpSha1;	
	/**
	 * adresse mail de l'utilisateur
	 */
	private String _adrMail;		
	/**
	 * num�ro de t�l�phone de l'utilisateur
	 */
	private String _numTel;	
	/**
	 * niveau d'acc�s : 1 = utilisateur (pratiquant ou proche)    2 = administrateur
	 */
	private int _niveau;	
	/**
	 * date de cr�ation du compte
	 */
	private Date _dateCreation;	
	/**
	 * nombre de traces stock�es actuellement
	 */
	private int _nbTraces;
	/**
	 * date de d�but de la derni�re trace
	 */
	private Date _dateDerniereTrace;		
	
	
	
}
