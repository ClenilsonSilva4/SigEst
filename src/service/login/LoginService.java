package service.login;

import dao.usuario.UsuarioDAO;
import dao.usuario.UsuarioDAOMySQL;
import exception.DBUnavailable;
import exception.InvalidData;
import exception.UserNotFoundException;

public class LoginService implements LoginServiceInterface{
    private final UsuarioDAO usuarioBD;

    public LoginService() {
        this.usuarioBD = new UsuarioDAOMySQL();
    }

    @Override
    public Usuario checarLogin(String email, String senha) throws UserNotFoundException, DBUnavailable, InvalidData {
        if(email.length() < 11) {
            throw new InvalidData("O e-mail inserido é muito curto");
        } else if(senha.length() < 6) {
            throw new InvalidData("A senha precisa ter um tamanho mínimo de 6 caracteres");
        }

        return usuarioBD.consultarUsuario(email, senha);
    }
}
