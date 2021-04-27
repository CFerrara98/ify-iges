package it.unisa.di.is.gc1.ify.Studente;

import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import it.unisa.di.is.gc1.ify.utenza.MailSingletonSender;
import it.unisa.di.is.gc1.ify.utenza.Utente;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * La classe fornisce i metodi per la logica di business delle richieste d'iscrizione
 * @author Carmine Ferrara Giacomo Izzo
 */

@Service
public class RichiestaIscrizioneService {
	@Autowired
	private StudenteRepository studenteRepository;

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private RichiestaIscrizioneRepository richiestaIscrizioneRepository;
	
	@Autowired
	private UtenzaService utenzaService;
	
	@Autowired
	private MailSingletonSender mailSingletonSender;

	
	/**
	 * Il metodo fornisce la funzionalita' di salvataggio di uno studente con la relativa
	 * richiesta d'iscrizione posta in stato di attesa
	 * @param richiestaIscrizione
	 * 
	 * @return RichiestaIscrizione richiestaIscrizione
	 * @pre studente != null
	 * @post richiestaIscrizione != null
	 */
	@Transactional(rollbackFor = Exception.class)
	public RichiestaIscrizione salvaRichiestaIscrizione(RichiestaIscrizione richiestaIscrizione) {
		studenteRepository.save(richiestaIscrizione.getStudente());
		richiestaIscrizione = richiestaIscrizioneRepository.save(richiestaIscrizione);

		return richiestaIscrizione;
	}
	
	
	/**
	 * Il metodo fornisce la funzionalita' di accettazione di una richiesta d'iscrizione
	 *
	 * @param idRichiesta
	 * @return Oggetto {@link RichiestaIscrizione} che rappresenta la richiesta di
	 *         iscrizione.
	 * @throws OperazioneNonAutorizzataException
	 */
	
	@Transactional(rollbackFor = Exception.class)
	public RichiestaIscrizione accettaRichiestaIscrizione(Long idRichiesta)
			throws OperazioneNonAutorizzataException {
		
		Utente utente = utenzaService.getUtenteAutenticato();
		
		//Solo il responsabile ufficio tirocini può accettare una richiesta di iscrizione
		if(!(utente instanceof ResponsabileUfficioTirocini)) {
			throw new OperazioneNonAutorizzataException();
		}

		RichiestaIscrizione richiestaIscrizione = richiestaIscrizioneRepository.findById(idRichiesta).orElse(null);
	
		if(!richiestaIscrizione.getStato().equals(RichiestaIscrizione.IN_ATTESA)) {
			throw new OperazioneNonAutorizzataException("Impossibile accettare questa richiesta");
		}
		
		richiestaIscrizione.setStato(RichiestaIscrizione.ACCETTATA);
		richiestaIscrizione = richiestaIscrizioneRepository.save(richiestaIscrizione);
		
		mailSingletonSender.sendEmail(richiestaIscrizione, richiestaIscrizione.getStudente().getEmail());
		
		
		return richiestaIscrizione;
	}
		
	 /** Il metodo fornisce la funzionalita' di rifiuto di una richiesta d'iscrizione
	 * 
	 * 
	 * @param idRichiesta
	 * @return Oggetto {@link RichiestaIscrizione} che rappresenta la richiesta di
	 *         iscrizione.
	 * @throws OperazioneNonAutorizzataException
	 */
	@Transactional(rollbackFor = Exception.class)
	public RichiestaIscrizione rifiutaRichiestaIscrizione(Long idRichiesta)
			throws OperazioneNonAutorizzataException {
				
		Utente utente = utenzaService.getUtenteAutenticato();
		
		//Solo il responsabile ufficio tirocini può rifiutare una richiesta di iscrizione
		if(!(utente instanceof ResponsabileUfficioTirocini)) {
			throw new OperazioneNonAutorizzataException();
		}
		
		RichiestaIscrizione richiestaIscrizione = richiestaIscrizioneRepository.findById(idRichiesta).orElse(null);

		if(!richiestaIscrizione.getStato().equals(RichiestaIscrizione.IN_ATTESA)) {
			throw new OperazioneNonAutorizzataException("Impossibile rifiutare questa richiesta");
		}
		
		richiestaIscrizione.setStato(RichiestaIscrizione.RIFIUTATA);
		richiestaIscrizione = richiestaIscrizioneRepository.save(richiestaIscrizione);
		
		mailSingletonSender.sendEmail(richiestaIscrizione, richiestaIscrizione.getStudente().getEmail());
		
		richiestaIscrizioneRepository.delete(richiestaIscrizione);

		return richiestaIscrizione;
	}

