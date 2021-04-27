package it.unisa.di.is.gc1.ify.Studente;

/**
 * Eccezione generata in caso di richiesta di operazioni per cui non si ha l'autorizzazione
 * @author Giacomo Izzo Geremia Cavezza
 */

public class OperazioneNonAutorizzataException extends Exception {

	private static final long serialVersionUID = -8425780939491274221L;
	
	/** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
	private static final String MESSAGGIO_DEFAULT = "Operazione non autorizzata";

	/**
	 * Genera un'eccezione che riporta come messaggio il messaggio di default:
	 * {@link #MESSAGGIO_DEFAULT}.
	 */
	public OperazioneNonAutorizzataException() {
		super(MESSAGGIO_DEFAULT);
	}

	/**
	 * Genera un'eccezione che riporta come messaggio un messaggio passato come
	 * parametro
	 * 
	 * @param messaggio Stringa che rappresenta il messaggio da mostrare
	 *               nell'output dell'eccezione
	 */
	public OperazioneNonAutorizzataException(String messaggio) {
		super(messaggio);
	}
}
