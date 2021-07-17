package it.unisa.di.is.gc1.ify.web;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.convenzioni.Azienda;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendale;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamento;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoService;
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
 * Controller per la gestione delle richieste di convenzionamento
 * @author Alessia Natale
 */

@Controller
public class ConvenzioneController {
	
	@Autowired
	private RichiestaConvenzionamentoService richiestaConvenzionamentoService;
	
	@Autowired
	private ConvenzioneFormValidator convenzioneFormValidator;
	
	@Autowired
	private UtenzaService utenzaService;
	
	/**
	 * Metodo per inviare una richiesta di convenzionamento
	 * 
	 * @param convenzioneForm
	 * @param result
	 * @param redirectAttribute
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/richiestaConvenzionamento", method = RequestMethod.POST)
	public String invioRichiestaConvenzionamento(@ModelAttribute("convenzioneForm") ConvenzioneForm convenzioneForm,
			BindingResult result, RedirectAttributes redirectAttribute, Model model) {
		
		convenzioneFormValidator.validate(convenzioneForm, result);
		
		if (result.hasErrors()) {
			// se ci sono errori il metodo controller setta tutti i parametri

			redirectAttribute.addFlashAttribute("convenzioneForm", convenzioneForm);

			for (ObjectError x : result.getGlobalErrors()) {
				redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
			}

			return "redirect:/iscrizioneAzienda";
		}
		
		Azienda azienda = new Azienda();
		azienda.setRagioneSociale(convenzioneForm.getRagioneSociale());
		azienda.setSede(convenzioneForm.getSede());
		azienda.setPartitaIva(convenzioneForm.getpIva());
		azienda.setSettore(convenzioneForm.getSettore());
		azienda.setDescrizione(convenzioneForm.getDescrizione());
		
		DelegatoAziendale delegatoAziendale = new DelegatoAziendale();
		delegatoAziendale.setNome(convenzioneForm.getNome());
		delegatoAziendale.setCognome(convenzioneForm.getCognome());
		delegatoAziendale.setIndirizzo(convenzioneForm.getIndirizzo());
		delegatoAziendale.setSesso(convenzioneForm.getSesso());
		delegatoAziendale.setRuolo(convenzioneForm.getRuolo());
		delegatoAziendale.setEmail(convenzioneForm.getEmail());
		delegatoAziendale.setPassword(convenzioneForm.getPassword());
		delegatoAziendale.setAzienda(azienda);
		
		RichiestaConvenzionamento richiestaConvenzionamento = new RichiestaConvenzionamento(RichiestaConvenzionamento.IN_ATTESA, azienda, delegatoAziendale);
		
		try {
			richiestaConvenzionamentoService.salvaRichiestaConvenzionamento(richiestaConvenzionamento);
		} catch (Exception e) {
			return "redirect:/";
		}
		
		redirectAttribute.addFlashAttribute("successoConvenzionamento", "La sua richiesta di convenzionamento ha avuto successo.");
		return "redirect:/";
	}
	
	/**
	 * Metodo per visualizzare la lista delle richieste di convenzionamento in attesa
	 * 
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaRichiesteConvenzionamento", method = RequestMethod.GET)
	public String visualizzaRichiesteConvenzionamento(Model model) {
		Utente utente = utenzaService.getUtenteAutenticato();

		if (utente instanceof ResponsabileUfficioTirocini) {

			try {
				List<RichiestaConvenzionamento> richiesteConvenzionamento = richiestaConvenzionamentoService.visualizzaRichiesteConvenzionamentoDettagli();
				model.addAttribute("richiesteConvenzionamento", richiesteConvenzionamento);
			} catch (OperazioneNonAutorizzataException e) {
				System.out.println(e.getMessage());
				return "redirect:/";
			}

			return "visualizzaRichiesteConvenzionamentoUfficio";
		} else
			return "redirect:/";
	}
	
	/**
	 * Metodo per visualizzare la lista delle aziende convenzionate
	 * 
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaAziendeConvenzionate", method = RequestMethod.GET)
	public String visualizzaAziendeConvenzionate(Model model) {
	
		List<Azienda> aziendeConvenzionate = richiestaConvenzionamentoService.visualizzaAziendeConvenzionate();
		model.addAttribute("aziendeConvenzionate", aziendeConvenzionate);
		return "visualizzaAziendeConvenzionate";
	}
	
	/**
	 * Metodo per visualizzare la lista delle aziende convenzionate da parte dello studente
	 * 
	 * @param model
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/visualizzaAziendeConvenzionateStudente", method = RequestMethod.GET)
	public String visualizzaAziendeConvenzionateStudente(Model model) {
	
		Utente utente=utenzaService.getUtenteAutenticato();
		if(utente instanceof Studente) {
			List<Azienda> aziendeConvenzionate = richiestaConvenzionamentoService.visualizzaAziendeConvenzionate();
			model.addAttribute("aziendeConvenzionate", aziendeConvenzionate);
			return "visualizzaAziendeConvenzionateStudente";
		} else {
			return "redirect:/";
		}

		
	}
	
	/**
	 * Metodo per visualizzare i dettagli delle aziende convenzionate con i relativi dettagli del delgato aziendale
	 * 
	 * @param pIva
	 * @param redirectAttribute
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/dettagliAzienda", method = RequestMethod.POST)
	public String dettagliAziendaConvenzionata(@RequestParam String pIva, RedirectAttributes redirectAttribute) {
	
		Azienda azienda = richiestaConvenzionamentoService.getAziendaFromPIva(pIva);
		DelegatoAziendale delegatoAziendale = richiestaConvenzionamentoService.getDelegatoFromAziendaPIva(pIva);
		
		redirectAttribute.addFlashAttribute("AziendaPerDettagli", azienda);
		redirectAttribute.addFlashAttribute("DelegatoPerDettagli", delegatoAziendale);

		return "redirect:/visualizzaAziendeConvenzionate";
	}
	
	/**
	 * Metodo per visualizzare i dettagli delle aziende convenzionate con i relativi dettagli del delgato aziendale
	 * da parte dello studente
	 * 
	 * @param pIva
	 * @param redirectAttribute
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/dettagliAziendaStudente", method = RequestMethod.POST)
	public String dettagliAziendaConvenzionataStudente(@RequestParam String pIva, RedirectAttributes redirectAttribute) {
	
		Azienda azienda = richiestaConvenzionamentoService.getAziendaFromPIva(pIva);
		DelegatoAziendale delegatoAziendale = richiestaConvenzionamentoService.getDelegatoFromAziendaPIva(pIva);
		
		redirectAttribute.addFlashAttribute("AziendaPerDettagli", azienda);
		redirectAttribute.addFlashAttribute("DelegatoPerDettagli", delegatoAziendale);

		return "redirect:/visualizzaAziendeConvenzionateStudente";
	}
	
	/**
	 * Metodo per accettare una richiesta di convenzionamento in attesa
	 * 
	 * @param model
	 * @param id
	 * @param redirectAttribute
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/accettaRichiestaConvenzionamento", method = RequestMethod.POST)
	public String accettaRichiestaConvenzionamento(@RequestParam("idRichiesta") long id, Model model, RedirectAttributes redirectAttribute) {

		RichiestaConvenzionamento richiestaConvenzionamento;
		try {
			richiestaConvenzionamento = richiestaConvenzionamentoService.accettaRichiestaConvenzionamento(id);
			model.addAttribute("richiestaConvenzionamentoAccettata", richiestaConvenzionamento);
		} catch (OperazioneNonAutorizzataException e) {
			return "redirect:/";
		}
		
		redirectAttribute.addFlashAttribute("message", "La richiesta dell'azienda " + richiestaConvenzionamento.getAzienda().getRagioneSociale() 
				+ " è stata accettata con successo");
		return "redirect:/visualizzaRichiesteConvenzionamento";
	}
	
	/**
	 * Metodo per rifiutare una richiesta di convenzionamento in attesa
	 * 
	 * @param model
	 * @param id
	 * @param redirectAttribute
	 * @return String stringa che rapprestenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/rifiutaRichiestaConvenzionamento", method = RequestMethod.POST)
	public String rifiutaRichiestaConvenzionamento(@RequestParam("idRichiesta") long id, Model model, RedirectAttributes redirectAttribute) {

		RichiestaConvenzionamento richiestaConvenzionamento;
		try {
			richiestaConvenzionamento = richiestaConvenzionamentoService.rifiutaRichiestaConvenzionamento(id);
			model.addAttribute("richiestaConvenzionamentoRifiutata", richiestaConvenzionamento);
		} catch (OperazioneNonAutorizzataException e) {
			return "redirect:/";
		}
		
		redirectAttribute.addFlashAttribute("message", "La richiesta dell'azienda " + richiestaConvenzionamento.getAzienda().getRagioneSociale() 
				+ " è stata rifiutata con successo");
		return "redirect:/visualizzaRichiesteConvenzionamento";
	}
	
	/**
	 * Metodo per visualizzare il form della richiesta di convenzionamento
	 * 
	 * @param model
	 * @return String stringa che rappresenta la pagina da visualizzare
	 */
	@RequestMapping(value = "/iscrizioneAzienda", method = RequestMethod.GET)
	public String iscrizioneAzienda(Model model) {

		return "RichiestaConvenzionamento";
	}

}
