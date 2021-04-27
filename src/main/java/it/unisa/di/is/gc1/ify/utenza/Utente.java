package it.unisa.di.is.gc1.ify.utenza;

import javax.persistence.*;

/**
 * 
 * @author Geremia Cavezza
 *
 *         Classe astratta che modella un utente generico registrato
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utente {
	
	/**
	 * Costruttore che crea un oggeto Utente vuoto, 
	 * che verra' popolato con i metodi setters.
	 */
	public Utente() {

	}
	
	/**
	 * Costruttore di un utente con parametri utili nei casi di test.
	 * @param nome e' il nome dell'utente.
	 * @param cognome e' il cognome dell'utente.
	 * @param sesso e' il sesso dell'utente.
	 * @param email e' l'email dell'utente.
	 * @param indirizzo e' l'indirizzo dell'utente.
	 * @param password e' la password dell'utente.
	 */
	public Utente(String nome, String cognome, String sesso, String email, String indirizzo, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.email = email.toLowerCase();
		this.indirizzo = indirizzo;
		this.password = password;
	}

	/**
	 * Metodo che ritorna l'id dell'utente.
	 * @return id e'l'id dell'utente.
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Metodo che ritorna il nome dell'utente.
	 * @return nome e' il nome dell'utente.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che setta il nome dell'utente.
	 * @param nome e' il nome dell'utente.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo che ritorna il cognome dell'utente.
	 * @return cognome e' il cognome dell'utente.
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Metodo che setta il cognome dell'utente.
	 * @param cognome e' il cognome dell'utente.
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Metodo che ritorna il sesso dell'utente.
	 * @return sesso e' il sesso dell'utente.
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * Metodo che setta il sesso dell'utente.
	 * @param sesso e' il sesso dell'utente.
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * Metodo che ritorna l'email dell'utente.
	 * @return email e' l'email dell'utente.
	 */
	public String getEmail() {
		return email.toLowerCase();
	}

	/**
	 * Metodo che setta l'email dell'utente.
	 * @param  email e' l'email dell'utente.
	 */
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	/**
	 * Metodo che ritorna l'indirizzo dell'utente.
	 * @return indirizzo e' l'indirizzo dell'utente.
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Metodo che setta l'indirizzo dell'utente.
	 * @param  indirizzo e' l'indirizzo dell'utente.
	 */	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Metodo che ritorna la password dell'account dell'utente.
	 * @return password e' la password dell'account dell'utente.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metodo che setta la password dell'account dell'utente.
	 * @param  password e' la password dell'account dell'utente.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	protected String nome;
	protected String cognome;
	protected String sesso;
	@Column(unique = true)
	protected String email;
	protected String indirizzo;
	protected String password;

	/** Espressione regolare che definisce il formato del campo nome. */
	public static final String NOME_PATTERN = "^[a-z A-Zàéèìòù]{2,255}$";

	/** Espressione regolare che definisce il formato del campo cognome. */
	public static final String COGNOME_PATTERN = "^[a-z A-Zàéèìòù]{2,255}$";

	/** Costante che rappresenta il genere maschile per l'utente. */
	public static final String SESSO_PATTERN = "^[MF]{1}$";

	/** Espressione regolare che definisce il formato del campo email. */
	public static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/"
			+ "=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f"
			+ "\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x"
			+ "0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-"
			+ "9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25["
			+ "0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2"
			+ "[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\"
			+ "x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]"
			+ "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	/** Espressione regolare che definisce il formato del campo indirizzo. */
	public static final String INDIRIZZO_PATTERN = "^[a-z A-Z 0-9 àéèìòù ',.-]{2,255}$";

	/** Espressione regolare che definisce il formato del campo password. */
	public static final String PASSWORD_PATTERN = "^(?=([^\\s])*[0-9])(?=([^\\s])*[a-zA-Z])([^\\s]){8,24}$";

	/**
	 * Costante che definisce la minima lunghezza dei campi nome, cognome ,
	 * indirizzo.
	 */
	public static final int MIN_LUNGHEZZA_CAMPO = 2;

	/**
	 * Costante che definisce la massima lunghezza dei campi nome, cognome e
	 * indirizzo.
	 */
	public static final int MAX_LUNGHEZZA_CAMPO = 255;

	/** Costante che definisce la massima lunghezza del campo mail. */
	public static final int MAX_LUNGHEZZA_MAIL = 256;

	/** Costante che definisce la minima lunghezza dei campo password. */
	public static final int MIN_LUNGHEZZA_PASSWORD = 8;

	/** Costante che definisce la massima lunghezza dei campo password. */
	public static final int MAX_LUNGHEZZA_PASSWORD = 24;

}
