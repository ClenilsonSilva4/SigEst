package framework.domain;

public abstract class Avaliador {
	private long id;
	private String nome;
	private String email;
	private String senha;

	public Avaliador(long id, String nome, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public abstract boolean validar();

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
}
