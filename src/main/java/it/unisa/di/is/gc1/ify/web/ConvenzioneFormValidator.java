package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoNonValidaException;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link ConvenzioneForm} tramite i servizi
 * offerti da {@link RichiestaConvenzionamentoService}. Il controllo effettuato
 * riguarda la validità dei campi definiti nell'entità Azienda e DelegatoAziendale.
 * 
 * @see ConvenzioneForm
 * @see RichiestaConvenzionamentoService
 * 
 * @author Geremia Cavezza
 *
 */

@Component
public class ConvenzioneFormValidator implements Validator {

	@Autowired
	private RichiestaConvenzionamentoService richiestaConvenzionamentoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ConvenzioneForm.class.isAssignableFrom(clazz);
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
		ConvenzioneForm convenzioneForm = (ConvenzioneForm) target;
		
		// Validazione del campo nome
		try {
			richiestaConvenzionamentoService.validaNome(convenzioneForm.getNome());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo cognome
		try {
			richiestaConvenzionamentoService.validaCognome(convenzioneForm.getCognome());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}		
		
		// Validazione del campo indirizzo
		try {
			richiestaConvenzionamentoService.validaIndirizzo(convenzioneForm.getIndirizzo());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo sesso
		try {
			richiestaConvenzionamentoService.validaSesso(convenzioneForm.getSesso());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo ruolo
		try {
			richiestaConvenzionamentoService.validaRuolo(convenzioneForm.getRuolo());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo email
		try {
			richiestaConvenzionamentoService.validaEmail(convenzioneForm.getEmail());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo password
		try {
			richiestaConvenzionamentoService.validaPassword(convenzioneForm.getPassword());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo confermaPsw
		try {
			richiestaConvenzionamentoService.validaConfermaPassword(convenzioneForm.getPassword(), convenzioneForm.getConfermaPassword());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo condizioniDelegato
		try {
			richiestaConvenzionamentoService.validaCondizioniDelegato(convenzioneForm.getCondizioniDelegato());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo ragioneSociale
		try {
			richiestaConvenzionamentoService.validaRagioneSociale(convenzioneForm.getRagioneSociale());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo sede
		try {
			richiestaConvenzionamentoService.validaSede(convenzioneForm.getSede());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}		
		
		// Validazione del campo piva
		try {
			richiestaConvenzionamentoService.validaPiva(convenzioneForm.getpIva());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo settore
		try {
			richiestaConvenzionamentoService.validaSettore(convenzioneForm.getSettore());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo descrizione
		try {
			richiestaConvenzionamentoService.validaDescrizione(convenzioneForm.getDescrizione());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
		
		// Validazione del campo condizioniAzienda
		try {
			richiestaConvenzionamentoService.validaCondizioniAzienda(convenzioneForm.getCondizioniAzienda());
		} catch (RichiestaConvenzionamentoNonValidaException e) {
			errors.reject(e.getTarget(), e.getMessage());
		}
	}
}
