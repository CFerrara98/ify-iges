package it.unisa.di.is.gc1.ify.DocenteTutor;

import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author Francesco Abate e Carmine Ferrara
 */

@Repository
public interface DocenteTutorRepository extends JpaRepository<DocenteTutor, Long> {

    public Optional<DocenteTutor> findById(Long id);
    public boolean existsById(Long id);
}
