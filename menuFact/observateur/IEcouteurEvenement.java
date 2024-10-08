package observateur;

/**
 * Interface pour définir les écouteurs d'évènements.
 */
public interface IEcouteurEvenement {
    void mettreAJour(String typeEvenement, String donnees);
}
