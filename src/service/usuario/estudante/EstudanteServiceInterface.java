package service.usuario.estudante;

import entities.Recurso;
import entities.AcompanhamentoRecurso;
import entities.ConjuntoRecurso;
import exception.DBUnavailable;
import exception.UserNotFoundException;

public interface EstudanteServiceInterface {
    Recurso consultarEstudante(int idEstudante) throws DBUnavailable;
    ConjuntoRecurso consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable;
    AcompanhamentoRecurso consultarPresenca(int idPresenca);
}
