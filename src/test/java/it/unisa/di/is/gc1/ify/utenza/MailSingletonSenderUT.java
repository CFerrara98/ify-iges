package it.unisa.di.is.gc1.ify.utenza;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizione;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.convenzioni.Azienda;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendale;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamento;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinio;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativo;

/**
 * Test di unità per la classe MailSingletonSender ; tipologia di test: whitebox
 * metodologia: branch coverage.  
 * @author Giacomo Izzo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MailSingletonSenderUT {

	
	@Mock 
	private JavaMailSender javaMailSender;
	@InjectMocks
	private MailSingletonSender mailSender;
	
	@Captor 
	ArgumentCaptor<String> captor;
	
	private RichiestaIscrizione richiestaIscrizione;
	
	private RichiestaConvenzionamento richiestaConvenzionamento;
	
	private DomandaTirocinio domandaTirocinio;
	
	private Studente studente;
	
	private DelegatoAziendale delegato;
	
	private Azienda azienda;
	
	private ProgettoFormativo progettoFormativo;

	private DocenteTutor docenteTutor;
	
	/**
	 * Salva lo studente, la richiesta di iscrizione, l'azienda, il delegato aziendale,
	 * il progetto formativo, la domanda di tirocinio e la richiesta di convenzionamento
	 * prima dell'esecuzione di ogni singolo test.
	 */
	@Before
	public void setUp() {
		studente = new Studente();
		studente.setNome("Mario");
		studente.setCognome("Rossi");
		studente.setEmail("m.rossi@studenti.unisa.it");
		
		richiestaIscrizione = new RichiestaIscrizione();
		richiestaIscrizione.setStudente(studente);
		
		azienda = new Azienda();
		azienda.setRagioneSociale("NetData");
		
		delegato = new DelegatoAziendale();
		delegato.setNome("Paolo");
		delegato.setCognome("Bianchi");
		delegato.setEmail("p.bianchi@gmail.com");
		delegato.setAzienda(azienda);
		
		progettoFormativo = new ProgettoFormativo();
		progettoFormativo.setNome("DataScience");
		progettoFormativo.setAzienda(azienda);

		docenteTutor = new DocenteTutor();
		docenteTutor.setEmail("docente@prova.it");
		docenteTutor.setCognome("Di Prova");
		docenteTutor.setNome("Docente");
		docenteTutor.setIndirizzo("via tal dei tali 1");
		docenteTutor.setSesso("M");
		docenteTutor.setCampoRicerca("Ingegneria");
		docenteTutor.setPassword("Password1");
	
		domandaTirocinio = new DomandaTirocinio();
		domandaTirocinio.setAzienda(azienda);
		domandaTirocinio.setStudente(studente);
		domandaTirocinio.setProgettoFormativo(progettoFormativo);
		domandaTirocinio.setTutor(docenteTutor);
		
		richiestaConvenzionamento = new RichiestaConvenzionamento();
		richiestaConvenzionamento.setDelegatoAziendale(delegato);
		richiestaConvenzionamento.setAzienda(azienda);
		
	}

	/**
	 * Testa il caso in cui l'oggetto passato al metodo sendEmal non sia un 
	 * istanza di RichiestaIscrizione.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void message_Not_Instance() {
		String destinatario = "m.rossi@studenti.unisa.it";
		Object object = new Object(); 
		
		mailSender.sendEmail(object, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	
	}
	
	/**
	 * Testa il caso in cui la richiesta d'iscrizione sia stata accettata.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void messageRichiestaIscrizioneAcettata() {
		String destinatario = studente.getEmail(); 
		richiestaIscrizione.setStato(RichiestaIscrizione.ACCETTATA);
		
		mailSender.sendEmail(richiestaIscrizione, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la richiesta d'iscrizione sia stata rifiutata.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void messageRichiestaIscrizioneRifiutata() {
		String destinatario = studente.getEmail(); 
		richiestaIscrizione.setStato(RichiestaIscrizione.RIFIUTATA);
		
		mailSender.sendEmail(richiestaIscrizione, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la richiesta d'iscrizione sia stata posta in attesa.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void messageRichiestaIscrizioneInAttesa() {
		String destinatario = studente.getEmail(); 
		richiestaIscrizione.setStato(RichiestaIscrizione.IN_ATTESA);
		
		mailSender.sendEmail(richiestaIscrizione, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la richiesta di convenzionamento sia stata accettata.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void messageRichiestaConvenzionamentoAcettata() {
		String destinatario = delegato.getEmail(); 
		richiestaConvenzionamento.setStato(RichiestaConvenzionamento.ACCETTATA);
		
		mailSender.sendEmail(richiestaConvenzionamento, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la richiesta di convenzionamento sia stata rifiutata.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void messageRichiestaConvenzionamentoRifiutata() {
		String destinatario = delegato.getEmail(); 
		richiestaConvenzionamento.setStato(RichiestaConvenzionamento.RIFIUTATA);
		
		mailSender.sendEmail(richiestaConvenzionamento, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la richiesta di convenzionamento sia stata posta in attesa.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void messageRichiestaConvenzionamentoInAttesa() {
		String destinatario = delegato.getEmail(); 
		richiestaConvenzionamento.setStato(RichiestaConvenzionamento.IN_ATTESA);
		
		mailSender.sendEmail(richiestaConvenzionamento, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la domanda di tirocinio sia stata accettata.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void messageDomandaTirocinioAccettata() {
		String destinatario = studente.getEmail(); 
		domandaTirocinio.setStato(DomandaTirocinio.ACCETTATA);
		
		mailSender.sendEmail(domandaTirocinio, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la domanda di tirocinio sia stata rifiutata.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void messageDomandaTirocinioRifiutata() {
		String destinatario = studente.getEmail(); 
		domandaTirocinio.setStato(DomandaTirocinio.RIFIUTATA);
		
		mailSender.sendEmail(domandaTirocinio, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la domanda di tirocinio sia stata approvata.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test e' superato se il metodo send della classe javaMailSender 
	 *		   e' correttamente invocato
	 */
	@Test
	public void messageDomandaTirocinioApprovata() {
		String destinatario = studente.getEmail(); 
		domandaTirocinio.setStato(DomandaTirocinio.APPROVATA);
		
		mailSender.sendEmail(domandaTirocinio, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la domanda di tirocinio sia stata respinta.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test è superato se il metodo send della classe javaMailSender 
	 *		   è correttamente invocato
	 */
	@Test
	public void messageDomandaTirocinioRespinta() {
		String destinatario = studente.getEmail(); 
		domandaTirocinio.setStato(DomandaTirocinio.RESPINTA);
		
		mailSender.sendEmail(domandaTirocinio, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	/**
	 * Testa il caso in cui la domanda di tirocinio sia stata posta in attesa.
	 * 
	 * @test {@link MailSingletonSender#sendEmail(Object, String)}
	 * 
	 * @result Il test è superato se il metodo send della classe javaMailSender 
	 *		   è correttamente invocato
	 */
	@Test
	public void messageDomandaTirocinioInAttesa() {
		String destinatario = studente.getEmail(); 
		domandaTirocinio.setStato(DomandaTirocinio.IN_ATTESA);
		
		mailSender.sendEmail(domandaTirocinio, destinatario);
		verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	

}
