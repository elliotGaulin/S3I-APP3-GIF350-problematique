package menufact.ingredients;

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
        Viande v = new Viande();
        v.setNom(nom);
        v.setDescription(description);
        v.setUniteDeMesure(uniteDeMesure);
        v.setTypeIngredient(TypeIngredient.VIANDE);
        return v;

    }
}
