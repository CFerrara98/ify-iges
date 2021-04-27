package it.unisa.di.is.gc1.ify.web;

/**
 * Oggetto utilizzato per mappare i campi di un form di un progetto formativo. Questo oggetto
 * viene passato come parametro al controller dalla dispatcher servlet quando un delegato aziendale 
 * sottomette il modulo di inserimento di un progetto formativo.
 * 
 * @author Benedetta Coccaro
 */

public class InserimentoProgettoFormativoForm {

	public InserimentoProgettoFormativoForm() {
		
	}
	
	
	/**
	 * Costruttore di un oggetto InserimentoProgettoFormativoForm
	 * @param nome del progetto formativo
	 * @param descrizione descrizione del progetto formativo
	 * @param ambito ambito del progetto formativo
	 * @param attivita attivita del progetto formativo
	 * @param conoscenze conoscenze del progetto formativo
	 * @param maxPartecipanti massimo numero di partecipanti progetto formativo
	 */
	public InserimentoProgettoFormativoForm(String nome, String descrizione, String ambito, String attivita,
			String conoscenze, String maxPartecipanti) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.ambito = ambito;
		this.attivita = attivita;
		this.conoscenze = conoscenze;
		this.maxPartecipanti = maxPartecipanti;
	}

	
	/**
	 * Metodo che ritorna il nome del progetto formativo
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * metodo che setta il nome del progetto formativo
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo che ritorna la descrizione del progetto formativo
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * metodo che setta la descrizione del progetto formativo
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Metodo che ritorna l'ambito del progetto formativo
	 * @return ambito
	 */
	public String getAmbito() {
		return ambito;
	}

	/**
	 * metodo che setta l'ambito del progetto formativo
	 * @param ambito
	 */
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	/**
	 * Metodo che ritorna l'attivita del progetto formativo
	 * @return attivita
	 */
	public String getAttivita() {
		return attivita;
	}

	/**
	 * metodo che setta l'attivita del progetto formativo
	 * @param attivita
	 */
	public void setAttivita(String attivita) {
		this.attivita = attivita;
	}

	/**
	 * Metodo che ritorna le conoscenze del progetto formativo
	 * @return conoscenze
	 */
	public String getConoscenze() {
		return conoscenze;
	}

	/**
	 * metodo che setta le conoscenze del progetto formativo
	 * @param conoscenze
	 */
	public void setConoscenze(String conoscenze) {
		this.conoscenze = conoscenze;
	}

	/**
	 * Metodo che ritorna il numero massimo dei partecipanti al progetto formativo
	 * @return maxPartecipanti
	 */
	public String getMaxPartecipanti() {
		return maxPartecipanti;
	}

	/**
	 * metodo che setta il numero massimo dei partecipanti al progetto formativo
	 * @param maxPartecipanti
	 */
	public void setMaxPartecipanti(String maxPartecipanti) {
		this.maxPartecipanti = maxPartecipanti;
	}


	

	private String nome;
	private String descrizione;
	private String ambito;
	private String attivita;
	private String conoscenze;
	private String maxPartecipanti;
	
	
	
	
}
