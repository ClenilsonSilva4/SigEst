package AplicaçãoEstudantil.dao.turma;

import framework.entities.ConjuntoRecurso;
import framework.exception.ChangeNotMade;
import framework.exception.DBUnavailable;
import framework.exception.UserNotFoundException;

public interface TurmaDAO {
    void inserirTurma(String nomeDisciplina, int capacidadeAlunos) throws ChangeNotMade, DBUnavailable;
    ConjuntoRecurso consultarTurma(int idTurma) throws DBUnavailable, UserNotFoundException;
    void removerTurma(int idTurma) throws ChangeNotMade, DBUnavailable;
    void alterarTurma(ConjuntoRecurso conjuntoRecursoAlterada) throws ChangeNotMade, DBUnavailable;
}
