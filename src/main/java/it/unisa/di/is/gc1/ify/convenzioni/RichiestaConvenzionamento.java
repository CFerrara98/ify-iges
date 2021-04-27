package it.unisa.di.is.gc1.ify.convenzioni;

import javax.persistence.*;

/**
 * Classe che modella i singoli dati di una richiesta di convenzionamento
 * @author Carmine Ferrara
 */
@Entity
public class RichiestaConvenzionamento {
	
	
	/**costruttore vuoto utile per il set dei dati con i setter
	 * 
	 */
	public RichiestaConvenzionamento() {
		super();
	}
	
	/**costruttore con parametri utile per i casi di test
	 * 
	 * @param stato
	 * @param azienda
	 * @param delegatoAziendale
	 */
	public RichiestaConvenzionamento(String stato, Azienda azienda, DelegatoAziendale delegatoAziendale) {
		super();
		this.stato = stato;
		this.azienda = azienda;
		this.delegatoAziendale = delegatoAziendale;
	}
	
	/**
	 * Rerstituisce l'id
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Configura l'id
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Rerstituisce lo stato
	 * @return stato
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Configura lo stato
	 * @param stato
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * Rerstituisce l'azienda associata
	 * @return azienda associata
	 */
	public Azienda getAzienda() {
		return azienda;
	}

	/**
	 * Configura l'azienda associata
	 * @param azienda associata
	 */
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
	
	/**
	 * Rerstituisce il delegato aziendale associato
	 * @return delegato aziendale associato
	 */
	public DelegatoAziendale getDelegatoAziendale() {
		return delegatoAziendale;
	}
	
	/**
	 * Configura il delegato aziendale associato
	 * @param delegatoAziendale
	 */
	public void setDelegatoAziendale(DelegatoAziendale delegatoAziendale) {
		this.delegatoAziendale = delegatoAziendale;
	}


	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String stato;
	@OneToOne(cascade = CascadeType.ALL)
	private Azienda azienda;
	@OneToOne(cascade = CascadeType.ALL)
	private DelegatoAziendale delegatoAziendale;
	
	/**
	 * Costante che rappresenta lo stato "in attesa" di una richiesta di convenzionamento.
	 * Una richiesta si trova in questo stato quando non è ancora stata valutata.
	 */
	public static final String IN_ATTESA = "in attesa";

	/**
	 * Costante che rappresenta lo stato "accettata" di una richiesta di convenzionamento.
	 * Una richiesta si trova in questo stato quando è stata accettata dal
	 * responsabile ufficio tirocini.
	 */
	public static final String ACCETTATA = "accettata";

	/**
	 * Costante che rappresenta lo stato "rifiutata" di una richiesta di convenzionamento.
	 * Una richiesta si trova in questo stato quando è stata rifiutata dal
	 * responsabile ufficio tirocini.
	 */
	public static final String RIFIUTATA = "rifiutata";
}
