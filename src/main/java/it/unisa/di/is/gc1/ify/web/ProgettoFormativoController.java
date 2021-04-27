package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendale;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativo;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoService;
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

import java.time.LocalDate;
import java.util.List;

/**
 * Controller per la gestione dei progetti formativi
 * 
 * @author Simone Civale Carmine Ferrara
 *
 */

@Controller
public class ProgettoFormativoController {

	@Autowired
	UtenzaService utenzaService;

	@Autowired
	ProgettoFormativoService progettoFormativoService;

	@Autowired
	InserimentoProgettoFormativoFormValidator inserimentoProgettoFormativoFormValidator;

	@Autowired
	ModificaProgettoFormativoFormValidator modificaProgettoFormativoFormValidator;

	/**
	 * Metodo per l'inserimento di un progetto formativo
	 * 
	 * @param inserimentoProgettoFormativoForm
	 * @param result
	 * @param redirectAttribute
	 * @param model
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/inserimentoProgettoFormativo", method = RequestMethod.POST)
	public String inserimentoProgettoFormativo(
			@ModelAttribute("InserimentoProgettoFormativoForm") InserimentoProgettoFormativoForm inserimentoProgettoFormativoForm,
			BindingResult result, RedirectAttributes redirectAttribute, Model model) {

		inserimentoProgettoFormativoFormValidator.validate(inserimentoProgettoFormativoForm, result);
		if (result.hasErrors()) {
			// se ci sono errori il metodo controller setta tutti i parametri

			redirectAttribute.addFlashAttribute("nuovoProgettoForm", inserimentoProgettoFormativoForm);

			for (ObjectError x : result.getGlobalErrors()) {
				redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
				System.out.println(x.getCode());
			}

			return "redirect:/nuovoProgettoFormativo";
		}
		Utente utente = utenzaService.getUtenteAutenticato();
		DelegatoAziendale delegatoAziendale = (DelegatoAziendale) utente;

		ProgettoFormativo progettoFormativo = new ProgettoFormativo();
		progettoFormativo.setNome(inserimentoProgettoFormativoForm.getNome());
		progettoFormativo.setDescrizione(inserimentoProgettoFormativoForm.getDescrizione());
		progettoFormativo.setAmbito(inserimentoProgettoFormativoForm.getAmbito());
		progettoFormativo.setAttivita(inserimentoProgettoFormativoForm.getAttivita());
		progettoFormativo.setConoscenze(inserimentoProgettoFormativoForm.getConoscenze());
		progettoFormativo.setMax_partecipanti(Integer.parseInt(inserimentoProgettoFormativoForm.getMaxPartecipanti()));
		progettoFormativo.setData_compilazione(LocalDate.now());
		progettoFormativo.setStato(ProgettoFormativo.ATTIVO);
		progettoFormativo.setAzienda(delegatoAziendale.getAzienda());

		try {
			progettoFormativoService.salvaProgettoFormativo(progettoFormativo);
		} catch (Exception e) {
			return "redirect:/";
		}

		redirectAttribute.addFlashAttribute("successoInserimento",
				"Il progetto formativo è stato inserito con successo.");
		return "redirect:/";
	}

	/**
	 * Metodo per la modifica di un progetto formativo attivo
	 * 
	 * @param modificaProgettoFormativoForm
	 * @param result
	 * @param redirectAttribute
	 * @param model
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/modificaProgettoFormativoAttivo", method = RequestMethod.POST)
	public String modificaProgettoFormativoAttivo(
			@ModelAttribute("ModificaProgettoFormativoForm") ModificaProgettoFormativoForm modificaProgettoFormativoForm,
			BindingResult result, RedirectAttributes redirectAttribute, Model model) {

		modificaProgettoFormativoFormValidator.validate(modificaProgettoFormativoForm, result);
		if (result.hasErrors()) {
			// se ci sono errori il metodo controller setta tutti i parametri

			redirectAttribute.addFlashAttribute("progettoPerModifica",
					progettoFormativoService.cercaProgettoPerId(modificaProgettoFormativoForm.getId()));

			for (ObjectError x : result.getGlobalErrors()) {
				redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
				System.out.println(x.getCode());
			}

			return "redirect:/progettiFormativiAttivi";
		}

		try {
			progettoFormativoService.modificaProgettoFormativo(modificaProgettoFormativoForm.getId(),
					modificaProgettoFormativoForm.getDescrizione(), modificaProgettoFormativoForm.getConoscenze(),
					Integer.parseInt(modificaProgettoFormativoForm.getMaxPartecipanti()));
		} catch (Exception e) {
			return "redirect:/progettiFormativiAttivi";
		}

		redirectAttribute.addFlashAttribute("message",
				"Il progetto formativo è stato modificato correttamente.");
		return "redirect:/progettiFormativiAttivi";
	}

	/**
	 * Metodo per la modifica di un progetto formativo archiviato
	 * 
	 * @param modificaProgettoFormativoForm
	 * @param result
	 * @param redirectAttribute
	 * @param model
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/modificaProgettoFormativoArchiviato", method = RequestMethod.POST)
	public String modificaProgettoFormativoArchiviato(
			@ModelAttribute("ModificaProgettoFormativoForm") ModificaProgettoFormativoForm modificaProgettoFormativoForm,
			BindingResult result, RedirectAttributes redirectAttribute, Model model) {
		System.out.println(modificaProgettoFormativoForm.getMaxPartecipanti());
		modificaProgettoFormativoFormValidator.validate(modificaProgettoFormativoForm, result);
		if (result.hasErrors()) {
			// se ci sono errori il metodo controller setta tutti i parametri

			redirectAttribute.addFlashAttribute("progettoPerModifica",
					progettoFormativoService.cercaProgettoPerId(modificaProgettoFormativoForm.getId()));

			for (ObjectError x : result.getGlobalErrors()) {
				redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
				System.out.println(x.getCode());
			}

			return "redirect:/progettiFormativiArchiviati";
		}

		try {
			progettoFormativoService.modificaProgettoFormativo(modificaProgettoFormativoForm.getId(),
					modificaProgettoFormativoForm.getDescrizione(), modificaProgettoFormativoForm.getConoscenze(),
					Integer.parseInt(modificaProgettoFormativoForm.getMaxPartecipanti()));
		} catch (Exception e) {
			return "redirect:/progettiFormativiArchiviati";
		}

		redirectAttribute.addFlashAttribute("message", "Il progetto formativo è stato modificato correttamente.");
		return "redirect:/progettiFormativiArchiviati";
	}

	/**
	 * Metodo per inserire un nuovo progetto formativo
	 * 
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/nuovoProgettoFormativo", method = RequestMethod.GET)
	public String nuovoProgettoFormativo(Model model) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (utente instanceof DelegatoAziendale) {

			return "inserimentoProgettoFormativo";
		} else
			return "redirect:/";
	}

	/**
	 * Metodo per archiviare un progetto formativo attivo
	 * 
	 * @param model
	 * @param id
	 * @param redirectAttribute
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/archiviaProgettoFormativo", method = RequestMethod.POST)
	public String archiviaProgettoFormativo(@RequestParam("idProgettoFormativo") long id, Model model,
			RedirectAttributes redirectAttribute) {

		ProgettoFormativo progettoFormativo;
		try {
			progettoFormativo = progettoFormativoService.archiviaProgettoFormativo(id);
			model.addAttribute("progettoFormativoArchiviato", progettoFormativo);
		} catch (OperazioneNonAutorizzataException e) {
			System.out.println(e.getMessage());

			return "redirect:/progettiFormativiAttivi";
		}

		redirectAttribute.addFlashAttribute("message",
				"Il progetto formativo " + progettoFormativo.getNome() + " è stato archiviato con successo");
		return "redirect:/progettiFormativiAttivi";
	}

	/**
	 * Metodo per riattivare un progetto formativo archiviato
	 * 
	 * @param model
	 * @param id
	 * @param redirectAttribute
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/riattivaProgettoFormativo", method = RequestMethod.POST)
	public String riattivaProgettoFormativo(@RequestParam("idProgettoFormativo") long id, Model model,
			RedirectAttributes redirectAttribute) {

		ProgettoFormativo progettoFormativo;
		try {
			progettoFormativo = progettoFormativoService.riattivazioneProgettoFormativo(id);
			model.addAttribute("progettoFormativoRiattivato", progettoFormativo);
		} catch (OperazioneNonAutorizzataException e) {
			System.out.println(e.getMessage());

			return "redirect:/progettiFormativiArchiviati";
		}

		redirectAttribute.addFlashAttribute("message",
				"Il progetto formativo " + progettoFormativo.getNome() + " è stato riattivato con successo");
		return "redirect:/progettiFormativiArchiviati";
	}

	/**
	 * Metodo per visualizzare la lista dei progetti formativi attivi
	 * 
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/progettiFormativiAttivi", method = RequestMethod.GET)
	public String visualizzaProgettiFormativiAttivi(Model model) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (utente instanceof DelegatoAziendale) {

			try {
				DelegatoAziendale delegatoAziendale = (DelegatoAziendale) utente;
				List<ProgettoFormativo> progettiFormativi = progettoFormativoService
						.visualizzaProgettiFormativiAttiviByAzienda(delegatoAziendale.getAzienda().getpIva());
				model.addAttribute("progettiFormativiAttivi", progettiFormativi);
			} catch (OperazioneNonAutorizzataException e) {
				System.out.println(e.getMessage());
				return "redirect:/";
			}

			return "visualizzaProgettiFormativiAttivi";
		} else
			return "redirect:/";
	}

	/**
	 * Metodo per visualizzare la lista dei progetti formativi archiviati
	 * 
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/progettiFormativiArchiviati", method = RequestMethod.GET)
	public String visualizzaProgettiFormativiArchiviati(Model model) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (utente instanceof DelegatoAziendale) {

			try {
				DelegatoAziendale delegatoAziendale = (DelegatoAziendale) utente;
				List<ProgettoFormativo> progettiFormativi = progettoFormativoService
						.visualizzaProgettiFormativiArchiviatiByAzienda(delegatoAziendale.getAzienda().getpIva());
				model.addAttribute("progettiFormativiArchiviati", progettiFormativi);
			} catch (OperazioneNonAutorizzataException e) {
				System.out.println(e.getMessage());
				return "redirect:/";
			}

			return "visualizzaProgettiFormativiArchiviati";
		} else
			return "redirect:/";
	}

	/**
	 * Metodo per visualizzare i dettagli dei progetti formativi attivi
	 * 
	 * @param redirectAttribute
	 * @param id
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaDettagliProgettoFormativoAttivo", method = RequestMethod.POST)
	public String visualizzaDettagliProgettoFormativoAttivo(RedirectAttributes redirectAttribute,
			@RequestParam("idProgettoFormativo") long id) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (utente instanceof DelegatoAziendale) {

			ProgettoFormativo progettoFormativo = progettoFormativoService.cercaProgettoPerId(id);
			redirectAttribute.addFlashAttribute("progettoPerDettagli", progettoFormativo);
			return "redirect:/progettiFormativiAttivi";
		} else
			return "/";
	}

	/**
	 * Metodo per visualizzare i dettagli dei progetti formativi archiviati
	 * 
	 * @param redirectAttribute
	 * @param id
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaDettagliProgettoFormativoArchiviato", method = RequestMethod.POST)
	public String visualizzaDettagliProgettoFormativoArchiviato(RedirectAttributes redirectAttribute,
			@RequestParam("idProgettoFormativo") long id) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (utente instanceof DelegatoAziendale) {

			ProgettoFormativo progettoFormativo = progettoFormativoService.cercaProgettoPerId(id);
			redirectAttribute.addFlashAttribute("progettoPerDettagli", progettoFormativo);
			return "redirect:/progettiFormativiArchiviati";
		} else
			return "/";
	}

	/**
	 * Metodo per visualizzare i progetti formativi archiviati
	 * 
	 * @param redirectAttribute
	 * @param id
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaFormModificaProgettoFormativoArchiviato", method = RequestMethod.POST)
	public String visualizzaFormModificaProgettoFormativoArchiviato(RedirectAttributes redirectAttribute,
			@RequestParam("idProgettoFormativo") long id) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (utente instanceof DelegatoAziendale) {

			ProgettoFormativo progettoFormativo = progettoFormativoService.cercaProgettoPerId(id);
			redirectAttribute.addFlashAttribute("progettoPerModifica", progettoFormativo);
			return "redirect:/progettiFormativiArchiviati";
		} else
			return "/";
	}

	/**
	 * Metodo per visualizzare i progetti formativi attivi
	 * 
	 * @param redirectAttribute
	 * @param id
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaFormModificaProgettoFormativoAttivo", method = RequestMethod.POST)
	public String visualizzaFormModificaProgettoFormativoAttivo(RedirectAttributes redirectAttribute,
			@RequestParam("idProgettoFormativo") long id) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (utente instanceof DelegatoAziendale) {

			ProgettoFormativo progettoFormativo = progettoFormativoService.cercaProgettoPerId(id);
			redirectAttribute.addFlashAttribute("progettoPerModifica", progettoFormativo);
			return "redirect:/progettiFormativiAttivi";
		} else
			return "/";
	}

	/**
	 * Metodo per visualizzare i dettagli dei progetti formativi nella vista
	 * generale delle aziende convenzionate
	 * 
	 * @param redirectAttribute
	 * @param id
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaDettagliProgettoFormativoUtente", method = RequestMethod.POST)
	public String visualizzaDettagliProgettoFormativoUtente(RedirectAttributes redirectAttribute,
			@RequestParam("idProgettoFormativo") long id) {

		ProgettoFormativo progettoFormativo = progettoFormativoService.cercaProgettoPerId(id);
		redirectAttribute.addFlashAttribute("progettoPerDettagli", progettoFormativo);
		return "redirect:/visualizzaAziendeConvenzionate";
	}

	/**
	 * Metodo per visualizzare i dettagli dei progetti formativi nella vista della
	 * dashboard dello studente
	 * 
	 * @param redirectAttribute
	 * @param id
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaDettagliProgettoFormativoStudente", method = RequestMethod.POST)
	public String visualizzaDettagliProgettoFormativoStudente(RedirectAttributes redirectAttribute,
			@RequestParam("idProgettoFormativo") long id) {

		Utente utente = utenzaService.getUtenteAutenticato();
		if (utente instanceof Studente) {
			ProgettoFormativo progettoFormativo = progettoFormativoService.cercaProgettoPerId(id);
			redirectAttribute.addFlashAttribute("progettoPerDettagli", progettoFormativo);
			return "redirect:/visualizzaAziendeConvenzionateStudente";
		} else {
			return "redirect:/";
		}
	}
}
