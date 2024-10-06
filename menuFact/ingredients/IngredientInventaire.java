package ingredients;

import ingredients.exceptions.IngredientException;

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

    public Ingredient getIngredient() { return ingredient; }

    public int getQuantite() {
        return quantite;
    }

    private void setQuantite(int quantite) throws IngredientException{

        if (quantite < 0)
            throw new IngredientException("Il n'est pas possible d'avoir une quantité negative");
        else
            this.quantite = quantite;
    }

    public void mettreAJourQuantite(int quantite) throws IngredientException{
        int nouvelle_quantite = this.quantite - quantite;
        setQuantite(quantite);
    }
}
