package Controller;

import DAO.AvaliaçãoDAO;
import Domain.Avaliação;
import Domain.AcompanhamentoRecurso;
import Domain.RegraAvaliação;
import java.util.List;

public class GerenciadorAvaliação {

	private AvaliaçãoDAO avaliaçãoDAO;

	private Avaliação avaliação;

	private AcompanhamentoRecurso acompanhamentoRecurso;

	private RegraAvaliação regraAvaliação;

	public void realizarAvaliação(Avaliação novaAvaliação) {

	}

	public Avaliação obterAvaliação(long avaliaçãoID) {
		return null;
	}

	public void alterarAvaliação(Avaliação avaliaçãoAlterada) {

	}

	public List listarAvaliações() {
		return null;
	}

	public boolean verificarAprovaçãoRecurso(long recursoID) {
		return false;
	}

}
