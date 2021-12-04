package service.professor;

import service.Presenca;
import service.Turma;
import java.util.Map;

public interface ProfessorServiceInterface {
    Professor consultarProfessor(int idProfessor);
    Turma consultarTurma(int idTurma);
    String inserirPresenca(Presenca novaPresenca);
    Map<String, Presenca> consultarPresenca(int idPresenca, int idTurma);
    String removerPresenca(int idPresenca, int idProfessor);
    String alterarPresenca(Presenca presencaAlterada);
}
