package it.unisa.di.is.gc1.ify.studente;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizione;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneNonValidaException;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneService;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import it.unisa.di.is.gc1.ify.utenza.MailSingletonSender;
import it.unisa.di.is.gc1.ify.utenza.Utente;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;
import it.unisa.di.is.gc1.ify.web.StudenteController;


/**
 * classe di test di unità per la richiesta di iscrizione
 * 
 * @author Giusy Castaldo, Giacomo Izzo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RichiestaIscrizioneUT {

	@Mock
	StudenteRepository studenteRepository;
	@Mock
	RichiestaIscrizioneRepository richiestaIscrizioneRepository;
	@Mock
	StudenteController studenteController;
	@Mock
	UtenteRepository utenteRepository;
	@Mock
	Utente utente;
	@Mock
	RichiestaIscrizione richiestaIscrizione;
	@Mock
	UtenzaService utenzaService;
	@Mock
	MailSingletonSender mailSender;
	@Mock 
	Studente studenteMock;
	@Mock
	RichiestaIscrizione richiestaMock;
	
	@InjectMocks
	RichiestaIscrizioneService richiestaIscrizioneService;

	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private LocalDate dataNascita;
	private String matricola;
	private String sesso;
	private String email;
	private String password;
	private String confermaPassword;
	private String condizioni;

	public void validaCampi() throws RichiestaIscrizioneNonValidaException {
		richiestaIscrizioneService.validaNome(nome);
		richiestaIscrizioneService.validaCognome(cognome);
		richiestaIscrizioneService.validaIndirizzo(indirizzo);
		richiestaIscrizioneService.validaTelefono(telefono);
		richiestaIscrizioneService.validaDataNascita(dataNascita);
		richiestaIscrizioneService.validaMatricola(matricola);
		richiestaIscrizioneService.validaSesso(sesso);
		richiestaIscrizioneService.validaEmail(email);
		richiestaIscrizioneService.validaPassword(password);
		richiestaIscrizioneService.validaConfermaPassword(password, confermaPassword);
		richiestaIscrizioneService.validaCondizioni(condizioni);
	}

	/**
	 * Verifica che il campo nome non sia null
	 */
	@Test
	public void ValidaNome_Null() {

		nome = null;
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo nome non può essere nullo.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica la lunghezza minima del campo nome
	 */
	@Test
	public void ValidaNome_Min_Lunghezza() {

		nome = "";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo nome deve contenere almeno 2 caratteri.";

		try {

			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza massima del nome
	 */
	@Test
	public void ValidaNome_Max_Lunghezza() {

		nome = "MarioAndreaaaaaaaaaaaaaaaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo nome deve contenere al massimo 255 caratteri.";

		try {

			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica formato del nome
	 */
	@Test
	public void ValdiaNome_Formato() {

		nome = "Mario97";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo nome deve contenere soltanto caratteri alfabetici o spazi.";

		try {

			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica che il campo cognome non sia null
	 */
	@Test
	public void ValidaCognome_Null() {

		nome = "Mario";
		cognome = null;
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo cognome non può essere nullo.";

		try {

			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza minima cognome
	 */
	@Test
	public void ValidaCognome_Min_Lunghezza() {
		nome = "Mario";
		cognome = "R";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo cognome deve contenere almeno 2 caratteri.";

		try {

			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza massima cognome
	 */
	@Test
	public void validaCognome_Max_Lunghezza() {
		nome = "Mario";
		cognome = "Rossiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo cognome deve contenere al massimo 255 caratteri.";

		try {

			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica formato cognome
	 */
	@Test
	public void validaCognome_Formato() {

		nome = "Mario";
		cognome = "Rossi97";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo cognome deve contenere soltanto caratteri alfabetici o spazi.";

		try {

			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica che il campo indirizzo non sia null
	 */
	@Test
	public void ValidaIndirizzo_Null() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = null;
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo indirizzo non può essere nullo.";

		try {

			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza minima indirizzo
	 * 
	 */
	@Test
	public void validaIndirizzo_Min_Lunghezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo indirizzo deve contenere almeno 2 caratteri.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza massima indirizzo
	 */
	@Test
	public void validaIndirizzo_Max_Lunghezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Romaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo indirizzo deve contenere al massimo 255 caratteri.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica formato indirizzo
	 */
	@Test
	public void validaIndirizzo_Formato() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma#Antonio 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo indirizzo deve contenere soltanto caratteri alfanumerici e segni di punteggiatura.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica che il campo telefono non sia null
	 */
	@Test
	public void validaTelefono_Null() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = null;
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo telefono non può essere nullo.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza minima telefono
	 */
	@Test
	public void validaTelefono_Min_Lunghezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-35";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo telefono deve contenere almeno 10 caratteri.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza massima telefono
	 */
	@Test
	public void validaTelefono_Max_Lunghezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-35444445446469";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo telefono deve contenere al massimo 11 caratteri.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica formato telefono
	 */
	@Test
	public void validaTelefono_Formato() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3BB4541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo telefono deve contenere soltanto caratteri numerici, al più le prime tre cifre possono essere separate da un trattino.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica che il campo data di nascita non sia null
	 */
	@Test
	public void validaDataNascita_Null() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = null;
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo data di nascita non può essere nullo.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica correttezza data di nascita
	 */
	@Test
	public void validaDataNascita_SottoRange() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1800-12-01");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "La data di nascita non rientra nel range consento " + Studente.MIN_DATE.getDayOfMonth()
				+ "/" + Studente.MIN_DATE.getMonthValue() + "/" + Studente.MIN_DATE.getYear() + " - "
				+ Studente.MAX_DATE.getDayOfMonth() + "/" + Studente.MAX_DATE.getMonthValue() + "/"
				+ Studente.MAX_DATE.getYear() + ".";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica correttezza data di nascita
	 */
	@Test
	public void validaDataNascita_SopraRange() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.now().minusYears(10L);
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "La data di nascita non rientra nel range consento " + Studente.MIN_DATE.getDayOfMonth()
				+ "/" + Studente.MIN_DATE.getMonthValue() + "/" + Studente.MIN_DATE.getYear() + " - "
				+ Studente.MAX_DATE.getDayOfMonth() + "/" + Studente.MAX_DATE.getMonthValue() + "/"
				+ Studente.MAX_DATE.getYear() + ".";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica che il campo matricola non sia null
	 */
	@Test
	public void validaMatricola_Null() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = null;
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo matricola non può essere nullo.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza matricola
	 */
	@Test
	public void validaMatricola_Lunghezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo matricola deve contenere 10 caratteri.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica formato matricola
	 */
	@Test
	public void validaMatricola_Formato() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "A512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo matricola deve contenere solo caratteri numerici.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	
	/**
	 * verifica se la email sia già presente nel db
	 */
	@Test
	public void validaMatricola_Esistenza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(studenteRepository.findByMatricola(matricola)).thenReturn(new Studente());

		final String message = "La matricola inserita è già esistente nel database";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	/**
	 * verifica che il campo sesso non sia null
	 */
	@Test
	public void validaSesso_Null() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = null;
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo sesso non può essere nullo.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica carattere sesso
	 */
	@Test
	public void validaSesso_Formato() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "A";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo sesso deve essere valorizzato con un solo carattere tra M o F.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica che il campo email non sia null
	 */
	@Test
	public void validaEmail_Null() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = null;
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo e-mail non può essere nullo.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza minima email
	 */
	@Test
	public void validaEmail_Min_Lunghezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo e-mail deve contenere almeno 2 caratteri.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza massima email
	 */
	@Test
	public void validaEmail_Max_Lunghezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo e-mail deve contenere al massimo 256 caratteri.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica formato email
	 */
	@Test
	public void validaEmail_Formato() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.r*ossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo e-mail non rispetta il formato stabilito.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica se la email sia già presente nel db
	 */
	@Test
	public void validaEmail_Esistenza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "p.bianchi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(true);

		final String message = "l'e-mail inserita è già presente.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica che il campo password non sia null
	 */
	@Test
	public void validaPassword_Null() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = null;
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo password non può essere nullo.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza minima password
	 */
	@Test
	public void validaPassword_Min_Lughezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Pw#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo password deve contenere almeno 8 caratteri, almeno una lettera, almeno un numero e nessuno spazio.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza massima password
	 */
	@Test
	public void validaPassword_Max_Lughezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "PasswordPasswordPassword#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo password deve contenere al massimo 24 caratteri, almeno una lettera, almeno un numero e nessuno spazio.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica formato password
	 */
	@Test
	public void validaPassword_Formato() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Pass   word#1";
		confermaPassword = "Password#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo password deve contenere almeno un numero, almeno una lettera e nessuno spazio.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica che il campo conferma password non sia null
	 */
	@Test
	public void validaConfermaPassword_Null() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = null;
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo conferma password non può essere nullo.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza minima conferma password
	 */
	@Test
	public void validaConfermaPassword_Min_Lunghezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Pw#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo password e il campo conferma password non corrispondono.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica lunghezza massima conferma password
	 */
	@Test
	public void validaConfermaPassword_Max_Lunghezza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "PasswordPasswordPassword#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo password e il campo conferma password non corrispondono.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica formato conferma password
	 */
	@Test
	public void validaConfermaPassword_Formato() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Pass  word#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo password e il campo conferma password non corrispondono.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica corrispondenza password e conferma password
	 */
	@Test
	public void validaConfermaPassword_Corrispondenza() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Passwordd#1";
		condizioni = "on";

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "Il campo password e il campo conferma password non corrispondono.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica accettazione condizioni privacy
	 */
	@Test
	public void verificaCondizioni() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = null;

		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		final String message = "È obbligatorio accettare le condizioni sulla privacy.";

		try {
			validaCampi();
		} catch (RichiestaIscrizioneNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * caso di successo
	 */
	@Test
	public void salvaRichiestaIscrizione() {
		nome = "Mario";
		cognome = "Rossi";
		indirizzo = "Via Roma 4 84080 Salerno SA";
		telefono = "333-3544541";
		dataNascita = LocalDate.parse("1997-12-24");
		matricola = "0512105144";
		sesso = "M";
		email = "m.rossi@studenti.unisa.it";
		password = "Password#1";
		confermaPassword = "Password#1";
		condizioni = "on";
		RichiestaIscrizione richiestaIscrizione=new RichiestaIscrizione();
		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		try {
			validaCampi();

			when(richiestaIscrizioneRepository.save(any(RichiestaIscrizione.class))).thenReturn(null);
			richiestaIscrizioneService.salvaRichiestaIscrizione(richiestaIscrizione);

			// verifica che il metodo save del mock venga invocato almeno una volta
			verify(richiestaIscrizioneRepository, times(1)).save(any(RichiestaIscrizione.class));

		} catch (RichiestaIscrizioneNonValidaException exception) {
			exception.printStackTrace();

		}
	}

	/**
	 * verifica metodo accettaRichiestaIscrizione con utente non autorizzato
	 */
	@Test
	public void accettaRichiestaIscrizioneNonAutorizzata() {
		RichiestaIscrizione richiestaiscrizione = new RichiestaIscrizione();
		when(richiestaIscrizioneRepository.findById(10L)).thenReturn(Optional.of(richiestaiscrizione));
		when(richiestaIscrizioneRepository.save(richiestaiscrizione)).thenReturn(richiestaiscrizione);
		when(utenzaService.getUtenteAutenticato()).thenReturn(new Studente());

		final String message = "Operazione non autorizzata";

		try {
			richiestaIscrizioneService.accettaRichiestaIscrizione(10L);
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica metodo accettaRichiestaIscrizione su richieste con stato non in
	 * attesa
	 */
	@Test
	public void accettaRichiestaIscrizioneAutorizzataStatoErrato() {
		RichiestaIscrizione richiestaiscrizione = new RichiestaIscrizione();
		richiestaiscrizione.setStato(RichiestaIscrizione.RIFIUTATA);
		when(richiestaIscrizioneRepository.findById(11L)).thenReturn(Optional.of(richiestaiscrizione));
		when(richiestaIscrizioneRepository.save(richiestaiscrizione)).thenReturn(richiestaiscrizione);
		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());

		final String message = "Impossibile accettare questa richiesta";

		try {
			richiestaIscrizioneService.accettaRichiestaIscrizione(11L);
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica metodo accettaRichiestaIscrizione successo
	 */
	@Test
	public void accettaRichiestaIscrizioneSuccesso() {
	
		when(richiestaIscrizioneRepository.findById(any(Long.class))).thenReturn(Optional.of(richiestaMock));
		when(richiestaIscrizioneRepository.save(richiestaMock)).thenReturn(richiestaMock);
		when(richiestaMock.getStato()).thenReturn(RichiestaIscrizione.IN_ATTESA);

		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());
				
		when(richiestaMock.getStudente()).thenReturn(studenteMock);
		when(studenteMock.getEmail()).thenReturn("m.rossi@studenti.unisa.it");
		
			
		try {
			richiestaIscrizioneService.accettaRichiestaIscrizione(12L);
			verify(richiestaIscrizioneRepository, times(1)).save(any(RichiestaIscrizione.class));
		} catch (OperazioneNonAutorizzataException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * verifica metodo rifiutaRichiestaIscrizione con utente non autorizzato
	 */
	@Test
	public void rifiutaRichiestaIscrizioneNonAutorizzata() {
		RichiestaIscrizione richiestaiscrizione = new RichiestaIscrizione();
		when(richiestaIscrizioneRepository.findById(13L)).thenReturn(Optional.of(richiestaiscrizione));
		when(richiestaIscrizioneRepository.save(richiestaiscrizione)).thenReturn(richiestaiscrizione);
		when(utenzaService.getUtenteAutenticato()).thenReturn(new Studente());

		final String message = "Operazione non autorizzata";

		try {
			richiestaIscrizioneService.rifiutaRichiestaIscrizione(13L);
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica metodo rifiutaRichiestaIscrizione con stato diverso da in attesa
	 */
	@Test
	public void rifiutaRichiestaIscrizioneStatoErrato() {
		RichiestaIscrizione richiestaiscrizione = new RichiestaIscrizione();
		richiestaiscrizione.setStato(RichiestaIscrizione.ACCETTATA);
		when(richiestaIscrizioneRepository.findById(14L)).thenReturn(Optional.of(richiestaiscrizione));
		when(richiestaIscrizioneRepository.save(richiestaiscrizione)).thenReturn(richiestaiscrizione);
		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());

		final String message = "Impossibile rifiutare questa richiesta";

		try {
			richiestaIscrizioneService.rifiutaRichiestaIscrizione(14L);
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	
	
	/**
	 * verifica metodo rifiutaRichiestaIscrizione successo
	 */
	@Test
	public void rifiutaRichiestaIscrizioneSuccesso() {		
		when(richiestaIscrizioneRepository.findById(15L)).thenReturn(Optional.of(richiestaMock));
		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());
		when(richiestaMock.getStato()).thenReturn(RichiestaIscrizione.IN_ATTESA);
		when(richiestaIscrizioneRepository.save(richiestaMock)).thenReturn(richiestaMock);
		
		when(richiestaMock.getStudente()).thenReturn(studenteMock);
		when(studenteMock.getEmail()).thenReturn("m.rossi@studenti.unisa.it");
				
		try {
			richiestaIscrizioneService.rifiutaRichiestaIscrizione(15L);
			verify(richiestaIscrizioneRepository, times(1)).save(any(RichiestaIscrizione.class));
		} catch (OperazioneNonAutorizzataException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * verifica metodo visualizzaRichiesteIscrizioneEDettagli con utente non
	 * autorizzato
	 */
	@Test
	public void visualizzaRichiesteNonAutorizzato() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new Studente());

		final String message = "Operazione non autorizzata";

		try {
			richiestaIscrizioneService.visualizzaRichiesteIscrizioneDettagli();
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	/**
	 * verifica metodo visualizzaRichiesteIscrizioneEDettagli con utente autorizzato
	 */
	@Test
	public void visualizzaRichiesteSuccesso() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());

		try {
			richiestaIscrizioneService.visualizzaRichiesteIscrizioneDettagli();
			verify(richiestaIscrizioneRepository, times(1)).findAllByStato(RichiestaIscrizione.IN_ATTESA);
		} catch (OperazioneNonAutorizzataException exception) {
			exception.printStackTrace();
		}
	}
}