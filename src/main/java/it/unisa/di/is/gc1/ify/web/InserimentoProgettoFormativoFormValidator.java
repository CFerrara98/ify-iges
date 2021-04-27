package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoNonValidoException;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link InserimentoProgettoFormativoForm} tramite i servizi
 * offerti da {@link ProgettoFormativoService}. Il controllo effettuato
 * riguarda la validità dei campi definiti nell'entità ProgettoFormativo.
 * 
 * @see InserimentoProgettoFormativoForm
 * @see ProgettoFormativoService
 * 
 * @author Coccaro Benedetta
 *
 */
@Component
public class InserimentoProgettoFormativoFormValidator implements Validator {

	@Autowired
	private ProgettoFormativoService progettoFormativoService;

	@Override
	public boolean supports(Class<?> clazz) {
		return InserimentoProgettoFormativoForm.class.isAssignableFrom(clazz);
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
		InserimentoProgettoFormativoForm inserimentoProgettoFormativoForm = (InserimentoProgettoFormativoForm) target;

		// Validazione del campo nome
		try {
			progettoFormativoService.validaNome(inserimentoProgettoFormativoForm.getNome());
		} catch (ProgettoFormativoNonValidoException e1) {
			errors.reject(e1.getTarget(), e1.getMessage());
		}

		// Validazione del campo descrizione
		try {
			progettoFormativoService.validaDescrizione(inserimentoProgettoFormativoForm.getDescrizione());
		} catch (ProgettoFormativoNonValidoException e2) {
			errors.reject(e2.getTarget(), e2.getMessage());
		}

		// Validazione del campo ambito
		try {
			progettoFormativoService.validaAmbito(inserimentoProgettoFormativoForm.getAmbito());
		} catch (ProgettoFormativoNonValidoException e3) {
			errors.reject(e3.getTarget(), e3.getMessage());
		}

		// Validazione del campo attività
		try {
			progettoFormativoService.validaAttivita(inserimentoProgettoFormativoForm.getAttivita());
		} catch (ProgettoFormativoNonValidoException e4) {
			errors.reject(e4.getTarget(), e4.getMessage());
		}

		// Validazione del campo conoscenze
		try {
			progettoFormativoService.validaConoscenze(inserimentoProgettoFormativoForm.getConoscenze());
		} catch (ProgettoFormativoNonValidoException e5) {
			errors.reject(e5.getTarget(), e5.getMessage());
		}
		
		// Validazione del campo maxPartecipanti
		try {
			progettoFormativoService.validaMaxPartecipanti(inserimentoProgettoFormativoForm.getMaxPartecipanti());
		} catch (ProgettoFormativoNonValidoException e5) {
			errors.reject(e5.getTarget(), e5.getMessage());
		}

	}

}
