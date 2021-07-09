package it.unisa.di.is.gc1.ify.utenza;

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizione;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamento;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Classe singleton che permette di inviare una email informativa all'utente
 * quando si è verificato un cambiamento di stato.
 * 
 * @author Alessia Natale, Benedetta Coccaro
 */

@Component
@Scope("singleton")
public class MailSingletonSender {

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * Metodo che permette di inviare una email
	 * 
	 * @param object       Object che rappresenta l'oggetto coinvolto ai cambiamenti
	 * @param destinatario String che rappresenta l'email del destinatario
	 */
	public void sendEmail(Object object, String destinatario) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(destinatario);

		msg.setSubject("Info piattaforma IFY");
		String message = message(object);
		msg.setText(message);

		javaMailSender.send(msg);

	}

	/**
	 * Metodo che ritorna la stringa contenente il messaggio informativo destinato
	 * all'utente
	 * 
	 * @param obj Object che rappresenta l'oggetto coinvolto ai cambiamenti
	 * @return String contenente il messaggio
	 */
	private String message(Object obj) {
		if (obj instanceof RichiestaIscrizione) {
			RichiestaIscrizione richiestaIscrizione = (RichiestaIscrizione) obj;
			String stato = richiestaIscrizione.getStato();
			String nome = richiestaIscrizione.getStudente().getNome();
			String cognome = richiestaIscrizione.getStudente().getCognome();
			if (stato == RichiestaIscrizione.ACCETTATA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la sua richiesta di iscrizione alla piattaforma IFY è stata " + stato
						+ ".\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
			else if (stato == RichiestaIscrizione.RIFIUTATA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la sua richiesta di iscrizione alla piattaforma IFY è stata " + stato
						+ ". La invitiamo a riprovare.\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
		} else if (obj instanceof RichiestaConvenzionamento) {
			RichiestaConvenzionamento richiestaConvenzionamento = (RichiestaConvenzionamento) obj;
			String stato = richiestaConvenzionamento.getStato();
			String nome = richiestaConvenzionamento.getDelegatoAziendale().getNome();
			String cognome = richiestaConvenzionamento.getDelegatoAziendale().getCognome();
			String nomeAzienda = richiestaConvenzionamento.getAzienda().getRagioneSociale();
			if (stato == RichiestaConvenzionamento.ACCETTATA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la richiesta di convenzionamento dell'azienda " + nomeAzienda
						+ " è stata " + stato
						+ ".\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
			else if (stato == RichiestaConvenzionamento.RIFIUTATA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la richiesta di convenzionamento dell'azienda " + nomeAzienda
						+ " è stata " + stato
						+ ". La invitiamo a riprovare.\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
		}

		else if (obj instanceof DomandaTirocinio) {
			DomandaTirocinio domandaTirocinio = (DomandaTirocinio) obj;
			String stato = domandaTirocinio.getStato();
			String nome = domandaTirocinio.getStudente().getNome();
			String cognome = domandaTirocinio.getStudente().getCognome();

			String progetto = domandaTirocinio.getProgettoFormativo().getNome();
			String nomeAzienda = domandaTirocinio.getAzienda().getRagioneSociale();
			String nomeDocente = domandaTirocinio.getTutor().getNome();
			String cognomeDocente = domandaTirocinio.getTutor().getCognome();

			System.err.println("Status: " + stato);

			if (stato == DomandaTirocinio.ACCETTATA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la sua domanda di tirocinio inviata all'azienda " + nomeAzienda
						+ ", per il progetto " + progetto + ", è stata " + stato
						+ " dall'azienda e dal docente tutor " +nomeDocente+" "+cognomeDocente+".\nManca l'approvazione dell'Ufficio Tirocini per concludere la richiesta.\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
			else if (stato == DomandaTirocinio.RIFIUTATA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la sua domanda di tirocinio inviata all'azienda " + nomeAzienda
						+ ", per il progetto " + progetto + ", è stata " + stato
						+ " . La invitiamo a riprovare.\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
			else if (stato == DomandaTirocinio.APPROVATA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la sua domanda di tirocinio inviata all'azienda " + nomeAzienda
						+ " e al docente " +nomeDocente+" "+cognomeDocente+", per il progetto " + progetto + ", è stata definitivamente " + stato
						+ " dall'Ufficio Tirocini.\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
			else if (stato == DomandaTirocinio.RESPINTA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la sua domanda di tirocinio inviata all'azienda " + nomeAzienda
						+ ", per il progetto " + progetto + ", è stata definitivamente " + stato
						+ ". La inviriamo a riprovare.\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
			else if (stato == DomandaTirocinio.IN_ATTESA_TUTOR)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la sua domanda di tirocinio inviata all'azienda " + nomeAzienda
						+ ", per il progetto " + progetto + ", è stata accettata dall'azienda.\nManca l'accettazione del docente " +nomeDocente+" "+cognomeDocente+" per completare la procedura.\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
			else if (stato == DomandaTirocinio.IN_ATTESA_AZIENDA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che la sua domanda di tirocinio inviata all'azienda " + nomeAzienda
						+ ", per il progetto " + progetto + ", è stata accettata dal docente " +nomeDocente+" "+cognomeDocente+".\nManca l'accettazione dell'azienda per completare la procedura.\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
			else if (stato == DomandaTirocinio.TERMINATA)
				return "Gentile " + nome + " " + cognome
						+ " la informiamo che il suo tirocinio presso l'azienda " + nomeAzienda
						+ " per il progetto " + progetto + " è stato concluso con successo e approvato dal docente " +nomeDocente+" "+cognomeDocente+".\nCordiali saluti, l'Ufficio Tirocini dell'Università degli Studi di Salerno.";
		}
		return "";
	}

}
