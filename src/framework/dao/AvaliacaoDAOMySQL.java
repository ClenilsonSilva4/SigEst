package framework.dao;

import framework.domain.Avaliacao;

import java.util.List;

public interface AvaliacaoDAOMySQL {

	public void salvarAvaliacao(Avaliacao avaliacao);

	public void excluirAvaliacao(Avaliacao avaliacaoRemovida);

	public void alterarAvaliacao(Avaliacao avaliacaoAlterada);

	public List listarAvaliacoes();

	public Avaliacao obterAvaliacao(long avaliacaoID);

}
