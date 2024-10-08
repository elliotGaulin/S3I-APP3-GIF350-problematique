package observateur;

/**
 * Interface pour définir les écouteurs d'évènements.
 */
public interface EcouteurEvenement {
    void mettreAJour(String typeEvenement, String donnees);
}
