package it.unisa.di.is.gc1.ify.utenza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe che defisce operazioni che permettono di realizzare la logica di
 * business comune a tutti gli utenti del sistema
 * 
 * @see Utente
 * @see UtenteRepository
 * 
 * @author Giacomo Izzo, Alessia Natale, Carmine Ferrara
 */
@Service
public class UtenzaService {

	@Autowired
	private UtenteRepository utenteRepository;

	/**
	 * Controlla che l'email di un utente sia specificata e che rispetti il formato
	 * prestabilito. Controlla inolte che tale email non sia già presente nel
	 * sistema.
	 * 
	 * @param email Stringa che rappresenta l'email da controllare
	 * 
	 * @return email La stringa che rappresenta l'email da controllare validata
	 * 
	 * @throws MailNonValidaException    se l'email non è specificata oppure se non
	 *                                   rispetta il formato
	 *                                   {@link Utente#EMAIL_PATTERN}
	 * 
	 * @throws MailNonEsistenteException se l'email specificata non è presente nel
	 *                                   sistema
	 */
	public String validaMail(String email) throws MailNonValidaException, MailNonEsistenteException {
		if (email == null) {
			throw new MailNonValidaException();
		} else {
			if (!email.matches(Utente.EMAIL_PATTERN)) {
				throw new MailNonValidaException();
			} else if (!utenteRepository.existsByEmail(email)) {
				throw new MailNonEsistenteException();
			} else {
				return email;
			}
		}
	}

	/**
	 * Permette di ottenerel'utente autenticato nel sistema
	 * 
	 * @return l'utente autenticato nel sistema, <b>null</b> se non vi è alcun
	 *         utente autenticato
	 */
	public Utente getUtenteAutenticato() {
		// Ottieni l'username dell'utente autenticato e restituisci null se non è
		// presente alcun utente
		// in sessione
		String email = (AutenticazioneHolder.getUtente());
		if (email == null) {
			return null;
		}

		Utente utente;

		// Controlla se l'username è associato ad uno studente
		utente = utenteRepository.findByEmail(email);
		if (utente != null) {
			return utente;
		}

		return null;
	}

	/**
	 * Permette di specificare l'utente autenticato nel sistema, tramite l'email, in
	 * una variabile visibile a livello di thread così da condividere l'informazione
	 * con tutti gli altri livelli. Questo metodo può essere utilizzato per
	 * iniettare automaticamente l'utente nel thread associato alla richiesta a
	 * partire dall'attributo di sessione del server.
	 * 
	 * @param email Stringa relativa all'email dell'utente che si vuole autenticare
	 *              nel sistema
	 */
	public void setUtenteAutenticato(String email) {
		// Se username è null, rimuovi la variabile di thread per prevenire memory leak
		if (email == null) {
			AutenticazioneHolder.setUtente(null);
			return;
		}

		if (utenteRepository.existsByEmail(email)) {
			AutenticazioneHolder.setUtente(email);
		}
	}

	/**
	 * Permette l'autenticazione di un utente nel sistema.
	 * 
	 * @param email    Stringa che rappresenta l'email dell'utente
	 * 
	 * @param password Stringa che rappresenta la password dell'utente
	 * 
	 * @throws PasswordNonValidaException se la coppia (email, password) non è
	 *                                    presente nel sistema
	 *                                    
	 * @return utente                                   
	 */
	public Utente login(String email, String password) throws PasswordNonValidaException {
		Utente utente;

		// Controlla se le credenziali corrispondono a quelle di uno studente e, nel
		// caso, controlla
		// che la richiesta d'iscrizione associatagli sia stata accettata
		utente = utenteRepository.findByEmailAndPassword(email, password);
		if (utente != null) {
			AutenticazioneHolder.setUtente(email);
			return utente;
		}

		throw new PasswordNonValidaException();
	}

	/**
	 * Permette la rimozione dell'utente dalla sessione.
	 */
	public void logout() {
		AutenticazioneHolder.setUtente(null);
	}
}
