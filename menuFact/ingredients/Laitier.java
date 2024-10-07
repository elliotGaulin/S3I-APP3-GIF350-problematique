package ingredients;

/**
 * Classe Laitier représentant
 * les produits laitier
 */
public class Laitier extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.LAITIER, new Laitier());
    }

    /**
     * Surcharge du constructeur de la Classe
     * Ingredient
     * @param nom
     * @param description
     * @param uniteDeMesure
     * @return
     */
    @Override
    public Laitier creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.LAITIER);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
