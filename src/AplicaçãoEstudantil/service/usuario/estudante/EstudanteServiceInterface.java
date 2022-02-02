package AplicaçãoEstudantil.service.usuario.estudante;

public interface EstudanteServiceInterface {
    Recurso consultarEstudante(int idEstudante) throws DBUnavailable;
    ConjuntoRecurso consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable;
    AcompanhamentoRecurso consultarPresenca(int idPresenca);
}
