package AplicaçãoEstudantil.service.usuario.professor;

import AplicaçãoEstudantil.exception.ChangeNotMade;
import AplicaçãoEstudantil.exception.DBUnavailable;
import AplicaçãoEstudantil.exception.UserNotFoundException;
import AplicaçãoEstudantil.exception.UserWithoutPermission;

public interface ProfessorServiceInterface {
    Avaliador consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable;
    ConjuntoRecurso consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable;
    void inserirPresenca(AcompanhamentoRecurso novaAcompanhamentoRecurso) throws ChangeNotMade, UserWithoutPermission, DBUnavailable;
    AcompanhamentoRecurso consultarPresenca(int idPresenca);
    void removerPresenca(int idPresenca, int idProfessor) throws ChangeNotMade, UserWithoutPermission, DBUnavailable;
    void alterarPresenca(AcompanhamentoRecurso acompanhamentoRecursoAlterada) throws UserWithoutPermission, ChangeNotMade, DBUnavailable;
}
