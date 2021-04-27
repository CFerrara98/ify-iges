package it.unisa.di.is.gc1.ify.Studente;

import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinio;
import it.unisa.di.is.gc1.ify.utenza.Utente;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che modella uno studente
 * @author Geremia Cavezza
 */

@Entity
public class Studente extends Utente {

	/**
	 * Costruisce un oggetto Studente vuoto che deve essere popolato con i metodi
	 * setters.
	 */
	public Studente() {
		this.domandeTirocinio = new ArrayList<DomandaTirocinio>();
	}

	/**
	 * Costruttore di un oggetto Studente con parametri utile nei casi di test.
	 * @param nome e' il nome dello studente.
	 * @param cognome e' il cognome dello studente.
	 * @param sesso e' il sesso dello studente.
	 * @param email e' l'email dello studente.
	 * @param indirizzo e' l'indirizzo dello studente.
	 * @param password e' la password dello studente.
	 * @param matricola e' la matricola dello studente.
	 * @param dataNascita e' la data di nascita dello studente.
	 * @param telefono e' il telefono dello studente.
	 */
	public Studente(String nome, String cognome, String sesso, String email, String indirizzo, String password,
			String matricola, LocalDate dataNascita, String telefono) {
		super(nome, cognome, sesso, email, indirizzo, password);
		this.matricola = matricola;
		this.dataNascita = dataNascita;
		this.telefono = telefono;
		this.domandeTirocinio = new ArrayList<DomandaTirocinio>();
	}

	/**
	 * Metodo che ritorna la matricola di uno studente.
	 * @return matricola
	 */
	public String getMatricola() {
		return matricola;
	}

	/**
	 * Metodo che setta la matricola di uno studente.
	 * @param matricola
	 */
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	/**
	 * Metodo che ritorna la data di nascita di uno studente.
	 * @return dataNascita
	 */
	public LocalDate getDataNascita() {
		return dataNascita;
	}

	/**
	 * Metodo che setta la data di nascita di uno studente.
	 * @param dataNascita
	 */
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * Metodo che ritorna il telefono di uno studente.
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Metodo che setta il telefono di uno studente.
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Metodo che ritorna la lista delle domande di tirocinio di uno studente.
	 * @return domandeTirocinio
	 */
	public List<DomandaTirocinio> getDomandeTirocinio() {
		return domandeTirocinio;
	}

	/**
	 * Metodo che setta la lista delle domande di tirocinio di uno studente.
	 * @param domandeTirocinio
	 */
	public void setDomandeTirocinio(List<DomandaTirocinio> domandeTirocinio) {
		this.domandeTirocinio = domandeTirocinio;
	}

	@Column(unique = true)
	private String matricola;
	private LocalDate dataNascita;
	private String telefono;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studente")
	private List<DomandaTirocinio> domandeTirocinio;

	/** Espressione regolare che definisce il formato del campo matricola. */
	public static final String MATRICOLA_PATTERN = "^[0-9]{10}$";

	/** Espressione regolare che definisce il formato del campo telefono. */
	public static final String TELEFONO_PATTERN = "^[0-9]{3}[\\-]?[0-9]{7}$";

	/** Espressione regolare che definisce il formato del campo Email Studente */
	public static final String EMAIL_STUDENTE_PATTERN = "^([A-Za-z]\\.[A-Za-z]+[1-9]?[0-9]*)@studenti.unisa.it$";

	/** Valore che definisce la minima data di nascita accettabile. */
	public static final LocalDate MIN_DATE = LocalDate.of(1900, Month.JANUARY, 1);

	/** Valore che definisce la massima data di nascita accettabile. */
	public static final LocalDate MAX_DATE = LocalDate.now().minusYears(19L);

	/**
	 * Costante che definisce la minima lunghezza dei campi nome, cognome e
	 * indirizzo.
	 */
	public static final int MIN_LUNGHEZZA_TELEFONO = 10;

	/**
	 * Costante che definisce la minima lunghezza dei campi nome, cognome e
	 * indirizzo.
	 */
	public static final int MAX_LUNGHEZZA_TELEFONO = 11;

	/** Costante che definisce la lunghezza del campo matricola. */
	public static final int LUNGHEZZA_MATRICOLA = 10;
}
