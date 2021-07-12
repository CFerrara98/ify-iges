package it.unisa.di.is.gc1.ify.DocenteTutor;

import it.unisa.di.is.gc1.ify.utenza.Utente;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Francesco Abate e Carmine Ferrara
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DocenteTutor extends Utente {

    private String campoRicerca = null;

    /**
     * Costruttore che crea un oggetto DocenteTutor vuoto,
     * che verra' popolato con i metodi setters.
     */
    public DocenteTutor() {super();}

    /**
     * Costruttore di un responsabile ufficio tirocini con parametri utile nei casi di test.
     * @param nome e' il nome del responsabile ufficio tirocini.
     * @param cognome e' il cognome del responsabile ufficio tirocini.
     * @param sesso e' il sesso del responsabile ufficio tirocini.
     * @param email e' l'email del responsabile ufficio tirocini.
     * @param indirizzo e' l'indirizzo del responsabile ufficio tirocini.
     * @param password e' la password del responsabile ufficio tirocini.
     * @param campoRicerca e' il campo di ricerca di cui si occupa il docente.
     */
    public DocenteTutor(String nome, String cognome, String sesso, String email, String indirizzo, String password, String campoRicerca) {
        super(nome, cognome, sesso, email, indirizzo, password);
        this.campoRicerca = campoRicerca;
    }

    /**
     * Metodo che ritorna il campo di ricerca del docente.
     * @return il compo di ricerca del docente.
     */
    public String getCampoRicerca() {
        return campoRicerca;
    }

    /**
     * Metodo che setta il campo di ricerca del docente.
     * @param campoRicerca il campo di ricerca del docente.
     */
    public void setCampoRicerca(String campoRicerca) {
        this.campoRicerca = campoRicerca;
    }

    /**
     * Espressione regolare che definisce il formato del campo di ricerca.
     */
    public static final String CAMPORICERCA_PATTERN = "^[a-z A-Z àéèìòù]{3,255}$";

    /**
     * Costante che definisce la minima lunghezza del campo di ricerca.
     */
    public static final int MIN_LUNGHEZZA_CAMPORICERCA = 3;

    /**
     * Costante che definisce la massima lunghezza del campo di ricerca.
     */
    public static final int MAX_LUNGHEZZA_CAMPODIRICERCA= 255;

}
