package it.unisa.di.is.gc1.ify.domandaTirocinio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.convenzioni.Azienda;
import it.unisa.di.is.gc1.ify.convenzioni.AziendaRepository;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendaleRepository;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativo;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;

/**
 * Test di integrazione fra la classe DomandaTirocinioRepository e il Database. 
 * Metodologia: bottom-up.
 * @author Giacomo Izzo , Alessia Natale, Roberto Calabrese.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DomandaTirocinioRepositoryIT {

	@Autowired
	private DomandaTirocinioRepository domandeRepository;

	@Autowired
	private StudenteRepository studentiRepository;

	@Autowired
	private AziendaRepository aziendeRepository;
	
	@Autowired
	private ProgettoFormativoRepository progettiRepository;
	

	
	@Autowired 
	private RichiestaConvenzionamentoRepository convenzionamentiRepository;
	
	
	@Autowired
	private DelegatoAziendaleRepository delegatoRepository;
	
	@Autowired
	private RichiestaIscrizioneRepository iscrizioneRepository;
	
	@Autowired
	private StudenteRepository studenteRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private ResponsabileUfficioTirociniRepository responsabileRepository;

	private List<ProgettoFormativo> listaProgettiFormativi;

	private List<Azienda> listaAziende;

	private List<Studente> listaStudenti;

	private List<DomandaTirocinio> listaDomande;

	private List<String> listaStati;

	/**
	 * Salva la lista delle domande di tirocinio sul database prima dell'esecuzione di ogni
	 * singolo test.
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

		listaStati.add(DomandaTirocinio.IN_ATTESA);
		listaStati.add(DomandaTirocinio.ACCETTATA);
		listaStati.add(DomandaTirocinio.RIFIUTATA);
		listaStati.add(DomandaTirocinio.APPROVATA);
		listaStati.add(DomandaTirocinio.RESPINTA);

		// Crea il progetto formativo #1
		ProgettoFormativo progettoFormativo1 = new ProgettoFormativo();
		// Crea l'azienda #1
		Azienda azienda1 = new Azienda();
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
		Studente studente1 = new Studente();
		studente1.setNome("Mario");
		studente1.setCognome("Rossi");
		studente1.setDataNascita(LocalDate.of(1997, 12, 24));
		studente1.setEmail("m.rossi@studenti.unisa.it");
		studente1.setIndirizzo("Via Roma 4 84080 Salerno SA");
		studente1.setMatricola("0512105144");
		studente1.setTelefono("333-3544541");
		studente1.setSesso("M");
		studente1.setPassword("Password#1");

		DomandaTirocinio domanda1 = new DomandaTirocinio();
		domanda1.setAzienda(azienda1);
		domanda1.setProgettoFormativo(progettoFormativo1);
		domanda1.setStudente(studente1);
		domanda1.setConoscenze("linguaggio c");
		domanda1.setMotivazioni("crescita personale");
		domanda1.setDataInizio(LocalDate.of(2019, 11, 10));
		domanda1.setDataFine(LocalDate.of(2020, 03, 10));
		domanda1.setCfu(6);
		domanda1.setStato(DomandaTirocinio.IN_ATTESA);

		studentiRepository.save(studente1);
		aziendeRepository.save(azienda1);
		progettiRepository.save(progettoFormativo1);
		domandeRepository.save(domanda1);
		listaProgettiFormativi.add(progettoFormativo1);
		listaAziende.add(azienda1);
		listaStudenti.add(studente1);
		listaDomande.add(domanda1);

		// Crea il progetto formativo #2
		ProgettoFormativo progettoFormativo2 = new ProgettoFormativo();
		// Crea l'azienda #2
		Azienda azienda2 = new Azienda();
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
		Studente studente2 = new Studente();
		studente2.setNome("Matteo");
		studente2.setCognome("Verdi");
		studente2.setDataNascita(LocalDate.of(1998, 04, 12));
		studente2.setEmail("m.verdi@studenti.unisa.it");
		studente2.setIndirizzo("Via Padova 4 84080 Salerno SA");
		studente2.setMatricola("0512104051");
		studente2.setTelefono("333-3241540");
		studente2.setSesso("M");
		studente2.setPassword("Password#2");

		DomandaTirocinio domanda2 = new DomandaTirocinio();
		domanda2.setAzienda(azienda2);
		domanda2.setProgettoFormativo(progettoFormativo2);
		domanda2.setStudente(studente2);
		domanda2.setConoscenze("linguaggio java");
		domanda2.setMotivazioni("introduzione al mondo lavorativo");
		domanda2.setDataInizio(LocalDate.of(2019, 9, 10));
		domanda2.setDataFine(LocalDate.of(2020, 01, 10));
		domanda2.setCfu(6);
		domanda2.setStato(DomandaTirocinio.IN_ATTESA);

		studentiRepository.save(studente2);
		aziendeRepository.save(azienda2);
		progettiRepository.save(progettoFormativo2);
		domandeRepository.save(domanda2);
		listaProgettiFormativi.add(progettoFormativo2);
		listaAziende.add(azienda2);
		listaStudenti.add(studente2);
		listaDomande.add(domanda2);
	}

	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una
	 * domanda di tirocinio tramite l'id dello studente associato avvenga correttamente.
	 * 
	 * @test {@link DomandaTirocinioRepository#findAllByStudenteId(Long)}
	 * 
	 * @result Il test è superato se le domande di tirocinio restituite dal metodo tramite 
	 * 		   l'id dello studente associato sono presenti nella lista utilizzata per il test.
	 */
	@Test
	public void findAllByStudenteId() {
		for (Studente studente : listaStudenti) {
			List<DomandaTirocinio> domandeRestituite = domandeRepository.findAllByStudenteId(studente.getId());
			for (DomandaTirocinio domandaRestituita : domandeRestituite)
				assertThat(listaDomande.contains(domandaRestituita), is(true));
		}
	}

	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una
	 * domanda di tirocinio tramite la partita iva dell'azienda associata avvenga correttamente.
	 * 
	 * @test {@link DomandaTirocinioRepository#findAllByAziendaPIva(String)}
	 * 
	 * @result Il test è superato se le domande di tirocinio restituite dal metodo tramite 
	 * 		   la partita iva dell'azienda associata sono presenti nella lista utilizzata per il test.
	 */
	@Test
	public void findAllByAziendaPIva() {
		for (DomandaTirocinio domanda : listaDomande) {
			List<DomandaTirocinio> domandeRestituite = domandeRepository.findAllByAziendaPIva(domanda.getAzienda().getpIva());
			for (DomandaTirocinio domandaRestituita : domandeRestituite)
				assertThat(listaDomande.contains(domandaRestituita), is(true));
		}
	}

	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una
	 * domanda di tirocinio tramite l'id dello studente associato e lo stato avvenga correttamente.
	 * 
	 * @test {@link DomandaTirocinioRepository#findAllByStudenteIdAndStato(Long, String)}
	 * 
	 * @result Il test è superato se le domande di tirocinio restituite dal metodo tramite l'id dello studente associato
	 * 		   e lo stato sono presenti nella lista utilizzata per il test.
	 */
	@Test
	public void findAllByStudenteIdAndStato() {
		for(Studente studente : listaStudenti ) {
			for(String stato : listaStati) {
				List<DomandaTirocinio> domandeRestituite = domandeRepository.findAllByStudenteIdAndStato(studente.getId(), stato);
				for(DomandaTirocinio domandaRestituita : domandeRestituite)
				assertThat(listaDomande.contains(domandaRestituita), is(true));
			}
		}
	}

	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una
	 * domanda di tirocinio tramite la partita iva dell'azienda associata e lo stato avvenga correttamente.
	 * 
	 * @test {@link DomandaTirocinioRepository#findAllByAziendaPIvaAndStato(String, String)}
	 * 
	 * @result Il test è superato se le domande di tirocinio restituite dal metodo tramite la partita iva dell'azienda associata
	 * 		   e lo stato sono presenti nella lista utilizzata per il test.
	 */
	@Test
	public void findAllByAziendaPIvaAndStato() {
		for (DomandaTirocinio domanda : listaDomande) {
			List<DomandaTirocinio> domandeRestituite = domandeRepository.findAllByAziendaPIvaAndStato(domanda.getAzienda().getpIva(), domanda.getStato());
			for (DomandaTirocinio domandaRestituita : domandeRestituite)
				assertThat(listaDomande.contains(domandaRestituita), is(true));
		}
	}

	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una
	 * domanda di tirocinio tramite lo stato avvenga correttamente.
	 * 
	 * @test {@link DomandaTirocinioRepository#findAllByStato(String)}
	 * 
	 * @result Il test è superato se le domande di tirocinio restituite dal metodo tramite 
	 * 		   lo stato sono presenti nella lista utilizzata per il test.
	 */
	@Test
	public void findAllByStato() {
		for (String stato : listaStati) {
			List<DomandaTirocinio> domandeRestituite = domandeRepository.findAllByStato(stato);
			for (DomandaTirocinio domandaRestituita : domandeRestituite) {
				System.out.println("stato domanda restituita: " + domandaRestituita.getStato() + " Stato atteso: " + stato + " Conoscenze domanda: " + domandaRestituita.getConoscenze());
				assertThat(listaDomande.contains(domandaRestituita), is(true));
			}
		}
	}

	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una
	 * domanda di tirocinio tramite Id avvenga correttamente.
	 * 
	 * @test {@link DomandaTirocinioRepository}
	 * 
	 * @result Il test è superato se la ricerca dell'id delle domande di tirocinio
	 * 		   presenti nella lista utilizzata per il test ha successo.
	 */
	@Test
	public void findById() {
		for (DomandaTirocinio domanda : listaDomande) {
			DomandaTirocinio domandaRestituita = domandeRepository.findById(domanda.getId()).orElse(null);
			assertThat(listaDomande.contains(domandaRestituita), is(true));
		}
	}
}
