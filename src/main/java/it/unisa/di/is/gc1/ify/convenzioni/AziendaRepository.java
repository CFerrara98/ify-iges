package it.unisa.di.is.gc1.ify.convenzioni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Simone Civale
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persistenti relative ad un'azienda.
 * @see Azienda
 */

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, String> {
	
	/**
	 * Permette di ottenere un'azienda a partire dalla sua partita iva. 
	 * @param pIva stringa che contiene la partita iva dell'azienda.
	 * @return Oggeto {@link Azienda} che rappresenta l'azienda.
	 * <b>Può essere null</b> se nel database non è presente un'azienda 
	 * con la partita iva passata come parametro.
	 * @pre pIva !=null
	 */
	Azienda findByPIva(String pIva);
	
	/**
	 * Permette di verificare se un'azienda esiste nel database attraverso 
	 * la partita iva.
	 * @param pIva stringa che contiene la partita iva dell'azienda.
	 * @return true se l'azienda esiste nel database, false se l'azienda non
	 * esiste nel database.
	 * @pre pIva != null
	 */
	boolean existsByPIva(String pIva);

}
