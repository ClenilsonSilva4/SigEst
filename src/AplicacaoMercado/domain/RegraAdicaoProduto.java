package AplicacaoMercado.domain;

import AplicacaoEstudantil.entities.Aluno;
import framework.domain.Recurso;
import framework.domain.RegraRecurso;

public class RegraAdicaoProduto implements RegraRecurso {
    private final int validadeMinima = 30;
    @Override
    public boolean verificarRecurso(Recurso checarRecurso) {
        Aluno checarAluno = (Aluno) checarRecurso;

        return checarAluno.getIdade() >= validadeMinima && checarAluno.getNome().length() > 2 && checarAluno.getCurso().length() > 5;
    }
}
