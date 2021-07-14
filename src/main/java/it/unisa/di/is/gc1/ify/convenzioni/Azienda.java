package it.unisa.di.is.gc1.ify.convenzioni;

import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativo;

import javax.persistence.*;
import java.util.List;


/**
 * Classe che modella i dati di una singola azienda
 * @author Carmine Ferrara
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Azienda {


	/**
	 * costruttore vuoto da usare con l'inserimento dei parametri dei metodi setter
	 */

	public Azienda() {
		super();
	}

	/**costruzione di un oggetto azienda con parametri utile per i casi di test
	 *
	 * @param partitaIva
	 * @param ragioneSociale
	 * @param sede
	 * @param settore
	 * @param descrizione
	 */
	public Azienda(String partitaIva, String ragioneSociale, String sede, String settore, String descrizione) {
		this.partitaIva = partitaIva;
		this.ragioneSociale = ragioneSociale;
		this.sede = sede;
		this.settore = settore;
		this.descrizione = descrizione;
	}
	
	/**
	 * Restituisce il campo partitaIva
	 * @return partita iva
	 */
	public String getPartitaIva() {
		return partitaIva;
	}
	
	/**
	 * Setta il campo partita iva
	 * @param partitaIva
	 */
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	
	/**
	 * Restituisce il campo ragione sociale
	 * @return ragione sociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	
	/**
	 * Setta il campo ragione sociale
	 * @param ragioneSociale
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	/**
	 * Restituisce il campo sede
	 * @return sede
	 */
	public String getSede() {
		return sede;
	}
	
	/**
	 * Setta il campo sede
	 * @param sede
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	
	/**
	 * Restituisce il campo settore
	 * @return settore
	 */
	public String getSettore() {
		return settore;
	}
	
	/**
	 * Setta il campo settore
	 * @param settore
	 */
	public void setSettore(String settore) {
		this.settore = settore;
	}
	
	/**
	 * Restituisce il campo descrizione
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * Setta il campo descrizione
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Restituisce la lista di progetti formativi associati
	 * @return lista di progetti formativi associati
	 */
	public List<ProgettoFormativo> getProgettiFormativi() {
		return progettiFormativi;
	}
	
	/**
	 * Setta la lista di progetti formativi associati
	 * @param progettiFormativi
	 */
	public void setProgettiFormativi(List<ProgettoFormativo> progettiFormativi) {
		this.progettiFormativi = progettiFormativi;
	}



	@Id
	@Column(unique=true)
	private String partitaIva;
	private String ragioneSociale;
	private String sede;
	private String settore;
	private String descrizione;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "azienda")
	private List<ProgettoFormativo> progettiFormativi;

	/** Espressione regolare che definisce il formato del campo partita iva. */
	public static final String PIVA_PATTERN = "^[0-9]{11}$";

	/** Espressione regolare che definisce il formato del campo ragione sociale. */
	public static final String RAGIONE_SOCIALE_PATTERN = "^[A-Z a-z àéèìòù 0-9,.-]{5,255}$";

	/** Espressione regolare che definisce il formato del campo sede. */
	public static final String SEDE_PATTERN = "^[A-Z a-z àéèìòù 0-9,.-]{5,255}$";

	/** Espressione regolare che definisce il formato del campo settore. */
	public static final String SETTORE_PATTERN = "^[A-Z a-z àéèìòù 0-9,.-]{5,255}$";


	/**
	 * Costante che definisce la minima lunghezza dei campi ragione sociale, sede e settore.
	 */
	public static final int MIN_LUNGHEZZA_SEDE_SETTORE_RAGIONE_SOCIALE = 5;

	/**
	 * Costante che definisce la massima lunghezza dei campi ragione sociale, sede e settore.
	 */
	public static final int MAX_LUNGHEZZA_SEDE_SETTORE_RAGIONE_SOCIALE = 255;

	/**
	 * Costante che definisce la lunghezza del campo Partita IVA.
	 */
	public static final int LUNGHEZZA_PARTITA_IVA = 11;

	/**
	 * Costante che definisce la minima lunghezza del campo descrizione.
	 */
	public static final int MIN_LUNGHEZZA_DESCRIZIONE = 2;

	/**
	 * Costante che definisce la massima lunghezza del campo descrizione.
	 */
	public static final int MAX_LUNGHEZZA_DESCRIZIONE = 800;
}
