package ingredients;

/**
 * Classe abstraite Ingredient
 */
public abstract class Ingredient {
    private String nom;
    private String description;
    private TypeIngredient typeIngredient;
    private String uniteDeMesure;

    public abstract Ingredient creerIngredient(String nom, String description, String uniteDeMesure);

    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter de la description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter de la description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter de l'unité de mesure
     * @return
     */
    public String getUniteDeMesure() {
        return uniteDeMesure;
    }

    /**
     * Setter de l'unité de mesure
     * @param uniteDeMesure
     */
    public void setUniteDeMesure(String uniteDeMesure) {
        this.uniteDeMesure = uniteDeMesure;
    }

    /**
     * Getter du type d'ingrédient
     * @return
     */
    public TypeIngredient getTypeIngredient() {
        return typeIngredient;
    }

    /**
     * Setter du type d'ingrédient
     * @param typeIngredient
     */
    public void setTypeIngredient(TypeIngredient typeIngredient) {
        this.typeIngredient = typeIngredient;
    }

    /**
     * Méthode toString
     * @return
     */
    @Override
    public String toString() {
        return "menuFact.Ingredient [nom=" + nom + ", description=" + description + ", typeIngredient=" +
                typeIngredient + ", uniteDeMesure=" +
                uniteDeMesure + "]";
    }
}
