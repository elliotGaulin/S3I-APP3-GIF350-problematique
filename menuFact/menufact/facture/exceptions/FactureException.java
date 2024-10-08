package menufact.facture.exceptions;

/**
 * FactureException est une classe d'exception qui est lancée lorsqu'une erreur est détectée dans la classe Facture.
 */
public class FactureException extends Exception{

    public FactureException(String message){
        super("FactureException: " + message);
   }
}
