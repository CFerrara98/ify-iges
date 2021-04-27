package it.unisa.di.is.gc1.ify.web;

import java.time.LocalDate;

/**
 * Oggetto utilizzato per mappare i campi di un form di uno studente. Questo oggetto
 * viene passato come parametro al controller dalla dispatcher servlet quando un utente 
 * sottomette il modulo di registrazione.
 * 
 * @author Giusy Castaldo, Alessia Natale
 */
public class StudenteForm {
	
	/**
	 * Costruttore di un oggetto StudenteForm vuoto
	 */
	public StudenteForm() {
		
	}
	
	/**
	 * Costruttore di un oggetto StudenteForm
	 * @param nome e' il nome dello studente.
	 * @param cognome e' il cognome dello studente.
	 * @param sesso e' il sesso dello studente.
	 * @param email e' l'email dello studente.
	 * @param indirizzo e' l'indirizzo dello studente.
	 * @param password e' la password dello studente.
	 * @param matricola e' la matricola dello studente.
	 * @param dataNascita e' la data di nascita dello studente.
	 * @param telefono e' il telefono dello studente.
	 * @param confermaPsw e' la conferma password dello studente.
	 * @param condizioni sono le condizioni privacy dello studente.
	 */
	public StudenteForm(String nome, String cognome, String indirizzo, String telefono, String dataNascita,
			String sesso, String matricola, String email, String password, String confermaPsw, String condizioni) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.matricola = matricola;
		this.email = email.toLowerCase();
		this.password = password;
		this.confermaPsw = confermaPsw;
		this.condizioni = condizioni;
	}
	
	/**
	 * Metodo che ritorna il nome di uno studente.
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metodo che setta il nome di uno studente.
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Metodo che ritorna il cognome di uno studente.
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * Metodo che setta il cognome di uno studente.
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * Metodo che ritorna l'indirizzo di uno studente.
	 * @return indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * Metodo che setta l'indirizzo di uno studente.
	 * @param indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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
	 * Metodo che ritorna la data di nascita di uno studente.
	 * @return dataNascita
	 */
	public LocalDate getDataNascita() {
		if(this.dataNascita.equals("")) return null;
		
		LocalDate tmp = LocalDate.parse(this.dataNascita);
		return tmp;
	}
	
	/**
	 * Metodo che setta la data di nascita di uno studente.
	 * @param dataNascita
	 */
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	/**
	 * Metodo che ritorna il sesso di uno studente.
	 * @return sesso
	 */
	public String getSesso() {
		return sesso;
	}
	
	/**
	 * Metodo che setta il sesso di uno studente.
	 * @param sesso
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
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
	 * Metodo che ritorna l'email di uno studente.
	 * @return email
	 */
	public String getEmail() {
		return email.toLowerCase();
	}
	
	/**
	 * Metodo che setta l'email di uno studente.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	
	/**
	 * Metodo che ritorna la password di uno studente.
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Metodo che setta la password di uno studente.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Metodo che ritorna la conferma della password di uno studente.
	 * @return confermaPsw
	 */
	public String getConfermaPsw() {
		return confermaPsw;
	}
	
	/**
	 * Metodo che setta la conferma password di uno studente.
	 * @param confermaPsw
	 */
	public void setConfermaPsw(String confermaPsw) {
		this.confermaPsw = confermaPsw;
	}
	
	/**
	 * Metodo che ritorna le condizioni privacy di uno studente.
	 * @return condizioni
	 */
	public String getCondizioni() {
		return condizioni;
	}
	
	/**
	 * Metodo che setta le condizioni privacy di uno studente.
	 * @param condizioni
	 */
	public void setCondizioni(String condizioni) {
		this.condizioni = condizioni;
	}
	

	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private String dataNascita;
	private String sesso;
	private String matricola;
	private String email;
	private String password;
	private String confermaPsw;
	private String condizioni;
}



