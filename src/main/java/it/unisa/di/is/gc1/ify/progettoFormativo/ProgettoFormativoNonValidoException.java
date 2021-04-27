package it.unisa.di.is.gc1.ify.progettoFormativo;

/**
 * Eccezione generata in caso di parametri non validi durante il controllo di
 * un progetto formativo
 * 
 * @author Simone Civale Carmine Ferrara
 */
public class ProgettoFormativoNonValidoException extends Exception {

	private static final long serialVersionUID = 25517774654648998L;
												
	/**
	 * Parametro indicante il tipo di errore avvenuto
	 */
	private String target;
	
	/** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
	private static final String MESSAGGIO_DEFAULT = "Progetto Formativo non valido";

	/**
	 * Genera un'eccezione che riporta come messaggio il messaggio di default:
	 * {@link #MESSAGGIO_DEFAULT}.
	 */
	public ProgettoFormativoNonValidoException() {
		super(MESSAGGIO_DEFAULT);
	}

	/**
	 * Genera un'eccezione che riporta come messaggio, un messaggio passato come
	 * parametro
	 * 
	 * @param messaggio Stringa che rappresenta il messaggio da mostrare
	 *               nell'output dell'eccezione
	 */
	public ProgettoFormativoNonValidoException(String messaggio) {
		super(messaggio);
	}

	
	/**
	 * Genera un'eccezione etichettata con parametro di discriminazione
	 * che riporta come messaggio, un messaggio passato come parametro
	 * 
	 * @param target Stringa che rappresenta il tipo di errore generato
	 * @param messaggio Stringa che rappresenta il messaggio da mostrare nell'output dell'eccezione
	 */
	public ProgettoFormativoNonValidoException(String target, String messaggio) {
		super(messaggio);
		this.target = target;
	}
	
	/**
	 * Metodo che ritorna la stringa che rappresenta il tipo di errore avvenuto.
	 * @return target e' la stringa che rappresenta il tipo di errore avvenuto.
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Metodo che setta la stringa che rappresenta il tipo di errore avvenuto.
	 * @param target e' la stringa che rappresenta il tipo di errore avvenuto.
	 */
	public void setTarget(String target) {
		this.target = target;
	}
}
