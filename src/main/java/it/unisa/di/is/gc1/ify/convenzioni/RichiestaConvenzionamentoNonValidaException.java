package it.unisa.di.is.gc1.ify.convenzioni;

/**
 * Eccezione generata in caso di parametri non validi durante il controllo di
 * una richiesta di convenzionamento
 * 
 * @author Alessia Natale, Giacomo Izzo
 */

public class RichiestaConvenzionamentoNonValidaException extends Exception {

	private static final long serialVersionUID = 2147587512125895022L;

	/**
	 * Parametro indicante il tipo di errore avvenuto
	 */
	private String target;
	
	/** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
	private static final String MESSAGGIO_DEFAULT = "Richiesta di convenzionamento non valida";

	/**
	 * Genera un'eccezione che riporta come messaggio il messaggio di default:
	 * {@link #MESSAGGIO_DEFAULT}.
	 */
	public RichiestaConvenzionamentoNonValidaException() {
		super(MESSAGGIO_DEFAULT);
	}

	/**
	 * Genera un'eccezione che riporta come messaggio, un messaggio passato come
	 * parametro
	 * 
	 * @param messaggio Stringa che rappresenta il messaggio da mostrare
	 *               nell'output dell'eccezione
	 */
	public RichiestaConvenzionamentoNonValidaException(String messaggio) {
		super(messaggio);
	}

	
	/**
	 * Genera un'eccezione etichettata con parametro di discriminazione
	 * che riporta come messaggio, un messaggio passato come parametro
	 * 
	 * @param target Stringa che rappresenta il tipo di errore generato
	 * @param messaggio Stringa che rappresenta il messaggio da mostrare nell'output dell'eccezione
	 */
	public RichiestaConvenzionamentoNonValidaException(String target, String messaggio) {
		super(messaggio);
		this.target = target;
	}
	
	/**
	 * Restituisce il target associato all'exception
	 * @return target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Configura il target associato all'exception
	 * @param target
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
}
