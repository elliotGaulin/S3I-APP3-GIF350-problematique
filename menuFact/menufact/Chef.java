package menufact;

public class Chef implements EcouteurEvenement{
    @Override
    public void mettreAJour(String typeEvenement, String donnees) {
        System.out.println(typeEvenement + " " + donnees);
    }
}
