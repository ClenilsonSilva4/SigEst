package AplicaçãoEstudantil.domain;

import AplicaçãoEstudantil.entities.Aluno;
import framework.Domain.Recurso;
import framework.Domain.RegraRecurso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegraAdicaoAluno implements RegraRecurso {
    @Override
    public boolean verificarRecurso(Recurso checarRecurso) {
        Aluno checarAluno = (Aluno) checarRecurso;

        return checarAluno.getIdade() > 14 && checarAluno.getNome().length() > 2 && checarAluno.getCurso().length() > 5;
    }
}
