package AplicacaoEstudantil.service.login;

import AplicacaoEstudantil.dao.AlunoDAOMySQL;
import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.InvalidData;
import AplicacaoEstudantil.exception.UserNotFoundException;
import framework.Domain.Gestor;

public class LoginService implements LoginServiceInterface{
    private final AlunoDAOMySQL usuarioBD;

    public LoginService() {
        this.usuarioBD = new AlunoDAOMySQL();
    }

    @Override
    public Gestor checarLogin(String email, String senha) throws UserNotFoundException, DBUnavailable, InvalidData {
        /*if(email.length() < 11) {
            throw new InvalidData("O e-mail inserido � muito curto");
        } else if(senha.length() < 6) {
            throw new InvalidData("A senha precisa ter um tamanho m�nimo de 6 caracteres");
        }

        return usuarioBD.consultarUsuario(email, senha);*/
        return null;
    }
}
