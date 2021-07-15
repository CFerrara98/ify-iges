package it.unisa.di.is.gc1.ify.docenteTutor;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutor;
import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutorRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirocini;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.convenzioni.AziendaRepository;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendaleRepository;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.utenza.UtenteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DocenteTutorRepositoryIT {

    @Autowired
    private DocenteTutorRepository docenteTutorRepository;

    private DocenteTutor docenteTutor;
    private List<DocenteTutor> listaDocenti;

    @Before
    public void setUp() {
        //testSupporter.clearDB();

        docenteTutorRepository.deleteAll();


        //cresazione e implementazione di un docente tutor
        docenteTutor = new DocenteTutor();
        docenteTutor.setEmail("docente@prova.it");
        docenteTutor.setCognome("Di Prova");
        docenteTutor.setNome("Docente");
        docenteTutor.setIndirizzo("via tal dei tali 1");
        docenteTutor.setSesso("M");
        docenteTutor.setCampoRicerca("Ingegneria");
        docenteTutor.setPassword("Password1");


        //salvataggio del docente nel database
        docenteTutorRepository.save(docenteTutor);
        listaDocenti = new ArrayList<>();
        listaDocenti.add(docenteTutor);
    }

    @Test
    public void findById() {
        for(DocenteTutor doc : listaDocenti) {
            DocenteTutor docente = docenteTutorRepository.findById(doc.getId()).orElse(null);
            assertTrue(listaDocenti.contains(docente));
        }
    }

    @Test
    public void existsById() {

        assertTrue(docenteTutorRepository.existsById(docenteTutor.getId()));

    }
}
