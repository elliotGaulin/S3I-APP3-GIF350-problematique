package menufact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionnaireEvenement {
    Map<String, List<EcouteurEvenement>> evenements = new HashMap<>();
    public GestionnaireEvenement(String... typeEvenements) {
        for (String type : typeEvenements) {
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
