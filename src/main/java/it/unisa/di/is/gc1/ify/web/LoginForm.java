package it.unisa.di.is.gc1.ify.web;

/** 
 * Oggetto utilizzato per mappare i campi di un form di login. Questo oggetto
 * viene passato come parametro al controller della dispatcher servlet quando
 * un utente sottomette il modulo di login.
 * 
 * @author Alessia Natale, Giacomo Izzo
 *
 */

public class LoginForm {
	
	/**
	 * Costruttore che crea un oggeto LoginForm vuoto, 
	 * che verra' popolato con i metodi setters.
	 */
	public LoginForm() {
		
	}
	
	/**
	 * Costruttore di un form di login con parametri utili nei casi di test.
	 * @param email e' l'email inserita nel form.
	 * @param password e' la password inserita nel form.
	 */
	public LoginForm(String email, String password) {
		this.email=email.toLowerCase();
		this.password=password;
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
	 * @param email e' l'email dell'utente.
	 */
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	/**
	 * Metodo che ritorna la password dell'utente.
	 * @return password e' la password dell'utente.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metodo che setta la password dell'utente.
	 * @param password e' la password dell'utente.
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	private String email;
	private String password;

}
