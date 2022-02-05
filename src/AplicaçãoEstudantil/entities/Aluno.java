package AplicaçãoEstudantil.entities;

import framework.Domain.Recurso;

public class Aluno extends Recurso {
    private String curso;

    public Aluno(long id, String nome, boolean estaAprovado, String curso) {
        super(id, nome, estaAprovado);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }
}
