package framework.DAO;

import AplicaçãoEstudantil.exception.ChangeNotMade;
import AplicaçãoEstudantil.exception.DBUnavailable;
import AplicaçãoEstudantil.exception.UserNotFoundException;
import framework.Domain.Avaliador;

import java.util.List;

public interface AvaliadorDAOMySQL {

	public void adicionarAvaliador(Avaliador novoAvaliador) throws DBUnavailable, ChangeNotMade;

	public void removerAvaliador(Avaliador avaliadorRemovido) throws ChangeNotMade, DBUnavailable;

	public void alterarAvaliador(Avaliador avaliadorAlterado) throws ChangeNotMade, DBUnavailable;

	public abstract Avaliador buscarAvaliadorPorID(long idAvaliador) throws UserNotFoundException, DBUnavailable;

	public abstract List listarAvaliadores() throws DBUnavailable, UserNotFoundException;

}
