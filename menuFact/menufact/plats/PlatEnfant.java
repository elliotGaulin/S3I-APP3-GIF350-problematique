package menufact.plats;

import menufact.ingredients.IngredientInventaire;

import java.util.ArrayList;

/**
 * Un plat enfant
 */
public class PlatEnfant extends PlatAuMenu{
    private double proportion;

    public PlatEnfant() {
    }

    public PlatEnfant(int code, String description, double prix, ArrayList<IngredientInventaire> ingredients, double proportion) {
        super(code, description, prix, ingredients);
        this.proportion = proportion;
    }

    public double getProportion() {
        return proportion;
    }

    @Override
    public String toString() {
        return "PlatEnfant{" +
                "proportion=" + proportion +
                "} " + super.toString();
    }
}
