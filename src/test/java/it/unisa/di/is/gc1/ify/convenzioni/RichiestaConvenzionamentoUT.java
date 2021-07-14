package it.unisa.di.is.gc1.ify.convenzioni;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import it.unisa.di.is.gc1.ify.utenza.MailSingletonSender;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;



/**
 * classe di test di unità per la richiesta di iscrizione
 * 
 * @author Geremia Cavezza
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RichiestaConvenzionamentoUT {

	@Mock
	private UtenteRepository utenteRepository;
	
	@Mock
	private AziendaRepository aziendaRepository;
	
	@Mock
	private DelegatoAziendaleRepository delegatoAziendaleRepository;
	
	@Mock
	private RichiestaConvenzionamentoRepository richiestaConvenzionamentoRepository;
	
	@Mock
	private RichiestaConvenzionamento richiestaConvenzionamento;

	@Mock
	private UtenzaService utenzaService;
	
	@Mock
	private DelegatoAziendale delegatoAziendale;
	
	@Mock
	private Azienda azienda;
	
	@Mock
	private List<Azienda> aziendaList;
	
	@Mock
	private MailSingletonSender mail;
	
	
	@InjectMocks
	private RichiestaConvenzionamentoService richiestaConvenzionamentoService;
	
	private String nome;
	private String cognome;
	private String ruolo;	
	private String sesso;
	private String email;
	private String indirizzo;
	private String password;
	private String confermaPassword;
	private String condizioniDelegato;

	private String pIva;
	private String ragioneSociale;
	private String sede;
	private String settore;
	private String descrizione;
	private String condizioniAzienda;
	
	private RichiestaConvenzionamento richiesta;
	
	public void validaCampi() throws RichiestaConvenzionamentoNonValidaException {
		richiestaConvenzionamentoService.validaNome(nome);
		richiestaConvenzionamentoService.validaCognome(cognome);
		richiestaConvenzionamentoService.validaRuolo(ruolo);
		richiestaConvenzionamentoService.validaSesso(sesso);
		richiestaConvenzionamentoService.validaEmail(email);
		richiestaConvenzionamentoService.validaIndirizzo(indirizzo);
		richiestaConvenzionamentoService.validaPassword(password);
		richiestaConvenzionamentoService.validaConfermaPassword(password, confermaPassword);
		richiestaConvenzionamentoService.validaCondizioniDelegato(condizioniDelegato);
		richiestaConvenzionamentoService.validaPiva(pIva);
		richiestaConvenzionamentoService.validaRagioneSociale(ragioneSociale);
		richiestaConvenzionamentoService.validaSede(sede);
		richiestaConvenzionamentoService.validaSettore(settore);
		richiestaConvenzionamentoService.validaDescrizione(descrizione);
		richiestaConvenzionamentoService.validaCondizioniAzienda(condizioniAzienda);
	}
	

	/**
	 * Verifica che il campo nome non sia null
	 */
	@Test
	public void ValidaNome_Null() {

		nome = null;
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo nome non può essere nullo.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);

		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo nome rispetti la lunghezza minima
	 */
	@Test
	public void ValidaNome_Min_Lunghezza() {

		nome = "";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo nome deve contenere almeno 2 caratteri.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	

	/**
	 * Verifica che il campo nome rispetti la lunghezza massima
	 */
	@Test
	public void ValidaNome_Max_Lunghezza() {

		nome = "MarioAndreaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo nome deve contenere al massimo 255 caratteri.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}	
	
	
	/**
	 * Verifica che il campo nome rispetti il formato
	 */
	@Test
	public void ValidaNome_Formato() {

		nome = "Mario7";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo nome deve contenere soltanto caratteri alfabetici.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo cognome non sia null
	 */
	@Test
	public void ValidaCognome_Null() {

		nome = "Mario";
		cognome = null;
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo cognome non può essere nullo.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo cognome rispetti la lunghezza minima
	 */
	@Test
	public void ValidaCognome_Min_Lunghezza() {

		nome = "Mario";
		cognome = "";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo cognome deve contenere almeno 2 caratteri.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo cognome rispetti la lunghezza massima
	 */
	@Test
	public void ValidaCognome_Max_Lunghezza() {

		nome = "Mario";
		cognome = "RossiFerrucciaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo cognome deve contenere al massimo 255 caratteri.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo cognome rispetti il formato
	 */
	@Test
	public void ValidaCognome_Formato() {

		nome = "Mario";
		cognome = "Rossi8";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo cognome deve contenere soltanto caratteri alfabetici.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo ruolo non sia null
	 */
	@Test
	public void ValidaRuolo_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = null;
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo ruolo non può essere nullo.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo ruolo rispetti la lunghezza minima
	 */
	@Test
	public void ValidaRuolo_Min_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo ruolo deve contenere almeno 2 caratteri.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo ruolo rispetti la lunghezza massima
	 */
	@Test
	public void ValidaRuolo_Man_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore generale aaaaaaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo ruolo deve contenere al massimo 255 caratteri.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo ruolo rispetti il formato
	 */
	@Test
	public void ValidaRuolo_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore3";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo ruolo deve contenere soltanto caratteri alfabetici.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	
	/**
	 * Verifica che il campo sesso non sia null
	 */
	@Test
	public void ValidaSesso_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = null;
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Effettuare una selezione per il campo Sesso.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo sesso rispetti il formato
	 */
	@Test
	public void ValidaSesso_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "X";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo sesso deve essere valorizzato con un solo carattere tra M o F.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo email non sia null
	 */
	@Test
	public void ValidaEmail_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = null;
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo email non può essere nullo.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	
	/**
	 * Verifica che il campo email rispetti la lunghezza minima
	 */
	@Test
	public void ValidaEmail_Min_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo email deve contenere almeno 2 caratteri.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo email rispetti la lunghezza massima
	 */
	@Test
	public void ValidaEmail_Max_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2asiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiie@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo email deve contenere al massimo 256 caratteri.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo email rispetti il formato
	 */
	@Test
	public void ValidaEmail_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo email deve rispettare il formato.";
//		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo email non sia già presente nel database
	 */
	@Test
	public void ValidaEmail_Esiste() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi1@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "L'e-mail è già presente nel database.";
		when(utenteRepository.existsByEmail(email)).thenReturn(true);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo indirizzo non sia null
	 */
	@Test
	public void ValidaIndirizzo_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = null;
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo indirizzo non può essere nullo.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo indirizzo rispetti la lunghezza minima
	 */
	@Test
	public void ValidaIndirizzo_Min_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo indirizzo deve contenere almeno 2 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo indirizzo rispetti la lunghezza massima
	 */
	@Test
	public void ValidaIndirizzo_Max_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via paolo,20 iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" 
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" 
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"  
				+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii Napoli";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo indirizzo deve contenere al massimo 255 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo indirizzo rispetti il formato
	 */
	@Test
	public void ValidaIndirizzo_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via San Paolo #";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo indirizzo deve contenere soltanto caratteri alfanumerici e di punteggiatura.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo password non sia null
	 */
	@Test
	public void ValidaPassword_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = null;
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo password non può essere nullo.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo password rispetti la lunghezza minima
	 */
	@Test
	public void ValidaPassword_Min_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo password deve contenere almeno 8 caratteri, almeno una lettera, almeno un numero e nessuno spazio.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo password rispetti la lunghezza massima
	 */
	@Test
	public void ValidaPassword_Max_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario??????????????????";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo password deve contenere al massimo 24 caratteri, almeno una lettera, almeno un numero e nessuno spazio.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo password rispetti il formato
	 */
	@Test
	public void ValidaPassword_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario ?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo password deve contenere almeno una lettera, almeno un numero e nessuno spazio.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo confermaPassword non sia null
	 */
	@Test
	public void ValidaConfermaPassword_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = null;
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo conferma password non può essere nullo.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	
	/**
	 * Verifica che il campo confermaPassword rispetti la lunghezza minima
	 */
	@Test
	public void ValidaConfermaPassword_Min_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo password e il campo conferma password non corrispondono.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo confermaPassword rispetti la lunghezza massima
	 */
	@Test
	public void ValidaConfermaPassword_Max_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario??????????????????";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo password e il campo conferma password non corrispondono.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo confermaPassword rispetti il formato
	 */
	@Test
	public void ValidaConfermaPassword_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario ?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo password e il campo conferma password non corrispondono.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	
	/**
	 * Verifica che il campo confermaPassword corrisponda al camp password
	 */
	@Test
	public void ValidaConfermaPassword_Corrispondenza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "10Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo password e il campo conferma password non corrispondono.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo condizioniDelegato sia spuntato
	 */
	@Test
	public void ValidaCondizioniDelegato_No() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = null;

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "È obbligatorio accettare le condizioni sulla privacy.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo pIva non sia null
	 */
	@Test
	public void ValidaPIva_NUll() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = null;
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo partita IVA non può essere nullo.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	
	/**
	 * Verifica che il campo pIva rispetti la lunghezza
	 */
	@Test
	public void ValidaPIva_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "1234567891011";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo partita IVA deve contenere 11 caratteri";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo pIva rispetti il formato
	 */
	@Test
	public void ValidaPIva_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "1234567891A";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo partita IVA deve contenere esattamente 11 numeri ";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	/**
	 * Verifica che il campo pIva rispetti il formato
	 */
	@Test
	public void ValidaPIva_Esistenza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678914";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "La Partita IVA inserita è già esistente nel database ";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		when(aziendaRepository.findByPIva(pIva)).thenReturn(new Azienda());
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo ragioneSociale non sia null
	 */
	@Test
	public void ValidaRagioneSociale_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = null;
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo ragione sociale non può essere nullo.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo ragioneSociale rispetti la lunghezza minima
	 */
	@Test
	public void ValidaRagioneSociale_Min_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetD";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo ragione sociale deve contenere almeno 5 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo ragioneSociale rispetti la lunghezza massima
	 */
	@Test
	public void ValidaRagioneSociale_Max_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioniiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				+"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo ragione sociale deve contenere al massimo 255 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo ragioneSociale rispetti il formato
	 */
	@Test
	public void ValidaRagioneSociale_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni #";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo ragione sociale deve contenere soltanto caratteri alafanumerici e di punteggiatura.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo sede non sia null
	 */
	@Test
	public void ValidaSede_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = null;
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo sede non può essere nullo.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}


	/**
	 * Verifica che il campo sede rispetti la lunghezza minima
	 */
	@Test
	public void ValidaSede_Min_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo sede deve contenere almeno 5 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	
	/**
	 * Verifica che il campo sede rispetti la lunghezza massima
	 */
	@Test
	public void ValidaSede_Max_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI – Italiaaaaaaaaaaaaaaaaaaaaaaa" 
				+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo sede deve contenere al massimo 255 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	

	/**
	 * Verifica che il campo sede rispetti il formato
	 */
	@Test
	public void ValidaSede_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI – Italia #";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo sede deve contenere soltanto caratteri alfanumerici e di punteggiatura";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo settore non sia null
	 */
	@Test
	public void ValidaSettore_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = null;
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo settore non può essere nullo.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}

	
	/**
	 * Verifica che il campo settore rispetti la lunghezza minima
	 */
	@Test
	public void ValidaSettore_Min_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "I";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo settore deve contenere almeno 2 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo settore rispetti la lunghezza massima
	 */
	@Test
	public void ValidaSettore_Max_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informaticaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo settore deve contenere al massimo 255 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo settore rispetti il formato
	 */
	@Test
	public void ValidaSettore_Formato() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica#";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		final String message = "Il campo settore deve contenere soltanto caratteri alfanumerici";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo descrizione non sia null
	 */
	@Test
	public void ValidaDescrizione_Null() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = null;
		condizioniAzienda = "on";	

		final String message = "Il campo descrizione non può essere nullo.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo descrizione rispetti la lunghezza minima
	 */
	@Test
	public void ValidaDescrizione_Min_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "";
		condizioniAzienda = "on";	

		final String message = "Il campo descrizione deve contenere almeno 2 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo descrizione rispetti la lunghezza massima
	 */
	@Test
	public void ValidaDescrizione_Max_Lunghezza() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analyticsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
		condizioniAzienda = "on";	

		final String message = "Il campo descrizione deve contenere al massimo 800 caratteri.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che il campo condizioiAzienda sia spuntato
	 */
	@Test
	public void ValidaCondizioniAzienda_No() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = null;	

		final String message = "È obbligatorio accettare le condizioni sulla privacy.";
		when(utenteRepository.existsByEmail(email)).thenReturn(false);
		
		try {
			validaCampi();
		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * Verifica che tutti i campi siano validi e salva la richiesta
	 */
	@Test
	public void salvaRichiestaConvenzionamento_Successo() {

		nome = "Mario";
		cognome = "Rossi";
		ruolo = "Direttore";
		sesso = "M";
		email = "m.rossi2@gmail.com";
		indirizzo = "Via san paolo, 20 Napoli NA - Italia";
		password = "20Mario?";
		confermaPassword = "20Mario?";
		condizioniDelegato = "on";

		pIva = "12345678910";
		ragioneSociale = "NetData Società per azioni";
		sede = "Via Roma, 39 Milano MI - Italia";
		settore = "Informatica";
		descrizione = "Consulenza e Data Analytics";
		condizioniAzienda = "on";	

		when(utenteRepository.existsByEmail(email)).thenReturn(false);
//		when(aziendaRepository.save(any(Azienda.class))).thenReturn(null);
		when(delegatoAziendaleRepository.save(any(DelegatoAziendale.class))).thenReturn(null);
		when(richiestaConvenzionamentoRepository.save(any(RichiestaConvenzionamento.class))).thenReturn(null);

		Azienda azienda = new Azienda(pIva, ragioneSociale, sede, settore, descrizione); 
		DelegatoAziendale delegato = new DelegatoAziendale(nome, cognome, sesso, email, indirizzo, password, ruolo, azienda);
		richiesta = new RichiestaConvenzionamento(RichiestaConvenzionamento.IN_ATTESA, azienda, delegato);
		
		try {
			validaCampi();
			
			richiestaConvenzionamentoService.salvaRichiestaConvenzionamento(richiesta);
			verify(richiestaConvenzionamentoRepository, times(1)).save(any(RichiestaConvenzionamento.class));

		} catch (RichiestaConvenzionamentoNonValidaException exception) {
			exception.printStackTrace();
		}
	}
	
	
	/**
	 * verifica metodo accettaRichiestaConvenzionamento con utente non autorizzato
	 */
	@Test
	public void accettaRichiestaConvenzionamento_NonAutorizzato() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new Studente());
//		when(richiestaConvenzionamentoRepository.findById(10L)).thenReturn(Optional.of(richiestaConvenzionamento));
//		when(richiestaConvenzionamento.getStato()).thenReturn(RichiestaConvenzionamento.IN_ATTESA);
//		when(richiestaConvenzionamentoRepository.save(richiestaConvenzionamento)).thenReturn(richiestaConvenzionamento);
		
//		when(richiestaConvenzionamento.getDelegatoAziendale()).thenReturn(delegatoAziendale);
//		when(delegatoAziendale.getEmail()).thenReturn("m.rossi2@gmail.com");
		
		final String message = "Operazione non autorizzata";

		try {
			richiestaConvenzionamentoService.accettaRichiestaConvenzionamento(10L);
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * verifica metodo accettaRichiestaConvenzionamento con stato non in attesa
	 */
	@Test
	public void accettaRichiestaConvenzionamento_StatoInvalido() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());
		when(richiestaConvenzionamentoRepository.findById(10L)).thenReturn(Optional.of(richiestaConvenzionamento));
		when(richiestaConvenzionamento.getStato()).thenReturn(RichiestaConvenzionamento.RIFIUTATA);
//		when(richiestaConvenzionamentoRepository.save(richiestaConvenzionamento)).thenReturn(richiestaConvenzionamento);
		
//		when(richiestaConvenzionamento.getDelegatoAziendale()).thenReturn(delegatoAziendale);
//		when(delegatoAziendale.getEmail()).thenReturn("m.rossi2@gmail.com");
		
		final String message = "Impossibile accettare questa richiesta";

		try {
			richiestaConvenzionamentoService.accettaRichiestaConvenzionamento(10L);
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	

	/**
	 * verifica metodo accettaRichiestaConvenzionamento successo
	 */
	@Test
	public void accettaRichiestaConvenzionamento_Successo() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());
		when(richiestaConvenzionamentoRepository.findById(10L)).thenReturn(Optional.of(richiestaConvenzionamento));
		when(richiestaConvenzionamento.getStato()).thenReturn(RichiestaConvenzionamento.IN_ATTESA);
		when(richiestaConvenzionamentoRepository.save(richiestaConvenzionamento)).thenReturn(richiestaConvenzionamento);
		
		when(richiestaConvenzionamento.getDelegatoAziendale()).thenReturn(delegatoAziendale);
		when(delegatoAziendale.getEmail()).thenReturn("m.rossi2@gmail.com");

		try {
			richiestaConvenzionamentoService.accettaRichiestaConvenzionamento(10L);
			verify(richiestaConvenzionamentoRepository, times(1)).save(any(RichiestaConvenzionamento.class));
		} catch (OperazioneNonAutorizzataException exception) {
			exception.printStackTrace();
		}
	}
	
	
	/**
	 * verifica metodo rifiutaRichiestaConvenzionamento con utente non autorizzato
	 */
	@Test
	public void rifiutaRichiestaConvenzionamento_NonAutorizzato() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new Studente());
//		when(richiestaConvenzionamentoRepository.findById(10L)).thenReturn(Optional.of(richiestaConvenzionamento));
//		when(richiestaConvenzionamento.getStato()).thenReturn(RichiestaConvenzionamento.IN_ATTESA);
//		when(richiestaConvenzionamentoRepository.save(richiestaConvenzionamento)).thenReturn(richiestaConvenzionamento);
		
//		when(richiestaConvenzionamento.getDelegatoAziendale()).thenReturn(delegatoAziendale);
//		when(delegatoAziendale.getEmail()).thenReturn("m.rossi2@gmail.com");
		
		final String message = "Operazione non autorizzata";

		try {
			richiestaConvenzionamentoService.rifiutaRichiestaConvenzionamento(10L);
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * verifica metodo rifiutaRichiestaConvenzionamento con stato non in attesa
	 */
	@Test
	public void rifiutaRichiestaConvenzionamento_StatoInvalido() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());
		when(richiestaConvenzionamentoRepository.findById(10L)).thenReturn(Optional.of(richiestaConvenzionamento));
		when(richiestaConvenzionamento.getStato()).thenReturn(RichiestaConvenzionamento.ACCETTATA);
//		when(richiestaConvenzionamentoRepository.save(richiestaConvenzionamento)).thenReturn(richiestaConvenzionamento);
		
//		when(richiestaConvenzionamento.getDelegatoAziendale()).thenReturn(delegatoAziendale);
//		when(delegatoAziendale.getEmail()).thenReturn("m.rossi2@gmail.com");
		
		final String message = "Impossibile rifiutare questa richiesta";

		try {
			richiestaConvenzionamentoService.rifiutaRichiestaConvenzionamento(10L);
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	

	/**
	 * verifica metodo rifiutaRichiestaConvenzionamento successo
	 */
	@Test
	public void rifiutaRichiestaConvenzionamento_Successo() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());
		when(richiestaConvenzionamentoRepository.findById(10L)).thenReturn(Optional.of(richiestaConvenzionamento));
		when(richiestaConvenzionamento.getStato()).thenReturn(RichiestaConvenzionamento.IN_ATTESA);
		when(richiestaConvenzionamentoRepository.save(richiestaConvenzionamento)).thenReturn(richiestaConvenzionamento);
		
		when(richiestaConvenzionamento.getDelegatoAziendale()).thenReturn(delegatoAziendale);
		when(delegatoAziendale.getEmail()).thenReturn("m.rossi2@gmail.com");

		try {
			richiestaConvenzionamentoService.rifiutaRichiestaConvenzionamento(10L);
			verify(richiestaConvenzionamentoRepository, times(1)).save(any(RichiestaConvenzionamento.class));
		} catch (OperazioneNonAutorizzataException exception) {
			exception.printStackTrace();
		}
	}
	
	
	/**
	 * verifica metodo visualizzaRichiesteConvenzionamentoDettagli con utente non autorizzato
	 */
	@Test
	public void visualizzaRichiesteConvenzionamentoDettagli_NonAutorizzato() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new Studente());
//		when(richiestaConvenzionamentoRepository.findAllByStato(RichiestaConvenzionamento.IN_ATTESA)).thenReturn(new ArrayList<RichiestaConvenzionamento>());

		final String message = "Operazione non autorizzata";

		try {
			richiestaConvenzionamentoService.visualizzaRichiesteConvenzionamentoDettagli();
		} catch (OperazioneNonAutorizzataException exception) {
			assertEquals(message, exception.getMessage());
		}
	}
	
	
	/**
	 * verifica metodo visualizzaRichiesteConvenzionamentoDettagli successo
	 */
	@Test
	public void visualizzaRichiesteConvenzionamentoDettagli_Successo() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(new ResponsabileUfficioTirocini());
		when(richiestaConvenzionamentoRepository.findAllByStato(RichiestaConvenzionamento.IN_ATTESA)).thenReturn(new ArrayList<RichiestaConvenzionamento>());

		try {
			richiestaConvenzionamentoService.visualizzaRichiesteConvenzionamentoDettagli();
			verify(richiestaConvenzionamentoRepository, times(1)).findAllByStato(RichiestaConvenzionamento.IN_ATTESA);
		} catch (OperazioneNonAutorizzataException exception) {
			exception.printStackTrace();
		}
	}
	

	/**
	 * verifica metodo visualizzaAziendeConvenzionate
	 */
	@Test
	public void visualizzaAziendeConvenzionate() {
		List<RichiestaConvenzionamento> l = new ArrayList<RichiestaConvenzionamento>();
		richiesta = new RichiestaConvenzionamento(RichiestaConvenzionamento.ACCETTATA, null, null);
		l.add(richiesta);
		when(richiestaConvenzionamentoRepository.findAllByStato(RichiestaConvenzionamento.ACCETTATA)).thenReturn(l);
		
		//when(richiestaConvenzionamento.getAzienda()).thenReturn(azienda);
//		when(aziendaList.add(azienda)).thenReturn(true);
		
		richiestaConvenzionamentoService.visualizzaAziendeConvenzionate();
	}
}
