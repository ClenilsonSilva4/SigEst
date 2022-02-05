package AplicaçãoEstudantil.entities;

import framework.Domain.Recurso;

public class Aluno extends Recurso {
    private String curso;
    private int idade;

    public Aluno(long id, String nome, boolean estaAprovado, String curso, int idade) {
        super(id, nome, estaAprovado);
        this.curso = curso;
        this.idade = idade;
    }

    public String getCurso() {
        return curso;
    }

    public int getIdade() {
        return idade;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
