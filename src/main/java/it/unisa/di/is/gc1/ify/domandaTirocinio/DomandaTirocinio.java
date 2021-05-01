package it.unisa.di.is.gc1.ify.domandaTirocinio;

import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutor;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.convenzioni.Azienda;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativo;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe che modella una domanda di tirocinio
 * @author Geremia Cavezza
 * 
 */

@Entity
public class DomandaTirocinio {
	
	/**
	 * Costruttore che crea un oggetto Domanda Tirocinio vuoto, 
	 * che verra' popolato con i metodi setters.
	 */
	public DomandaTirocinio() {
		
	}
	
	/**
	 * Costruttore di una domanda tirocinio con parametri utili nei casi di test.
	 * @param id è l'id della domanda di tirocinio.
	 * @param conoscenze sono le conoscenze della domanda di tirocinio.
	 * @param motivazioni sono le motivazioni della domanda di tirocinio.
	 * @param dataInizio e' la data di inizio della domanda di tirocinio.
	 * @param dataFine e' la data di fine della domanda di tirocinio.
	 * @param cfu sono gli cfu della domanda di tirocinio.
	 * @param stato e' lo stato della domanda di tirocinio.
	 * @param progettoFormativo è il progetto formativo della domanda di tirocinio.
	 * @param azienda e' l'azienda della domanda di tirocinio.
	 * @param studente e' lo studente della domanda di tirocinio.
	 * @param tutor e' il docente scelto per la domanda di tirocinio.
	 */
	public DomandaTirocinio(long id, String conoscenze, String motivazioni, LocalDate dataInizio, LocalDate dataFine,
			int cfu, String stato, ProgettoFormativo progettoFormativo, Azienda azienda, Studente studente, DocenteTutor tutor) {
		super();
		this.id = id;
		this.conoscenze = conoscenze;
		this.motivazioni = motivazioni;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.cfu = cfu;
		this.stato = stato;
		this.progettoFormativo = progettoFormativo;
		this.azienda = azienda;
		this.studente = studente;
		this.docenteTutor = tutor;
	}

	public DomandaTirocinio(long id, String conoscenze, String motivazioni, LocalDate dataInizio, LocalDate dataFine,
							int cfu, String stato, ProgettoFormativo progettoFormativo, Azienda azienda, Studente studente) {
		super();
		this.id = id;
		this.conoscenze = conoscenze;
		this.motivazioni = motivazioni;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.cfu = cfu;
		this.stato = stato;
		this.progettoFormativo = progettoFormativo;
		this.azienda = azienda;
		this.studente = studente;
		this.docenteTutor = null;
		System.err.println("Created DomandaTirocinio with empty tutor for debugging");
	}
	
	/**
	 * Metodo che ritorna l'id della domanda di tirocinio.
	 * @return id l'id della domanda di tirocinio.
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Metodo che ritorna le conoscenze della domanda di tirocinio.
	 * @return conoscenze le conoscenze della domanda di tirocinio.
	 */
	public String getConoscenze() {
		return conoscenze;
	}

	/**
	 * Metodo che setta le conoscenze della domanda di tirocinio.
	 * @param conoscenze sono le conoscenze della domanda di tirocinio.
	 */
	public void setConoscenze(String conoscenze) {
		this.conoscenze = conoscenze;
	}

	/**
	 * Metodo che ritorna le motivazioni della domanda di tirocinio.
	 * @return motivazioni le motivazioni della domanda di tirocinio.
	 */
	public String getMotivazioni() {
		return motivazioni;
	}

	/**
	 * Metodo che setta le motivazioni della domanda di tirocinio.
	 * @param motivazioni sono le motivazioni della domanda di tirocinio.
	 */
	public void setMotivazioni(String motivazioni) {
		this.motivazioni = motivazioni;
	}

	/**
	 * Metodo che ritorna la data di inizio della domanda di tirocinio.
	 * @return dataInizio la data di inizio della domanda di tirocinio.
	 */
	public LocalDate getDataInizio() {
		return dataInizio;
	}

	/**
	 * Metodo che setta la data di inizio della domanda di tirocinio.
	 * @param data di inizio e' la data di inizio della domanda di tirocinio.
	 */
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	/**
	 * Metodo che ritorna la data di fine della domanda di tirocinio.
	 * @return dataFine la data di fine della domanda di tirocinio.
	 */
	public LocalDate getDataFine() {
		return dataFine;
	}

	/**
	 * Metodo che setta la data di fine della domanda di tirocinio.
	 * @param data di fine e' la data di fine della domanda di tirocinio.
	 */
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	/**
	 * Metodo che ritorna i cfu della domanda di tirocinio.
	 * @return cfu i cfu della domanda di tirocinio.
	 */
	public int getCfu() {
		return cfu;
	}

	/**
	 * Metodo che setta i cfu della domanda di tirocinio.
	 * @param cfu sono i cfu della domanda di tirocinio.
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	/**
	 * Metodo che ritorna lo stato della domanda di tirocinio.
	 * @return stato lo stato della domanda di tirocinio.
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Metodo che setta lo stato della domanda di tirocinio.
	 * @param stato e' lo stato della domanda di tirocinio.
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * Metodo che ritorna il progetto formativo della domanda di tirocinio.
	 * @return progettoFormativo il progetto formativo della domanda di tirocinio.
	 */
	public ProgettoFormativo getProgettoFormativo() {
		return progettoFormativo;
	}

