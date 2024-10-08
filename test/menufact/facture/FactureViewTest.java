package menufact.facture;

import Iterateur.IIterable;
import Iterateur.IIterateur;
import ingredients.FabriqueIngredient;
import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import ingredients.TypeIngredient;
import ingredients.exceptions.IngredientException;
import inventaire.Inventaire;
import menufact.Client;
import menufact.Menu;
import menufact.TestMenuFact02;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatSante;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactureViewTest {

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
    public void testRender() throws PlatException {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot", "1234567890");
        FactureView factureView = new FactureView();

        facture.associerClient(client);

        IIterateur<PlatAuMenu> itm = Menu.getInstance().creerIterateur();
        PlatAuMenu platAuMenu = itm.courant();
        PlatChoisi platChoisi = new PlatChoisi(platAuMenu, 2);

        try {
            facture.ajoutePlat(platChoisi);
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        String res = factureView.render(facture);

        System.out.println(res);

        assertTrue(res.contains("Facture generee."));
        assertTrue(res.contains("Date:"));
        assertTrue(res.contains("Description:"));
        assertTrue(res.contains("Client:"));
        assertTrue(res.contains("Elliot"));
        assertTrue(res.contains("Les plats commandes:"));
        assertTrue(res.contains("Seq   Plat         Prix   Quantite"));
        assertTrue(res.contains("PlatAuMenu0  10.0      2"));
        assertTrue(res.contains("TPS:"));
        assertTrue(res.contains("TVQ:"));
        assertTrue(res.contains("Le total est de:"));


    }
}
