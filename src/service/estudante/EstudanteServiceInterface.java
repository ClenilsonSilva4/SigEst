package service.estudante;

import service.Presenca;
import service.Turma;
import java.util.Map;

public interface EstudanteServiceInterface {
    Estudante consultarEstudante(int idEstudante);
    Turma consultarTurma(int idTurma);
    Map<String, Presenca> consultarPresenca(int idTurma, int idEstudante);
}
