package it.unisa.di.is.gc1.ify.Studente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persisteti relative ad una richiesta di iscrizione
 * @see RichiestaIscrizione
 * @author Geremia Cavezza
 */

@Repository
public interface RichiestaIscrizioneRepository extends JpaRepository<RichiestaIscrizione, Long> {

	/**
	 * Permette di ottenere l'elenco delle richiesta di iscrizione con un
	 * determinato stato.
	 * 
	 * @param stato Stringa che rappresenta lo stato della richiesta di iscrizione
	 * 
	 * @return Lista di {@link RichiestaIscrizione} che rappresenta la lista delle
	 *         richieste di iscrizione <b>Puo' essere vuota</b> se nel database non
	 *         sono presenti richieste di iscrizione con lo stato passato come
	 *         parametro
	 * 
	 * @pre stato = {@link RichiestaIscrizione#IN_ATTESA} or stato =
	 *      {@link RichiestaIscrizione#ACCETTATA} or stato =
	 *      {@link RichiestaIscrizione#RIFIUTATA}
	 */
	List<RichiestaIscrizione> findAllByStato(String stato);

	/**
	 * Permette di ottenere la richiesta di iscrizione di uno studente.
	 * 
	 * @param id Long che rappresenta l'id dello studente per cui si cerca la
	 *           richiesta
	 * 
	 * @return Oggetto {@link RichiestaIscrizione} che rappresenta la richiesta di
	 *         iscrizione.
	 * 
	 * @pre id > 0
	 */
	RichiestaIscrizione findByStudenteId(Long id);



	/**
	 * Permette di ottenere la richiesta di iscrizione con un determinato id.
	 *
	 * @param id Long che rappresenta l'id della richiesta di iscrizione cercata
	 *
	 * @return Oggetto {@link RichiestaIscrizione} che rappresenta la richiesta di
	 *         iscrizione.
	 *
	 * @pre id > 0
	 */


	Optional<RichiestaIscrizione> findById(Long id);

	/**
	 * Permette di ottenere la richiesta di iscrizione di uno studente.
	 * 
	 * @param email String che rappresenta la stringa dello studente per cui si cerca la
	 *           richiesta
	 * 
	 * @return Oggetto {@link RichiestaIscrizione} che rappresenta la richiesta di
	 *         iscrizione.
	 * 
	 * @pre email != null
	 */
	RichiestaIscrizione findByStudenteEmail(String email);
}
