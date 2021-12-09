package dao.turma;

import entities.Turma;

public interface TurmaDAO {
    void inserirTurma(Turma novaTurma);
    Turma consultarTurma(int idTurma);
    void removerTurma(int idTurma);
    void alterarTurma(Turma turmaAlterada);
}
