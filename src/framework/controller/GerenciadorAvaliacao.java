package framework.controller;

import framework.dao.AvaliacaoDAOMySQL;
import framework.domain.AcompanhamentoRecurso;
import framework.domain.Avaliacao;
import framework.domain.RegraAvaliacao;

import java.util.List;

public class GerenciadorAvaliacao {

	private AvaliacaoDAOMySQL avaliacaoDAO;
	private AcompanhamentoRecurso acompanhamentoRecurso;
	private RegraAvaliacao regraAvaliacao;

	public void realizarAvaliacao(Avaliacao novaAvaliacao) {

	}

	public Avaliacao obterAvaliacao(long avaliacaoID) {
		return null;
	}

	public void alterarAvaliacao(Avaliacao avaliacaoAlterada) {

	}

	public List<Avaliacao> listarAvaliacoes() {
		return null;
	}

	public boolean verificarAprovacaoRecurso(long recursoID) {
		return false;
	}

}
