package AplicacaoControleQualidade.entities;

import framework.domain.Avaliador;

public class Empregado extends Avaliador {
    private String profissao;

    public Empregado(long id, String nome, String email, String senha, String profissao) {
        super(id, nome, email, senha);
        this.profissao = profissao;
    }

    public String getProfissao() {
        return profissao;
    }

    @Override
    public boolean validar() {
        return false;
    }
}
