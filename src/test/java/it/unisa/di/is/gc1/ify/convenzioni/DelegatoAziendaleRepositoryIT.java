package it.unisa.di.is.gc1.ify.convenzioni;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;


/**
 * Test di integrazione fra la classe DelegatoAziendaleRepository e il Database. 
 * Metodologia: bottom-up.
 * @author Geremia Cavezza, Benedetta Coccaro
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DelegatoAziendaleRepositoryIT {
	
	@Autowired
	private DelegatoAziendaleRepository delegatoAziendaleRepository;
	
	
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
	private StudenteRepository studenteRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private ResponsabileUfficioTirociniRepository responsabileRepository;
	/*@Autowired
	private TestSupporter testSupporter;*/
	
	
	private Azienda azienda;
	private DelegatoAziendale delegatoAziendale;
	
	
	/**
	 * Salva azienda e delegato aziendale su database prima dell'esecuzione di ogni singolo
	 * test.
	 */
	@Before
	public void salvaAziendaDelegato() {
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
		
		
		azienda = new Azienda();
		azienda.setPartitaIva("12345678911");
		azienda.setRagioneSociale("NetData Società per azioni");
		azienda.setDescrizione("Consulenza e Data Analytics");
		azienda.setSettore("Informatica");
		azienda.setSede("Via Roma, 39 Milano MI - Italia");
		
		delegatoAziendale = new DelegatoAziendale();
		delegatoAziendale.setNome("Mario");
		delegatoAziendale.setCognome("Rossi");
		delegatoAziendale.setRuolo("Direttore");
		delegatoAziendale.setSesso("M");
		delegatoAziendale.setEmail("m.rossi2@gmail.com");
		delegatoAziendale.setIndirizzo("Via san paolo, 20 Napoli NA - Italia");
		delegatoAziendale.setPassword("20Mario?");
		delegatoAziendale.setAzienda(azienda);
		
		delegatoAziendale = delegatoAziendaleRepository.save(delegatoAziendale);

	}
	
	@After
	public void Reset() {
		delegatoAziendaleRepository.delete(delegatoAziendale);
	}
	
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di un delegato
	 *  tramite pIva avvenga correttamente.
	 * 
	 * @test {@link DelegatoAziendaleRepository#findByAziendaPIva(String)}
	 * 
	 * @result Il test è superato se la ricerca per pIva del delegato aziendale
	 *         presente per il test ha successo
	 */
	@Test
	public void findByAziendaPIva() {
		// Controlla che il delegato inserito come test sia esistente
		// nel database 
		
		DelegatoAziendale d;
		
		d = delegatoAziendaleRepository.findByAziendaPartitaIva(delegatoAziendale.getAzienda().getPartitaIva());
		assertThat(delegatoAziendale, is(equalTo(d)));
	}
	
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di un
	 *  delegato  tramite pIva avvenga correttamente.
	 * 
	 * @test {@link DelegatoAziendaleRepository#existsByAziendaPartitaIva(String)}
	 * 
	 * @result Il test è superato se la ricerca per pIva del delegato
	 *         presente nel test ha successo
	 */
	@Test
	public void existsByAziendaPIva() {
		// Controlla che il delegato inserito per il test sia esistente
		// nel database 
		
		boolean delegatoEsistente = delegatoAziendaleRepository.existsByAziendaPartitaIva(delegatoAziendale.getAzienda().getPartitaIva());
		assertThat(delegatoEsistente, is(true));
	}

	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di un delegato
	 *  tramite email avvenga correttamente.
	 * 
	 * @test {@link DelegatoAziendaleRepository#findByEmail(String)}
	 * 
	 * @result Il test è superato se la ricerca per email del delegato aziendale
	 *         presente per il test ha successo
	 */
	@Test
	public void findByEmail() {
		// Controlla che il delegato inserito come test sia esistente
		// nel database 
		
		DelegatoAziendale d;
		
		d = delegatoAziendaleRepository.findByEmail(delegatoAziendale.getEmail());
		assertThat(delegatoAziendale, is(equalTo(d)));
	}

	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di un
	 *  delegato  tramite email avvenga correttamente.
	 * 
	 * @test {@link DelegatoAziendaleRepository#existsByEmail(String)}
	 * 
	 * @result Il test è superato se la ricerca per email del delegato
	 *         presente nel test ha successo
	 */
	@Test
	public void existsByEmail() {
		// Controlla che il delegato inserito per il test sia esistente
		// nel database 
		
		boolean delegatoEsistente = delegatoAziendaleRepository.existsByEmail(delegatoAziendale.getEmail());
		assertThat(delegatoEsistente, is(true));
	}
	
}
