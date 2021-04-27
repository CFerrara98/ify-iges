package it.unisa.di.is.gc1.ify.web;

/**
 * Oggetto utilizzato per mappare i campi di un form di un progetto formativo. Questo oggetto
 * viene passato come parametro al controller dalla dispatcher servlet quando un delegato aziendale 
 * sottomette il modulo di modifica di un progetto formativo.
 * 
 * @author Benedetta Coccaro
 */

public class ModificaProgettoFormativoForm {
	
	/**
	 * Costruttore che crea un oggeto ModificaProgettoFormativoForm vuoto, 
	 * che verra' popolato con i metodi setters.
	 */
	public ModificaProgettoFormativoForm() {
		
	}
	

	/**
	 * Costruttore di un form di modifica progetto con parametri utili nei casi di test.
	 * @param id e' l'id del progetto formativo
	 * @param descrizione e' la descrizione inserita nel form di modifica.
	 * @param conoscenze sono le conoscenze necessarie inserite nel form di modifica.
	 * @param maxPartecipanti e' il numero di partecipanti inseriti nel form di modifica.
	 */
	public ModificaProgettoFormativoForm(Long id, String descrizione, String conoscenze, String maxPartecipanti) {
		super();
		this.setId(id);
		this.descrizione = descrizione;
		this.conoscenze = conoscenze;
		this.maxPartecipanti = maxPartecipanti;
	}

	

	/**
	 * Metodo che ritorna la descrizione del progetto.
	 * @return descrizione e' la descrizione del progetto
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Metodo che setta la descrizione del progetto.
	 * @param descrizione e' la descrizione del progetto
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Metodo che ritorna le conoscenze necessarie del progetto.
	 * @return conoscenze sono le conoscenze necessarie del progetto
	 */
	public String getConoscenze() {
		return conoscenze;
	}

	/**
	 * Metodo che setta le conoscenze necessarie del progetto.
	 * @param conoscenze sono le conoscenze necessarie del progetto
	 */
	public void setConoscenze(String conoscenze) {
		this.conoscenze = conoscenze;
	}

	/**
	 * Metodo che ritorna il massimo numero di partecipanti del progetto.
	 * @return maxPartecipanti e' il massimo numero di partecipanti del progetto
	 */
	public String getMaxPartecipanti() {
		return maxPartecipanti;
	}

	/**
	 * Metodo che setta il massimo numero di partecipanti del progetto.
	 * @param maxPartecipanti e' il massimo numero di partecipanti del progetto
	 */
	public void setMaxPartecipanti(String maxPartecipanti) {
		this.maxPartecipanti = maxPartecipanti;
	}

	/**
	 * Metodo che ritorna l'id del progetto.
	 * @return id e' l'id del progetto
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che setta l'id del progetto.
	 * @param id e' l'id del progetto
	 */
	public void setId(Long id) {
		this.id = id;
	}


	private Long id;
	private String descrizione;
	private String conoscenze;
	private String maxPartecipanti;
	
	
	
	
}
