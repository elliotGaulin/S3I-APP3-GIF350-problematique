package menufact.plats;

import ingredients.IngredientInventaire;
import menufact.plats.PlatAuMenu;

import java.util.ArrayList;

/**
 * Un plat sain du menu
 */
public class PlatSante extends PlatAuMenu {
    private double kcal;
    private double chol;
    private double gras;


    /**
     * Constructeur
     * @param code le code du plat
     * @param description la description du plat
     * @param prix le prix du plat
     * @param ingredients les ingredients du plat
     * @param kcal les calories du plat
     * @param chol le cholesterol du plat
     * @param gras les gras du plat
     */
    public PlatSante(int code, String description, double prix, ArrayList<IngredientInventaire> ingredients, double kcal, double chol, double gras) {
        super(code, description, prix, ingredients);
        this.kcal = kcal;
        this.chol = chol;
        this.gras = gras;
    }

    public PlatSante() {
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatSante{" +
                "kcal=" + kcal +
                ", chol=" + chol +
                ", gras=" + gras +
                "} " + super.toString();
    }

    /**
     * Getters
     */
    public double getKcal() {
        return kcal;
    }

    public double getChol() {
        return chol;
    }

    public double getGras() {
        return gras;
    }
}
