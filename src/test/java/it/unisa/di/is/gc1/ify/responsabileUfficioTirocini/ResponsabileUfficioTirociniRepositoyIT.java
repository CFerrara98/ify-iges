package it.unisa.di.is.gc1.ify.responsabileUfficioTirocini;

import static org.junit.Assert.assertTrue;

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
import it.unisa.di.is.gc1.ify.convenzioni.AziendaRepository;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendaleRepository;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;


/**
 * Test di integrazione fra la classe ResponsabileUfficioTirocini e il Database. 
 * Metodologia: bottom-up.
 * @author Giacomo Izzo , Alessia Natale, Roberto Calabrese.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ResponsabileUfficioTirociniRepositoyIT {

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
	
	
	private List<ResponsabileUfficioTirocini> listaResponsabili ; 
	
	/**
	 * Inizializza e salva una lista di responsabili nel database prima dell'esecuzione di 
	 * ogni test.
	 */
	@Before
	public void setUp() {
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
		
		//cresazione e implementazione di un responsabile
		String nome1 = "Mario";
		String cognome1 = "Rossi"; 
		String sesso1 = "M"; 
		String email1 = "m.rossi@unisa.it"; 
		String indirizzo1 = "Via Verdi, 1"; 
		String password1 = "mariorossi1"; 
		String ruolo1 = "responsabile"; 
		ResponsabileUfficioTirocini responsabile1 = new ResponsabileUfficioTirocini(nome1, cognome1, sesso1, email1, indirizzo1, password1, ruolo1);
		responsabile1.setEmail("m.rossi@unisa.it");

		//cresazione e implementazione di un responsabile
		String nome2 = "Claudia";
		String cognome2 = "Bianchi"; 
		String sesso2 = "F"; 
		String email2 = "c.bianchi@unisa.it"; 
		String indirizzo2 = "Via Roma, 1"; 
		String password2 = "claudiabianchi1"; 
		String ruolo2 = "responsabile"; 
		ResponsabileUfficioTirocini responsabile2 = new ResponsabileUfficioTirocini(nome2, cognome2, sesso2, email2, indirizzo2, password2, ruolo2);
		
		responsabile1.setEmail("g.bianchi@unisa.it");
		responsabile1.getRuolo();
		responsabile1.setRuolo("responsabile");
		
		//salvataggio dei responsabili nel database
		responsabileRepository.save(responsabile1);
		responsabileRepository.save(responsabile2);

		//aggiunta dei responsabili in lista
		listaResponsabili = new ArrayList<>();
		listaResponsabili.add(responsabile1);
		listaResponsabili.add(responsabile2);
	}
	
	/**
	 * Testa l'interazione con il database per determinare se la ricerca di un
	 * responsabile tramite email avviene correttamente.
	 * 
	 * @test {@link ResponsabileUfficioTirociniRepository#findByEmail()}
	 * 
	 * @result Il test è superato se il responsabile resitutito dal metodo testato
	 * è presente nella lista utilizzata per il test.
	 */
	@Test
	public void findByEmail() {
		for(ResponsabileUfficioTirocini responsabile : listaResponsabili) {
			ResponsabileUfficioTirocini responsabileRestituito = responsabileRepository.findByEmail(responsabile.getEmail());
			assertTrue(listaResponsabili.contains(responsabileRestituito));
		}
	}

	/**
	 * Testa l'interazione con il database per determinare se la verifica 
	 * dell'esistenza di un responsabile tramite mail avviene correttamente.
	 * 
	 * @test {@link ResponsabileUfficioTirociniRepository#existsByEmail()}
	 * 
	 * @result Il test è superato se il metodo invocato restituisce true  
	 * per i responsabili presenti nella lista usata per il test.
	 */
	@Test
	public void existsByEmail() {
		for(ResponsabileUfficioTirocini responsabile : listaResponsabili) 
			assertTrue(responsabileRepository.existsByEmail(responsabile.getEmail()));
	}
	
}
