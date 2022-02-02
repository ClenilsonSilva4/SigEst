package DAO;

import Domain.Gestor;
import java.util.List;

public interface GestorDAOMySQL {

	public void adicionarGestor(Gestor novoGestor);

	public void removerGestor(Gestor gestorRemovido);

	public void alterarGestor(Gestor gestorAlterado);

	public Gestor buscarGestorPorID(long gestorID);

	public List listarGestores();

}
