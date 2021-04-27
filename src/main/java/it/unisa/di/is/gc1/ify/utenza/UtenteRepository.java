package it.unisa.di.is.gc1.ify.utenza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Geremia Cavezza
 * 
 *         Classe che definisce le operazioni per la modellazione e l'accesso
 *         alle informazioni persisteti relative ad un utente registrato alla
 *         piattaforma
 *
 * @see Utente
 */

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

	/**
	 * Permette di ottenere un utente a partire dalle proprie credenziali di
	 * accesso.
	 * 
	 * @param password Stringa che rappresenta la password dell'utente
	 * @param email    Stringa che rappresenta l'email dell'utente
	 * 
	 * @return Oggetto {@link Utente} che rappresenta l'utente. <b>Può essere
	 *         null</b> se nel database non è presente un utente con email e
	 *         password passati come parametro
	 * 
	 * @pre email != null and password != null
	 */
	Utente findByEmailAndPassword(String email, String password);

	/**
	 * Permette di ottenere un utente a partire dalla proria mail.
	 * 
	 * @param email Stringa che rappresenta l'email dell'utente
	 * 
	 * @return Oggetto {@link Utente} che rappresenta l'utente. <b>Può essere
	 *         null</b> se nel database non è presente un utente con email come
	 *         parametro
	 * 
	 * @pre email != null
	 */
	Utente findByEmail(String email);

	/**
	 * Permette di verificare se un'utente esiste nel database attraverso la propria
	 * email.
	 * 
	 * @param email Stringa che rappresenta l'email di un utente
	 * 
	 * @return true se l'utente esiste, false se l'utente non esiste
	 * 
	 * @pre email != null
	 */
	boolean existsByEmail(String email);

}
