package ingredients;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueIngredientTest {

    private FabriqueIngredient fabriqueIngredient;

    @BeforeEach
    void setUp() {
        fabriqueIngredient = FabriqueIngredient.getInstance();

        try {
            Class.forName("ingredients.Legume");
            Class.forName("ingredients.Viande");
            Class.forName("ingredients.Fruit");
            Class.forName("ingredients.Laitier");
            Class.forName("ingredients.Epice");

        } catch (ClassNotFoundException any) {
            any.printStackTrace();
        }
    }

    @Test
    void getInstance() {
        FabriqueIngredient instance1 = FabriqueIngredient.getInstance();
        FabriqueIngredient instance2 = FabriqueIngredient.getInstance();
        assertSame(instance1, instance2, "La fabrique devrait retourner la même instance (singleton)");
    }

    @Test
    void enregistrerIngredient() {

        Ingredient ingredientCree = fabriqueIngredient.creerIngredient(TypeIngredient.LEGUME, "Carotte", "Carotte bio", "kg");

        assertNotNull(ingredientCree, "L'ingrédient ne devrait pas être nul");
        assertEquals("Carotte", ingredientCree.getNom(), "Le nom de l'ingrédient devrait correspondre");
        assertEquals("Carotte bio", ingredientCree.getDescription(), "La description de l'ingrédient devrait correspondre");
    }

    @Test
    void creerIngredient() {
        Ingredient ingredientCree = fabriqueIngredient.creerIngredient(TypeIngredient.FRUIT, "Banane", "Banane fraîche", "kg");

        assertNotNull(ingredientCree, "L'ingrédient ne devrait pas être nul");
        assertEquals("Banane", ingredientCree.getNom(), "Le nom de l'ingrédient devrait être 'Banane'");
        assertEquals("Banane fraîche", ingredientCree.getDescription(), "La description devrait être 'Banane fraîche'");
        assertEquals("kg", ingredientCree.getUniteDeMesure(), "L'unité de mesure devrait être 'kg'");
    }
}
