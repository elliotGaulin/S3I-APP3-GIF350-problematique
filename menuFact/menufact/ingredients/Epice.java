package menufact.ingredients;

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
        Epice e = new Epice();
        e.setNom(nom);
        e.setDescription(description);
        e.setUniteDeMesure(uniteDeMesure);
        e.setTypeIngredient(TypeIngredient.EPICE);
        return e;

    }
}
