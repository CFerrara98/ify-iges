package it.unisa.di.is.gc1.ify.progettoFormativo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * classe per interfacciamento con il database 
 * @author Carmine Ferrara
 */
public interface ProgettoFormativoRepository extends JpaRepository<ProgettoFormativo, Long> {
	
	/**
	 * Permette di ottenere un elenco di progettiFormativi dato un 
	 * determinato stato
	 * 
	 *
	 *@pre stato = {@link ProgettoFormativo#ATTIVO} or stato =
	 *      {@link  ProgettoFormativo#ARCHIVIATO} 
	 *@param stato Stringa - rappresenta lo stato di un progetto fromativo
	* @param pIva stringa - rappresenta la partita iva dell'azienda associata al progetto formativo
	 *@return Lista di {@link ProgettoFormativo} che rappresenta una lista di progetti formativi di una azienda
	 *       	<b>Può essere vuota</b> se nel database non
	 *        sono presenti progetti formativi di un azienda 
	 *         
	 * 
	 */
	public List<ProgettoFormativo> findAllByAziendaPIvaAndStato(String pIva, String stato);
	
	
	/**
	 * Permette di ottenere un progetto formativo dato un determinato id.
	 * 
	 * @param id Long che rappresenta l'id del progetto formativo cercato
	 *
	 * @return Oggetto {@link ProgettoFormativo} che rappresenta il progetto formativo cercato.
	 * 
	 * @pre id > 0
	 */
	public Optional<ProgettoFormativo> findById(Long id);
}
