package AplicacaoControleQualidade.entities;

import framework.domain.Recurso;

public class ItemProducao extends Recurso {
    private String curso;
    private int idade;
    private final String email;
    private final String senha;

    public ItemProducao(long id, String nome, String curso, int idade, String email, String senha) {
        super(id, nome);
        this.curso = curso;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
    }

    public ItemProducao(long id, String nome, boolean estaAprovado, String curso, int idade, String email, String senha) {
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
