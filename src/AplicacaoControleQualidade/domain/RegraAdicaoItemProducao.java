package AplicacaoControleQualidade.domain;

import AplicacaoControleQualidade.entities.ItemProducao;
import framework.domain.Recurso;
import framework.domain.RegraRecurso;

public class RegraAdicaoItemProducao implements RegraRecurso {
    private final int idadeMinima = 14;
    @Override
    public boolean verificarRecurso(Recurso checarRecurso) {
        ItemProducao checarAluno = (ItemProducao) checarRecurso;

        return checarAluno.getIdade() >= idadeMinima && checarAluno.getNome().length() > 2 && checarAluno.getCurso().length() > 5;
    }
}
