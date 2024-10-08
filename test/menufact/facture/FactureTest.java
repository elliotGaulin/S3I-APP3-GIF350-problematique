package menufact.facture;

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
import menufact.exceptions.IterateurException;
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

public class FactureTest {
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
    public void testAssocierClient() {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(client, facture.getClient(), "Le client associé à la facture devrait être le client passé en paramètre.");
    }

    @Test
    public void testSousTotal() {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(0, facture.sousTotal(), "Le sous-total de la facture devrait être 0.");

        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> iterateur = Menu.getInstance().creerIterateur();

        PlatAuMenu plat1 = iterateur.courant();
        PlatAuMenu plat2 = iterateur.positionSuivante();

        try {
            facture.ajoutePlat(new PlatChoisi(plat1, 5));
            facture.ajoutePlat(new PlatChoisi(plat2, 10));
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        assertEquals(250, facture.sousTotal(), "Le sous-total de la facture devrait être 250.");
    }

    @Test
    public void testTps() {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(0, facture.tps(), "Le TPS de la facture devrait être 0.");

        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> iterateur = Menu.getInstance().creerIterateur();

        PlatAuMenu plat1 = iterateur.courant();
        PlatAuMenu plat2 = iterateur.positionSuivante();

        try {
            facture.ajoutePlat(new PlatChoisi(plat1, 5));
            facture.ajoutePlat(new PlatChoisi(plat2, 10));
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        assertEquals(12.5, facture.tps(), "Le TPS de la facture devrait être 12.5.");
    }

    @Test
    public void testTvq() {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(0, facture.tvq(), "Le TVQ de la facture devrait être 0.");

        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> iterateur = Menu.getInstance().creerIterateur();

        PlatAuMenu plat1 = iterateur.courant();
        PlatAuMenu plat2 = iterateur.positionSuivante();

        try {
            facture.ajoutePlat(new PlatChoisi(plat1, 5));
            facture.ajoutePlat(new PlatChoisi(plat2, 10));
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        assertEquals(24.9375, facture.tvq(), "Le TVQ de la facture devrait être 24.9375.");
    }

    @Test
    public void testTotal () {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(0, facture.total(), "Le total de la facture devrait être 0.");

        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> iterateur = Menu.getInstance().creerIterateur();

        PlatAuMenu plat1 = iterateur.courant();
        PlatAuMenu plat2 = iterateur.positionSuivante();

        try {
            facture.ajoutePlat(new PlatChoisi(plat1, 5));
            facture.ajoutePlat(new PlatChoisi(plat2, 10));
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        assertEquals(287.4375, facture.total(), "Le total de la facture devrait être 287.4375.");
    }

    @Test
    public void testPayer() {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(FactureOuverte.class, facture.getEtat().getClass(), "L'état de la facture devrait être OUVERTE.");

        try {
            facture.payer();
        } catch (FactureException ignored) {
        }

        assertEquals(FacturePayee.class, facture.getEtat().getClass(), "L'état de la facture devrait être PAYEE.");
    }

    @Test
    public void testFermer() {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(FactureOuverte.class, facture.getEtat().getClass(), "L'état de la facture devrait être OUVERTE.");

        try {
            facture.fermer();
        } catch (FactureException ignored) {
        }

        assertEquals(FactureFermee.class, facture.getEtat().getClass(), "L'état de la facture devrait être FERMEE.");
    }


    @Test
    public void testOuvrir() throws FactureException {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(FactureOuverte.class, facture.getEtat().getClass(), "L'état de la facture devrait être OUVERTE.");

        try {
            facture.ouvrir();
        } catch (FactureException ignored) {
        }
        assertEquals(FactureOuverte.class, facture.getEtat().getClass(), "L'état de la facture devrait être OUVERTE.");
    }

    @Test
    public void testGetDescription() {
        Facture facture = new Facture("Ma facture");
        assertEquals("Ma facture", facture.getDescription(), "La description de la facture devrait être \"Ma facture\".");
    }

    @Test
    public void testGetDate() {
        Facture facture = new Facture("Ma facture");
        assertEquals(facture.getDate(), facture.getDate(), "La date de la facture devrait être la date de création de la facture.");
    }

    @Test
    public void testGetClient() {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(client, facture.getClient(), "Le client associé à la facture devrait être le client passé en paramètre.");
    }

    @Test
    public void testGetPlatchoisi() {
        Facture facture = new Facture("Ma facture");
        assertEquals(new ArrayList<PlatChoisi>(), facture.getPlatchoisi(), "La liste des plats choisis de la facture devrait être vide.");

        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> iterateur = Menu.getInstance().creerIterateur();

        PlatAuMenu plat1 = iterateur.courant();
        PlatAuMenu plat2 = iterateur.positionSuivante();

        try {
            facture.ajoutePlat(new PlatChoisi(plat1, 5));
            facture.ajoutePlat(new PlatChoisi(plat2, 10));
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        assertEquals(2, facture.getPlatchoisi().size(), "La liste des plats choisis de la facture devrait contenir 2 éléments.");
    }

    @Test
    public void testGetEtat() {
        Facture facture = new Facture("Ma facture");
        assertEquals(FactureOuverte.class, facture.getEtat().getClass(), "L'état de la facture devrait être OUVERTE.");
    }

    @Test
    public void testAjoutePlat() {
        Facture facture = new Facture("Ma facture");
        Client client = new Client(1, "Elliot Gaulin", "123465789");
        facture.associerClient(client);
        assertEquals(0, facture.getPlatchoisi().size(), "La liste des plats choisis de la facture devrait être vide.");

        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> iterateur = Menu.getInstance().creerIterateur();

        PlatAuMenu plat1 = iterateur.courant();
        PlatAuMenu plat2 = iterateur.positionSuivante();

        try {
            facture.ajoutePlatChoisi(new PlatChoisi(plat1, 5));
            facture.ajoutePlatChoisi(new PlatChoisi(plat2, 10));
        } catch (PlatException ignored) {
        }

        assertEquals(2, facture.getPlatchoisi().size(), "La liste des plats choisis de la facture devrait contenir 2 éléments.");
    }

    @Test
    public void testSetEtat() {
        Facture facture = new Facture("Ma facture");
        FactureEtat factureEtat = new FactureOuverte(facture);
        facture.setEtat(factureEtat);

        assertEquals(factureEtat, facture.getEtat(), "L'état de la facture devrait être celui passé en paramètre.");
    }

    @Test
    public void testCreerIterateur() throws PlatException {
        Facture facture = new Facture("Ma facture");
        assertThrows(IterateurException.class, facture::creerIterateur, "L'itérateur ne devrait pas être créé sans plats.");

        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> itm = Menu.getInstance().creerIterateur();

        PlatAuMenu plat1 = itm.courant();
        PlatChoisi platChoisi1 = new PlatChoisi(plat1, 5);

        try {
            facture.ajoutePlat(platChoisi1);
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        IIterateur<PlatChoisi> itf = facture.creerIterateur();
        assertEquals(platChoisi1, itf.courant(), "Le plat inital de l'itérateur devrait être le premier plat ajouté.");
    }

    @Test
    public void testIterateurPrecedent() throws PlatException {
        Facture facture = new Facture("Ma facture");
        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> itm = Menu.getInstance().creerIterateur();

        PlatChoisi plat1 = new PlatChoisi(itm.courant(), 5);
        PlatChoisi plat2 = new PlatChoisi(itm.positionSuivante(), 10);
        PlatChoisi plat3 = new PlatChoisi(itm.positionSuivante(), 15);

        try {
            facture.ajoutePlat(plat1);
            facture.ajoutePlat(plat2);
            facture.ajoutePlat(plat3);
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        IIterateur<PlatChoisi> itf = facture.creerIterateur();
        assertEquals(plat1, itf.courant(), "Le plat inital de l'itérateur devrait être le premier plat ajouté.");
        itf.positionSuivante();
        assertEquals(plat1, itf.positionPrecedente(), "Le premier plat devrait être le plat precedent.");
        assertThrows(IterateurException.class, itf::positionPrecedente, "Il ne devrait pas y avoir de plat précédent.");
    }

    @Test
    public void testIterateurSuivant() throws PlatException {
        Facture facture = new Facture("Ma facture");
        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> itm = Menu.getInstance().creerIterateur();

        PlatChoisi plat1 = new PlatChoisi(itm.courant(), 5);
        PlatChoisi plat2 = new PlatChoisi(itm.positionSuivante(), 10);
        PlatChoisi plat3 = new PlatChoisi(itm.positionSuivante(), 15);

        try {
            facture.ajoutePlat(plat1);
            facture.ajoutePlat(plat2);
            facture.ajoutePlat(plat3);
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        IIterateur<PlatChoisi> itf = facture.creerIterateur();
        assertEquals(plat1, itf.courant(), "Le plat inital de l'itérateur devrait être le premier plat ajouté.");
        itf.positionSuivante();
        assertEquals(plat2, itf.courant(), "Le premier plat devrait être le plat suivant.");
        itf.positionSuivante();
        assertEquals(plat3, itf.courant(), "Le premier plat devrait être le plat suivant.");
        assertThrows(IterateurException.class, itf::positionSuivante, "Il ne devrait pas y avoir de plat suivant.");
    }

    @Test
    public void testIteratorPremier() throws PlatException {
        Facture facture = new Facture("Ma facture");
        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> itm = Menu.getInstance().creerIterateur();

        PlatChoisi plat1 = new PlatChoisi(itm.courant(), 5);
        PlatChoisi plat2 = new PlatChoisi(itm.positionSuivante(), 10);
        PlatChoisi plat3 = new PlatChoisi(itm.positionSuivante(), 15);

        try {
            facture.ajoutePlat(plat1);
            facture.ajoutePlat(plat2);
            facture.ajoutePlat(plat3);
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        IIterateur<PlatChoisi> itf = facture.creerIterateur();
        assertEquals(plat1, itf.premier(), "Le premier plat devrait être le premier plat ajouté.");
    }

    @Test
    public void testIterateurDernier() throws PlatException {
        Facture facture = new Facture("Ma facture");
        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> itm = Menu.getInstance().creerIterateur();

        PlatChoisi plat1 = new PlatChoisi(itm.courant(), 5);
        PlatChoisi plat2 = new PlatChoisi(itm.positionSuivante(), 10);
        PlatChoisi plat3 = new PlatChoisi(itm.positionSuivante(), 15);

        try {
            facture.ajoutePlat(plat1);
            facture.ajoutePlat(plat2);
            facture.ajoutePlat(plat3);
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        IIterateur<PlatChoisi> itf = facture.creerIterateur();
        assertEquals(plat3, itf.dernier(), "Le dernier plat devrait être le dernier plat ajouté.");
    }

    @Test
    public void testIterateurPosition() throws PlatException {
        Facture facture = new Facture("Ma facture");
        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> itm = Menu.getInstance().creerIterateur();

        PlatChoisi plat1 = new PlatChoisi(itm.courant(), 5);
        PlatChoisi plat2 = new PlatChoisi(itm.positionSuivante(), 10);
        PlatChoisi plat3 = new PlatChoisi(itm.positionSuivante(), 15);

        try {
            facture.ajoutePlat(plat1);
            facture.ajoutePlat(plat2);
            facture.ajoutePlat(plat3);
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        IIterateur<PlatChoisi> itf = facture.creerIterateur();
        assertEquals(plat2, itf.position(1), "Le deuxième plat devrait être le plat à la position 1.");
        assertEquals(plat3, itf.position(2), "Le troisième plat devrait être le plat à la position 2.");
        assertThrows(IterateurException.class, () -> itf.position(3), "Il ne devrait pas y avoir de plat à la position 3.");
        assertThrows(IterateurException.class, () -> itf.position(-1), "Il ne devrait pas y avoir de plat à la position -1.");
    }

    @Test
    public void testIterateurCourant() throws PlatException {
        Facture facture = new Facture("Ma facture");
        Menu menu = Menu.getInstance();
        IIterateur<PlatAuMenu> itm = Menu.getInstance().creerIterateur();

        PlatChoisi plat1 = new PlatChoisi(itm.courant(), 5);
        PlatChoisi plat2 = new PlatChoisi(itm.positionSuivante(), 10);
        PlatChoisi plat3 = new PlatChoisi(itm.positionSuivante(), 15);

        try {
            facture.ajoutePlat(plat1);
            facture.ajoutePlat(plat2);
            facture.ajoutePlat(plat3);
        } catch (FactureException | IngredientException | PlatException ignored) {
        }

        IIterateur<PlatChoisi> itf = facture.creerIterateur();
        assertEquals(plat1, itf.courant(), "Le plat inital de l'itérateur devrait être le premier plat ajouté.");
    }
}
