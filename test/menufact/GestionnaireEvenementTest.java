package menufact;

import menufact.observateur.Chef;
import menufact.observateur.GestionnaireEvenement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireEvenementTest {

    private GestionnaireEvenement gestionnaireEvenement;
    private Chef chef;

    // Pour capturer la sortie console
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Initialisation du gestionnaire d'événements avec différents types
        gestionnaireEvenement = GestionnaireEvenement.getInstance("type1", "type2");

        chef = new Chef("Chef");

        // Permet de capturer les messages de System.out
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        gestionnaireEvenement.desabonner("type1", chef);
        gestionnaireEvenement.desabonner("type2", chef);

    }


    @Test
    void abonner() {
        gestionnaireEvenement.abonner("type1", chef);

        gestionnaireEvenement.notifier("type1", "Plat prêt");

        assertEquals("type1 Plat prêt", outputStreamCaptor.toString().trim(),
                "Le chef devrait être notifié et afficher l'événement correctement.");
    }

    @Test
    void desabonner() {
        gestionnaireEvenement.abonner("type1", chef);
        gestionnaireEvenement.desabonner("type1", chef);

        gestionnaireEvenement.notifier("type1", "Plat prêt");

        assertEquals("", outputStreamCaptor.toString().trim(),
                "Le chef ne devrait pas être notifié après avoir été désabonné.");
    }

    @Test
    void notifier() {
        gestionnaireEvenement.abonner("type1", chef);
        gestionnaireEvenement.abonner("type2", chef);

        gestionnaireEvenement.notifier("type1", "Premier plat");
        gestionnaireEvenement.notifier("type2", "Deuxième plat");

        String[] output = outputStreamCaptor.toString().split(System.lineSeparator());

        assertEquals("type1 Premier plat", output[0].trim(),
                "Le chef devrait être notifié du premier événement.");
        assertEquals("type2 Deuxième plat", output[1].trim(),
                "Le chef devrait être notifié du deuxième événement.");
    }
}
