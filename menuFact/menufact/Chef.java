package menufact;

/**
 * Un Chef pour le système menuFact
 */
public class Chef implements EcouteurEvenement{

    private String nom;
    @Override
    public void mettreAJour(String typeEvenement, String donnees) {
        System.out.println(typeEvenement + " " + donnees);
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Chef{" + "nom=" + nom + '}';
    }
}
