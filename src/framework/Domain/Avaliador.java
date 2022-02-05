package framework.Domain;

public abstract class Avaliador {

	private long id;

	private String nome;

	private String email;

	private String senha;

	public void Avaliador(String nome, String email, String senha) {

	}

	public boolean validar() {
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
}
