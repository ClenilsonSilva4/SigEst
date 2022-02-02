package View;

import Controller.GerenciadorGestor;

public interface GestorView {

	private GerenciadorGestor gerenciadorGestor;

	public abstract void inserirGestor();

	public abstract void consultarGestor();

	public abstract void removerGestor();

	public abstract void alterarGestor();

}
