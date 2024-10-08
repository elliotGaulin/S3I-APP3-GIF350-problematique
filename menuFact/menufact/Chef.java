package menufact;

/**
 * Un Chef pour le système menuFact
 */
public class Chef implements EcouteurEvenement{
    @Override
    public void mettreAJour(String typeEvenement, String donnees) {
        System.out.println(typeEvenement + " " + donnees);
    }
}
