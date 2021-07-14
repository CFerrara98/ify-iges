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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.convenzioni.Azienda;
import it.unisa.di.is.gc1.ify.convenzioni.AziendaRepository;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendaleRepository;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;

/**
 * Test di integrazione fra la classe ProgettoFormativoRepository e il Database. 
 * Metodologia: bottom-up.
 * @author Giacomo Izzo , Simone Civale.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProgettoFormativoRepositoryIT {
	
	@Autowired
	private ProgettoFormativoRepository progettoFormativoRepository;
	
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
	
	private List<ProgettoFormativo> listaProgettiFormativi;
	
	private List<Azienda> listaAziende;

	/**
	 * Salva la lista dei progetti formativi sul database prima dell'esecuzione di ogni
	 * singolo test.
	 */
	
	@Before
	public void salvaProgettoFormativo() {

		domandeRepository.deleteAll();
		progettiRepository.deleteAll();
		convenzionamentiRepository.deleteAll();
		delegatoRepository.deleteAll();
		aziendeRepository.deleteAll();
		iscrizioneRepository.deleteAll();
		studenteRepository.deleteAll();
		responsabileRepository.deleteAll();
		utenteRepository.deleteAll();
		
		listaProgettiFormativi= new ArrayList<ProgettoFormativo>();
		listaAziende= new ArrayList<Azienda>();
		// Crea il progetto formativo #1
		ProgettoFormativo progettoFormativo1=new ProgettoFormativo();
		// Crea l'azienda #1
		Azienda azienda1=new Azienda();
		azienda1.setDescrizione("azienda di software informatici");
		azienda1.setPartitaIva("01234567892");
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
		progettoFormativo1.toString();

		progettoFormativoRepository.save(progettoFormativo1);
		listaProgettiFormativi.add(progettoFormativo1);
		listaAziende.add(azienda1);
		
		// Crea il progetto formativo #2
		ProgettoFormativo progettoFormativo2= new ProgettoFormativo();
		// Crea l'azienda #2
		Azienda azienda2=new Azienda();
		azienda2.setDescrizione("azienda di software gestionali");
		azienda2.setPartitaIva("01234789564");
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
		
		progettoFormativoRepository.save(progettoFormativo2);
		listaProgettiFormativi.add(progettoFormativo2);
		listaAziende.add(azienda2);

	}
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di un
	 * progetto formativo tramite Id avvenga correttamente.
	 * 
	 * @test {@link ProgettoFormativoRepository}
	 * 
	 * @result Il test è superato se la ricerca dell'id dei progetti formativi
	 * 		   presenti nella lista utilizzata per il test ha successo.
	 */
	@Test
	public void findById() {
		/* Controlla che ogni progetto formativo della lista utilizzata per il test sia esistente
		 *  nel database
		 */
		for(ProgettoFormativo progettoFormativo : listaProgettiFormativi) {
			ProgettoFormativo progettoFormativoSalvato=progettoFormativoRepository.findById(progettoFormativo.getId()).orElse(null);
			assertThat(progettoFormativo, is(equalTo(progettoFormativoSalvato)));
		}
	}
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di un progetto formativo
	 * tramite la partita iva dell'azienda e lo stato del progetto formativo avvenga correttamente.
	 * 
	 * @test {@link ProgettoFormativoRepository#findAllByAziendaPartitaIvaAndStato(String, String)}
	 * 
	 * @result Il test è superato se la ricerca della partita iva e lo stato del progetto formativo
	 *         presenti nella lista utilizzata per il test ha successo.
	 */
	@Test
	public void findAllByAziendaPartitaIvaAndStato() {
		/* Controlla che ogni progetto formativo inserito per il test sia presente sul database
		 * ricercandolo per partita iva e per stato del progetto formativo
		 */
		System.err.println("PIPPO: " + listaAziende);
		System.err.println("BAUDO: " + progettoFormativoRepository);
		for(Azienda azienda : listaAziende) {
			List <ProgettoFormativo> listprogettiSalvati = progettoFormativoRepository.findAllByAziendaPartitaIvaAndStato(azienda.getPartitaIva(), ProgettoFormativo.ATTIVO);
			for (ProgettoFormativo progetto: listprogettiSalvati) {
				assertThat(listaProgettiFormativi.contains(progetto), is(true));
			}			
		}
	}
}