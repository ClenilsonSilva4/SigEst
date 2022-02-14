package AplicacaoControleQualidade.domain;

import framework.Domain.ConjuntoRecurso;

import java.util.List;

public class RegraAvaliacao implements framework.Domain.RegraAvaliacao {

    @Override
    public boolean verificarAprovacao(List<ConjuntoRecurso> conjuntoRecurso) {
        return false;
    }
}