	 /** Il metodo fornisce la funzionalita' di visualizzazione delle richieste di iscrizione
	 * in attesa e relativi dettagli
	 * 
	 * @return Lista di {@link RichiestaIscrizione} che rappresenta la lista delle
	 *         richieste di iscrizione <b>Puo' essere vuota</b> se nel database non
	 *         sono presenti richieste di iscrizione in attesa
	 * @throws OperazioneNonAutorizzataException
	 */	
	@Transactional(rollbackFor = Exception.class)
	public List<RichiestaIscrizione> visualizzaRichiesteIscrizioneDettagli() 
			throws OperazioneNonAutorizzataException {
		
		Utente utente = utenzaService.getUtenteAutenticato();
		
		//Solo il responsabile ufficio tirocini puo visualizzare le richieste di iscrizione
		if(!(utente instanceof ResponsabileUfficioTirocini)) {
			throw new OperazioneNonAutorizzataException();
		}
		
		List<RichiestaIscrizione> richiestaIscrizione = richiestaIscrizioneRepository.findAllByStato(RichiestaIscrizione.IN_ATTESA);

		return richiestaIscrizione;
	}
	
	/** Il metodo ritorna lo stato della richiesta d'iscrizione
	 * @param email
	 * @return String stringa che rappresenta lo stato della richiesta d'iscrizione
	 */
	@Transactional(rollbackFor = Exception.class)
	public String getStatoRichiestaByEmail(String email) {
		return richiestaIscrizioneRepository.findByStudenteEmail(email).getStato();
	}
		
