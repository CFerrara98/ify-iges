package it.unisa.di.is.gc1.ify.responsabileUfficioTirocini;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Simone Civale
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persistenti relative ad un Responsabile Ufficio 
 * Tirocini.
 * @see ResponsabileUfficioTirocini
 */

@Repository
public interface ResponsabileUfficioTirociniRepository extends JpaRepository<ResponsabileUfficioTirocini, Long> {
	
	/**
	 * Permette di ottenere un responsabile ufficio tirocini a partire
	 * dalla propria email.
	 * @param email stringa che contiene l'email del responsabile ufficio
	 * tirocini.
	 * @return Oggetto {@link ResponsabileUfficioTirocini} che rappresenta 
	 * il responsabile ufficio tirocini. <b> Può essere null </b> se nel
	 * database non è presente un responsabile ufficio tirocini con l'email
	 * passata come parametro.
	 * @pre email != null
	 */
	ResponsabileUfficioTirocini findByEmail(String email);
	
	/**
	 * Permette di verificare se un responsabile ufficio tirocini esiste nel
	 * database attraverso la propria email. 
	 * @param email stringa che contiene l'email del responsabile ufficio 
	 * tirocini.
	 * @return true se il responsabile ufficio tirocini esiste nel database, 
	 * false se il responsabile ufficio tirocini non esiste nel database. 
	 * @pre email !=null
	 */
	boolean existsByEmail(String email);

}
