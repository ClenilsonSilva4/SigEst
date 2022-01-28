package service.usuario.professor;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.AcompanhamentoRecurso;
import entities.Avaliador;
import entities.ConjuntoRecurso;
import exception.UserWithoutPermission;

public interface ProfessorServiceInterface {
    Avaliador consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable;
    ConjuntoRecurso consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable;
    void inserirPresenca(AcompanhamentoRecurso novaAcompanhamentoRecurso) throws ChangeNotMade, UserWithoutPermission, DBUnavailable;
    AcompanhamentoRecurso consultarPresenca(int idPresenca);
    void removerPresenca(int idPresenca, int idProfessor) throws ChangeNotMade, UserWithoutPermission, DBUnavailable;
    void alterarPresenca(AcompanhamentoRecurso acompanhamentoRecursoAlterada) throws UserWithoutPermission, ChangeNotMade, DBUnavailable;
}
