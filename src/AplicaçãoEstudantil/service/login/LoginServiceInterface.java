package AplicaçãoEstudantil.service.login;

import AplicaçãoEstudantil.exception.DBUnavailable;
import AplicaçãoEstudantil.exception.InvalidData;
import AplicaçãoEstudantil.exception.UserNotFoundException;

public interface LoginServiceInterface {
    Usuario checarLogin (String email, String senha) throws UserNotFoundException, DBUnavailable, InvalidData;
}
