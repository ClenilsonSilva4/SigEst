package AplicacaoEstudantil.service.login;

import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.InvalidData;
import AplicacaoEstudantil.exception.UserNotFoundException;
import framework.Domain.Gestor;

public interface LoginServiceInterface {
    Gestor checarLogin (String email, String senha) throws UserNotFoundException, DBUnavailable, InvalidData;
}
