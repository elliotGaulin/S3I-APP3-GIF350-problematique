package ingredients;

/**
 * Classe Legume pour représenter les
 * légumes
 */
public class Legume extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.LEGUME, new Legume());
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
    public Legume creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.LEGUME);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
