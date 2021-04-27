package it.unisa.di.is.gc1.ify.convenzioni;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author carmi
 *
 */

public interface RichiestaConvenzionamentoRepository extends JpaRepository<RichiestaConvenzionamento, Long> {
	
	/**
	 * Permette di ottenere un elenco di richieste di convenzionamento dato un 
	 * determinato stato
	 * 
	 * 
	 *@pre stato = {@link RichiestaConvenzionamento#IN_ATTESA} or stato =
	 *      {@link RichiestaConvenzionamento#ACCETTATA} or stato =
	 *      {@link RichiestaConvenzionamento#RIFIUTATA}
	 *@param stato Stringa - rappresenta lo stato di una richiesta di convenzionamento
	 *@return Lista di {@link RichiestaConvenzionamento} che rappresenta la lista delle
	 *        richieste di convenzionamento<b>Può essere vuota</b> se nel database non
	 *        sono presenti richieste di convenzionamento cone lo stato passato come
	 *        parametro
	 *         
	 * 
	 */
	public List<RichiestaConvenzionamento> findAllByStato(String stato);
	
	
	/**
	 * Permette di ottenere la richiesta di convenzionamento di un' azienda.
	 * 
	 * @param pIVA String che rappresenta l'id (partita iva dell' azienda per cui si cerca la
	 *           richiesta
	 * 
	 * @return Oggetto {@link RichiestaConvenzionamento} che rappresenta la richiesta di
	 *         convenzionamento.
	 * 
	 */
	public RichiestaConvenzionamento findByAziendaPIva(String pIVA);

	/**
	 * Permette di ottenere la richiesta di convenzionamento con un determinato id.
	 *
	 * @param id Long che rappresenta l'id della richiesta di convenzionamento cercata
	 *
	 * @return Oggetto {@link RichiestaConvenzionamento} che rappresenta la richiesta di
	 *        convenzionamento.
	 *
	 * @pre id > 0

	 */


	public Optional<RichiestaConvenzionamento> findById(Long id);

	/**
	 * Permette di ottenere la richiesta di convenzionamento di un delegato aziendale.
	 * 
	 * @param email String che rappresenta la stringa del delegato aziendale per cui si cerca la
	 *           richiesta
	 * 
	 * @return Oggetto {@link RichiestaConvenzionamento} che rappresenta la richiesta di
	 *         convenzionamento.
	 * 
	 * @pre email != null
	 */
	RichiestaConvenzionamento findByDelegatoAziendaleEmail(String email);
}
