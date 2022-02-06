package framework.Controller;

import AplicacaoEstudantil.exception.ChangeNotMade;
import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.UserNotFoundException;
import AplicacaoEstudantil.exception.UserWithoutPermission;
import framework.DAO.RecursoDAOMySQL;
import framework.Domain.Gestor;
import framework.Domain.Recurso;
import framework.Domain.RegraRecurso;

import java.util.List;

public class GerenciadorRecurso {
	private final RecursoDAOMySQL recursoDAO;
	private final RegraRecurso adicaoRecurso;
	private final GerenciadorGestor gestorService;

	public GerenciadorRecurso(RecursoDAOMySQL recursoDAO, RegraRecurso adicaoRecurso, GerenciadorGestor gestorService) {
		this.recursoDAO = recursoDAO;
		this.adicaoRecurso = adicaoRecurso;
		this.gestorService = gestorService;
	}

	public void adicionarRecurso(Gestor autor, Recurso novoRecurso) throws ChangeNotMade, DBUnavailable, UserWithoutPermission {
		gestorService.validarGestor(autor);
		if(adicaoRecurso.verificarRecurso(novoRecurso)) {
			recursoDAO.adicionarRecurso(novoRecurso);
		}
	}

	public void removerRecurso(Gestor autor, long recursoRemovido) throws UserWithoutPermission, DBUnavailable, ChangeNotMade {
		gestorService.validarGestor(autor);
		recursoDAO.removerRecurso(recursoRemovido);
	}

	public List<Recurso> listarRecursos() throws UserNotFoundException, DBUnavailable {
		return recursoDAO.listarRecursos();
	}

	public void alterarRecurso(Gestor autor, Recurso recursoAlterado) throws UserWithoutPermission, DBUnavailable, ChangeNotMade {
		gestorService.validarGestor(autor);
		recursoDAO.alterarRecurso(recursoAlterado);
	}

	public Recurso buscarRecursoPorId(long idRecurso) throws UserNotFoundException, DBUnavailable {
		return recursoDAO.buscarRecursoPorID(idRecurso);
	}
}