	/**
	 * Metodo che ritorna il progetto formativo della domanda di tirocinio.
	 * @param progetto formativo e' il progetto formativo della domanda di tirocinio.
	 */
	public void setProgettoFormativo(ProgettoFormativo progettoFormativo) {
		this.progettoFormativo = progettoFormativo;
	}

	/**
	 * Metodo che ritorna l'azienda della domanda di tirocinio.
	 * @return azienda l'azienda della domanda di tirocinio.
	 */
	public Azienda getAzienda() {
		return azienda;
	}

	/**
	 * Metodo che setta l'azienda della domanda di tirocinio.
	 * @param azienda e' l'azienda della domanda di tirocinio.
	 */
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	/**
	 * Metodo che ritorna lo studente della domanda di tirocinio.
	 * @return studente lo studente della domanda di tirocinio.
	 */
	public Studente getStudente() {
		return studente;
	}

	/**
	 * Metodo che setta lo studente della domanda di tirocinio.
	 * @param studente e' lo studente della domanda di tirocinio.
	 */
	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	/**
	 * Metodo che setta il tutor scelto per la domanda di tirocinio.
	 * @param tutor e' il docente scelto per la domanda di tirocinio.
	 */
	public DocenteTutor getTutor() {
		return this.docenteTutor;
	}

	/**
	 * Metodo che restituisce il tutor scelto per la domanda di tirocinio.
	 * @param tutor e' il docente scelto per la domanda di tirocinio.
	 */
	public void setTutor(DocenteTutor tutor) {
		this.docenteTutor = tutor;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String conoscenze;
	private String motivazioni;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private int cfu;
	private String stato;
	@ManyToOne
	private ProgettoFormativo progettoFormativo;	
	@ManyToOne
	private Azienda azienda;
	@ManyToOne
	private Studente studente;
	@ManyToOne
	private DocenteTutor docenteTutor;
	
	/**
	 * Costante che rappresenta lo stato "in attesa" di una domanda di tirocinio.
	 * Una domanda si trova in questo stato quando non e' ancora stata valutata.
	 */
	public static final String IN_ATTESA = "in attesa";

	/**
	 * Costante che rappresenta lo stato "accettata" di una domanda di tirocinio.
	 * Una domanda si trova in questo stato quando e' stata accettata dal delegato aziendale e dal docente tutor.
	 */
	public static final String ACCETTATA = "accettata";

	/**
	 * Costante che rappresenta lo stato "rifiutata" di una domanda di tirocinio.
	 * Una domanda si trova in questo stato quando e' stata rifiutata dal delegato aziendale e dal docente tutor.
	 */
	public static final String RIFIUTATA = "rifiutata";
	
	/**
	 * Costante che rappresenta lo stato "approvata" di una domanda di tirocinio.
	 * Una domanda si trova in questo stato quando e' stata approvata dal responsabile ufficio tirocini.
	 */
	public static final String APPROVATA = "approvata";

	/**
	 * Costante che rappresenta lo stato "respinta" di una domanda di tirocinio.
	 * Una domanda si trova in questo stato quando e' stata respinta dal responsabile ufficio tirocini.
	 */
	public static final String RESPINTA = "respinta";

	/**
	 * Costante che rappresenta lo stato "accettata" di una domanda di tirocinio da parte dell'azienda.
	 * Una domanda si trova in questo stato quando e' stata accettata dal delegato aziendale.
	 */
	public static final String ACCETTATA_AZIENDA = "accettata_azienda";

	/**
	 * Costante che rappresenta lo stato "rifiutata" di una domanda di tirocinio da parte dell'azienda.
	 * Una domanda si trova in questo stato quando e' stata rifiutata dal delegato aziendale.
	 */
	public static final String RIFIUTATA_AZIENDA = "rifiutata_azienda";

	/**
	 * Costante che rappresenta lo stato "accettata" di una domanda di tirocinio da parte del tutor.
	 * Una domanda si trova in questo stato quando e' stata accettata dal docente tutor scelto.
	 */
	public static final String ACCETTATA_TUTOR = "accettata_tutor";

	/**
	 * Costante che rappresenta lo stato "rifiutata" di una domanda di tirocinio da parte del tutor.
	 * Una domanda si trova in questo stato quando e' stata rifiutata dal docente tutor scelto.
	 */
	public static final String RIFIUTATA_TUTOR = "rifiutata_tutor";

	/**
	 * Costante che definisce la minima lunghezza del campo conoscenze.
	 */	
	public static final int MIN_LUNGHEZZA_CONOSCENZE = 1;
	
	/**
	 * Costante che definisce la massima lunghezza del campo conoscenze.
	 */
	public static final int MAX_LUNGHEZZA_CONOSCENZE  = 200;
	
	/**
	 * Costante che definisce la minima lunghezza del campo motivazioni.
	 */	
	public static final int MIN_LUNGHEZZA_MOTIVAZIONI = 1;
	
	/**
	 * Costante che definisce la massima lunghezza del campo motivazioni.
	 */
	public static final int MAX_LUNGHEZZA_MOTIVAZIONI  = 300;
	
	/** Valore che definisce la minima data di inizio accettabile. */
	public static final LocalDate MIN_DATE_INIZIO = LocalDate.now();

	/**
	 * Costante che definisce il minimo numero di cfu.
	 */	
	public static final int MIN_CFU = 6;
	
	/**
	 * Costante che definisce il massimo numero di cfu.	 */
	public static final int MAX_CFU  = 12;

}
