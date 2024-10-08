package menufact.plats;

import menufact.ingredients.IngredientInventaire;

import java.util.ArrayList;

/**
 * Plat au menu
 */
public class PlatAuMenu {
    private int code;
    private String description;
    private double prix;
    private ArrayList<IngredientInventaire> ingredients;

    public PlatAuMenu(int code, String description, double prix, ArrayList<IngredientInventaire> ingredients) {
        this.code = code;
        this.description = description;
        this.prix = prix;
        this.ingredients = ingredients;
    }

    public PlatAuMenu() {
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatAuMenu{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", ingredients=" + ingredients +
                "}\n";
    }

    /**
     * @return le code du plat
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code le code du plat
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return la description du plat
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description la description du plat
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return le prix du plat
     */
    public double getPrix() {
        return prix;
    }

    /**
     * @param prix le prix du plat
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * @return les ingrédients du plat
     */
    public ArrayList<IngredientInventaire> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients les ingrédients du plat
     */
    public void setIngredients(ArrayList<IngredientInventaire> ingredients) {
        this.ingredients = ingredients;
    }
}
