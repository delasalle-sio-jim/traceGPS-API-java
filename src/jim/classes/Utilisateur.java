package jim.classes;

import java.util.Date;

/**
 * Cette classe représente un utilisateur de l'application<br>
 * Dernière mise à jour : 22/1/2018<br>
 * @author JM<br>
 *
 */
public class Utilisateur {

	// attributs privés ---------------------------------------------------------------------------
	/**
	 * identifiant de l'utilisateur (numéro automatique dans la BDD)
	 */
	private int _id;	
	/**
	 * pseudo de l'utilisateur
	 */
	private String _pseudo;	
	/**
	 * mot de passe de l'utilisateur (hashé en SHA1)
	 */
	private String _mdpSha1;	
	/**
	 * adresse mail de l'utilisateur
	 */
	private String _adrMail;		
	/**
	 * numéro de téléphone de l'utilisateur
	 */
	private String _numTel;	
	/**
	 * niveau d'accès : 1 = utilisateur (pratiquant ou proche)    2 = administrateur
	 */
	private int _niveau;	
	/**
	 * date de création du compte
	 */
	private Date _dateCreation;	
	/**
	 * nombre de traces stockées actuellement
	 */
	private int _nbTraces;
	/**
	 * date de début de la dernière trace
	 */
	private Date _dateDerniereTrace;		
	
	
	
}
