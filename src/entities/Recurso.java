package entities;

public abstract class Recurso {
    private long id;
    private String nome;
    private boolean estaAprovado;

    public Recurso(String nome) {
        this.nome = nome;
        estaAprovado = false;
    }

    public boolean validar() {
        //TODO
        return false;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isEstaAprovado() {
        return estaAprovado;
    }
}
