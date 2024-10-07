package ingredients;

/**
 * Classe Epice pour représenter
 * les épices
 */
public class Epice extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.EPICE, new Epice());
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
    public Epice creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.EPICE);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