	/**
	 * Il metodo fornisce i controlli di validazione del parametro nome di un generico studente
	 * @param nome
	 * @return nome
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaNome(String nome) throws RichiestaIscrizioneNonValidaException {
	
		if (nome == null)
			throw new RichiestaIscrizioneNonValidaException("NomeError", "Il campo nome non può essere nullo.");

		if (nome.length() < Studente.MIN_LUNGHEZZA_CAMPO)
			throw new RichiestaIscrizioneNonValidaException("NomeError", "Il campo nome deve contenere almeno 2 caratteri.");

		if (nome.length() > Studente.MAX_LUNGHEZZA_CAMPO)
			throw new RichiestaIscrizioneNonValidaException("NomeError", "Il campo nome deve contenere al massimo 255 caratteri.");

		if (!nome.matches(Studente.NOME_PATTERN))
			throw new RichiestaIscrizioneNonValidaException("NomeError", "Il campo nome deve contenere soltanto caratteri alfabetici o spazi.");
		return nome;
	}
	
	/**
	 * Il metodo fornisce i controlli di validazione del parametro cognome di un generico studente
	 * @param cognome
	 * @return cognome
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaCognome(String cognome) throws RichiestaIscrizioneNonValidaException {
		if (cognome == null)
			throw new RichiestaIscrizioneNonValidaException("CognomeError", "Il campo cognome non può essere nullo.");

		if (cognome.length() < Studente.MIN_LUNGHEZZA_CAMPO)
			throw new RichiestaIscrizioneNonValidaException("CognomeError", "Il campo cognome deve contenere almeno 2 caratteri.");

		if (cognome.length() > Studente.MAX_LUNGHEZZA_CAMPO)
			throw new RichiestaIscrizioneNonValidaException("CognomeError",
					"Il campo cognome deve contenere al massimo 255 caratteri.");

		if (!cognome.matches(Studente.COGNOME_PATTERN))
			throw new RichiestaIscrizioneNonValidaException("CognomeError",
					"Il campo cognome deve contenere soltanto caratteri alfabetici o spazi.");
		return cognome;
	}

	/**
	 * Il metodo fornisce i controlli di validazione del parametro indirizzo di un generico studente
	 * @param indirizzo
	 * @return indirizzo
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaIndirizzo(String indirizzo) throws RichiestaIscrizioneNonValidaException {
		if (indirizzo == null)
			throw new RichiestaIscrizioneNonValidaException("IndirizzoError", "Il campo indirizzo non può essere nullo.");

		if (indirizzo.length() < Studente.MIN_LUNGHEZZA_CAMPO)
			throw new RichiestaIscrizioneNonValidaException("IndirizzoError","Il campo indirizzo deve contenere almeno 2 caratteri.");

		if (indirizzo.length() > Studente.MAX_LUNGHEZZA_CAMPO)
			throw new RichiestaIscrizioneNonValidaException("IndirizzoError",
					"Il campo indirizzo deve contenere al massimo 255 caratteri.");

		if (!indirizzo.matches(Studente.INDIRIZZO_PATTERN))
			throw new RichiestaIscrizioneNonValidaException("IndirizzoError",
					"Il campo indirizzo deve contenere soltanto caratteri alfanumerici e segni di punteggiatura.");
		return indirizzo;
	}

	/**
	 * Il metodo fornisce i controlli di validazione del parametro telefono di un generico studente
	 * @param telefono
	 * @return telefono
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaTelefono(String telefono) throws RichiestaIscrizioneNonValidaException {
		if (telefono == null)
			throw new RichiestaIscrizioneNonValidaException("TelefonoError", "Il campo telefono non può essere nullo.");

		if (telefono.length() < 10)
			throw new RichiestaIscrizioneNonValidaException("TelefonoError", "Il campo telefono deve contenere almeno 10 caratteri.");

		if (telefono.length() > 11)
			throw new RichiestaIscrizioneNonValidaException("TelefonoError",
					"Il campo telefono deve contenere al massimo 11 caratteri.");

		if (!telefono.matches(Studente.TELEFONO_PATTERN))
			throw new RichiestaIscrizioneNonValidaException("TelefonoError",
					"Il campo telefono deve contenere soltanto caratteri numerici, al più le prime tre cifre possono"
							+ " essere separate da un trattino.");
		return telefono;
	}

	/**
	 * Il metodo fornisce i controlli di validazione del parametro data di nascita di un generico studente
	 * @param dataNascita
	 * @return data di nascita
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public LocalDate validaDataNascita(LocalDate dataNascita) throws RichiestaIscrizioneNonValidaException {
		if (dataNascita == null)
			throw new RichiestaIscrizioneNonValidaException("DataNascitaError", "Il campo data di nascita non può essere nullo.");

		if (dataNascita.isBefore(Studente.MIN_DATE) || dataNascita.isAfter(Studente.MAX_DATE))
			throw new RichiestaIscrizioneNonValidaException("DataNascitaError", "La data di nascita non rientra nel range consento "
					+ Studente.MIN_DATE.getDayOfMonth() + "/" + Studente.MIN_DATE.getMonthValue() + "/"
					+ Studente.MIN_DATE.getYear() + " - " + Studente.MAX_DATE.getDayOfMonth() + "/"
					+ Studente.MAX_DATE.getMonthValue() + "/" + Studente.MAX_DATE.getYear() + ".");

		return dataNascita;
	}

	/**
	 * Il metodo fornisce i controlli di validazione del parametro matricola di un generico studente
	 * @param matricola
	 * @return matricola
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaMatricola(String matricola) throws RichiestaIscrizioneNonValidaException {
		if (matricola == null)
			throw new RichiestaIscrizioneNonValidaException("MatricolaError", "Il campo matricola non può essere nullo.");

		if (matricola.length() != Studente.LUNGHEZZA_MATRICOLA)
			throw new RichiestaIscrizioneNonValidaException("MatricolaError", "Il campo matricola deve contenere 10 caratteri.");

		if (!matricola.matches(Studente.MATRICOLA_PATTERN))
			throw new RichiestaIscrizioneNonValidaException("MatricolaError",
					"Il campo matricola deve contenere solo caratteri numerici.");
		
		if (studenteRepository.findByMatricola(matricola) != null)
			throw new RichiestaIscrizioneNonValidaException("MatricolaError",
					"La matricola inserita è già esistente nel database");
		
		return matricola;
	}

	/**
	 * Il metodo fornisce i controlli di validazione del parametro sesso di un generico studente
	 * @param sesso
	 * @return sesso
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaSesso(String sesso) throws RichiestaIscrizioneNonValidaException {
		if (sesso == null)
			throw new RichiestaIscrizioneNonValidaException("SessoError", "Il campo sesso non può essere nullo.");

		if (!sesso.matches(Studente.SESSO_PATTERN))
			throw new RichiestaIscrizioneNonValidaException("SessoError", "Il campo sesso deve essere valorizzato con un solo carattere tra M o F.");
		return sesso;
	}

	/**
	 * Il metodo fornisce i controlli di validazione del parametro email di un generico studente
	 * @param email
	 * @return email
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaEmail(String email) throws RichiestaIscrizioneNonValidaException {
		if (email == null)
			throw new RichiestaIscrizioneNonValidaException("EmailError", "Il campo e-mail non può essere nullo.");

		if (email.length() < Studente.MIN_LUNGHEZZA_CAMPO)
			throw new RichiestaIscrizioneNonValidaException("EmailError", "Il campo e-mail deve contenere almeno 2 caratteri.");

		if (email.length() > Studente.MAX_LUNGHEZZA_MAIL)
			throw new RichiestaIscrizioneNonValidaException("EmailError","Il campo e-mail deve contenere al massimo 256 caratteri.");

		if (!email.matches(Studente.EMAIL_STUDENTE_PATTERN))
			throw new RichiestaIscrizioneNonValidaException("EmailError", "Il campo e-mail non rispetta il formato stabilito.");

		if (utenteRepository.existsByEmail(email))
			throw new RichiestaIscrizioneNonValidaException("EmailError", "l'e-mail inserita è già presente.");

		return email;
	}

	/**
	 * Il metodo fornisce i controlli di validazione del parametro password di un generico studente
	 * @param password
	 * @return password
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaPassword(String password) throws RichiestaIscrizioneNonValidaException {
		if (password == null)
			throw new RichiestaIscrizioneNonValidaException("PasswordError", "Il campo password non può essere nullo.");

		if (password.length() < Studente.MIN_LUNGHEZZA_PASSWORD)
			throw new RichiestaIscrizioneNonValidaException("PasswordError", "Il campo password deve contenere almeno 8 caratteri, almeno una lettera, almeno un numero e nessuno spazio.");

		if (password.length() > Studente.MAX_LUNGHEZZA_PASSWORD)
			throw new RichiestaIscrizioneNonValidaException("PasswordError",
					"Il campo password deve contenere al massimo 24 caratteri, almeno una lettera, almeno un numero e nessuno spazio.");

		if (!password.matches(Studente.PASSWORD_PATTERN))
			throw new RichiestaIscrizioneNonValidaException("PasswordError", "Il campo password deve contenere almeno un numero, almeno una lettera e nessuno spazio.");
		return password;
	}
	
	/**
	 * Il metodo fornisce i controlli di validazione del parametro conferma password di un generico studente.
	 * @param password
	 * @param confermaPassword
	 * @return confermaPassword
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaConfermaPassword(String password, String confermaPassword) throws RichiestaIscrizioneNonValidaException {
		if (confermaPassword == null)
			throw new RichiestaIscrizioneNonValidaException("ConfermaPasswordError", "Il campo conferma password non può essere nullo.");

		if (confermaPassword.length() < Studente.MIN_LUNGHEZZA_PASSWORD)
			throw new RichiestaIscrizioneNonValidaException("ConfermaPasswordError", "Il campo password e il campo conferma password non corrispondono.");

		if (confermaPassword.length() > Studente.MAX_LUNGHEZZA_PASSWORD)
			throw new RichiestaIscrizioneNonValidaException("ConfermaPasswordError", "Il campo password e il campo conferma password non corrispondono.");

		if (!confermaPassword.matches(Studente.PASSWORD_PATTERN))
			throw new RichiestaIscrizioneNonValidaException("ConfermaPasswordError", "Il campo password e il campo conferma password non corrispondono.");

		if (!confermaPassword.equals(password))
			throw new RichiestaIscrizioneNonValidaException("ConfermaPasswordError", "Il campo password e il campo conferma password non corrispondono.");
		return confermaPassword;
	}
	
	/**
	 * Il metodo fornisce i controlli di validazione del parametro condizioni privacy per una richiesta di iscrizione.
	 * @param condizioni
	 * @return condizioni
	 * @throws RichiestaIscrizioneNonValidaException
	 */
	public String validaCondizioni(String condizioni) throws RichiestaIscrizioneNonValidaException {
		if (condizioni == null)
			throw new RichiestaIscrizioneNonValidaException("CondizioniError", "È obbligatorio accettare le condizioni sulla privacy.");
		
		return condizioni;
	}
	
}
