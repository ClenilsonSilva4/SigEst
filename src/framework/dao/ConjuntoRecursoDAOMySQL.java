package framework.DAO;

import AplicacaoEstudantil.exception.ChangeNotMade;
import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.UserNotFoundException;
import framework.Domain.ConjuntoRecurso;
import java.util.List;

public interface ConjuntoRecursoDAOMySQL {
	void adicionarConjuntoRecurso(ConjuntoRecurso novoConjunto) throws ChangeNotMade, DBUnavailable;

	void alterarConjuntoRecurso(ConjuntoRecurso ambienteAlterado) throws ChangeNotMade, DBUnavailable;

	void removerConjuntoRecurso(ConjuntoRecurso ambienteRemovido) throws ChangeNotMade, DBUnavailable;

	List<ConjuntoRecurso> consultarConjuntosRecurso();

	ConjuntoRecurso consultarConjuntoID(long idConjunto) throws UserNotFoundException, DBUnavailable;
}
