package dao.professor;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Professor;

public interface ProfessorDAO {
    void inserirProfessor(String nome, String email, String senha) throws ChangeNotMade, DBUnavailable;
    Professor consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable;
    void removerProfessor(int idProfessor) throws ChangeNotMade;
    void alterarProfessor(Professor professorAlterado) throws ChangeNotMade, DBUnavailable;
}
