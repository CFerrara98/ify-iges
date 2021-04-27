package it.unisa.di.is.gc1.ify.studente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizione;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneService;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.convenzioni.AziendaRepository;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendaleRepository;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;

/**
 * Classe di test d'integrazione RichiestaIscrizioneRepository - (StudenteRepository - Richiesta iscrizione repository 
 * UtenteRepository 
 * @author Carmine Ferrara - Giacomo Izzo -  Geremia Cavezza
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class ServiceRichiestaIscrizioneRepositoriesIT {
	
	@Autowired
	private RichiestaIscrizioneService richiestaIscrizioneService;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private UtenzaService utenzaService;
	
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
	private ResponsabileUfficioTirociniRepository responsabileRepository;
	
	private Studente studente;
	private RichiestaIscrizione richiestaIscrizione;
	private ResponsabileUfficioTirocini responsabile;
	
	/**
	 * Testa il corretto funzionamento del metodo di salvataggio di una richiesta di iscrizione.
	 * 
	 * @test {@link RichiestaIscrizioneService#salvaRichiestaIscrizione(RichiestaIscrizione)}
	 * 
	 * @result Il test è superato se la richesta di iscrizione viene correttamente
	 * salvata nel database
	 */
	
	@Test
	public void salvaRichiestaIscrizione() {
		domandeRepository.deleteAll();
		progettiRepository.deleteAll();
		convenzionamentiRepository.deleteAll();
		delegatoRepository.deleteAll();
		aziendeRepository.deleteAll();
		iscrizioneRepository.deleteAll();
		studenteRepository.deleteAll();
		responsabileRepository.deleteAll();
		utenteRepository.deleteAll();
		
		studente = new Studente();
		
		studente.setNome("Mario");
		studente.setCognome("Rossi");
		studente.setDataNascita(LocalDate.of(1997, 12, 24));
		studente.setEmail("m.rossi@studenti.unisa.it");
		studente.setIndirizzo("Via Roma 4 84080 Salerno SA");
		studente.setMatricola("0512105144");
		studente.setTelefono("333-3544541");
		studente.setSesso("M");
		studente.setPassword("Password#1");
		
		richiestaIscrizione=new RichiestaIscrizione();
		richiestaIscrizione.setStudente(studente);
		richiestaIscrizione = richiestaIscrizioneService.salvaRichiestaIscrizione(richiestaIscrizione);
		
		assertNotNull(richiestaIscrizione);
		
	}
	
	/**
	 * Testa il corretto funzionamento del metodo di accettazione di una richiesta di iscrizione.
	 * 
	 * @test {@link RichiestaIscrizioneService#accettaRichiestaIscrizione(Long)}
	 * 
	 * @result Il test è superato se la richesta di iscrizione viene correttamente
	 * accettata ed aggiornata nel database
	 */
	
	@Test
	public void accettaRichiestaIscrizione() {
		
		studente = new Studente();
		
		studente.setNome("Mario");
		studente.setCognome("Rossi");
		studente.setDataNascita(LocalDate.of(1997, 12, 24));
		studente.setEmail("m.rossi@studenti.unisa.it");
		studente.setIndirizzo("Via Roma 4 84080 Salerno SA");
		studente.setMatricola("0512105144");
		studente.setTelefono("333-3544541");
		studente.setSesso("M");
		studente.setPassword("Password#1");
		
		
		responsabile = new ResponsabileUfficioTirocini();
		
		responsabile.setNome("Paolo");
		responsabile.setCognome("Neri");
		responsabile.setIndirizzo("Via Roma 29 80197 Salerno SA");
		responsabile.setSesso("M");
		responsabile.setEmail("p.neri87@gmail.com");
		responsabile.setRuolo("Responsabile");
		responsabile.setPassword("Password#5");
		
		utenteRepository.save(responsabile);
		richiestaIscrizione=new RichiestaIscrizione();
		richiestaIscrizione.setStato(RichiestaIscrizione.IN_ATTESA);
		richiestaIscrizione.setStudente(studente);
		richiestaIscrizione = richiestaIscrizioneService.salvaRichiestaIscrizione(richiestaIscrizione);
		utenzaService.setUtenteAutenticato(responsabile.getEmail());

		try {
			richiestaIscrizione = richiestaIscrizioneService.accettaRichiestaIscrizione(richiestaIscrizione.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		
		assertEquals(RichiestaIscrizione.ACCETTATA, richiestaIscrizione.getStato());
		
	}

	/**
	 * Testa il corretto funzionamento del metodo di rifiuto di una richiesta di iscrizione.
	 * 
	 * @test {@link RichiestaIscrizioneService#rifiutaRichiestaIscrizione(Long))}
	 * 
	 * @result Il test è superato se la richesta di iscrizione viene correttamente
	 * rifiutata ed aggiornata nel database
	 */
	
	@Test
	public void rifiutaRichiestaIscrizione() {
		
		studente = new Studente();
		
		studente.setNome("Mario");
		studente.setCognome("Rossi");
		studente.setDataNascita(LocalDate.of(1997, 12, 24));
		studente.setEmail("m.rossi@studenti.unisa.it");
		studente.setIndirizzo("Via Roma 4 84080 Salerno SA");
		studente.setMatricola("0512105144");
		studente.setTelefono("333-3544541");
		studente.setSesso("M");
		studente.setPassword("Password#1");
		
		
		responsabile = new ResponsabileUfficioTirocini();
		
		responsabile.setNome("Paolo");
		responsabile.setCognome("Neri");
		responsabile.setIndirizzo("Via Roma 29 80197 Salerno SA");
		responsabile.setSesso("M");
		responsabile.setEmail("p.neri87@gmail.com");
		responsabile.setRuolo("Responsabile");
		responsabile.setPassword("Password#5");
		
		utenteRepository.save(responsabile);
		richiestaIscrizione=new RichiestaIscrizione();
		richiestaIscrizione.setStudente(studente);
		richiestaIscrizione.setStato(RichiestaIscrizione.IN_ATTESA);
		richiestaIscrizione = richiestaIscrizioneService.salvaRichiestaIscrizione(richiestaIscrizione);
		utenzaService.setUtenteAutenticato(responsabile.getEmail());

		try {
			richiestaIscrizione = richiestaIscrizioneService.rifiutaRichiestaIscrizione(richiestaIscrizione.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		
		assertEquals(RichiestaIscrizione.RIFIUTATA, richiestaIscrizione.getStato());
	}
}
