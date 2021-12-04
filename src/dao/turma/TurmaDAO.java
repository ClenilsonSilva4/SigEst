package dao.turma;

import service.Turma;

public interface TurmaDAO {
    void inserirTurma(Turma novaTurma);
    Turma consultarTurma(int idTurma);
    void removerTurma(int idTurma);
    void alterarTurma(Turma turmaAlterada);
}
