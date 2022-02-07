package framework.DAO;

import AplicacaoEstudantil.exception.ChangeNotMade;
import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.UserNotFoundException;
import framework.Domain.Recurso;
import java.util.List;

public interface RecursoDAOMySQL {

	void adicionarRecurso(Recurso novoRecurso) throws ChangeNotMade, DBUnavailable;

	void removerRecurso(long recursoRemovido) throws ChangeNotMade, DBUnavailable;

	List<Recurso> listarRecursos() throws UserNotFoundException, DBUnavailable;

	void alterarRecurso(Recurso recursoAlterado) throws ChangeNotMade, DBUnavailable;

	Recurso buscarRecursoPorID(long recursoID) throws UserNotFoundException, DBUnavailable;

}
