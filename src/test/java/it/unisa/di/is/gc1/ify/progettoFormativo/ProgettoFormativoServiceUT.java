package it.unisa.di.is.gc1.ify.progettoFormativo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.convenzioni.Azienda;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendale;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;

/**
 * Test di unità per la classe ProgettoFormativoService; tipologia di test: whitebox
 * strategia: branch coverage.  
 * @author Geremia Cavezza, Simone Civale , Giacomo Izzo
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class ProgettoFormativoServiceUT {
	
	@Mock
	private ProgettoFormativoRepository progettoFormativoRepository;
	
	@Mock
	private UtenzaService utenzaService;
	
	@Autowired
	@InjectMocks
	private ProgettoFormativoService progettoFormativoService;
	
	private ProgettoFormativo progettoFormativo;
	
	private DelegatoAziendale delegatoAziendale;
	
	private Azienda azienda;
	
	private Studente studente;
	
	@Mock
	private DelegatoAziendale delegatoAziendaleMock;
	
	@Mock
	private Azienda aziendaMock;
	
	/**
	 * Salva il progetto formativo, il delegato aziendale, l'azienda prima dell'esecuzione
	 * di ogni singolo test.
	 */
	@Before
	public void setUpProgetto() {
		progettoFormativo=new ProgettoFormativo();
		delegatoAziendale=new DelegatoAziendale();
		azienda=new Azienda();
		azienda.setpIva("01234567897");
		delegatoAziendale.setAzienda(azienda);
		progettoFormativo.setAmbito("Informatica");
		progettoFormativo.setAttivita("tirocinio");
		progettoFormativo.setAzienda(azienda);
		progettoFormativo.setConoscenze("linguaggio c e Java");
		progettoFormativo.setData_compilazione(LocalDate.of(2019, 11, 11));
		progettoFormativo.setMax_partecipanti(3);
		progettoFormativo.setNome("Progetto 1");
	}
	
	/**
	 * Testa il caso in cui il salvataggio del progetto vada a buon fine.
	 * 
	 * @test {@link ProgettoFormativoService#salvaProgettoFormativo(ProgettoFormativo)}
	 * 
	 * @result Il test è superato se il metodo save del repository è correttamente invocato.
	 */
	@Test
	public void salvaProgettoFormativo() {
		when(progettoFormativoRepository.save(progettoFormativo)).thenReturn(progettoFormativo);
		progettoFormativoService.salvaProgettoFormativo(progettoFormativo);
		verify(progettoFormativoRepository, times(1)).save(progettoFormativo);
	}
	
	/**
	 * Testa il caso in cui l'archiviazione del progetto vada a buon fine.
	 * 
	 * @test {@link ProgettoFormativoService#archiviaProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il metodo save del repository è correttamente invocato.
	 */
	@Test
	public void archiviaProgettoFormativo() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendale);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		progettoFormativo.setStato(ProgettoFormativo.ATTIVO);
		try {
			progettoFormativoService.archiviaProgettoFormativo(progettoFormativo.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(progettoFormativoRepository, times(1)).save(progettoFormativo);
	}
	
	/**
	 * Testa il caso in cui l'utente non sia autorizzato all'operazione.
	 * 
	 * @test {@link ProgettoFormativoService#archiviaProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
	 * previsto dall'oracolo.
	 */
	@Test
	public void archiviaProgettoFormativoUtenteNonAutorizzato() {
		String message="Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		progettoFormativo.setStato(ProgettoFormativo.ATTIVO);
		try {
			progettoFormativoService.archiviaProgettoFormativo(progettoFormativo.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui la piva dell'utente non coincide con quella dell'azienda 
	 * associata al progetto.
	 * 
	 * @test {@link ProgettoFormativoService#archiviaProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
	 * previsto dall'oracolo.	 
	 */
	@Test
	public void archiviaProgettoFormativoPIvaNotEquals() {
		String message="Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendaleMock);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		when(delegatoAziendaleMock.getAzienda()).thenReturn(aziendaMock);
		when(aziendaMock.getpIva()).thenReturn("0123456784");
		progettoFormativo.setStato(ProgettoFormativo.ATTIVO);
		try {
			progettoFormativoService.archiviaProgettoFormativo(progettoFormativo.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui lo stato del progetto da archiviare non è attivo.
	 * 
	 * @test {@link ProgettoFormativoService#archiviaProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
	 * previsto dall'oracolo.	 
	 */
	@Test
	public void archiviaProgettoFormativoStatoNonAttivo() {
		String message="Impossibile archiviare questo progetto";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendale);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		progettoFormativo.setStato(ProgettoFormativo.ARCHIVIATO);
		try {
			progettoFormativoService.archiviaProgettoFormativo(progettoFormativo.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui il progetto formativo sia correttamente attivato.
	 * 
	 * @test {@link ProgettoFormativoService#riattivazioneProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il metodo save del repository è correttamente invocato
	 */
	@Test
	public void riattivaProgettoFormativo() {
		ProgettoFormativo progetto1 = new ProgettoFormativo(progettoFormativo.getNome(), progettoFormativo.getDescrizione(), progettoFormativo.getAmbito(), progettoFormativo.getAttivita(), progettoFormativo.getConoscenze(), progettoFormativo.getMax_partecipanti(), progettoFormativo.getData_compilazione(), progettoFormativo.getStato(), azienda);
		progetto1.setStato(ProgettoFormativo.ARCHIVIATO);
		progetto1.setId(progettoFormativo.getId());
		
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendale);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progetto1));
		try {
			progettoFormativoService.riattivazioneProgettoFormativo(progetto1.getId());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(progettoFormativoRepository, times(1)).save(progetto1);
	}
	
	/**
	 * Testa il caso in cui l' utente non sia autorizzato all'operazione.
	 * 
	 * @test {@link ProgettoFormativoService#riattivazioneProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void riattivazioneProgettoFormativoUtenteNonAutorizzato() {
		String message="Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		progettoFormativo.setStato(ProgettoFormativo.ARCHIVIATO);
		try {
			progettoFormativoService.riattivazioneProgettoFormativo(progettoFormativo.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui la piva del delegato sia diversa da quella dell'azienda associata 
	 * al progetto.
	 * 
	 * @test {@link ProgettoFormativoService#riattivazioneProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void riattivazioneProgettoFormativoPIvaNotEquals() {
		String message="Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendaleMock);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		when(delegatoAziendaleMock.getAzienda()).thenReturn(aziendaMock);
		when(aziendaMock.getpIva()).thenReturn("0123456784");
		progettoFormativo.setStato(ProgettoFormativo.ARCHIVIATO);
		try {
			progettoFormativoService.riattivazioneProgettoFormativo(progettoFormativo.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui il lo stato del progetto non è archiviato.
	 * 
	 * @test {@link ProgettoFormativoService#riattivazioneProgettoFormativo(Long)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void riattivazioneProgettoFormativoStatoNonArchiviato() {
		String message="Impossibile riattivare questo progetto";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendale);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		progettoFormativo.setStato(ProgettoFormativo.ATTIVO);
		try {
			progettoFormativoService.riattivazioneProgettoFormativo(progettoFormativo.getId());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui il progetto è correttamente trovato.
	 * 
	 * @test {@link ProgettoFormativoService#cercaProgettoPerId(Long)}
	 * 
	 * @result Il test è superato se il metodo findById del repository è correttamente invocato.
	 */
	@Test
	public void cercaProgettoPerId() {
		when(progettoFormativoRepository.save(progettoFormativo)).thenReturn(progettoFormativo);
		progettoFormativoService.cercaProgettoPerId(progettoFormativo.getId());
		verify(progettoFormativoRepository, times(1)).findById(progettoFormativo.getId());
	}
	/**
	 * Testa il caso in cui l' utente non sia autorizzato all'operazione.
	 * 
	 * @test {@link ProgettoFormativoService#modificaProgettoFormativo(Long , String , String , int)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void modificaProgettoFormativoUtenteNonAutorizzato() {
		String message="Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		String descrizione = "programmazione";
		String conoscenze= "linguaggio c";
		int maxPartecipanti = 7;
		progettoFormativo.setDescrizione(descrizione);
		progettoFormativo.setConoscenze(conoscenze);
		progettoFormativo.setMax_partecipanti(maxPartecipanti);

		try {
			progettoFormativoService.modificaProgettoFormativo(progettoFormativo.getId() , descrizione , conoscenze, maxPartecipanti);
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui la piva del delegato sia diversa da quella dell'azienda associata 
	 * al progetto.
	 * 
	 * @test {@link ProgettoFormativoService#modificaProgettoFormativo(Long , String , String , int)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void modificaProgettoFormativoPIvaNotEquals() {
		String message="Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendaleMock);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		when(delegatoAziendaleMock.getAzienda()).thenReturn(aziendaMock);
		when(aziendaMock.getpIva()).thenReturn("0123456784");
		String descrizione = "programmazione";
		String conoscenze= "linguaggio c";
		int maxPartecipanti = 7;
		progettoFormativo.setDescrizione(descrizione);
		progettoFormativo.setConoscenze(conoscenze);
		progettoFormativo.setMax_partecipanti(maxPartecipanti);
		
		try {
			progettoFormativoService.modificaProgettoFormativo(progettoFormativo.getId() , descrizione , conoscenze, maxPartecipanti);
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui la modifica del progetto vada a buon fine.
	 * 
	 * @test {@link ProgettoFormativoService#modificaProgettoFormativo(Long , String , String , int)}
	 * 
	 * @result Il test è superato se il metodo save del repository è correttamente invocato.
	 */
	@Test
	public void modificaProgettoFormativo() {
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendale);
		when(progettoFormativoRepository.findById(progettoFormativo.getId())).thenReturn(Optional.of(progettoFormativo));
		String descrizione = "programmazione";
		String conoscenze= "linguaggio c";
		int maxPartecipanti = 7;
		progettoFormativo.getAmbito();
		progettoFormativo.setDescrizione(descrizione);
		progettoFormativo.setConoscenze(conoscenze);
		progettoFormativo.setMax_partecipanti(maxPartecipanti);		
		try {
			progettoFormativoService.modificaProgettoFormativo(progettoFormativo.getId() , progettoFormativo.getDescrizione() , progettoFormativo.getConoscenze(), progettoFormativo.getMax_partecipanti());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(progettoFormativoRepository, times(1)).save(progettoFormativo);
	}
	
	/**
	 * Testa il caso in cui la visualizzazione dei progetti attivi vada a buon fine.
	 * 
	 * @test {@link ProgettoFormativoService#visualizzaProgettiFormativiAttiviByAzienda(String)}
	 * 
	 * @result Il test è superato se il metodo findAllByAziendaPIvaAndStato del repository è 
	 * correttamente invocato.
	 */
	@Test
	public void visualizzaProgettiFormativiAttiviByAzienda() {
		List<ProgettoFormativo> progettiFormativiSalvati= new ArrayList<ProgettoFormativo>();
		when(progettoFormativoRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), ProgettoFormativo.ATTIVO)).thenReturn(progettiFormativiSalvati);
		try {
			progettoFormativoService.visualizzaProgettiFormativiAttiviByAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(progettoFormativoRepository, times(1)).findAllByAziendaPIvaAndStato(azienda.getpIva(), ProgettoFormativo.ATTIVO);
	}
	
	/**
	 * Testa il caso in cui l' utente non sia autorizzato all'operazione.
	 * 
	 * @test {@link ProgettoFormativoService#visualizzaProgettiFormativiArchiviatiByAzienda(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaProgettiFormativiArchiviatiByAziendaUtenteNonAutorizzato() {
		String message="Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(studente);
		try {
			progettoFormativoService.visualizzaProgettiFormativiArchiviatiByAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui la piva del delegato sia diversa da quella dell'azienda associata 
	 * al progetto.	
	 *   
	 * @test {@link ProgettoFormativoService#visualizzaProgettiFormativiArchiviatiByAzienda(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void visualizzaProgettiFormativiArchiviatiByAziendaPIvaNotEquals() {
		String message="Operazione non autorizzata";
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendale);		
		try {
			progettoFormativoService.visualizzaProgettiFormativiArchiviatiByAzienda("01234567892");
		} catch (OperazioneNonAutorizzataException e) {
			assertEquals(message, e.getMessage());
		}
	}
	
	/**
	 * Testa il caso in cui la visualizzazione dei progetti archiviati vada a buon fine.
	 * 
	 * @test {@link ProgettoFormativoService#visualizzaProgettiFormativiArchiviatiByAzienda(String)}
	 * 
	 * @result Il test è superato se il metodo findAllByAziendaPIvaAndStato del repository è 
	 * correttamente invocato.
	 */
	@Test
	public void visualizzaProgettiFormativiArchiviatiByAzienda() {
		List<ProgettoFormativo> progettiFormativiSalvati= new ArrayList<ProgettoFormativo>();
		when(utenzaService.getUtenteAutenticato()).thenReturn(delegatoAziendale);
		when(progettoFormativoRepository.findAllByAziendaPIvaAndStato(azienda.getpIva(), ProgettoFormativo.ARCHIVIATO)).thenReturn(progettiFormativiSalvati);
		try {
			progettoFormativoService.visualizzaProgettiFormativiArchiviatiByAzienda(azienda.getpIva());
		} catch (OperazioneNonAutorizzataException e) {
			e.printStackTrace();
		}
		verify(progettoFormativoRepository, times(1)).findAllByAziendaPIvaAndStato(azienda.getpIva(), ProgettoFormativo.ARCHIVIATO);
	}
	
	/**
	 * Testa il caso in cui il nome inserito sia null.
	 * 
	 * @test {@link ProgettoFormativoService#validaNome(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaNomeNull() {
		String message="Il campo nome non può essere nullo.";
		try {
			progettoFormativoService.validaNome(null);
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza del nome inserito sia minore della lunghezza minima.
	 * 
	 * @test {@link ProgettoFormativoService#validaNome(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaNomeLunghezzaMinFail() {
		String message="Il campo nome deve contenere almeno 2 caratteri";
		try {
			progettoFormativoService.validaNome("a");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza del nome inserito sia maggiore della lunghezza massima.
	 * 
	 * @test {@link ProgettoFormativoService#validaNome(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaNomeLunghezzaMaxFail() {
		String message="Il campo nome deve contenere massimo 255 caratteri";
		try {
			progettoFormativoService.validaNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaa");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui il nome non rispetti il pattern previsto per il campo nome.
	 * 
	 * @test {@link ProgettoFormativoService#validaNome(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaNomePatternFail() {
		String message="Il campo nome deve contenere soltanto caratteri alfanumerici e di punteggiatura";
		try {
			progettoFormativoService.validaNome("Mario@");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui il nome inserito sia corretto.
	 * 
	 * @test {@link ProgettoFormativoService#validaNome(String)}
	 * 
	 * @result Il test è superato se il metodo invocato restituisce il nome inserito.
	 */
	@Test
	public void validaNome() {
		String nomeImmesso = "Mario"; 
		String nomeRestituito = new String();
		try {
			nomeRestituito = progettoFormativoService.validaNome(nomeImmesso);
		} catch (ProgettoFormativoNonValidoException e) {
			e.printStackTrace();
		}
		assertEquals(nomeImmesso , nomeRestituito );
	}
	
	/**
	 * Testa il caso in cui la descrizione inserita sia null.
	 * 
	 * @test {@link ProgettoFormativoService#validaDescrizione(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.	  
	 */
	@Test
	public void validDescrizioneNull() {
		String message="Il campo descrizione non può essere nullo.";
		try {
			progettoFormativoService.validaDescrizione(null);
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lughezza della descrizione inserita sia minore 
	 * della lunghezza minima.
	 * 
	 * @test {@link ProgettoFormativoService#validaDescrizione(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.	  
	 */
	@Test
	public void validaDescrizioneLunghezzaMinFail() {
		String message="Il campo descrizione deve contenere almeno 2 caratteri";
		try {
			progettoFormativoService.validaDescrizione("P");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lughezza della descrizione inserita sia maggiore 
	 * della lunghezza massima.	 
	 *  
	 * @test {@link ProgettoFormativoService#validaDescrizione(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.	  
	 */
	@Test
	public void validaDescrizioneLunghezzaMaxFail() {
		String message="Il campo descrizione deve contenere massimo 800 caratteri";
		try {
			progettoFormativoService.validaDescrizione("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
					+ "aaaaaa");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la descrizione inserita sia corretta.
	 * 
	 * @test {@link ProgettoFormativoService#validaDescrizione(String)}
	 * 
	 * @result Il test è superato se il metodo invocato restituisce la descrizione inserita.
	 */
	@Test
	public void validaDescrizione() {
		String descrizioneImmessa = "Programmazione";
		String descrizioneRestituita = new String();
		try {
			descrizioneRestituita = progettoFormativoService.validaDescrizione(descrizioneImmessa);
		} catch (ProgettoFormativoNonValidoException e) {
			e.printStackTrace();
		}
		assertEquals(descrizioneImmessa , descrizioneRestituita);
	}
	
	/**
	 * Testa il caso in cui l'ambito inserito sia null.
	 * 
	 * @test {@link ProgettoFormativoService#validaAmbito(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaAmbitoNull() {
		String message="Il campo ambito non può essere nullo.";
		try {
			progettoFormativoService.validaAmbito(null);
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza dell'ambito inserito sia minore della lunghezza minima.
	 * 
	 * @test {@link ProgettoFormativoService#validaAmbito(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaAmbitoLunghezzaMinFail() {
		String message="Il campo ambito deve contenere almeno 2 caratteri";
		try {
			progettoFormativoService.validaAmbito("a");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza dell'ambito inserito sia maggiore della lunghezza massima.
	 * 
	 * @test {@link ProgettoFormativoService#validaAmbito(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaAmbitoLunghezzaMaxFail() {
		String message="Il campo ambito deve contenere massimo 255 caratteri";
		try {
			progettoFormativoService.validaAmbito("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui l'ambito non rispetti il pattern previsto per il campo ambito.
	 * 
	 * @test {@link ProgettoFormativoService#validaAmbito(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaAmbitoFormatoFail() {
		String message="Il campo ambito deve contenere soltanto caratteri alfanumerici e di punteggiatura";
		try {
			progettoFormativoService.validaAmbito("ambito?");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui l'ambito inserito sia corretto.
	 * 
	 * @test {@link ProgettoFormativoService#validaAmbito(String)}
	 * 
	 * @result Il test è superato se il metodo invocato restituisce l'ambito inserito.
	 */
	@Test
	public void validaAmbito() {
		try {
			progettoFormativoService.validaAmbito("Informatica");
		} catch (ProgettoFormativoNonValidoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Testa il caso in cui l'attività inserita sia null.
	 * 
	 * @test {@link ProgettoFormativoService#validaAttivita(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaAttivitaNull() {
		String message="Il campo attività non può essere nullo.";
		try {
			progettoFormativoService.validaAttivita(null);
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza dell'attività inserita sia minore della lunghezza minima.
	 * 
	 * @test {@link ProgettoFormativoService#validaAmbito(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaAttivitaLunghezzaMinFail() {
		String message="Il campo attività deve contenere almeno 2 caratteri";
		try {
			progettoFormativoService.validaAttivita("a");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza dell'attività inserito sia maggiore della lunghezza massima.
	 * 
	 * @test {@link ProgettoFormativoService#validaAttivita(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaAttivitaLunghezzaMaxFail() {
		String message="Il campo attività deve contenere massimo 500 caratteri";
		try {
			progettoFormativoService.validaAttivita("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui l'ambito inserito sia corretto.
	 * 
	 * @test {@link ProgettoFormativoService#validaAttivita(String)}
	 * 
	 * @result Il test è superato se il metodo invocato restituisce l'attività inserito.
	 */
	@Test
	public void validaAttivita() {
		try {
			progettoFormativoService.validaAttivita("Progetto");
		} catch (ProgettoFormativoNonValidoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Testa il caso in cui le conoscenze inserite siano null.
	 * 
	 * @test {@link ProgettoFormativoService#validaConoscenze(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaConoscenzeNull() {
		String message="Il campo conoscenze non può essere nullo.";
		try {
			progettoFormativoService.validaConoscenze(null);
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza delle conoscenze inserite sia minore della lunghezza minima.
	 * 
	 * @test {@link ProgettoFormativoService#validaConoscenze(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaConoscenzeLunghezzaMinFail() {
		String message="Il campo conoscenze deve contenere almeno 2 caratteri";
		try {
			progettoFormativoService.validaConoscenze("a");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza del campo conoscenze inserito sia maggiore della lunghezza massima.
	 * 
	 * @test {@link ProgettoFormativoService#validaConoscenze(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaConoscenzeLunghezzaMaxFail() {
		String message="Il campo conoscenze deve contenere massimo 500 caratteri";
		try {
			progettoFormativoService.validaConoscenze("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui le conoscenze inserite siano corrette.
	 * 
	 * @test {@link ProgettoFormativoService#validaConoscenze(String)}
	 * 
	 * @result Il test è superato se il metodo invocato restituisce le conoscenze inserite.
	 */
	@Test
	public void validaConoscenze() {
		try {
			progettoFormativoService.validaConoscenze("Linguaggio C e Java");
		} catch (ProgettoFormativoNonValidoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Testa il caso in cui i max partecipanti inseriti siano null.
	 * 
	 * @test {@link ProgettoFormativoService#validaMaxPartecipanti(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaMaxPartecipantiNull() {
		String message="Il campo Max partecipanti non può essere nullo.";
		try {
			progettoFormativoService.validaMaxPartecipanti(null);
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui i max partecipanti inseriti siano una stringa vuota.
	 * 
	 * @test {@link ProgettoFormativoService#validaMaxPartecipanti(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaMaxPartecipantiStringaVuota() {
		String message="Il campo Max partecipanti non può essere nullo.";
		try {
			progettoFormativoService.validaMaxPartecipanti("");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza di max partecipanti inseriti sia minore della lunghezza minima.
	 * 
	 * @test {@link ProgettoFormativoService#validaMaxPartecipanti(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaMaxPartecipantiLunghezzaMinFail() {
		String message="Il campo Max partecipanti deve contenere almeno 1 numero";
		try {
			progettoFormativoService.validaMaxPartecipanti("0");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui la lunghezza del campo maxPartecipanti inserito sia maggiore della 
	 * lunghezza massima.
	 * 
	 * @test {@link ProgettoFormativoService#validaMaxPartecipanti(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaMaxPartecipantiLunghezzaMaxFail() {
		String message="Il campo Max Partecipanti deve contenere massimo 3 numeri";
		try {
			progettoFormativoService.validaMaxPartecipanti("1000");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui i maxPartecipanti non rispetti il pattern previsto per 
	 * il campo maxPartecipanti.
	 * 
	 * @test {@link ProgettoFormativoService#validaMaxPartecipanti(String)}
	 * 
	 * @result Il test è superato se il messaggio generato dal sistema è uguale a quello 
	 * previsto dall'oracolo.
	 */
	@Test
	public void validaMaxPartecipantiFormatoFail() {
		String message="Il campo Max Partecipanti deve contenere soltanto numeri";
		try {
			progettoFormativoService.validaMaxPartecipanti("+1");
		} catch (ProgettoFormativoNonValidoException e) {
			assertEquals(e.getMessage(), message);
		}
	}
	
	/**
	 * Testa il caso in cui i maxPartecipanti inseriti siano corretti.
	 * 
	 * @test {@link ProgettoFormativoService#validaMaxPartecipanti(String)}
	 * 
	 * @result Il test è superato se il metodo invocato restituisce i maxPartecipanti inseriti.
	 */
	@Test
	public void validaMaxPartecipanti() {
		try {
			progettoFormativoService.validaMaxPartecipanti("3");
		} catch (ProgettoFormativoNonValidoException e) {
			e.printStackTrace();
		}
	}
}
