package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutor;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizione;
import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneService;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendale;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamento;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoService;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import it.unisa.di.is.gc1.ify.utenza.PasswordNonValidaException;
import it.unisa.di.is.gc1.ify.utenza.Utente;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controller per la gestione dell'utenza
 * 
 * @author Carmine Ferrara, Giacomo Izzo, Alessia Natale
 */
@Controller
public class UtenzaController {

	@Autowired
	private UtenzaService utenzaService;

	@Autowired
	private LoginFormValidator loginFormValidator;

	@Autowired
	private RichiestaIscrizioneService richiestaIscrizioneService;

	@Autowired
	private RichiestaConvenzionamentoService richiestaConvenzionamentoService;

	/**
	 * Metodo per effettuare il login
	 * 
	 * @param request
	 * @param loginForm
	 * @param result
	 * @param redirectAttribute
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @ModelAttribute LoginForm loginForm, BindingResult result,
			RedirectAttributes redirectAttribute, Model model) {

		System.out.println(loginForm.getEmail());

		Utente utente = null;

		loginFormValidator.validate(loginForm, result);
		if (result.hasErrors()) {
			// se ci sono errori il metodo controller setta tutti i parametri

			redirectAttribute.addFlashAttribute("EmailError", result.getGlobalError().getDefaultMessage());
			return "redirect:/loginPage";
		}

		try {
			utente = utenzaService.login(loginForm.getEmail(), loginForm.getPassword());
		} catch (PasswordNonValidaException e) {
			redirectAttribute.addFlashAttribute("EmailPrecedente", loginForm.getEmail());
			redirectAttribute.addFlashAttribute("PasswordError", e.getMessage());
			model.addAttribute("utente", utente);
			return "redirect:/loginPage";
		}

		if (utente instanceof Studente) {
			if (richiestaIscrizioneService.getStatoRichiestaByEmail(utente.getEmail())
					.equals(RichiestaIscrizione.ACCETTATA)) {
				request.getSession().setAttribute("email", utente.getEmail());
				return "studenteDashboard";
			} else {
				redirectAttribute.addFlashAttribute("message",
						"Le sue credenziali sono ancora in attesa di valutazione");
				return "redirect:/loginPage";
			}
		} else if (utente instanceof DelegatoAziendale) {
			if (richiestaConvenzionamentoService.getStatoRichiestaByEmail(utente.getEmail())
					.equals(RichiestaConvenzionamento.ACCETTATA)) {
				request.getSession().setAttribute("email", utente.getEmail());
				return "delegatoDashboard";
			} else {
				redirectAttribute.addFlashAttribute("message",
						"Le sue credenziali sono ancora in attesa di valutazione");
				return "redirect:/loginPage";
			}
		} else if (utente instanceof ResponsabileUfficioTirocini) {
			request.getSession().setAttribute("email", utente.getEmail());
			return "responsabileDashboard";
		} else if (utente instanceof DocenteTutor) {
			request.getSession().setAttribute("email", utente.getEmail());
			return "docenteDashboard";
		} else {
			return "redirect:/";
		}

	}

	/**
	 * Metodo per effettuare il logout
	 * 
	 * @param request
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		utenzaService.logout();
		request.getSession().removeAttribute("email");
		request.getSession().invalidate();
		return "/homepage";
	}

	/**
	 * Metodo per la visualizzazione dell'homepage o della dashboard
	 * 
	 * @param model
	 * @param session
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String visualizzaHome(HttpSession session, Model model) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (session.getAttribute("email") != null && utente != null) {
			if (utente instanceof Studente) {
				return "studenteDashboard";
			} else if (utente instanceof DelegatoAziendale) {
				return "delegatoDashboard";
			} else if (utente instanceof DocenteTutor) {
				return "docenteDashboard";
			}
			else {
				return "responsabileDashboard";
			}
		}
		return "homepage";
	}

	/**
	 * Metodo per la visualizzazione della pagina di login
	 * 
	 * @param model
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String visualizzaLoginForm(Model model) {

		return "login";
	}

}
