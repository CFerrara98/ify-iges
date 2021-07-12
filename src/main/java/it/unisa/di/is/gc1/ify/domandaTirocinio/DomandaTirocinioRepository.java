package it.unisa.di.is.gc1.ify.domandaTirocinio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Geremia Cavezza
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persistenti relative ad una Domanda di Tirocinio.
 * @see DomandaTirocinio
 */

@Repository
public interface DomandaTirocinioRepository extends JpaRepository<DomandaTirocinio, Long> {

	/**
	 * Permette di ottenere l'elenco delle domande di tirocinio per un determinato studente.
	 *      
	 * @param id Long che rappresenta l'identificativo dello studente
	 * 
	 * @return Lista di {@link DomandaTirocinio} che rappresenta la lista delle
	 *         domande di tirocinio <b>Può essere vuota</b> se nel database non
	 *         sono presenti domande di tirocinio per lo studente passato come
	 *         parametro 
	 */
	public List<DomandaTirocinio> findAllByStudenteId(Long id);
	
	/**
	 * Permette di ottenere l'elenco delle domande di tirocinio per una determinata azienda.
	 *      
	 * @param pIva String che rappresenta l'identificativo dell'azienda
	 * 
	 * @return Lista di {@link DomandaTirocinio} che rappresenta la lista delle
	 *         domande di tirocinio <b>Può essere vuota</b> se nel database non
	 *         sono presenti domande di tirocinio per l'azienda passata come
	 *         parametro 
	 */
	public List<DomandaTirocinio> findAllByAziendaPIva(String pIva);
	
	/**
	 * Permette di ottenere l'elenco delle domande di tirocinio con un determinato studente e stato.
	 * 
	 * @pre stato = {@link DomandaTirocinio#IN_ATTESA} or stato =
	 *      {@link DomandaTirocinio#ACCETTATA} or stato =
	 *      {@link DomandaTirocinio#RIFIUTATA} or stato =
	 *      {@link DomandaTirocinio#APPROVATA} or stato =
	 *      {@link DomandaTirocinio#RESPINTA}
	 *      
	 * @param id Long che rappresenta l'id dello studente.
	 * @param stato String che rappresenta lo stato della domanda.
	 * 
	 * @return Lista di {@link DomandaTirocinio} che rappresenta la lista delle
	 *         domande di tirocinio <b>Puo' essere vuota</b> se nel database non
	 *         sono presenti domande di tirocinio per studente e stato passati come
	 *         parametro 
	 */
	public List<DomandaTirocinio> findAllByStudenteIdAndStato(Long id, String stato);
	
	/**
	 * Permette di ottenere l'elenco delle domande di tirocinio con una determinata azienda e stato.
	 * 
	 * @pre stato = {@link DomandaTirocinio#IN_ATTESA} or stato =
	 *      {@link DomandaTirocinio#ACCETTATA} or stato =
	 *      {@link DomandaTirocinio#RIFIUTATA} or stato =
	 *      {@link DomandaTirocinio#APPROVATA} or stato =
	 *      {@link DomandaTirocinio#RESPINTA}
	 *      
	 * @param pIva String che rappresenta la partita iva dell'azienda.
	 * @param stato String che rappresenta lo stato della domanda.
	 * 
	 * @return Lista di {@link DomandaTirocinio} che rappresenta la lista delle
	 *         domande di tirocinio <b>Puo' essere vuota</b> se nel database non
	 *         sono presenti domande di tirocinio per azienda e stato passati come
	 *         parametro 
	 */
	public List<DomandaTirocinio> findAllByAziendaPIvaAndStato(String pIva, String stato);
	
	/**
	 * Permette di ottenere l'elenco delle domande di tirocinio con un determinato stato.
	 * 
	 * @pre stato = {@link DomandaTirocinio#IN_ATTESA} or stato =
	 *      {@link DomandaTirocinio#ACCETTATA} or stato =
	 *      {@link DomandaTirocinio#RIFIUTATA} or stato =
	 *      {@link DomandaTirocinio#APPROVATA} or stato =
	 *      {@link DomandaTirocinio#RESPINTA}
	 *      
	 * @param stato String che rappresenta lo stato della domanda.
	 * 
	 * @return Lista di {@link DomandaTirocinio} che rappresenta la lista delle
	 *         domande di tirocinio <b>Puo' essere vuota</b> se nel database non
	 *         sono presenti domande di tirocinio con lo stato passato come
	 *         parametro 
	 */
	public List<DomandaTirocinio> findAllByStato(String stato);
	
	/**
	 * Permette di ottenere una determinata domanda di tirocinio tramite id.
	 *      
	 * @param id Long che rappresenta l'identificativo della domanda di tirocinio
	 *
	 * @return Oggetto {@link DomandaTirocinio} che rappresenta la
	 *         domanda di tirocinio <b>Puo' essere vuota</b> se nel database non
	 *         e' presente una domanda di tirocinio con id passato come
	 *         parametro 
	 */
	public Optional<DomandaTirocinio> findById(Long id);

	/**
	 * Permette di ottenere l'elenco delle domande di tirocinio con l'id del docente tutor
	 *
	 * @param id Long che rappresenta l'identificativo del docente tutor
	 *
	 * @return Oggetto {@link DomandaTirocinio} che rappresenta la
	 *         domanda di tirocinio <b>Puo' essere vuota</b> se nel database non
	 *         e' presente una domanda di tirocinio con id passato come
	 *         parametro
	 */
	public List<DomandaTirocinio> findAllByDocenteTutorId(Long id);

	/**
	 * Permette di ottenere l'elenco delle domande di tirocinio con l'id del docente tutor
	 *
	 * @param id Long che rappresenta l'identificativo del docente tutor
	 *
	 * @return Oggetto {@link DomandaTirocinio} che rappresenta la
	 *         domanda di tirocinio <b>Puo' essere vuota</b> se nel database non
	 *         e' presente una domanda di tirocinio con id passato come
	 *         parametro
	 */
	public List<DomandaTirocinio> findAllByDocenteTutorIdAndStato(Long id, String st);
}
