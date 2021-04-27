package it.unisa.di.is.gc1.ify.progettoFormativo;

import it.unisa.di.is.gc1.ify.convenzioni.Azienda;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe che modella i singoli dati di un progetto formativo aziendale
 * @author Carmine Ferrara
 */
@Entity
public class ProgettoFormativo {
	
	/**costruttore vuoto utile per il set dei dati con i setter
	 * 
	 */
	public ProgettoFormativo() {
		super();
	}
	
	/**costruttore con parametri utile per i casi di test
	 * 
	 * @param nome
	 * @param descrizione
	 * @param ambito
	 * @param attivita
	 * @param conoscenze
	 * @param max_partecipanti
	 * @param data_compilazione
	 * @param stato
	 * @param azienda
	 */
	public ProgettoFormativo(String nome, String descrizione, String ambito, String attivita, String conoscenze,
			int max_partecipanti, LocalDate data_compilazione, String stato, Azienda azienda) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.ambito = ambito;
		this.attivita = attivita;
		this.conoscenze = conoscenze;
		this.max_partecipanti = max_partecipanti;
		this.data_compilazione = data_compilazione;
		this.stato = stato;
		this.azienda = azienda;
	}

	/**
	 * Metodo che ritorna l'id del progetto formativo.
	 * @return id e' l'id del progetto formativo.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che setta l'id del progetto formativo.
	 * @param id e' l'id del progetto formativo.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metodo che ritorna il nome del progetto formativo
	 * @return nome il nome del progetto formativo.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che setta il nome del progetto formativo. 
	 * @param nome e' il nome del progetto formativo.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo che ritorna la descrizione del progetto formativo. 
	 * @return descrizione e' la descrizione del progetto formativo.
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * Metodo che setta la descrizione del progetto formativo.
	 * @param descrizione e' la descrizione del progettto formativo.
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Metodo che ritorna l'ambito del progetto formativo.
	 * @return ambito e' l'ambito del progetto formativo.
	 */
	public String getAmbito() {
		return ambito;
	}

	/**
	 * Metodo che setta l'ambito del progetto formativo.
	 * @param ambito e' l'ambito del progetto formativo.
	 */
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	/**
	 * Metodo che ritorna l'attivita del progetto formativo.
	 * @return attivita e' l'attività del progetto formativo.
	 */
	public String getAttivita() {
		return attivita;
	}

	/**
	 * Metodo che setta l'attività del progetto formativo.
	 * @param attivita e' l'attività del progetto formativo.
	 */
	public void setAttivita(String attivita) {
		this.attivita = attivita;
	}

	/**
	 * Metodo che ritorna le conoscenze del progetto formativo.
	 * @return conoscenze sono le conoscenze del progetto formativo.
	 */
	public String getConoscenze() {
		return conoscenze;
	}

	/**
	 * Metodo che setta le conoscenze del progetto formativo.
	 * @param conoscenze sono le conoscenze del progetto formativo.
	 */
	public void setConoscenze(String conoscenze) {
		this.conoscenze = conoscenze;
	}

	/**
	 * Metodo che ritorna i partecipanti massimi del progetto formativo.
	 * @return max_partecipanti sono i partecipanti massimi del progetto formativo.
	 */
	public int getMax_partecipanti() {
		return max_partecipanti;
	}

	/**
	 * Metodo che setta i partecipanti massimi del progetto formativo.
	 * @param max_partecipanti sono i partecipanti massimi del progetto formativo. 
	 */
	public void setMax_partecipanti(int max_partecipanti) {
		this.max_partecipanti = max_partecipanti;
	}

	/**
	 * Metodo che ritorna la data di compilazione del progetto formativo.
	 * @return data_compilazione e' la data di compilazione del progetto formativo.
	 */
	public LocalDate getData_compilazione() {
		return data_compilazione;
	}

	/**
	 * Metodo che setta la data di compilazione del progetto formativo.
	 * @param data_compilazione e' la data di compilazione del progetto formativo.
	 */
	public void setData_compilazione(LocalDate data_compilazione) {
		this.data_compilazione = data_compilazione;
	}

	/**
	 * Metodo che ritorna lo stato del progetto formativo. 
	 * @return stato e' lo stato del progetto formativo.
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Metodo che setta lo stato del progetto formativo.
	 * @param stato e' lo stato del progetto formativo.
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * Metodo che ritorna l'azienda del progetto formativo
	 * @return azienda e' l'azienda del progetto formativo.
	 */
	public Azienda getAzienda() {
		return azienda;
	}

	/**
	 * Metodo che setta l'azienda del progetto formativo.
	 * @param azienda e' l'azienda del progetto formativo.
	 */
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	/**
	 * Metodo che stampa i dati relativi al progetto formativo.
	 */
	@Override
	public String toString() {
		return "ProgettoFormativo [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", ambito=" + ambito
				+ ", attivita=" + attivita + ", conoscenze=" + conoscenze + ", max_partecipanti=" + max_partecipanti
				+ ", data_compilazione=" + data_compilazione + ", stato=" + stato + ", azienda=" + azienda + "]";
	}



	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descrizione;
	private String ambito;
	private String attivita;
	private String conoscenze;
	private int max_partecipanti;
	private LocalDate data_compilazione;
	private String stato;
	@ManyToOne
	private Azienda azienda;
	
	
	/**
	 * Costante che rappresenta lo stato "attivo" di un progetto formativo aziendale.
	 * Un progetto formativo si trova in questo stato al momento della creazione
	 * oppure può essere riportato in tale stato a discrezione dell'azienda.
	 */
	public static final String ATTIVO = "attivo";

	/**
	 * Costante che rappresenta lo stato "archiviato" di un progetto formativo aziendale.
	 * Un progetto formativo si trova in questo stato quando è stato archiviato dall'azienda.
	 */
	public static final String ARCHIVIATO = "archiviato";
	
	/** Espressione regolare che definisce il formato del campo nome del progetto formativo. */
	public static final String NOME_PATTERN = "^[A-Z a-z 0-9 àèéìòù ‘,-:]{2,255}$";
	  
	
	/** Espressione regolare che definisce il formato del campo ambito del progetto formativo. */
	public static final String AMBITO_PATTERN = "^[A-Z a-z 0-9 àèéìòù ‘,-:]{2,255}$";
	
	
	/** Espressione regolare che definisce il formato del campo max_partecipanti del progetto formativo. */
	public static final String MAX_PARTECIPANTI_PATTERN = "^[0-9]+$";
	
	/**
	 * Costante che definisce la minima lunghezza dei campi nome, ambito del progetto formativo.
	 */
	public static final int MIN_LUNGHEZZA_CAMPO = 2;

	/**
	 * Costante che definisce la massima lunghezza dei campo nome, ambido del progetto formativo.
	 */
	public static final int MAX_LUNGHEZZA_CAMPO = 255;
	
	/**
	 * Costante che definisce la minima lunghezza del campo descrizione del progetto formativo.
	 */
	public static final int MIN_LUNGHEZZA_DESCRIZIONE = 2;

	/**
	 * Costante che definisce la massima lunghezza del campo descrizione del progetto formativo.
	 */
	public static final int MAX_LUNGHEZZA_DESCRIZIONE = 800;
	
	
	/**
	 * Costante che definisce la minima lunghezza del campo attività del progetto formativo.
	 */
	public static final int MIN_LUNGHEZZA_ATTIVITA = 2;

	/**
	 * Costante che definisce la massima lunghezza del campo attivita del progetto formativo.
	 */
	public static final int MAX_LUNGHEZZA_ATTIVITA = 500;

	/**
	 * Costante che definisce la minima lunghezza del campo conoscenze del progetto formativo.
	 */
	public static final int MIN_LUNGHEZZA_CONOSCENZE = 2;

	/**
	 * Costante che definisce la massima lunghezza del campo conoscenze del progetto formativo.
	 */
	public static final int MAX_LUNGHEZZA_CONOSCENZE = 500;
	
	/**
	 * Costante che definisce il valore minimo del campo max_partecipanti del progetto formativo.
	 */
	public static final int MIN_VAL_MAX_PARTECIPANTI = 1;
	
	/**
	 * Costante che definisce il valore massimo del campo max_partecipanti del progetto formativo.
	 */
	public static final int MAX_VAL_MAX_PARTECIPANTI = 999;
}
