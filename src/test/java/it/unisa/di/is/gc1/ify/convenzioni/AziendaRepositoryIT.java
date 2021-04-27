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
 * Test di integrazione fra la classe AziendaRepository e il Database. 
 * Metodologia: bottom-up.
 * @author Geremia Cavezza, Benedetta Coccaro, Giacomo Izzo
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class AziendaRepositoryIT {
	
	@Autowired
	private AziendaRepository aziendaRepository;
	
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
	
	private Azienda azienda;
	
	/**
	 * Salva azienda su database prima dell'esecuzione di ogni singolo
	 * test.
	 */
	@Before
	public void salvaAziendaDelegato() {
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
		azienda.setpIva("12345678910");
		azienda.setRagioneSociale("NetData Società per azioni");
		azienda.setDescrizione("Consulenza e Data Analytics");
		azienda.setSettore("Informatica");
		azienda.setSede("Via Roma, 39 Milano MI - Italia");
		
		azienda = aziendaRepository.save(azienda);
	}
	
	@After
	public void Reset() {
		aziendaRepository.delete(azienda);
	}
	
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di un'azienda
	 *  tramite pIva avvenga correttamente.
	 * 
	 * @test {@link AziendaRepository#findByPIva(String)}
	 * 
	 * @result Il test è superato se la ricerca per pIva dell'azienda
	 *         presente per il test ha successo
	 */
	@Test
	public void findByPIva() {
		// Controlla che l'azienda inserita come test sia esistente
		// nel database 
		
		Azienda a;
		
		a = aziendaRepository.findByPIva(azienda.getpIva());
		assertThat(azienda, is(equalTo(a)));
	}
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una
	 * azienda tramite pIva avvenga correttamente.
	 * 
	 * @test {@link AziendaRepository#existsByPIva(String)}
	 * 
	 * @result Il test è superato se la ricerca per pIva dell'azienda
	 *         presente nel test ha successo
	 */
	@Test
	public void existsByPIva() {
		// Controlla che l'azienda inserita per il test sia esistente
		// nel database 
		
		boolean aziendaEsistente = aziendaRepository.existsByPIva(azienda.getpIva());
		assertThat(aziendaEsistente, is(true));
	}

}
