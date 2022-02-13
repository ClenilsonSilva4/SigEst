package framework.DAO;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import framework.Domain.AcompanhamentoRecurso;

import java.util.List;

public interface AcompanhamentoRecursoDAOMySQL {

	void inserirAcompanhamentoRecurso(AcompanhamentoRecurso novoAcompanhamento) throws ChangeNotMade, DBUnavailable;

	void alterarAcompanhamentoRecurso(AcompanhamentoRecurso acompanhamentoAlterado) throws ChangeNotMade, DBUnavailable;

	void removerAcompanhamentoRecurso(long acompanhamentoRemovido) throws ChangeNotMade, DBUnavailable;

	List<AcompanhamentoRecurso> consultarAcompanhamentosRecurso(long idRecurso);

}
