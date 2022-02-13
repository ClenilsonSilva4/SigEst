package framework.DAO;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import framework.Domain.Avaliador;

import java.util.List;

public interface AvaliadorDAOMySQL {

	void adicionarAvaliador(Avaliador novoAvaliador) throws DBUnavailable, ChangeNotMade;

	void removerAvaliador(long avaliadorRemovido) throws ChangeNotMade, DBUnavailable;

	void alterarAvaliador(Avaliador avaliadorAlterado) throws ChangeNotMade, DBUnavailable;

	Avaliador buscarAvaliadorPorID(long idAvaliador) throws UserNotFoundException, DBUnavailable;

	List<Avaliador> listarAvaliadores() throws DBUnavailable, UserNotFoundException;

}
