package it.unisa.di.is.gc1.ify.studente;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizione;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.convenzioni.AziendaRepository;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendaleRepository;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;

/**
 * Classe di test d'integrazione RichiestaIscrizioneRepository - DataBase
 * @pre si presuppone che il database sia vuoto prima dell'esecuzione della classe di test
 * @author Carmine Ferrara
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RichiestaIscrizioneRepositoryIT {

	@Autowired
	private StudenteRepository studenteRepository;

	@Autowired
	private RichiestaIscrizioneRepository richiestaIscrizioneRepository;
	
	@Autowired
	private DomandaTirocinioRepository domandeRepository;

	@Autowired
	private AziendaRepository aziendeRepository;
	
	@Autowired 
	private RichiestaConvenzionamentoRepository convenzionamentiRepository;
	
	@Autowired
	private ProgettoFormativoRepository progettiRepository;
	
	@Autowired
	private DelegatoAziendaleRepository delegatoRepository;
	
	@Autowired
	private RichiestaIscrizioneRepository iscrizioneRepository;
	

	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private ResponsabileUfficioTirociniRepository responsabileRepository;
	
	/*@Autowired
	private TestSupporter testSupporter;*/

	private List<Studente> listaStudenti;
	private List<RichiestaIscrizione> listaRichieste;

	/**
	 * Salva la lista di studenti e la lista di richieste su database prima
	 * dell'esecuzione di ogni singolo test.
	 */
	@Before
	public void salvaStudente() {
		//testSupporter.clearDB();
		
		domandeRepository.deleteAll();
		progettiRepository.deleteAll();
		convenzionamentiRepository.deleteAll();
		delegatoRepository.deleteAll();
		aziendeRepository.deleteAll();
		iscrizioneRepository.deleteAll();
		studenteRepository.deleteAll();
		responsabileRepository.deleteAll();
		utenteRepository.deleteAll();
		
		listaRichieste = new ArrayList<RichiestaIscrizione>();
		listaStudenti = new ArrayList<Studente>();

		// creo e salvo lo studente 1
		Studente studente = new Studente();
		studente.setNome("Mario");
		studente.setCognome("Rossi");
		studente.setDataNascita(LocalDate.of(1997, 12, 24));
		studente.setEmail("m.rossi@studenti.unisa.it");
		studente.setIndirizzo("Via Roma 4 84080 Salerno SA");
		studente.setMatricola("0512105144");
		studente.setTelefono("333-3544541");
		studente.setSesso("M");
		studente.setPassword("Password#1");

		studente = studenteRepository.save(studente);

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

		studente = studenteRepository.save(studente);

		// creo e salvo le richieste d'iscrizione

		RichiestaIscrizione r1 = new RichiestaIscrizione();
		r1.setStato("in Attesa");
		r1.setStudente(studente);

		RichiestaIscrizione r2 = new RichiestaIscrizione();
		r2.setStato("in Attesa");
		r2.setStudente(studente2);

		richiestaIscrizioneRepository.save(r1);
		richiestaIscrizioneRepository.save(r2);

		// inserisco gli elementi nelle liste
		listaRichieste.add(r1);
		listaRichieste.add(r2);

		listaStudenti.add(studente);
		listaStudenti.add(studente2);
	}
	
	/*@After
	public void Reset() {
		studenteRepository.delete(listaStudenti);
		richiestaIscrizioneRepository.delete(listaRichieste);
	}*/

	/**
	 * Testa l'interazione con il database per determinare se la selezione di tutte le richieste in 
	 * un particolare stato vengano prelevate.
	 * 
	 * @test
	 * 
	 * @result Il test è superato se la ricerca di tutte le richiste nella lista, 
	 * 		le quali riportano lo stato in attesa vengono selezionate dal database
	 * 		 in maniera corretta
	 */
	@Test
	public void findAllByStato() {
		List <RichiestaIscrizione> listRichiesteInAttesaOnDB  = richiestaIscrizioneRepository.findAllByStato("in Attesa");
		
		// Controlla che ogni studente della lista utilizzata per il test sia presente
		// su database
		// ricercandolo per matricola
		boolean richiestaEsistente = false;
		for (RichiestaIscrizione x: listRichiesteInAttesaOnDB) {
			for(RichiestaIscrizione y: listaRichieste) {
				if(y.getId() == x.getId()) richiestaEsistente = true;
			}
			assertThat(richiestaEsistente, is(true));
		}	
	}
	
	/**
	 * Testa l'interazione con il database per determinare se la selezione
	 * di una richiesta d'iscrizione tramite id avviene correttamente.
	 * 
	 * @test
	 * 
	 * @result Il test è superato se la richiesta con id stabilito
	 * è presente nel database
	 */
	@Test
	public void findByID() {
		RichiestaIscrizione tmp = richiestaIscrizioneRepository.findById(listaRichieste.get(0).getId()).orElse(null);
		
		//verifico che il valore di ritorno della richiesta selezionata non sia nulla
		assertNotNull(tmp);
	}
	
	
	
	/**
	 * Testa l'interazione con il database per determinare se la selezione
	 * di una richiesta d'iscrizione tramite chiave dello studemte avviene correttamente.
	 * 
	 * @test
	 * 
	 * @result Il test è superato se la richiesta con id stabilito
	 * è presente nel database
	 */
	@Test
	public void findByStudenteID() {
		RichiestaIscrizione tmp = richiestaIscrizioneRepository.findByStudenteId(listaStudenti.get(0).getId());
		
		//verifico che la prima richiesta della lista non sia vuota
		assertNotNull(tmp);
	}
}
