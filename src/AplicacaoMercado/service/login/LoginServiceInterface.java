package AplicacaoMercado.service.login;

import exception.DBUnavailable;
import exception.InvalidData;
import exception.UserNotFoundException;

public interface LoginServiceInterface {
    Object checarLogin (String email, String senha) throws UserNotFoundException, DBUnavailable, InvalidData;
}
