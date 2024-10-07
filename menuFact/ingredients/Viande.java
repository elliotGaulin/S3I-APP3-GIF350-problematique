package ingredients;

public class Viande extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.VIANDE, new Viande());
    }

    @Override
    public Viande creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.VIANDE);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
