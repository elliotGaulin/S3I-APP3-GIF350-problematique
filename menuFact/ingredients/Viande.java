package ingredients;

/**
 * Classe Viande pour représenter
 * les viandes
 */
public class Viande extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.VIANDE, new Viande());
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
    public Viande creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.VIANDE);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
