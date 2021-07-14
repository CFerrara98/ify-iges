package it.unisa.di.is.gc1.ify.progettoFormativo;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.convenzioni.Azienda;
import it.unisa.di.is.gc1.ify.convenzioni.AziendaRepository;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendale;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendaleRepository;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;

/**
 * Classe di test d'integrazione ProgettoFormativoRepository - (ProgettoFormativoRepository - azienda repository 
 * delegatoAziendaleRepository 
 * @author Simone Civale , Giacomo Izzo , Geremia Cavezza
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class ProgettoFormativoServiceRepositoriesIT {
	
	@Autowired
	private ProgettoFormativoService progettoFormativoService;
	
	@Autowired
	private UtenzaService utenzaService;
	
	@Autowired
	private ProgettoFormativoRepository progettoFormativoRepository;
	
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
	
	private ProgettoFormativo progettoFormativo1;
	
	private ProgettoFormativo progettoFormativo2;
	
	private Azienda azienda1;
	
	private Azienda azienda2;
	
	private DelegatoAziendale delegatoAziendale;
	
	private DelegatoAziendale delegatoAziendale2;
	
	private List<Azienda> listaAziende = new ArrayList<Azienda>();
	private List<ProgettoFormativo> listaProgetti = new ArrayList<ProgettoFormativo>();
	
	/**
	 * Salva il progetto formativo, il delegato aziendale, l'azienda e le liste di aziende e progetti
	 * prima dell'esecuzione di ogni singolo test.
	 */
	
	@Before
	public void salvaProgetto() {
		domandeRepository.deleteAll();
		progettiRepository.deleteAll();
		convenzionamentiRepository.deleteAll();
		delegatoRepository.deleteAll();
		aziendeRepository.deleteAll();
		iscrizioneRepository.deleteAll();
		studenteRepository.deleteAll();
		responsabileRepository.deleteAll();
		utenteRepository.deleteAll();
		
		progettoFormativo1=new ProgettoFormativo();
		azienda1=new Azienda();
		delegatoAziendale=new DelegatoAziendale();
		azienda1.setDescrizione("azienda di software informatici");
		azienda1.setPartitaIva("01234567899");
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
		delegatoAziendale.setEmail("m.rossi10@azienda.it");
		delegatoAziendale.setAzienda(azienda1);
		
		delegatoAziendaleRepository.save(delegatoAziendale);
		progettoFormativoRepository.save(progettoFormativo1);
		listaAziende.add(azienda1);
		listaProgetti.add(progettoFormativo1);
		
		
		progettoFormativo2=new ProgettoFormativo();
		azienda2=new Azienda();
		delegatoAziendale2=new DelegatoAziendale();
		delegatoAziendale2.setEmail("m.rossi11@azienda.it");
		delegatoAziendale2.setAzienda(azienda2);
		
		azienda2.setDescrizione("azienda2");
		azienda2.setPartitaIva("01234567894");
		azienda2.setRagioneSociale("azienda 2");
		azienda2.setSede("Milano");
		azienda2.setSettore("Informatica");
		
		progettoFormativo2.setAmbito("AI");
		progettoFormativo2.setAttivita("ricerca");
		progettoFormativo2.setAzienda(azienda2);
		progettoFormativo2.setConoscenze("python e c");
		progettoFormativo2.setData_compilazione(LocalDate.of(2019, 10, 06));
		progettoFormativo2.setMax_partecipanti(3);
		progettoFormativo2.setNome("Progetto 2");
		progettoFormativo2.setStato(ProgettoFormativo.ATTIVO);
		
		delegatoAziendaleRepository.save(delegatoAziendale2);
		progettoFormativoRepository.save(progettoFormativo2);
		
		listaAziende.add(azienda2);
		listaProgetti.add(progettoFormativo2);
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di archiviazione di un progetto formativo.
	 * 
	 * @test {@link ProgettoFormativoService#archiviaProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il progetto formativo viene correttamente
	 * archiviato nel database
	 */
	
	@Test
	public void archiviaProgettoFormativo() {
		ProgettoFormativo progettoFormativoSalvato= new ProgettoFormativo();
		utenzaService.setUtenteAutenticato(delegatoAziendale.getEmail());
		try {
			progettoFormativoSalvato=progettoFormativoService.archiviaProgettoFormativo(progettoFormativo1.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertThat(progettoFormativo1.getStato(), is(equalTo(progettoFormativoSalvato.getStato())));
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di ricerca di un progetto formativo.
	 * 
	 * @test {@link ProgettoFormativoService#cercaProgettoPerId(Long)}
	 * 
	 * @result Il test è superato se il progetto formativo viene correttamente
	 * trovato nel database
	 */
	
	@Test
	public void cercaProgettoPerId() {
		ProgettoFormativo progettoFormativoSalvato=progettoFormativoService.cercaProgettoPerId(progettoFormativo1.getId());
		assertThat(progettoFormativo1, is(equalTo(progettoFormativoSalvato)));
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di modifica di un progetto formativo.
	 * 
	 * @test {@link ProgettoFormativoService#modificaProgettoFormativo(Long, String, String, int)}
	 * 
	 * @result Il test è superato se il progetto formativo viene correttamente
	 * modificato nel database
	 */
	
	@Test
	public void modificaProgettoFormativo() {
		ProgettoFormativo progettoFormativoSalvato=new ProgettoFormativo();
		System.out.println(delegatoAziendale.getEmail());
		utenzaService.setUtenteAutenticato(delegatoAziendale.getEmail());
		String descrizione="azienda"; 
		String conoscenze="html";
		int max_partecipanti=2;
		progettoFormativo1.setDescrizione(descrizione);
		progettoFormativo1.setConoscenze(conoscenze);
		progettoFormativo1.setMax_partecipanti(max_partecipanti);
		try {
			progettoFormativoSalvato=progettoFormativoService.modificaProgettoFormativo(progettoFormativo1.getId(), descrizione, conoscenze, max_partecipanti);
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertThat(progettoFormativo1, is(equalTo(progettoFormativoSalvato)));
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di riattivazione di un progetto formativo.
	 * 
	 * @test {@link ProgettoFormativoService#riattivazioneProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il progetto formativo viene correttamente
	 * riattivato nel database
	 */
	
	@Test
	public void riattivazioneProgettoFormativo() {
		ProgettoFormativo progettoFormativoSalvato = new ProgettoFormativo();
		utenzaService.setUtenteAutenticato(delegatoAziendale.getEmail());
		progettoFormativo1.setStato(ProgettoFormativo.ARCHIVIATO);
		try {
			progettoFormativoSalvato=progettoFormativoService.riattivazioneProgettoFormativo(progettoFormativo1.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		assertThat(progettoFormativo1.getStato(), is(equalTo(progettoFormativoSalvato.getStato())));
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di salvataggio di un progetto formativo.
	 * 
	 * @test {@link ProgettoFormativoService#salvaProgettoFormativo(ProgettoFormativo)}
	 * 
	 * @result Il test è superato se il progetto formativo viene correttamente
	 * salvato nel database
	 */
	
	@Test
	public void salvaProgettoFormativo() {
		ProgettoFormativo progettoFormativoSalvato= progettoFormativoService.salvaProgettoFormativo(progettoFormativo1);
		assertThat(progettoFormativo1, is(equalTo(progettoFormativoSalvato)));
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di visualizzazione di un progetto formativo
	 * archiviato.
	 * 
	 * @test {@link ProgettoFormativoService#visualizzaProgettiFormativiArchiviatiByAzienda(String)}
	 * 
	 * @result Il test è superato se il progetto formativo viene correttamente
	 * visualizzato
	 */
	
	@Test
	public void visualizzaProgettiFormativiArchiviatiByAzienda() {
		progettoFormativo1.setStato(ProgettoFormativo.ARCHIVIATO);
		progettoFormativo2.setStato(ProgettoFormativo.ARCHIVIATO);
		
		progettoFormativoRepository.save(progettoFormativo1);
		progettoFormativoRepository.save(progettoFormativo2);
		
		List<ProgettoFormativo> progettiSalvati = new ArrayList<ProgettoFormativo>() ;
		for(Azienda azienda : listaAziende) {
			try {
				utenzaService.setUtenteAutenticato(delegatoAziendaleRepository.findByAziendaPartitaIva(azienda.getPartitaIva()).getEmail());
				progettiSalvati = progettoFormativoService.visualizzaProgettiFormativiArchiviatiByAzienda(azienda.getPartitaIva());
			} catch (OperazioneNonAutorizzataException e) {
				e.printStackTrace();
			}
			for(ProgettoFormativo progettoSalvato : progettiSalvati)
				assertThat(listaProgetti.contains(progettoSalvato) , is(true));
		}
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di visualizzazione di un progetto formativo
	 * attivo.
	 * 
	 * @test {@link ProgettoFormativoService#visualizzaProgettiFormativiAttiviByAzienda(String)}
	 * 
	 * @result Il test è superato se il progetto formativo viene correttamente
	 * visualizzato
	 */
	
	@Test
	public void visualizzaProgettiFormativiAttiviByAzienda() {
		List<ProgettoFormativo> progettiSalvati = new ArrayList<ProgettoFormativo>() ;
		for(Azienda azienda : listaAziende) {
			try {
				progettiSalvati = progettoFormativoService.visualizzaProgettiFormativiAttiviByAzienda(azienda.getPartitaIva());
			} catch (OperazioneNonAutorizzataException e) {
				e.printStackTrace();
			}
			for(ProgettoFormativo progettoSalvato : progettiSalvati)
				assertThat(listaProgetti.contains(progettoSalvato) , is(true));
		}
	}
}
