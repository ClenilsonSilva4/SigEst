package exception;

public class InvalidData extends Exception{
    public InvalidData() {
    }

    public InvalidData(String message) {
        super(message);
    }
}
