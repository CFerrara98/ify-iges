package it.unisa.di.is.gc1.ify.web;

import java.time.LocalDate;

/**
 * Oggetto utilizzato per mappare i campi di un form di una domanda di tirocinio. Questo oggetto
 * viene passato come parametro al controller dalla dispatcher servlet quando uno studente 
 * sottomette il modulo d'invio di una domanda di tirocinio.
 * 
 * @author Benedetta Coccaro
 */

public class DomandaTirocinioForm {
	
	public DomandaTirocinioForm() {
		
	}
	
	/**
	 * Costruttore di un oggetto DomandaTirocinioForm
	 * @param conoscenze conoscenze dello studente
	 * @param motivazioni motivazioni dello studente
	 * @param dataInizio data inizio tirocinio
	 * @param dataFine data fine tirocinio
	 * @param numeroCFU numero cfu tirocinio
	 * @param condizioni condizioni tirocinio
	 */
	public DomandaTirocinioForm(String conoscenze, String motivazioni, String dataInizio, String dataFine,
			String numeroCFU, String condizioni, String docenteTutorId) {
		super();
		this.conoscenze = conoscenze;
		this.motivazioni = motivazioni;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.numeroCFU = numeroCFU;
		this.condizioni = condizioni;
		this.docenteTutorId = docenteTutorId;
	}
	
	
	/**
	 * Metodo che ritorna le conoscenze dello studente
	 * @return conoscenze
	 */
	public String getConoscenze() {
		return conoscenze;
	}

	/**
	 * metodo che setta le conoscenze dello studente
	 * @param conoscenze
	 */
	public void setConoscenze(String conoscenze) {
		this.conoscenze = conoscenze;
	}

	/**
	 * Metodo che ritorna le motivazioni dello studente
	 * @return motivazioni
	 */
	public String getMotivazioni() {
		return motivazioni;
	}

	/**
	 * metodo che setta le motivazioni dello studente
	 * @param motivazioni
	 */
	public void setMotivazioni(String motivazioni) {
		this.motivazioni = motivazioni;
	}
	
	/**
	 * Metodo che ritorna la data inizio del tirocinio
	 * @return motivazioni
	 */
	public LocalDate getDataInizio() {
		if(this.dataInizio.equals("")) return null;
		
		LocalDate tmp = LocalDate.parse(this.dataInizio);
		return tmp;
	}
	
	/**
	 * metodo che setta la data di inizio del tirocinio
	 * @param dataInizio
	 */
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	/**
	 * metodo che ritorna la data fine del tirocinio
	 * @return dataFine
	 */
	public LocalDate getDataFine() {
		if(this.dataFine.equals("")) return null;
		
		LocalDate tmp = LocalDate.parse(this.dataFine);
		return tmp;
	}
	
	/**
	 * metodo che setta la data di fine del tirocinio
	 * @param dataFine
	 */
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	/**
	 * metodo che ritorna il numero di CFU del tirocinio
	 * @return numeroCFU
	 */
	public String getNumeroCFU() {
		return numeroCFU;
	}

	/**
	 * metodo che setta il numero di CFU del tirocinio
	 * @param numeroCFU
	 */
	public void setNumeroCFU(String numeroCFU) {
		this.numeroCFU = numeroCFU;
	}

	/**
	 * metodo che ritorna le condizioni del tirocinio
	 * @return condizioni
	 */
	public String getCondizioni() {
		return condizioni;
	}

	/**
	 * metodo che setta le condizioni del tirocinio
	 * @param condizioni
	 */
	public void setCondizioni(String condizioni) {
		this.condizioni = condizioni;
	}

	public String getDocenteTutorId() {
		return docenteTutorId;
	}

	public void setDocenteTutorId(String docenteTutorId) {
		this.docenteTutorId = docenteTutorId;
	}
	
	private String conoscenze;
	private String motivazioni;
	private String dataInizio;
	private String dataFine;
	private String numeroCFU;
	private String condizioni;
	private String docenteTutorId;
}
