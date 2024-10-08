package menufact;

import Iterateur.IIterateur;
import ingredients.IngredientInventaire;
import menufact.exceptions.IterateurException;
import menufact.plats.PlatAuMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class MenuTest {
    @BeforeAll
    public static void setup() {
        Field field = null;
        try {
            field = Menu.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
    }

    @AfterEach
    public void tearDown() {
        Field field = null;
        try {
            field = Menu.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
    }

    @Test
    public void testSingleton() {
        Menu menu1 = Menu.getInstance("Menu 1");
        Menu menu2 = Menu.getInstance("Menu 2");
        Menu menu3 = Menu.getInstance("");
        assertEquals(menu1, menu2, "Les deux menus devraient être les mêmes instances.");
        assertEquals(menu1, menu3, "Les deux menus devraient être les mêmes instances.");
    }

    @Test
    public void testAjoute() {
        Menu menu = Menu.getInstance("Menu 1");
        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        menu.ajoute(plat);
        assertEquals(plat, menu.creerIterateur().premier(), "Le plat ajouté devrait être le premier plat du menu.");
    }

    @Test
    public void testCreerIterateur() {
        Menu menu = Menu.getInstance("Menu 1");

        assertThrows(IterateurException.class, () -> menu.creerIterateur(), "L'itérateur ne devrait pas être créé sans plats.");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        menu.ajoute(plat);

        IIterateur<PlatAuMenu> iterateur = menu.creerIterateur();
        assertEquals(plat, iterateur.courant(), "Le plat inital de l'itérateur devrait être le premier plat ajouté.");
    }

    @Test
    public void testIterateurAPrecedent() {
        Menu menu = Menu.getInstance("Menu 1");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat1 = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        PlatAuMenu plat2 = new PlatAuMenu(2, "Plat 2", 20.0, ingredients);
        PlatAuMenu plat3 = new PlatAuMenu(3, "Plat 3", 30.0, ingredients);
        menu.ajoute(plat1);
        menu.ajoute(plat2);
        menu.ajoute(plat3);

        IIterateur<PlatAuMenu> iterateur = menu.creerIterateur();
        assertFalse(iterateur.aPrecedent(), "Il ne devrait pas y avoir de plat précédent.");
        assertTrue(iterateur.aSuivant(), "Il devrait y avoir un plat suivant.");
        assertEquals(plat2, iterateur.positionSuivante(), "Le premier plat devrait être le plat suivant.");
        assertTrue(iterateur.aPrecedent(), "Il devrait y avoir un plat précédent.");
        assertEquals(plat1, iterateur.positionPrecedente(), "Le premier plat devrait être le plat précédent.");
        assertFalse(iterateur.aPrecedent(), "Il ne devrait pas y avoir de plat précédent.");
    }

    @Test
    public void testIterateurASuivant() {
        Menu menu = Menu.getInstance("Menu 1");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat1 = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        PlatAuMenu plat2 = new PlatAuMenu(2, "Plat 2", 20.0, ingredients);
        PlatAuMenu plat3 = new PlatAuMenu(3, "Plat 3", 30.0, ingredients);
        menu.ajoute(plat1);
        menu.ajoute(plat2);
        menu.ajoute(plat3);


        IIterateur<PlatAuMenu> iterateur = menu.creerIterateur();
        assertTrue(iterateur.aSuivant(), "Il devrait y avoir un plat suivant.");
        assertEquals(plat2, iterateur.positionSuivante(), "Le premier plat devrait être le plat suivant.");
        assertTrue(iterateur.aSuivant(), "Il devrait y avoir un plat suivant.");
        assertEquals(plat3, iterateur.positionSuivante(), "Le deuxième plat devrait être le plat suivant.");
        assertFalse(iterateur.aSuivant(), "Il ne devrait pas y avoir de plat suivant.");
    }

    @Test
    public void testIterateurPositionPrecedente() {
        Menu menu = Menu.getInstance("Menu 1");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat1 = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        PlatAuMenu plat2 = new PlatAuMenu(2, "Plat 2", 20.0, ingredients);
        PlatAuMenu plat3 = new PlatAuMenu(3, "Plat 3", 30.0, ingredients);
        menu.ajoute(plat1);
        menu.ajoute(plat2);
        menu.ajoute(plat3);

        IIterateur<PlatAuMenu> iterateur = menu.creerIterateur();
        iterateur.positionSuivante();
        iterateur.positionSuivante();
        assertEquals(plat2, iterateur.positionPrecedente(), "Le deuxième plat devrait être le plat précédent.");
        assertEquals(plat1, iterateur.positionPrecedente(), "Le premier plat devrait être le plat précédent.");
        assertThrows(IterateurException.class, iterateur::positionPrecedente, "Il ne devrait pas y avoir de plat précédent.");
    }

    @Test
    public void testIterateurPositionSuivante() {
        Menu menu = Menu.getInstance("Menu 1");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat1 = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        PlatAuMenu plat2 = new PlatAuMenu(2, "Plat 2", 20.0, ingredients);
        PlatAuMenu plat3 = new PlatAuMenu(3, "Plat 3", 30.0, ingredients);
        menu.ajoute(plat1);
        menu.ajoute(plat2);
        menu.ajoute(plat3);

        IIterateur<PlatAuMenu> iterateur = menu.creerIterateur();
        iterateur.positionSuivante();
        iterateur.positionSuivante();
        assertEquals(plat2, iterateur.positionPrecedente(), "Le deuxième plat devrait être le plat précédent.");
        assertEquals(plat3, iterateur.positionSuivante(), "Le deuxième plat devrait être le plat suivant.");
        assertThrows(IterateurException.class, iterateur::positionSuivante, "Il ne devrait pas y avoir de plat suivant.");
    }

    @Test
    public void testIterateurPremier() {
        Menu menu = Menu.getInstance("Menu 1");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat1 = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        PlatAuMenu plat2 = new PlatAuMenu(2, "Plat 2", 20.0, ingredients);
        PlatAuMenu plat3 = new PlatAuMenu(3, "Plat 3", 30.0, ingredients);
        menu.ajoute(plat1);
        menu.ajoute(plat2);
        menu.ajoute(plat3);

        IIterateur<PlatAuMenu> iterateur = menu.creerIterateur();
        iterateur.positionSuivante();
        iterateur.positionSuivante();
        assertEquals(plat1, iterateur.premier(), "Le premier plat devrait être le premier plat du menu.");
        assertEquals(plat1, iterateur.courant(), "Le premier plat devrait être le plat courant.");
    }

    @Test
    public void testIterateurDernier() {
        Menu menu = Menu.getInstance("Menu 1");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat1 = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        PlatAuMenu plat2 = new PlatAuMenu(2, "Plat 2", 20.0, ingredients);
        PlatAuMenu plat3 = new PlatAuMenu(3, "Plat 3", 30.0, ingredients);
        menu.ajoute(plat1);
        menu.ajoute(plat2);
        menu.ajoute(plat3);

        IIterateur<PlatAuMenu> iterateur = menu.creerIterateur();
        assertEquals(plat3, iterateur.dernier(), "Le dernier plat devrait être le dernier plat du menu.");
        assertEquals(plat3, iterateur.courant(), "Le dernier plat devrait être le plat courant.");
    }

    @Test
    public void testIterateurPosition() {
        Menu menu = Menu.getInstance("Menu 1");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat1 = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        PlatAuMenu plat2 = new PlatAuMenu(2, "Plat 2", 20.0, ingredients);
        PlatAuMenu plat3 = new PlatAuMenu(3, "Plat 3", 30.0, ingredients);
        menu.ajoute(plat1);
        menu.ajoute(plat2);
        menu.ajoute(plat3);

        IIterateur<PlatAuMenu> iterateur = menu.creerIterateur();
        assertEquals(plat2, iterateur.position(1), "Le deuxième plat devrait être le plat à la position 1.");
        assertEquals(plat3, iterateur.position(2), "Le troisième plat devrait être le plat à la position 2.");
        assertThrows(IterateurException.class, () -> iterateur.position(3), "Il ne devrait pas y avoir de plat à la position 3.");
        assertThrows(IterateurException.class, () -> iterateur.position(-1), "Il ne devrait pas y avoir de plat à la position -1.");
    }

    @Test
    public void testIterateurCourant() {
        Menu menu = Menu.getInstance("Menu 1");

        ArrayList<IngredientInventaire> ingredients = new ArrayList<>();
        PlatAuMenu plat1 = new PlatAuMenu(1, "Plat 1", 10.0, ingredients);
        PlatAuMenu plat2 = new PlatAuMenu(2, "Plat 2", 20.0, ingredients);
        PlatAuMenu plat3 = new PlatAuMenu(3, "Plat 3", 30.0, ingredients);
        menu.ajoute(plat1);
        menu.ajoute(plat2);
        menu.ajoute(plat3);

        IIterateur<PlatAuMenu> iterateur = menu.creerIterateur();
        assertEquals(plat1, iterateur.courant(), "Le premier plat devrait être le plat courant.");
        iterateur.positionSuivante();
        assertEquals(plat2, iterateur.courant(), "Le deuxième plat devrait être le plat courant.");
        iterateur.positionSuivante();
        assertEquals(plat3, iterateur.courant(), "Le troisième plat devrait être le plat courant.");
    }

}
