package AplicacaoMercado.entities;

import framework.Domain.Recurso;

public class Produtos extends Recurso {
    private String validade;

    public Produtos(long id, String nome, boolean estaAprovado, String validade) {
        super(id, nome, estaAprovado);
        this.validade = validade;
    }

    @Override
    public boolean validarRecurso() {
        return false;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
