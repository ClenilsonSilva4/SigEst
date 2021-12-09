package dao.professor;

import exception.ChangeNotMade;
import exception.UserNotFoundException;
import entities.Professor;

public interface ProfessorDAO {
    void inserirProfessor(String nome, String email, String senha) throws ChangeNotMade;
    Professor consultarProfessor(int idProfessor) throws UserNotFoundException;
    void removerProfessor(int idProfessor) throws ChangeNotMade;
    void alterarProfessor(Professor professorAlterado) throws UserNotFoundException, ChangeNotMade;
}
