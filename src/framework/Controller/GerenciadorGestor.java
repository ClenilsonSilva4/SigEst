package framework.Controller;

import AplicaçãoEstudantil.exception.*;
import framework.DAO.GestorDAOMySQL;
import framework.Domain.Gestor;

import java.util.List;

public class GerenciadorGestor {
	private final GestorDAOMySQL gestorDAO;

	public GerenciadorGestor(GestorDAOMySQL gestorDAO) {

		this.gestorDAO = gestorDAO;
	}

	public void adicionarGestor(Gestor autorAcao, Gestor novoGestor) throws EmailAlreadyInUse, ChangeNotMade,
			DBUnavailable, UserWithoutPermission {
		validarGestor(autorAcao);
		try {
			gestorDAO.checarEmail(novoGestor.getEmail());
		} catch (UserNotFoundException e) {
			gestorDAO.adicionarGestor(novoGestor);
		}
	}

	public void removerGestor(Gestor autor, Gestor excluirGestor) throws UserWithoutPermission, DBUnavailable, ChangeNotMade {
		validarGestor(autor);
		gestorDAO.removerGestor(excluirGestor);
	}

	public List<Gestor> listarGestor() throws DBUnavailable {
		return gestorDAO.listarGestores();
	}

	public Gestor buscarGestorPorId(long idGestor) throws UserNotFoundException, DBUnavailable {
		return gestorDAO.buscarGestorPorID(idGestor);
	}

	public void validarGestor(Gestor checarGestor) throws DBUnavailable, UserWithoutPermission {
		try {
			Gestor checarNoBD = buscarGestorPorId(checarGestor.getId());
			if(!checarGestor.getEmail().equals(checarNoBD.getEmail()) &&
					!checarGestor.getNome().equals(checarNoBD.getNome()) && !checarGestor.getSenha().equals(checarNoBD.getSenha())) {
				throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
			}
		} catch (UserNotFoundException e) {
			throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
		}
	}
}
