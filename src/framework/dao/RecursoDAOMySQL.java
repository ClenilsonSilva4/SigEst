package framework.DAO;

import Domain.Recurso;
import java.util.List;

public interface RecursoDAOMySQL {

	public void adicionarRecurso(Recurso novoRecurso);

	public void removerRecurso(Recurso recursoRemovido);

	public List<Recurso> listarRecursos();

	public void alterarRecurso(Recurso recursoAlterado);

	public Recurso buscarRecursoPorID(long recursoID);

}
