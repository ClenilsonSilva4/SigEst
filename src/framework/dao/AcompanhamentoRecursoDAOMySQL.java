package framework.dao;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import framework.domain.AcompanhamentoRecurso;

import java.util.List;

public interface AcompanhamentoRecursoDAOMySQL {

	void inserirAcompanhamentoRecurso(AcompanhamentoRecurso novoAcompanhamento) throws ChangeNotMade, DBUnavailable;

	void alterarAcompanhamentoRecurso(AcompanhamentoRecurso acompanhamentoAlterado) throws ChangeNotMade, DBUnavailable;

	void removerAcompanhamentoRecurso(long acompanhamentoRemovido) throws ChangeNotMade, DBUnavailable;

	List<AcompanhamentoRecurso> consultarAcompanhamentosRecurso(long idRecurso);

}
