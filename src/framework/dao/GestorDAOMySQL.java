package framework.DAO;

import AplicacaoEstudantil.exception.ChangeNotMade;
import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.EmailAlreadyInUse;
import AplicacaoEstudantil.exception.UserNotFoundException;
import framework.Domain.Gestor;
import java.util.List;

public interface GestorDAOMySQL {

	public void adicionarGestor(Gestor novoGestor) throws DBUnavailable, ChangeNotMade;

	public void removerGestor(Gestor gestorRemovido) throws ChangeNotMade, DBUnavailable;

	public void alterarGestor(Gestor gestorAlterado) throws ChangeNotMade, DBUnavailable;

	public Gestor buscarGestorPorID(long gestorID) throws UserNotFoundException, DBUnavailable;

	public List<Gestor> listarGestores() throws DBUnavailable;

	public void checarEmail(String emailGestor) throws EmailAlreadyInUse, UserNotFoundException, DBUnavailable;

}
