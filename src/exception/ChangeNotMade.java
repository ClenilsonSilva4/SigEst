package exception;

public class ChangeNotMade extends Exception{
    public ChangeNotMade() {
    }

    public ChangeNotMade(String message) {
        super(message);
    }
}
