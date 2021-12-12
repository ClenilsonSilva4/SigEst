package service.login;

import entities.Usuario;

public interface LoginServiceInterface {
    Usuario checarLogin (String email, String senha);
}
