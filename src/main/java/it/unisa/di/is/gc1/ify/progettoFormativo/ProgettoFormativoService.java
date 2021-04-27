package it.unisa.di.is.gc1.ify.progettoFormativo;

import it.unisa.di.is.gc1.ify.Studente.OperazioneNonAutorizzataException;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendale;
import it.unisa.di.is.gc1.ify.utenza.Utente;
import it.unisa.di.is.gc1.ify.utenza.UtenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * La classe fornisce i metodi per la logica di business per i progetti formativi
 * @author Simone Civale e Carmine Ferrara
 */

@Service
public class ProgettoFormativoService {
	
	@Autowired
	ProgettoFormativoRepository progettoFormativoRepository;
	
	@Autowired
	UtenzaService utenzaService;
	
	/**
	 * Il metodo fornisce la funzionalita' di salvataggio di un progetto formativo
	 * e viene posto nello stato di attivo
	 * @param progettoFormativo e' il progetto formativo che viene messo in attivo
	 * @pre progettoFormativo!=null
	 * @post progettoFormativo!=null
	 * @return progetto formativo 
	 */
	
	@Transactional(rollbackFor = Exception.class)
	public ProgettoFormativo salvaProgettoFormativo(ProgettoFormativo progettoFormativo) {
		
		progettoFormativo.setStato(ProgettoFormativo.ATTIVO);
		progettoFormativo= progettoFormativoRepository.save(progettoFormativo);
		return progettoFormativo;
	}
	
	/**
	 * Il metodo fornisce la funzionalita' di archiviazione di un progetto formativo
	 * 
	 * 
	 * @param idProgettoFormativo e' l'id del progetto formativo
	 * @return Oggetto {@link ProgettoFormativo} che rappresenta il progetto formativo
	 * archiviato
	 * @throws OperazioneNonAutorizzataException
	 */
	
	@Transactional(rollbackFor = Exception.class)
	public ProgettoFormativo archiviaProgettoFormativo(Long idProgettoFormativo)
			throws OperazioneNonAutorizzataException {
		
		Utente utente = utenzaService.getUtenteAutenticato();
		
		//Solo il delegato aziendale puo' archiviare un progetto formativo
		if(!(utente instanceof DelegatoAziendale)) {
			throw new OperazioneNonAutorizzataException();
		}
		
		
		DelegatoAziendale delegatoAziendale=(DelegatoAziendale) utente;
		

		ProgettoFormativo progettoFormativo = progettoFormativoRepository.findById(idProgettoFormativo).orElse(null);
	
		//Un progetto formativo di un'azienda puo' essere archiviato solo dal delegato aziendale dell'azienda stessa
		if(!(delegatoAziendale.getAzienda().getpIva().equals(progettoFormativo.getAzienda().getpIva()))) {
			throw new OperazioneNonAutorizzataException();
		}
		
		if(!progettoFormativo.getStato().equals(ProgettoFormativo.ATTIVO)) {
			throw new OperazioneNonAutorizzataException("Impossibile archiviare questo progetto");
		}
		
		progettoFormativo.setStato(ProgettoFormativo.ARCHIVIATO);
		progettoFormativo = progettoFormativoRepository.save(progettoFormativo);
		
		return progettoFormativo;
	}
	
	/**
	 * Il metodo fornisce la funzionalita' di riattivazione di un progetto formativo
	 * 
	 * 
	 * @param idProgettoFormativo e' l'id del progetto formativo
	 * @return Oggetto {@link ProgettoFormativo} che rappresenta il progetto formativo
	 * riattivato
	 * @throws OperazioneNonAutorizzataException
	 */
	
