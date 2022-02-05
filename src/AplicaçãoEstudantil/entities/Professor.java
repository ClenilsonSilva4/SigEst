package AplicaçãoEstudantil.entities;

import framework.Domain.Avaliador;

public class Professor extends Avaliador {
    private String titularidade;

    public Professor(long id, String nome, String email, String senha, String titularidade) {
        super(id, nome, email, senha);
        this.titularidade = titularidade;
    }

    public String getTitularidade() {
        return titularidade;
    }
}
