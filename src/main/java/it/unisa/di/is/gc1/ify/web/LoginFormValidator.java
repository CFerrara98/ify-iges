package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.utenza.MailNonEsistenteException;
import it.unisa.di.is.gc1.ify.utenza.MailNonValidaException;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link LoginForm} tramite i servizi
 * offerti da {@link LoginService}. Il controllo effettuato rigurda la 
 * validità di alcuni campi definiti nell'entità Utente.
 * 
 * @author Alessia Natale, Giacomo Izzo
 *
 */

@Component
public class LoginFormValidator implements Validator {
	
	@Autowired
	private UtenzaService utenzaService;

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.isAssignableFrom(clazz);
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
		LoginForm loginForm = (LoginForm) target;
		
		//Validazione del campo email
		try {
			utenzaService.validaMail(loginForm.getEmail());
		} catch(MailNonValidaException e1) {
			errors.reject("errore", e1.getMessage());
		} catch(MailNonEsistenteException e2) {
			errors.reject("errore", e2.getMessage());
		}
	}
	
	

}
