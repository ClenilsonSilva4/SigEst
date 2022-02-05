package framework.DAO;

import AplicaçãoEstudantil.exception.ChangeNotMade;
import AplicaçãoEstudantil.exception.DBUnavailable;
import AplicaçãoEstudantil.exception.UserNotFoundException;
import framework.Domain.Recurso;
import java.util.List;

public interface RecursoDAOMySQL {

	public void adicionarRecurso(Recurso novoRecurso) throws ChangeNotMade, DBUnavailable;

	public void removerRecurso(Recurso recursoRemovido) throws ChangeNotMade, DBUnavailable;

	public List<Recurso> listarRecursos() throws UserNotFoundException, DBUnavailable;

	public void alterarRecurso(Recurso recursoAlterado) throws ChangeNotMade, DBUnavailable;

	public Recurso buscarRecursoPorID(long recursoID) throws UserNotFoundException, DBUnavailable;

}
