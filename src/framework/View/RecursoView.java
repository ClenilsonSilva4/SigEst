package View;

import Controller.GerenciadorRecurso;

public interface RecursoView {

	private GerenciadorRecurso gerenciadorRecurso;

	public abstract void inserirRecurso();

	public abstract void consultarRecurso();

	public abstract void removerRecurso();

	public abstract void alterarRecurso();

}
