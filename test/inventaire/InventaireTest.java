package inventaire;

import menufact.ingredients.FabriqueIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.IngredientInventaire;
import menufact.ingredients.TypeIngredient;
import menufact.ingredients.exceptions.IngredientException;
import menufact.inventaire.Inventaire;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InventaireTest {

    private Inventaire inventaire;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeEach
    void setUp() {

        try {
            Class.forName("menufact.ingredients.Legume");
            Class.forName("menufact.ingredients.Viande");
            Class.forName("menufact.ingredients.Fruit");
            Class.forName("menufact.ingredients.Laitier");
            Class.forName("menufact.ingredients.Epice");

        } catch (ClassNotFoundException any) {
            any.printStackTrace();
        }
        // Initialisation des ingrédients pour les tests
        FabriqueIngredient fabriqueIngredient = FabriqueIngredient.getInstance();

        ingredient1 = fabriqueIngredient.creerIngredient(TypeIngredient.LEGUME, "Tomate", "Tomate fraîche", "kg");
        ingredient2 = fabriqueIngredient.creerIngredient(TypeIngredient.LEGUME, "Patate", "Patate", "kg");


        // Initialisation de l'inventaire avec certains ingrédients et leurs quantités
        Map<String, Integer> stockInitial = new HashMap<>();
        stockInitial.put(ingredient1.getNom(), 10);
        stockInitial.put(ingredient2.getNom(), 5);

        // Créer l'inventaire avec le stock initial
        inventaire = Inventaire.getInstance(Optional.of(stockInitial));
    }

    @AfterEach
    void tearDown() throws IngredientException {
        inventaire.modifierInventaireIngredient(ingredient1,10);
        inventaire.modifierInventaireIngredient(ingredient2,5);

    }


    @Test
    void getInstance() {
        // Vérifier que l'instance est bien initialisée
        assertNotNull(inventaire, "L'instance d'inventaire ne devrait pas être nulle.");
    }

    @Test
    void modifierInventaireIngredient() throws IngredientException {
        // Modifier la quantité d'un ingrédient dans l'inventaire
        inventaire.modifierInventaireIngredient(ingredient1, 15);

        // Vérifier que la quantité a bien été mise à jour
        assertEquals(15, inventaire.getIngredientInventaires().get(ingredient1.getNom()).intValue(),
                "La quantité de l'ingrédient devrait être mise à jour à 15.");
    }

    @Test
    void verifierEtMettreAJourInventaireIngredient() throws IngredientException {
        // Créer une liste d'ingrédients nécessaires
        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        ingredients.add(new IngredientInventaire(ingredient1, 3));  // On a besoin de 3 kg de tomates
        ingredients.add(new IngredientInventaire(ingredient2, 2));  // On a besoin de 2 kg de pommes de terre

        // Vérifier que l'inventaire est suffisant et le mettre à jour
        boolean resultat = inventaire.verifierEtMettreAJourInventaireIngredient(ingredients);

        assertTrue(resultat, "Tous les ingrédients devraient être disponibles.");
        assertEquals(7, inventaire.getIngredientInventaires().get(ingredient1.getNom()).intValue(),
                "La quantité de tomates devrait être 7.");
        assertEquals(3, inventaire.getIngredientInventaires().get(ingredient2.getNom()).intValue(),
                "La quantité de pommes de terre devrait être 3.");
    }

    @Test
    void verifierEtMettreAJourInventaireIngredient_manquant() throws IngredientException {
        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        ingredients.add(new IngredientInventaire(ingredient1, 20));

        // Vérifier que l'exception est levée
        Exception exception = assertThrows(IngredientException.class, () -> {
            inventaire.verifierEtMettreAJourInventaireIngredient(ingredients);
        });

        assertEquals("IngredientException: Il manque des ingrédients", exception.getMessage(),
                "Le message d'exception devrait indiquer qu'il manque des ingrédients.");
    }

    @Test
    void verifierEtMettreAJourInventaireIngredient_quantiteNulle() throws IngredientException {
        // Créer une liste d'ingrédients où la quantité est nulle
        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        ingredients.add(new IngredientInventaire(ingredient1, 0));
        // Vérifier que le système passe correctement sans erreur
        boolean resultat = inventaire.verifierEtMettreAJourInventaireIngredient(ingredients);

        assertTrue(resultat, "Le système devrait ignorer les ingrédients avec une quantité de 0.");
    }
}
