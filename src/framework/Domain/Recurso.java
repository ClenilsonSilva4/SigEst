package framework.Domain;

public abstract class Recurso {
	private long id;
	private String nome;
	private boolean estaAprovado;

	public Recurso(long id, String nome, boolean estaAprovado) {
		this.id = id;
		this.nome = nome;
		this.estaAprovado = estaAprovado;
	}

	public abstract boolean validarRecurso();

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
