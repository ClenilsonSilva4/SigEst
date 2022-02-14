package AplicacaoControleQualidade.entities;

import framework.domain.Avaliador;

public class Empregado extends Avaliador {
    private String titularidade;

    public Empregado(long id, String nome, String email, String senha, String titularidade) {
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
