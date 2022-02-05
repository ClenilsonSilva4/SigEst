package framework.Domain;

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

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	protected void setId(long id) {
		this.id = id;
	}
}
