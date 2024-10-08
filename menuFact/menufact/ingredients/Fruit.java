package menufact.ingredients;

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
        Fruit f = new Fruit();
        f.setNom(nom);
        f.setDescription(description);
        f.setUniteDeMesure(uniteDeMesure);
        f.setTypeIngredient(TypeIngredient.FRUIT);
        return f;

    }
}
