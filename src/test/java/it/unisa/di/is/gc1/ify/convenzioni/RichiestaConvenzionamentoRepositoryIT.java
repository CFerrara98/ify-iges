package it.unisa.di.is.gc1.ify.convenzioni;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;


/**
 * Test di integrazione fra la classe RichiestaConvenzionamentoRepository e il Database. 
 * Metodologia: bottom-up.
 * @author Geremia Cavezza, Benedetta Coccaro
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RichiestaConvenzionamentoRepositoryIT {
	
	@Autowired
	private DelegatoAziendaleRepository delegatoAziendaleRepository;
	
	@Autowired
	private RichiestaConvenzionamentoRepository richiestaConvenzionamentoRepository;
	
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
	
	private Azienda azienda, azienda2;
	private DelegatoAziendale delegatoAziendale, delegatoAziendale2;
	private RichiestaConvenzionamento richiestaConvenzionamento, richiestaConvenzionamento2;
	private List<RichiestaConvenzionamento> listaRichieste;
	/**
	 * Salva azienda e delegato aziendale su database prima dell'esecuzione di ogni singolo
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
		azienda.setPartitaIva("12345678911");
		azienda.setRagioneSociale("NetData Società per azioni");
		azienda.setDescrizione("Consulenza e Data Analytics");
		azienda.setSettore("Informatica");
		azienda.setSede("Via Roma, 39 Milano MI - Italia");
		
		azienda2 = new Azienda();
		azienda2.setPartitaIva("12345678910");
		azienda2.setRagioneSociale("NetData Società per azioni");
		azienda2.setDescrizione("Consulenza e Data Analytics");
		azienda2.setSettore("Informatica");
		azienda2.setSede("Via Roma, 39 Milano MI - Italia");
		
		delegatoAziendale = new DelegatoAziendale();
		delegatoAziendale.setNome("Mario");
		delegatoAziendale.setCognome("Rossi");
		delegatoAziendale.setRuolo("Direttore");
		delegatoAziendale.setSesso("M");
		delegatoAziendale.setEmail("m.rossi2@gmail.com");
		delegatoAziendale.setIndirizzo("Via san paolo, 20 Napoli NA - Italia");
		delegatoAziendale.setPassword("20Mario?");
		delegatoAziendale.setAzienda(azienda);
		
		delegatoAziendale2 = new DelegatoAziendale();
		delegatoAziendale2.setNome("Mario");
		delegatoAziendale2.setCognome("Rossi");
		delegatoAziendale2.setRuolo("Direttore");
		delegatoAziendale2.setSesso("M");
		delegatoAziendale2.setEmail("m.rossi3@gmail.com");
		delegatoAziendale2.setIndirizzo("Via san paolo, 20 Napoli NA - Italia");
		delegatoAziendale2.setPassword("20Mario?");
		delegatoAziendale2.setAzienda(azienda2);
		
		delegatoAziendale = delegatoAziendaleRepository.save(delegatoAziendale);
		delegatoAziendale2 = delegatoAziendaleRepository.save(delegatoAziendale2);
		
		richiestaConvenzionamento = new RichiestaConvenzionamento();
		richiestaConvenzionamento.setStato(RichiestaConvenzionamento.IN_ATTESA);
		richiestaConvenzionamento.setAzienda(azienda);
		richiestaConvenzionamento.setDelegatoAziendale(delegatoAziendale);
		
		richiestaConvenzionamento2 = new RichiestaConvenzionamento();
		richiestaConvenzionamento2.setStato(RichiestaConvenzionamento.IN_ATTESA);
		richiestaConvenzionamento2.setAzienda(azienda2);
		richiestaConvenzionamento2.setDelegatoAziendale(delegatoAziendale2);
		
		richiestaConvenzionamentoRepository.save(richiestaConvenzionamento);
		richiestaConvenzionamentoRepository.save(richiestaConvenzionamento2);
		
		listaRichieste = new ArrayList<RichiestaConvenzionamento>();
		listaRichieste.add(richiestaConvenzionamento);
		listaRichieste.add(richiestaConvenzionamento2);
	}
	
	@After
	public void Reset() {
		delegatoAziendaleRepository.delete(delegatoAziendale);
		richiestaConvenzionamentoRepository.delete(richiestaConvenzionamento);
	}
	
	
	/**
	 * Testa l'interazione con il database per determinare se la selezione di tutte le richieste in 
	 * un particolare stato vengano prelevate.
	 * 
	 * @test {@link RichiestaConvenzionamentoRepository#findAllByStato(String)}
	 * 
	 * @result Il test è superato se la ricerca di tutte le richiste nella lista, 
	 * 		le quali riportano lo stato in attesa vengono selezionate dal database
	 * 		 in maniera corretta
	 */
	@Test
	public void findAllByStato() {
		List <RichiestaConvenzionamento> listRichiesteInAttesaOnDB  = richiestaConvenzionamentoRepository.findAllByStato(RichiestaConvenzionamento.IN_ATTESA);
		
		// Controlla che ogni richiesta di convenzionamento della lista utilizzata per il test sia presente
		// su database
		// ricercandole per stato
		boolean richiestaEsistente = false;
		for (RichiestaConvenzionamento x: listRichiesteInAttesaOnDB) {
			for(RichiestaConvenzionamento y: listaRichieste) {
				if(y.getId() == x.getId()) richiestaEsistente = true;
			}
			assertThat(richiestaEsistente, is(true));
		}	
	}
	
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una richiesta di
	 *  convenzionamento tramite pIva avvenga correttamente.
	 * 
	 * @test {@link RichiestaConvenzionamentoRepository#findByAziendaPIva(String)}
	 * 
	 * @result Il test è superato se la ricerca per pIva della richiesta
	 *         presente nel test ha successo
	 */
	@Test
	public void findByAziendaPIva() {
		// Controlla che la richiesta inserita per il test sia esistente
		// nel database 
		
		RichiestaConvenzionamento r;
		
		r = richiestaConvenzionamentoRepository.findByAziendaPartitaIva(richiestaConvenzionamento.getAzienda().getPartitaIva());
		assertThat(richiestaConvenzionamento, is(equalTo(r)));
	}
	
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una richiesta di
	 *  convenzionamento tramite id avvenga correttamente.
	 * 
	 * @test
	 * 
	 * @result Il test è superato se la ricerca per id della richiesta
	 *         presente nel test ha successo
	 */
	@Test
	public void findById() {
		// Controlla che la richiesta inserita per il test sia esistente
		// nel database 
		
		RichiestaConvenzionamento r;
		
		r = richiestaConvenzionamentoRepository.findById(richiestaConvenzionamento.getId()).orElse(null);
		assertThat(richiestaConvenzionamento, is(equalTo(r)));
	}
	
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di una richiesta di
	 *  convenzionamento tramite email delegato avvenga correttamente.
	 * 
	 * @test {@link RichiestaConvenzionamentoRepository#findByDelegatoAziendaleEmail(String)}
	 * 
	 * @result Il test è superato se la ricerca per email delegato della richiesta
	 *         presente nel test ha successo
	 */
	@Test
	public void findByDelegatoAziendaleEmail() {
		// Controlla che la richiesta inserita per il test sia esistente
		// nel database 
		
		RichiestaConvenzionamento r;
		
		r = richiestaConvenzionamentoRepository.findByDelegatoAziendaleEmail(richiestaConvenzionamento.getDelegatoAziendale().getEmail());
		assertThat(richiestaConvenzionamento, is(equalTo(r)));
	}
}
