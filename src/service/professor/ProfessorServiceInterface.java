package service.professor;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Presenca;
import entities.Professor;
import entities.Turma;
import exception.UserWithoutPermission;

public interface ProfessorServiceInterface {
    Professor consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable;
    Turma consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable;
    void inserirPresenca(Presenca novaPresenca) throws ChangeNotMade, UserWithoutPermission, DBUnavailable;
    Presenca consultarPresenca(int idPresenca);
    void removerPresenca(int idPresenca, int idProfessor) throws ChangeNotMade, UserWithoutPermission, DBUnavailable;
    void alterarPresenca(Presenca presencaAlterada) throws UserWithoutPermission, ChangeNotMade, DBUnavailable;
}
