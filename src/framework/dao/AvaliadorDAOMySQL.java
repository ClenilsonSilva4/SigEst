package framework.DAO;

import Domain.Avaliador;
import java.util.List;

public interface AvaliadorDAOMySQL {

	public void adicionarAvaliador(Avaliador novoAvaliador);

	public void removerAvaliador(Avaliador avaliadorRemovido);

	public void alterarAvaliador(Avaliador avaliadorAlterado);

	public abstract Avaliador buscarAvaliadorPorID();

	public abstract List listarAvaliadores();

}
