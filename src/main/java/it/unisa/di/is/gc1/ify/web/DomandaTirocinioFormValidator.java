package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioNonValidaException;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link DomandaTirocinioForm} tramite i servizi
 * offerti da {@link DomandaTirocinioService}. Il controllo effettuato
 * riguarda la validità dei campi definiti nell'entità DomandaTirocinio.
 * 
 * @see DomandaTirocinioForm
 * @see DomandaTirocinioService
 * 
 * @author Benedetta Coccaro
 *
 */

@Component
public class DomandaTirocinioFormValidator implements Validator {

	@Autowired
	private DomandaTirocinioService domandaTirocinioService;

	@Override
	public boolean supports(Class<?> clazz) {
		return DomandaTirocinioForm.class.isAssignableFrom(clazz);
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
		DomandaTirocinioForm domandaTirocinioForm = (DomandaTirocinioForm) target;

		// Validazione del campo conoscenze
		try {
			domandaTirocinioService.validaConoscenze(domandaTirocinioForm.getConoscenze());
		} catch (DomandaTirocinioNonValidaException e1) {
			errors.reject(e1.getTarget(), e1.getMessage());
		}

		// Validazione del campo motivazioni
		try {
			domandaTirocinioService.validaMotivazioni(domandaTirocinioForm.getMotivazioni());
		} catch (DomandaTirocinioNonValidaException e2) {
			errors.reject(e2.getTarget(), e2.getMessage());
		}

		// Validazione del campo dataInizio
		try {
			domandaTirocinioService.validaDataInizio(domandaTirocinioForm.getDataInizio());
		} catch (DomandaTirocinioNonValidaException e3) {
			errors.reject(e3.getTarget(), e3.getMessage());
		}
		
		// Validazione del campo dataFine
		try {
			domandaTirocinioService.validaDataFine(domandaTirocinioForm.getDataInizio(), domandaTirocinioForm.getDataFine());
		} catch (DomandaTirocinioNonValidaException e4) {
			errors.reject(e4.getTarget(), e4.getMessage());
		}
		
		// Validazione del campo numeroCFU
		try {
			domandaTirocinioService.cfu(domandaTirocinioForm.getNumeroCFU());
		} catch (DomandaTirocinioNonValidaException e5) {
			errors.reject(e5.getTarget(), e5.getMessage());
		}

		// Validazione del campo condizioni privacy
		try {
			domandaTirocinioService.validaCondizioni(domandaTirocinioForm.getCondizioni());
		} catch (DomandaTirocinioNonValidaException e6) {
			errors.reject(e6.getTarget(), e6.getMessage());
		}
	}

}

