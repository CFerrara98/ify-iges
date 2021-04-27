package it.unisa.di.is.gc1.ify.Studente;

/**
 * Eccezione generata in caso di parametri non validi durante il controllo di
 * una richiesta d'iscrizione
 * @author Giacomo Izzo Carmine Ferrara
 */
public class RichiestaIscrizioneNonValidaException extends Exception {

	private static final long serialVersionUID = 2441773366582183446L;
	
	/**
	 * Parametro indicante il tipo di errore avvenuto
	 */
	private String target;
	
	/** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
	private static final String MESSAGGIO_DEFAULT = "Richiesta d'iscrizione non valida";

	/**
	 * Genera un'eccezione che riporta come messaggio il messaggio di default:
	 * {@link #MESSAGGIO_DEFAULT}.
	 */
	public RichiestaIscrizioneNonValidaException() {
		super(MESSAGGIO_DEFAULT);
	}

	/**
	 * Genera un'eccezione che riporta come messaggio un messaggio passato come
	 * parametro
	 * 
	 * @param messaggio Stringa che rappresenta il messaggio da mostrare
	 *               nell'output dell'eccezione
	 */
	public RichiestaIscrizioneNonValidaException(String messaggio) {
		super(messaggio);
	}

	
	/**
	 * Genera un'eccezione  etichettata con parametro di discriminazione
	 * che riporta come messaggio, un messaggio passato come parametro
	 * 
	 * @param target Stringa che rappresenta il tipo di errore generato
	 * @param messaggio Stringa che rappresenta il messaggio da mostrare nell'output dell'eccezione
	 */
	public RichiestaIscrizioneNonValidaException(String target, String messaggio) {
		super(messaggio);
		this.target = target;
	}
	
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
