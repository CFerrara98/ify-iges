package it.unisa.di.is.gc1.ify.DocenteTutor;

import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocenteTutorService {

    @Autowired
    DocenteTutorRepository tutorRepo;

    /**
     * Il metodo restituisce tutti i tutor ottenuti interrogando il database senza alcun criterio
     *
     * @return List<DocenteTutor> listDocentiTutor
     */
    @Transactional(rollbackFor = Exception.class)
    public List<DocenteTutor> getAllDocentiTutor() {
        List<DocenteTutor> listDocentiTutor = null;
        listDocentiTutor = tutorRepo.findAll();
        return listDocentiTutor;
    }

    /**
     * Il metodo restituisce il tutor dato l'ID
     *
     * @return DocenteTutor docenteTutor
     */
    @Transactional(rollbackFor = Exception.class)
    public DocenteTutor getDocenteTutorById(Long id) {
        DocenteTutor docenteTutor = null;
        docenteTutor = tutorRepo.findById(id).orElse(null);
        return docenteTutor;
    }

}
