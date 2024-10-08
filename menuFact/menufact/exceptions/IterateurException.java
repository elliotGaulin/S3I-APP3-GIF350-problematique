package menufact.exceptions;

public class IterateurException extends RuntimeException {

    public IterateurException(String message) {
        super("IterateurException: " + message);
    }
}
