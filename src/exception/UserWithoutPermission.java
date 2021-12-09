package exception;

public class UserWithoutPermission extends Exception{
    public UserWithoutPermission() {
    }

    public UserWithoutPermission(String message) {
        super(message);
    }
}
