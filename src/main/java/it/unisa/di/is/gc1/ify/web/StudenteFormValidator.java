package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneNonValidaException;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link StudenteForm} tramite i servizi
 * offerti da {@link RichiestaIscrizioneService}. Il controllo effettuato
 * riguarda la validità dei campi definiti nell'entità Studente.
 * 
 * @see StudenteForm
 * @see RichiestaIscrizioneService
 * 
 * @author Giusy Castaldo, Alessia Natale
 *
 */
@Component
public class StudenteFormValidator implements Validator {

	@Autowired
	private RichiestaIscrizioneService richiestaIscrizioneService;

	@Override
	public boolean supports(Class<?> clazz) {
		return StudenteForm.class.isAssignableFrom(clazz);
	}

	/**
	 * Effettua la validazione dell'oggetto target riportando gli errori
	 * nell'oggetto errors.
	 * 
	 * @param target Oggetto da validare
	 * @param errors Oggetto in cui salvare l'esito della validazione
	 */
	@Override
	public void validate(Object target, Errors errors) {
		StudenteForm studenteForm = (StudenteForm) target;

		// Validazione del campo nome
		try {
			richiestaIscrizioneService.validaNome(studenteForm.getNome());
		} catch (RichiestaIscrizioneNonValidaException e1) {
			errors.reject(e1.getTarget(), e1.getMessage());
		}

		// Validazione del campo cognome
		try {
			richiestaIscrizioneService.validaCognome(studenteForm.getCognome());
		} catch (RichiestaIscrizioneNonValidaException e2) {
			errors.reject(e2.getTarget(), e2.getMessage());
		}

		// Validazione del campo indirizzo
		try {
			richiestaIscrizioneService.validaIndirizzo(studenteForm.getIndirizzo());
		} catch (RichiestaIscrizioneNonValidaException e3) {
			errors.reject(e3.getTarget(), e3.getMessage());
		}

		// Validazione del campo telefono
		try {
			richiestaIscrizioneService.validaTelefono(studenteForm.getTelefono());
		} catch (RichiestaIscrizioneNonValidaException e4) {
			errors.reject(e4.getTarget(), e4.getMessage());
		}

		// Validazione del campo data nascita
		try {
			richiestaIscrizioneService.validaDataNascita(studenteForm.getDataNascita());
		} catch (RichiestaIscrizioneNonValidaException e5) {
			errors.reject(e5.getTarget(), e5.getMessage());
		}

		// Validazione del campo sesso
		try {
			richiestaIscrizioneService.validaSesso(studenteForm.getSesso());
		} catch (RichiestaIscrizioneNonValidaException e6) {
			errors.reject(e6.getTarget(), e6.getMessage());
		}

		// Validazione del campo matricola
		try {
			richiestaIscrizioneService.validaMatricola(studenteForm.getMatricola());
		} catch (RichiestaIscrizioneNonValidaException e7) {
			errors.reject(e7.getTarget(), e7.getMessage());
		}

		// Validazione del campo email
		try {
			richiestaIscrizioneService.validaEmail(studenteForm.getEmail());
		} catch (RichiestaIscrizioneNonValidaException e8) {
			errors.reject(e8.getTarget(), e8.getMessage());
		}

		// Validazione del campo password
		try {
			richiestaIscrizioneService.validaPassword(studenteForm.getPassword());
		} catch (RichiestaIscrizioneNonValidaException e9) {
			errors.reject(e9.getTarget(), e9.getMessage());
		}

		// Validazione del campo conferma password
		try {
			richiestaIscrizioneService.validaConfermaPassword(studenteForm.getPassword(),
					studenteForm.getConfermaPsw());
		} catch (RichiestaIscrizioneNonValidaException e10) {
			errors.reject(e10.getTarget(), e10.getMessage());
		}

		// Validazione del campo condizioni privacy
		try {
			richiestaIscrizioneService.validaCondizioni(studenteForm.getCondizioni());
		} catch (RichiestaIscrizioneNonValidaException e11) {
			errors.reject(e11.getTarget(), e11.getMessage());
		}
	}
}
