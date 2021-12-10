package exception;

public class EmailAlreadyInUse extends Exception{
    public EmailAlreadyInUse() {
    }

    public EmailAlreadyInUse(String message) {
        super(message);
    }
}
