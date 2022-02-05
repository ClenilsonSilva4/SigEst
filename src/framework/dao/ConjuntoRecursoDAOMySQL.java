package framework.DAO;

import framework.Domain.ConjuntoRecurso;
import java.util.List;

public interface ConjuntoRecursoDAOMySQL {

	public void inserirAmbienteRecurso(int novoAmbiente);

	public void alterarAmbienteRecurso(ConjuntoRecurso ambienteAlterado);

	public void removerAmbienteRecurso(ConjuntoRecurso ambienteRemovido);

	public List consultarAmbientesRecurso();

}
