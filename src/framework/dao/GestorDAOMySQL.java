package framework.dao;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.EmailAlreadyInUse;
import exception.UserNotFoundException;
import framework.domain.Gestor;
import java.util.List;

public interface GestorDAOMySQL {

	void adicionarGestor(Gestor novoGestor) throws DBUnavailable, ChangeNotMade;

	void removerGestor(long gestorRemovido) throws ChangeNotMade, DBUnavailable;

	void alterarGestor(Gestor gestorAlterado) throws ChangeNotMade, DBUnavailable;

	Gestor buscarGestorPorID(long gestorID) throws UserNotFoundException, DBUnavailable;

	List<Gestor> listarGestores() throws DBUnavailable;

	void checarEmail(String emailGestor) throws EmailAlreadyInUse, UserNotFoundException, DBUnavailable;

}
