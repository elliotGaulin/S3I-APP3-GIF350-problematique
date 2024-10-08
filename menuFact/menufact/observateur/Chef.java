package menufact.observateur;

/**
 * Un Chef pour le système menuFact
 */
public class Chef implements IEcouteurEvenement {

    private String nom;

    /**
     * Constructeur du chef
     * @param nom Le nom du chef
     */
    public Chef(String nom) {
        this.nom = nom;
    }

    /**
     * Met à jour le chef avec un évènement
     * @param typeEvenement Le type de l'évènement
     * @param donnees Les données de l'évènement
     */
    @Override
    public void mettreAJour(String typeEvenement, String donnees) {
        System.out.println(typeEvenement + " " + donnees);
    }

    /**
     * Getter du nom
     * @return Le nom du chef
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom
     * @param nom Le nom du chef
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Chef{" + "nom=" + nom + '}';
    }
}
