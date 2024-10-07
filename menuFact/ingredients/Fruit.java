package ingredients;

public class Fruit extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.FRUIT, new Fruit());
    }

    @Override
    public Fruit creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.FRUIT);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
