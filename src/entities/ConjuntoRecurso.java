package entities;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoRecurso {
    private long idConjunto;
    private String nomeConjunto;
    private List<Avaliador> avaliadoresConjunto;
    private List<Recurso> recursosConjuntos;

    public ConjuntoRecurso(String nomeConjunto) {
        this.nomeConjunto = nomeConjunto;
        avaliadoresConjunto = new ArrayList<>();
        recursosConjuntos = new ArrayList<>();
    }

    public long getIdConjunto() {
        return idConjunto;
    }

    public String getNomeConjunto() {
        return nomeConjunto;
    }

    public List<Avaliador> getAvaliadoresConjunto() {
        return avaliadoresConjunto;
    }

    public List<Recurso> getRecursosConjuntos() {
        return recursosConjuntos;
    }
}
