package menufact.observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gestionnaire d'évènements
 */
public class GestionnaireEvenement {
    Map<String, List<IEcouteurEvenement>> evenements = new HashMap<>();
    private static GestionnaireEvenement instance;

    public static String evenementAjoutPlatChoisi = "ajoutPlatChoisi";

    /**
     * Retourne l'instance du gestionnaire d'évènements
     *
     * @param typeEvenements Les types d'évènements à gérer
     * @return L'instance du gestionnaire d'évènements
     */
    public static GestionnaireEvenement getInstance(String... typeEvenements) {
        if (instance == null) {
            instance = new GestionnaireEvenement(typeEvenements);
        }
        if (typeEvenements.length > 0) {
            instance.ajoutListeEvenements(typeEvenements);
        }
        return instance;
    }

    /**
     * Constructeur privé du gestionnaire d'évènements
     *
     * @param typeEvenements Les types d'évènements à gérer
     */
    private GestionnaireEvenement(String... typeEvenements) {
        for (String type : typeEvenements) {
            evenements.put(type, new ArrayList<IEcouteurEvenement>());
        }
    }

    /**
     * Ajoute une liste d'évènements à gérer
     *
     * @param typeEvenements Les types d'évènements à gérer
     */
    public void ajoutListeEvenements(String... typeEvenements) {
        for (String type : typeEvenements) {
            if (!evenements.containsKey(type))
                evenements.put(type, new ArrayList<IEcouteurEvenement>());

        }
    }

    /**
     * Abonne un écouteur à un évènement
     *
     * @param typeEvenement     Le type d'évènement
     * @param ecouteurEvenement L'écouteur à abonner
     */
    public void abonner(String typeEvenement, IEcouteurEvenement ecouteurEvenement) {
        List<IEcouteurEvenement> ecouteurs = evenements.get(typeEvenement);
        ecouteurs.add(ecouteurEvenement);
    }

    /**
     * Désabonne un écouteur à un évènement
     *
     * @param typeEvenement     Le type d'évènement
     * @param ecouteurEvenement L'écouteur à désabonner
     */
    public void desabonner(String typeEvenement, IEcouteurEvenement ecouteurEvenement) {
        List<IEcouteurEvenement> ecouteurs = evenements.get(typeEvenement);
        ecouteurs.remove(ecouteurEvenement);
    }

    /**
     * Notifie les écouteurs d'un évènement
     *
     * @param typeEvenement Le type d'évènement
     * @param donnees       Les données à envoyer
     */
    public void notifier(String typeEvenement, String donnees) {
        List<IEcouteurEvenement> ecouteurs = evenements.get(typeEvenement);
        for (IEcouteurEvenement ecouteur : ecouteurs) {
            ecouteur.mettreAJour(typeEvenement, donnees);
        }
    }
}
