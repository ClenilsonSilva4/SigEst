package View;

import Controller.GerenciadorChat;
import Controller.GerenciadorAvaliação;
import Controller.GerenciadorAvaliador;
import Controller.GerenciadorRecurso;

public interface AvaliaçãoView {

	private GerenciadorChat gerenciadorChat;

	private GerenciadorAvaliação gerenciadorAvaliação;

	private GerenciadorAvaliador gerenciadorAvaliador;

	private GerenciadorRecurso gerenciadorRecurso;

	public abstract void realizarAvaliação();

	public abstract void consultarAvaliação();

	public abstract void alterarAvaliação();

}
