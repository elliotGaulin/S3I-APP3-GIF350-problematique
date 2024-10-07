package inventaire;

import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import ingredients.exceptions.IngredientException;

import java.util.*;

/**
 * L'inventaire des ingrédients du système MenuFact
 *
 * @author Vincent Bélisle
 */
public class Inventaire {
    private final Map<Ingredient, Integer> ingredientInventaires = new HashMap<>();
    private static Inventaire instance;

    private Inventaire(Optional<Map<Ingredient, Integer>> ingredientInventaires) {
    }

    public static Inventaire getInstance(Optional<Map<Ingredient, Integer>> ingredientInventaires) {
        if (instance == null) {
            instance = new Inventaire(ingredientInventaires);
        }
        return instance;
    }

    /**
     * Modifier l'inventaire d'un ingredient
     *
     * @param ingredient
     * @param quantite
     * @throws IngredientException
     */
    public void modifierInventaireIngredient(Ingredient ingredient, int quantite) throws IngredientException {
        ingredientInventaires.put(ingredient, quantite);
    }

    /**
     * Verifier si tous les ingrédients nécessaires sont disponibles
     *
     * @param ingredients
     * @return
     * @throws IngredientException
     */
    public boolean verifierEtMettreAJourInventaireIngredient(ArrayList<IngredientInventaire> ingredients) throws IngredientException {
        if (ingredients.isEmpty()) {
            return true;
        }

        Map<Ingredient, Integer> misesAJour = new HashMap<>();

        for (IngredientInventaire ingredientInventaire : ingredients) {
            int quantite = ingredientInventaire.getQuantite();

            if (quantite == 0) {
                continue;
            }

            Integer quantiteDisponible = ingredientInventaires.get(ingredientInventaire.getIngredient());

            if (quantiteDisponible == null || quantiteDisponible < quantite) {
                throw new IngredientException("Il manque des ingrédients");
            }

            misesAJour.put(ingredientInventaire.getIngredient(), quantiteDisponible - quantite);
        }

        // Pour chaque entrée dans la Map de mises à jour, mettre à jour dans l'inventaire
        ingredientInventaires.putAll(misesAJour);

        return true;
    }

}
