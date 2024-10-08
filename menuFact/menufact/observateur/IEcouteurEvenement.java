package menufact.observateur;

/**
 * Interface pour définir les écouteurs d'évènements.
 */
public interface IEcouteurEvenement {

    /**
     * Met à jour les données de l'évènement.
     * @param typeEvenement Le type de l'évènement
     * @param donnees Les données de l'évènement
     */
    void mettreAJour(String typeEvenement, String donnees);
}
