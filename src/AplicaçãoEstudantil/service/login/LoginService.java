package AplicaçãoEstudantil.service.login;

import AplicaçãoEstudantil.dao.RecursoDAOMySQL;
import AplicaçãoEstudantil.exception.DBUnavailable;
import AplicaçãoEstudantil.exception.InvalidData;
import AplicaçãoEstudantil.exception.UserNotFoundException;

public class LoginService implements LoginServiceInterface{
    private final  usuarioBD;

    public LoginService() {
        this.usuarioBD = new RecursoDAOMySQL();
    }

    @Override
    public Gestor checarLogin(String email, String senha) throws UserNotFoundException, DBUnavailable, InvalidData {
        if(email.length() < 11) {
            throw new InvalidData("O e-mail inserido � muito curto");
        } else if(senha.length() < 6) {
            throw new InvalidData("A senha precisa ter um tamanho m�nimo de 6 caracteres");
        }

        return usuarioBD.consultarUsuario(email, senha);
    }
}
