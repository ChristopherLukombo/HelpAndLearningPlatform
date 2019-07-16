package fr.esgi.config;

/**
 * 
 * @author christopher
 *
 */
public final class ErrorMessage {

	// Account
	public static final String EMAIL_IS_ALREADY_USED = "Email is already used.";
	public static final String LOGIN_IS_ALREADY_REGISTERED = "Login is already registered.";
	public static final String PASS_IS_NOT_VALID = "Password is not valid.";

	// Category
	public static final String NO_CATEGORIES = "Pas de catégories";

	// Comment
	public static final String A_COMMENT_CANNOT_HAVE_AN_ID = "Un commentaire doit avoir un id.";
	public static final String A_NEW_COMMENT_CANNOT_HAVE_ALREADY_HAVE_UN_ID = "Un nouveau commentaire ne peut pas déjà avoir un id.";
	public static final String NO_COMMENT = "Pas de commentaire";

	// Contact
	public static final String ERROR_TRYING_TO_CONTACT_COMPANY = "Error trying to contact Company";

	// Notation
	public static final String A_NOTATION_SHOULD_HAVE_AN_ID = "A notation should have an ID";
	public static final String A_NEW_NOTATION_CANNOT_ALREADY_HAVE_AN_ID = "A new notation cannot already have an ID";

	// QCMAnswers
	public static final String A_NEW_QCM_ANSWERS_CANNOT_ALREADY_HAVE_AN_ID = "A new qcmAnswers cannot already have an ID";

	// Subscription
	public static final String THE_FIELD_SUBSCRIPTION_ID_CAN_BE_EMPTY = "The field subscriptionId can be empty.";
	public static final String TRICK_IS_ALREADY_SUBSCRIBED_BY_THE_USER = "Trick is already subscribed by the user";
	public static final String A_NEW_SUBSCRIPTION_CANNOT_ALREADY_HAVE_AN_ID = "A new subscription cannot already have an ID";

	// Trick
	public static final String ERORR_ID_OF_TRICK_CANNOT_BE_NUL = "Erreur l'id du trick ne peut être nul !";
	public static final String TRICK_NOT_FOUND = "Trick non trouvé";
	public static final String NO_TRICKS = "Pas de tricks";

	private ErrorMessage() {}
}
