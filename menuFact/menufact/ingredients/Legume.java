package menufact.ingredients;

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
        Legume l = new Legume();
        l.setNom(nom);
        l.setDescription(description);
        l.setUniteDeMesure(uniteDeMesure);
        l.setTypeIngredient(TypeIngredient.LEGUME);
        return l;

    }
}
