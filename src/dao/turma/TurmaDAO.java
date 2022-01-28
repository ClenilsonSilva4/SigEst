package dao.turma;

import entities.ConjuntoRecurso;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;

public interface TurmaDAO {
    void inserirTurma(String nomeDisciplina, int capacidadeAlunos) throws ChangeNotMade, DBUnavailable;
    ConjuntoRecurso consultarTurma(int idTurma) throws DBUnavailable, UserNotFoundException;
    void removerTurma(int idTurma) throws ChangeNotMade, DBUnavailable;
    void alterarTurma(ConjuntoRecurso conjuntoRecursoAlterada) throws ChangeNotMade, DBUnavailable;
}
