package dao.turma;

import entities.Turma;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;

public interface TurmaDAO {
    void inserirTurma(String nomeDisciplina, int capacidadeAlunos) throws ChangeNotMade, DBUnavailable;
    Turma consultarTurma(int idTurma) throws DBUnavailable, UserNotFoundException;
    void removerTurma(int idTurma) throws ChangeNotMade, DBUnavailable;
    void alterarTurma(Turma turmaAlterada) throws ChangeNotMade, DBUnavailable;
}
