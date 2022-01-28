package dao.usuario;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;

public interface UsuarioDAO {
    void inserirUsuario(String nome, String email, String senha) throws ChangeNotMade, DBUnavailable;
    Usuario consultarUsuario(int idUsuario) throws UserNotFoundException, DBUnavailable;
    Usuario consultarUsuario(String emailUsuario, String senha) throws UserNotFoundException, DBUnavailable;
    void removerUsuario(int idUsuario) throws ChangeNotMade, DBUnavailable;
    void alterarUsuario(Usuario usuarioAlterado) throws ChangeNotMade, DBUnavailable;
}
