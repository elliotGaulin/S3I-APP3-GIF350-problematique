package inventaire;

import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import ingredients.exceptions.IngredientException;

import java.util.*;

/**
 * L'inventaire des ingrédients du système MenuFact
 *
 */
public class Inventaire {
    private Map<String, Integer> ingredientInventaires;
    private static Inventaire instance;

    private Inventaire(Optional<Map<String, Integer>> ingredientInventaires) {
        this.ingredientInventaires = ingredientInventaires.orElse(new HashMap<>());
    }

    public static Inventaire getInstance(Optional<Map<String, Integer>> ingredientInventaires) {
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
        ingredientInventaires.put(ingredient.getNom(), quantite);
    }

    /**
     * Verifier si tous les ingrédients nécessaires sont disponibles
     * et mettre à jour l'inventaire
     *
     * @param ingredients
     * @return
     * @throws IngredientException
     */
    public boolean verifierEtMettreAJourInventaireIngredient(ArrayList<IngredientInventaire> ingredients) throws IngredientException {
        if (ingredients.isEmpty()) {
            return true;
        }

        Map<String, Integer> misesAJour = new HashMap<>();

        for (IngredientInventaire ingredientInventaire : ingredients) {
            int quantite = ingredientInventaire.getQuantite();

            if (quantite == 0) {
                continue;
            }

            Integer quantiteDisponible = ingredientInventaires.get(ingredientInventaire.getIngredient().getNom());

            if (quantiteDisponible == null || quantiteDisponible < quantite) {
                throw new IngredientException("Il manque des ingrédients");
            }

            misesAJour.put(ingredientInventaire.getIngredient().getNom(), quantiteDisponible - quantite);
        }

        // Pour chaque entrée dans la Map de mises à jour, mettre à jour dans l'inventaire
        ingredientInventaires.putAll(misesAJour);

        return true;
    }

    public Map<String, Integer> getIngredientInventaires() {
        return ingredientInventaires;
    }

}
