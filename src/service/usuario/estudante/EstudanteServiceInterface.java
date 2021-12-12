package service.usuario.estudante;

import entities.Estudante;
import entities.Presenca;
import entities.Turma;
import exception.DBUnavailable;
import exception.UserNotFoundException;

public interface EstudanteServiceInterface {
    Estudante consultarEstudante(int idEstudante) throws DBUnavailable;
    Turma consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable;
    Presenca consultarPresenca(int idPresenca);
}
