package it.unisa.di.is.gc1.ify.utenza;

/**
 * Eccezione lanciata quando il login fallisce poiché le credenziali inserite
 * non sono presenti nel sistema.
 * 
 * @author Giacomo Izzo, Alessia Natale
 */
public class PasswordNonValidaException extends Exception {

	private static final long serialVersionUID = -3212124280736230614L;

	/** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
	private static final String MESSAGGIODEFAULT = "Password non valida";

	/**
	 * Costruisce un'eccezione che ha come messaggio {@link #MESSAGGIODEFAULT}.
	 */
	public PasswordNonValidaException() {
		super(MESSAGGIODEFAULT);
	}

}