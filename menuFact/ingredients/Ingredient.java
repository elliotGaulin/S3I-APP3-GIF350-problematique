package ingredients;

public abstract class Ingredient {
    private String nom;
    private String description;
    private TypeIngredient typeIngredient;
    private String uniteDeMesure;

    public abstract Ingredient creerIngredient(String nom, String description, String uniteDeMesure);

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniteDeMesure() { return uniteDeMesure; }

    public void setUniteDeMesure(String uniteDeMesure) { this.uniteDeMesure = uniteDeMesure; }

    public TypeIngredient getTypeIngredient() {
        return typeIngredient;
    }

    public void setTypeIngredient(TypeIngredient typeIngredient) {
        this.typeIngredient = typeIngredient;
    }
}
