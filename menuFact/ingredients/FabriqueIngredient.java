package ingredients;

import java.util.HashMap;

public class FabriqueIngredient {

    private final HashMap ingredientsEnregistrees = new HashMap<>();

    private static FabriqueIngredient instance;

    private FabriqueIngredient() {
    }

    public static FabriqueIngredient getInstance() {
        if (instance == null) {
            instance = new FabriqueIngredient();
        }
        return instance;
    }


    public void enregistrerIngredient(TypeIngredient typeIngredient, Ingredient ingredient) {
        ingredientsEnregistrees.put(typeIngredient, ingredient);
    }

    public Ingredient creerIngredient(TypeIngredient typeIngredient, String nom, String description, String uniteDeMesure) {
        return ((Ingredient) ingredientsEnregistrees.get(typeIngredient)).creerIngredient(nom, description, uniteDeMesure);

    }

}
