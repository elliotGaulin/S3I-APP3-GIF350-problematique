package menufact.Iterateur.exceptions;

/**
 * Classe IterateurException est une classe d'exception qui est lancée lorsqu'une erreur est détectée dans la classe Iterateur.
 */
public class IterateurException extends RuntimeException {
    /**
     * Constructeur par defaut
     * @param message le message d'erreur
     */
    public IterateurException(String message) {
        super("IterateurException: " + message);
    }
}