	@Transactional(rollbackFor = Exception.class)
	public ProgettoFormativo riattivazioneProgettoFormativo(Long idProgettoFormativo)
			throws OperazioneNonAutorizzataException {
		
		Utente utente = utenzaService.getUtenteAutenticato();
		
		//Solo il delegato aziendale puo' riattivare un progetto formativo
		if(!(utente instanceof DelegatoAziendale)) {
			throw new OperazioneNonAutorizzataException();
		}

		DelegatoAziendale delegatoAziendale=(DelegatoAziendale) utente;
		
		ProgettoFormativo progettoFormativo = progettoFormativoRepository.findById(idProgettoFormativo).orElse(null);
	
		//Un progetto formativo di un'azienda puo' essere archiviato solo dal delegato aziendale dell'azienda stessa
		if(!(delegatoAziendale.getAzienda().getpIva().equals(progettoFormativo.getAzienda().getpIva()))) {
			throw new OperazioneNonAutorizzataException();
		}
		
		if(!progettoFormativo.getStato().equals(ProgettoFormativo.ARCHIVIATO)) {
			throw new OperazioneNonAutorizzataException("Impossibile riattivare questo progetto");
		}
		
		progettoFormativo.setStato(ProgettoFormativo.ATTIVO);
		progettoFormativo = progettoFormativoRepository.save(progettoFormativo);
		
		return progettoFormativo;
	}
	
	/** Il metodo fornisce la funzionalita' di visualizzazione dei progetti formativi
	 * attivi
	 * 
	 * @param pIva
	 * @return Lista di {@link ProgettoFormativo} che rappresenta la lista dei
	 *         progetti formativi attivi <b>Può essere vuota</b> se nel database non
	 *         sono presenti progetti formativi attivi di quella determinata azienda
	 * @throws OperazioneNonAutorizzataException
	 */	
	@Transactional(rollbackFor = Exception.class)
	public List<ProgettoFormativo> visualizzaProgettiFormativiAttiviByAzienda(String pIva) 
			throws OperazioneNonAutorizzataException {
		
		List<ProgettoFormativo> progettiFormativi = progettoFormativoRepository.findAllByAziendaPIvaAndStato(pIva, ProgettoFormativo.ATTIVO);

		return progettiFormativi;
	}
	
