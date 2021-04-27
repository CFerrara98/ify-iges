package it.unisa.di.is.gc1.ify.convenzioni;

import it.unisa.di.is.gc1.ify.utenza.Utente;

import javax.persistence.*;

/**
 * 
 * @author Carmine Ferrara - Simone Civale
 * classe che modella i dati relativi ad un delegato aziendale
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DelegatoAziendale extends Utente {
	
	/**
	 * Costruisce un oggetto Delegato aziendale vuoto che deve essere popolato con i metodi
	 * setters.
	 * 
	 * */
	public DelegatoAziendale() {
		super();
	}
	
	
	/**
	 * costruttore di un delegato con parametri utile nei casi di test
	 * @param nome
	 * @param cognome
	 * @param sesso
	 * @param email
	 * @param indirizzo
	 * @param password
	 * @param ruolo
	 * @param azienda
	 */
	public DelegatoAziendale(String nome, String cognome, String sesso, String email, String indirizzo,
			String password, String ruolo, Azienda azienda) {
		super(nome, cognome, sesso, email, indirizzo, password);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Restituisce il campo ruolo
	 * @return ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}

	/**
	 * Setta il ruolo
	 * @param ruolo
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * Restituisce l'azienda associata
	 * @return azienda associata
	 */
	public Azienda getAzienda() {
		return azienda;
	}

	/**
	 * Setta l'azienda associata
	 * @param azienda
	 */
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}



	String ruolo;
	@OneToOne(cascade = CascadeType.ALL)
	Azienda azienda;
	
	/** Espressione regolare che definisce il formato del campo ruolo. */
	public static final String RUOLO_PATTERN = "^[a-z A-Z àéèìòù]{2,255}$";
	
	/**
	 * Costante che definisce la minima lunghezza del campo ruolo.
	 */
	public static final int MIN_LUNGHEZZA_RUOLO = 2;

	/**
	 * Costante che definisce la massima lunghezza del campo ruolo.
	 */
	public static final int MAX_LUNGHEZZA_RUOLO =255;

	
}
