package service.professor;

import service.Presenca;
import service.Turma;
import java.util.Map;

public interface ProfessorServiceInterface {
    Professor consultarProfessor(int idProfessor);
    Turma consultarTurma(int idTurma);
    void inserirPresenca(Presenca novaPresenca);
    Map<String, Presenca> consultarPresenca(int idPresenca, int idTurma);
    void removerPresenca(int idPresenca, int idProfessor);
    void alterarPresenca(Presenca presencaAlterada);
}
