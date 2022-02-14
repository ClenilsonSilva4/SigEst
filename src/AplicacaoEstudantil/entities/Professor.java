package AplicacaoEstudantil.entities;

import framework.domain.Avaliador;

public class Professor extends Avaliador {
    private String titularidade;

    public Professor(long id, String nome, String email, String senha, String titularidade) {
        super(id, nome, email, senha);
        this.titularidade = titularidade;
    }

    public String getTitularidade() {
        return titularidade;
    }

    @Override
    public boolean validar() {
        return false;
    }
}
