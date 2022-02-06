package AplicacaoEstudantil.entities;

import framework.Domain.Recurso;

public class Aluno extends Recurso {
    private String curso;
    private int idade;
    private String email;
    private String senha;

    public Aluno(long id, String nome, boolean estaAprovado, String curso, int idade, String email, String senha) {
        super(id, nome, estaAprovado);
        this.curso = curso;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
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

    @Override
    public boolean validarRecurso() {
        return false;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
