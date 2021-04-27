package it.unisa.di.is.gc1.ify.Studente;

import javax.persistence.*;

/**
 * Classe che modella una richiesta di iscrizione
 * @author Geremia Cavezza     
 */

@Entity
public class RichiestaIscrizione {

	/**
	 * Costruttore che crea un oggetto Richiesta Iscrizione vuoto, 
	 * che verrà popolato con i metodi setters.
	 */
	public RichiestaIscrizione() {

	}

	/**
	 * Costruttore di una richiesta di iscrizione con parametri utile nei casi di test.
	 * @param stato è lo stato di una richiesta di iscrizione.
	 * @param studente è lo studente che effettua la richiesta di iscrizione.
	 */
	public RichiestaIscrizione(String stato, Studente studente) {
		this.stato = stato;
		this.studente = studente;
	}

	/**
	 * Metodo che ritorna l'id di una richiesta di iscrizione.
	 * @return id l'id di una richiesta di iscrizione.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Metodo che ritorna lo stato di una richiesta di iscrizione.
	 * @return stato lo stato di una richiesta di iscrizione.
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Metodo che setta lo stato di una richiesta di iscrizione.
	 * @param stato lo stato di una richiesta di iscrizione.
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * Metodo che ritorna lo studente di una richiesta di iscrizione.
	 * @return studente lo studente di una richiesta di iscrizione.
	 */
	public Studente getStudente() {
		return studente;
	}

	/**
	 * Metodo che setta lo studente di una richiesta di iscrizione.
	 * @param studente lo studente di una richiesta di iscrizione.
	 */
	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String stato;
	@OneToOne(cascade = CascadeType.ALL)
	private Studente studente;

	/**
	 * Costante che rappresenta lo stato "in attesa" di una richiesta di iscrizione.
	 * Una richiesta si trova in questo stato quando non e' ancora stata valutata.
	 */
	public static final String IN_ATTESA = "in attesa";

	/**
	 * Costante che rappresenta lo stato "accettata" di una richiesta di iscrizione.
	 * Una richiesta si trova in questo stato quando e' stata accettata dal
	 * responsabile ufficio tirocini.
	 */
	public static final String ACCETTATA = "accettata";

	/**
	 * Costante che rappresenta lo stato "rifiutata" di una richiesta di iscrizione.
	 * Una richiesta si trova in questo stato quando e' stata rifiutata dal
	 * responsabile ufficio tirocini.
	 */
	public static final String RIFIUTATA = "rifiutata";
}
