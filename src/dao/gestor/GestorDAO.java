package dao.gestor;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Gestor;

public interface GestorDAO {
    void inserirGestor(String nome, String email, String senha) throws ChangeNotMade, DBUnavailable;
    Gestor consultarGestor(int idGestor) throws UserNotFoundException, DBUnavailable;
    void removerGestor(int idGestor) throws ChangeNotMade;
    void alterarGestor(Gestor gestorAlterado) throws ChangeNotMade, DBUnavailable;
}