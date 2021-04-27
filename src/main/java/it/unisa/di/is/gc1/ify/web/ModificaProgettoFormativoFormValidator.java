package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoNonValidoException;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link ModificaProgettoFormativoForm} tramite i servizi
 * offerti da {@link ProgettoFormativoService}. Il controllo effettuato
 * riguarda la validità dei campi definiti nell'entità ProgettoFormativo.
 * 
 * @see ModificaProgettoFormativoForm
 * @see ProgettoFormativoService
 * 
 * @author Coccaro Benedetta
 *
 */
@Component
public class ModificaProgettoFormativoFormValidator implements Validator {

	@Autowired
	private ProgettoFormativoService progettoFormativoService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ModificaProgettoFormativoForm.class.isAssignableFrom(clazz);
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
		ModificaProgettoFormativoForm modificaProgettoFormativoForm = (ModificaProgettoFormativoForm) target;


		// Validazione del campo descrizione
		try {
			progettoFormativoService.validaDescrizione(modificaProgettoFormativoForm.getDescrizione());
		} catch (ProgettoFormativoNonValidoException e2) {
			errors.reject(e2.getTarget(), e2.getMessage());
		}


		// Validazione del campo conoscenze
		try {
			progettoFormativoService.validaConoscenze(modificaProgettoFormativoForm.getConoscenze());
		} catch (ProgettoFormativoNonValidoException e5) {
			errors.reject(e5.getTarget(), e5.getMessage());
		}

		
		// Validazione del campo maxPartecipanti
		try {
			progettoFormativoService.validaMaxPartecipanti(modificaProgettoFormativoForm.getMaxPartecipanti());
		} catch (ProgettoFormativoNonValidoException e5) {
			errors.reject(e5.getTarget(), e5.getMessage());
		}

	}

}
