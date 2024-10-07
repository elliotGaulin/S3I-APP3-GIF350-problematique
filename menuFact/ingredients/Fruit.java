package ingredients;

/**
 * Classe Fruit pour représenter
 * les fruits
 */
public class Fruit extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.FRUIT, new Fruit());
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
    public Fruit creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.FRUIT);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
