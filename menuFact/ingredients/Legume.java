package ingredients;

public class Legume extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.LEGUME, new Legume());
    }

    @Override
    public Legume creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.LEGUME);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
