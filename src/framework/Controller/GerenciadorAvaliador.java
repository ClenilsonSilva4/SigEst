package framework.Controller;

import AplicacaoEstudantil.exception.ChangeNotMade;
import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.UserNotFoundException;
import AplicacaoEstudantil.exception.UserWithoutPermission;
import framework.DAO.AvaliadorDAOMySQL;
import framework.Domain.Gestor;
import framework.Domain.Avaliador;
import java.util.List;

public class GerenciadorAvaliador {
	private final AvaliadorDAOMySQL avaliadorDAO;
	private final GerenciadorGestor gerenciadorGestor;

	public GerenciadorAvaliador(AvaliadorDAOMySQL avaliadorDAO, GerenciadorGestor gerenciadorGestor) {
		this.avaliadorDAO = avaliadorDAO;
		this.gerenciadorGestor = gerenciadorGestor;
	}

	public void adicionarAvaliador(Gestor autor, Avaliador novoAvaliador) throws UserWithoutPermission, DBUnavailable, ChangeNotMade {
		gerenciadorGestor.validarGestor(autor);
		avaliadorDAO.adicionarAvaliador(novoAvaliador);
	}

	public void removerAvaliador(Gestor autor, long avaliadorRemovido) throws UserWithoutPermission, DBUnavailable, ChangeNotMade {
		gerenciadorGestor.validarGestor(autor);
		avaliadorDAO.removerAvaliador(avaliadorRemovido);
	}

	public void alterarAvaliador(Gestor autor, Avaliador avaliadorAlterado) throws UserWithoutPermission, DBUnavailable, ChangeNotMade {
		gerenciadorGestor.validarGestor(autor);
		avaliadorDAO.alterarAvaliador(avaliadorAlterado);
	}

	public Avaliador buscarAvaliadorPorId(long idAvaliador) throws UserNotFoundException, DBUnavailable {
		return avaliadorDAO.buscarAvaliadorPorID(idAvaliador);
	}

	public List<Avaliador> listarAvaliadores() throws UserNotFoundException, DBUnavailable {
		return avaliadorDAO.listarAvaliadores();
	}

	public boolean validarAvaliador(Avaliador usuario) {
		return usuario.validar();
	}
}
