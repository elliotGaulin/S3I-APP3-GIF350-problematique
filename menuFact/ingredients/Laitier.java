package ingredients;

public class Laitier extends Ingredient{

    static {
        FabriqueIngredient.getInstance().enregistrerIngredient(TypeIngredient.LAITIER, new Laitier());
    }

    @Override
    public Laitier creerIngredient(String nom, String description, String uniteDeMesure) {
        this.setNom(nom);
        this.setDescription(description);
        this.setTypeIngredient(TypeIngredient.LAITIER);
        this.setUniteDeMesure(uniteDeMesure);

        return this;

    }
}
