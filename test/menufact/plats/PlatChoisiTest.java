package menufact.plats;

import menufact.ingredients.IngredientInventaire;
import menufact.plats.exceptions.PlatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PlatChoisiTest {

    private PlatAuMenu platAuMenu;
    private PlatChoisi platChoisi;
    ArrayList<IngredientInventaire> ingredients;

    @BeforeEach
    void setUp() throws PlatException {

        platAuMenu = new PlatAuMenu(1, "Spaghetti", 10.99, ingredients);
        platChoisi = new PlatChoisi(platAuMenu, 2); // Quantité de 2
    }

    @Test
    void testToString() {
        String expected = "menufact.plats.PlatChoisi{quantite=2," + " plat=" + platAuMenu.toString() + "}";
        assertTrue(Objects.equals(platChoisi.toString(), expected));
    }

    @Test
    void getQuantite() {
        assertEquals(2, platChoisi.getQuantite());
    }

    @Test
    void setQuantite() throws PlatException {
        platChoisi.setQuantite(5);
        assertEquals(5, platChoisi.getQuantite());
    }

    @Test
    void getPlat() {
        assertEquals(platAuMenu, platChoisi.getPlat());
    }

    @Test
    void getEtat() {
        assertTrue(platChoisi.getEtat() instanceof PlatCommandee);
    }

    @Test
    void setEtat() {
        PlatEtat newEtat = new PlatEnPreparation(platChoisi);
        platChoisi.setEtat(newEtat);
        assertEquals(newEtat, platChoisi.getEtat());
    }

    @Test
    void commandee() throws PlatException {
        platChoisi.commandee();
        assertTrue(platChoisi.getEtat() instanceof PlatCommandee);
    }

    @Test
    void preparee() throws PlatException {
        platChoisi.preparee();
        assertTrue(platChoisi.getEtat() instanceof PlatEnPreparation);
    }

    @Test
    void terminee() throws PlatException {
        platChoisi.preparee();
        platChoisi.terminee();
        assertTrue(platChoisi.getEtat() instanceof PlatTerminee);
    }

    @Test
    void servir() throws PlatException {
        platChoisi.preparee();
        platChoisi.terminee();
        platChoisi.servir();
        assertTrue(platChoisi.getEtat() instanceof PlatServi);
    }

    @Test
    void impossibleServir() throws PlatException {
        platChoisi.impossibleServir();
        assertTrue(platChoisi.getEtat() instanceof PlatImpossibleServir);
    }
}
