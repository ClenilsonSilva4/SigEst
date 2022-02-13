package AplicacaoMercado.entities;

import framework.Domain.Avaliador;

public class Estocador extends Avaliador {
    private String dataAdmissao;

    public Estocador(long id, String nome, String email, String senha, String dataAdmissao) {
        super(id, nome, email, senha);
        this.dataAdmissao = dataAdmissao;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    @Override
    public boolean validar() {
        return false;
    }
}
