package it.unisa.di.is.gc1.ify.convenzioni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Simone Civale
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persistenti relative ad un Delegato Aziendale.
 * @see DelegatoAziendale
 */

@Repository
public interface DelegatoAziendaleRepository extends JpaRepository<DelegatoAziendale, Long> {
	
	/**
	 * Permette di ottenere un delegato a partire dalla partita iva dell'azienda.
	 * @param pIva stringa che rappresenta la partita iva dell'azienda a cui 
	 * appartiene il delegato aziendale.
	 * @return Oggetto {@link DelegatoAziendale} che rappresenta il delegato aziendale.
	 * <b>Può essere null</b> se nel database non è presente un delegato aziendale con
	 * la partita iva a cui appartiene passata come parametro. 
	 * @pre pIva != null
	 */
	DelegatoAziendale findByAziendaPIva(String pIva);
	
	/**
	 * Permette di verificare se un Delegato Aziendale esiste nel database attraverso
	 * la partita iva dell'azienda. 
	 * @param pIva stringa che rappresenta la partita iva dell'azienda a cui appartiene
	 * il delegato aziendale.
	 * @return true se il delegato aziendale esiste nel database, false se il delegato 
	 * aziendale non esiste nel database. 
	 * @pre pIva != null
	 */
	boolean existsByAziendaPIva(String pIva);
	
	/**
	 * Permette di ottenere un delegato a partire dall'email.
	 * @param email stringa che rappresenta l'email del delegato aziendale
	 * @return Oggetto {@link DelegatoAziendale} che rappresenta il delegato aziendale.
	 * <b>Può essere null</b> se nel database non è presente un delegato aziendale con l'email
	 * passata come parametro.
	 * @pre email != null
	 */
	DelegatoAziendale findByEmail(String email);
	
	/**
	 * Permette di verificare se un Delegato Aziendale esiste nel database attrverso l'email. 
	 * @param email stringa che rappresenta l'email del delegato aziendale
	 * @return true se il delegato aziendale esiste nel database, false se il delegato aziendale
	 * non esiste nel database.
	 * @pre email != null
	 */
	boolean existsByEmail(String email);
}
