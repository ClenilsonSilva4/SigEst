package AplicacaoEstudantil.exception;

public class UserWithoutPermission extends Exception{

    public UserWithoutPermission(String message) {
        super(message);
    }
}
