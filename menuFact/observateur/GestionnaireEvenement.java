package observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gestionnaire d'évènements
 */
public class GestionnaireEvenement {
    Map<String, List<EcouteurEvenement>> evenements = new HashMap<>();
    private static GestionnaireEvenement instance;

    public static String evenementAjoutPlatChoisi = "ajoutPlatChoisi";

    public static GestionnaireEvenement getInstance(String... typeEvenements) {
        if (instance == null) {
            instance = new GestionnaireEvenement(typeEvenements);
        }
        if (typeEvenements.length > 0) {
            instance.ajoutListeEvenements(typeEvenements);
        }
        return instance;
    }
    private GestionnaireEvenement(String... typeEvenements) {
        for (String type : typeEvenements) {
            evenements.put(type, new ArrayList<EcouteurEvenement>());
        }
    }

    public void ajoutListeEvenements(String... typeEvenements) {
        for (String type : typeEvenements) {
            if (!evenements.containsKey(type))
                evenements.put(type, new ArrayList<EcouteurEvenement>());

        }
    }

    public void abonner(String typeEvenement, EcouteurEvenement ecouteurEvenement) {
        List<EcouteurEvenement> ecouteurs = evenements.get(typeEvenement);
        ecouteurs.add(ecouteurEvenement);
    }

    public void desabonner(String typeEvenement, EcouteurEvenement ecouteurEvenement) {
        List<EcouteurEvenement> ecouteurs = evenements.get(typeEvenement);
        ecouteurs.remove(ecouteurEvenement);
    }

    public void notifier(String typeEvenement, String donnees) {
        List<EcouteurEvenement> ecouteurs = evenements.get(typeEvenement);
        for (EcouteurEvenement ecouteur : ecouteurs) {
            ecouteur.mettreAJour(typeEvenement, donnees);
        }

    }

}
