package Domain;

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

}
