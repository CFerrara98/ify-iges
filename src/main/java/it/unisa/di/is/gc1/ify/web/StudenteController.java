package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizione;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneService;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import it.unisa.di.is.gc1.ify.utenza.Utente;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller per la gestione delle richieste di iscrizione
 * @author Giusy Castaldo Alessia Natale
 *
 */
@Controller
public class StudenteController {

	@Autowired
	private RichiestaIscrizioneService richiestaIscrizioneService;

	@Autowired
	private StudenteFormValidator studenteFormValidator;

	@Autowired
	private UtenzaService utenzaService;

	/**
	 * Metodo per inviare una richiesta di iscrizione
	 * 
	 * @param studenteForm
	 * @param result
	 * @param redirectAttribute
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/richiestaIscrizione", method = RequestMethod.POST)
	public String invioRichiestaIscrizione(@ModelAttribute("studenteForm") StudenteForm studenteForm,
			BindingResult result, RedirectAttributes redirectAttribute, Model model) {

		studenteFormValidator.validate(studenteForm, result);
		if (result.hasErrors()) {
			// se ci sono errori il metodo controller setta tutti i parametri

			redirectAttribute.addFlashAttribute("studenteForm", studenteForm);

			for (ObjectError x : result.getGlobalErrors()) {
				redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
				System.out.println(x.getCode());
			}

			return "redirect:/iscrizioneStudente";
		}

		Studente studente = new Studente();
		studente.setNome(studenteForm.getNome());
		studente.setCognome(studenteForm.getCognome());
		studente.setIndirizzo(studenteForm.getIndirizzo());
		studente.setTelefono(studenteForm.getTelefono());
		studente.setDataNascita(studenteForm.getDataNascita());
		studente.setSesso(studenteForm.getSesso());
		studente.setMatricola(studenteForm.getMatricola());
		studente.setEmail(studenteForm.getEmail());
		studente.setPassword(studenteForm.getPassword());
		RichiestaIscrizione richiestaIscrizione=new RichiestaIscrizione();
		richiestaIscrizione.setStudente(studente);
		richiestaIscrizione.setStato(RichiestaIscrizione.IN_ATTESA);
		try {
			richiestaIscrizioneService.salvaRichiestaIscrizione(richiestaIscrizione);
		} catch (Exception e) {
			return "redirect:/";
		}

		redirectAttribute.addFlashAttribute("successoIscrizione", "La sua richiesta d'iscrizione ha avuto successo.");
		return "redirect:/";
	}

	/**
	 * Metodo per visualizzare la lista delle richieste di iscrizione in attesa
	 * 
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaRichiesteIscrizione", method = RequestMethod.GET)
	public String visualizzaRichiesteIscrizione(Model model) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (utente instanceof ResponsabileUfficioTirocini) {

			try {
				List<RichiestaIscrizione> richiesteIscrizione = richiestaIscrizioneService
						.visualizzaRichiesteIscrizioneDettagli();
				model.addAttribute("richiesteIscrizione", richiesteIscrizione);
			} catch (OperazioneNonAutorizzataException e) {
				System.out.println(e.getMessage());
				return "redirect:/";
			}

			return "visualizzaRichiesteIscrizioneUfficio";
		} else
			return "redirect:/";
	}

	/**
	 * Metodo per accettare una richiesta di iscrizione in attesa
	 * 
	 * @param model
	 * @param id
	 * @param redirectAttribute
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/accettaRichiestaIscrizione", method = RequestMethod.POST)
	public String accettaRichiestaIscrizione(@RequestParam("idRichiesta") long id, Model model, RedirectAttributes redirectAttribute) {

		RichiestaIscrizione richiestaIscrizione;
		try {
			richiestaIscrizione = richiestaIscrizioneService.accettaRichiestaIscrizione(id);
			model.addAttribute("richiestaAccettata", richiestaIscrizione);
		} catch (OperazioneNonAutorizzataException e) {
			System.out.println(e.getMessage());
			
			return "redirect:/";
		}
		
		redirectAttribute.addFlashAttribute("message", "La richiesta dello studente " + richiestaIscrizione.getStudente().getNome() + 
				" " + richiestaIscrizione.getStudente().getCognome() + " è stata accettata con successo");
		return "redirect:/visualizzaRichiesteIscrizione";
	}

	/**
	 * Metodo per rifiutare una richiesta di iscrizione in attesa
	 * 
	 * @param model
	 * @param id
	 * @param redirectAttribute
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/rifiutaRichiestaIscrizione", method = RequestMethod.POST)
	public String rifiutaRichiestaIscrizione(@RequestParam("idRichiesta") long id, Model model, RedirectAttributes redirectAttribute) {

		RichiestaIscrizione richiestaIscrizione;
		try {
			richiestaIscrizione = richiestaIscrizioneService.rifiutaRichiestaIscrizione(id);
			model.addAttribute("richiestaRifiutata", richiestaIscrizione);
		} catch (OperazioneNonAutorizzataException e) {
			System.out.println(e.getMessage());
			
			return "redirect:/";
		}
		
		redirectAttribute.addFlashAttribute("message", "La richiesta dello studente " + richiestaIscrizione.getStudente().getNome() + 
				" " + richiestaIscrizione.getStudente().getCognome() + " è stata rifiutata con successo");
		return "redirect:/visualizzaRichiesteIscrizione";
	}
	
	/**
	 * Metodo per visualizzare la dashboard del responsabile dell'ufficio tirocini
	 * 
	 * @param model
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/responsabileDashboard", method = RequestMethod.GET)
	public String dashboardResponsabile(Model model) {

		return "responsabileDashboard";
	}
	
	/**
	 * Metodo per visualizzare il form della richiesta di iscrizione
	 * 
	 * @param model
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/iscrizioneStudente", method = RequestMethod.GET)
	public String iscrizioneStudente(Model model) {

		return "RichiestaIscrizione";
	}

	

}
