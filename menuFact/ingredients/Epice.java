package ingredients;

public class Epice extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.EPICE, new Epice());
    }

    @Override
    public Epice creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.EPICE);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
