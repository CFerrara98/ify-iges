package it.unisa.di.is.gc1.ify.domandaTirocinio;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.convenzioni.Azienda;
import it.unisa.di.is.gc1.ify.convenzioni.AziendaRepository;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendale;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendaleRepository;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativo;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;

/**
 * Classe di test d'integrazione DomandaTirocinioRepository -
 * (ProgettoFormativoRepository - azienda repository delegatoAziendaleRepository
 * Metodologia: bottom-up.
 * 
 * @author Giacomo Izzo , Alessia Natale, Roberto Calabrese.
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class DomandaTirocinioServiceRepositoriesIT {


	@Autowired
	private AziendaRepository aziendeRepository;
	
	@Autowired 
	private RichiestaConvenzionamentoRepository convenzionamentiRepository;
	
	@Autowired
	private RichiestaIscrizioneRepository iscrizioneRepository;
	
	@Autowired
	private StudenteRepository studenteRepository;
	
	@Autowired
	private DomandaTirocinioRepository domandeRepository;

	@Autowired
	private StudenteRepository studentiRepository;

	@Autowired
	private ProgettoFormativoRepository progettiRepository;

	@Autowired
	private UtenzaService utenzaService;

	@Autowired
	private DelegatoAziendaleRepository delegatoRepository;

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private DomandaTirocinioService domandaTirocinioService;
	
	@Autowired
	private ResponsabileUfficioTirociniRepository responsabileRepository;

	private List<ProgettoFormativo> listaProgettiFormativi;

	private List<Azienda> listaAziende;

	private List<Studente> listaStudenti;

	private List<DomandaTirocinio> listaDomande;

	private List<String> listaStati;

	private List<DelegatoAziendale> listaDelegati;

	private Azienda azienda1;

	private Azienda azienda2;

	private Studente studente1;

	private Studente studente2;

	private DelegatoAziendale delegato1;

	private DelegatoAziendale delegato2;

	private ResponsabileUfficioTirocini responsabile;

	private ProgettoFormativo progettoFormativo1;

	private ProgettoFormativo progettoFormativo2;

	private DomandaTirocinio domanda1;

	private DomandaTirocinio domanda2;

	/**
	 * Salva la lista delle domande di tirocinio sul database prima dell'esecuzione
	 * di ogni singolo test.
	 */
	@Before
	public void setUp() {
		domandeRepository.deleteAll();
		progettiRepository.deleteAll();
		convenzionamentiRepository.deleteAll();
		delegatoRepository.deleteAll();
		aziendeRepository.deleteAll();
		iscrizioneRepository.deleteAll();
		studenteRepository.deleteAll();
		responsabileRepository.deleteAll();
		utenteRepository.deleteAll();
		
		listaProgettiFormativi = new ArrayList<ProgettoFormativo>();
		listaAziende = new ArrayList<Azienda>();
		listaStudenti = new ArrayList<Studente>();
		listaDomande = new ArrayList<DomandaTirocinio>();
		listaStati = new ArrayList<String>();
		listaDelegati = new ArrayList<DelegatoAziendale>();

		listaStati.add(DomandaTirocinio.IN_ATTESA);
		listaStati.add(DomandaTirocinio.ACCETTATA);
		listaStati.add(DomandaTirocinio.RIFIUTATA);
		listaStati.add(DomandaTirocinio.APPROVATA);
		listaStati.add(DomandaTirocinio.RESPINTA);

		// Crea il progetto formativo #1
		progettoFormativo1 = new ProgettoFormativo();
		// Crea l'azienda #1
		azienda1 = new Azienda();
		azienda1.setDescrizione("azienda di software informatici");
		azienda1.setpIva("01234567892");
		azienda1.setRagioneSociale("azienda 1");
		azienda1.setSede("Roma");
		azienda1.setSettore("Informatica");
		progettoFormativo1.setAmbito("Informatica");
		progettoFormativo1.setAttivita("tirocinio");
		progettoFormativo1.setAzienda(azienda1);
		progettoFormativo1.setConoscenze("linguaggio c e Java");
		progettoFormativo1.setData_compilazione(LocalDate.of(2019, 11, 11));
		progettoFormativo1.setMax_partecipanti(3);
		progettoFormativo1.setNome("Progetto 1");
		progettoFormativo1.setStato(ProgettoFormativo.ATTIVO);

		// creo e salvo lo studente 1
		studente1 = new Studente();
		studente1.setNome("Mario");
		studente1.setCognome("Rossi");
		studente1.setDataNascita(LocalDate.of(1997, 12, 24));
		studente1.setEmail("m.rossi@studenti.unisa.it");
		studente1.setIndirizzo("Via Roma 4 84080 Salerno SA");
		studente1.setMatricola("0512105144");
		studente1.setTelefono("333-3544541");
		studente1.setSesso("M");
		studente1.setPassword("Password#1");

		domanda1 = new DomandaTirocinio();
		domanda1.setAzienda(azienda1);
		domanda1.setProgettoFormativo(progettoFormativo1);
		domanda1.setStudente(studente1);
		domanda1.setConoscenze("linguaggio c");
		domanda1.setMotivazioni("crescita personale");
		domanda1.setDataInizio(LocalDate.of(2019, 11, 10));
		domanda1.setDataFine(LocalDate.of(2020, 03, 10));
		domanda1.setCfu(6);
		domanda1.setStato(DomandaTirocinio.IN_ATTESA);

		delegato1 = new DelegatoAziendale();
		delegato1.setEmail("g.bianchi@gmail.it");
		delegato1.setAzienda(azienda1);

		studentiRepository.save(studente1);
		// aziendeRepository.save(azienda1);
		progettiRepository.save(progettoFormativo1);
		domandeRepository.save(domanda1);
		delegatoRepository.save(delegato1);
		listaProgettiFormativi.add(progettoFormativo1);
		listaAziende.add(azienda1);
		listaStudenti.add(studente1);
		listaDomande.add(domanda1);

		// Crea il progetto formativo #2
		progettoFormativo2 = new ProgettoFormativo();
		// Crea l'azienda #2
		azienda2 = new Azienda();
		azienda2.setDescrizione("azienda di software gestionali");
		azienda2.setpIva("01234789564");
		azienda2.setRagioneSociale("azienda 2");
		azienda2.setSede("Milano");
		azienda2.setSettore("Informatica");
		progettoFormativo2.setAmbito("Informatica");
		progettoFormativo2.setAttivita("progetto");
		progettoFormativo2.setAzienda(azienda2);
		progettoFormativo2.setConoscenze("html e css");
		progettoFormativo2.setData_compilazione(LocalDate.of(2019, 12, 12));
		progettoFormativo2.setMax_partecipanti(2);
		progettoFormativo2.setNome("Progetto 2");
		progettoFormativo2.setStato(ProgettoFormativo.ATTIVO);

		// creo e salvo lo studente 2
		studente2 = new Studente();
		studente2.setNome("Matteo");
		studente2.setCognome("Verdi");
		studente2.setDataNascita(LocalDate.of(1998, 04, 12));
		studente2.setEmail("m.verdi@studenti.unisa.it");
		studente2.setIndirizzo("Via Padova 4 84080 Salerno SA");
		studente2.setMatricola("0512104051");
		studente2.setTelefono("333-3241540");
		studente2.setSesso("M");
		studente2.setPassword("Password#2");
		
		domanda2 = new DomandaTirocinio();
		domanda2.setAzienda(azienda1);
		domanda2.setProgettoFormativo(progettoFormativo1);
		domanda2.setStudente(studente1);
		domanda2.setConoscenze("linguaggio c");
		domanda2.setMotivazioni("crescita personale");
		domanda2.setDataInizio(LocalDate.of(2019, 11, 10));
		domanda2.setDataFine(LocalDate.of(2020, 03, 10));
		domanda2.setCfu(6);
		domanda2.setStato(DomandaTirocinio.IN_ATTESA);
		
		String conoscenze = "linguaggio java";
		String motivazioni ="introduizione al mondo lavorativo";
		LocalDate dataInizio = LocalDate.of(2019, 9, 10);
		LocalDate dataFine = LocalDate.of(2020, 01, 10);
		int cfu = 6;
		String stato = DomandaTirocinio.ACCETTATA;
		
		DomandaTirocinio domanda3 = new DomandaTirocinio(1 , conoscenze, motivazioni, dataInizio, dataFine, cfu, stato, progettoFormativo2, azienda2, studente2);
		domanda3.getAzienda();
		
		delegato2 = new DelegatoAziendale();
		delegato2.setEmail("f.rossi@gmail.it");
		delegato2.setAzienda(azienda2);

		responsabile = new ResponsabileUfficioTirocini();
		responsabile.setEmail("m.rossi@unisa.it");
		utenteRepository.save(responsabile);

		studentiRepository.save(studente2);
		// aziendeRepository.save(azienda2);
		delegatoRepository.save(delegato2);
		progettiRepository.save(progettoFormativo2);
		domandeRepository.save(domanda2);

		listaProgettiFormativi.add(progettoFormativo2);
		listaAziende.add(azienda2);
		listaStudenti.add(studente2);
		listaDomande.add(domanda2);

		listaDelegati.add(delegato1);
		listaDelegati.add(delegato2);
	}

	/**
	 * Testa il corretto funzionamento del metodo di controllo stato di una domanda.
	 * 
	 * @test {@link DomandaTirocinioService#controllaStatoStudente()}
	 * 
	 * @result Il test è superato se il messaggio restituito dal sistema è uguale a quello previsto 
	 * dall' oracolo.
	 */
	@Test
	public void controllaStatoStudenteDomandaEsistente() {
		String messaggio = "Hai già una domanda di tirocinio in attesa, non puoi inviarne un'altra al momento.";
		String messaggioRestituito = null;
		utenzaService.setUtenteAutenticato(studente1.getEmail());
		try {
			messaggioRestituito = domandaTirocinioService.controllaStatoStudente();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertEquals(messaggio, messaggioRestituito);
	}

	/**
	 * Testa il corretto funzionamento del metodo di controllo stato di una domanda.
	 * 
	 * @test {@link DomandaTirocinioService#controllaStatoStudente()}
	 * 
	 * @result Il test è superato se il messaggio restituito dal sistema è uguale a quello previsto 
	 * dall' oracolo.
	 */
	@Test
	public void controllaStatoStudenteDomandaNonEsistente() {
		String messaggio = "";
		String messaggioRestituito = null;
		utenzaService.setUtenteAutenticato(studente2.getEmail());
		try {
			messaggioRestituito = domandaTirocinioService.controllaStatoStudente();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertEquals(messaggio ,messaggioRestituito);
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di salvataggio di una domanda.
	 * 
	 * @test {@link DomandaTirocinioService#salvaDomandaTirocinio(DomandaTirocinio)}
	 * 
	 * @result Il test è superato se la domanda è correttamente salvata.
	 */
	@Test
	public void salvaDomandaTirocinio() {
		DomandaTirocinio domandaRestituita = domandaTirocinioService.salvaDomandaTirocinio(domanda1);
		assertThat(domanda1, is(equalTo(domandaRestituita)));
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di accettazione di una domanda.
	 * 
	 * @test {@link DomandaTirocinioService}
	 * 
	 * @result Il test è superato se la domanda è correttamente accettata.
	 */
	@Test
	public void accettaDomandaTirocinio() {
		utenzaService.setUtenteAutenticato(delegato1.getEmail());
		DomandaTirocinio domandaRestituita = null;
		try {
			domandaRestituita = domandaTirocinioService.accettaDomandaTirocinio(domanda1.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertThat(domanda1, is(equalTo(domandaRestituita)));
	}

	/**
	 * Testa il corretto funzionamento del metodo di visualizzazione delle domande in attesa.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInAttesaAzienda(String)}
	 * 
	 * @result Il test è superato se la lista delle domande restituite corrisponde a quelle salvate.
	 */
	@Test
	public void visualizzaDomandeTirocinioInAttesaAzienda() {
		List<DomandaTirocinio> domandeRestituite = new ArrayList<DomandaTirocinio>();
		for (DelegatoAziendale delegato : listaDelegati) {
			utenzaService.setUtenteAutenticato(delegato.getEmail());
			try {
				domandeRestituite = domandaTirocinioService
						.visualizzaDomandeTirocinioInAttesaAzienda(delegato.getAzienda().getpIva());
			} catch (OperazioneNonAutorizzataException e) {
				e.printStackTrace();
			}
			for (DomandaTirocinio domandaTirocinio : domandeRestituite)
				assertThat(listaDomande.contains(domandaTirocinio), is(true));
		}
	}

	/**
	 * Testa il corretto funzionamento del metodo di respingimento di una domanda.
	 * 
	 * @test {@link DomandaTirocinioService#respintaDomandaTirocinio(Long)}
	 * 
	 * @result Il test è superato se la domanda è correttamente respinta.
	 */
	@Test
	public void respintaDomandaTirocinio() {
		DomandaTirocinio domandaRestituita = null;
		utenzaService.setUtenteAutenticato(responsabile.getEmail());
		for (DomandaTirocinio domanda : listaDomande) {
			domanda.setStato(DomandaTirocinio.ACCETTATA);
			try {
				domandaRestituita = domandaTirocinioService.respintaDomandaTirocinio(domanda.getId());
			} catch (OperazioneNonAutorizzataException e) {
				e.printStackTrace();
			}
			domanda.setStato(DomandaTirocinio.RESPINTA);
			assertThat(domanda, is(equalTo(domandaRestituita)));
		}
	}

	/**
	 * Testa il corretto funzionamento del metodo di visualizzazione dei tirocini in corso per studente.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoStudente(Long)}
	 * 
	 * @result Il test è superato se la lista delle domande restituite corrisponde a quelle salvate.
	 */
	@Test
	public void visualizzaTirociniInCorsoStudente() {
		List<DomandaTirocinio> domandeRestituite = new ArrayList<DomandaTirocinio>();
		for (Studente studente : listaStudenti) {
			utenzaService.setUtenteAutenticato(studente.getEmail());
			try {
				domandeRestituite = domandaTirocinioService.visualizzaTirociniInCorsoStudente(studente.getId());
			} catch (OperazioneNonAutorizzataException e) {
				e.printStackTrace();
			}
			for (DomandaTirocinio domandaTirocinio : domandeRestituite)
				assertThat(listaDomande.contains(domandaTirocinio), is(true));
		}
	}

	/**
	 * Testa il corretto funzionamento del metodo di visualizzazione delle domande in attesa per ufficio.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioInAttesaUfficio()}
	 * 
	 * @result Il test è superato se la lista delle domande restituite corrisponde a quelle salvate.
	 */
	@Test
	public void visualizzaDomandeTirocinioInAttesaUfficio() {
		List<DomandaTirocinio> domandeRestituite = new ArrayList<DomandaTirocinio>();
		utenzaService.setUtenteAutenticato(responsabile.getEmail());
		try {
			domandeRestituite = domandaTirocinioService.visualizzaDomandeTirocinioInAttesaUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		for (DomandaTirocinio domandaTirocinio : domandeRestituite)
			assertThat(listaDomande.contains(domandaTirocinio), is(true));
	}

	/**
	 * Testa il corretto funzionamento del metodo di visualizzazione delle domande valutate per ufficio.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaDomandeTirocinioValutateUfficio()}
	 * 
	 * @result Il test è superato se la lista delle domande restituite corrisponde a quelle salvate.
	 */
	@Test
	public void visualizzaDomandeTirocinioValutateUfficio() {
		List<DomandaTirocinio> domandeRestituite = new ArrayList<DomandaTirocinio>();
		utenzaService.setUtenteAutenticato(responsabile.getEmail());
		for (DomandaTirocinio domanda : listaDomande) {
			domanda.setStato(DomandaTirocinio.APPROVATA);
			domandeRepository.save(domanda);
		}
		try {
			domandeRestituite = domandaTirocinioService.visualizzaDomandeTirocinioValutateUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		for (DomandaTirocinio domandaTirocinio : domandeRestituite)
			assertThat(listaDomande.contains(domandaTirocinio), is(true));
	}

	/**
	 * Testa il corretto funzionamento del metodo di visualizzazione dei tirocini in corso per ufficio.
	 * 
	 * @test {@link DomandaTirocinioService#visualizzaTirociniInCorsoUfficio()}
	 * 
	 * @result Il test è superato se la lista delle domande restituite corrisponde a quelle salvate.
	 */
	@Test
	public void visualizzaTirociniInCorsoUfficio() {
		List<DomandaTirocinio> domandeRestituite = new ArrayList<DomandaTirocinio>();
		utenzaService.setUtenteAutenticato(responsabile.getEmail());
		for (DomandaTirocinio domanda : listaDomande) {
			domanda.setStato(DomandaTirocinio.APPROVATA);
			domanda.setDataInizio(LocalDate.now().minusMonths(1));
			domanda.setDataFine(LocalDate.now().plusMonths(1));
			domandeRepository.save(domanda);
		}
		try {
			domandeRestituite = domandaTirocinioService.visualizzaTirociniInCorsoUfficio();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		for (DomandaTirocinio domandaTirocinio : domandeRestituite)
			assertThat(listaDomande.contains(domandaTirocinio), is(true));
	}

}
