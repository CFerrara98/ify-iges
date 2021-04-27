package it.unisa.di.is.gc1.ify.utenza;

/**
 * Eccezione lanciata quando il controllo sull'email di un utente fallisce
 * perché questo è già presente nel sistema.
 * 
 * @author Giacomo Izzo, Alessia Natale
 */
public class MailNonEsistenteException extends Exception {

	private static final long serialVersionUID = 6746818670147635153L;

	/** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
	private static final String MESSAGGIODEFAULT = "Email non presente nel sistema";

	/**
	 * Costruisce un'eccezione che ha come messaggio {@link #MESSAGGIODEFAULT}.
	 */
	public MailNonEsistenteException() {
		super(MESSAGGIODEFAULT);
	}

}
