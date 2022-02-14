package framework.controller;

import exception.*;
import framework.dao.GestorDAOMySQL;
import framework.domain.Gestor;

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

	public void alterarGestor(Gestor autor, Gestor alterarGestor) throws UserWithoutPermission, DBUnavailable, ChangeNotMade, EmailAlreadyInUse {
		validarGestor(autor);
		try {
			gestorDAO.checarEmail(alterarGestor.getEmail());
		} catch (UserNotFoundException e) {
			gestorDAO.alterarGestor(alterarGestor);
		}
	}

	public void removerGestor(Gestor autor, long excluirGestor) throws UserWithoutPermission, DBUnavailable, ChangeNotMade {
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
