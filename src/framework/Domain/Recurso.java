package framework.Domain;

public abstract class Recurso {
	private final long id;
	private final String nome;
	private boolean estaAprovado;

	public Recurso(long id, String nome) {
		this.id = id;
		this.nome = nome;
		this.estaAprovado = false;
	}

	public Recurso(long id, String nome, boolean estaAprovado) {
		this.id = id;
		this.nome = nome;
		this.estaAprovado = estaAprovado;
	}

	public abstract boolean validarRecurso();

	public void setEstaAprovado(boolean estaAprovado) {
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
