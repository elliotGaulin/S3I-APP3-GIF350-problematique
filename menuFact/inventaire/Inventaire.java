package inventaire;

import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import ingredients.exceptions.IngredientException;

import java.util.ArrayList;
import java.util.Optional;

public class Inventaire {
    private ArrayList<IngredientInventaire> ingredientInventaires = new ArrayList<IngredientInventaire>();

    private Optional<IngredientInventaire> getIngredientInventaire(Ingredient ingredient) {
        return ingredientInventaires.stream()
                .filter(ing -> ing.getIngredient().equals(ingredient))
                .findFirst();
    }


    public void modifierInventaireIngredient(Ingredient ingredient, int quantite) throws IngredientException {
        Optional<IngredientInventaire> ing = getIngredientInventaire(ingredient);
        if (ing.isPresent()) {
            ing.get().setQuantite(quantite);
            return;
        }
        ingredientInventaires.add(new IngredientInventaire(ingredient, quantite));

    }

}
