package it.unisa.di.is.gc1.ify.web;


/**
 * Oggetto utilizzato per mappare i campi di un form di azienda e delegato. Questo oggetto
 * viene passato come parametro al controller dalla dispatcher servlet quando un utente 
 * sottomette il modulo di convenzionamento.
 * @author Geremia Cavezza
 */

public class ConvenzioneForm {
	
	public ConvenzioneForm() {
		
	}

	/**
	 * Costruttore di un oggetto ConvenzioneForm
	 * @param nome nome del delegato
	 * @param cognome cognome del delegato
	 * @param indirizzo indirizzo del delegato
	 * @param sesso sesso del delegato
	 * @param ruolo ruolo del delegato
	 * @param email email del delegato
	 * @param password password del delegato
	 * @param confermaPassword conferma password del delegato
	 * @param condizioniDelegato condizioni del delegato
	 * @param ragioneSociale ragione sociale dell'azienda
	 * @param sede sede dell'azienda
	 * @param pIva partita iva dell'azienda
	 * @param settore settore dell'azienda
	 * @param descrizione descrizione dell'azienda
	 * @param condizioniAzienda condizioni dell'azienda
	 */
	public ConvenzioneForm(String nome, String cognome, String indirizzo, String sesso, String ruolo, String email,
			String password, String confermaPassword, String condizioniDelegato, String ragioneSociale, String sede,
			String pIva, String settore, String descrizione, String condizioniAzienda) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.sesso = sesso;
		this.ruolo = ruolo;
		this.email = email.toLowerCase();
		this.password = password;
		this.confermaPassword = confermaPassword;
		this.condizioniDelegato = condizioniDelegato;
		this.ragioneSociale = ragioneSociale;
		this.sede = sede;
		this.pIva = pIva;
		this.settore = settore;
		this.descrizione = descrizione;
		this.condizioniAzienda = condizioniAzienda;
	}	
	
	/**
	 * Metodo che ritorna il nome del delegato
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * metodo che setta il nome del delegato
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Metodo che ritorna il cognome del delegato
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * metodo che setta il cognome del delegato
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * Metodo che ritorna l'indirizzo del delegato
	 * @return indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * metodo che setta l'indirizzo del delegato
	 * @param indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	/**
	 * Metodo che ritorna il sesso del delegato
	 * @return sesso
	 */
	public String getSesso() {
		return sesso;
	}
	
	/**
	 * metodo che setta il sesso del delegato
	 * @param sesso
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	/**
	 * Metodo che ritorna il ruolo del delegato
	 * @return ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}
	
	/**
	 * metodo che setta il ruolo del delegato
	 * @param ruolo
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	/**
	 * Metodo che ritorna la email del delegato
	 * @return email
	 */
	public String getEmail() {
		return email.toLowerCase();
	}
	
	/**
	 * metodo che setta la mail del delegato
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	
	/**
	 * Metodo che ritorna la password del delegato
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * metodo che setta la password del delegato
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Metodo che ritorna la conferma password del delegato
	 * @return confermaPassword
	 */
	public String getConfermaPassword() {
		return confermaPassword;
	}
	
	/**
	 * metodo che setta la conferma password del delegato
	 * @param confermaPassword
	 */
	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}
	
	/**
	 * Metodo che ritorna le condizioni del delegato
	 * @return condizioniDelegato
	 */
	public String getCondizioniDelegato() {
		return condizioniDelegato;
	}
	
	/**
	 * metodo che setta le condizioni del delegato
	 * @param condizioniDelegato
	 */
	public void setCondizioniDelegato(String condizioniDelegato) {
		this.condizioniDelegato = condizioniDelegato;
	}
	
	/**
	 * Metodo che ritorna la ragione sociale dell'azienda
	 * @return ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	
	/**
	 * metodo che setta la ragione sociale dell'azienda
	 * @param ragioneSociale
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	/**
	 * Metodo che ritorna la sede dell'azienda
	 * @return sede
	 */
	public String getSede() {
		return sede;
	}
	
	/**
	 * metodo che setta la sede dell'azienda
	 * @param sede
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	
	/**
	 * Metodo che ritorna la partita iva dell'azienda
	 * @return pIva
	 */
	public String getpIva() {
		return pIva;
	}
	
	/**
	 * metodo che setta la partita iva dell'azienda
	 * @param pIva
	 */
	public void setpIva(String pIva) {
		this.pIva = pIva;
	}
	
	/**
	 * Metodo che ritorna il settore dell'azienda
	 * @return settore
	 */
	public String getSettore() {
		return settore;
	}
	
	/**
	 * metodo che setta il settore dell'azienda
	 * @param settore
	 */
	public void setSettore(String settore) {
		this.settore = settore;
	}
	
	/**
	 * Metodo che ritorna la descrizione dell'azienda
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * metodo che setta la descrizione dell'azienda
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * Metodo che ritorna le condizioni dell'azienda
	 * @return condizioniAzienda
	 */
	public String getCondizioniAzienda() {
		return condizioniAzienda;
	}
	
	/**
	 * metodo che setta le condizione dell'azienda
	 * @param condizioniAzienda
	 */
	public void setCondizioniAzienda(String condizioniAzienda) {
		this.condizioniAzienda = condizioniAzienda;
	}

	private String nome;
	private String cognome;
	private String indirizzo;
	private String sesso;
	private String ruolo;
	private String email;
	private String password;
	private String confermaPassword;
	private String condizioniDelegato;
	
	private String ragioneSociale;
	private String sede;
	private String pIva;
	private String settore;
	private String descrizione;
	private String condizioniAzienda;
}
