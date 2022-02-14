package AplicacaoMercado.service.login;

import AplicacaoEstudantil.dao.AlunoDAOMySQL;
import AplicacaoEstudantil.dao.GestorDAOMySQL;
import AplicacaoEstudantil.dao.ProfessorDAOMySQL;
import exception.DBUnavailable;
import exception.InvalidData;
import exception.UserNotFoundException;

public class LoginService implements LoginServiceInterface{
    private final GestorDAOMySQL gestorBD;
    private final ProfessorDAOMySQL avaliadorBD;
    private final AlunoDAOMySQL recursoBD;

    public LoginService() {
        gestorBD = new GestorDAOMySQL();
        avaliadorBD = new ProfessorDAOMySQL();
        recursoBD = new AlunoDAOMySQL();
    }

    @Override
    public Object checarLogin(String email, String senha) throws DBUnavailable, InvalidData, UserNotFoundException {
        if(email.length() < 11) {
            throw new InvalidData("O e-mail inserido é muito curto");
        } else if(senha.length() < 6) {
            throw new InvalidData("A senha precisa ter um tamanho mínimo de 6 caracteres");
        }

        try {
            return recursoBD.checarAcesso(email, senha);
        } catch (UserNotFoundException e) {
            try {
                return avaliadorBD.checarAcesso(email, senha);
            } catch (UserNotFoundException ex) {
                return gestorBD.checarAcesso(email, senha);
            }
        }
    }
}