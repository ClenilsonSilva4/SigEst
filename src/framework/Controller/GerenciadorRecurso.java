package framework.Controller;

import framework.Domain.Gestor;
import framework.Domain.Recurso;
import View.RecursoView;
import framework.DAO.RecursoDAO;
import java.util.List;

public class GerenciadorRecurso extends Gestor {

	private RecursoView recursoView;

	private Recurso recurso;

	private RecursoDAO recursoDAO;

	public void adicionarRecurso(Recurso novoRecurso) {

	}

	public void removerRecurso(Recurso recursoRemovido) {

	}

	public List listarRecursos() {
		return null;
	}

	public void alterarRecurso(Recurso recursoAlterado) {

	}

	public Recurso buscarRecursoPorId(long idRecurso) {
		return null;
	}

}
