package ingredients;

import menufact.ingredients.FabriqueIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.IngredientInventaire;
import menufact.ingredients.TypeIngredient;
import menufact.ingredients.exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientInventaireTest {

    private Ingredient ingredient;
    private IngredientInventaire ingredientInventaire;
    private FabriqueIngredient fabriqueIngredient;

    @BeforeEach
    void setUp() throws IngredientException {

        fabriqueIngredient = FabriqueIngredient.getInstance();

        try {
            Class.forName("menufact.ingredients.Legume");
            Class.forName("menufact.ingredients.Viande");
            Class.forName("menufact.ingredients.Fruit");
            Class.forName("menufact.ingredients.Laitier");
            Class.forName("menufact.ingredients.Epice");

        } catch (ClassNotFoundException any) {
            any.printStackTrace();
        }
        // Initialisation de l'ingrédient pour les tests
        ingredient = fabriqueIngredient.creerIngredient(TypeIngredient.LEGUME, "Carotte", "Carotte bio", "kg");

        // Initialisation d'un inventaire d'ingrédient avec une quantité initiale de 10
        ingredientInventaire = new IngredientInventaire(ingredient, 10);
    }

    @Test
    void getIngredient() {
        // Vérifier que l'ingrédient est bien récupéré
        assertEquals(ingredient, ingredientInventaire.getIngredient(),
                "L'ingrédient récupéré devrait être une tomate.");
    }

    @Test
    void getQuantite() {
        // Vérifier que la quantité initiale est correcte
        assertEquals(10, ingredientInventaire.getQuantite(),
                "La quantité d'ingrédient devrait être de 10.");
    }

    @Test
    void setQuantite_valide() throws IngredientException {
        // Modifier la quantité à une valeur valide
        ingredientInventaire.setQuantite(15);

        // Vérifier que la quantité a été mise à jour correctement
        assertEquals(15, ingredientInventaire.getQuantite(),
                "La quantité d'ingrédient devrait être mise à jour à 15.");
    }

    @Test
    void setQuantite_negative() {
        // Vérifier que la méthode lève une exception pour une quantité négative
        Exception exception = assertThrows(IngredientException.class, () -> {
            ingredientInventaire.setQuantite(-5);
        });

        assertEquals("IngredientException: Il n'est pas possible d'avoir une quantité negative", exception.getMessage(),
                "Le message d'exception devrait indiquer qu'une quantité négative est impossible.");
    }

    @Test
    void constructor_negativeQuantite() {
        // Vérifier que le constructeur lève une exception pour une quantité initiale négative
        Exception exception = assertThrows(IngredientException.class, () -> {
            new IngredientInventaire(ingredient, -3);
        });

        assertEquals("IngredientException: Il n'est pas possible d'avoir une quantité négative", exception.getMessage(),
                "Le message d'exception devrait indiquer qu'une quantité initiale négative est impossible.");
    }

    @Test
    void testToString() {
        // Vérifier que la méthode toString() renvoie la bonne chaîne de caractères
        String expected = "menuFact.Ingredient [nom=Carotte, description=Carotte bio, typeIngredient=LEGUME, uniteDeMesure=kg], quantite:10";
        assertEquals(expected, ingredientInventaire.toString(),
                "La représentation sous forme de chaîne devrait correspondre à la valeur attendue.");
    }
}
