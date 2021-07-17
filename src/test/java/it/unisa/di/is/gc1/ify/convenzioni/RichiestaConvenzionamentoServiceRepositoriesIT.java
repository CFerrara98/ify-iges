package it.unisa.di.is.gc1.ify.convenzioni;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;

/**
 * Classe di test d'integrazione RichiestaConvenzionamentoRepository - (RichiestaConvenzionamentoRepository
 * - DelegatoAziendaleRepository - ResponsabileUfficioTirociniRepository 
 * @author Giacomo Izzo Simone Civale Benedetta Coccaro
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class RichiestaConvenzionamentoServiceRepositoriesIT {
	
	@Autowired
	private DelegatoAziendaleRepository delegatoAziendaleRepository;
	
	@Autowired
	private RichiestaConvenzionamentoRepository richiestaConvenzionamentoRepository;
	
	@Autowired
	private UtenzaService utenzaService;
	
	@Autowired
	private RichiestaConvenzionamentoService richiestaConvenzionamentoService;
	
	@Autowired
	private ResponsabileUfficioTirociniRepository responsabileUfficioTirociniRepository;
	
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
	
	
	private Azienda azienda1;
	
	private Azienda azienda2;
	
	private DelegatoAziendale delegatoAziendale1;
	
	private DelegatoAziendale delegatoAziendale2;
	
	private RichiestaConvenzionamento richiestaConvenzionameto1;
	
	private RichiestaConvenzionamento richiestaConvenzionameto2;
	
	private ResponsabileUfficioTirocini responsabileUfficioTirocini;
	
	private List<Azienda> listaAziende;
	
	private List<RichiestaConvenzionamento> listaRichieste;
	
	/**
	 * Salva il delegato aziendale e le richieste di convenzionamento prima dell'esecuzione 
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
		
		azienda1=new Azienda();
		azienda1.setPartitaIva("01234567895");
		
		delegatoAziendale1=new DelegatoAziendale();
		delegatoAziendale1.setEmail("p.bianchi2@azienda.it");
		delegatoAziendale1.setAzienda(azienda1);
		
		richiestaConvenzionameto1=new RichiestaConvenzionamento();
		richiestaConvenzionameto1.setStato(RichiestaConvenzionamento.IN_ATTESA);
		richiestaConvenzionameto1.setAzienda(azienda1);
		richiestaConvenzionameto1.setDelegatoAziendale(delegatoAziendale1);
		
		responsabileUfficioTirocini=new ResponsabileUfficioTirocini();
		responsabileUfficioTirocini.setEmail("m.rossi10@unisa.it");
		responsabileUfficioTirociniRepository.save(responsabileUfficioTirocini);
		
		azienda2=new Azienda();
		azienda2.setPartitaIva("01234567897");
		
		delegatoAziendale2=new DelegatoAziendale();
		delegatoAziendale2.setEmail("r.rossi2@azienda2.it");
		
		richiestaConvenzionameto2=new RichiestaConvenzionamento();
		richiestaConvenzionameto2.setStato(RichiestaConvenzionamento.IN_ATTESA);
		richiestaConvenzionameto2.setAzienda(azienda2);
		richiestaConvenzionameto2.setDelegatoAziendale(delegatoAziendale2);
		
		listaAziende=new ArrayList<Azienda>();
		listaRichieste=new ArrayList<RichiestaConvenzionamento>();
		delegatoAziendaleRepository.save(delegatoAziendale1);
		delegatoAziendaleRepository.save(delegatoAziendale2);
		richiestaConvenzionamentoRepository.save(richiestaConvenzionameto1);
		richiestaConvenzionamentoRepository.save(richiestaConvenzionameto2);
		listaAziende.add(azienda1);
		listaAziende.add(azienda2);
		listaRichieste.add(richiestaConvenzionameto1);
		listaRichieste.add(richiestaConvenzionameto2);
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di salvataggio di una richiesta di convenzionamento.
	 * 
	 * @test {@link RichiestaConvenzionamentoService#salvaRichiestaConvenzionamento(RichiestaConvenzionamento)}
	 * 
	 * @result Il test è superato se la richiesta di convenzionamento viene correttamente
	 * salvata nel database.
	 */
	
	@Test
	public void salvaRichiestaConvenzionamento() {
		RichiestaConvenzionamento richiestaSalvata=richiestaConvenzionamentoService.salvaRichiestaConvenzionamento(richiestaConvenzionameto1);
		assertThat(richiestaConvenzionameto1, is(equalTo(richiestaSalvata)));
	}
	
	/**
	 * Testa il corretto funzionamento del metodo getAziendaFromPIva(String pIva).
	 * 
	 * @test {@link RichiestaConvenzionamentoService#getAziendaFromPIva(String)}
	 * 
	 * @result Il test è superato se l'oggetto restituito dal metodo è lo stesso di
	 * quello che si usa per il test.
	 */
	
	@Test
	public void getAziendaFromPIva() {
		Azienda aziendaSalvata=richiestaConvenzionamentoService.getAziendaFromPIva(azienda1.getPartitaIva());
		assertThat(azienda1, is(equalTo(aziendaSalvata)));
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di accettazione di una richiesta di convenzionamento.
	 * 
	 * @test {@link RichiestaConvenzionamentoService#accettaRichiestaConvenzionamento(Long)}
	 * 
	 * @result Il test è superato se la richiesta di convenzionamento viene correttamente
	 * accettata.
	 */
	
	@Test
	public void accettaRichiestaConvenzionamento() {
		utenzaService.setUtenteAutenticato(responsabileUfficioTirocini.getEmail());
		try {
			richiestaConvenzionamentoService.accettaRichiestaConvenzionamento(richiestaConvenzionameto1.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertEquals(RichiestaConvenzionamento.ACCETTATA, richiestaConvenzionameto1.getStato());
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di rifiuto di una richiesta di convenzionamento.
	 * 
	 * @test {@link RichiestaConvenzionamentoService#rifiutaRichiestaConvenzionamento(Long)}
	 * 
	 * @result Il test è superato se la richiesta di convenzionamento viene correttamente
	 * rifiutata.
	 */
	
	@Test
	public void rifiutaRichiestaConvenzionamento() {
		utenzaService.setUtenteAutenticato(responsabileUfficioTirocini.getEmail());
		try {
			richiestaConvenzionamentoService.rifiutaRichiestaConvenzionamento(richiestaConvenzionameto1.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertEquals(RichiestaConvenzionamento.RIFIUTATA, richiestaConvenzionameto1.getStato());
	}
	
	/**
	 * Testa il corretto funzionamento del metodo getStatoRichiestaByEmail(String email).
	 * 
	 * @test {@link RichiestaConvenzionamentoService#getStatoRichiestaByEmail(String)}
	 * 
	 * @result Il test è superato se l'oggetto restituito dal metodo è lo stesso di
	 * quello che si usa per il test.
	 */
	
	@Test
	public void getStatoRichiestaByEmail() {
		delegatoAziendaleRepository.save(delegatoAziendale1);
		String statoRestituito=richiestaConvenzionamentoService.getStatoRichiestaByEmail(delegatoAziendale1.getEmail());
		assertEquals(richiestaConvenzionameto1.getStato(), statoRestituito);
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di visualizzazione delle azienda convenzionate
	 * 
	 * @test {@link RichiestaConvenzionamentoService#visualizzaAziendeConvenzionate()}
	 * 
	 * @result Il test è superato se le aziende convenzionate vengono correttamente
	 * visualizzate.
	 */
	
	@Test
	public void visualizzaAziendaConvenzionate() {
		List<Azienda> aziendeRestituite=richiestaConvenzionamentoService.visualizzaAziendeConvenzionate();
		for(Azienda aziendaSalvata:aziendeRestituite) {
			assertThat(listaAziende.contains(aziendaSalvata), is(true));
		}
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di visualizzazione dei dettagli delle 
	 * richieste di convenzionamento.
	 * 
	 * @test {@link RichiestaConvenzionamentoService#visualizzaRichiesteConvenzionamentoDettagli()}
	 * 
	 * @result Il test è superato se i dettagli delle richieste di convenzionamento vengono 
	 * correttamente visualizzate.
	 */
	
	@Test
	public void visualizzaRichiestaConvenzionamentoDettagli() {
		utenzaService.setUtenteAutenticato(responsabileUfficioTirocini.getEmail());
		List<RichiestaConvenzionamento> richiesteRestituite=new ArrayList<RichiestaConvenzionamento>();
		try {
			richiesteRestituite = richiestaConvenzionamentoService.visualizzaRichiesteConvenzionamentoDettagli();
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		for(RichiestaConvenzionamento richiestaSalvata:richiesteRestituite) {
			assertThat(listaRichieste.contains(richiestaSalvata), is(true));
		}
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di validazione dell'email.
	 * 
	 * @test {@link RichiestaConvenzionamentoService#validaEmail(String)}
	 * 
	 * @result Il test è superato se il metodo invocato restituisce la mail inserita.
	 */
	
	@Test
	public void validaEmail() {
		String emailInserita="m.rossi2@unisa.it";
		String emailRestituita="";
		try {
			emailRestituita=richiestaConvenzionamentoService.validaEmail(emailInserita);
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			e.printStackTrace();
		}
		assertEquals(emailInserita, emailRestituita);
	}
}