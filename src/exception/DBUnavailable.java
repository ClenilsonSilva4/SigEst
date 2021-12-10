package exception;

public class DBUnavailable extends Exception{
    public DBUnavailable() {
    }

    public DBUnavailable(String message) {
        super(message);
    }
}
