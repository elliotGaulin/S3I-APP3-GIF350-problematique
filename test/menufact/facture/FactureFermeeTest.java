package menufact.facture;

import ingredients.FabriqueIngredient;
import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import ingredients.TypeIngredient;
import inventaire.Inventaire;
import menufact.Menu;
import menufact.TestMenuFact02;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;
import menufact.plats.PlatSante;
import menufact.plats.exceptions.PlatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactureFermeeTest {

    @BeforeAll
    public static void setUp() {
        try {
            Class.forName("ingredients.Legume");
            Class.forName("ingredients.Viande");
            Class.forName("ingredients.Fruit");
            Class.forName("ingredients.Laitier");
            Class.forName("ingredients.Epice");

        } catch (ClassNotFoundException any) {
            any.printStackTrace();
        }

        FabriqueIngredient fabriqueIngredient = FabriqueIngredient.getInstance();

        Ingredient carotte = fabriqueIngredient.creerIngredient(TypeIngredient.LEGUME, "Carotte", "Légume orange", "g");
        Ingredient boeuf = fabriqueIngredient.creerIngredient(TypeIngredient.VIANDE, "Boeuf", "", "g");
        Ingredient poulet = fabriqueIngredient.creerIngredient(TypeIngredient.VIANDE, "Poulet", "", "g");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();

        Map<String, Integer> mapIngredients = new HashMap<>();

        mapIngredients.put(carotte.getNom(), 50);
        mapIngredients.put(boeuf.getNom(), 50);
        mapIngredients.put(poulet.getNom(), 50);


        Inventaire inventaire = Inventaire.getInstance(
                Optional.of(mapIngredients));


        try {

            ingredients.add(new IngredientInventaire(carotte, 0));
            ingredients.add(new IngredientInventaire(poulet, 0));
            ingredients.add(new IngredientInventaire(boeuf, 0));
        } catch (Exception ignored) {
        }


        TestMenuFact02 t = new TestMenuFact02();

        PlatAuMenu p1 = new PlatAuMenu(0, "PlatAuMenu0", 10, ingredients);
        PlatAuMenu p2 = new PlatAuMenu(1, "PlatAuMenu1", 20, ingredients);
        PlatAuMenu p3 = new PlatAuMenu(2, "PlatAuMenu2", 30, ingredients);
        PlatAuMenu p4 = new PlatAuMenu(3, "PlatAuMenu3", 40, ingredients);
        PlatAuMenu p5 = new PlatAuMenu(4, "PlatAuMenu4", 50, ingredients);


        PlatSante ps1 = new PlatSante(10, "PlatSante0", 10, ingredients, 11, 11, 11);
        PlatSante ps2 = new PlatSante(11, "PlatSante1", 20, ingredients, 11, 11, 11);
        PlatSante ps3 = new PlatSante(12, "PlatSante2", 30, ingredients, 11, 11, 11);
        PlatSante ps4 = new PlatSante(13, "PlatSante3", 40, ingredients, 11, 11, 11);
        PlatSante ps5 = new PlatSante(14, "PlatSante4", 50, ingredients, 11, 11, 11);


        Menu m1 = Menu.getInstance("menufact.Menu 1");

        m1.ajoute(p1);
        m1.ajoute(p2);
        m1.ajoute(p3);
        m1.ajoute(p4);
        m1.ajoute(p5);
        m1.ajoute(ps1);
        m1.ajoute(ps2);
        m1.ajoute(ps3);
        m1.ajoute(ps4);
        m1.ajoute(ps5);
    }

    @Test
    public void testOuvrir() {
        Facture facture = new Facture("Ma facture");
        FactureFermee factureFermee = new FactureFermee(facture);
        facture.setEtat(factureFermee);

        factureFermee.ouvrir();

        assertEquals(FactureOuverte.class, facture.getEtat().getClass());
    }

    @Test
    public void testFermer() {
        Facture facture = new Facture("Ma facture");
        FactureFermee factureFermee = new FactureFermee(facture);
        facture.setEtat(factureFermee);

        factureFermee.fermer();

        assertEquals(FactureFermee.class, facture.getEtat().getClass());
    }

    @Test
    public void testPayer() {
        Facture facture = new Facture("Ma facture");
        FactureFermee factureFermee = new FactureFermee(facture);
        facture.setEtat(factureFermee);

        factureFermee.payer();

        assertEquals(FacturePayee.class, facture.getEtat().getClass());
    }

    @Test
    public void testAjoutePlat() throws PlatException {
        Facture facture = new Facture("Ma facture");
        FactureFermee factureFermee = new FactureFermee(facture);
        facture.setEtat(factureFermee);

        PlatAuMenu plat = Menu.getInstance().creerIterateur().premier();
        PlatChoisi platChoisi = new PlatChoisi(plat, 1);

        assertThrows(FactureException.class, () -> factureFermee.ajoutePlat(platChoisi));
    }

    @Test
    public void testToString() {
        Facture facture = new Facture("Ma facture");
        FactureFermee factureFermee = new FactureFermee(facture);
        facture.setEtat(factureFermee);

        assertEquals("Fermée", factureFermee.toString());
    }
}
