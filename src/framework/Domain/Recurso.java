package framework.Domain;

public abstract class Recurso {
	private long id;
	private String nome;
	private boolean estaAprovado;

	public boolean validar() {
		return false;
	}

	public Recurso(long id, String nome, boolean estaAprovado) {
		this.id = id;
		this.nome = nome;
		this.estaAprovado = estaAprovado;
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
