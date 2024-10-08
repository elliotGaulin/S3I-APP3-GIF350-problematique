package menufact.facture;

import ingredients.FabriqueIngredient;
import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import ingredients.TypeIngredient;
import ingredients.exceptions.IngredientException;
import inventaire.Inventaire;
import menufact.GestionnaireEvenement;
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;
import menufact.facture.exceptions.FactureException;
import menufact.plats.exceptions.PlatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FactureFacadeTest {

    private FactureFacade factureFacade;
    private Facture facture;
    private FactureView factureView;
    private FactureController factureController;
    private PlatChoisi platChoisi;
    private Inventaire inventaire;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeEach
    void setUp() throws IngredientException, PlatException {

        try {
            Class.forName("ingredients.Legume");
            Class.forName("ingredients.Viande");
            Class.forName("ingredients.Fruit");
            Class.forName("ingredients.Laitier");
            Class.forName("ingredients.Epice");

        } catch (ClassNotFoundException any) {
            any.printStackTrace();
        }

        // Initialisation des ingrédients
        FabriqueIngredient fabriqueIngredient = FabriqueIngredient.getInstance();
        ingredient1 = fabriqueIngredient.creerIngredient(TypeIngredient.LEGUME, "Tomate", "Tomate fraîche", "kg");
        ingredient2 = fabriqueIngredient.creerIngredient(TypeIngredient.VIANDE, "Poulet", "Poulet frais", "kg");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        ingredients.add(new IngredientInventaire(ingredient1, 2));
        ingredients.add(new IngredientInventaire(ingredient2, 1));

        // Initialisation du plat avec les ingrédients
        PlatAuMenu plat = new PlatAuMenu(1, "Poulet aux tomates", 15.99, ingredients);

        // Initialisation du plat choisi (quantité 2, donc double des ingrédients nécessaires)
        platChoisi = new PlatChoisi(plat, 2);

        // Initialisation de l'inventaire
        inventaire = Inventaire.getInstance(Optional.empty());
        inventaire.modifierInventaireIngredient(ingredient1, 10);  // 10kg de tomates en stock
        inventaire.modifierInventaireIngredient(ingredient2, 5);   // 5kg de poulet en stock

        // Initialisation de la facture et de la façade
        facture = new Facture("Test");
        factureFacade = new FactureFacade();
        factureController = new FactureController(facture, factureView);
    }

    @Test
    void ajoutePlatChoisi_succes() throws IngredientException, PlatException, FactureException {
        // Tester l'ajout réussi d'un plat choisi à la facture
        factureFacade.ajoutePlatChoisi(factureController, platChoisi);

        // Vérifier que le plat a été ajouté à la facture
        assertEquals(1, facture.getPlatchoisi().size(), "Le plat choisi devrait être ajouté à la facture.");

        // Vérifier que l'inventaire a été mis à jour correctement
        assertEquals(6, inventaire.getIngredientInventaires().get(ingredient1.getNom()).intValue(),
                "Il devrait rester 6kg de tomates dans l'inventaire.");
        assertEquals(3, inventaire.getIngredientInventaires().get(ingredient2.getNom()).intValue(),
                "Il devrait rester 3kg de poulet dans l'inventaire.");
    }

    @Test
    void ajoutePlatChoisi_ingredientManquant() throws IngredientException, PlatException, FactureException {
        // Réduire la quantité de poulet à un niveau insuffisant pour la recette
        inventaire.modifierInventaireIngredient(ingredient2, 0);  // 0kg de poulet disponible

        // Tester l'ajout d'un plat avec un ingrédient manquant
        IngredientException exception = assertThrows(IngredientException.class, () -> {
            factureFacade.ajoutePlatChoisi(factureController
                    , platChoisi);
        });

        // Vérifier que l'exception est correctement lancée
        assertEquals("IngredientException: Impossible de servir le plat : manque d'ingrédients.", exception.getMessage());

        // Vérifier que le plat n'a pas été ajouté à la facture
        assertEquals(0, facture.getPlatchoisi().size(), "Le plat ne devrait pas être ajouté à la facture.");
    }

}
