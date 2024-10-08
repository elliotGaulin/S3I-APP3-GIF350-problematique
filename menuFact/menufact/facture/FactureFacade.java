package menufact.facture;

import ingredients.IngredientInventaire;
import ingredients.exceptions.IngredientException;
import inventaire.Inventaire;
import observateur.GestionnaireEvenement;
import menufact.plats.PlatChoisi;
import menufact.facture.exceptions.FactureException;
import menufact.plats.exceptions.PlatException;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Facade pour gérer l'ajout d'un plat choisi à une facture, vérification de l'inventaire
 * et notification au Chef
 */
public class FactureFacade {

    private final Inventaire inventaire;
    private final GestionnaireEvenement gestionnaireEvenement;


    public FactureFacade() {
        this.inventaire = Inventaire.getInstance(Optional.empty());
        this.gestionnaireEvenement = GestionnaireEvenement.getInstance(
                GestionnaireEvenement.evenementAjoutPlatChoisi
        );


    }

    /**
     * Ajoute un plat choisi à la facture
     *
     * @param p le plat choisi
     * @throws IngredientException
     * @throws PlatException
     * @throws FactureException
     */
    public void ajoutePlatChoisi(FactureController factureController, PlatChoisi p) throws IngredientException, PlatException, FactureException {
        ArrayList<IngredientInventaire> ingredientsNecessaires = new ArrayList<>();

        // Calculer la quantité nécessaire pour chaque ingrédient
        for (IngredientInventaire ingInventaire : p.getPlat().getIngredients()) {
            ingredientsNecessaires.add(new IngredientInventaire(ingInventaire.getIngredient(), ingInventaire.getQuantite() * p.getQuantite()));
        }

        try {
            // Vérifier et mettre à jour l'inventaire
            inventaire.verifierEtMettreAJourInventaireIngredient(ingredientsNecessaires);
        } catch (IngredientException exception) {
            p.impossibleServir();
            throw new IngredientException("Impossible de servir le plat : manque d'ingrédients.");

        }

        factureController.ajoutePlat(p);

        // Notifier chef
        gestionnaireEvenement.notifier(GestionnaireEvenement.evenementAjoutPlatChoisi, "Veuillez procéder à la préparation du plat suivant : " + p.toString());
    }

}
