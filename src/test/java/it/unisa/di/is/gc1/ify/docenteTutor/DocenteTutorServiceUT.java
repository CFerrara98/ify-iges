package it.unisa.di.is.gc1.ify.docenteTutor;

import static org.mockito.Mockito.when;

import java.util.Optional;

import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutor;
import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutorRepository;
import it.unisa.di.is.gc1.ify.DocenteTutor.DocenteTutorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


@RunWith(MockitoJUnitRunner.class)
public class DocenteTutorServiceUT {

    @Mock
    private DocenteTutorRepository tutorRepo;

    @Autowired
    @InjectMocks
    private DocenteTutorService docenteTutorService;


    @Test
    public void getAllDocentiTutor() {
        when(tutorRepo.findAll()).thenReturn(null);
        Assert.assertNull(docenteTutorService.getAllDocentiTutor());

    }

    @Test
    public void getDocenteTutorById() {
        when(tutorRepo.findById(0l)).thenReturn(Optional.of(new DocenteTutor()));
        Assert.assertEquals(docenteTutorService.getDocenteTutorById(0l).getId(), 0l);
    }
}