	/** Il metodo fornisce la funzionalita' di visualizzazione dei progetti formativi
	 * archiviati
	 * 
	 * @param pIva
	 * @return Lista di {@link ProgettoFormativo} che rappresenta la lista dei
	 *         progetti formativi archiviati <b>Può essere vuota</b> se nel database non
	 *         sono presenti progetti formativi archiviati di quella determinata azienda
	 * @throws OperazioneNonAutorizzataException
	 */	
	@Transactional(rollbackFor = Exception.class)
	public List<ProgettoFormativo> visualizzaProgettiFormativiArchiviatiByAzienda(String pIva) 
			throws OperazioneNonAutorizzataException {
		
		Utente utente = utenzaService.getUtenteAutenticato();
		
		//Solo il delegato aziendale puo' visualizzare un progetto formativo archiviato
		if(!(utente instanceof DelegatoAziendale)) {
			throw new OperazioneNonAutorizzataException();
		}
		DelegatoAziendale delegatoAziendale=(DelegatoAziendale) utente;
		//Un progetto formativo di un'azienda puo' essere visualizzato solo dal delegato aziendale dell'azienda stessa
		if(!(delegatoAziendale.getAzienda().getpIva().equals(pIva))) {
			throw new OperazioneNonAutorizzataException();
		}
		List<ProgettoFormativo> progettiFormativi = progettoFormativoRepository.findAllByAziendaPIvaAndStato(pIva, ProgettoFormativo.ARCHIVIATO);

		return progettiFormativi;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public ProgettoFormativo cercaProgettoPerId(Long id) {
		return progettoFormativoRepository.findById(id).orElse(null);
	}
	
	/**
	 * Il metodo fornisce la funzionalità di modifica di un progetto formativo
	 * 
	 * 
	 * @param idProgettoFormativo e' l'id del progetto formativo
	 * @param descrizione e' la descrizione del progetto formativo
	 * @param conoscenze e' le conoscenze da avere per partecipare al progetto formativo
	 * @param numPartecipanti e' il numero massimo di partecipanti
	 * @return Oggetto {@link ProgettoFormativo} che rappresenta il progetto formativo
	 * da modificare
	 * @throws OperazioneNonAutorizzataException
	 */
	@Transactional(rollbackFor = Exception.class)
	public ProgettoFormativo modificaProgettoFormativo(Long idProgettoFormativo, String descrizione, String conoscenze, int numPartecipanti)
			throws OperazioneNonAutorizzataException {
		
		Utente utente = utenzaService.getUtenteAutenticato();
		
		//Solo il delegato aziendale puo' modificare un progetto formativo
		if(!(utente instanceof DelegatoAziendale)) {
			throw new OperazioneNonAutorizzataException();
		}

		DelegatoAziendale delegatoAziendale=(DelegatoAziendale) utente;
		
		ProgettoFormativo progettoFormativo = progettoFormativoRepository.findById(idProgettoFormativo).orElse(null);
	
		//Un progetto formativo di un'azienda puo' essere archiviato solo dal delegato aziendale dell'azienda stessa
		if(!(delegatoAziendale.getAzienda().getpIva().equals(progettoFormativo.getAzienda().getpIva()))) {
			throw new OperazioneNonAutorizzataException();
		}
		
		progettoFormativo.setDescrizione(descrizione);
		progettoFormativo.setConoscenze(conoscenze);
		progettoFormativo.setMax_partecipanti(numPartecipanti);
		progettoFormativo = progettoFormativoRepository.save(progettoFormativo);
		
		return progettoFormativo;
	}
	
	/**
	 * Il metodo fornisce i controlli di validazione del parametro nome di un generico progetto formativo
	 * @param nome
	 * @return nome
	 * @throws ProgettoFormativoNonValidoException
	 */
	public String validaNome(String nome) throws ProgettoFormativoNonValidoException {
	
		if (nome == null)
			throw new ProgettoFormativoNonValidoException("NomeError", "Il campo nome non può essere nullo.");

		if (nome.length() < ProgettoFormativo.MIN_LUNGHEZZA_CAMPO)
			throw new ProgettoFormativoNonValidoException("NomeError", "Il campo nome deve contenere almeno 2 caratteri");

		if (nome.length() > ProgettoFormativo.MAX_LUNGHEZZA_CAMPO)
			throw new ProgettoFormativoNonValidoException("NomeError", "Il campo nome deve contenere massimo 255 caratteri");

		if (!nome.matches(ProgettoFormativo.NOME_PATTERN))
			throw new ProgettoFormativoNonValidoException("NomeError", "Il campo nome deve contenere soltanto caratteri alfanumerici e di punteggiatura");
		return nome;
	}
	
	/**
	 * Il metodo fornisce i controlli di validazione del parametro descrizione di un generico progetto 
	 * formativo
	 * @param descrizione
	 * @return descrizione
	 * @throws ProgettoFormativoNonValidoException
	 */
	public String validaDescrizione(String descrizione) throws ProgettoFormativoNonValidoException {
	
		if (descrizione == null)
			throw new ProgettoFormativoNonValidoException("DescrizioneError", "Il campo descrizione non può essere nullo.");

		if (descrizione.length() < ProgettoFormativo.MIN_LUNGHEZZA_DESCRIZIONE)
			throw new ProgettoFormativoNonValidoException("DescrizioneError", "Il campo descrizione deve contenere almeno 2 caratteri");

		if (descrizione.length() > ProgettoFormativo.MAX_LUNGHEZZA_DESCRIZIONE)
			throw new ProgettoFormativoNonValidoException("DescrizioneError", "Il campo descrizione deve contenere massimo 800 caratteri");

		return descrizione;
	}

	
	/**
	 * Il metodo fornisce i controlli di validazione del parametro ambito di un generico progetto 
	 * formativo
	 * @param ambito
	 * @return ambito
	 * @throws ProgettoFormativoNonValidoException
	 */
	public String validaAmbito(String ambito) throws ProgettoFormativoNonValidoException {
	
		if (ambito == null)
			throw new ProgettoFormativoNonValidoException("AmbitoError", "Il campo ambito non può essere nullo.");

		if (ambito.length() < ProgettoFormativo.MIN_LUNGHEZZA_CAMPO)
			throw new ProgettoFormativoNonValidoException("AmbitoError", "Il campo ambito deve contenere almeno 2 caratteri");

		if (ambito.length() > ProgettoFormativo.MAX_LUNGHEZZA_CAMPO)
			throw new ProgettoFormativoNonValidoException("AmbitoError", "Il campo ambito deve contenere massimo 255 caratteri");

		if (!ambito.matches(ProgettoFormativo.AMBITO_PATTERN))
			throw new ProgettoFormativoNonValidoException("AmbitoError", "Il campo ambito deve contenere soltanto caratteri alfanumerici e di punteggiatura");
		
		return ambito;
	}
	
	/**
	 * Il metodo fornisce i controlli di validazione del parametro attività di un generico progetto 
	 * formativo
	 * @param attivita
	 * @return attivita
	 * @throws ProgettoFormativoNonValidoException
	 */
	public String validaAttivita(String attivita) throws ProgettoFormativoNonValidoException {
	
		if (attivita == null)
			throw new ProgettoFormativoNonValidoException("AttivitaError", "Il campo attività non può essere nullo.");

		if (attivita.length() < ProgettoFormativo.MIN_LUNGHEZZA_ATTIVITA)
			throw new ProgettoFormativoNonValidoException("AttivitaError", "Il campo attività deve contenere almeno 2 caratteri");

		if (attivita.length() > ProgettoFormativo.MAX_LUNGHEZZA_ATTIVITA)
			throw new ProgettoFormativoNonValidoException("AttivitaError", "Il campo attività deve contenere massimo 500 caratteri");

		return attivita;
	}
	
	/**
	 * Il metodo fornisce i controlli di validazione del parametro conoscenze di un generico progetto 
	 * formativo
	 * @param conoscenze
	 * @return conoscenze
	 * @throws ProgettoFormativoNonValidoException
	 */
	public String validaConoscenze(String conoscenze) throws ProgettoFormativoNonValidoException {
	
		if (conoscenze == null)
			throw new ProgettoFormativoNonValidoException("ConoscenzeError", "Il campo conoscenze non può essere nullo.");

		if (conoscenze.length() < ProgettoFormativo.MIN_LUNGHEZZA_CONOSCENZE)
			throw new ProgettoFormativoNonValidoException("ConoscenzeError", "Il campo conoscenze deve contenere almeno 2 caratteri");

		if (conoscenze.length() > ProgettoFormativo.MAX_LUNGHEZZA_CONOSCENZE)
			throw new ProgettoFormativoNonValidoException("ConoscenzeError", "Il campo conoscenze deve contenere massimo 500 caratteri");
		
		return conoscenze;
	}
	
	/**
	 * Il metodo fornisce i controlli di validazione del parametro maxPartecipanti di un generico progetto 
	 * formativo
	 * @param maxPartecipanti
	 * @return maxPartecipanti
	 * @throws ProgettoFormativoNonValidoException
	 */
	public String validaMaxPartecipanti(String maxPartecipanti) throws ProgettoFormativoNonValidoException {
		if (maxPartecipanti == null || maxPartecipanti.equals(""))
			throw new ProgettoFormativoNonValidoException("MaxPartecipantiError", "Il campo Max partecipanti non può essere nullo.");
		
		if (!maxPartecipanti.matches(ProgettoFormativo.MAX_PARTECIPANTI_PATTERN))
			throw new ProgettoFormativoNonValidoException("MaxPartecipantiError", "Il campo Max Partecipanti deve contenere soltanto numeri");
		
		if (Integer.parseInt(maxPartecipanti) < ProgettoFormativo.MIN_VAL_MAX_PARTECIPANTI)
			throw new ProgettoFormativoNonValidoException("MaxPartecipantiError", "Il campo Max partecipanti deve contenere almeno 1 numero");

		if (Integer.parseInt(maxPartecipanti) > ProgettoFormativo.MAX_VAL_MAX_PARTECIPANTI)
			throw new ProgettoFormativoNonValidoException("MaxPartecipantiError", "Il campo Max Partecipanti deve contenere massimo 3 numeri");

		
		return maxPartecipanti;
	}
}
