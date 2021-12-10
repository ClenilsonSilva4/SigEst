package dao.estudante;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Estudante;

public interface EstudanteDAO {
    void inserirEstudante(String nome, String email, String senha) throws ChangeNotMade;
    Estudante consultarEstudante(int idEstudante) throws UserNotFoundException, DBUnavailable;
    void removerEstudante(int idEstudante) throws ChangeNotMade, DBUnavailable;
    void alterarEstudante(Estudante estudanteAlterado) throws ChangeNotMade, DBUnavailable;
}
