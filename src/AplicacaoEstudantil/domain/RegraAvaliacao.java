package AplicacaoEstudantil.domain;

import framework.domain.ConjuntoRecurso;

import java.util.List;

public class RegraAvaliacao implements framework.domain.RegraAvaliacao {

    @Override
    public boolean verificarAprovacao(List<ConjuntoRecurso> conjuntoRecurso) {
        return false;
    }
}
