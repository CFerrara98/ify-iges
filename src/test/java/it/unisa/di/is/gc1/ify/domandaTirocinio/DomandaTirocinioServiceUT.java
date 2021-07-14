package it.unisa.di.is.gc1.ify.domandaTirocinio;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.convenzioni.Azienda;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendale;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativo;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import it.unisa.di.is.gc1.ify.utenza.MailSingletonSender;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;

import javax.swing.text.html.Option;

/**
 * Test di unità per la classe DomandaTirocinioService; tipologia di test:
 * whitebox strategia: branch coverage.
 * 
 * @author Giacomo Izzo , Alessia Natale , Roberto Calabrese
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class DomandaTirocinioServiceUT {

	@Mock
	private DomandaTirocinioRepository domandaTirocinioRepository;

	@Mock
	private UtenzaService utenzaService;

	@Mock
	private DomandaTirocinio domandaMock;

	@Mock
	private Azienda aziendaMock;

	@Mock
	private DelegatoAziendale delegatoMock;

	@Mock
	private Studente studenteMock;

	@Mock
	private List<DomandaTirocinio> listaTirociniMock;

	@Mock
	private MailSingletonSender mail;

	@Autowired
	@InjectMocks
	private DomandaTirocinioService domandaTirocinioService;

	private Azienda azienda;

	private Studente studente;

	private ProgettoFormativo progettoFormativo;

	private DomandaTirocinio domanda;

	private DelegatoAziendale delegato;

	private DocenteTutor docenteTutor;

	private ResponsabileUfficioTirocini responsabile;

	private List<DomandaTirocinio> listaDomande;

	/**
	 * Inizializza delle istanze di domanda e entità collegate , prima di ogni test.
	 */
	@Before
	public void setUp() {
		// Crea il progetto formativo #1
		progettoFormativo = new ProgettoFormativo();
		// Crea l'azienda #1
		azienda = new Azienda();
		azienda.setpIva("0123456789");
		progettoFormativo.setAzienda(azienda);
		progettoFormativo.setStato(ProgettoFormativo.ATTIVO);

		delegato = new DelegatoAziendale();
		delegato.setAzienda(azienda);
		delegato.setEmail("m.rossi@gmail.com");

		docenteTutor = new DocenteTutor();
		docenteTutor.setEmail("docente@prova.it");
		docenteTutor.setCognome("Di Prova");
		docenteTutor.setNome("Docente");
		docenteTutor.setIndirizzo("via tal dei tali 1");
		docenteTutor.setSesso("M");
		docenteTutor.setCampoRicerca("Ingegneria");
		docenteTutor.setPassword("Password1");

		responsabile = new ResponsabileUfficioTirocini();

		// creo e salvo lo studente 1
		studente = new Studente();
		studente.setEmail("m.rossi@studenti.unisa.it");

		domanda = new DomandaTirocinio();
		domanda.setAzienda(azienda);
		domanda.setProgettoFormativo(progettoFormativo);
		domanda.setStudente(studente);
		domanda.setConoscenze("linguaggio c");
		domanda.setMotivazioni("crescita personale");
		domanda.setDataInizio(LocalDate.of(2019, 11, 10));
		domanda.setDataFine(LocalDate.of(2020, 03, 10));
		domanda.setCfu(6);
		domanda.setTutor(docenteTutor);
		domanda.setStato(DomandaTirocinio.IN_ATTESA);

	}

	/**
	 * Testa il caso in cui la lista da controllare sia vuota.
	 * 
	 * @test {@link DomandaTirocinioService#controllaStatoStudente()}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void controllaStatoStudenteListaVuota() {
		String messaggio = "";
		String messaggioRestituito = null;
		listaDomande = new ArrayList<>();
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(domandaTirocinioRepository.findAllByStato(DomandaTirocinio.IN_ATTESA)).thenReturn(listaDomande);
		try {
			messaggioRestituito = domandaTirocinioService.controllaStatoStudente();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertEquals(messaggio, messaggioRestituito);
	}

	/**
	 * Testa il caso in cui l' id della domanda non corrisponda a quello dello studente.
	 * 
	 * @test {@link DomandaTirocinioService#controllaStatoStudente()}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void controllaStatoStudenteIdNotEqual() {
		String messaggio = "";
		String messaggioRestituito = null;
		listaDomande = new ArrayList<DomandaTirocinio>();
		listaDomande.add(domandaMock);
		when(domandaMock.getStudente()).thenReturn(studenteMock);
		when(studenteMock.getId()).thenReturn(studente.getId()+1);
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(domandaTirocinioRepository.findAllByStato(DomandaTirocinio.IN_ATTESA)).thenReturn(listaDomande);
		try {
			messaggioRestituito = domandaTirocinioService.controllaStatoStudente();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertEquals(messaggio, messaggioRestituito);
	}

	/**
	 * Testa il caso in cui esista già una domanda in attesa.
	 * 
	 * @test {@link DomandaTirocinioService#controllaStatoStudente()}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void controllaStatoStudenteIdEqual() {
		String messaggio = "Hai già una domanda di tirocinio in attesa, non puoi inviarne un'altra al momento.";
		String messaggioRestituito = null;
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(domandaTirocinioRepository.findAllByStato(DomandaTirocinio.IN_ATTESA)).thenReturn(listaDomande);
		try {
			messaggioRestituito = domandaTirocinioService.controllaStatoStudente();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertEquals(messaggio, messaggioRestituito);
	}

	/**
	 * Testa il caso in cui il salvataggio della domanda vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#salvaDomandaTirocinio(DomandaTirocinio)}
	 * 
	 * @result Il test è superato se il metodo save del repository è correttamente invocato.
	 */
	@Test
	public void salvaDomandaTirocinio() {
		when(domandaTirocinioRepository.save(domanda)).thenReturn(domanda);
		domandaTirocinioService.salvaDomandaTirocinio(domanda);
		verify(domandaTirocinioRepository, times(1)).save(domanda);
	}

	/**
	 * Testa il caso in cui l'utente non sia autorizzato all'accettazione della domanda.
	 * 
	 * @test {@link DomandaTirocinioService#accettaDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void accettaDomandaTirocinioUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		try {
			domandaTirocinioService.accettaDomandaTirocinioByAzienda(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la piva di delegato e azienda associata alla domanda siano diverse.
	 * 
	 * @test {@link DomandaTirocinioService#accettaDomandaTirocinioByAzienda(Long)} (Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void accettaDomandaTirocinioPIvaNotEqual() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoMock);
		when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		when(delegatoMock.getAzienda()).thenReturn(aziendaMock);
		when(aziendaMock.getpIva()).thenReturn("0123456781");
		try {
			domandaTirocinioService.accettaDomandaTirocinioByAzienda(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la piva di delegato e azienda associata alla domanda siano diverse.
	 * 
	 * @test {@link DomandaTirocinioService#accettaDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void accettaDomandaTirocinioStatoNotInAttesa() {
		String messaggio = "Impossibile accettare questa domanda";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoMock);
		when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		when(delegatoMock.getAzienda()).thenReturn(aziendaMock);
		when(aziendaMock.getpIva()).thenReturn("0123456789");
		domanda.setStato(DomandaTirocinio.ACCETTATA);
		try {
			domandaTirocinioService.accettaDomandaTirocinioByAzienda(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui l'accettazione della domanda vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#accettaDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il metodo save del repository è correttamente invocato.
	 */
	@Test
	public void accettaDomandaTirocinio() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegato);
		when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		when(domandaTirocinioRepository.save(domanda)).thenReturn(domanda);
		try {
			domandaTirocinioService.accettaDomandaTirocinioByAzienda(domanda.getId());

		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(domandaTirocinioRepository, times(1)).save(domanda);
	}

	/**
	 * Testa il caso in cui l'utente non sia autorizzato al rifiuto della domanda.
	 * 
	 * @test {@link DomandaTirocinioService#rifiutoDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void rifiutoDomandaTirocinioUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		try {
			domandaTirocinioService.rifiutoDomandaTirocinioByAzienda(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la piva di delegato e azienda associata alla domanda siano diverse.
	 * 
	 * @test {@link DomandaTirocinioService#rifiutoDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void rifiutoDomandaTirocinioPivaNotEqual() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoMock);
		when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		when(delegatoMock.getAzienda()).thenReturn(aziendaMock);
		when(aziendaMock.getpIva()).thenReturn("0123456781");
		try {
			domandaTirocinioService.rifiutoDomandaTirocinioByAzienda(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui lo stato della domanda da rifiutare non sia in attesa.
	 * 
	 * @test {@link DomandaTirocinioService#rifiutoDomandaTirocinioByAzienda(Long)} (Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void rifiutoDomandaTirocinioStatoNotAttesa() {
		long idDomanda = domanda.getId();
		String messaggio = "Impossibile rifiutare questa domanda";
		when(domandaTirocinioRepository.save(domanda)).thenReturn(domanda);
		//when(domandaTirocinioRepository.findById(idDomanda)).thenReturn(Optional.of(domanda));
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoMock);
		when(domandaTirocinioRepository.findById(idDomanda)).thenReturn(Optional.of(domanda));
		when(delegatoMock.getAzienda()).thenReturn(aziendaMock);
		when(aziendaMock.getpIva()).thenReturn("0123456789");
		domanda.setStato(DomandaTirocinio.ACCETTATA);
		System.out.println("domanda " + domanda.getId());
		try {
			domandaTirocinioService.rifiutoDomandaTirocinioByAzienda(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il rifiuto della domanda vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#rifiutoDomandaTirocinioByAzienda(Long)} (Long)}
	 * 
	 * @result Il test è superato se il metodo save del repository è correttamente invocato.
	 */
	@Test
	public void rifiutoDomandaTirocinio() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegato);
		when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		when(domandaTirocinioRepository.save(domanda)).thenReturn(domanda);
		try {
			domandaTirocinioService.rifiutoDomandaTirocinioByAzienda(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(domandaTirocinioRepository , times(1)).save(domanda);
	}
	
	/**
	 * Testa il caso in cui l'utente non sia autorizzato all'approvazione della domanda.
	 * 
	 * @test {@link DomandaTirocinioService#approvazioneDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void approvazioneDomandaTirocinioUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		try {
			domandaTirocinioService.approvazioneDomandaTirocinio(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui lo stato della domanda da approvare non sia 'accettata'.
	 * 
	 * @test {@link DomandaTirocinioService#approvazioneDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void approvazioneDomandaTirocinioStatoFail() {
		String messaggio = "Impossibile rifiutare questa domanda";
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		try {
			domandaTirocinioService.approvazioneDomandaTirocinio(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui l'approvazione della domdanda vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#approvazioneDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il metodo save del repository è correttamente invocato.
	 */
	@Test
	public void approvazioneDomandaTirocinio() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		when(domandaTirocinioRepository.save(domanda)).thenReturn(domanda);
		domanda.setStato(DomandaTirocinio.ACCETTATA);
		try {
			domandaTirocinioService.approvazioneDomandaTirocinio(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(domandaTirocinioRepository , times(1)).save(domanda);
	}
	
	/**
	 * Testa il caso in cui l'utente non sia autorizzato al respingimento della domanda.
	 * 
	 * @test {@link DomandaTirocinioService#respintaDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void respintaDomandaTirocinioUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		try {
			domandaTirocinioService.respintaDomandaTirocinio(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui lo stato della domanda da respingere non sia 'accettata'.
	 * 
	 * @test {@link DomandaTirocinioService#respintaDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void respintaDomandaTirocinioStatoFail() {
		String messaggio = "Impossibile rifiutare questa domanda";
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		try {
			domandaTirocinioService.respintaDomandaTirocinio(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui il respingimento della domanda vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#respintaDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se il metodo save del repository è correttamente invocato.
	 */
	@Test
	public void respintaDomandaTirocinio() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		when(domandaTirocinioRepository.save(domanda)).thenReturn(domanda);
		domanda.setStato(DomandaTirocinio.ACCETTATA);
		try {
			domandaTirocinioService.respintaDomandaTirocinio(domanda.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(domandaTirocinioRepository , times(1)).save(domanda);
	}
	
	/**
	 * Testa il caso in cui l'utente non sia autorizzato alla  delle domande.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInAttesaAzienda(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaDomandeTirocinioInAttesaAziendaUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInAttesaAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui la piva di delegato e azienda associata alle domande siano diverse.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInAttesaAzienda(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaDomandeTirocinioInAttesaAziendaPivaNotEqual() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegato);
		String piva = "0123456781";
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInAttesaAzienda(piva);
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui la visualizzazione delle domande vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInAttesaAzienda(String)}
	 * 
	 * @result Il test è superato se il metodo findAllByAziendaPIvaAndStato del repository
	 * è correttamente invocato.
	 */
	@Test
	public void visualizzaDomandeTirocinioInAttesaAzienda() {

		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);

		when(utenzaService.getUtenteAutenticato()).thenReturn(delegato);
		//when(domandaTirocinioRepository.save(domanda)).thenReturn(domanda);
		when(domandaTirocinioRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), DomandaTirocinio.IN_ATTESA)).thenReturn(listaDomande);
		when(domandaTirocinioRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), DomandaTirocinio.IN_ATTESA_AZIENDA)).thenReturn(listaDomande);
		System.out.println("ciao piva"+ azienda.getpIva());
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInAttesaAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(domandaTirocinioRepository , times(1)).findAllByAziendaPIvaAndStato(azienda.getpIva() , DomandaTirocinio.IN_ATTESA);

	}
	
	/**
	 * Testa il caso in cui l'utente non sia autorizzato alla  delle domande.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInoltrateAzienda(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaDomandeTirocinioInoltrateAziendaUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInoltrateAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la piva di delegato e azienda associata alle domande siano diverse.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInoltrateAzienda(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaDomandeTirocinioInoltrateAziendaPivaNotEqual() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegato);
		String piva = "0123456781";
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInoltrateAzienda(piva);
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui la visualizzazione delle domande inoltrate vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInoltrateAzienda(String)}
	 * 
	 * @result Il test è superato se il metodo findAllByAziendaPIvaAndStato del repository è correttamente
	 * invocato .
	 */
	@Test
	public void visualizzaDomandeTirocinioInoltrateAzienda() {
		listaDomande = new ArrayList<>();
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegato);
		when(domandaTirocinioRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), DomandaTirocinio.ACCETTATA)).thenReturn(listaDomande);
		when(domandaTirocinioRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), DomandaTirocinio.APPROVATA)).thenReturn(listaDomande);
		when(domandaTirocinioRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), DomandaTirocinio.RESPINTA)).thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInoltrateAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(domandaTirocinioRepository , times(1)).findAllByAziendaPIvaAndStato(azienda.getpIva() , DomandaTirocinio.ACCETTATA);
		verify(domandaTirocinioRepository , times(1)).findAllByAziendaPIvaAndStato(azienda.getpIva() , DomandaTirocinio.APPROVATA);
		verify(domandaTirocinioRepository , times(1)).findAllByAziendaPIvaAndStato(azienda.getpIva() , DomandaTirocinio.RESPINTA);

	}
	
	/**
	 * Testa il caso in cui l'utente non sia autorizzato alla  dei tirocini.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoAzienda(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaTirociniInCorsoAziendaUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la piva di delegato e azienda associata alle domande siano diverse.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoAzienda(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaTirociniInCorsoAziendaPivaNotEqual() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoMock);
		//when(domandaTirocinioRepository.findById(domanda.getId())).thenReturn(Optional.of(domanda));
		when(delegatoMock.getAzienda()).thenReturn(aziendaMock);
		when(aziendaMock.getpIva()).thenReturn("0123456781");
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la lista dei tirocini in corso restituita sia vuota.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoAzienda(String)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoAziendaListaVuota() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		listaDomande = new ArrayList<>();
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoMock);
		when(delegatoMock.getAzienda()).thenReturn(azienda);
		when(domandaTirocinioRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), DomandaTirocinio.APPROVATA))
				.thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Testa il caso in cui la data di fine di uno dei tirocini in lista sia precedente 
	 * alla data odierna.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoAzienda(String)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoAziendaFuoriDataFine() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		domanda.setDataFine(LocalDate.now().minusDays(1));
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoMock);
		when(delegatoMock.getAzienda()).thenReturn(azienda);
		when(domandaTirocinioRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), DomandaTirocinio.APPROVATA))
				.thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Testa il caso in cui la data di inizio di uno dei tirocini in lista sia successiva 
	 * alla data odierna.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoAzienda(String)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoAziendaFuoriDataInizio() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		domanda.setDataInizio(LocalDate.now().plusDays(1));
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoMock);
		when(delegatoMock.getAzienda()).thenReturn(azienda);
		when(domandaTirocinioRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), DomandaTirocinio.APPROVATA))
				.thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Testa il caso in cui la visualizzazione dei tirocini in corso vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoAzienda(String)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoAzienda() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoMock);
		when(delegatoMock.getAzienda()).thenReturn(azienda);
		when(domandaTirocinioRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), DomandaTirocinio.APPROVATA))
				.thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Testa il caso in cui l'utente non sia autorizzato alla  delle domande.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInoltrateStudente(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaDomandeTirocinioInoltrateStudenteUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInoltrateStudente(studente.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui l'id dello utente autenticato sia diverso da quello passato come 
	 * parametro.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInoltrateStudente(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaDomandeTirocinioInoltrateStudenteIdNotEqual() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studenteMock);
		when(studenteMock.getId()).thenReturn(studente.getId() + 1);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInoltrateStudente(studente.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la visualizzazione delle domande inoltrate vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInoltrateStudente(Long)}
	 * 
	 * @result Il test è superato se il metodo findAllByStudenteId del repository è correttamente
	 * invocato.
	 */
	@Test
	public void visualizzaDomandeTirocinioInoltrateStudente() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInoltrateStudente(studente.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(domandaTirocinioRepository, times(1)).findAllByStudenteId(studente.getId());
	}

	/**
	 * Testa il caso in cui l'utente non sia autorizzato alla visualizzazione delle domande.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoStudente(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaTirociniInCorsoStudenteUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoStudente(studente.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui l'id dello utente autenticato sia diverso da quello passato come 
	 * parametro.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoStudente(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaTirociniInCorsoStudenteIdNotEqual() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studenteMock);
		when(studenteMock.getId()).thenReturn(studente.getId() + 1);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoStudente(studente.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la lista dei tirocini in corso restituita sia vuota.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoStudente(Long)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoStudenteListaVuota() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		listaDomande = new ArrayList<>();
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(domandaTirocinioRepository.findAllByStudenteIdAndStato(studente.getId(), DomandaTirocinio.APPROVATA))
				.thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoStudente(studente.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui la data di inizio di uno dei tirocini in lista sia successiva 
	 * alla data odierna.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoStudente(Long)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoStudenteFuoriDataInizio() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		domanda.setDataInizio(LocalDate.now().plusDays(2));
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(domandaTirocinioRepository.findAllByStudenteIdAndStato(studente.getId(), DomandaTirocinio.APPROVATA))
				.thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoStudente(studente.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui la data di fine di uno dei tirocini in lista sia precedente 
	 * alla data odierna.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoStudente(Long)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoStudenteFuoriDataFine() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		domanda.setDataFine(LocalDate.now().minusDays(1));
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(domandaTirocinioRepository.findAllByStudenteIdAndStato(studente.getId(), DomandaTirocinio.APPROVATA))
				.thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoStudente(studente.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui la visualizzazione dei tirocini in corso vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoStudente(Long)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoStudente() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(domandaTirocinioRepository.findAllByStudenteIdAndStato(studente.getId(), DomandaTirocinio.APPROVATA))
				.thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoStudente(studente.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui l'utente non sia autorizzato alla visualizzazione delle domande.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInAttesaUfficio()}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaDomandeTirocinioInAttesaUfficioUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInAttesaUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la visualizzazione delle domande vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInAttesaUfficio()}
	 * 
	 * @result Il test è superato se il metodo findAllByStato del repository è correttamente 
	 * invocato.
	 */
	@Test
	public void visualizzaDomandeTirocinioInAttesaUfficio() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		when(domandaTirocinioRepository.findAllByStato(DomandaTirocinio.ACCETTATA)).thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioInAttesaUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(domandaTirocinioRepository, times(1)).findAllByStato(DomandaTirocinio.ACCETTATA);
	}

	/**
	 * Testa il caso in cui l'utente non sia autorizzato alla visualizzazione delle domande.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioValutateUfficio()}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaDomandeTirocinioValutateUfficioUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioValutateUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la visualizzazione delle domande vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioValutateUfficio()}
	 * 
	 * @result Il test è superato se il metodo findAllByStato del repository è correttamente
	 * invocato.
	 */
	@Test
	public void visualizzaDomandeTirocinioValutateUfficio() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		//when(domandaTirocinioRepository.findAllByStato(DomandaTirocinio.ACCETTATA)).thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaDomandeTirocinioValutateUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(domandaTirocinioRepository, times(1)).findAllByStato(DomandaTirocinio.APPROVATA);
		verify(domandaTirocinioRepository, times(1)).findAllByStato(DomandaTirocinio.RESPINTA);
	}

	/**
	 * Testa il caso in cui l'utente non sia autorizzato alla visualizzazione delle domande.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoUfficio()}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaTirociniInCorsoUfficioUtenteNonAutorizzato() {
		String messaggio = "Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(messaggio, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la lista dei tirocini in corso restituita sia vuota.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoUfficio()}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoUfficioListaVuota() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		listaDomande = new ArrayList<>();
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		when(domandaTirocinioRepository.findAllByStato(DomandaTirocinio.APPROVATA)).thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui la data di inizio di uno dei tirocini in lista sia successiva 
	 * alla data odierna.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoUfficio()}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoUfficioFuoriDataInizio() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		domanda.setDataInizio(LocalDate.now().plusDays(1));
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		when(domandaTirocinioRepository.findAllByStato(DomandaTirocinio.APPROVATA)).thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui la data di fine di uno dei tirocini in lista sia precedente 
	 * alla data odierna.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoUfficio()}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoUfficioFuoriDataFine() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		domanda.setDataFine(LocalDate.now().minusDays(1));
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		when(domandaTirocinioRepository.findAllByStato(DomandaTirocinio.APPROVATA)).thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui la visualizzazione dei tirocini vada a buon fine.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoUfficio()}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void visualizzaTirociniInCorsoUfficio() {
		domanda.setStato(DomandaTirocinio.APPROVATA);
		listaDomande = new ArrayList<>();
		listaDomande.add(domanda);
		when(utenzaService.getUtenteAutenticato()).thenReturn(responsabile);
		when(domandaTirocinioRepository.findAllByStato(DomandaTirocinio.APPROVATA)).thenReturn(listaDomande);
		try {
			domandaTirocinioService.visualizzaTirociniInCorsoUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui il campo conoscenze inserito , sia null.
	 * 
	 * @test {@link DomandaTirocinioService#validaConoscenze(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaConoscenzeNull() {
		String message = "Il campo conoscenze non può essere nullo.";
		try {
			domandaTirocinioService.validaConoscenze(null);
		} catch (DomandaTirocinioNonValidaException e) {
			DomandaTirocinioNonValidaException e1 = new DomandaTirocinioNonValidaException();
			DomandaTirocinioNonValidaException e2 = new DomandaTirocinioNonValidaException(e.getTarget());
			e1.hashCode();
			e2.hashCode();
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la lunghezza del campo conoscenze inserito sia minore della 
	 * lunghezza minima.
	 * 
	 * @test {@link DomandaTirocinioService#validaConoscenze(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaConoscenzeLunghezzaMinFail() {
		String message = "il campo Conoscenze Tecniche deve contenere almeno un carattere.";
		try {
			domandaTirocinioService.validaConoscenze("");
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la lunghezza del campo conoscenze inserito sia maggiore della 
	 * lunghezza massima.
	 * 
	 * @test {@link DomandaTirocinioService#validaConoscenze(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaConoscenzeLunghezzaMaxFail() {
		String message = "il campo Conoscenze Tecniche deve contenere al massimo 200 caratteri.";
		try {
			domandaTirocinioService.validaConoscenze("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il campo conoscenze inserito , sia corretto.
	 * 
	 * @test {@link DomandaTirocinioService#validaConoscenze(String)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void validaConoscenze() {
		try {
			domandaTirocinioService.validaConoscenze(domanda.getConoscenze());
		} catch (DomandaTirocinioNonValidaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Testa il caso in cui il campo motivazioni inserito , sia null.
	 * 
	 * @test {@link DomandaTirocinioService#validaMotivazioni(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaMotivazioniNull() {
		String message = "Il campo motivazioni non può essere nullo.";
		try {
			domandaTirocinioService.validaMotivazioni(null);
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la lunghezza del campo motivazioni inserito , sia minore della 
	 * lunghezza minima.
	 * 
	 * @test {@link DomandaTirocinioService#validaMotivazioni(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaMotivazioniLunghezzaMinFail() {
		String message = "il campo Motivazioni deve contenere almeno un carattere.";
		try {
			domandaTirocinioService.validaMotivazioni("");
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la lunghezza del campo conoscenze inserito sia maggiore della 
	 * lunghezza massima.
	 * 
	 * @test {@link DomandaTirocinioService#validaMotivazioni(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaMotivazioniLunghezzaMaxFail() {
		String message = "il campo Motivazioni deve contenere al massimo 300 caratteri.";
		try {
			domandaTirocinioService.validaMotivazioni("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaa");
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il campo motivazioni inserito , sia corretto.
	 * 
	 * @test {@link DomandaTirocinioService#validaMotivazioni(String)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void validaMotivazioni() {
		try {
			domandaTirocinioService.validaMotivazioni(domanda.getMotivazioni());
		} catch (DomandaTirocinioNonValidaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui il campo data inizio inserito , sia null.
	 * 
	 * @test {@link DomandaTirocinioService#validaDataInizio(LocalDate)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaDataInizioNull() {
		String message = "Il campo data inizio non può essere nullo.";
		try {
			domandaTirocinioService.validaDataInizio(null);
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la data di inizio inserita ,  sia antecedente alla minima
	 * data di inizio consentita .
	 * 
	 * @test {@link DomandaTirocinioService#validaDataInizio(LocalDate)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaDataInizioDataMinFail() {
		String message = "Il campo Data Inizio Tirocinio deve contenere una data maggiore della data corrente.";
		try {
			domandaTirocinioService.validaDataInizio(LocalDate.now().minusDays(1));
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il campo data inizio inserito , sia corretto.
	 * 
	 * @test {@link DomandaTirocinioService#validaDataInizio(LocalDate)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void validaDataInizio() {
		try {
			domandaTirocinioService.validaDataInizio(LocalDate.now().plusDays(1));
		} catch (DomandaTirocinioNonValidaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui il campo data fine inserito , sia null.
	 * 
	 * @test
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaDataFineNull() {
		String message = "Il campo data fine non può essere nullo.";
		try {
			domandaTirocinioService.validaDataFine(LocalDate.now(), null);
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui la data di fine inserita, sia antecedente alla data di 
	 * inizio inserita .
	 * 
	 *
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaDataFineDataFineFail() {
		String message = "il campo Data Fine Tirocinio deve contenere una data di fine maggiore della data di inizio del tirocinio.";
		try {
			domandaTirocinioService.validaDataFine(LocalDate.now(), LocalDate.now().minusDays(1));
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il campo data fine inserito, sia corretto.
	 * 
	 * @test
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void validaDataFine() {
		try {
			domandaTirocinioService.validaDataFine(LocalDate.now(), LocalDate.now().plusMonths(6));
		} catch (DomandaTirocinioNonValidaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui il campo cfu inserito, sia null.
	 * 
	 * @test {@link DomandaTirocinioService#cfu(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void cfuNull() {
		String message = "Il campo cfu non può essere nullo.";
		try {
			domandaTirocinioService.cfu(null);
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il cfu inserito , sia vuoto.
	 * 
	 * @test {@link DomandaTirocinioService#cfu(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void cfuVoid() {
		String message = "Il campo cfu non può essere nullo.";
		try {
			domandaTirocinioService.cfu("");
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il cfu inserito , sia minore dei cfu minimi.
	 * 
	 * @test {@link DomandaTirocinioService#cfu(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void cfuRangeMinFail() {
		String message = "Il campo Numero CFU Associati deve contenere valori che vadano da 6 a 12.";
		String cfu = String.valueOf(DomandaTirocinio.MIN_CFU - 1);
		try {
			domandaTirocinioService.cfu(cfu);
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il cfu inserito , sia maggiore dei cfu massimi.
	 * 
	 * @test {@link DomandaTirocinioService#cfu(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void cfuRangeMaxFail() {
		String message = "Il campo Numero CFU Associati deve contenere valori che vadano da 6 a 12.";
		String cfu = String.valueOf(DomandaTirocinio.MAX_CFU + 1);
		try {
			domandaTirocinioService.cfu(cfu);
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il campo data cfu inserito, sia corretto.
	 * 
	 * @test {@link DomandaTirocinioService#cfu(String)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void cfu() {
		String cfu = String.valueOf(domanda.getCfu());
		try {
			domandaTirocinioService.cfu(cfu);
		} catch (DomandaTirocinioNonValidaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testa il caso in cui il campo condizioni inserito, sia null.
	 * 
	 * @test {@link DomandaTirocinioService#validaCondizioni(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaCondizioniNull() {
		String message = "È obbligatorio accettare le condizioni sulla privacy.";
		try {
			domandaTirocinioService.validaCondizioni(null);
		} catch (DomandaTirocinioNonValidaException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testa il caso in cui il campo condizioni inserito, sia corretto.
	 * 
	 * @test {@link DomandaTirocinioService#validaCondizioni(String)}
	 * 
	 * @result Il test è superato se il branch corrispondente al caso è correttamente raggiunto.
	 */
	@Test
	public void validaCondizioni() {
		try {
			domandaTirocinioService.validaCondizioni("Accetto");
		} catch (DomandaTirocinioNonValidaException e) {
			e.printStackTrace();
		}
	}

}
