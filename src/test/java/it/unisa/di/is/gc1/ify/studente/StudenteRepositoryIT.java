package it.unisa.di.is.gc1.ify.studente;

import static org.hamcrest.CoreMatchers.equalTo;
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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

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

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test di integrazione fra la classe StudenteRepository e il Database. 
 * Metodologia: bottom-up.
 * @author Giacomo Izzo
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StudenteRepositoryIT {

	@Autowired
	private StudenteRepository studenteRepository;
	
	/*@Autowired
	private TestSupporter testSupporter;*/
	
	
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
	
	private List<Studente> listaStudenti;

	/**
	 * Salva la lista di studenti su database prima dell'esecuzione di ogni singolo
	 * test.
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
		
		listaStudenti = new ArrayList<Studente>();

		// Crea lo studente #1
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

		studenteRepository.save(studente1);
		listaStudenti.add(studente1);

		// Crea lo studente #2 Studente studente2 = new Studente();
		Studente studente2 = new Studente();
		studente2.setNome("Francesco");
		studente2.setCognome("Facchinetti");
		studente2.setDataNascita(LocalDate.of(1990, 12, 12));
		studente2.setEmail("francesco@facchinetti.com");
		studente2.setIndirizzo("Via francesco, 9");
		studente2.setMatricola("0512103434");
		studente2.setTelefono("3331234123");
		studente2.setSesso("M");
		studente2.setPassword("francescof1");

		studente2 = studenteRepository.save(studente2);
		listaStudenti.add(studente2);

		studenteRepository.flush();
	}

	/**
	 * Testa l'interazione con il database per determinare se la ricerca di uno
	 * studente tramite matricola avvenga correttamente.
	 * 
	 * @test {@link StudenteRepository#existsByMatricola(String)}
	 * 
	 * @result Il test e superato se la ricerca delle matricole deglis studenti
	 *         presenti nella lista utilizzata per il test ha successo
	 */
	@Test
	public void existsByMatricola() {
		// Controlla che ogni studente della lista utilizzata per il test sia esistente
		// nel database 
		for (Studente studente : listaStudenti) {
			boolean studenteEsistente = studenteRepository.existsByMatricola(studente.getMatricola());
			assertThat(studenteEsistente, is(true));
		}
	}

	/**
	 * Testa l'interazione con il database per il caricamento della lista di
	 * studenti tramite username.
	 * 
	 * @test
	 * 
	 * @result Il test Ã¨ superato l'entitÃ  coinvolta viene correttamente caricata
	 *         dal database
	 */
	@Test
	public void findByMatricola() {
		// Controlla che ogni studente inserito per il test sia presente su database
		// ricercandolo per matricola
		for (Studente studente : listaStudenti) {
			Studente studenteSalvato = studenteRepository.findByMatricola(studente.getMatricola());
			assertThat(studente, is(equalTo(studenteSalvato)));
		}
	}

}
