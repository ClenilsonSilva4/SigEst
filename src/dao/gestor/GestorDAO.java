package dao.gestor;

import exception.ChangeNotMade;
import exception.UserNotFoundException;
import entities.Gestor;

public interface GestorDAO {
    void inserirGestor(String nome, String email, String senha) throws ChangeNotMade;
    Gestor consultarGestor(int idGestor) throws UserNotFoundException;
    void removerGestor(int idGestor) throws ChangeNotMade;
    void alterarGestor(Gestor gestorAlterado) throws UserNotFoundException, ChangeNotMade;
}