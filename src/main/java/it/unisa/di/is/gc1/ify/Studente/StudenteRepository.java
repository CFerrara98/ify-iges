package it.unisa.di.is.gc1.ify.Studente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Geremia Cavezza
 * 
 *         Classe che definisce le operazioni per la modellazione e l'accesso
 *         alle informazioni persistenti relative ad uno studente
 *
 * @see Studente
 */

@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {

	/**
	 * Permette di ottenere uno studente a partire dalla propria matricola.
	 * 
	 * @param matricola Stringa che rappresenta la matricola dello studente
	 * 
	 * @return Oggetto {@link Studente} che rappresenta lo studente. <b>Può essere
	 *         null</b> se nel database non è presente uno studente con la matricola
	 *         passata come parametro
	 * 
	 * @pre matricola != null
	 */
	Studente findByMatricola(String matricola);

	/**
	 * Permette di verificare se uno Studente esiste nel database attraverso la
	 * propria matricola.
	 * 
	 * @param matricola Stringa che rappresenta la matricola dello Studente
	 * 
	 * @return true se lo studente esiste nel database, false se lo studente non
	 *         esiste nel database
	 * 
	 * @pre matricola != null
	 */
	boolean existsByMatricola(String matricola);

}
