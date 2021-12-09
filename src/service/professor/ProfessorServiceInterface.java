package service.professor;

import exception.ChangeNotMade;
import exception.UserNotFoundException;
import entities.Presenca;
import entities.Professor;
import entities.Turma;
import exception.UserWithoutPermission;

public interface ProfessorServiceInterface {
    Professor consultarProfessor(int idProfessor) throws UserNotFoundException;
    Turma consultarTurma(int idTurma);
    void inserirPresenca(Presenca novaPresenca) throws ChangeNotMade, UserWithoutPermission;
    Presenca consultarPresenca(int idPresenca);
    void removerPresenca(int idPresenca, int idProfessor) throws ChangeNotMade, UserWithoutPermission;
    void alterarPresenca(Presenca presencaAlterada) throws UserWithoutPermission, ChangeNotMade;
}
