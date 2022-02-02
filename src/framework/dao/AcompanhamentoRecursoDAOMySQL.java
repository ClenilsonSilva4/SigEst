package DAO;

import Domain.AcompanhamentoRecurso;
import java.util.List;

public interface AcompanhamentoRecursoDAOMySQL {

	public void inserirAcompanhamentoRecurso(AcompanhamentoRecurso novoAcompanhamento);

	public void alterarAcompanhamentoRecurso(AcompanhamentoRecurso acompanhamentoAlterado);

	public void removerAcompanhamentoRecurso(AcompanhamentoRecurso acompanhamentoRemovido);

	public List consultarAcompanhamentosRecurso(long idRecurso);

}
