package framework.DAO;

import AplicacaoEstudantil.exception.ChangeNotMade;
import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.EmailAlreadyInUse;
import AplicacaoEstudantil.exception.UserNotFoundException;
import framework.Domain.Gestor;
import java.util.List;

public interface GestorDAOMySQL {

	void adicionarGestor(Gestor novoGestor) throws DBUnavailable, ChangeNotMade;

	void removerGestor(long gestorRemovido) throws ChangeNotMade, DBUnavailable;

	void alterarGestor(Gestor gestorAlterado) throws ChangeNotMade, DBUnavailable;

	Gestor buscarGestorPorID(long gestorID) throws UserNotFoundException, DBUnavailable;

	List<Gestor> listarGestores() throws DBUnavailable;

	void checarEmail(String emailGestor) throws EmailAlreadyInUse, UserNotFoundException, DBUnavailable;

}
