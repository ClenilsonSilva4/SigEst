package framework.DAO;

import AplicacaoEstudantil.exception.ChangeNotMade;
import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.UserNotFoundException;
import framework.Domain.Recurso;
import java.util.List;

public interface RecursoDAOMySQL {

	public void adicionarRecurso(Recurso novoRecurso) throws ChangeNotMade, DBUnavailable;

	public void removerRecurso(Recurso recursoRemovido) throws ChangeNotMade, DBUnavailable;

	public List<Recurso> listarRecursos() throws UserNotFoundException, DBUnavailable;

	public void alterarRecurso(Recurso recursoAlterado) throws ChangeNotMade, DBUnavailable;

	public Recurso buscarRecursoPorID(long recursoID) throws UserNotFoundException, DBUnavailable;

}
