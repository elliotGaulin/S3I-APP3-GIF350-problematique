package ingredients;

import java.util.HashMap;

/**
 * Fabrique pour la création d'ingrédient
 */
public class FabriqueIngredient {

    private final HashMap<TypeIngredient,Ingredient> ingredientsEnregistrees = new HashMap<>();

    private static FabriqueIngredient instance;

    private FabriqueIngredient() {
    }

    public static FabriqueIngredient getInstance() {
        if (instance == null) {
            instance = new FabriqueIngredient();
        }
        return instance;
    }


    /**
     * Enregistrer un ingrédient à la fabrique
     * @param typeIngredient
     * @param ingredient
     */
    public void enregistrerIngredient(TypeIngredient typeIngredient, Ingredient ingredient) {
        ingredientsEnregistrees.put(typeIngredient, ingredient);
    }

    /**
     * Créateur d'ingrédient
     * @param typeIngredient
     * @param nom
     * @param description
     * @param uniteDeMesure
     * @return
     */
    public Ingredient creerIngredient(TypeIngredient typeIngredient, String nom, String description, String uniteDeMesure) {
        return ((Ingredient) ingredientsEnregistrees.get(typeIngredient)).creerIngredient(nom, description, uniteDeMesure);

    }

}
