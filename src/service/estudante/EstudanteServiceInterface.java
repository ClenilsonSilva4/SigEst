package service.estudante;

import entities.Estudante;
import entities.Presenca;
import entities.Turma;
import java.util.Map;

public interface EstudanteServiceInterface {
    Estudante consultarEstudante(int idEstudante);
    Turma consultarTurma(int idTurma);
    Presenca consultarPresenca(int idPresenca);
}
