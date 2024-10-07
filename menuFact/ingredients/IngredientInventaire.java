package ingredients;

import ingredients.exceptions.IngredientException;

/**
 * Représente l'inventaire pour un Ingredient
 */
public class IngredientInventaire {
    private Ingredient ingredient;
    private int quantite;

    public IngredientInventaire(Ingredient ingredient, int quantite) throws IngredientException {
        if (quantite < 0) {
            throw new IngredientException("Il n'est pas possible d'avoir une quantité négative");
        }
        this.ingredient = ingredient;
        this.quantite = quantite;
    }

    /**
     *
     * @return l'ingrédient
     */
    public Ingredient getIngredient() { return ingredient; }

    /**
     *
     * @return la quantite de l'ingrédient
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     *
     * @param quantite la quantite de l'ingrédient
     * @throws IngredientException
     */
    public void setQuantite(int quantite) throws IngredientException{

        if (quantite < 0)
            throw new IngredientException("Il n'est pas possible d'avoir une quantité negative");
        else
            this.quantite = quantite;
    }

    /**
     * Surcharge de la méthode toString()
     * @return
     */
    @Override
    public String toString() {
        return this.ingredient.toString() + ", quantite:" + this.quantite;
    }
}
