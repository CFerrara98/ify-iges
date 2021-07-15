package it.unisa.di.is.gc1.ify.docenteTutor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.print.Doc;
import javax.transaction.Transactional;

import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutor;
import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutorRepository;
import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.is.gc1.ify.Studente.RichiestaIscrizioneRepository;
import it.unisa.di.is.gc1.ify.Studente.Studente;
import it.unisa.di.is.gc1.ify.Studente.StudenteRepository;
import it.unisa.di.is.gc1.ify.convenzioni.AziendaRepository;
import it.unisa.di.is.gc1.ify.convenzioni.DelegatoAziendaleRepository;
import it.unisa.di.is.gc1.ify.convenzioni.RichiestaConvenzionamentoRepository;
import it.unisa.di.is.gc1.ify.domandaTirocinio.DomandaTirocinioRepository;
import it.unisa.di.is.gc1.ify.progettoFormativo.ProgettoFormativoRepository;
import it.unisa.di.is.gc1.ify.responsabileUfficioTirocini.ResponsabileUfficioTirociniRepository;

import org.junit.Before;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class DocenteTutorServiceRepositoryIT {

    @Autowired
    private DocenteTutorRepository docenteTutorRepository;

    @Autowired
    private DocenteTutorService docenteTutorService;

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
    public void getAllDocentiTutor() {
        List<DocenteTutor> lista = docenteTutorService.getAllDocentiTutor();
        for (DocenteTutor doc : lista) {
            Assert.assertTrue(listaDocenti.contains(doc));
        }
    }

    @Test
    public void getDocenteTutorById() {
        Assert.assertEquals(docenteTutorService.getDocenteTutorById(docenteTutor.getId()).getId(), docenteTutor.getId());
    }
}
